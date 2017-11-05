package entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("administracion")
public class Entity_EmpAdministracion extends Entity_Empleado {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Entity_EmpAdministracion() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
