package dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.*;

public class DTO_Cocina extends DTO_Area implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private List<DTO_Tarea> tareas;
	
	public DTO_Cocina() {
		this.tareas = new ArrayList<DTO_Tarea>();
	}

	public List<DTO_Tarea> getTareas() {
		return tareas;
	}

	public void setTareas(List<DTO_Tarea> tareas) {
		this.tareas = tareas;
	}
	
	
	
	
	
}
