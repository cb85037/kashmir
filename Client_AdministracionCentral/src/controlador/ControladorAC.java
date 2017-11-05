package controlador;

import interfaces.InterfazRemotaAdministracion;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import dto.DTO_Barra;
import dto.DTO_CompraNoVenta;
import dto.DTO_CompraVenta;
import dto.DTO_ElaboradoNoVenta;
import dto.DTO_ElaboradoVenta;
import dto.DTO_Empleado;
import dto.DTO_Local;
import dto.DTO_PlanProdSemiElab;
import dto.DTO_Plato;
import dto.DTO_Producto;
import dto.DTO_Receta;
import dto.DTO_RubroCarta;
import dto.DTO_Tarea;

public class ControladorAC {

	// Interfaz Remota que esta en el Repositorio
	InterfazRemotaAdministracion objetoRemoto;
	private static ControladorAC instancia;

	// Implemento Sigleton para que sea mas facil hacer las llamadas desde las
	// vistas
	private ControladorAC() {
		getStub();
	}

	public static ControladorAC getInstancia() {
		if (instancia == null)
			instancia = new ControladorAC();
		return instancia;
	}

	public boolean getStub() {

		try {
			// Establezco el lugar a donde el cliente va a hacer el lookup para
			// conectarse con el server
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

	// Aca van todos los metodos llamando los metodos de la interface

	public void altaSucursal(String nombre) {

		DTO_Local s = new DTO_Local();
		s.setNombre(nombre);

		try {
			objetoRemoto.altaSucursal(s);
		} catch (RemoteException e) {
			// Mensaje indicando la excepcion que se tomo del SucursalManager.
			JOptionPane.showMessageDialog(null, e.getCause().getMessage());
		}

	}

	// MM
	public void altaProductoENV(String nombre, String caducidad, int precio) {
		DTO_ElaboradoNoVenta p = new DTO_ElaboradoNoVenta();
		p.setNombre(nombre);
		p.setCaducidad(caducidad);
		p.setPrecio(precio);

		try {
			objetoRemoto.altaProductoENV(p);
		} catch (RemoteException e) {
			// Mensaje indicando la excepcion que se tomo del ProductoManager.
			JOptionPane.showMessageDialog(null, e.getCause().getMessage());
		}

	}

	public void altaProductoEV(String nombre, String caducidad, int precio) {
		DTO_ElaboradoVenta p = new DTO_ElaboradoVenta();
		p.setNombre(nombre);
		p.setCaducidad(caducidad);
		p.setPrecio(precio);
		try {
			objetoRemoto.altaProductoEV(p);
		} catch (RemoteException e) {
			// Mensaje indicando la excepcion que se tomo del SucursalManager.
			JOptionPane.showMessageDialog(null, e.getCause().getMessage());
		}
	}

	public void altaProductoCNV(String nombre, String caducidad, int precio) {
		DTO_CompraNoVenta p = new DTO_CompraNoVenta();
		p.setNombre(nombre);
		p.setCaducidad(caducidad);
		p.setPrecio(precio);
		try {
			objetoRemoto.altaProductoCNV(p);
		} catch (RemoteException e) {
			// Mensaje indicando la excepcion que se tomo del SucursalManager.
			JOptionPane.showMessageDialog(null, e.getCause().getMessage());
		}

	}

	public void altaProductoCV(String nombre, String caducidad, int precio) {
		DTO_CompraVenta p = new DTO_CompraVenta();
		p.setNombre(nombre);
		p.setCaducidad(caducidad);
		p.setPrecio(precio);

		try {
			objetoRemoto.altaProductoCV(p);
		} catch (RemoteException e) {
			// Mensaje indicando la excepcion que se tomo del ProductoManager.
			JOptionPane.showMessageDialog(null, e.getCause().getMessage());
		}
	}

	public List<DTO_Receta> listarRecetas() {
		List<DTO_Receta> lista = null;
		try {
			lista = objetoRemoto.listarRecetas();
		} catch (RemoteException e) {
			JOptionPane.showMessageDialog(null, e.getCause().getMessage());
		}
		return lista;
	}
	public List<DTO_Local> listarSucursales() {
		List<DTO_Local> lista = null;
		try {
			lista = objetoRemoto.listarSucursales();
		} catch (RemoteException e) {
			JOptionPane.showMessageDialog(null, e.getCause().getMessage());
		}
		return lista;
	}
	public List<DTO_RubroCarta> listarRubros() {
		List<DTO_RubroCarta> lista = null;
		try {
			lista = objetoRemoto.listarRubros();
		} catch (RemoteException e) {
			JOptionPane.showMessageDialog(null, e.getCause().getMessage());
		}
		return lista;
	}
	public List<DTO_Plato> listarPlatos() {
		List<DTO_Plato> lista = null;
		try {
			lista = objetoRemoto.listarPlatos();
		} catch (RemoteException e) {
			JOptionPane.showMessageDialog(null, e.getCause().getMessage());
		}
		return lista;
	}	
	// Fin MM
	
	public void altaBarra(DTO_Local s, DTO_Barra b){
		try {
			objetoRemoto.altaBarra(s,b);
		} catch (RemoteException e) {
			JOptionPane.showMessageDialog(null, e.getCause().getMessage());
		}
	}

	public List<DTO_Tarea> listarTareasActivas() {
		List<DTO_Tarea> lista = null;
		try {
			lista = objetoRemoto.listarTareasActivas();
		} catch (RemoteException e) {
			JOptionPane.showMessageDialog(null, e.getCause().getMessage());
		}
		return lista;
	}

	public List<DTO_Local> calcularOcupacionSucursales(List<DTO_Local> sucursales) {
		
		try {
			sucursales = objetoRemoto.calcularOcupacionSuc(sucursales);
		} catch (RemoteException e) {
			JOptionPane.showMessageDialog(null, e.getCause().getMessage());
		}
		return sucursales;
	}

	public int getNumeroPlanProduccion() {
		DTO_PlanProdSemiElab plan = null;
		try {
			plan = objetoRemoto.getPlanProduccion();
		} catch (RemoteException e) {
			JOptionPane.showMessageDialog(null, e.getCause().getMessage());
		}
		
		if(plan != null){
			return plan.getIdPlanProduccion();
		}else{
			return 0;
		}
		
	}

	public Date getFechaPP() {
		DTO_PlanProdSemiElab plan = null;
		try {
			plan = objetoRemoto.getPlanProduccion();
		} catch (RemoteException e) {
			JOptionPane.showMessageDialog(null, e.getCause().getMessage());
		}
		
		if(plan != null){
			return plan.getFecha();
		}else{
			return new Date();
		}
		
	}

	public List<DTO_Producto> listarProductosElaborados() {
		List<DTO_Producto> lista = null;
		try {
			lista = objetoRemoto.listarProductosElaborados();
		} catch (RemoteException e) {
			JOptionPane.showMessageDialog(null, e.getCause().getMessage());
		}
		return lista;
	}

	public boolean altaTarea(DTO_Empleado e2, DTO_Local suc, DTO_Producto prod, int cantidad,
			String estado, int horasRequeridas) {
		
		boolean resp = false;
		
		DTO_Tarea t = new DTO_Tarea();
		t.setCantidad(cantidad);
		t.setEstado(estado);
		t.setTiempoRequerido(horasRequeridas);
		t.setProducto(prod);
		t.setSucursal(suc);

		
		try{
			resp = objetoRemoto.altaTarea(e2,t);
			
		}catch(RemoteException e){
			JOptionPane.showMessageDialog(null, e.getCause().getMessage());
		}
		
		return resp;
		
	}

	public DTO_Empleado validarUsuarioAdmin(String usuario, String password) {
		DTO_Empleado emp = null;
		
		try {
			
			emp = objetoRemoto.validarUsuarioAdmin(usuario,password);
			
		} catch (RemoteException e) {
			JOptionPane.showMessageDialog(null, e.getCause().getMessage());
		}
		return emp;
	}
	
	public List<DTO_Producto> listarProductosElabNoVenta() {
		List<DTO_Producto> lista = new ArrayList<DTO_Producto>();
		try {
			lista = objetoRemoto.listarProductosParaPlanProd();
		} catch (RemoteException e) {
			JOptionPane.showMessageDialog(null, e.getCause().getMessage());
		}
		return lista;
	}

	
}
