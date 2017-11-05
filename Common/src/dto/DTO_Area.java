package dto;

import java.io.Serializable;

public class DTO_Area implements Serializable{
	
	private static final long serialVersionUID = 1L;
	protected int id;
	protected String nombre;
	protected DTO_Deposito deposito;
	protected DTO_EmpProduccion empleado;
	
	
	public DTO_Area() {
	}

	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public DTO_Deposito getDeposito() {
		return deposito;
	}


	public void setDeposito(DTO_Deposito deposito) {
		this.deposito = deposito;
	}


	public DTO_EmpProduccion getEmpleado() {
		return empleado;
	}


	public void setEmpleado(DTO_EmpProduccion empleado) {
		this.empleado = empleado;
	}

	
}
