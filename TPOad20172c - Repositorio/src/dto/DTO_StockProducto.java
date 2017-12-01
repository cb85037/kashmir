package dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DTO_StockProducto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int idStockProducto;
	private DTO_Producto producto;
	private int stockMinimo;
	private List<DTO_Lote> lotes;
	
	public DTO_StockProducto() {
		this.lotes = new ArrayList<DTO_Lote>();
	}

	public int getIdStockProducto() {
		return idStockProducto;
	}

	public void setIdStockProducto(int idStockProducto) {
		this.idStockProducto = idStockProducto;
	}

	public DTO_Producto getProducto() {
		return producto;
	}

	public void setProducto(DTO_Producto producto) {
		this.producto = producto;
	}

	public int getStockMinimo() {
		return stockMinimo;
	}

	public void setStockMinimo(int stockMinimo) {
		this.stockMinimo = stockMinimo;
	}

	public List<DTO_Lote> getLotes() {
		return lotes;
	}

	public void setLotes(List<DTO_Lote> lotes) {
		this.lotes = lotes;
	}
	
	
	
	
	
}
