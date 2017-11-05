package administracion;

import java.rmi.RemoteException;

import dao.EmpleadoDAO;
import dto.DTO_Empleado;
import entity.Entity_EmpAdministracion;
import entity.Entity_Empleado;



public class Gestor_Empleado {

	private static Gestor_Empleado instancia;
	private EmpleadoDAO dao = EmpleadoDAO.getInstancia();
	
	private Gestor_Empleado() {
		
	}
	
	public static Gestor_Empleado getInstancia(){
		if(instancia == null)
			instancia = new Gestor_Empleado();
		return instancia;
	}

	public DTO_Empleado validarUsuarioAdmin(String usuario, String password) throws RemoteException {
		Entity_Empleado e = dao.validarUsuarioAdmin(usuario,password);
		DTO_Empleado emp = null;
		if(e != null){
			emp = e.getDTO();
		}else{
			throw new RemoteException("El usuario ingresado no existe"); 
		}
		
		return emp;
		
		
	}

	public DTO_Empleado validarUsuario(String usuario, String password, String tipo) throws RemoteException {
		
		Entity_Empleado e = dao.validarUsuario(usuario,password,tipo);
		
		DTO_Empleado emp = null;
		
		if(e != null){
			
			emp = e.getDTO();
			
		}else{
			throw new RemoteException("El usuario ingresado no existe"); 
		}
		
		return emp;
		
	}

	public boolean validarUsuarioCocina(DTO_Empleado emp) {
		
		Entity_Empleado e = dao.buscarUsuarioCocina(emp);
		
		if(e != null)
			return true;
		else
			return false;
	}
	
	public boolean validarUsuarioBarra(DTO_Empleado emp) {
		
		Entity_Empleado e = dao.buscarUsuarioBarra(emp);
		
		if(e != null)
			return true;
		else
			return false;
	}
	
	public boolean validarUsuarioCafeteria(DTO_Empleado emp) {
		
		Entity_Empleado e = dao.buscarUsuarioCafeteria(emp);
		
		if(e != null)
			return true;
		else
			return false;
	}
	
	
}