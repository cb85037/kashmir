package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;






import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import dto.DTO_Sector;
import dto.DTO_Local;

@Entity
@Table(name="Sector")
public class Entity_Sector implements Serializable{
	
	private static final long serialVersionUID = 1L;

@Id
@Column(name="id")
@GeneratedValue(strategy=GenerationType.AUTO)
	private int idSector;

@Column(name="nombre")
	private String nombre;

@OneToMany(cascade=CascadeType.ALL)
@LazyCollection(LazyCollectionOption.FALSE)
@JoinColumn(name="idSector",referencedColumnName="id")
	private List<Entity_Mesa> mesas;

@OneToMany(cascade=CascadeType.ALL)
@LazyCollection(LazyCollectionOption.FALSE)
@JoinColumn(name="idSector",referencedColumnName="id")
	private List<Entity_Mozo> mozos;
	

	public Entity_Sector() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Entity_Sector(String nombre) {
		this.nombre = nombre;
	}


	public int getIdSector() {
		return idSector;
	}

	public void setIdSector(int idSector) {
		this.idSector = idSector;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public List<Entity_Mesa> getMesas() {
		return mesas;
	}


	public void setMesas(List<Entity_Mesa> mesas) {
		this.mesas = mesas;
	}

	public List<Entity_Mozo> getMozos() {
		return mozos;
	}

	public void setMozos(List<Entity_Mozo> mozos) {
		this.mozos = mozos;
	}
	
	public DTO_Sector getDTO(){
		DTO_Sector sec = new DTO_Sector();
		sec.setIdSector(this.idSector);
		sec.setNombre(this.nombre);
		return sec;
	}		
}
