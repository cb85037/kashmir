package Negocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import repositorio.Cauducidad;
import repositorio.OrigenDestino;
import repositorio.ProductoDTO;
import repositorio.ProveedorDTO;
import repositorio.UsuarioDTO;

@Entity
@Table(name="productos")
public class Producto implements Serializable{
	
	private static final long serialVersionUID = 7933908845328930824L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id_producto")
	private int id;
	@Column
	private String nombre;
	@Column
	private Cauducidad cauducidad;
	@Column
	private OrigenDestino origenDestino;
	@ManyToMany
	private List<Provedor> proveedores;
	
	public Producto() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Cauducidad getCauducidad() {
		return cauducidad;
	}
	public void setCauducidad(Cauducidad cauducidad) {
		this.cauducidad = cauducidad;
	}
	public OrigenDestino getOrigenDestino() {
		return origenDestino;
	}
	public void setOrigenDestino(OrigenDestino origenDestino) {
		this.origenDestino = origenDestino;
	}
	public List<Provedor> getProveedores() {
		return proveedores;
	}
	public void setProveedores(List<Provedor> proveedores) {
		this.proveedores = proveedores;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/***********************************
	 ******METODOS DTO******************
	 ***********************************/
	
	public ProductoDTO generarDTOdeProducto(){
		ProductoDTO dto = new ProductoDTO();
		if((this.id != 0)){
			dto.setId(id);
			dto.setCauducidad(this.cauducidad);
			dto.setNombre(nombre);
			dto.setOrigenDestino(origenDestino);
		}
		return dto;
	}
	
	public static Producto crearInstanciaDeProducto(ProductoDTO dto){
		Producto p = new Producto();
		p.setCauducidad(dto.getCauducidad());
		p.setId(dto.getId());
		p.setNombre(dto.getNombre());
		p.setOrigenDestino(dto.getOrigenDestino());
		return p;
	}
}
