package entity;

import java.io.Serializable;



import javax.persistence.*;

import dto.DTO_Plato;


@Entity
@Table(name = "Plato")

public class Entity_Plato implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int codigo;
	
	@Column
	private String nombre;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idReceta")
	private Entity_Receta receta;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idProducto")
	private Entity_Producto producto;
	
	@Column
	private String rubro;
	
	@Column
	private float precio;
	
	@Column
	private float comision;
	
	public Entity_Plato(int codigo, String nombre, Entity_Receta receta, String rubro,
			float precio, float comision) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.receta = receta;
		this.rubro = rubro;
		this.precio = precio;
		this.comision = comision;
	}

	public Entity_Plato() {
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

	public Entity_Receta getReceta() {
		return receta;
	}

	public void setReceta(Entity_Receta receta) {
		this.receta = receta;
	}

	public String getRubro() {
		return rubro;
	}

	public void setRubro(String rubro) {
		this.rubro = rubro;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public float getComision() {
		return comision;
	}

	public void setComision(float comision) {
		this.comision = comision;
	}
	
	

	public Entity_Producto getProducto() {
		return producto;
	}

	public void setProducto(Entity_Producto producto) {
		this.producto = producto;
	}

	public DTO_Plato getDTO() {
		
		DTO_Plato p = new DTO_Plato();
		p.setCodigo(this.codigo);
		p.setComision(this.comision);
		p.setNombre(this.nombre);
		p.setPrecio(this.precio);
		p.setRubro(this.rubro);
		if(this.receta != null)
			p.setReceta(this.receta.getDTO());
		if(this.producto != null && this.receta == null)
			p.setProducto(this.producto.getDTO());
		
		return p;
	}
	
	
	
	
}
