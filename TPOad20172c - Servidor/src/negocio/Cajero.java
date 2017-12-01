package Negocio;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Cajero")
public class Cajero extends Empleado {

	@Override
	public boolean soyEmpleado() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean soyAdministrador() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean soyCajero() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean soyMozo() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean soyJefeDeArea() {
		// TODO Auto-generated method stub
		return false;
	}

}
