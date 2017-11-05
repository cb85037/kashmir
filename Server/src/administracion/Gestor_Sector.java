package administracion;

import dao.SectorDAO;
import dto.DTO_Mozo;
import dto.DTO_Sector;
import entity.Entity_Sector;

public class Gestor_Sector {
	
	private SectorDAO dao= SectorDAO.getInstancia();
	private static Gestor_Sector instancia;
	
	private Gestor_Sector() {
	
	}

	public static Gestor_Sector getInstancia(){
		if (instancia==null)
			return instancia=new Gestor_Sector();
		return instancia;
	}
	
	public void altaSector(DTO_Sector s){
		Entity_Sector sector = new Entity_Sector(s.getNombre());
		dao.altaSector(sector);
	}

	public DTO_Sector buscarSector(DTO_Sector s){
		Entity_Sector sector;
		sector= SectorDAO.getInstancia().buscarSector(s.getIdSector());
		return sector.getDTO();
	}
	
	public DTO_Sector buscarSectorDelMozo(DTO_Mozo m){
		Entity_Sector sector;
		sector= SectorDAO.getInstancia().buscarSectorMozo(m);
		return sector.getDTO();
	}
	
//	public Sector buscarSucursal()
	
	
}
