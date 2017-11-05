package administracion;

import java.rmi.RemoteException;

import dao.EmpleadoDAO;
import dao.LocalDAO;
import dao.PlanProdSemiElabDAO;
import dao.ProductoDAO;
import dto.DTO_Empleado;
import dto.DTO_PlanProdSemiElab;
import dto.DTO_Tarea;
import entity.Entity_ElaboracionNoVenta;
import entity.Entity_Empleado;
import entity.Entity_Local;
import entity.Entity_PlanProdSemiElab;
import entity.Entity_Tarea;

public class Gestor_PlanProdSemiElab {

	private static Gestor_PlanProdSemiElab instancia;
	private PlanProdSemiElabDAO dao = PlanProdSemiElabDAO.getInstancia();
	
	private Gestor_PlanProdSemiElab() {
		
	}
	
	public static Gestor_PlanProdSemiElab getInstancia(){
		if(instancia == null)
			instancia = new Gestor_PlanProdSemiElab();
		return instancia;
	}

	public DTO_PlanProdSemiElab getPlanProduccion() throws RemoteException {
		Entity_PlanProdSemiElab p = dao.getInstancia().getPlanProduccion();
		
		if(p != null){
			return p.getDTO();
		}else{
			throw new RemoteException("No existe un Plan de Produccion Activo");
		}
	}
	
	public boolean altaTarea(DTO_Empleado e2, DTO_Tarea t) throws RemoteException {
		
		
		Entity_Local s = LocalDAO.getInstancia().buscarSucursalNombre(t.getSucursal().getNombre());
		Entity_ElaboracionNoVenta p = (Entity_ElaboracionNoVenta)ProductoDAO.getInstancia().buscarProductoCodigo(t.getProducto().getCodigo());
		
		//AdministracionCentral adm = AdministracionCentralDAO.getInstancia().getAdministracionCentral();
		Entity_Empleado emp = EmpleadoDAO.getInstancia().getEmpleadoLegajo(e2.getLegajo());
		boolean verif = Gestor_AdministracionCentral.getInstancia().verificarStockDepositoCentral(p, t.getCantidad());
		if(verif == true){ //Hay stock disponible para cumplir la tarea
			
			//Recupero los lotes que sera utilizados para la tarea.
			//List<Lote> lotes = DepositoManager.getInstancia().lotesProductosTarea(p);
			
			//Creo el movimiento de mercaderia que va al deposito del area correspondiente
			Gestor_Movimiento.getInstancia().altaMovimiento(s,emp,p,t.getCantidad());
			
			//Recupero la sucursal actualizada
			s = LocalDAO.getInstancia().buscarSucursalNombre(t.getSucursal().getNombre());
			
			//Creo la tarea
			Entity_Tarea tarea = new Entity_Tarea();
			tarea.setSucursal(s);
			tarea.setProducto(p);
			tarea.setCantidad(t.getCantidad());
			tarea.setEstado(t.getEstado());
			tarea.setTiempoRequerido(t.getTiempoRequerido());
			
			//Actualizo el plan de produccion
			Entity_PlanProdSemiElab plan = dao.getPlanProduccion();
			plan.getTareas().add(tarea);
			
			//Actualizo todo
			dao.update(plan);
			
		}else{
			
			throw new RemoteException("No hay stock suficiente para realizar dicha tarea");
			
		}
		
		return verif;
		
		
		
		
	}
}
