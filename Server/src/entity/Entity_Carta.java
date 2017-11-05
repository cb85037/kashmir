package entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.*;

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
@Table(name = "Carta")
public class Entity_Carta implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "numero")
	private int numero;
	
	@OneToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name = "carta", referencedColumnName = "numero")
	private List<Entity_RubroCarta> rubros;
	
	@Column(name = "desde")
	private Date desde;
	
	@Column(name = "hasta")
	private Date hasta;
	

	public Entity_Carta(int numero, Date desde, Date hasta) {
		super();
		this.numero = numero;
		this.desde = desde;
		this.hasta = hasta;
		this.rubros = new ArrayList<Entity_RubroCarta>();
	}
	
	

	public Entity_Carta() {
		super();
		// TODO Auto-generated constructor stub
	}



	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public List<Entity_RubroCarta> getRubros() {
		return rubros;
	}

	public void setRubros(List<Entity_RubroCarta> rubros) {
		this.rubros = rubros;
	}

	public Date getDesde() {
		return desde;
	}

	public void setDesde(Date desde) {
		this.desde = desde;
	}

	public Date getHasta() {
		return hasta;
	}

	public void setHasta(Date hasta) {
		this.hasta = hasta;
	}
	
	
	
}
