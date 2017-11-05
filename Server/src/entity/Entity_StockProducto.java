package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "StockProducto")
@SequenceGenerator(name="sec", sequenceName="gen_stock")
public class Entity_StockProducto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	@GeneratedValue(generator = "sec")
	private int idStockProducto;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "producto")
	private Entity_Producto producto;
	
	@Column(name = "stockMinimo")
	private int stockMinimo;
	
	@OneToMany(cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name = "stockProducto", referencedColumnName = "id")
	private List<Entity_Lote> lotes;
	
	
	public Entity_StockProducto() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getIdStockProducto() {
		return idStockProducto;
	}

	public void setIdStockProducto(int idStockProducto) {
		this.idStockProducto = idStockProducto;
	}

	public Entity_Producto getProducto() {
		return producto;
	}

	public void setProducto(Entity_Producto producto) {
		this.producto = producto;
	}

	public int getStockMinimo() {
		return stockMinimo;
	}

	public void setStockMinimo(int stockMinimo) {
		this.stockMinimo = stockMinimo;
	}

	public List<Entity_Lote> getLotes() {
		return lotes;
	}

	public void setLotes(List<Entity_Lote> lotes) {
		this.lotes = lotes;
	}
	
	
	
	
	
}
