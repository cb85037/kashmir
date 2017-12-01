package Negocio;
import java.io.Serializable;
import java.sql.Date;

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

import repositorio.ItemAReponerDTO;
import repositorio.ItemIngredienteDTO;

@Entity
@Table(name="itemAReponer")
public class ItemAReponer implements Serializable {
	@ManyToOne
	@JoinColumn(name="id_producto")
	private Producto producto;
	@ManyToOne
	@JoinColumn(name="id_restaurante")
	private Restaurante restaurante;
	@ManyToOne
	@JoinColumn(name="id_area")
	private AreaPreparacion area;
	private int cantidad;
	private boolean depositoCentral;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_itemAReponer")
	private int id;
	private Date fecha;
	
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public Restaurante getRestaurante() {
		return restaurante;
	}
	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}
	public AreaPreparacion getArea() {
		return area;
	}
	public void setArea(AreaPreparacion area) {
		this.area = area;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public boolean isDepositoCentral() {
		return depositoCentral;
	}
	public void setDepositoCentral(boolean depositoCentral) {
		this.depositoCentral = depositoCentral;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ItemAReponer() {
		super();
		this.producto = producto;
		this.restaurante = restaurante;
		this.area = area;
		this.cantidad = cantidad;
		this.depositoCentral = depositoCentral;
		this.id = id;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	/***********************************
	 ******METODOS DTO******************
	 ***********************************/
	
	public ItemAReponerDTO generarItemAReponerDTO(){
		ItemAReponerDTO dto = new ItemAReponerDTO();
	//	if((this.id != 0)){
			dto.setId(id);
			if(area != null){
				dto.setArea(this.area.getNombreSector());
			} else {
				dto.setArea("");
			}
			dto.setCantidad(cantidad);
			dto.setDepositoCentral(depositoCentral);
			dto.setFecha(fecha);
			dto.setProducto(producto.generarDTOdeProducto());
			if(restaurante != null){
				dto.setRestaurante(restaurante.getId());
			} else {
				dto.setRestaurante(0);
			}
			
		//}
		return dto;
	}
	
}
