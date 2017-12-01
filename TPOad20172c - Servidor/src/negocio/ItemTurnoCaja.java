package Negocio;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import exceptions.CajaException;

@Entity
@Table(name="turnoCaja")
public class ItemTurnoCaja implements Serializable {
	private boolean finalizado;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_empleado")
	private Cajero cajeroEncargado;
	private float total;
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name="id_itemTurnoCaja")
	private List<ItemCaja> items;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_itemTurnoCaja")
	private int id;
	
	public ItemTurnoCaja(boolean finalizado, Cajero cajeroEncargado,
			float total, List<ItemCaja> items) {
		super();
		this.finalizado = finalizado;
		this.cajeroEncargado = cajeroEncargado;
		this.total = total;
		this.items = items;
	}

	public ItemTurnoCaja() {
		// TODO Auto-generated constructor stub
	}

	public boolean isFinalizado() {
		return finalizado;
	}

	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}

	public Cajero getCajeroEncargado() {
		return cajeroEncargado;
	}

	public void setCajeroEncargado(Cajero cajeroEncargado) {
		this.cajeroEncargado = cajeroEncargado;
	}

	public float getTotal() {
		float total = 0;
		for (ItemCaja it : this.getItems()) {
			total = total + (it.getCantidad() * it.getValor());
		}
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public List<ItemCaja> getItems() {
		if(this.items == null){
			this.items = new ArrayList<ItemCaja>();
		}
		
		return this.items;
	}

	public void setItems(List<ItemCaja> items) {
		this.items = items;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void AgregarItem(ItemCaja it3) {
		// TODO Auto-generated method stub
		if(this.items == null){
			this.items = new ArrayList<ItemCaja>();
		}
		boolean exist = false;
		for (ItemCaja itCaja : this.items) {
			if(it3.getOrigen().toUpperCase().equals(itCaja.getOrigen().toUpperCase()) && it3.getValor() == itCaja.getValor()){
				itCaja.setCantidad(it3.getCantidad() + itCaja.getCantidad());
				exist = true;
			}
		}
		
		if(!exist){
			this.items.add(it3);
		}
	}
	
	public void RemoveItem(int id){
		if(this.items == null){
			this.items = new ArrayList<ItemCaja>();
		}
		
		Iterator<ItemCaja> it = this.items.iterator();
		while(it.hasNext()){
			ItemCaja item = it.next();
			if(item.getId() == id){
				it.remove();
				break;
			}
		}
	}

	public float AgregarItemsPorFactura(Factura f) throws CajaException {
		// TODO Auto-generated method stub
		float vuelto = 0;
		if(f.getItemsCaja().size() == 0){
			throw new CajaException("No hay nada para cobrar");
		}
		
		if(f.getTotal() > f.getTotalPago()){
			throw new CajaException("Falta pagar : " + (f.getTotal() - f.getTotalPago()));
		}
		
		if(f.getTotal() == f.getTotalPago()){
			vuelto = 0;
		}
		
		if(f.getTotalPago() > f.getTotal()){
			vuelto = f.getTotalPago() - f.getTotal();
		}
		
		Iterator<ItemCaja> items =  f.getItemsCaja().iterator();
		while(items.hasNext()){
			ItemCaja it = items.next();
			this.AgregarItem(it);
			items.remove();
		}
		
		return vuelto;
	}
}
