package Negocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import repositorio.ProductoDTO;
import repositorio.ProveedorDTO;
import repositorio.Restaurantes;
import repositorio.UsuarioDTO;


@Entity
@Table(name="proveedores")
public class Provedor implements Serializable{
	
	private static final long serialVersionUID = 7571696254242597333L;
		
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_provedor")
	private int provId;
	@Column
	private String nombre;
	@Column
	private boolean disponible;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_restaurante")
	private Restaurante restaurante;
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="productoProveedor",
			joinColumns= { @JoinColumn(name="id_provedor") },
			inverseJoinColumns={ @JoinColumn(name="id_producto") })
	private List<Producto> productos;
	
	public Provedor() {
		super();
	}

	public int getId() {
		return this.provId;
	}

	public void setId(int id) {
		this.provId = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}

	public List<Producto> getProductos() {
		return this.productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	
	/***********************************
	 ******METODOS DTO******************
	 ***********************************/
	
	public ProveedorDTO generarDTOdeProveedor(){
		ProveedorDTO dto = new ProveedorDTO();
		if((this.provId != 0)){
			dto.setDisponible(disponible);
			dto.setProvId(provId);
			dto.setRestaurante(restaurante.getId());
			dto.setNombre(nombre);
			if(this.productos != null){
				Iterator<Producto> it = this.productos.iterator();
				while(it.hasNext()){
					ProductoDTO prod = it.next().generarDTOdeProducto();
					dto.AddProducto(prod);
				}
			}
			
		}
		return dto;
	}
	
	public static Provedor crearInstanciaDeProveedor(ProveedorDTO dto){
		Provedor p = new Provedor();
		p.setDisponible(dto.isDisponible());
		p.setId(dto.getProvId());
		p.setNombre(dto.getNombre());
		
		if(dto.getProductos() != null){
			List<Producto> prods = new ArrayList<Producto>();
			Iterator<ProductoDTO> it = dto.getProductos().iterator();
			while(it.hasNext()){
				Producto prod = Producto.crearInstanciaDeProducto(it.next());
				prods.add(prod);
			}
			p.setProductos(prods);
		}
		
		return p;
	}
}
