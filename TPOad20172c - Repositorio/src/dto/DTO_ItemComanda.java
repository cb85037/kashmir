package dto;

import java.io.Serializable;
import java.util.List;



public class DTO_ItemComanda implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;

	private DTO_Plato plato;
	private int cantidad;
	private String estado;
	
	public DTO_ItemComanda() {
	}

	public DTO_Plato getPlato() {
		return plato;
	}

	public void setPlato(DTO_Plato plato) {
		this.plato = plato;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
	
}
