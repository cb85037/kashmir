package dto;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class DTO_Remito implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int idRemito;
	private Date fecha;
	private DTO_Proveedor proveedor;
	private List<DTO_ItemRemito> items;
	
	public DTO_Remito() {
		this.items = new ArrayList<DTO_ItemRemito>();
	}

	public int getIdRemito() {
		return idRemito;
	}

	public void setIdRemito(int idRemito) {
		this.idRemito = idRemito;
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

	public List<DTO_ItemRemito> getItems() {
		return items;
	}

	public void setItems(List<DTO_ItemRemito> items) {
		this.items = items;
	}
	
	
	
	
}
