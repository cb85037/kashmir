
package Negocio;
import java.util.Date;
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
import java.util.List;

@Entity
@Table(name="movimientoStock")
public class MovimientoStock implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id_movimientoStock")
	private int id;
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;
	private Date fechaYHora;
	private String motivo;
	@ManyToOne
	@JoinColumn(name="id_area")
	private AreaPreparacion sector;
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Date getFechaYHora() {
		return fechaYHora;
	}
	public void setFechaYHora(Date fechaYHora) {
		this.fechaYHora = fechaYHora;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public AreaPreparacion getSector() {
		return sector;
	}
	public void setSector(AreaPreparacion sector) {
		this.sector = sector;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public MovimientoStock(Usuario usuario, Date fechaYHora, String motivo,
			AreaPreparacion sector, int id) {
		super();
		this.usuario = usuario;
		this.fechaYHora = fechaYHora;
		this.motivo = motivo;
		this.sector = sector;
		this.id = id;
	}
	public MovimientoStock() {
		// TODO Auto-generated constructor stub
	}
	
	
}
