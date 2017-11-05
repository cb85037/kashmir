package dto;

import java.io.Serializable;

public class DTO_Proveedor implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int idProveedor;
	private String nombre;
	private String razonSocial;
	private String cuit;
	
	public DTO_Proveedor() {
	}

	public int getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}
	
	
	
}
