package dto;

import java.io.Serializable;


public class DTO_Mesa implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	
	protected int idMesa;
	
	protected int numero;
	
	protected int capacidad;
	
	protected String estado;
	
	protected DTO_Comanda comanda;
	
	protected boolean facturada;

	public int getIdMesa() {
		return idMesa;
	}

	public void setIdMesa(int idMesa) {
		this.idMesa = idMesa;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public DTO_Comanda getComanda() {
		return comanda;
	}

	public void setComanda(DTO_Comanda comanda) {
		this.comanda = comanda;
	}

	public boolean isFacturada() {
		return facturada;
	}

	public void setFacturada(boolean facturada) {
		this.facturada = facturada;
	}

	public DTO_Mesa() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
