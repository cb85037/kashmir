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
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import repositorio.ItemCompraDTO;
import repositorio.ProductoDTO;

@Entity
@Table(name="itemCompra")
public class ItemCompra implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id_itemCompra")
	private int id;
	@ManyToOne
	@JoinColumn(name="id_producto")
	private Producto producto;
	private int cantidad;
	private String calidad;
	private int minimoDiasFechaVencimiento;
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
	public String getCalidad() {
		return calidad;
	}
	public void setCalidad(String calidad) {
		this.calidad = calidad;
	}
	public int getMinimoDiasFechaVencimiento() {
		return minimoDiasFechaVencimiento;
	}
	public void setMinimoDiasFechaVencimiento(int minimoDiasFechaVencimiento) {
		this.minimoDiasFechaVencimiento = minimoDiasFechaVencimiento;
	}
	public ItemCompra(Producto producto, int cantidad, String calidad,
			int minimoDiasFechaVencimiento) {
		super();
		this.producto = producto;
		this.cantidad = cantidad;
		this.calidad = calidad;
		this.minimoDiasFechaVencimiento = minimoDiasFechaVencimiento;
	}
	
	public ItemCompra() {
		// TODO Auto-generated constructor stub
	}
	/***********************************
	 ******METODOS DTO******************
	 ***********************************/
	
	public ItemCompraDTO generarDTO(){
		ItemCompraDTO dto = new ItemCompraDTO();
		if((this.id != 0)){
			dto.setId(id);
			dto.setCalidad(calidad);
			dto.setCantidad(cantidad);
			dto.setMinimoDiasFechaVencimiento(minimoDiasFechaVencimiento);
			dto.setProducto(producto.generarDTOdeProducto());
		}
		return dto;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public static ItemCompra crearInstancia(ItemCompraDTO dto){
		ItemCompra p = new ItemCompra();
		p.setCalidad(dto.getCalidad());
		p.setMinimoDiasFechaVencimiento(dto.getMinimoDiasFechaVencimiento());
		p.setProducto(Producto.crearInstanciaDeProducto(dto.getProducto()));
		p.setCantidad(dto.getCantidad());
		p.setId(dto.getId());
		return p;
	}
}
