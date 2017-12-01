package Negocio;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="itemDeposito")
public class ItemDeposito implements Serializable {

	private int stockDeseado;
	private int stock;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id_itemDeposito")
	private int id;
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="id_itemDeposito")
	private List<ItemProducto> itemProducto;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_producto")
	private Producto producto;
	
	public List<ItemProducto> getItemProducto() {
		return itemProducto;
	}
	public void setItemProducto(List<ItemProducto> itemProducto) {
		this.itemProducto = itemProducto;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public int getStockDeseado() {
		return stockDeseado;
	}
	public void setStockDeseado(int stockDeseado) {
		this.stockDeseado = stockDeseado;
	}
	public int getStock() {
		return this.getItemProducto().size();
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ItemDeposito() {
		super();
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
