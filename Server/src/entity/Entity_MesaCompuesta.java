package entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.CollectionOfElements;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import dto.DTO_MesaCompuesta;
import dto.DTO_Mesa;
import dto.DTO_MesaSimple;

@Entity
@Table(name = "MesaCompuesta")
public class Entity_MesaCompuesta extends Entity_Mesa {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "idMesaCompuesta")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Entity_MesaSimple> componentes;
	
	
	public Entity_MesaCompuesta() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public List<Entity_MesaSimple> getComponentes() {
		return componentes;
	}
	public void setComponentes(List<Entity_MesaSimple> componentes) {
		this.componentes = componentes;
	}

	public boolean esCompuesta() {
		// TODO Auto-generated method stub
		return true;
	}

	public DTO_Mesa getDTO(){
		DTO_MesaCompuesta m=new DTO_MesaCompuesta();
		m.setIdMesa(this.idMesa);
		m.setNumero(this.numero);
		m.setEstado(this.estado);
		m.setCapacidad(this.capacidad);
		m.setFacturada(this.facturada);
		if(this.comanda != null)
			m.setComanda(this.comanda.getDTO());
		
		//Falta logica de mover vecinos	
		//Falta poner la comanda
		
		return m;
	}

}
