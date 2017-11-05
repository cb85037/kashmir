package entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Entity_Remito implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int idRemito;
	private Date fecha;
	private Entity_Proveedor proveedor;
	private List<Entity_ItemRemito> items;
	
	public Entity_Remito(int idRemito, Date fecha, Entity_Proveedor proveedor) {
		super();
		this.idRemito = idRemito;
		this.fecha = fecha;
		this.proveedor = proveedor;
		this.items = new ArrayList<Entity_ItemRemito>();
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

	public Entity_Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Entity_Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public List<Entity_ItemRemito> getItems() {
		return items;
	}

	public void setItems(List<Entity_ItemRemito> items) {
		this.items = items;
	}
	
	
	
	
}
