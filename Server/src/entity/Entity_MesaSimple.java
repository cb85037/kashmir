package entity;

import java.util.List;


import javax.persistence.*;


import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import dto.DTO_Mesa;
import dto.DTO_MesaSimple;

@Entity
@Table(name = "MesaSimple")
public class Entity_MesaSimple extends Entity_Mesa{
	
	private static final long serialVersionUID = 1L;
		
	@ManyToMany(cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "vecinos",
	joinColumns = {@JoinColumn(name = "idMesa")},
	inverseJoinColumns = {@JoinColumn(name = "idVecino")})
	private List<Entity_MesaSimple> vecinos;
	
	
	public Entity_MesaSimple() {
		super();
		// TODO Auto-generated constructor stub
	}


	public List<Entity_MesaSimple> getVecinos() {
		return vecinos;
	}

	public void setVecinos(List<Entity_MesaSimple> vecinos) {
		this.vecinos = vecinos;
	}

	
	public DTO_Mesa getDTO(){
		DTO_MesaSimple m=new DTO_MesaSimple();
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


	public boolean esCompuesta() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	
}
