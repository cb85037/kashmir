package entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Lote")
@SequenceGenerator(name="sec", sequenceName="gen_lotes")
public class Entity_Lote implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	@GeneratedValue(generator = "sec")
	private int idLote;
	
	@Column
	private String numero;
	
	@Column(name = "cantidad")
	private int cantidad;
	
	@Column(name = "vencimiento")
	private Date vencimiento;
	
	@Column(name = "planProduccion")
	private boolean planProduccion;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "producto")
	private Entity_Producto producto;
	

	public Entity_Lote() {
		// TODO Auto-generated constructor stub
	}



	public int getIdLote() {
		return idLote;
	}

	public void setIdLote(int idLote) {
		this.idLote = idLote;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Date getVencimiento() {
		return vencimiento;
	}

	public void setVencimiento(Date vencimiento) {
		this.vencimiento = vencimiento;
	}

	public boolean isPlanProduccion() {
		return planProduccion;
	}

	public void setPlanProduccion(boolean planProduccion) {
		this.planProduccion = planProduccion;
	}

	public Entity_Producto getProducto() {
		return producto;
	}

	public void setProducto(Entity_Producto producto) {
		this.producto = producto;
	}



	public String getNumero() {
		return numero;
	}



	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	
	
	
	
}
