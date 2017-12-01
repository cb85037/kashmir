package Negocio;

import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="comandas")
public class Comanda implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_comanda")
	private int id;
	@ManyToOne
	@JoinColumn(name="id_mesa")
	private Mesa mesa;
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="id_comanda")
	private List<ItemComanda> itemsComanda;
	@Column
	private boolean finalizada;
	@Column
	private Date fechaLiberacionMesa;
	@ManyToOne
	@JoinColumn(name="id_mozo")
	private Mozo mozo;
	
	public Mesa getMesa() {
		return mesa;
	}
	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}
	public List<ItemComanda> getItemsComanda() {
		return itemsComanda;
	}
	public Comanda(Mesa mesa, List<ItemComanda> itemsComanda,
			boolean finalizada, Date fechaDeEntregaFactura, int numero, Mozo mozo) {
		super();
		this.mesa = mesa;
		this.itemsComanda = itemsComanda;
		this.finalizada = finalizada;
		this.fechaLiberacionMesa = fechaDeEntregaFactura;
		this.mozo = mozo;
	}
	public Comanda() {
		// TODO Auto-generated constructor stub
	}
	public void setItemsComanda(List<ItemComanda> itemsComanda) {
		this.itemsComanda = itemsComanda;
	}
	public boolean isFinalizada() {
		return finalizada;
	}
	public void setFinalizada(boolean finalizada) {
		this.finalizada = finalizada;
	}
	public Date getFechaLiberacionMesa() {
		return fechaLiberacionMesa;
	}
	public void setFechaLiberacionMesa(Date fechaLiberacionMesa) {
		this.fechaLiberacionMesa = fechaLiberacionMesa;
	}
	public void setMozo(Mozo mozo) {
		this.mozo = mozo;
	}
	public Mozo getMozo() {
		return mozo;
	}

	public float calcularComisionMozo() {
		// TODO Auto-generated method stub
		float total = 0;
		for (ItemComanda it : this.itemsComanda) {
			if(it.isRegular()){
				total = total + (it.getPlatoPrecio().getPrecio() 
						* it.getPlatoPrecio().getPlato().getComision() / 100);
			}
			
		}
		return total;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void AgregarItem(ItemComanda itComanda) {
		// TODO Auto-generated method stub
		if(this.itemsComanda == null){
			this.itemsComanda = new ArrayList<ItemComanda>();
		}
		
		 this.itemsComanda.add(itComanda);
	}
}
