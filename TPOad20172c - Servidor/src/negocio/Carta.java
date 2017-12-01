package Negocio;

import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="cartas")
public class Carta implements Serializable {
 @Id
 @GeneratedValue(strategy=GenerationType.AUTO)
 @Column(name="id_carta")
 private int id;
 private Date fechaDeFin;
 @LazyCollection(LazyCollectionOption.FALSE)
 @OneToMany(cascade=CascadeType.ALL)
 @JoinColumn(name="id_carta")
 private List<ItemCarta> platos;
 private boolean disponible;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public Date getFechaDeFin() {
	return fechaDeFin;
}
public void setFechaDeFin(Date fechaDeFin) {
	this.fechaDeFin = fechaDeFin;
}
public List<ItemCarta> getPlatos() {
	if(this.platos == null){
		this.platos = new ArrayList<ItemCarta>();
	}
	return platos;
}
public void setPlatos(List<ItemCarta> platos) {
	this.platos = platos;
}
public boolean isDisponible() {
	return disponible;
}
public void setDisponible(boolean disponible) {
	this.disponible = disponible;
}
public void AgregarNuevoItem(ItemCarta itemCarta) {
	// TODO Auto-generated method stub
	if(this.platos == null){
		this.platos = new ArrayList<ItemCarta>();
	}
	
	this.platos.add(itemCarta);
}
 
 
}
