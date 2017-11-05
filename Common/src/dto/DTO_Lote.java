package dto;

import java.io.Serializable;
import java.sql.Date;

public class DTO_Lote implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int idLote;
	private String numero;
	private int cantidad;
	private Date vencimiento;
	private boolean planProduccion;
	private DTO_Producto producto;
	
	public DTO_Lote() {
	}

	public int getIdLote() {
		return idLote;
	}

	public void setIdLote(int idLote) {
		this.idLote = idLote;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Date getVencimiento() {
		return vencimiento;
	}

	public void setVencimiento(Date vencimiento) {
		this.vencimiento = vencimiento;
	}

	public boolean isPlanProduccion() {
		return planProduccion;
	}

	public void setPlanProduccion(boolean planProduccion) {
		this.planProduccion = planProduccion;
	}

	public DTO_Producto getProducto() {
		return producto;
	}

	public void setProducto(DTO_Producto producto) {
		this.producto = producto;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	
	
	
}
