package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "Salon")
public class Entity_Salon implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "nombre")
	private String nombre;
	
	@OneToMany(cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name = "salon", referencedColumnName = "nombre")
	private List<Entity_Sector> sectores;
	
	public Entity_Salon(String nombre) {
		super();
		this.nombre = nombre;
		this.sectores = new ArrayList<Entity_Sector>();
	}
	

	public Entity_Salon() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Entity_Sector> getSectores() {
		return sectores;
	}

	public void setSectores(List<Entity_Sector> sectores) {
		this.sectores = sectores;
	}
	
	
	
	
}
