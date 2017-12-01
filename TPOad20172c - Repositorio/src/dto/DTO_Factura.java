package dto;

import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class DTO_Factura implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int numero;
	private Date fecha;
	private float total;
	private DTO_Mesa mesa;
	private DTO_Encargado cajero;
	private List<DTO_ItemFactura> items;
	
	public DTO_Factura() {
		this.items = new ArrayList<DTO_ItemFactura>();
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public DTO_Mesa getMesa() {
		return mesa;
	}

	public void setMesa(DTO_Mesa mesa) {
		this.mesa = mesa;
	}

	public DTO_Encargado getEmpleado() {
		return cajero;
	}

	public void setEmpleado(DTO_Encargado empleado) {
		this.cajero = empleado;
	}

	public List<DTO_ItemFactura> getItems() {
		return items;
	}

	public void setItems(List<DTO_ItemFactura> items) {
		this.items = items;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}
	
	
	
	
	
}
