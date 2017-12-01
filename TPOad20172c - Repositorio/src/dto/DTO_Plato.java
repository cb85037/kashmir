package dto;

import java.io.Serializable;

public class DTO_Plato implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int codigo;
	private String nombre;
	private DTO_Receta receta;
	private String rubro;
	private float precio;
	private float comision;
	private DTO_Producto producto;
	
	public DTO_Plato() {
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public DTO_Receta getReceta() {
		return receta;
	}

	public void setReceta(DTO_Receta receta) {
		this.receta = receta;
	}

	public String getRubro() {
		return rubro;
	}

	public void setRubro(String rubro) {
		this.rubro = rubro;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public float getComision() {
		return comision;
	}

	public void setComision(float comision) {
		this.comision = comision;
	}

	public DTO_Producto getProducto() {
		return producto;
	}

	public void setProducto(DTO_Producto producto) {
		this.producto = producto;
	}
	
	

//	public Plato pToEntity() {
//		// TODO Auto-generated method stub
//		Plato p=new Plato();
//		p.setCodigo(getCodigo());
//		p.setComision(getComision());
//		p.setNombre(getNombre());
//		p.setPrecio(getPrecio());
//		p.setRubro(getRubro());
//		p.setReceta(getReceta().rToEntity());
//		
//		
//		return null;
//	}
	
	
	
	
}
