package dto;

import java.io.Serializable;

public class DTO_ItemReceta implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	private DTO_Producto producto;
	private int cantidad;
	
	public DTO_ItemReceta() {
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
	
	
	
	
}
