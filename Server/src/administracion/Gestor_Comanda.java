package administracion;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import dao.ComandaDAO;
import dao.MesaDAO;
import dao.MozoDAO;
import dao.PlatoDAO;
import dao.LocalDAO;
import dto.DTO_Comanda;
import dto.DTO_Empleado;
import dto.DTO_ItemComanda;
import dto.DTO_Mesa;
import dto.DTO_Plato;
import entity.Entity_Comanda;
import entity.Entity_ItemComanda;
import entity.Entity_Mesa;
import entity.Entity_Mozo;
import entity.Entity_Plato;
import entity.Entity_Salon;
import entity.Entity_Sector;
import entity.Entity_Local;

public class Gestor_Comanda {
	private static Gestor_Comanda instancia;
	private ComandaDAO dao = ComandaDAO.getInstancia();
	
	
	public Gestor_Comanda() {
		
		
	}
	
	public static Gestor_Comanda getInstancia(){
		if(instancia == null)
			instancia = new Gestor_Comanda();
		return instancia;
	}
	
	public void altaComanda(DTO_Mesa mes, DTO_Comanda c) throws RemoteException {
//		MesaSimple mesa = MesaManager.getInstancia().buscarMesaNombre(mes);
		Entity_Mozo m = MozoDAO.getInstancia().buscarMozoCodigo(c.getMozo().getLegajo());
		//por cada item comandadto hago un item comanda como la gente
		Entity_Comanda comanda=new Entity_Comanda();
		comanda.setEstado("Nueva");
//		comanda.setMesa(mesa);
		comanda.setMozo(m);
		comanda.setItems(null);
		//aca termina, solo genera el encabezado de la comanda
//		
//		for(ItemComandaDTO i: c.getItems()){
//			ItemComanda it=new ItemComanda();
//			it.setCantidad(i.getCantidad());
//			it.setEstado(i.getEstado());
//			//aca tengo que buscar el plato...
//			it.setPlato(PlatoDAO.getInstancia().buscarPlatoCodigo(i.getPlato().getCodigo()));
//		}
		
		
	}
	
	public boolean adicionarItemAComanda(DTO_Comanda comanda, DTO_Plato plato, int cantidad, DTO_Empleado emp){
		boolean respuesta = false;
		
		Entity_Comanda c = dao.buscarComanda(comanda.getNumero());
		Entity_Plato p = PlatoDAO.getInstancia().buscarPlatoCodigo(plato);
		
		//verifico si se puede producir el plato de acuerdo a su receta y a la
		//disponibilidad de productos en el deposito del area correspondiente
		// Nota: Esta es una validacion parcial, solo se presupone que hay stock
		// Esto podria cambiar si por ejemplo se pide el mismo plato muchas veces
		//al realizar el test, daria que hay stock disponible, pero a la hora de
		// producir el plato, quizas me quede sin stock.
		
		respuesta = Gestor_Deposito.getInstancia().verificarStockDeposito(plato, cantidad, emp);
		
		if(respuesta){
			
			Entity_ItemComanda ic = new Entity_ItemComanda();
			ic.setPlato(p);
			ic.setEstado("pendiente");
			ic.setCantidad(cantidad);
			c.addItem(ic);
			
			dao.updateComanda(c);
		}
		
		return respuesta;
		
	}
	
	public void confirmarComanda(DTO_Comanda comanda){
		
	}
	

	public DTO_Comanda buscarComandaId(DTO_Comanda com) {
		Entity_Comanda c = dao.buscarComanda(com.getNumero());
		DTO_Comanda comanda = new DTO_Comanda();
		if(c != null){
			comanda = c.getDTO();
		}
		
		return comanda;
	}

	public void eliminarItemComanda(DTO_ItemComanda com) {
		Entity_ItemComanda item = dao.buscarItemComanda(com.getId());
		item.setPlato(null);
		dao.updateItemComanda(item);
		Entity_ItemComanda item2 = dao.buscarItemComanda(com.getId());
		dao.eliminarItemComanda(item);
		
	}

	public List<DTO_Comanda> comandaPendienteCocina(DTO_Empleado emp) {
		List<Entity_Comanda> comandas = new ArrayList<Entity_Comanda>();
		List<DTO_Comanda> result = new ArrayList<DTO_Comanda>();
		
		Entity_Local s = LocalDAO.getInstancia().buscarSucursalEmpleadoCocina(emp);
		
		List<Entity_Sector> sect = s.getSalon().getSectores();
		
		for(Entity_Sector sec: sect){
			List<Entity_Mesa> mesas = new ArrayList<Entity_Mesa>();
			mesas = sec.getMesas();
			
			for(Entity_Mesa m: mesas){
				Entity_Comanda c = new Entity_Comanda();
				c = m.getComanda();
				
				if(c != null){
					if(c.getEstado().equalsIgnoreCase("pendiente")){
						comandas.add(c); //Agrego las comanda pendientes
					}
				}
			}
 		}
		
		for(Entity_Comanda com: comandas){ // Paso las Comandas
			result.add(com.getDTO());
		}
		
		return result;
	}

	public List<DTO_Comanda> comandaPendienteBarra(DTO_Empleado emp) {
		List<Entity_Comanda> comandas = new ArrayList<Entity_Comanda>();
		List<DTO_Comanda> result = new ArrayList<DTO_Comanda>();
		
		Entity_Local s = LocalDAO.getInstancia().buscarSucursalEmpleadoBarra(emp);
		
		List<Entity_Sector> sect = s.getSalon().getSectores();
		
		for(Entity_Sector sec: sect){
			List<Entity_Mesa> mesas = new ArrayList<Entity_Mesa>();
			mesas = sec.getMesas();
			
			for(Entity_Mesa m: mesas){
				Entity_Comanda c = new Entity_Comanda();
				c = m.getComanda();
				
				if(c != null){
					if(c.getEstado().equalsIgnoreCase("pendiente")){
						comandas.add(c); //Agrego las comanda pendientes
					}
				}
			}
 		}
		
		for(Entity_Comanda com: comandas){ // Paso las Comandas
			result.add(com.getDTO());
		}
		
		return result;
	}

	public List<DTO_Comanda> comandaPendienteCafeteria(DTO_Empleado emp) {
		List<Entity_Comanda> comandas = new ArrayList<Entity_Comanda>();
		List<DTO_Comanda> result = new ArrayList<DTO_Comanda>();
		
		Entity_Local s = LocalDAO.getInstancia().buscarSucursalEmpleadoCafeteria(emp);
		
		List<Entity_Sector> sect = s.getSalon().getSectores();
		
		for(Entity_Sector sec: sect){
			List<Entity_Mesa> mesas = new ArrayList<Entity_Mesa>();
			mesas = sec.getMesas();
			
			for(Entity_Mesa m: mesas){
				Entity_Comanda c = new Entity_Comanda();
				c = m.getComanda();
				
				if(c != null){
					if(c.getEstado().equalsIgnoreCase("pendiente")){
						comandas.add(c); //Agrego las comanda pendientes
					}
				}
			}
 		}
		
		for(Entity_Comanda com: comandas){ // Paso las Comandas
			result.add(com.getDTO());
		}
		
		return result;
	}

	public boolean prepararItemComanda(DTO_Comanda com, DTO_ItemComanda item, DTO_Empleado emp) {
		boolean resp = false;
		boolean stockDisponible = false;
		
		//Calcular que haya elementos suficientes para realizar el plato del itemComanda
		Entity_ItemComanda i = ComandaDAO.getInstancia().buscarItemComanda(item.getId());
		
		DTO_Plato p = i.getPlato().getDTO();
		
		//Verifico el stock del deposito al cual pertenece el empleado
		stockDisponible = Gestor_Deposito.getInstancia().verificarStockDeposito(p, i.getCantidad(), emp);
		
		if(stockDisponible){
			
			//Resto de los lotes la cantidad a utilizar para preparar el plato
			Gestor_Deposito.getInstancia().prepararItemComanda(p, i.getCantidad(), emp);
			
			// No se genera un movimiento con la baja de productos ya que para las reposiciones
			//se utilizara el stock minimo
			
			//Actualizo el estado del ItemComanda
			
			i.setEstado("completa");
			ComandaDAO.getInstancia().updateItemComanda(i);
			
			resp = true;
			return resp;
			
			
		}else{
			
			i.setEstado("cancelada");
			ComandaDAO.getInstancia().updateItemComanda(i);
			
			resp = false;
			return resp;
		}
		
		
	}

}
