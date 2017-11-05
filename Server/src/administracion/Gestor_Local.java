package administracion;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import dao.LocalDAO;
import dao.MesaDAO;
import dto.DTO_DepositoLocal;
import dto.DTO_Empleado;
import dto.DTO_Mesa;
import dto.DTO_Local;
import dto.DTO_MesaCompuesta;
import dto.DTO_Tarea;
import entity.Entity_DepositoLocal;
import entity.Entity_Mesa;
import entity.Entity_MesaCompuesta;
import entity.Entity_MesaSimple;
import entity.Entity_Sector;
import entity.Entity_Local;

public class Gestor_Local {

	private LocalDAO dao = LocalDAO.getInstancia();
	private static Gestor_Local instancia;
	
	private Gestor_Local(){
	}
	
	public static Gestor_Local getInstancia(){
		if(instancia == null)
			instancia = new Gestor_Local();
		return instancia;
	}
	
	public void altaSucursal(DTO_Local sucursal) throws RemoteException{
		
		Entity_Local s = dao.buscarSucursalNombre(sucursal.getNombre());
		
		if(s != null){
			throw new RemoteException("La Sucursal ya existe"); //Una exception nuestra
		}
		else{			
			s = new Entity_Local(sucursal.getNombre());
			dao.altaSucursal(s);
		}
	}

	public Entity_Local buscarSucursalNombre(DTO_Local s) throws RemoteException {
		if(s != null){
			return dao.buscarSucursalNombre(s.getNombre());
		}else{
			throw new RemoteException("El nombre no es valido");
		}
	}

	public void merge(Entity_Local sucursal) {
		if(sucursal != null)
			dao.merge(sucursal);
		
	}

	
	public List<DTO_Local> listarSucursales() {
		List<Entity_Local> lista = dao.listarSucursales();
		List<DTO_Local> result = new ArrayList<DTO_Local>();
		
		for(Entity_Local s: lista){
			result.add(s.getDTO());
		}
		return result;
		
	}
	
	public void actualizarHsProduccion(Entity_Local s){
		if(s != null)
			dao.actualizarHsProduccion(s);
	}

	public List<DTO_Local> calcularOcupacionSuc(List<DTO_Local> sucursales) {
		
		for(DTO_Local s: sucursales){
			float hsDisponibles = s.getHsProduccion();
			//Me traigo todas las tareas de cada una de las sucursales
			List<DTO_Tarea> tareas = Gestor_Tarea.getInstancia().listarTareasDeSucursal(s.getNombre());
			
			if(tareas != null){
			//Por cada una de las tareas actualizo las horas disponibles de produccion
				for(DTO_Tarea t: tareas){
					hsDisponibles = hsDisponibles - t.getTiempoRequerido();
				}
				
				s.setHsDisponiblesProduccion(hsDisponibles);
				
				Entity_Local suc = new Entity_Local();
				suc.setHsDisponiblesProduccion(hsDisponibles);
				suc.setNombre(s.getNombre());
				
				actualizarHsProduccion(suc);
			}
		}
		
		return sucursales;
		
	}

	public DTO_DepositoLocal buscarDSucursalUsuario(String legajo) {
		
		Entity_DepositoLocal d = dao.buscarDSucursalUsuario(legajo);
		DTO_DepositoLocal result = new DTO_DepositoLocal();
		
		if(d != null){
			result = d.toDTO();
		}
		
		return result;
	}

	public List<DTO_Mesa> getMesasOcupadas(DTO_Empleado e) {
		Entity_Local suc = dao.buscarSucursalCajero(e);
		List<DTO_Mesa> mesasVista = new ArrayList<DTO_Mesa>();
		//Ya obtengo las mesas que estan ocupadas
		List<Entity_Mesa> mesasFinal = dao.buscarMesasSucursal(suc);
		List<Entity_Mesa> mesasFinalaux = new ArrayList<Entity_Mesa>(); 
		List<Entity_MesaCompuesta> compuestas = MesaDAO.getInstancia().listarMesasCompuestas();
		
		for(Entity_MesaCompuesta mc : compuestas){
			if(mc.getEstado().equals("ocupada") && mesasFinal.contains(mc.getComponentes().get(0))){
				mesasVista.add(mc.getDTO());
				mesasFinalaux.addAll(mc.getComponentes());
			}
		}
		
		mesasFinal.removeAll(mesasFinalaux);
		
		for(Entity_Mesa m: mesasFinal){
			mesasVista.add(m.getDTO());
		}
	
		return mesasVista;
	}

	
}
