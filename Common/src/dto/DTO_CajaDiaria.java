package dto;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class DTO_CajaDiaria implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int idCaja;
	private Date fechaApertura;
	private DTO_Encargado cajero;
	private float montoInicial;
	private float montoCierre;
	private Date fechaCierre;
	private List<DTO_Factura> facturas;
	
	
	public DTO_CajaDiaria() {
		this.facturas = new ArrayList<DTO_Factura>();
	}



	public int getIdCaja() {
		return idCaja;
	}



	public void setIdCaja(int idCaja) {
		this.idCaja = idCaja;
	}



	public Date getFechaApertura() {
		return fechaApertura;
	}



	public void setFechaApertura(Date fechaApertura) {
		this.fechaApertura = fechaApertura;
	}



	



	public DTO_Encargado getCajero() {
		return cajero;
	}



	public void setCajero(DTO_Encargado cajero) {
		this.cajero = cajero;
	}



	public float getMontoInicial() {
		return montoInicial;
	}



	public void setMontoInicial(float montoInicial) {
		this.montoInicial = montoInicial;
	}



	public float getMontoCierre() {
		return montoCierre;
	}



	public void setMontoCierre(float montoCierre) {
		this.montoCierre = montoCierre;
	}



	public Date getFechaCierre() {
		return fechaCierre;
	}



	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}



	public List<DTO_Factura> getFacturas() {
		return facturas;
	}



	public void setFacturas(List<DTO_Factura> facturas) {
		this.facturas = facturas;
	}

	

	}
