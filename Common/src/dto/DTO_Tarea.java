package dto;

import java.io.Serializable;

public class DTO_Tarea implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int numero;
	private DTO_Producto producto;
	private int cantidad;
	private DTO_Local sucursal;
	private float tiempoRequerido;
	private String estado;
	
	public DTO_Tarea() {
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public DTO_Producto getProducto() {
		return producto;
	}

	public void setProducto(DTO_Producto producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public DTO_Local getSucursal() {
		return sucursal;
	}

	public void setSucursal(DTO_Local sucursal) {
		this.sucursal = sucursal;
	}


	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public float getTiempoRequerido() {
		return tiempoRequerido;
	}

	public void setTiempoRequerido(float tiempoRequerido) {
		this.tiempoRequerido = tiempoRequerido;
	}
	
	
	
	
}
