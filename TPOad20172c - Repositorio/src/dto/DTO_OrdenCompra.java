package dto;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class DTO_OrdenCompra implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int idOC;
	private Date fecha;
	private DTO_Proveedor proveedor;
	private List<DTO_ItemOrdenCompra> items;
	
	
	public DTO_OrdenCompra() {
		this.items = new ArrayList<DTO_ItemOrdenCompra>();
	}
	
	public int getIdOC() {
		return idOC;
	}
	public void setIdOC(int idOC) {
		this.idOC = idOC;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public DTO_Proveedor getProveedor() {
		return proveedor;
	}
	public void setProveedor(DTO_Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	public List<DTO_ItemOrdenCompra> getItems() {
		return items;
	}
	public void setItems(List<DTO_ItemOrdenCompra> items) {
		this.items = items;
	}
	
	
}
