package entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@DiscriminatorValue("produccion")
public class Entity_EmpProduccion extends Entity_Empleado{

	private static final long serialVersionUID = 1L;

	public Entity_EmpProduccion() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
