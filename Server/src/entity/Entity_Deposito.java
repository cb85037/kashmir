package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import dto.DTO_DepositoLocal;
import dto.DTO_Deposito;
import dto.DTO_Movimiento;

@Entity
@Table(name = "Deposito")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
public class Entity_Deposito implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	protected int idDeposito;
	
	@Column(name = "descripcion")
	protected String descripcion;
	
	@OneToMany(cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name = "idDeposito")
	protected List<Entity_StockProducto> productos;
	
	@OneToMany(cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name = "idDeposito")
	protected List<Entity_Movimiento> movimientos;

	
	public Entity_Deposito() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getIdDeposito() {
		return idDeposito;
	}

	public void setIdDeposito(int idDeposito) {
		this.idDeposito = idDeposito;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Entity_StockProducto> getProductos() {
		return productos;
	}

	public void setProductos(List<Entity_StockProducto> productos) {
		this.productos = productos;
	}

	public List<Entity_Movimiento> getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(List<Entity_Movimiento> movimientos) {
		this.movimientos = movimientos;
	}
	
	
	public DTO_Deposito toDTO() {
		
		DTO_Deposito d = new DTO_Deposito();
		
		d.setIdDeposito(this.getIdDeposito());
		d.setDescripcion(this.getDescripcion());
		
		return d;
	}
	
	
	
}
