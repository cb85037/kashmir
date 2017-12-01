package Negocio;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="itemLoteCantidad")
public class ItemLoteCantidad implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_itemLoteCantidad")
	private int id;
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="id_itemLoteCantidad")
	private List<ItemProducto> itemProducto;


	private int cantidad;
	private int stockReposicion;
	@ManyToOne
	@JoinColumn(name="id_area")
	private AreaPreparacion area;
	
	@ManyToOne
	@JoinColumn(name="id_producto")
	private Producto producto;
	
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public int getCantidad() {
		return this.getItemProducto().size();
	}
	
	public int getStockReposicion() {
		return stockReposicion;
	}
	public void setStockReposicion(int stockReposicion) {
		this.stockReposicion = stockReposicion;
	}
	
	public ItemLoteCantidad(Producto producto, int cantidad,
			int stockReposicion, AreaPreparacion area) {
		super();
		this.producto = producto;
		this.cantidad = cantidad;
		this.stockReposicion = stockReposicion;
		this.area = area;
	}
	public ItemLoteCantidad() {
		// TODO Auto-generated constructor stub
	}
	public void setArea(AreaPreparacion area) {
		this.area = area;
	}
	public AreaPreparacion getArea() {
		return area;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public List<ItemProducto> getItemProducto() {
		return itemProducto;
	}
	public void setItemProducto(List<ItemProducto> itemProducto) {
		this.itemProducto = itemProducto;
	}
	
	public void AddItemProducto(ItemProducto item){
		if(this.itemProducto == null){
			this.itemProducto = new ArrayList<ItemProducto>();
		}
		this.itemProducto.add(item);
	}
	
	public void RemoveItemProducto(int index){
		if(this.itemProducto == null){
			this.itemProducto = new ArrayList<ItemProducto>();
		}
		
		Iterator<ItemProducto> it = this.itemProducto.iterator();
		while(it.hasNext()){
			ItemProducto item = it.next();
			if(item.getId() == index){
				it.remove();
				break;
			}
		}
	}
}
