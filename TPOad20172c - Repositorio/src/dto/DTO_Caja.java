package dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DTO_Caja implements Serializable{

	private static final long serialVersionUID = 1L;
	private int idCaja;
	private String descripcion;
	private List<DTO_CajaDiaria> cajasDiarias;
	
	public DTO_Caja() {
		this.cajasDiarias = new ArrayList<DTO_CajaDiaria>();
	}

	public int getIdCaja() {
		return idCaja;
	}

	public void setIdCaja(int idCaja) {
		this.idCaja = idCaja;
	}

	public List<DTO_CajaDiaria> getCajasDiarias() {
		return cajasDiarias;
	}

	public void setCajasDiarias(List<DTO_CajaDiaria> cajasDiarias) {
		this.cajasDiarias = cajasDiarias;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	
	

	
}
