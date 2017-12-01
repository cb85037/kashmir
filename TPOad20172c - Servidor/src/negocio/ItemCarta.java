package Negocio;
import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import repositorio.ItemCartaDTO;
import repositorio.ItemPlanDeProduccionDTO;

@Entity
@Table(name="itemCarta")
public class ItemCarta  implements Serializable {
	@ManyToOne
	@JoinColumn(name="id_plato")
	private Plato plato;
	private float precio;
	private boolean disponible;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_itemCarta")
	private int id;
	
	public ItemCarta(Plato plato, float precio, boolean disponible, int id) {
		super();
		this.plato = plato;
		this.precio = precio;
		this.disponible = disponible;
		this.id = id;
	}
	public ItemCarta() {
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Plato getPlato() {
		return plato;
	}
	public void setPlato(Plato plato) {
		this.plato = plato;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public boolean isDisponible() {
		return disponible;
	}
	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
	
	/***********************************
	 ******METODOS DTO******************
	 ***********************************/
	
	public ItemCartaDTO generarDTO(){
		ItemCartaDTO dto = new ItemCartaDTO();
		if((this.id != 0)){
			dto.setId(id);
			dto.setPlato(this.plato.generarDTOdePlato());
			dto.setPrecio(precio);
			dto.setDisponible(disponible);
		}
		return dto;
	}
}
