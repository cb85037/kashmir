package dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DTO_Salon implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String nombre;
	private List<DTO_Sector> sectores;
	
	public DTO_Salon() {
		this.sectores = new ArrayList<DTO_Sector>();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<DTO_Sector> getSectores() {
		return sectores;
	}

	public void setSectores(List<DTO_Sector> sectores) {
		this.sectores = sectores;
	}
	
	public void add(DTO_Sector s){
		sectores.add(s);
	}
	
	
	
}
