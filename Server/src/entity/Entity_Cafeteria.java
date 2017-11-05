package entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Cafeteria")
public class Entity_Cafeteria extends Entity_Area implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public Entity_Cafeteria(){
		
	}

}
