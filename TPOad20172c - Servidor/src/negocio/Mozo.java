package Negocio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import repositorio.MesaDTO;
import repositorio.MozoDTO;
import repositorio.SectorDTO;

@Entity
@DiscriminatorValue("Mozo")
public class Mozo extends Empleado {

	private float comision;
	@ManyToOne
	@JoinColumn(name="id_sector")
	private Sector sector;
	
	@Override
	public boolean soyCajero() {
		// TODO Auto-generated method stub
		return false;
	}

	public float getComision() {
		return comision;
	}

	public void setComision(float comision) {
		this.comision = comision;
	}

	public Sector getSector() {
		return sector;
	}

	public void setSector(Sector sector) {
		this.sector = sector;
	}


	public Mozo(float comision, Sector sector, int id) {
		super();
		this.comision = comision;
		this.sector = sector;
	}

	public Mozo() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean soyMozo() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean soyJefeDeArea() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean soyEmpleado() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean soyAdministrador() {
		// TODO Auto-generated method stub
		return false;
	}

	/***********************************
	 ******METODOS DTO******************
	 ***********************************/
	
	public MozoDTO generarDTOdeMozo(){
		MozoDTO dto = new MozoDTO();
		if(!(this.getId() != 0)){
			dto.setComision(comision);
			dto.setEmpleado(((Empleado) this).generarDTOEmpleado());
			dto.setSector(sector.generarDTOdeSector());
		}
		return dto;
	}
	
	public static Mozo crearInstanciaDeMozo(MozoDTO dto){
		Mozo mozo = new Mozo();
		mozo.setComision(dto.getComision());
		mozo.setNombreEmpleado(dto.getEmpleado().getNombreEmpleado());
		mozo.setId(dto.getEmpleado().getRol().getId());
		mozo.setSector(Sector.crearInstanciaDeSector(dto.getSector()));
		mozo.setRestaurante(Restaurante.crearInstanciaDeRestaurante(dto.getEmpleado().getRestaurante()));
		
		return mozo;
	}
}
