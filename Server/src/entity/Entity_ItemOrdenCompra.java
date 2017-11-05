package entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ItemOrdenCompra")
public class Entity_ItemOrdenCompra implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "producto", referencedColumnName = "codigo")
	private Entity_Producto producto;
	private int cantidad;
	private String calidadEsperada;
	
	public Entity_ItemOrdenCompra(Entity_Producto producto, int cantidad, String calidadEsperada) {
		super();
		this.producto = producto;
		this.cantidad = cantidad;
		this.calidadEsperada = calidadEsperada;
	}

	public Entity_Producto getProducto() {
		return producto;
	}

	public void setProducto(Entity_Producto producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getCalidadEsperada() {
		return calidadEsperada;
	}

	public void setCalidadEsperada(String calidadEsperada) {
		this.calidadEsperada = calidadEsperada;
	}
	
	
	
	
}
