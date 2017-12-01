package Negocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import exceptions.AreaPreparacionException;

import repositorio.Cauducidad;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipoArea",discriminatorType=DiscriminatorType.STRING)
@Table(name="areaspreparaciones")
public abstract class AreaPreparacion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8262815225893306857L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_area")
	private int id;

	@Fetch(FetchMode.SELECT)
	@OneToMany(cascade= CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="id_area")
	private List<ItemLoteCantidad> itemsEstimados;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<ItemLoteCantidad> getItemsEstimados() {
		return itemsEstimados;
	}
	public void setItemsEstimados(List<ItemLoteCantidad> itemsEstimados) {
		this.itemsEstimados = itemsEstimados;
	}
	public Carta getCartaActual() {
		return cartaActual;
	}
	public void setCartaActual(Carta cartaActual) {
		this.cartaActual = cartaActual;
	}
	public JefeAreaPreparacion getJefe() {
		return jefe;
	}
	public void setJefe(JefeAreaPreparacion jefe) {
		this.jefe = jefe;
	}
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_carta")
	private Carta cartaActual;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_rol")
	private JefeAreaPreparacion jefe;
	
	public abstract String getNombreSector();
	
	public void LimpiarItemsVencidos() {
		Date hoy = Calendar.getInstance().getTime();
		for (ItemLoteCantidad itemLote : itemsEstimados) {
			if(itemLote.getCantidad() > 0){
				Iterator<ItemProducto> it = itemLote.getItemProducto().iterator();
				while(it.hasNext()){
					ItemProducto itProd = it.next();
					if(itProd.getFechaVencimiento().compareTo(hoy) < 0){
						it.remove();
					}
				}
			}
		}
	}
	
	public void AgregarItemProducto(ItemProducto it){
		ItemLoteCantidad itLC = buscarItemLote(it.getProducto());
		if(itLC != null){
			itLC.AddItemProducto(it);
		} 
	}
	
	public void AgregarItemEstimado(ItemLoteCantidad itLote){
		if(this.itemsEstimados == null){
			this.itemsEstimados = new ArrayList<ItemLoteCantidad>();
		}
		
		this.itemsEstimados.add(itLote);
	}
	
	public ItemLoteCantidad buscarItemLote(Producto p){
		for (ItemLoteCantidad it : this.getItemsEstimados()) {
			if(it.getProducto().getId() == p.getId()){
				return it;
			}
		}
		return null;
	}
	
	public ItemLoteCantidad buscarItemLote(Plato p){
		for (ItemLoteCantidad it : this.getItemsEstimados()) {
			if(it.getProducto().getNombre() == p.getNombre()){
				return it;
			}
		}
		return null;
	}
	
	public List<ItemAReponer> GetItemsAReponer(){
		List<ItemAReponer> items = new ArrayList<ItemAReponer>();
		for (ItemLoteCantidad itLote : itemsEstimados) {
			int cantidad = itLote.getStockReposicion() - itLote.getCantidad();
			if(itLote.getStockReposicion() > 0 && cantidad > 0){
				
				ItemAReponer itR = new ItemAReponer();
				itR.setArea(this);
				itR.setCantidad(cantidad);
				boolean depoCentral = !(itLote.getProducto().getCauducidad() == Cauducidad.ALTA);
				itR.setDepositoCentral(depoCentral);
				itR.setProducto(itLote.getProducto());
				items.add(itR);
			}
			
		}
		
		return items;
	}
	
	public ItemCarta getItemCartaByNombre(String p) {
		// TODO Auto-generated method stub
		for (ItemCarta it : this.getCartaActual().getPlatos()) {
			if(it.getPlato().getNombre().equals(p)){
				return it;
			}
		}
		
		return null;
	}
	

	public void prepararPlato(Plato plato, int cantidad) throws AreaPreparacionException {
		// TODO Auto-generated method stub
		for (int i = 0; i <= cantidad - 1; i++) {
			for (ItemIngrediente ingrediente : plato.getIngredientes()) {
				Producto prod = ingrediente.getProducto();
				ItemLoteCantidad itLote = this.buscarItemLote(prod);
				if(itLote.getCantidad() > 0){
					itLote.RemoveItemProducto(0);
				} else {
					throw new AreaPreparacionException("No hay ingredientes para realizar el pedido"
							+ " falta : " + prod.getNombre());
				}
			}
		}
	}
}
