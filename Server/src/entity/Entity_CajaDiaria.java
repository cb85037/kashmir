package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;




@Entity
@Table(name = "CajaDiaria")
public class Entity_CajaDiaria implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idCajaDiaria;
	
	@Column(name = "fechaApertura")
	private Date fechaApertura;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idCajero")
	private Entity_Encargado cajero;
	
	@Column
	private float montoInicial;
	
	@Column
	private float montoCierre;
	
	@Column
	private Date fechaCierre;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "idCajaDiaria")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Entity_Factura> facturas;
	

	public Entity_CajaDiaria() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Entity_Encargado getCajero() {
		return cajero;
	}



	public void setCajero(Entity_Encargado cajero) {
		this.cajero = cajero;
	}


	public Date getFechaApertura() {
		return fechaApertura;
	}



	public void setFechaApertura(Date fechaApertura) {
		this.fechaApertura = fechaApertura;
	}



	public float getMontoInicial() {
		return montoInicial;
	}



	public void setMontoInicial(float montoInicial) {
		this.montoInicial = montoInicial;
	}



	public float getMontoCierre() {
		return montoCierre;
	}



	public void setMontoCierre(float montoCierre) {
		this.montoCierre = montoCierre;
	}



	public Date getFechaCierre() {
		return fechaCierre;
	}



	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}



	public List<Entity_Factura> getFacturas() {
		return facturas;
	}



	public void setFacturas(List<Entity_Factura> facturas) {
		this.facturas = facturas;
	}



	public int getIdCajaDiaria() {
		return idCajaDiaria;
	}



	public void setIdCajaDiaria(int idCajaDiaria) {
		this.idCajaDiaria = idCajaDiaria;
	}


	

	}
