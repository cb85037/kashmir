package administracion;

import java.util.ArrayList;
import java.util.List;

import dao.ComandaDAO;
import dao.MesaDAO;
import dao.MozoDAO;
import dao.SectorDAO;
import dto.DTO_Empleado;
import dto.DTO_Mesa;
import dto.DTO_MesaCompuesta;
import dto.DTO_MesaSimple;
import dto.DTO_Mozo;
import dto.DTO_Sector;
import entity.Entity_Comanda;
import entity.Entity_Mesa;
import entity.Entity_MesaCompuesta;
import entity.Entity_MesaSimple;
import entity.Entity_Mozo;
import entity.Entity_Sector;

public class Gestor_Mesa {
	private static Gestor_Mesa instancia;
	private MesaDAO dao = MesaDAO.getInstancia();
	
	private Gestor_Mesa() {
			
	}
	
	public static Gestor_Mesa getInstancia(){
		if(instancia == null)
			instancia = new Gestor_Mesa();
		return instancia;
	}


	public List<DTO_Mesa> listarMesasLibresPorSector(DTO_Sector sector, int cantComensales) {
		
		List<DTO_Mesa> result=new ArrayList<DTO_Mesa>();
		
		//Valida Sector
		Gestor_Sector.getInstancia().buscarSector(sector);
		
		//Buscar Mesas disponibles
		List<Entity_MesaSimple> mesasSimples= dao.listarMesasLibresPorSector(sector.getIdSector());
	
		if (mesasSimples != null) {
			for(Entity_MesaSimple ms:mesasSimples){
				if (ms.getCapacidad()>=cantComensales){
					result.add(ms.getDTO());
				}
			}
		}
		
		return result;
	}
	
	public List<DTO_Mesa> armarMesaCompuesta(DTO_Mozo mozo, int cantComensales) {
		
		List<DTO_Mesa> result=new ArrayList<DTO_Mesa>();
		
		//Valida Sector
		Entity_Sector s = SectorDAO.getInstancia().buscarSectorMozo(mozo);
		
		//Buscar Mesas disponibles 
		List<Entity_MesaSimple> mesasSimples= dao.listarMesasLibresPorSector(s.getIdSector());
		List<Entity_MesaSimple> posibles= unirMesas(mesasSimples,cantComensales);
	
		if (posibles != null) {
			for(Entity_MesaSimple ms:posibles){
				result.add(ms.getDTO());
			}
		}
		//DEVUELVO LAS MESAS QUE DEBE UNIR
		return result;
	}
	
	private List<Entity_MesaSimple> unirMesas(List<Entity_MesaSimple> mesas, int cantComensales){
		//Para todas las mesas disponibles busco los vecinos
		for(Entity_MesaSimple ms:mesas){
			int cont=0;
			List<Entity_MesaSimple> aux=new ArrayList<Entity_MesaSimple>();
			
			cont=cont+ms.getCapacidad();
			aux.add(ms);
			List<Entity_MesaSimple> vecinos=ms.getVecinos();
			for(Entity_MesaSimple vecino:vecinos){
				if (cont<cantComensales && vecino.getEstado().equalsIgnoreCase("libre")){
					cont=cont+vecino.getCapacidad();
					aux.add(vecino);
				}
			}
			
			if (cont>=cantComensales){
				return aux;
			}

		}
		return null;
		
	}

	//Apertura Mesa Simple
	public boolean abrirMesaSimpl(DTO_MesaSimple mesaDto, DTO_Mozo mozoDto){

		Entity_MesaSimple mesaSimp = (Entity_MesaSimple) dao.buscarMesa(mesaDto.getIdMesa());
		Entity_Mozo mozo = MozoDAO.getInstancia().buscarMozoCodigo(mozoDto.getLegajo());
		
		//Asocia comanda sin items a instancia de Mesa
		Entity_Comanda comanda=new Entity_Comanda();
		comanda.setMesa(mesaSimp);
		comanda.setMozo(mozo);
		comanda.setEstado("pendiente");
		comanda.setItems(null);
		
		
		ComandaDAO.getInstancia().save(comanda);
		
		//Entity_Comanda c = ComandaDAO.getInstancia().buscarComandaMesa(mesaSimp);
		
		mesaSimp.setComanda(comanda);
		mesaSimp.setEstado("ocupada");
		
		dao.mergeMesa(mesaSimp);
		
		return true;
		
	}
	
	//Apertura Mesa Compuesta
	public DTO_Mesa abrirMesaComp(DTO_MesaCompuesta mesas, DTO_Mozo mozoDto){
			
		//Obtiene el mayor numero de mesa	
		/*int numMesaComp=0;
		for (DTO_MesaSimple ms:mesas){
			if (ms.getNumero()>numMesaComp){
				numMesaComp=ms.getNumero();
			}
		}*/
			
		//Crea instancia de mesa compuesta	
		Entity_MesaCompuesta mesaComp = new Entity_MesaCompuesta();
		mesaComp.setEstado("ocupada");
		mesaComp.setNumero(mesas.getNumero());

//		mesaComp.setIdMesa(mesaDto.getIdMesa());
		//mesaComp.setNumero(numMesaComp);
		dao.altaMesa(mesaComp);	
			
		//Actualiza el estado de cada mesa componente
		List<Entity_MesaSimple> listaMS = new ArrayList<Entity_MesaSimple>();
		for(DTO_MesaSimple mList:mesas.getComponentes()){
			Entity_MesaSimple mesaSimp= (Entity_MesaSimple) dao.buscarMesa(mList.getIdMesa());
			mesaSimp.setEstado("ocupada");
			dao.mergeMesa(mesaSimp);
			listaMS.add(mesaSimp);
		}
			
		//Asocia mesas componentes
		mesaComp.setComponentes(listaMS);
		
		//Asocia comanda sin items a instancia de Mesa
		Entity_Mozo mozo=MozoDAO.getInstancia().buscarMozoCodigo(mozoDto.getLegajo());
		Entity_Comanda comanda=new Entity_Comanda();
		comanda.setMesa(mesaComp);
		comanda.setMozo(mozo);
		comanda.setEstado("pendiente");
		comanda.setItems(null);
		//ComandaDAO.getInstancia().save(comanda);
		mesaComp.setComanda(comanda);
		dao.mergeMesa(mesaComp);
		return mesaComp.getDTO();
	}
	
	//Cierre Mesa Simple
	public boolean cerrarMesaSimpl(DTO_Mesa mesa, DTO_Empleado emp){
		Entity_MesaSimple m = MesaDAO.getInstancia().buscarMesa(mesa.getIdMesa());
		Entity_Mozo mozo = MozoDAO.getInstancia().buscarMozoCodigo(emp.getLegajo());
		List<Entity_MesaSimple> asignadas = new ArrayList<Entity_MesaSimple>();
		
		if(m == null){
			Entity_MesaCompuesta mc = MesaDAO.getInstancia().buscarMesaCompuesta(mesa.getIdMesa());
			for(Entity_MesaSimple mes: mc.getComponentes()){
				asignadas.add(mes);
				mes.setEstado("libre");
				MesaDAO.getInstancia().mergeMesa(mes);
			}
			
			mc.getComanda().setEstado("completa");
			ComandaDAO.getInstancia().updateComanda(mc.getComanda());
			mc.setComponentes(null);
			mc.setEstado("cerra");
			mc.setFacturada(false);
			MesaDAO.getInstancia().mergeMesa(mc);
			//MesaDAO.getInstancia().deleteMesa(mc);
			
			List<Entity_MesaSimple> mesasNuevas =mozo.getMesasAsignadas();
			mesasNuevas.removeAll(asignadas);
			mozo.setMesasAsignadas(mesasNuevas);
			//MozoDAO.getInstancia().updateMozo(mozo);
			
			return true;
		
		}
		else{
				//Desvinculo esa mesa del mozo
			for(Entity_MesaSimple mes: mozo.getMesasAsignadas()){
				if(mes.getIdMesa() != m.getIdMesa())
				asignadas.add(mes);
			}
			mozo.setMesasAsignadas(asignadas);
			//MozoDAO.getInstancia().updateMozo(mozo);
			m.getComanda().setEstado("completa");
			ComandaDAO.getInstancia().updateComanda(m.getComanda());
			m.setComanda(null); //Saco la comanda de la mesa
			m.setEstado("libre");
			m.setFacturada(false); //La mesa ya esta facturada
			MesaDAO.getInstancia().mergeMesa(m);
			return true;
		}

		
		
	}
		
	//Cierre Mesa Compuesta
	public void cerrarMesaComp(DTO_Mesa mesa){
		
	
	}	
	
	public Entity_Mesa buscarMesaNombre(DTO_Mesa mes) {
		Entity_MesaSimple me = MesaDAO.getInstancia().buscarMesa(mes.getIdMesa());
		if(me== null){
			Entity_MesaCompuesta mc = MesaDAO.getInstancia().buscarMesaCompuesta(mes.getIdMesa());
			return mc;
		}
		return me;
		
	}

}
