package entity;

import java.io.Serializable;

import javax.persistence.*;

import dto.DTO_Mesa;
import dto.DTO_MesaSimple;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Entity_Mesa implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	protected int idMesa;
	
	@Column(name = "numero")
	protected int numero;
	
	@Column(name = "capacidad")
	protected int capacidad;
	
	@Column(name = "estado")
	protected String estado;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="idComanda")
	protected Entity_Comanda comanda;
	
	@Column(name = "facturada")
	protected boolean facturada;

	public int getIdMesa() {
		return idMesa;
	}

	public void setIdMesa(int idMesa) {
		this.idMesa = idMesa;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Entity_Comanda getComanda() {
		return comanda;
	}

	public void setComanda(Entity_Comanda comanda) {
		this.comanda = comanda;
	}

	public boolean isFacturada() {
		return facturada;
	}

	public void setFacturada(boolean facturada) {
		this.facturada = facturada;
	}
	
	public abstract DTO_Mesa getDTO();
	
	public abstract boolean esCompuesta();

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idMesa;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entity_Mesa other = (Entity_Mesa) obj;
		if (idMesa != other.idMesa)
			return false;
		return true;
	}
	
	
	
}
