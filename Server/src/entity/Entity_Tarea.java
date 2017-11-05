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

import dto.DTO_Tarea;

@Entity
@Table(name = "Tarea")
public class Entity_Tarea implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "numero")
	private int numero;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idProducto")
	private Entity_Producto producto;
	
	@Column(name = "cantidad")
	private int cantidad;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idSucursal")
	private Entity_Local sucursal;
	
	@Column(name = "tiempoRequerido")
	private float tiempoRequerido;
	
	@Column(name = "estado")
	private String estado;
	

	public Entity_Tarea() {
		super();
		// TODO Auto-generated constructor stub
	}



	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
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

	public Entity_Local getSucursal() {
		return sucursal;
	}

	public void setSucursal(Entity_Local sucursal) {
		this.sucursal = sucursal;
	}

	public float getTiempoRequerido() {
		return tiempoRequerido;
	}

	public void setTiempoRequerido(float tiempoRequerido) {
		this.tiempoRequerido = tiempoRequerido;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public DTO_Tarea getDTO() {
		DTO_Tarea t = new DTO_Tarea();
		
		t.setCantidad(this.cantidad);
		t.setEstado(this.estado);
		t.setTiempoRequerido(this.tiempoRequerido);
		t.setNumero(this.numero);
		t.setSucursal(this.sucursal.getDTO());
		t.setProducto(this.producto.getDTO());
		
		return t;
	}
	
	
	
	
}
