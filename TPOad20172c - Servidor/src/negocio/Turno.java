package Negocio;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import repositorio.MesaDTO;
import repositorio.MozoDTO;
import repositorio.SectorDTO;
import repositorio.TurnoDTO;

@Entity()
@Table(name="turnos")
public class Turno implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_turno")
	private int id;
	private int horaInicio;
	private int horaFin;
	private String nombre;
	
	public Turno(int id, int horaInicio, int horaFin, String nombre) {
		super();
		this.id = id;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.nombre = nombre;
	}

	public Turno() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(int horaInicio) {
		this.horaInicio = horaInicio;
	}

	public int getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(int horaFin) {
		this.horaFin = horaFin;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/***********************************
	 ******METODOS DTO******************
	 ***********************************/
	
	public TurnoDTO generarDTOdeTurno(){
		TurnoDTO dto = new TurnoDTO();
		if(!(this.id != 0)){
			dto.setId(id);
			dto.setHoraFin(horaFin);
			dto.setHoraInicio(horaInicio);
			dto.setNombre(nombre);
		}
		return dto;
	}
	
	public static Turno crearInstanciaDeMesa(TurnoDTO dto){
		Turno turno = new Turno();
		turno.setHoraFin(dto.getHoraFin());
		turno.setHoraInicio(dto.getHoraInicio());
		turno.setNombre(dto.getNombre());
		return turno;
	}
}
