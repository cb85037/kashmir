package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;



@Entity
@Table(name = "Factura")
public class Entity_Factura implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)

	private int numero;
	
	@Column
	private Date fecha;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idMesa")
	private Entity_Mesa mesa;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idCajero")
	private Entity_Encargado cajero;

	
	@OneToMany(cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name = "idFactura")
	private List<Entity_ItemFactura> items;
	
	@Column
	float total;
	


	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public Entity_Factura() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Entity_Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Entity_Mesa mesa) {
		this.mesa = mesa;
	}

	public Entity_Encargado getEmpleado() {
		return cajero;
	}

	public void setEmpleado(Entity_Encargado empleado) {
		this.cajero = empleado;
	}

	public List<Entity_ItemFactura> getItems() {
		return items;
	}

	public void setItems(List<Entity_ItemFactura> items) {
		this.items = items;
	}
	
	
	
	
	
}
