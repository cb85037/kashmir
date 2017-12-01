package Negocio;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="reservas")
public class Reserva implements Serializable {
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 @Column(name="id_reserva")
	 private int id;
	 private Date hora;
	 private int cantidadHoras;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getHora() {
		return hora;
	}
	public void setHora(Date hora) {
		this.hora = hora;
	}
	public int getCantidadHoras() {
		return cantidadHoras;
	}
	public void setCantidadHoras(int cantidadHoras) {
		this.cantidadHoras = cantidadHoras;
	}

	public Reserva(int id, Date hora, int cantidadHoras) {
		super();
		this.id = id;
		this.hora = hora;
		this.cantidadHoras = cantidadHoras;
	}
	public Reserva() {
		// TODO Auto-generated constructor stub
	}
	 
	 
}
