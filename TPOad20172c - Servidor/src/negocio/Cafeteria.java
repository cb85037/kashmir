package Negocio;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CAFETERIA")
public class Cafeteria extends AreaPreparacion implements Serializable{

	
	@Override
	public String getNombreSector() {
		// TODO Auto-generated method stub
		return "Cafeteria";
	}
}
