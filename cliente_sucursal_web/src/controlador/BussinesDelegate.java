package controlador;

import interfaces.InterfazRemotaAdministracion;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import dto.DTO_Encargado;
import dto.DTO_Comanda;
import dto.DTO_DepositoLocal;
import dto.DTO_Empleado;
import dto.DTO_Factura;
import dto.DTO_ItemComanda;
import dto.DTO_Mesa;
import dto.DTO_MesaCompuesta;
import dto.DTO_MesaSimple;
import dto.DTO_Mozo;
import dto.DTO_Plato;
import dto.DTO_RubroCarta;
import dto.DTO_Sector;
import dto.DTO_Local;


public class BussinesDelegate {
	InterfazRemotaAdministracion objetoRemoto;
	private static BussinesDelegate instancia;

	private BussinesDelegate() {
		getStub();
	}

	public static BussinesDelegate getInstancia() {
		if (instancia == null)
			instancia = new BussinesDelegate();
		return instancia;
	}

	public boolean getStub() {

		try {
			objetoRemoto = (InterfazRemotaAdministracion) Naming
					.lookup(InterfazRemotaAdministracion.url);
			System.out.println("Obtenido el servicio de la Instancia Remota "
					+ InterfazRemotaAdministracion.url);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public DTO_Empleado validarUsuario(String usuario, String password, String tipo) throws RemoteException {
		
		if(tipo.equalsIgnoreCase("Mozo")){
			try{
				DTO_Empleado m =	objetoRemoto.validarUsuario(usuario, password, tipo);
				return m;
			}catch(Exception e){
				
			}
			
		}else if(tipo.equalsIgnoreCase("Produccion")){
			try{
				DTO_Empleado emp = objetoRemoto.validarUsuario(usuario, password, tipo);
				return emp;
			}catch(Exception e){
				
			}
			
		}else if(tipo.equalsIgnoreCase("Cajero")){
			try{
				DTO_Empleado c = objetoRemoto.validarUsuario(usuario, password, tipo);
				return c;
			}catch(Exception e){
				
			}
		}
		
		return null;
		
		
	}
	
	

	public DTO_DepositoLocal getSucursalUsuario(String legajo) {
		DTO_DepositoLocal dep = null;
		try {
			dep = objetoRemoto.getDSucursalUsuario(legajo);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return dep;
	}

	public void despacharMovSucursal(String idMovimiento, DTO_Empleado e2) {
		try {
			objetoRemoto.despacharMovSucursal(idMovimiento, e2);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

	public List<DTO_Mesa> mesasSimplesDisponibles(String legajo, int cantComensales) {
		List<DTO_Mesa> mesas = new ArrayList<DTO_Mesa>();
		DTO_Mozo mozo = new DTO_Mozo();
		mozo.setLegajo(legajo);
		
		try {
			mesas = objetoRemoto.listarMesasPorMozo(mozo, cantComensales);
		} catch (RemoteException e) {
			
			e.printStackTrace();
		} 
		return mesas;
	}
	
	public DTO_MesaCompuesta mesasCompuestasDisponibles(String legajo, int cantComensales) {
		DTO_MesaCompuesta mesa = new DTO_MesaCompuesta();
		DTO_Mozo mozo = new DTO_Mozo();
		mozo.setLegajo(legajo);
		
		try {
			mesa =  objetoRemoto.listarMesaCompuestaPorMozo(mozo, cantComensales);
		} catch (RemoteException e) {
			
			e.printStackTrace();
		} 
		return mesa;
	}	

	public boolean abrirMesaSimple(int idMesa, String legajo) {
		boolean respuesta = false;
		
		DTO_Mozo mozo = new DTO_Mozo();
		mozo.setLegajo(legajo);
		
		DTO_MesaSimple mesa = new DTO_MesaSimple();
		mesa.setIdMesa(idMesa);
		
		try {
			respuesta = objetoRemoto.AbrirMesa(mesa, mozo);
		} catch (RemoteException e) {
			
			e.printStackTrace();
		} 
		return respuesta;
	}
	
	public boolean abrirMesaCompuesta(DTO_MesaCompuesta mesas, String legajo) {
		boolean respuesta = false;
		
		DTO_Mozo mozo = new DTO_Mozo();
		mozo.setLegajo(legajo);
		
		try {
			respuesta = objetoRemoto.AbrirMesaComp(mesas, mozo);
		} catch (RemoteException e) {
			
			e.printStackTrace();
		} 
		return respuesta;
	}
	


	public List<DTO_Mesa> mesasComandasNoFacturadas(String legajo) {
		List<DTO_Mesa> mesas = new ArrayList<DTO_Mesa>();
		DTO_Mozo mozo = new DTO_Mozo();
		mozo.setLegajo(legajo);
		
		try {
			mesas = objetoRemoto.mesasComandasNoFacturadas(mozo);
		} catch (RemoteException e) {
			
			e.printStackTrace();
		} 
		return mesas;
	}

	public DTO_Comanda getComanda(int idComanda) {
		DTO_Comanda com = new DTO_Comanda();
		com.setNumero(idComanda);
		
		try{
			com = objetoRemoto.buscarComanda(com);
		}catch (RemoteException e){
			e.printStackTrace();
		}
		return com;
	}

	public void eliminarItemComanda(int idIitem) {
		DTO_ItemComanda com = new DTO_ItemComanda();
		com.setId(idIitem);
		
		try{
			objetoRemoto.eliminarItemComanda(com);
		}catch (RemoteException e){
			e.printStackTrace();
		}
		
		
	}

	public List<DTO_RubroCarta> getPlatos(DTO_Empleado emp) {
		List<DTO_RubroCarta> rubros = new ArrayList<DTO_RubroCarta>();
		
		try{
			rubros = objetoRemoto.listarPlatosRubro(emp);
		}catch (RemoteException e){
			e.printStackTrace();
		}
		
		return rubros;
	}

	public boolean agregarItemComanda(int idComanda, int canti, int idPlato, String legajo) {
		boolean resp = false;
		DTO_Comanda c = new DTO_Comanda();
		c.setNumero(idComanda);
		
		DTO_Plato p = new DTO_Plato();
		p.setCodigo(idPlato);
		
		DTO_Empleado emp = new DTO_Empleado();
		emp.setLegajo(legajo);
		
		try{
			resp = objetoRemoto.agregarItemComanda(c,p,canti,emp);
			
		}catch (RemoteException e){
			e.printStackTrace();
		}
		
		return resp;
		
	}

	public boolean validarEmpleadoCocina(DTO_Empleado emp) {
		boolean resp = false;
		try{
			resp = objetoRemoto.validarEmpleadoCocina(emp);
		}catch (RemoteException e){
			e.printStackTrace();
		}
		return resp;
	}
	
	public boolean validarEmpleadoBarra(DTO_Empleado emp) {
		boolean resp = false;
		try{
			resp = objetoRemoto.validarEmpleadoBarra(emp);
		}catch (RemoteException e){
			e.printStackTrace();
		}
		return resp;
	}
	
	public boolean validarEmpleadoCafeteria(DTO_Empleado emp) {
		boolean resp = false;
		try{
			resp = objetoRemoto.validarEmpleadoCafeteria(emp);
		}catch (RemoteException e){
			e.printStackTrace();
		}
		return resp;
	}

	public List<DTO_Comanda> comandaPendienteCocina(DTO_Empleado emp) {
		List<DTO_Comanda> comandas = new ArrayList<DTO_Comanda>();
		
		try{
			comandas = objetoRemoto.comandaPendienteCocina(emp);
		}catch (RemoteException e){
			e.printStackTrace();
		}
		
		return comandas;
	}

	public List<DTO_Comanda> comandaPendienteBarra(DTO_Empleado emp) {
		List<DTO_Comanda> comandas = new ArrayList<DTO_Comanda>();
		
		try{
			comandas = objetoRemoto.comandaPendienteBarra(emp);
		}catch (RemoteException e){
			e.printStackTrace();
		}
		
		return comandas;
	}

	public List<DTO_Comanda> comandaPendienteCafeteria(DTO_Empleado emp) {
		List<DTO_Comanda> comandas = new ArrayList<DTO_Comanda>();
		
		try{
			comandas = objetoRemoto.comandaPendienteCafeteria(emp);
		}catch (RemoteException e){
			e.printStackTrace();
		}
		
		return comandas;
	}

	public boolean prepararItemComanda(int idComanda, int idItem, DTO_Empleado emp) {
		boolean resp = false;
		
		DTO_Comanda com = new DTO_Comanda();
		com.setNumero(idComanda);
		
		DTO_ItemComanda item = new DTO_ItemComanda();
		item.setId(idItem);
		
		try{
			resp = objetoRemoto.prepararItemComanda(com,item,emp);
			
		}catch (RemoteException e){
			e.printStackTrace();
		}
		
		return resp;
	}

	public List<DTO_Mesa> mesasAbiertasMozo(DTO_Empleado emp) {
		List<DTO_Mesa> mesas = new ArrayList<DTO_Mesa>();
		
		try {
			mesas = objetoRemoto.mesasAbiertasMozo(emp);
		} catch (RemoteException e) {
			
			e.printStackTrace();
		} 
		return mesas;
	}


	public boolean cerrarMesaSimple(int idMesa, String legajo) {
		
		DTO_Empleado emp = new DTO_Empleado();
		emp.setLegajo(legajo);
		
		DTO_Mesa m = new DTO_Mesa();
		m.setIdMesa(idMesa);
		
		boolean resp = false;
		
		try {
			resp = objetoRemoto.CerrarMesa(m, emp);
		} catch (RemoteException e) {
			
			e.printStackTrace();
		} 
		return resp;
	}


	public List<DTO_Mesa> getMesasOcupadas(String legajo) {
		DTO_Empleado e = new DTO_Empleado();
		e.setLegajo(legajo);
		
		
		List<DTO_Mesa> mesas=null;
		try {
			mesas=objetoRemoto.getMesasOcupadas(e);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return mesas;
		
	}

	public DTO_Factura facturarMesa(int idMesa, String Legajo) {
		DTO_Mesa m=new DTO_MesaSimple();
		m.setIdMesa(idMesa);
		DTO_Encargado caj=new DTO_Encargado();
		caj.setLegajo(Legajo);
		
		DTO_Factura f= new DTO_Factura();
		try {
			f=objetoRemoto.facturarMesa(m, caj);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return f;
		
	}

	public boolean abrirCajaDiaria(DTO_Empleado emp) {
	
		boolean resp = false;
		
		try {
			resp = objetoRemoto.abrirCajaDiaria(emp);
		} catch (RemoteException e) {
			
			e.printStackTrace();
		} 
		return resp;
	}
	
	public boolean generarLiquidaciones(DTO_Encargado emp) {
		
		boolean resp = false;
		
		try {
			resp = objetoRemoto.generarLiquidaciones(emp);
		} catch (RemoteException e) {
			
			e.printStackTrace();
		} 
		return resp;
	}
	
	public boolean cerrarCajaDiaria(DTO_Empleado emp, float montoCierre){
		boolean resp = false;
		
		try {
			resp = objetoRemoto.cerrarCajaDiaria(emp, montoCierre);
		} catch (RemoteException e) {
			
			e.printStackTrace();
		} 
		return resp;
	}
	
	
	//ojo a medio hacer!!!!!!!!!!MM
//	public boolean actualizarFactura(FacturaDTO fac){
//		boolean resp = false;
//		/*
//		try {
//			resp = objetoRemoto.actualizarFactura(fac);
//		} catch (RemoteException e) {
//			
//			e.printStackTrace();
//		} */
//		return resp;
	//}

	
	
}
