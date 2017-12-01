package dto;

import java.io.Serializable;

public class DTO_ItemFactura implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int id;
	private DTO_Plato producto;
	private int cantidad;
	private float descuento;
	private float precio;
	
	public DTO_ItemFactura() {
	
	}

	
	public float getDescuento() {
		return descuento;
	}


	public void setDescuento(float descuento) {
		this.descuento = descuento;
	}


	public DTO_Plato getProducto() {
		return producto;
	}

	public void setProducto(DTO_Plato producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
}
