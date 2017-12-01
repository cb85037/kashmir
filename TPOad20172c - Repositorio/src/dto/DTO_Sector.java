package dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DTO_Sector implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int idSector;
	private String nombre;
	private List<DTO_Mesa> mesas;
	private List<DTO_Mozo> mozos;
	
	public DTO_Sector() {
		this.mesas = new ArrayList<DTO_Mesa>();
		this.mozos = new ArrayList<DTO_Mozo>();
	}

	public int getIdSector() {
		return idSector;
	}

	public void setIdSector(int idSector) {
		this.idSector = idSector;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<DTO_Mesa> getMesas() {
		return mesas;
	}

	public void setMesas(List<DTO_Mesa> mesas) {
		this.mesas = mesas;
	}

	public List<DTO_Mozo> getMozos() {
		return mozos;
	}

	public void setMozos(List<DTO_Mozo> mozos) {
		this.mozos = mozos;
	}
	
	
	
	
}
