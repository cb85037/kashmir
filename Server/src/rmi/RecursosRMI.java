package rmi;

import interfaces.InterfazRemotaAdministracion;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import administracion.Gestor_Barra;
import administracion.Gestor_Caja;
import administracion.Gestor_Comanda;
import administracion.Gestor_Empleado;
import administracion.Gestor_Factura;
import administracion.Gestor_Local;
import administracion.Gestor_Mesa;
import administracion.Gestor_Movimiento;
import administracion.Gestor_Mozo;
import administracion.Gestor_PlanProdSemiElab;
import administracion.Gestor_Plato;
import administracion.Gestor_Producto;
import administracion.Gestor_Receta;
import administracion.Gestor_Tarea;
import dto.DTO_Barra;
import dto.DTO_Cafeteria;
import dto.DTO_Caja;
import dto.DTO_Cocina;
import dto.DTO_Comanda;
import dto.DTO_CompraNoVenta;
import dto.DTO_CompraVenta;
import dto.DTO_DepositoLocal;
import dto.DTO_ElaboradoNoVenta;
import dto.DTO_ElaboradoVenta;
import dto.DTO_Empleado;
import dto.DTO_Encargado;
import dto.DTO_Factura;
import dto.DTO_ItemComanda;
import dto.DTO_Local;
import dto.DTO_Mesa;
import dto.DTO_MesaCompuesta;
import dto.DTO_MesaSimple;
import dto.DTO_Mozo;
import dto.DTO_PlanProdSemiElab;
import dto.DTO_Plato;
import dto.DTO_Producto;
import dto.DTO_Receta;
import dto.DTO_RubroCarta;
import dto.DTO_Salon;
import dto.DTO_Sector;
import dto.DTO_Tarea;
import entity.Entity_Mesa;


public class RecursosRMI extends UnicastRemoteObject implements InterfazRemotaAdministracion {
	
	private static final long serialVersionUID = 1L;

	public RecursosRMI() throws RemoteException {
		
	}

	
	@Override
	public List<DTO_Receta> listarRecetas() throws RemoteException{
		Gestor_Receta rm= Gestor_Receta.getInstancia();
		List<DTO_Receta> recetas = rm.listarRecetas();
		return recetas;
	}

	@Override
	public List<DTO_RubroCarta> listarPlatosRubro(DTO_Empleado emp) throws RemoteException {
		Gestor_Plato pm= Gestor_Plato.getInstancia();
		List<DTO_RubroCarta> platos = pm.listarPlatosRubro(emp);
		return platos;
	}

	@Override
	public List<DTO_Local> listarSucursales() throws RemoteException {
		List<DTO_Local> sucursales = Gestor_Local.getInstancia().listarSucursales();
		
		return sucursales;
	}

	@Override
	public List<DTO_Tarea> listarTareasActivas() throws RemoteException {
		List<DTO_Tarea> tareas = Gestor_Tarea.getInstancia().listarTareasActivas();
		
		return tareas;
	}

	@Override
	public List<DTO_Local> calcularOcupacionSuc(List<DTO_Local> sucursales) throws RemoteException {
		sucursales = Gestor_Local.getInstancia().calcularOcupacionSuc(sucursales);
		
		return sucursales;
	}

	@Override
	public DTO_PlanProdSemiElab getPlanProduccion() throws RemoteException {
		DTO_PlanProdSemiElab plan;
		
		plan = Gestor_PlanProdSemiElab.getInstancia().getPlanProduccion();
		
		return plan;
	}

	@Override
	public List<DTO_Producto> listarProductos() throws RemoteException {
		List<DTO_Producto> productos = Gestor_Producto.getInstancia().listarProductos();
		
		return productos;
	}




	@Override
	public boolean CerrarMesa(DTO_Mesa mesaDto, DTO_Empleado emp) throws RemoteException {
		boolean resp = Gestor_Mesa.getInstancia().cerrarMesaSimpl(mesaDto, emp);
		
		return resp;
		
	}


	@Override
	public DTO_Empleado validarUsuarioAdmin(String usuario, String password)
			throws RemoteException {
		DTO_Empleado e = Gestor_Empleado.getInstancia().validarUsuarioAdmin(usuario,password);
		
		return e;
	}

	@Override
	public boolean altaTarea(DTO_Empleado e2, DTO_Tarea t) throws RemoteException {
		return Gestor_PlanProdSemiElab.getInstancia().altaTarea(e2, t);
		
	}

//Atencion a cliente

	@Override
	public DTO_MesaCompuesta listarMesaCompuestaPorMozo(DTO_Mozo mozo, int cantComensales) throws RemoteException {
		List<DTO_Mesa> dto_Mesas = Gestor_Mesa.getInstancia().armarMesaCompuesta(mozo, cantComensales);
		DTO_MesaCompuesta compuesta = new DTO_MesaCompuesta();
		
		List<DTO_MesaSimple> componentes = new ArrayList<DTO_MesaSimple>();
		int numero = 0;
		for(DTO_Mesa m : dto_Mesas){
			DTO_MesaSimple a = new DTO_MesaSimple();
			if(numero < m.getNumero()){
				compuesta.setNumero(m.getNumero());
				numero = m.getNumero();
			}
			a.setNumero(m.getNumero());
			a.setIdMesa(m.getIdMesa());
			componentes.add(a);
		}
		compuesta.setComponentes(componentes);
		return compuesta;
		//return Gestor_Mesa.getInstancia().armarMesaCompuesta(mozo, cantComensales);
	}

	@Override
	public boolean AbrirMesa(DTO_MesaSimple mesaDto,DTO_Mozo mozoDto) throws RemoteException {
		boolean resp = Gestor_Mesa.getInstancia().abrirMesaSimpl(mesaDto,mozoDto);
		Gestor_Mozo.getInstancia().asociarMesaAMozo(mozoDto, mesaDto);
		
		return resp;
	}
	
	@Override
	public boolean AbrirMesaComp(DTO_MesaCompuesta mesasDto, DTO_Mozo mozoDto)
			throws RemoteException {
		Gestor_Mesa.getInstancia().abrirMesaComp(mesasDto, mozoDto);
		Gestor_Mozo.getInstancia().asociarMesasAMozo(mozoDto, mesasDto);
		
		return true;
	}

	@Override
	public List<DTO_Mesa> listarMesasPorMozo(DTO_Mozo mozo, int cantComensales) throws RemoteException {
		//Devuelvo el conjunto de mesas que van a ser unidas por el mozo!!
		return Gestor_Mozo.getInstancia().listarMesasPorMozo(mozo, cantComensales);
	}
	
	


	@Override
	public List<DTO_Producto> listarProductosElaborados() throws RemoteException {
		List<DTO_Producto> productos = Gestor_Producto.getInstancia().listarProductosElaborados();
		
		return productos;
	}


	@Override
	public DTO_Empleado validarUsuario(String usuario, String password, String tipo)
			throws RemoteException {
		
		DTO_Empleado e = Gestor_Empleado.getInstancia().validarUsuario(usuario,password,tipo);
		return e;
	}

	@Override
	public DTO_DepositoLocal getDSucursalUsuario(String legajo) throws RemoteException {
		
		DTO_DepositoLocal d = Gestor_Local.getInstancia().buscarDSucursalUsuario(legajo);
		
		return d;
	}

	@Override
	public void despacharMovSucursal(String idMovimiento, DTO_Empleado e)
			throws RemoteException {
		
		Gestor_Movimiento.getInstancia().despacharMovSucursal(idMovimiento, e);
		
	}

	@Override
	public List<DTO_Mesa> mesasComandasNoFacturadas(DTO_Mozo mozo)
			throws RemoteException {
		List<DTO_Mesa> mesas = Gestor_Mozo.getInstancia().mesasComandasNoFacturadas(mozo);
		
		return mesas;
	}

	@Override
	public DTO_Comanda buscarComanda(DTO_Comanda com) throws RemoteException {
		DTO_Comanda comanda = Gestor_Comanda.getInstancia().buscarComandaId(com);
		
		return comanda;
	}

	@Override
	public void eliminarItemComanda(DTO_ItemComanda com)
			throws RemoteException {
		Gestor_Comanda.getInstancia().eliminarItemComanda(com);
		
	}


	@Override
	public boolean agregarItemComanda(DTO_Comanda c, DTO_Plato p, int canti, DTO_Empleado emp)
			throws RemoteException {
		
		boolean resp = Gestor_Comanda.getInstancia().adicionarItemAComanda(c, p, canti, emp);
		return resp;
	}

	@Override
	public boolean validarEmpleadoCocina(DTO_Empleado emp) throws RemoteException {
		boolean resp = Gestor_Empleado.getInstancia().validarUsuarioCocina(emp);
		return resp;
	}
	
	@Override
	public boolean validarEmpleadoBarra(DTO_Empleado emp) throws RemoteException {
		boolean resp = Gestor_Empleado.getInstancia().validarUsuarioBarra(emp);
		return resp;
	}
	
	@Override
	public boolean validarEmpleadoCafeteria(DTO_Empleado emp) throws RemoteException {
		boolean resp = Gestor_Empleado.getInstancia().validarUsuarioCafeteria(emp);
		return resp;
	}

	@Override
	public List<DTO_Comanda> comandaPendienteCocina(DTO_Empleado emp)
			throws RemoteException {
		
		List<DTO_Comanda> com = Gestor_Comanda.getInstancia().comandaPendienteCocina(emp);
		return com;
	}

	@Override
	public List<DTO_Comanda> comandaPendienteBarra(DTO_Empleado emp)
			throws RemoteException {
		List<DTO_Comanda> com = Gestor_Comanda.getInstancia().comandaPendienteBarra(emp);
		return com;
	}

	@Override
	public List<DTO_Comanda> comandaPendienteCafeteria(DTO_Empleado emp)
			throws RemoteException {
		List<DTO_Comanda> com = Gestor_Comanda.getInstancia().comandaPendienteCafeteria(emp);
		return com;
	}

	@Override
	public boolean prepararItemComanda(DTO_Comanda com, DTO_ItemComanda item, DTO_Empleado emp)
			throws RemoteException {
		
		boolean resp = Gestor_Comanda.getInstancia().prepararItemComanda(com, item, emp);
		return resp;
	}

	@Override
	public List<DTO_Mesa> mesasAbiertasMozo(DTO_Empleado emp)
			throws RemoteException {
		List<DTO_Mesa> mesas = Gestor_Mozo.getInstancia().listarMesasAbiertasMozo(emp);
		return mesas;
	}

	@Override
	public List<DTO_Mesa> getMesasOcupadas(DTO_Empleado e) throws RemoteException {
		return Gestor_Local.getInstancia().getMesasOcupadas(e);
	}

	@Override
	public DTO_Factura facturarMesa(DTO_Mesa m, DTO_Encargado e) throws RemoteException {
		Entity_Mesa mesa = Gestor_Mesa.getInstancia().buscarMesaNombre(m);		
		DTO_Factura fac =Gestor_Factura.getInstancia().altaFactura(m, e);
		return fac;
	}

	@Override
	public boolean abrirCajaDiaria(DTO_Empleado emp) throws RemoteException {
		boolean resp = false;
		resp = Gestor_Caja.getInstancia().abrirCajaDiaria(emp);
		return resp;
	}

	@Override
	public List<DTO_Producto> listarProductosParaPlanProd()
			throws RemoteException {
		List<DTO_Producto> productos = new ArrayList<DTO_Producto>();
		productos = Gestor_Producto.getInstancia().listarProductosElabNoVenta();
		
		return productos;
	}

	@Override
	public boolean cerrarCajaDiaria(DTO_Empleado emp, float montoCierre)
			throws RemoteException {
		return Gestor_Caja.getInstancia().cerrarCajaDiaria(emp, montoCierre);
	}

	@Override
	public boolean generarLiquidaciones(DTO_Encargado emp)
			throws RemoteException {
		return Gestor_Factura.getInstancia().generarLiquidaciones(emp);
	}
	
	
	@Override
	public void altaSucursal(DTO_Local s) throws RemoteException {
		Gestor_Local.getInstancia().altaSucursal(s);
	}


	@Override
	public void altaProductoENV(DTO_ElaboradoNoVenta p) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void altaProductoEV(DTO_ElaboradoVenta p) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void altaProductoCV(DTO_CompraVenta p) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void altaProductoCNV(DTO_CompraNoVenta p) throws RemoteException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void altaSalon(DTO_Local sucursal, DTO_Salon salon)
			throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void altaSector(DTO_Local sucursal, DTO_Salon salon, DTO_Sector sector) throws RemoteException {
		sucursal.setSalon(salon);
//		salon.setSectores(sector);
	}

	@Override
	public void altaMesa(DTO_Local sucursal, DTO_Salon salon, DTO_Sector sector, DTO_Mesa mesa) throws RemoteException {
		// TODO Auto-generated method stub
	}

	@Override
	public void altaBarra(DTO_Local s, DTO_Barra b) throws RemoteException {
		if(s != null && b != null){
			Gestor_Barra.getInstancia().altaBarra(s, b);
		}else{
			return;
		}
		
	}

	@Override
	public void altaCafeteria(DTO_Local s, DTO_Cafeteria c)
			throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void altaCocina(DTO_Local s, DTO_Cocina c) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void altaCaja(DTO_Local s, DTO_Caja c) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void altaPlanProduccion(DTO_PlanProdSemiElab p) throws RemoteException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public List<DTO_RubroCarta> listarRubros() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void AsignarMozoAMesa(DTO_Mesa mesaDto, DTO_Mozo mozoDto)
			throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void AsignarComandaAMesa(DTO_Mesa mesaDTO, DTO_Comanda comandaDTO)
			throws RemoteException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public List<DTO_Mesa> listarMesasPorSector(DTO_Sector sector,
			int cantComensales) throws RemoteException {
				return null;
		//return MesaManager.getInstancia().listarMesasLibresPorSector(sector, cantComensales);
	}
	

	@Override
	public void agregarItemAComanda(DTO_Mesa mesaDto, DTO_Plato platoDto, int cantidad) throws RemoteException {
		//ComandaManager.getInstancia().adicionarItemAComanda(mesaDto.getComanda(), platoDto, cantidad);
	}

	@Override
	public void confirmarComanda(DTO_Mesa mesaDto) throws RemoteException {
		//ComandaManager.getInstancia().confirmarComanda(mesaDto.getComanda());
	}
	

	@Override
	public List<DTO_Plato> listarPlatos(DTO_Empleado emp) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DTO_Plato> listarPlatos() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
