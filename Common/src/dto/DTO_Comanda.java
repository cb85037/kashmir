package dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class DTO_Comanda implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	private int numero;
	private List<DTO_ItemComanda> items;
	private DTO_Mesa mesa;
	private DTO_Mozo mozo;
	private String estado;
	
	public DTO_Comanda() {
		super();
		this.items = new ArrayList<DTO_ItemComanda>();
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public List<DTO_ItemComanda> getItems() {
		return items;
	}

	public void setItems(List<DTO_ItemComanda> items) {
		this.items = items;
	}

	public DTO_Mesa getMesa() {
		return mesa;
	}

	public void setMesa(DTO_Mesa mesa) {
		this.mesa = mesa;
	}

	public DTO_Mozo getMozo() {
		return mozo;
	}

	public void setMozo(DTO_Mozo mozo) {
		this.mozo = mozo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	/*public List<ItemComanda> iToEntity(){
		List<ItemComanda> result;
		//con un for devolver los items comanda.
		for(ItemComandaDTO i: this.items){
			result.add(i.iToEntity());//falta
			
		}
	}
	*/

	
}
