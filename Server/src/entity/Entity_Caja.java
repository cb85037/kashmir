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

@Entity
@Table(name = "Caja")
public class Entity_Caja implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int idCaja;
	
	@Column
	private String descripcion;
	
	@OneToMany(cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name = "idCaja")
	private List<Entity_CajaDiaria> cajasDiarias;
	
	public Entity_Caja() {
		this.cajasDiarias = new ArrayList<Entity_CajaDiaria>();
	}

	public int getIdCaja() {
		return idCaja;
	}

	public void setIdCaja(int idCaja) {
		this.idCaja = idCaja;
	}

	public List<Entity_CajaDiaria> getCajasDiarias() {
		return cajasDiarias;
	}

	public void setCajasDiarias(List<Entity_CajaDiaria> cajasDiarias) {
		this.cajasDiarias = cajasDiarias;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setCajaDiaria(Entity_CajaDiaria c) {
		this.cajasDiarias.add(c);
		
	}
	
	
	

	
}
