package entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Entity_OrdenCompra implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	private int idOC;
	private Date fecha;
	private Entity_Proveedor proveedor;
	private List<Entity_ItemOrdenCompra> items;
	
	
	public Entity_OrdenCompra(int idOC, Date fecha, Entity_Proveedor proveedor) {
		super();
		this.idOC = idOC;
		this.fecha = fecha;
		this.proveedor = proveedor;
		this.items = new ArrayList<Entity_ItemOrdenCompra>();
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
	public Entity_Proveedor getProveedor() {
		return proveedor;
	}
	public void setProveedor(Entity_Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	public List<Entity_ItemOrdenCompra> getItems() {
		return items;
	}
	public void setItems(List<Entity_ItemOrdenCompra> items) {
		this.items = items;
	}
	
	
}
