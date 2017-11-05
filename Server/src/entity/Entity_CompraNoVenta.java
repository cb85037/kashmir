package entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue("cnv")
public class Entity_CompraNoVenta extends Entity_Producto {

	
	private static final long serialVersionUID = 1L;

	public Entity_CompraNoVenta() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
}
