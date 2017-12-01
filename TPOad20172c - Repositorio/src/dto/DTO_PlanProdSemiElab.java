package dto;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class DTO_PlanProdSemiElab implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int idPlanProduccion;
	private Date fecha;
	private List<DTO_Tarea> tareas;
	private String estado;
	
	public DTO_PlanProdSemiElab() {
		this.tareas = new ArrayList<DTO_Tarea>();
	}

	public int getIdPlanProduccion() {
		return idPlanProduccion;
	}

	public void setIdPlanProduccion(int idPlanProduccion) {
		this.idPlanProduccion = idPlanProduccion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public List<DTO_Tarea> getTareas() {
		return tareas;
	}

	public void setTareas(List<DTO_Tarea> tareas) {
		this.tareas = tareas;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	
	
}
