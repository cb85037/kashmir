package entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("cv")
public class Entity_CompraVenta extends Entity_Producto {

	private static final long serialVersionUID = 1L;

	public Entity_CompraVenta() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}
