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
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import exceptions.EntityInvalidaException;

@Entity
@Table(name="deposito")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipoDeposito",discriminatorType=DiscriminatorType.STRING)
public abstract class Deposito implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_deposito")
	private int id;
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="id_deposito")
	private List<ItemDeposito> itemsDeposito;
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="id_deposito")
	private List<MovimientoStock> itemsMovimientoStock;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<ItemDeposito> getItemsDeposito() {
		return itemsDeposito;
	}
	public void setItemsDeposito(List<ItemDeposito> itemsDeposito) {
		this.itemsDeposito = itemsDeposito;
	}
	public List<MovimientoStock> getItemsMovimientoStock() {
		return itemsMovimientoStock;
	}
	public void setItemsMovimientoStock(List<MovimientoStock> itemsMovimientoStock) {
		this.itemsMovimientoStock = itemsMovimientoStock;
	}
	
	public void LimparItemsVencidos() {
		Date hoy = Calendar.getInstance().getTime();
		
		for (ItemDeposito itemDeposito : this.itemsDeposito) {
			List<Integer> itemsARemover = new ArrayList<Integer>();
			if(itemDeposito.getStock() > 0){
				Iterator<ItemProducto> it = itemDeposito.getItemProducto().iterator();
				while(it.hasNext()){
					ItemProducto itProd = it.next();
					if(itProd.getFechaVencimiento().compareTo(hoy) < 0){
						it.remove();
					}
				}
			}
		}
	}
	

	protected ItemDeposito buscarItemDeposito(Producto producto) {
		// TODO Auto-generated method stub
		for (ItemDeposito it : this.getItemsDeposito()) {
			if(it.getProducto().getId() == producto.getId()){
				return it;
			}
		}
		return null;
	}
	
	public void AgregarItem(ItemProducto itProducto){
		ItemDeposito itemMismoProducto = this.buscarItemDeposito(itProducto.getProducto());
		if(itemMismoProducto != null){
			itemMismoProducto.AddItemProducto(itProducto);
		}
	}
	
	protected void AgregarMovimientoStock(MovimientoStock mov){
		if(this.itemsMovimientoStock == null){
			this.itemsMovimientoStock = new ArrayList<MovimientoStock>();
		}
		
		this.itemsMovimientoStock.add(mov);
	}
	
	protected void RealizarMovimientoDeStock(
			List<ItemAReponer> items, AreaPreparacion area) {
		// TODO Auto-generated method stub

		for (ItemAReponer itemAReponer : items) {
			if(itemAReponer.getArea().getId() == area.getId()){
				ItemDeposito itemMismoProducto = this.buscarItemDeposito(itemAReponer.getProducto());
				if(itemMismoProducto != null){
					int cantidadAReponer = itemAReponer.getCantidad();
					Iterator<ItemProducto> itemProductos = itemMismoProducto.getItemProducto().iterator();
					while(itemProductos.hasNext() && cantidadAReponer > 0){
						ItemProducto it = itemProductos.next();
						area.AgregarItemProducto(it);
						itemProductos.remove();
						cantidadAReponer--;
					}
			}
		}
	}
}
}
