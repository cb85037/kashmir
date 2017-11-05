package entity;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("cajero")
public class Entity_Encargado extends Entity_Empleado implements Serializable {

	private static final long serialVersionUID = 1L;

	public Entity_Encargado() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	
	
}
