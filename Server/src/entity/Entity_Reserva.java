package entity;

import java.io.Serializable;
import java.sql.Date;

public class Entity_Reserva implements Serializable{

	private static final long serialVersionUID = 1L;
	private int numero;
	private Date fecha;
	private int cantidadPersonas;
	private String estado;
	
	
	public Entity_Reserva(int numero, Date fecha, int cantidadPersonas, String estado) {
		super();
		this.numero = numero;
		this.fecha = fecha;
		this.cantidadPersonas = cantidadPersonas;
		this.estado = estado;
	}
	
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public int getCantidadPersonas() {
		return cantidadPersonas;
	}
	public void setCantidadPersonas(int cantidadPersonas) {
		this.cantidadPersonas = cantidadPersonas;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
}
