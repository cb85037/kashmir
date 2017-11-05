package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;



@Entity
@Table(name = "AdministracionCentral")
public class Entity_AdministracionCentral implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idAdministracionCentral;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "iEmpleado")
	private Entity_EmpAdministracion empleado;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "iDeposito")
	private Entity_DepositoCentral deposito;
	
	
	public Entity_AdministracionCentral() {
		
	}

	
	public Entity_DepositoCentral getDeposito() {
		return deposito;
	}


	public void setDeposito(Entity_DepositoCentral deposito) {
		this.deposito = deposito;
	}


	public int getIdAdministracionCentral() {
		return idAdministracionCentral;
	}

	public void setIdAdministracionCentral(int idAdministracionCentral) {
		this.idAdministracionCentral = idAdministracionCentral;
	}


	public Entity_EmpAdministracion getEmpleado() {
		return empleado;
	}


	public void setEmpleado(Entity_EmpAdministracion empleado) {
		this.empleado = empleado;
	}


	

	
	
	
	
}
