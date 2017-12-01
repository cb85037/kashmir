package Negocio;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import repositorio.ItemCompraDTO;
import repositorio.OrdenDeCompraDTO;

@Entity
@Table(name="ordencompras")
public class OrdenDeCompra implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id_ordenDeCompra")
	private int id;
	@ManyToOne
	@JoinColumn(name="id_provedor")
	private Provedor proveedor;
	private Date fecha;
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;
	private String nombre;
	private String estado;
	private boolean porFaltante;
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="id_ordenDeCompra")
	private List<ItemCompra> items;
	public OrdenDeCompra(Provedor proveedor, Date fecha, Usuario usuario,
			String nombre, String estado, boolean porFaltante,
			List<ItemCompra> items) {
		super();
		this.proveedor = proveedor;
		this.fecha = fecha;
		this.usuario = usuario;
		this.nombre = nombre;
		this.estado = estado;
		this.porFaltante = porFaltante;
		this.items = items;
	}
	public OrdenDeCompra() {
		// TODO Auto-generated constructor stub
	}
	public Provedor getProveedor() {
		return proveedor;
	}
	public void setProveedor(Provedor proveedor) {
		this.proveedor = proveedor;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public boolean isPorFaltante() {
		return porFaltante;
	}
	public void setPorFaltante(boolean porFaltante) {
		this.porFaltante = porFaltante;
	}
	public List<ItemCompra> getItems() {
		return items;
	}
	public void setItems(List<ItemCompra> items) {
		this.items = items;
	}
	
	/***********************************
	 ******METODOS DTO******************
	 ***********************************/
	
	public OrdenDeCompraDTO generarDTO(){
		OrdenDeCompraDTO dto = new OrdenDeCompraDTO();
		if((this.id != 0)){
			dto.setEstado(estado);
			dto.setFecha(fecha);
			dto.setId(id);
			dto.setNombre(nombre);
			dto.setPorFaltante(porFaltante);
			dto.setProveedor(proveedor.generarDTOdeProveedor());
			Iterator<ItemCompra> it = this.items.iterator();
			while(it.hasNext()){
				dto.AddItem(it.next().generarDTO());
			}
		}
		return dto;
	}
	
	public void AddItem(ItemCompra it){
		if(this.items == null){
			this.items = new ArrayList<ItemCompra>();
		}
		
		this.items.add(it);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public static OrdenDeCompra crearInstancia(OrdenDeCompraDTO dto){
		OrdenDeCompra p = new OrdenDeCompra();
		p.setEstado(dto.getEstado());
		p.setFecha(dto.getFecha());
		p.setId(dto.getId());
		p.setNombre(dto.getNombre());
		p.setPorFaltante(dto.isPorFaltante());
		p.setProveedor(Provedor.crearInstanciaDeProveedor(dto.getProveedor()));
		Iterator<ItemCompraDTO> it = dto.getItems().iterator();
		while(it.hasNext()){
			p.AddItem(ItemCompra.crearInstancia(it.next()));
		}
		return p;
	}
}
