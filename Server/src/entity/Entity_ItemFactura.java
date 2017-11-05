package entity;

import java.io.Serializable;

import javax.persistence.*;


@Entity
@Table(name = "ItemFactura")

public class Entity_ItemFactura implements Serializable{
	
	private static final long serialVersionUID = 1L;

	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idPlato")
	private Entity_Plato plato;
	
	@Column
	private int cantidad;
	
	@Column
	private float precio;
	
	@Column
	private float descuento;
	

	public Entity_ItemFactura() {
		super();
		// TODO Auto-generated constructor stub
	}

	public float getDescuento() {
		return descuento;
	}

	public void setDescuento(float descuento) {
		this.descuento = descuento;
	}

	public Entity_Plato getProducto() {
		return plato;
	}

	public void setProducto(Entity_Plato plato) {
		this.plato = plato;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
}
