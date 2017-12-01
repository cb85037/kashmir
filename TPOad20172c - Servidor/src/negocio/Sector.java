package Negocio;

import java.io.Serializable;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import exceptions.CajaException;

import repositorio.MesaDTO;
import repositorio.MozoDTO;
import repositorio.SectorDTO;

@Entity
@Table(name="sectores")
public class Sector implements Serializable {
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="id_sector")
	private List<Mozo> mozos;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_sector")
	private int id;
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="id_sector")
	private List<Mesa> mesas;
	@Column
	private String nombre;
	
	
	public List<Mozo> getMozos() {
		return mozos;
	}
	public void setMozos(List<Mozo> mozos) {
		this.mozos = mozos;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Mesa> getMesas() {
		return mesas;
	}
	public void setMesas(List<Mesa> mesas) {
		this.mesas = mesas;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Sector(List<Mozo> mozos, int id, List<Mesa> mesas, String nombre) {
		super();
		this.mozos = mozos;
		this.id = id;
		this.mesas = mesas;
		this.nombre = nombre;
	}
	
	public Sector() {
		// TODO Auto-generated constructor stub
	}
	
	/***********************************
	 ******METODOS DTO******************
	 ***********************************/
	
	public SectorDTO generarDTOdeSector(){
		SectorDTO dto = new SectorDTO();
		if(!(this.id != 0)){
			dto.setIdSector(getId());
			dto.setMesas(getMesasDTO());
			dto.setMozos(getMozosDTO());
			dto.setNombre(nombre);
		}
		return dto;
	}
	
	private List<MesaDTO> getMesasDTO()
	{
		List<MesaDTO> lst = new ArrayList<MesaDTO>();
		Iterator<Mesa> itr = mesas.iterator();
	      while(itr.hasNext()) {
	    	  Mesa m = itr.next();
	          lst.add(m.generarDTOdeMesa());
	      }
	     return lst;
	}
	
	private List<MozoDTO> getMozosDTO()
	{
		List<MozoDTO> lst = new ArrayList<MozoDTO>();
		Iterator<Mozo> itr = mozos.iterator();
	      while(itr.hasNext()) {
	    	  Mozo m = itr.next();
	          lst.add(m.generarDTOdeMozo());
	      }
	     return lst;
	}
	
	private static List<Mesa> getMesasFromDTO(List<MesaDTO> mesasDTO)
	{
		List<Mesa> lst = new ArrayList<Mesa>();
		Iterator<MesaDTO> itr = mesasDTO.iterator();
	      while(itr.hasNext()) {
	    	  MesaDTO m = itr.next();
	          lst.add(Mesa.crearInstanciaDeMesa(m));
	      }
	     return lst;
	}
	
	private static List<Mozo> getMozosFromDTO(List<MozoDTO> mozoDTO)
	{
		List<Mozo> lst = new ArrayList<Mozo>();
		Iterator<MozoDTO> itr = mozoDTO.iterator();
	      while(itr.hasNext()) {
	    	  MozoDTO m = itr.next();
	          lst.add(Mozo.crearInstanciaDeMozo(m));
	      }
	     return lst;
	}
	
	public static Sector crearInstanciaDeSector(SectorDTO dto){
		Sector sector = new Sector();
		sector.setId(dto.getIdSector());
		sector.setMesas(getMesasFromDTO(dto.getMesas()));
		sector.setMozos(getMozosFromDTO(dto.getMozos()));
		sector.setNombre(dto.getNombre());
		
		return sector;
	}
	public void addMesas(List<Mesa> mesas2) {
		// TODO Auto-generated method stub
		if(this.mesas == null){
			this.mesas = new ArrayList<Mesa>();
		}
		this.mesas.addAll(mesas2);
	}
	
	//FALTA VER QUE NO ESTE EN EL MISMO TURNO
	public boolean addMozo(Mozo m){
		if(this.mozos == null){
			this.mozos = new ArrayList<Mozo>();
		}
		
		if(this.existeMozo(m)){
			return false;
		}
		
		int cantidadMesas = this.mesas.size();
		int cantidadMozosEnElTurno = this.mozosPorTurno(m.getTurno()).size();
		
		if (cantidadMozosEnElTurno == 0 
				|| (cantidadMesas > 5 && cantidadMozosEnElTurno == 1 ) 
				|| (cantidadMesas <= 5 && cantidadMozosEnElTurno == 0)){
			this.mozos.add(m);
			return true;
		}
		
		return false;
	}
	
	private List<Mozo> mozosPorTurno(Turno t){
		List<Mozo> mozosPorTurno = new ArrayList<Mozo>();
		for (Mozo mozo : this.mozos) {
			if(mozo.getTurno().getId() == t.getId()){
				mozosPorTurno.add(mozo);
			}
		}
		return mozosPorTurno;
	}
	
	private boolean existeMozo(Mozo m){
		for (Mozo mozo : this.mozos) {
			if(mozo.getId() == m.getId()){
				return true;
			}
		}
		return false;
	}
	public Mesa AbrirMesa(int cantComensales) throws CajaException {
		// TODO Auto-generated method stub
		if(cantComensales <= 4){
			if(getMesasLibresDeCuatro().size() > 0){
				return getMesasLibresDeCuatro().get(0);
			} else if (getMesasLibresDeSeis().size() > 0){
				return getMesasLibresDeSeis().get(0);
			} else if (getMesasLibresDeOcho().size() > 0){
				return getMesasLibresDeOcho().get(0);
			} else {
				return null;
			}
		} else if(cantComensales > 4 && cantComensales <= 7){
			if (getMesasLibresDeSeis().size() > 0){
				return getMesasLibresDeSeis().get(0);
			} else if (getMesasLibresDeOcho().size() > 0){
				return getMesasLibresDeOcho().get(0);
			} else {
				return null;
			}
		} else if (cantComensales > 7 && cantComensales <= 10){
			if(getMesasLibresDeOcho().size() > 0){
				return getMesasLibresDeOcho().get(0);
			}
		}  else {
			return this.UnirMesas(this.getMesasLibres(), cantComensales) ;
		}
		
		return null;
	}
	
	private Mesa UnirMesas(List<Mesa> mesas, int cantidadPersonas) {
		// TODO Auto-generated method stub
		int cantidad = 0;
		Iterator<Mesa> mesasLibres = mesas.iterator();
		List<Mesa> unir = new ArrayList<Mesa>();
		int cantidadAcumulada = 0;
		while(mesasLibres.hasNext()){
			Mesa m = mesasLibres.next();
			cantidad++;
			unir.add(m);
			cantidadAcumulada = this.GetCantidadAcumulada(unir);
			if(this.SonContiguas(unir) && cantidadAcumulada >= cantidadPersonas){
				Mesa mesaNueva = new Mesa();
				mesaNueva.setEstado(false);
				mesaNueva.setMaxPersonas(cantidadAcumulada);
				mesaNueva.setMinPersonas(cantidadAcumulada);
				mesaNueva.setSector(this);
				mesaNueva.setEstado(false);
				
				for (Mesa mesa : unir) {
					mesa.setEstado(false);
					mesa.setMesaPadre(mesaNueva);
				}
				return mesaNueva;
			} else if(!this.SonContiguas(unir)){
				for (int i = 0; i <= cantidad - 1; i++) {
					mesas.remove(0);
				}
				
				return this.UnirMesas(mesas, cantidadPersonas);
			}
		}
		return null;
	}
	private boolean SonContiguas(List<Mesa> unir) {
		// TODO Auto-generated method stub
		if(unir.size() == 1){
			return true;
		}
		
		Mesa m1 = unir.get(unir.size() - 1);
		Mesa m2 = unir.get(unir.size() - 2);
		
		return m1.getId() == m2.getId() + 1;
	}
	private int GetCantidadAcumulada(List<Mesa> unir) {
		// TODO Auto-generated method stub
		int total = 0;
		for (Mesa mesa : unir) {
			total = total + mesa.getMinPersonas();
		}
		return total;
	}
	public List<Mesa> getMesasLibres(){
		List<Mesa> mesasLibres = new ArrayList<Mesa>();
		for (Mesa m : this.mesas) {
			if(m.isEstado()){
				mesasLibres.add(m);
			}
		}
		
		return mesasLibres;
	}
	
	public List<Mesa> getMesasLibresDeCuatro(){
		List<Mesa> mesasLibres = new ArrayList<Mesa>();
		for (Mesa m : this.mesas) {
			if(m.isEstado() && m.getMinPersonas() == 4){
				mesasLibres.add(m);
			}
		}
		
		return mesasLibres;
	}
	
	public List<Mesa> getMesasLibresDeSeis(){
		List<Mesa> mesasLibres = new ArrayList<Mesa>();
		for (Mesa m : this.mesas) {
			if(m.isEstado() && m.getMinPersonas() == 6){
				mesasLibres.add(m);
			}
		}
		
		return mesasLibres;
	}
	
	public List<Mesa> getMesasLibresDeOcho(){
		List<Mesa> mesasLibres = new ArrayList<Mesa>();
		for (Mesa m : this.mesas) {
			if(m.isEstado() && m.getMinPersonas() == 8){
				mesasLibres.add(m);
			}
		}
		
		return mesasLibres;
	}
	
	public void LiberarMesa(Mesa mesa) {
		// TODO Auto-generated method stub

		if(this.isMesaPadre(mesa)){
			Iterator<Mesa> mesasEnSector = this.getMesas().iterator();
			while(mesasEnSector.hasNext()){
				Mesa m = mesasEnSector.next();
				if(m.getMesaPadre() != null && m.getMesaPadre().getId() == mesa.getId()){
					m.setEstado(true);
					m.setMesaPadre(null);
				}
			}
			
			Iterator<Mesa> mesasPadres = this.getMesas().iterator();
			
			while(mesasPadres.hasNext()){
				if(mesasPadres.next().getId() == mesa.getId()){
					mesasPadres.remove();
				}
			}
		} else{
			mesa.setEstado(true);
		}
		
	}
	private boolean isMesaPadre(Mesa mesa) {
		// TODO Auto-generated method stub
		for (Mesa m : this.getMesas()) {
			if(m.getMesaPadre() != null && m.getMesaPadre().getId() == mesa.getId()){
				return true;
			}
		}
		return false;
	}
}
