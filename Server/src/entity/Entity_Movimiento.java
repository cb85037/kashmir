package entity;

import java.io.Serializable;
import java.util.Date;


import java.util.List;

import javax.persistence.*;

import dto.DTO_Deposito;
import dto.DTO_Movimiento;

@Entity
@Table(name = "Movimiento")
@SequenceGenerator(name="sec", sequenceName="gen_movimientos")
public class Entity_Movimiento implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@GeneratedValue(generator = "sec")
	protected int idMovimiento;

	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "idMovimiento")
	protected List<Entity_Lote> lotes;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idOrigen")
	protected Entity_Deposito origen;

	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idDestino")
	protected Entity_Deposito destino;

	
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Date fecha;
	
	@Column
	protected String motivo;

	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idEmpleado")
	protected Entity_Empleado usuario;

	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idArea")
	protected Entity_Deposito area;
	
	@Column
	protected boolean planProduccion;
	
	@Column
	protected String estado;
	
	

	public Entity_Movimiento() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getIdMovimiento() {
		return idMovimiento;
	}

	public void setIdMovimiento(int idMovimiento) {
		this.idMovimiento = idMovimiento;
	}

	
	public List<Entity_Lote> getLotes() {
		return lotes;
	}

	public void setLotes(List<Entity_Lote> lotes) {
		this.lotes = lotes;
	}

	public Entity_Deposito getOrigen() {
		return origen;
	}

	public void setOrigen(Entity_Deposito origen) {
		this.origen = origen;
	}

	public Entity_Deposito getDestino() {
		return destino;
	}

	public void setDestino(Entity_Deposito destino) {
		this.destino = destino;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public Entity_Empleado getUsuario() {
		return usuario;
	}

	public void setUsuario(Entity_Empleado usuario) {
		this.usuario = usuario;
	}

	
	public Entity_Deposito getArea() {
		return area;
	}

	public void setArea(Entity_Deposito area) {
		this.area = area;
	}

	public boolean isPlanProduccion() {
		return planProduccion;
	}

	public void setPlanProduccion(boolean planProduccion) {
		this.planProduccion = planProduccion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public DTO_Movimiento toDTO() {
		
		DTO_Movimiento m = new DTO_Movimiento();
		
		DTO_Deposito area1 = new DTO_Deposito();
		area1.setIdDeposito(area.idDeposito);
		area1.setDescripcion(area.descripcion);
		
		
		DTO_Deposito destino1 = new DTO_Deposito();
		destino1.setIdDeposito(destino.idDeposito);
		destino1.setDescripcion(destino.descripcion);
		
		
		DTO_Deposito origen1 = new DTO_Deposito();
		origen1.setIdDeposito(origen.idDeposito);
		origen1.setDescripcion(origen.descripcion);
		
		
		m.setArea(area1);
		m.setDestino(destino1);
		m.setEstado(this.estado);
		//m.setFecha((java.sql.Date) this.fecha);
		m.setIdMovimiento(this.idMovimiento);
		m.setMotivo(this.motivo);
		m.setOrigen(origen1);
		m.setPlanProduccion(this.planProduccion);
		m.setUsuario(this.usuario.getDTO());
		
		return m;
		
	}
	
	
	
	
}
