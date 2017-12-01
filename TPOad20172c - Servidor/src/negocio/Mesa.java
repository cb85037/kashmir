package Negocio;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import repositorio.MesaDTO;

@Entity
@Table(name="mesas")
public class Mesa implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_mesa")
	private int id;
	private int maxPersonas;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_sector")
	private Sector sector;
	private boolean estado;
	private int minPersonas;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_mesaPadre")
	private Mesa mesaPadre;
	
	
	public Mesa(int id, int maxPersonas, Sector sector, Mozo mozo,
			boolean estado, int minPersonas) {
		super();
		this.id = id;
		this.maxPersonas = maxPersonas;
		this.sector = sector;
		this.estado = estado;
		this.minPersonas = minPersonas;
	}
	public Mesa() {
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMaxPersonas() {
		return maxPersonas;
	}
	public void setMaxPersonas(int maxPersonas) {
		this.maxPersonas = maxPersonas;
	}
	public Sector getSector() {
		return sector;
	}
	public void setSector(Sector sector) {
		this.sector = sector;
	}

	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	public void setMinPersonas(int minPersonas) {
		this.minPersonas = minPersonas;
	}
	public int getMinPersonas() {
		return minPersonas;
	}
	
	/***********************************
	 ******METODOS DTO******************
	 ***********************************/
	
	public MesaDTO generarDTOdeMesa(){
		MesaDTO dto = new MesaDTO();
		if(!(this.id != 0)){
			dto.setEstado(this.estado);
			dto.setId(id);
			dto.setMaxPersonas(maxPersonas);
			dto.setMinPersonas(minPersonas);
			dto.setSector(sector.generarDTOdeSector());
		}
		return dto;
	}
	
	
	public static Mesa crearInstanciaDeMesa(MesaDTO dto){
		Mesa mesa = new Mesa();
		mesa.setEstado(dto.isEstado());
		mesa.setId(dto.getId());
		mesa.setMaxPersonas(dto.getMaxPersonas());
		mesa.setMinPersonas(dto.getMinPersonas());
		mesa.setSector(new Sector());
		
		return mesa;
	}
	public void setMesaPadre(Mesa mesaPadre) {
		this.mesaPadre = mesaPadre;
	}
	public Mesa getMesaPadre() {
		return mesaPadre;
	}
}
