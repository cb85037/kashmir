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
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import dto.DTO_Receta;

@Entity
@Table(name = "Receta")
public class Entity_Receta implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "numero")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int numero;
	
	@Column(name = "nombre")
	private String nombre;
	
	@OneToMany(cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name = "idReceta")
	private List<Entity_ItemReceta> items;
	
	@Column(name = "descripcion")
	private String descripcion;
	

	public Entity_Receta() {
		super();
		// TODO Auto-generated constructor stub
	}



	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Entity_ItemReceta> getItems() {
		return items;
	}

	public void setItems(List<Entity_ItemReceta> items) {
		this.items = items;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



	public DTO_Receta getDTO() {
		
		DTO_Receta r = new DTO_Receta();
		r.setNumero(this.numero);
		r.setDescripcion(this.descripcion);
		r.setNombre(this.nombre);
		//Por ahora no se usan los items
		
		return r;
	}
	
	
	
	
	
}
