package Negocio;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name="notaDevolucion")
public class NotaDeDevolucion implements Serializable {
 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 @Column(name="id_notaDeDevolucion")
 private int id;
 private Date fecha;
 @ManyToOne
 @JoinColumn(name="id_provedor")
 private Provedor proveedor;
 @OneToMany(cascade=CascadeType.ALL)
 @JoinColumn(name="id_notaDeDevolucion")
 private List<ItemRemito> items;
public Date getFecha() {
	return fecha;
}
public void setFecha(Date fecha) {
	this.fecha = fecha;
}
public Provedor getProveedor() {
	return proveedor;
}
public void setProveedor(Provedor proveedor) {
	this.proveedor = proveedor;
}
public List<ItemRemito> getItems() {
	return items;
}
public void setItems(List<ItemRemito> items) {
	this.items = items;
}

public NotaDeDevolucion() {
	// TODO Auto-generated constructor stub
}
public void setId(int id) {
	this.id = id;
}
public int getId() {
	return id;
}

}
