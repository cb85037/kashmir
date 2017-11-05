package entity;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("ev")
public class Entity_ElaboracionVenta extends Entity_Producto {
	
	private static final long serialVersionUID = 1L;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idReceta")
	private Entity_Receta receta;
	
	
	public Entity_ElaboracionVenta() {
		
		// TODO Auto-generated constructor stub
	}



	public Entity_Receta getReceta() {
		return receta;
	}

	public void setReceta(Entity_Receta receta) {
		this.receta = receta;
	}
	
	
	
}
