package entity;

import java.io.Serializable;

import javax.persistence.*;

import dto.DTO_ItemComanda;


@Entity
@Table(name = "ItemComanda")
public class Entity_ItemComanda implements Serializable{

	private static final long serialVersionUID = 1L;

	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idPlato")

	private Entity_Plato plato;
	
	@Column
	private int cantidad;
	
	@Column
	private String estado;
	

	public Entity_ItemComanda() {
		// TODO Auto-generated constructor stub
	}

	public Entity_Plato getPlato() {
		return plato;
	}

	public void setPlato(Entity_Plato plato) {
		this.plato = plato;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public DTO_ItemComanda getDTO() {
		
		DTO_ItemComanda i = new DTO_ItemComanda();
		i.setCantidad(this.cantidad);
		i.setEstado(this.estado);
		i.setId(this.id);
		i.setPlato(this.plato.getDTO());
		
		return i;
	}
	
	
	
	
}
