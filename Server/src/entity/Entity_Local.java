package entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import dto.DTO_Local;

@Entity
@Table(name = "Local")
public class Entity_Local implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "nombre")
	private String nombre;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "salon", referencedColumnName ="nombre")
	private Entity_Salon salon;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idCaja")
	private Entity_Caja caja;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idCocina", referencedColumnName = "id")
	private Entity_Cocina cocina;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idBarra", referencedColumnName = "id")
	private Entity_Barra barra;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idCafeteria", referencedColumnName = "id")
	private Entity_Cafeteria cafeteria;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idDeposito")
	private Entity_DepositoLocal deposito;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idCajero")
	private Entity_Encargado empleado;
	
	
	@Column
	private float hsProduccion;
	@Column
	private float hsDisponiblesProduccion;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idCarta")
	private Entity_Carta carta;
	
	public Entity_Local(){
		
	}
	
	public Entity_Local(String nombre) {
		this.nombre = nombre;
	}

	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Entity_Salon getSalon() {
		return salon;
	}

	public void setSalon(Entity_Salon salon) {
		this.salon = salon;
	}

	public Entity_Caja getCaja() {
		return caja;
	}

	public void setCaja(Entity_Caja caja) {
		this.caja = caja;
	}

	public Entity_Cocina getCocina() {
		return cocina;
	}

	public void setCocina(Entity_Cocina cocina) {
		this.cocina = cocina;
	}

	public Entity_Barra getBarra() {
		return barra;
	}

	public void setBarra(Entity_Barra barra) {
		this.barra = barra;
	}

	public Entity_Cafeteria getCafeteria() {
		return cafeteria;
	}

	public void setCafeteria(Entity_Cafeteria cafeteria) {
		this.cafeteria = cafeteria;
	}


	public Entity_DepositoLocal getDeposito() {
		return deposito;
	}


	public void setDeposito(Entity_DepositoLocal deposito) {
		this.deposito = deposito;
	}

	public float getHsProduccion() {
		return hsProduccion;
	}

	public void setHsProduccion(float hsProduccion) {
		this.hsProduccion = hsProduccion;
	}

	public float getHsDisponiblesProduccion() {
		return hsDisponiblesProduccion;
	}

	public void setHsDisponiblesProduccion(float hsDisponiblesProduccion) {
		this.hsDisponiblesProduccion = hsDisponiblesProduccion;
	}
	

	public Entity_Encargado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Entity_Encargado empleado) {
		this.empleado = empleado;
	}

	public Entity_Carta getCarta() {
		return carta;
	}

	public void setCarta(Entity_Carta carta) {
		this.carta = carta;
	}

	public DTO_Local getDTO(){
		DTO_Local suc = new DTO_Local();
		suc.setNombre(this.nombre);
		suc.setHsProduccion(this.hsProduccion);
		suc.setHsDisponiblesProduccion(this.hsDisponiblesProduccion);
		return suc;
	}

	


	
	
	
	
	
}
