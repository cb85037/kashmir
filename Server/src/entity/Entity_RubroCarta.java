package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import dto.DTO_Plato;
import dto.DTO_RubroCarta;

@Entity
@Table(name = "RubroCarta")
public class Entity_RubroCarta implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int idRubroCarta;
	
	@Column(name = "nombre")
	private String nombre;
	
	@OneToMany(cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name = "rubroCarta")
	private List<Entity_Plato> platos;
	
	public Entity_RubroCarta(String nombre) {
		super();
		this.nombre = nombre;
		this.platos = new ArrayList<Entity_Plato>();
	}
	
	

	public Entity_RubroCarta() {
		super();
		// TODO Auto-generated constructor stub
	}



	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Entity_Plato> getPlatos() {
		return platos;
	}

	public void setPlatos(List<Entity_Plato> platos) {
		this.platos = platos;
	}

	public int getIdRubroCarta() {
		return idRubroCarta;
	}

	public void setIdRubroCarta(int idRubroCarta) {
		this.idRubroCarta = idRubroCarta;
	}



	public DTO_RubroCarta getDTO() {
		
		List<DTO_Plato> platos = new ArrayList<DTO_Plato>();
		DTO_RubroCarta r = new DTO_RubroCarta();
		r.setIdRubroCarta(this.idRubroCarta);
		r.setNombre(this.nombre);
		if(this.platos != null){
			for(Entity_Plato p: this.platos){
				platos.add(p.getDTO());
			}
			r.setPlatos(platos);
		}
		
		return r;
		
	}
	
	
	
	
}
