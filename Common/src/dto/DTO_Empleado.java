package dto;

import java.io.Serializable;

public class DTO_Empleado implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	protected String nombre;
	protected String legajo;
	protected String usuario;
	protected String password;
	
	public DTO_Empleado() {
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getLegajo() {
		return legajo;
	}

	public void setLegajo(String legajo) {
		this.legajo = legajo;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
