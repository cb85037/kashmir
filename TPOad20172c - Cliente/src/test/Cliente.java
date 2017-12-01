package ClienteSwing;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import Negocio.ItemAReponer;

import repositorio.*;


public class Cliente {
	TDAInterface objetoRemoto;
	static Cliente instancia;
	static UsuarioDTO usuarioLogueado;
	
	public static Cliente getInstancia() throws RemoteException{
		if (instancia == null){
			instancia = new Cliente();
		}
		return instancia;
	}
	
	public static UsuarioDTO getUsuarioLogueado() {
		return usuarioLogueado;
	}

	public Cliente() {
		objetoRemoto = getStub();
	}

	private TDAInterface getStub() {
    	
    	try {
    		objetoRemoto = (TDAInterface)Naming.lookup ("//localhost/TPOSM");
			return objetoRemoto;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
    }
	
	public String AsignarMozo(String mozo, String sector, int restaurante){
		String error = null;
		try {
			error = objetoRemoto.AsignarMozoAUnSector(mozo, sector, restaurante);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.instancia = null;
		}
		return error;
	}
	
	public void Login(String nombre, String contrasenia){
		UsuarioDTO usuario= null;
		try {
			usuario = objetoRemoto.Login(nombre, contrasenia);
			usuarioLogueado = usuario;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.instancia = null;
		}
	}
	
	public boolean LoginAdm(String nombre, String contrasenia){
		UsuarioDTO usuario= null;
		try {
			usuario = objetoRemoto.Login(nombre, contrasenia);
			usuarioLogueado = usuario;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.instancia = null;
			return false;
		}
		return usuario.isSoyAdmin();
	}
	
	public ProductoDTO GetProductoByNombreParaPlato(String nombre){
		ProductoDTO prod = null;
		try {
			prod = objetoRemoto.buscarProductoParaPlato(nombre);
		} catch (Exception e) {
			// TODO: handle exception
			this.instancia = null;
			throw new NullPointerException();
		}
		return prod;
	}
	
	public ProductoDTO GetProductoByNombreParaProveedor(String nombre){
		ProductoDTO prod = null;
		try {
			prod = objetoRemoto.buscarProductoParaProveedor(nombre);
		} catch (Exception e) {
			// TODO: handle exception.
			this.instancia = null;
			throw new NullPointerException();
		}
		return prod;
	}
	
	public ProductoDTO GetProductoByNombre(String nombre){
		ProductoDTO prod = null;
		try {
			prod = objetoRemoto.buscarProducto(nombre);
		} catch (Exception e) {
			// TODO: handle exception
			this.instancia = null;
			throw new NullPointerException();
		}
		return prod;
	}
	
	public ProveedorDTO GetProveedorByNombre(String nombre){
		ProveedorDTO prod = null;
		try {
			prod = objetoRemoto.buscarProveedor(nombre);
		} catch (Exception e) {
			// TODO: handle exception
			this.instancia = null;
			return null;
		}
		return prod;
	}
	
	public void GuardarProvedor(ProveedorDTO prov){
		try {
			objetoRemoto.GuardarProveedor(prov);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			this.instancia = null;
			e.printStackTrace();
		}
	}
	
	public PlatoDTO GetPlatoByNombre(String nombre){
		PlatoDTO plato = null;
		try {
			plato = objetoRemoto.buscarPlato(nombre);
		} catch (Exception e) {
			// TODO: handle exception
			this.instancia = null;
			return null;
		}
		return plato;
	}
	
	public PlatoDTO GetSemielaboradoByNombre(String nombre){
		PlatoDTO plato = null;
		try {
			plato = objetoRemoto.buscarPlatoSemi(nombre);
		} catch (RemoteException e) {
			// TODO: handle exception
			this.instancia = null;
			return null;
		}
		return plato;
	}

	public void GuardarPlato(PlatoDTO plato) {
		// TODO Auto-generated method stub
		try {
			objetoRemoto.GuardarPlato(plato);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			this.instancia = null;
			e.printStackTrace();
		}
	}

	public void GuardarProducto(ProductoDTO prod) {
		// TODO Auto-generated method stub
		try {
			objetoRemoto.GuardarProducto(prod);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			this.instancia = null;
			e.printStackTrace();
		}
	}
	
	public String CrearItemPlanDeProd(ItemPlanDeProduccionDTO item, boolean porPrimeraVez) {
		// TODO Auto-generated method stub
		try {
			int estado = objetoRemoto.CrearNuevoItemPlanDeProduccion(item, porPrimeraVez);
			switch (estado) {
			case -1:
				return "No hay horas disponibles para el restaurante seleccionado";

			default:
				return "Se ha agregado el semi elaborado al plan, el id es : " + estado;
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.instancia = null;
		}
		return "se ha producido un error";
	}
	
	public ItemPlanDeProduccionDTO buscarItemPlan(int id){
		ItemPlanDeProduccionDTO item = null;
		try {
			item = objetoRemoto.buscarItemPlanProduccion(id);
		} catch (RemoteException e) {
			// TODO: handle exception
			this.instancia = null;
			return null;
		}
		return item;
	}

	public boolean GuardarItemPlan(ItemPlanDeProduccionDTO item) {
		// TODO Auto-generated method stub
		try {
			return objetoRemoto.GuardarItemPlanDeProduccion(item);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			this.instancia = null;
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean BorrarItemPlanDeProduccion(ItemPlanDeProduccionDTO item) {
		// TODO Auto-generated method stub
		try {
			return objetoRemoto.BorrarItemPlanDeProduccion(item);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			this.instancia = null;
			e.printStackTrace();
		}
		return false;
	}
	
	public List<ItemPlanDeProduccionDTO> GetPlanDeProduccion() {
		// TODO Auto-generated method stub
		try {
			return objetoRemoto.GetPlanDeProduccion(usuarioLogueado.getId());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			this.instancia = null;
			e.printStackTrace();
		}
		return new ArrayList<ItemPlanDeProduccionDTO>();
	}
	
	public String GenerarOrdenesDeCompra(List<ItemCompraDTO> items, boolean porFaltante, String path) {
		// TODO Auto-generated method stub
		try {
			return objetoRemoto.GenerarOrdenesDeCompra(items, porFaltante, path, usuarioLogueado.getId());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			this.instancia = null;
			return e.getMessage();
		}
	}

	public List<ItemAReponerDTO> GenerarListaDeReposicion() {
		// TODO Auto-generated method stub
		try {
			return objetoRemoto.GenerarListaDeReposicion(usuarioLogueado.getId());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			this.instancia = null;
			e.printStackTrace();
		}
		return null;
	}
	
	public List<String> CargarRemito(String path, String oC) {
		// TODO Auto-generated method stub
		try {
			return objetoRemoto.CargarRemito(path, oC);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			this.instancia = null;
			e.printStackTrace();
		}
		return null;
	}

	public EmpleadoDTO GetEmpleadoByUsuario(int id) {
		// TODO Auto-generated method stub
		try {
			return objetoRemoto.GetEmpleadoByUsuario(id);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			this.instancia = null;
			e.printStackTrace();
		}
		return null;
	}
}
