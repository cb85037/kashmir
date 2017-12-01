package Negocio;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("COCINA")
public class Cocina extends AreaPreparacion {

	private int horasDisponibles;

	public int getHorasDisponibles() {
		return horasDisponibles;
	}

	public void setHorasDisponibles(int horasDisponibles) {
		this.horasDisponibles = horasDisponibles;
	}
	
	@Override
	public String getNombreSector() {
		// TODO Auto-generated method stub
		return "Cocina";
	}
}
