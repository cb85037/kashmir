package Negocio;
import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="itemCaja")
public class ItemCaja implements Serializable, Comparable<ItemCaja> {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_itemCaja")
	private int id;
	private int cantidad;
	private float valor;
	private String origen;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	public String getOrigen() {
		return origen;
	}
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	public ItemCaja() {
		super();
		this.id = id;
		this.cantidad = cantidad;
		this.valor = valor;
		this.origen = origen;
	}
	
	@Override
	public int compareTo(ItemCaja o) {
		// TODO Auto-generated method stub
		if(this.origen.compareToIgnoreCase(o.getOrigen()) == 0){
			return Float.compare(this.valor, o.getValor());
		}
		
		return this.origen.compareToIgnoreCase(o.getOrigen());
	}
	
	
}
