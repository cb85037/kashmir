package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import dto.*;

import java.util.*;

public interface InterfazRemotaAdministracion extends Remote{

	public static final String url = "localhost/RecursoRMI";

	public void altaSucursal(DTO_Local s) throws RemoteException ;
	
	public void altaProductoENV(DTO_ElaboradoNoVenta p) throws RemoteException;
	
	public void altaProductoEV(DTO_ElaboradoVenta p) throws RemoteException;
	
	public void altaProductoCV(DTO_CompraVenta p) throws RemoteException;
	
	public void altaProductoCNV(DTO_CompraNoVenta p) throws RemoteException;
	
	public List<DTO_Receta> listarRecetas() throws RemoteException;
	
	public List<DTO_Plato> listarPlatos(DTO_Empleado emp) throws RemoteException;
	
	public List<DTO_RubroCarta> listarRubros() throws RemoteException;
		
	public void altaSalon(DTO_Local sucursal, DTO_Salon salon) throws RemoteException;

	public void altaSector(DTO_Local sucursal, DTO_Salon salon, DTO_Sector sector) throws RemoteException;
	
	public void altaMesa(DTO_Local sucursal, DTO_Salon salon, DTO_Sector sector, DTO_Mesa mesa) throws RemoteException;

	public List<DTO_Local> listarSucursales() throws RemoteException;

	public void altaBarra(DTO_Local s, DTO_Barra b) throws RemoteException;
	
	public void altaCafeteria(DTO_Local s, DTO_Cafeteria c) throws RemoteException;
	
	public void altaCocina(DTO_Local s, DTO_Cocina c) throws RemoteException;
	
	public void altaCaja(DTO_Local s, DTO_Caja c) throws RemoteException;
	
	public void altaPlanProduccion(DTO_PlanProdSemiElab p) throws RemoteException;

	public List<DTO_Tarea> listarTareasActivas() throws RemoteException;

	public List<DTO_Local> calcularOcupacionSuc(List<DTO_Local> sucursales) throws RemoteException;

	public DTO_PlanProdSemiElab getPlanProduccion() throws RemoteException;

	public List<DTO_Producto> listarProductos() throws RemoteException;

	public boolean altaTarea(DTO_Empleado e2, DTO_Tarea t) throws RemoteException;

	public DTO_Empleado validarUsuarioAdmin(String usuario, String password) throws RemoteException;

//Atencion a Cliente
	public List<DTO_Mesa> listarMesasPorSector(DTO_Sector sector, int cantComensales) throws RemoteException;
	
	public DTO_MesaCompuesta listarMesaCompuestaPorMozo(DTO_Mozo mozo, int cantComensales) throws RemoteException;
	//alta mesa
	public boolean AbrirMesa(DTO_MesaSimple mesaDto,DTO_Mozo mozoDto) throws RemoteException;
	//alta mesa compuesta
	public boolean AbrirMesaComp(DTO_MesaCompuesta mesasDto,DTO_Mozo mozoDto) throws RemoteException;
	
	public void AsignarMozoAMesa(DTO_Mesa mesaDto, DTO_Mozo mozoDto) throws RemoteException;
	
	public void AsignarComandaAMesa(DTO_Mesa mesaDTO, DTO_Comanda comandaDTO) throws RemoteException;
	
	public boolean CerrarMesa(DTO_Mesa mesaDto, DTO_Empleado emp) throws RemoteException;

	public List<DTO_Mesa> listarMesasPorMozo (DTO_Mozo mozo, int cantComensales) throws RemoteException;

	public List<DTO_Producto> listarProductosElaborados() throws RemoteException;

	public void agregarItemAComanda(DTO_Mesa mesaDto, DTO_Plato platoDto, int cantidad) throws RemoteException;

	public void confirmarComanda(DTO_Mesa mesaDto) throws RemoteException;

	public DTO_Empleado validarUsuario(String usuario, String password, String tipo) throws RemoteException;

	public DTO_DepositoLocal getDSucursalUsuario(String legajo) throws RemoteException;

	public void despacharMovSucursal(String idMovimiento, DTO_Empleado e2) throws RemoteException;

	public List<DTO_Mesa> mesasComandasNoFacturadas(DTO_Mozo mozo) throws RemoteException;

	public DTO_Comanda buscarComanda(DTO_Comanda com) throws RemoteException;

	public void eliminarItemComanda(DTO_ItemComanda com) throws RemoteException;

	List<DTO_RubroCarta> listarPlatosRubro(DTO_Empleado emp) throws RemoteException;

	public List<DTO_Plato> listarPlatos() throws RemoteException;

	public boolean agregarItemComanda(DTO_Comanda c, DTO_Plato p, int canti, DTO_Empleado emp) throws RemoteException;

	public boolean validarEmpleadoCocina(DTO_Empleado emp) throws RemoteException;
	
	public boolean validarEmpleadoBarra(DTO_Empleado emp) throws RemoteException;
	
	public boolean validarEmpleadoCafeteria(DTO_Empleado emp) throws RemoteException;

	public List<DTO_Comanda> comandaPendienteCocina(DTO_Empleado emp) throws RemoteException;

	public List<DTO_Comanda> comandaPendienteBarra(DTO_Empleado emp) throws RemoteException;

	public List<DTO_Comanda> comandaPendienteCafeteria(DTO_Empleado emp) throws RemoteException;

	public boolean prepararItemComanda(DTO_Comanda com, DTO_ItemComanda item, DTO_Empleado emp) throws RemoteException;

	public List<DTO_Mesa> mesasAbiertasMozo(DTO_Empleado emp) throws RemoteException;

	public List<DTO_Mesa> getMesasOcupadas(DTO_Empleado e) throws RemoteException;

	public boolean abrirCajaDiaria(DTO_Empleado emp) throws RemoteException;

	public DTO_Factura facturarMesa(DTO_Mesa m, DTO_Encargado e) throws RemoteException;

	public List<DTO_Producto> listarProductosParaPlanProd() throws RemoteException;
	
	public boolean cerrarCajaDiaria(DTO_Empleado emp, float montoCierre) throws RemoteException;
	
	public boolean generarLiquidaciones(DTO_Encargado emp) throws RemoteException;
}