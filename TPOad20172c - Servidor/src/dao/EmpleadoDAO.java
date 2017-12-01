package dao;

import hibernate.HibernateUtil;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import dto.DTO_Empleado;
import entity.Entity_Encargado;
import entity.Entity_EmpAdministracion;
import entity.Entity_EmpProduccion;
import entity.Entity_Empleado;
import entity.Entity_Mozo;


public class EmpleadoDAO {

	private static EmpleadoDAO instancia = null;
	private static SessionFactory sf = null;
	
	private EmpleadoDAO(){
		sf = HibernateUtil.getSessionFactory();
	}
	
	public static EmpleadoDAO getInstancia(){
		if(instancia == null)
			instancia = new EmpleadoDAO();
		return instancia;
	}

	public Entity_Empleado validarUsuarioAdmin(String usuario, String password) {
		Session s = null;
		Entity_Empleado result = null;
		try{
			s = sf.getCurrentSession();
			Transaction t = s.beginTransaction();
			
			Query q = s.createQuery("from Entity_EmpAdministracion e where e.usuario = :user and e.password = :pass");
			q.setParameter("user", usuario);
			q.setParameter("pass", password);
			result = (Entity_EmpAdministracion) q.uniqueResult();
			t.commit();
		}catch(Exception e){
			System.out.println(e);
			System.out.println("ErrorDAO: EmpleadoDAO.validarUsuario");
		}
		
		return result;
	}
	
	public Entity_Empleado getEmpleadoLegajo(String legajo){
		Session s = null;
		Entity_Empleado result = null;
		try{
			s = sf.getCurrentSession();
			Transaction t = s.beginTransaction();
			
			Query q = s.createQuery("from Entity_Empleado s where s.legajo = :identificador");
			q.setParameter("identificador", legajo);
			result = (Entity_Empleado) q.uniqueResult();
			t.commit();
		}catch(Exception e){
			System.out.println(e);
			System.out.println("ErrorDAO: EmpleadoDAO.getEmpleadoLegajo");
		}
		
		return result;
	}

	public Entity_Empleado validarUsuario(String usuario, String password, String tipo) {
		
		Session s = null;
		
		Entity_Empleado result = null;
		try{
			s = sf.getCurrentSession();
			Transaction t = s.beginTransaction();
			
			if(tipo.equalsIgnoreCase("Mozo")){
				Query q = s.createQuery("from Entity_Mozo e where e.usuario = :user and e.password = :pass");
				q.setParameter("user", usuario);
				q.setParameter("pass", password);
				result = (Entity_Mozo) q.uniqueResult();
				t.commit();
				
			}else if(tipo.equalsIgnoreCase("Produccion")){
				Query q = s.createQuery("from Entity_EmpProduccion e where e.usuario = :user and e.password = :pass");
				q.setParameter("user", usuario);
				q.setParameter("pass", password);
				result = (Entity_EmpProduccion) q.uniqueResult();
				t.commit();
				
			}else if(tipo.equalsIgnoreCase("Cajero")){
				Query q = s.createQuery("from Entity_Encargado e where e.usuario = :user and e.password = :pass");
				q.setParameter("user", usuario);
				q.setParameter("pass", password);
				result = (Entity_Encargado) q.uniqueResult();
				t.commit();
			}
			
		}catch(Exception e){
			System.out.println(e);
			System.out.println("ErrorDAO: EmpleadoDAO.validarUsuario");
		}
		
		return result;
	}

	public Entity_Empleado buscarUsuarioCocina(DTO_Empleado emp) {
		Session s = null;
		Entity_Empleado result = null;
		try{
			s = sf.getCurrentSession();
			Transaction t = s.beginTransaction();
			
			Query q = s.createQuery("Select e from Entity_Cocina x inner join x.empleado e where e.legajo = :identificador");
			q.setParameter("identificador", emp.getLegajo());
			result = (Entity_Empleado) q.uniqueResult();
			t.commit();
		}catch(Exception e){
			System.out.println(e);
			System.out.println("ErrorDAO: EmpleadoDAO.getEmpleadoCocina");
		}
		
		return result;
	}

	public Entity_Empleado buscarUsuarioBarra(DTO_Empleado emp) {
		Session s = null;
		Entity_Empleado result = null;
		try{
			s = sf.getCurrentSession();
			Transaction t = s.beginTransaction();
			
			Query q = s.createQuery("Select e from Entity_Barra x inner join x.empleado e where e.legajo = :identificador");
			q.setParameter("identificador", emp.getLegajo());
			result = (Entity_Empleado) q.uniqueResult();
			t.commit();
		}catch(Exception e){
			System.out.println(e);
			System.out.println("ErrorDAO: EmpleadoDAO.getEmpleadoBarra");
		}
		
		return result;
	}

	public Entity_Empleado buscarUsuarioCafeteria(DTO_Empleado emp) {
		Session s = null;
		Entity_Empleado result = null;
		try{
			s = sf.getCurrentSession();
			Transaction t = s.beginTransaction();
			
			Query q = s.createQuery("Select e from Entity_Cafeteria x inner join x.empleado e where e.legajo = :identificador");
			q.setParameter("identificador", emp.getLegajo());
			result = (Entity_Empleado) q.uniqueResult();
			t.commit();
		}catch(Exception e){
			System.out.println(e);
			System.out.println("ErrorDAO: EmpleadoDAO.getEmpleadoCafeteria");
		}
		
		return result;
	}
	
	
}