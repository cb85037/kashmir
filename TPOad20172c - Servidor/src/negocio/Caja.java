package Negocio;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import dao.TurnoDAO;

import exceptions.CajaException;

@Entity
@Table(name="cajas")
public class Caja implements Serializable {
	private Date fechaInicio;
	private Date fechaCierre;
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="id_caja")
	private List<ItemTurnoCaja> items;
	private float total;
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="id_caja")
	private List<ItemComisionMozo> itemsComisionMozo;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_caja")
	private int id;
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaCierre() {
		return fechaCierre;
	}
	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}
	public List<ItemTurnoCaja> getItems() {
		return items;
	}
	public void setItems(List<ItemTurnoCaja> items) {
		this.items = items;
	}
	public float getTotal() {
		float total = 0;
		
		if(this.items != null){
			total = this.items.get(this.items.size() - 1).getTotal();
		}
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	public List<ItemComisionMozo> getItemsComisionMozo() {
		return itemsComisionMozo;
	}
	public void setItemsComisionMozo(List<ItemComisionMozo> itemsComisionMozo) {
		this.itemsComisionMozo = itemsComisionMozo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void AddItem(ItemTurnoCaja item) {
		// TODO Auto-generated method stub
		if(this.items == null){
			this.items = new ArrayList<ItemTurnoCaja>();
		}
		this.items.add(item);
	}
	
	public void RemoveItem(int id){
		if(this.items == null){
			this.items = new ArrayList<ItemTurnoCaja>();
		}
		
		Iterator<ItemTurnoCaja> it = this.items.iterator();
		while(it.hasNext()){
			ItemTurnoCaja item = it.next();
			if(item.getId() == id){
				it.remove();
				break;
			}
		}
	}
	public ItemTurnoCaja IniciarNuevoTurno(Cajero c, Caja anterior) throws CajaException {
		// TODO Auto-generated method stub
		if(this.fechaCierre != null){
			throw new CajaException("La caja ya fue cerrada");
		}
		
		ItemTurnoCaja it = new ItemTurnoCaja();
		ItemTurnoCaja ultimoItem = this.GetUltimoItem();
		if(ultimoItem != null){
			if(!ultimoItem.isFinalizado()){
				throw new CajaException("No finalizo el turno anterior");
			}
			
			Turno t = ultimoItem.getCajeroEncargado().getTurno();
			if(t.getHoraFin() != c.getTurno().getHoraInicio()){
				throw new CajaException("El cajero no puede abrir la caja, su turno no es el siguiente");
			}
			
			it.setItems(ultimoItem.getItems());
			
			ultimoItem.setItems(null);
			
		}  else{
			it.setItems(anterior.GetUltimoItem().getItems());
			anterior.GetUltimoItem().setItems(null);
		}
		it.setCajeroEncargado(c);
		it.setFinalizado(false);
		this.AddItem(it);
		return it;
	}
	
	public ItemTurnoCaja GetUltimoItem(){
		if(this.items != null){
			return this.items.get(this.items.size() -1);
		}
		
		return null;
	}
	public ItemTurnoCaja CerrarItemCaja(Cajero c) throws CajaException {
		// TODO Auto-generated method stub
		ItemTurnoCaja it = null;
		Date d = new Date(Calendar.getInstance().getTime().getTime());
		it = this.GetUltimoItem();
		if(it.isFinalizado()){
			throw new CajaException("El turno que desea cerrar ya fue cerrado");
		}
		if(it.getCajeroEncargado().getId() == c.getId()){
			it.setFinalizado(true);
			this.setTotal(this.getTotal());
		} else{
			throw new CajaException("el cajero que cierre la caja debe ser el mismo que la abrio");
		}
		return it;
	}
}
