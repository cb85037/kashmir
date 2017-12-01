package Negocio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import repositorio.ItemIngredienteDTO;
import repositorio.PlatoDTO;
import repositorio.ProductoDTO;

@Entity
@Table(name="platos")
public class Plato implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id_plato")
	private int id;
	private String nombre;
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="id_plato")
	private List<ItemIngrediente> ingredientes;
	private float comision;
	private boolean semiElaborado;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_area")
	private AreaPreparacion area;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<ItemIngrediente> getIngredientes() {
		return ingredientes;
	}
	public void setIngredientes(List<ItemIngrediente> ingredientes) {
		this.ingredientes = ingredientes;
	}
	public float getComision() {
		return comision;
	}
	public void setComision(float comision) {
		this.comision = comision;
	}
	public Plato(int id, String nombre, List<ItemIngrediente> ingredientes,
			float comision) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.ingredientes = ingredientes;
		this.comision = comision;
	}
	
	public Plato() {
		// TODO Auto-generated constructor stub
	}
	/***********************************
	 ******METODOS DTO******************
	 ***********************************/
	
	public PlatoDTO generarDTOdePlato(){
		PlatoDTO dto = new PlatoDTO();
		if((this.id != 0)){
			dto.setId(id);
			dto.setComision(comision);
			dto.setNombre(nombre);
			dto.setSemiElaborado(semiElaborado);
			Iterator<ItemIngrediente> it = ingredientes.iterator();
			while(it.hasNext()){
				dto.AddIngrediente(it.next().generarItemIngredienteDTO());
			}
		}
		return dto;
	}
	
	public static Plato crearInstanciaDePlato(PlatoDTO dto){
		Plato p = new Plato();
		p.setComision(dto.getComision());
		p.setId(dto.getId());
		p.setNombre(dto.getNombre());
		p.setSemiElaborado(dto.isSemiElaborado());
		if(dto.getIngredientes() != null){
			Iterator<ItemIngredienteDTO> it = dto.getIngredientes().iterator();
			while(it.hasNext()){
				p.AddIngrediente(ItemIngrediente.crearInstanciaDeProducto(it.next()));
			}
		}
		
		return p;
	}
	
	public void AddIngrediente(ItemIngrediente it){
		if(this.ingredientes == null){
			this.ingredientes = new ArrayList<ItemIngrediente>();
		}
		
		this.ingredientes.add(it);
	}
	public boolean isSemiElaborado() {
		return semiElaborado;
	}
	public void setSemiElaborado(boolean semiElaborado) {
		this.semiElaborado = semiElaborado;
	}
	public void setArea(AreaPreparacion area) {
		this.area = area;
	}
	public AreaPreparacion getArea() {
		return area;
	}
}
