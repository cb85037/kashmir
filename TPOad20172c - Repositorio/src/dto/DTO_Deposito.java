package dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class DTO_Deposito implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	protected int idDeposito;
	protected String descripcion;
	protected List<DTO_StockProducto> productos;
	protected List<DTO_Movimiento> movimientos;
	
	public DTO_Deposito() {
		this.productos = new ArrayList<DTO_StockProducto>();
		this.movimientos = new ArrayList<DTO_Movimiento>();
	}

	public int getIdDeposito() {
		return idDeposito;
	}

	public void setIdDeposito(int idDeposito) {
		this.idDeposito = idDeposito;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<DTO_StockProducto> getProductos() {
		return productos;
	}

	public void setProductos(List<DTO_StockProducto> productos) {
		this.productos = productos;
	}

	public List<DTO_Movimiento> getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(List<DTO_Movimiento> movimientos) {
		this.movimientos = movimientos;
	}
	
	
	
	
	
}
