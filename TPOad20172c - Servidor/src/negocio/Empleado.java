package Negocio;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import repositorio.EmpleadoDTO;

@Entity
@DiscriminatorValue("E")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipoEmpleado",discriminatorType=DiscriminatorType.STRING)
public abstract class Empleado extends Rol implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3405238340105762856L;
	@ManyToOne
	@JoinColumn(name="id_restaurante")
	private Restaurante restaurante;
	private String nombreEmpleado;
	@ManyToOne
	@JoinColumn(name="id_turno")
	private Turno turno;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_usuario")
	private Usuario usuario;

	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Restaurante getRestaurante() {
		return restaurante;
	}
	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}
	public String getNombreEmpleado() {
		return nombreEmpleado;
	}
	public void setNombreEmpleado(String nombre) {
		this.nombreEmpleado = nombre;
	}
	public Turno getTurno() {
		return turno;
	}
	public void setTurno(Turno turno) {
		this.turno = turno;
	}
	
	public abstract boolean soyCajero();
	public abstract boolean soyMozo();
	public abstract boolean soyJefeDeArea();
	
	/***********************************
	 ******METODOS DTO******************
	 ***********************************/
	
	public EmpleadoDTO generarDTOEmpleado() {
		EmpleadoDTO empleado = new EmpleadoDTO();
		empleado.setNombreEmpleado(nombreEmpleado);
		empleado.setRestaurante(restaurante.generarDTOdeRestaurante());
		empleado.setTurno(turno.generarDTOdeTurno());
		empleado.setSoyCajero(this.soyCajero());
		empleado.setSoyJefe(this.soyJefeDeArea());
		empleado.setSoyMozo(this.soyMozo());
		return empleado;
	}
}
