package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import dto.DTO_Comanda;
import dto.DTO_Empleado;
import dto.DTO_ItemComanda;
import dto.DTO_Mozo;

@Entity
@Table(name = "Comandas")
public class Entity_Comanda implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "numero")
	private int numero;
	
	@OneToMany(cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name = "idComanda")
	private List<Entity_ItemComanda> items;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idMesa")
	private Entity_Mesa mesa;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idMozo")
	private Entity_Mozo mozo;
	
	@Column(name = "estado")
	private String estado;
	

	public Entity_Comanda() {
		// TODO Auto-generated constructor stub
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public List<Entity_ItemComanda> getItems() {
		return items;
	}

	public void setItems(List<Entity_ItemComanda> items) {
		this.items = items;
	}

	public Entity_Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Entity_Mesa mesa) {
		this.mesa = mesa;
	}

	public Entity_Mozo getMozo() {
		return mozo;
	}

	public void setMozo(Entity_Mozo mozo) {
		this.mozo = mozo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public void addItem(Entity_ItemComanda itemComanda){
		this.items.add(itemComanda);
	}

	public DTO_Comanda getDTO() {
		List<DTO_ItemComanda> lista = new ArrayList<DTO_ItemComanda>();
		DTO_Comanda c = new DTO_Comanda();
		c.setEstado(this.estado);
		c.setNumero(this.numero);
		DTO_Mozo m =  new DTO_Mozo();
		m.setLegajo(this.mozo.getLegajo());
		m.setNombre(this.mozo.getNombre());
		m.setUsuario(this.mozo.getUsuario());
		m.setPassword(this.mozo.getPassword());
		c.setMozo(m);
		if(this.items != null){
			for(Entity_ItemComanda i: this.items){
				lista.add(i.getDTO());
			}
			c.setItems(lista);
		}
		
		return c;
	}

	
}
