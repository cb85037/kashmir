package administracion;

import java.util.ArrayList;
import java.util.List;

import dao.MesaDAO;
import dao.MozoDAO;
import dao.SectorDAO;
import dto.DTO_Empleado;
import dto.DTO_Mesa;
import dto.DTO_MesaCompuesta;
import dto.DTO_MesaSimple;
import dto.DTO_Mozo;
import entity.Entity_Mesa;
import entity.Entity_MesaCompuesta;
import entity.Entity_MesaSimple;
import entity.Entity_Mozo;
import entity.Entity_Sector;

public class Gestor_Mozo {
	private static MozoDAO dao=MozoDAO.getInstancia();
	private static Gestor_Mozo instancia;
	
	public static Gestor_Mozo getInstancia(){
		if(instancia == null)
			instancia = new Gestor_Mozo();
		return instancia;
	}
	
	public List<DTO_Mesa> listarMesasPorMozo(DTO_Mozo mozo, int cantComensales){
		Entity_Sector s = SectorDAO.getInstancia().buscarSectorMozo(mozo);
		
		List<Entity_Mesa> mesas = s.getMesas();
		
		List<DTO_Mesa> result=new ArrayList<DTO_Mesa>();
		
		for(Entity_Mesa m:mesas){
			if(m.getCapacidad() >= cantComensales){
				if(m.getEstado().equalsIgnoreCase("libre")){
					result.add(m.getDTO());
				}
			}
		}
		return result;
	}
	
	public void asociarMesaAMozo(DTO_Mozo mozoDto,DTO_Mesa mesaDto){
		
		Entity_Mozo mozo=dao.buscarMozoCodigo(mozoDto.getLegajo());
		Entity_MesaSimple mesa = (Entity_MesaSimple) MesaDAO.getInstancia().buscarMesa(mesaDto.getIdMesa());
		mozo.agregarMesa(mesa);
		
		dao.updateMozo(mozo);
	}
	
	public void asociarMesasAMozo(DTO_Mozo mozoDto,DTO_MesaCompuesta mesaDto){
		
		Entity_Mozo mozo=dao.buscarMozoCodigo(mozoDto.getLegajo());
		for(DTO_MesaSimple m : mesaDto.getComponentes()){
			Entity_MesaSimple mesa = (Entity_MesaSimple) MesaDAO.getInstancia().buscarMesa(mesaDto.getIdMesa());
			mozo.agregarMesa(mesa);
		}
		//dao.updateMozo(mozo);

	}

	public List<DTO_Mesa> mesasComandasNoFacturadas(DTO_Mozo mozo) {
		Entity_Sector s = SectorDAO.getInstancia().buscarSectorMozo(mozo);
		
		List<Entity_Mesa> mesas = s.getMesas();
		List<Entity_MesaCompuesta> compuestas = MesaDAO.getInstancia().listarMesasCompuestas();
		List<DTO_Mesa> result=new ArrayList<DTO_Mesa>();
		
		for(Entity_Mesa m:mesas){
			if(m.getEstado().equalsIgnoreCase("ocupada")){
				if(m.getComanda()!= null)
					if(m.getComanda().getEstado().equalsIgnoreCase("pendiente")){
						if(m.isFacturada() == false){
							result.add(m.getDTO());
						}
					}
			}
			
		}
		
		for(Entity_MesaCompuesta mc : compuestas){
			if(mc.getEstado().equalsIgnoreCase("ocupada")){
				if(mc.getComanda()!=null){
					if(mc.getComanda().getEstado().equalsIgnoreCase("pendiente")){
						if(mc.isFacturada() == false && mesas.contains(mc.getComponentes().get(0))){
							result.add(mc.getDTO());
						}
					}
				}
			}
		}
		
		return result;
	}

	public List<DTO_Mesa> listarMesasAbiertasMozo(DTO_Empleado emp) {
		DTO_Mozo m = new DTO_Mozo();
		m.setLegajo(emp.getLegajo());
		List<Entity_MesaSimple> mesas = new ArrayList<Entity_MesaSimple>();
		List<DTO_Mesa> result = new ArrayList<DTO_Mesa>();
		Entity_Mozo mozo = MozoDAO.getInstancia().buscarMozoCodigo(m.getLegajo());
		
		List<Entity_MesaCompuesta> compuestas = MesaDAO.getInstancia().listarMesasCompuestas();
		
		mesas = mozo.getMesasAsignadas();
		
		for(Entity_MesaCompuesta mc : compuestas){
			if(mc.getEstado().equals("ocupada")){
				mesas.removeAll(mc.getComponentes());
				result.add(mc.getDTO());
			}
		}
		
		if(mesas.size() != 0){
			for(Entity_Mesa mes: mesas){
				if(!(mes.getEstado().equalsIgnoreCase("libre")))
						result.add(mes.getDTO());
				}
		}
		
		return result;
	}
	
	
}
