package dto;

import java.io.Serializable;
import java.util.List;

public class DTO_Local implements Serializable{

	private static final long serialVersionUID = 1L;
	private String nombre;
	private DTO_Salon salon;
	private DTO_CajaDiaria caja;
	private DTO_Cocina cocina;
	private DTO_Barra barra;
	private DTO_Cafeteria cafeteria;
	private DTO_Deposito deposito;
	private DTO_Encargado empleado;
	private float hsProduccion;
	private float hsDisponiblesProduccion;
	private DTO_Carta carta;
	
	public DTO_Carta getCarta() {
		return carta;
	}

	public void setCarta(DTO_Carta carta) {
		this.carta = carta;
	}

	public DTO_Local() {
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public DTO_Salon getSalon() {
		return salon;
	}

	public void setSalon(DTO_Salon salon) {
		this.salon = salon;
	}

	public DTO_CajaDiaria getCaja() {
		return caja;
	}

	public void setCaja(DTO_CajaDiaria caja) {
		this.caja = caja;
	}

	public DTO_Cocina getCocina() {
		return cocina;
	}

	public void setCocina(DTO_Cocina cocina) {
		this.cocina = cocina;
	}

	public DTO_Barra getBarra() {
		return barra;
	}

	public void setBarra(DTO_Barra barra) {
		this.barra = barra;
	}

	public DTO_Cafeteria getCafeteria() {
		return cafeteria;
	}

	public void setCafeteria(DTO_Cafeteria cafeteria) {
		this.cafeteria = cafeteria;
	}

	public DTO_Deposito getDeposito() {
		return deposito;
	}

	public void setDeposito(DTO_Deposito deposito) {
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

	public DTO_Encargado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(DTO_Encargado empleado) {
		this.empleado = empleado;
	}
	
	
	
	
	
}
