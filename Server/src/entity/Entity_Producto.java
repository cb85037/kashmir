package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import dto.DTO_ElaboradoVenta;
import dto.DTO_Producto;

@Entity
@Table(name = "Producto")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn( name="tipo", discriminatorType = DiscriminatorType.STRING)


public class Entity_Producto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "codigo")
	private int codigo;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "caducidad")
	private String caducidad;
	
	@Column(name = "precio")
	private float precio;
	
	

	public Entity_Producto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCaducidad() {
		return caducidad;
	}

	public void setCaducidad(String caducidad) {
		this.caducidad = caducidad;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
	//Aplica para ElaboradoVenta
	public DTO_Producto getDTO() {
		DTO_Producto p = new DTO_ElaboradoVenta();
		p.setCodigo(this.codigo);
		p.setCaducidad(this.caducidad);
		p.setNombre(this.nombre);
		p.setPrecio(this.precio);
		
		return p;
	}
	
	
	
}
