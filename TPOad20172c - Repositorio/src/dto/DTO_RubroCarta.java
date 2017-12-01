package dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
	
public class DTO_RubroCarta implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int idRubroCarta;
	private String nombre;
	private List<DTO_Plato> platos;
	
	public DTO_RubroCarta() {
		this.platos = new ArrayList<DTO_Plato>();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<DTO_Plato> getPlatos() {
		return platos;
	}

	public void setPlatos(List<DTO_Plato> platos) {
		this.platos = platos;
	}

	public int getIdRubroCarta() {
		return idRubroCarta;
	}

	public void setIdRubroCarta(int idRubroCarta) {
		this.idRubroCarta = idRubroCarta;
	}
	
	
	
	
}
