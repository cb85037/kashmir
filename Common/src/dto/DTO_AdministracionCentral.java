package dto;
//comment
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class DTO_AdministracionCentral implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	private int idAdministracionCentral;
	
	
	private DTO_EmpAdministracion empleado;
	
	
	private DTO_DepositoCentral deposito;
	
	
	public DTO_AdministracionCentral() {
	
	}

	public int getIdAdministracionCentral() {
		return idAdministracionCentral;
	}

	public void setIdAdministracionCentral(int idAdministracionCentral) {
		this.idAdministracionCentral = idAdministracionCentral;
	}

	public DTO_EmpAdministracion getEmpleado() {
		return empleado;
	}

	public void setEmpleado(DTO_EmpAdministracion empleado) {
		this.empleado = empleado;
	}

	public DTO_DepositoCentral getDeposito() {
		return deposito;
	}

	public void setDeposito(DTO_DepositoCentral deposito) {
		this.deposito = deposito;
	}

	
	
}
