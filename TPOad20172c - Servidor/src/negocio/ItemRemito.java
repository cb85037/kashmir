package Negocio;
import java.io.Serializable;
import java.sql.Date;
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
import java.util.List;

@Entity
@Table(name="itemRemito")
public class ItemRemito implements Serializable {

	@ManyToOne
	@JoinColumn(name="id_producto")
	private Producto prod;
	private Date fechaVencimiento;
	private String calidad;
	private int cantidad;
	private boolean porFaltante;
	@ManyToOne
	@JoinColumn(name="id_productoAsustituir")
	private Producto prodASustituir;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_itemRemito")
	private int id;
	
	public ItemRemito() {
		super();
	}
	public Producto getProd() {
		return prod;
	}
	public void setProd(Producto prod) {
		this.prod = prod;
	}
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	public String getCalidad() {
		return calidad;
	}
	public void setCalidad(String calidad) {
		this.calidad = calidad;
	}
	public boolean isPorFaltante() {
		return porFaltante;
	}
	public void setPorFaltante(boolean porFaltante) {
		this.porFaltante = porFaltante;
	}
	public Producto getProdASustituir() {
		return prodASustituir;
	}
	public void setProdASustituir(Producto prodASustituir) {
		this.prodASustituir = prodASustituir;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
}
