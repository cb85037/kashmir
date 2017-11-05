package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Cocinas")
public class Entity_Cocina extends Entity_Area implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Transient
	private List<Entity_Tarea> tareas;
	
	public Entity_Cocina() {
		this.tareas = new ArrayList<Entity_Tarea>();
	}

	public List<Entity_Tarea> getTareas() {
		return tareas;
	}

	public void setTareas(List<Entity_Tarea> tareas) {
		this.tareas = tareas;
	}
	
	
	
	
	
}
