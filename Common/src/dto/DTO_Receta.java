package dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



public class DTO_Receta implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int numero;
	private String nombre;
	private List<DTO_ItemReceta> items;
	private String descripcion;
	
	public DTO_Receta() {
		this.items = new ArrayList<DTO_ItemReceta>();
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<DTO_ItemReceta> getItems() {
		return items;
	}

	public void setItems(List<DTO_ItemReceta> items) {
		this.items = items;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

//	public Receta rToEntity() {
//		// TODO Auto-generated method stub
//		Receta r=new Receta();
//		r.setDescripcion(descripcion);
//		r.setNombre(nombre);
//		//spp
//		if (this.numero<=0)
//			r.setNumero(numero);
//		return r;
//	}

	
	
	
	
	
}
