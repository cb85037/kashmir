package Negocio;
import java.io.Serializable;

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

import repositorio.ItemIngredienteDTO;
import repositorio.ProductoDTO;

@Entity
@Table(name="itemIngrediente")
public class ItemIngrediente implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_itemIngrediente")
	private int id;
	@ManyToOne
	@JoinColumn(name="id_producto")
	private Producto producto;
	private int cantidad;
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public ItemIngrediente(Producto producto, int cantidad) {
		super();
		this.producto = producto;
		this.cantidad = cantidad;
	}
	
	public ItemIngrediente() {
		// TODO Auto-generated constructor stub
	}
	/***********************************
	 ******METODOS DTO******************
	 ***********************************/
	
	public ItemIngredienteDTO generarItemIngredienteDTO(){
		ItemIngredienteDTO dto = new ItemIngredienteDTO();
		if((this.id != 0)){
			dto.setId(id);
			dto.setCantidad(cantidad);
			dto.setProducto(producto.generarDTOdeProducto());
		}
		return dto;
	}
	
	public static ItemIngrediente crearInstanciaDeProducto(ItemIngredienteDTO dto){
		ItemIngrediente p = new ItemIngrediente();
		p.setCantidad(dto.getCantidad());
		p.setProducto(Producto.crearInstanciaDeProducto(dto.getProducto()));
		return p;
	}
}
