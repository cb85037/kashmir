package entity;

import java.io.Serializable;

import javax.persistence.*;

import dto.DTO_Empleado;

@Entity
@Table(name = "Empleado")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
public class Entity_Empleado implements Serializable{

	
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "legajo")
	protected String legajo;
	
	@Column(name = "nombre")
	protected String nombre;
	
	@Column(name = "usuario")
	protected String usuario;
	
	@Column(name = "password")
	protected String password;
	
	

	public Entity_Empleado() {
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

	public DTO_Empleado getDTO() {
		DTO_Empleado e = new DTO_Empleado();
		e.setLegajo(this.legajo);
		e.setNombre(this.nombre);
		e.setUsuario(this.usuario);
		e.setPassword(this.password);
		
		return e;
	}
	
	
	
}
