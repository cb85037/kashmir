package Negocio;

import javax.persistence.DiscriminatorValue;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@DiscriminatorValue("JefeArea")
public class JefeAreaPreparacion extends Empleado {



	public JefeAreaPreparacion() {
		super();
	}

	@Override
	public boolean soyCajero() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean soyMozo() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean soyJefeDeArea() {
		// TODO Auto-generated method stub
		return true;
	}

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

}
