package Negocio;
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
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="facturas")
public class Factura implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="factura_numero")
	private int numero;
	private String estado;
	@ManyToOne
	@JoinColumn(name="id_comanda")
	private Comanda comanda;
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name="factura_numero")
	private List<ItemFactura> items;
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name="factura_numero")
	private List<ItemCaja> itemsCaja;
	private Date fechaPago;
	public Factura(int numero, String estado, Comanda comanda,
			List<ItemFactura> items) {
		super();
		this.numero = numero;
		this.estado = estado;
		this.comanda = comanda;
		this.items = items;
	}
	public Factura() {
		// TODO Auto-generated constructor stub
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Comanda getComanda() {
		return comanda;
	}
	public void setComanda(Comanda comanda) {
		this.comanda = comanda;
	}
	public List<ItemFactura> getItems() {
		return items;
	}
	public void setItems(List<ItemFactura> items) {
		this.items = items;
	}
	
	public void addItem(ItemFactura it){
		if(this.items == null){
			this.items = new ArrayList<ItemFactura>();
		}
		
		this.items.add(it);
	}
	public float getTotal() {
		// TODO Auto-generated method stub
		float total = 0;
		for (ItemFactura itF : this.getItems()) {
			total = total + itF.getValor();
		}
		
		return total;
	}
	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}
	public Date getFechaPago() {
		return fechaPago;
	}
	public void setItemsCaja(List<ItemCaja> itemsCaja) {
		this.itemsCaja = itemsCaja;
	}
	public List<ItemCaja> getItemsCaja() {
		if(this.itemsCaja == null){
			this.itemsCaja = new ArrayList<ItemCaja>();
		}
		return itemsCaja;
	}
	
	public float getTotalPago() {
		// TODO Auto-generated method stub
		float pagoParcial = 0;
		for (ItemCaja itCaja : this.getItemsCaja()) {
			pagoParcial = (itCaja.getValor() * itCaja.getCantidad()) + pagoParcial;
		}
		return pagoParcial;
	}
	public void addItemCaja(ItemCaja itCaja) {
		// TODO Auto-generated method stub
		this.getItemsCaja().add(itCaja);
	}
}
