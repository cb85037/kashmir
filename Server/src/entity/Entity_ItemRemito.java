package entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "ItemRemito")
public class Entity_ItemRemito implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int idMovimiento;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "producto", referencedColumnName = "codigo")
	private Entity_Producto producto;
	private int cantidad;
	private String calidad;
	
	
	public Entity_ItemRemito(Entity_Producto producto, int cantidad, String calidad) {
		super();
		this.producto = producto;
		this.cantidad = cantidad;
		this.calidad = calidad;
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

	public String getCalidad() {
		return calidad;
	}

	public void setCalidad(String calidad) {
		this.calidad = calidad;
	}
	
	
	
}
