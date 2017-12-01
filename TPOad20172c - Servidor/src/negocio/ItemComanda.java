package Negocio;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table(name="itemsComanda")
public class ItemComanda implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_itemComanda")
	private int id;
	private boolean regular;
	@ManyToOne
	@JoinColumn(name="id_platoPrecio")
	private ItemCarta platoPrecio;
	public boolean isRegular() {
		return regular;
	}
	public void setRegular(boolean regular) {
		this.regular = regular;
	}
	public ItemCarta getPlatoPrecio() {
		return platoPrecio;
	}
	public void setPlatoPrecio(ItemCarta platoPrecio) {
		this.platoPrecio = platoPrecio;
	}
	public ItemComanda(boolean regular, ItemCarta platoPrecio) {
		super();
		this.regular = regular;
		this.platoPrecio = platoPrecio;
	}
	public ItemComanda() {
		// TODO Auto-generated constructor stub
	}
	
}
