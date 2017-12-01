package Negocio;
import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import repositorio.ItemIngredienteDTO;
import repositorio.ItemPlanDeProduccionDTO;
import repositorio.PlatoDTO;
import repositorio.ProductoDTO;
import repositorio.TurnoDTO;

@Entity
@Table(name="itemPlanDeProduccion")
public class ItemPlanDeProduccion implements Serializable {
	@ManyToOne
	@JoinColumn(name="id_plato")
	private Plato semielaborado;
	private int cantidad;
	private float tiempoPorUnidadEnHoras;
	@ManyToOne
	@JoinColumn(name="id_restaurante")
	private Restaurante restaurante;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_itemPlanDeProduccion")
	private int id;
	private boolean libre;
	private boolean reposicionDeIngredientes;
	
	public ItemPlanDeProduccion(Plato semielaborado, int cantidad,
			float tiempoPorUnidadEnHoras, Restaurante restaurante, int id
			, boolean libre) {
		super();
		this.semielaborado = semielaborado;
		this.cantidad = cantidad;
		this.tiempoPorUnidadEnHoras = tiempoPorUnidadEnHoras;
		this.setRestaurante(restaurante);
		this.id = id;
		this.libre = libre;
	}
	public ItemPlanDeProduccion() {
		// TODO Auto-generated constructor stub
	}
	public Plato getSemielaborado() {
		return semielaborado;
	}
	public void setSemielaborado(Plato semielaborado) {
		this.semielaborado = semielaborado;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public float getTiempoPorUnidadEnHoras() {
		return tiempoPorUnidadEnHoras;
	}
	public void setTiempoPorUnidadEnHoras(float tiempoPorUnidadEnHoras) {
		this.tiempoPorUnidadEnHoras = tiempoPorUnidadEnHoras;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}
	public Restaurante getRestaurante() {
		return restaurante;
	}
	public void setLibre(boolean libre) {
		this.libre = libre;
	}
	public boolean isLibre() {
		return libre;
	}
	
	/***********************************
	 ******METODOS DTO******************
	 ***********************************/
	
	public ItemPlanDeProduccionDTO generarDTO(){
		ItemPlanDeProduccionDTO dto = new ItemPlanDeProduccionDTO();
		if((this.id != 0)){
			dto.setId(id);
			dto.setCantidad(cantidad);
			dto.setLibre(libre);
			dto.setSemielaborado(semielaborado.generarDTOdePlato());
			dto.setRestaurante(restaurante.getId());
			dto.setTiempoPorUnidadEnHoras(tiempoPorUnidadEnHoras);
		}
		return dto;
	}
	
	public static ItemPlanDeProduccion crearInstanciaDeMesa(ItemPlanDeProduccionDTO dto){
		ItemPlanDeProduccion item = new ItemPlanDeProduccion();
		item.setCantidad(dto.getCantidad());
		item.setId(dto.getId());
		item.setLibre(dto.isLibre());
		item.setSemielaborado(Plato.crearInstanciaDePlato(dto.getSemielaborado()));
		item.setTiempoPorUnidadEnHoras(dto.getTiempoPorUnidadEnHoras());
		return item;
	}
	public boolean isReposicionDeIngredientes() {
		return reposicionDeIngredientes;
	}
	public void setReposicionDeIngredientes(boolean reposicionDeIngredientes) {
		this.reposicionDeIngredientes = reposicionDeIngredientes;
	}
}
