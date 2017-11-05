package entity;

import java.io.Serializable;

import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

@Entity
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Entity_Area implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected int id;
	
	@Column(name = "nombre")
	protected String nombre;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "IdDeposito")
	protected Entity_DepositoArea deposito;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "IdEmpleado", referencedColumnName = "legajo")
	protected Entity_EmpProduccion empleado;
	
	
	public Entity_Area(){
		
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


	public Entity_EmpProduccion getEmpleado() {
		return empleado;
	}


	public void setEmpleado(Entity_EmpProduccion empleado) {
		this.empleado = empleado;
	}


	public Entity_DepositoArea getDeposito() {
		return deposito;
	}


	public void setDeposito(Entity_DepositoArea deposito) {
		this.deposito = deposito;
	}
	
	

	
}
