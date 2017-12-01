package dao;

import java.util.ArrayList;
import java.util.List;

import hibernate.HibernateUtil;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import dto.DTO_Empleado;
import entity.Entity_Caja;
import entity.Entity_Encargado;
import entity.Entity_DepositoLocal;
import entity.Entity_Mesa;
import entity.Entity_MesaSimple;
import entity.Entity_Local;

public class LocalDAO {

	private static LocalDAO instancia = null;
	private static SessionFactory sf = null;
	
	private LocalDAO(){
		sf = HibernateUtil.getSessionFactory();
	}
	
	public static LocalDAO getInstancia(){
		if(instancia == null)
			instancia = new LocalDAO();
		return instancia;
	}

	 public Entity_Local buscarSucursalNombre(String nombre) {
		
		Session s = null;
		Entity_Local result = null;
		try{
			s = sf.getCurrentSession();
			Transaction t = s.beginTransaction();
			
			Query q = s.createQuery("from Entity_Local s where s.nombre = :identificador");
			q.setParameter("identificador", nombre);
			result = (Entity_Local) q.uniqueResult();
			t.commit();
		}catch(Exception e){
			System.out.println(e);
			System.out.println("ErrorDAO: SucursalDAO.buscarSucursalNombre");
		}
		
		return result;
	}

	 public void altaSucursal(Entity_Local s){
		
		try{
			/*Abro sesion y grabo el objeto*/
			Session session = sf.openSession();
			session.beginTransaction();
			session.save(s);
			session.flush();
			session.getTransaction().commit();
			session.close();
		}catch(Exception e){
			System.out.println(e);
			System.out.println("ErrorDAO: Error al Insertar Sucursal");
		}
	 }

	public void merge(Entity_Local suc) {
			Transaction t = null;
			Session s = sf.getCurrentSession();
			try {
				t = s.beginTransaction();

				s.merge(suc);
				t.commit();
				
			} catch (Exception e) {
				t.rollback();
				System.out.println(e);
				System.out.println("ErrorDAO: Merge Sucursal");
			}
		}

	public List<Entity_Local> listarSucursales() {
		Session s = null;
		List<Entity_Local> lista = null;
		
		try{
			s = sf.getCurrentSession();
			Transaction t = s.beginTransaction();
			
			lista = s.createQuery("from Entity_Local").list();
			t.commit();
			
		}catch(Exception e){
			System.out.println(e);
			System.out.println("ErrorDAO: SucursalDAO.ListarSucursales");
		}
		
		return lista;
	}

	public void actualizarHsProduccion(Entity_Local suc) {
		Transaction t = null;
		Session s = sf.getCurrentSession();
		try {
			t = s.beginTransaction();
			Query q = s.createQuery("Update Entity_Local s set s.hsDisponiblesProduccion = :hs "
					+ "where s.nombre = :nombre");
			q.setParameter("hs", suc.getHsDisponiblesProduccion());
			q.setParameter("nombre", suc.getNombre());
			
			q.executeUpdate();
			
			t.commit();
			
		} catch (Exception e) {
			t.rollback();
			System.out.println(e);
			System.out.println("ErrorDAO: actualizarHsProduccionl");
		}
		
	}

	public Entity_DepositoLocal buscarDSucursalUsuario(String legajo) {
		Entity_DepositoLocal result = null;
		Transaction t = null;
		Session s = sf.getCurrentSession();
		try {
			t = s.beginTransaction();
			Query q = s.createQuery("Select s.deposito from Entity_Local s inner join s.empleado e where e.legajo = :legajo");
			q.setParameter("legajo", legajo);
			
			result = (Entity_DepositoLocal) q.uniqueResult();
			
			t.commit();
			
		} catch (Exception e) {
			t.rollback();
			System.out.println(e);
			System.out.println("ErrorDAO: buscarDSucursalUsuario");
		}
		
		return result;
	}

	public Entity_Local buscarSucursalEmpleado(DTO_Empleado emp) {
		Entity_Local result = null;
		Transaction t = null;
		Session s = sf.getCurrentSession();
		try {
			t = s.beginTransaction();
			Query q = s.createQuery("Select s from Entity_Local s inner join s.salon sal inner join sal.sectores sec "
			+ "inner join sec.mozos m where m.legajo = :legajo");
			q.setParameter("legajo", emp.getLegajo());
			
			result = (Entity_Local) q.uniqueResult();
			
			t.commit();
			
		} catch (Exception e) {
			t.rollback();
			System.out.println(e);
			System.out.println("ErrorDAO: buscarSucursalEmpleado");
		}
		
		return result;
	}

	public Entity_Local buscarSucursalEmpleadoCocina(DTO_Empleado emp) {
		Entity_Local result = null;
		Transaction t = null;
		Session s = sf.getCurrentSession();
		try {
			t = s.beginTransaction();
			Query q = s.createQuery("Select s from Entity_Local s inner join s.cocina c where c.empleado.legajo = :legajo");
			q.setParameter("legajo", emp.getLegajo());
			
			result = (Entity_Local) q.uniqueResult();
			
			t.commit();
			
		} catch (Exception e) {
			t.rollback();
			System.out.println(e);
			System.out.println("ErrorDAO: buscarSucursalEmpleadoCocina");
		}
		
		return result;
	}

	public Entity_Local buscarSucursalEmpleadoBarra(DTO_Empleado emp) {
		Entity_Local result = null;
		Transaction t = null;
		Session s = sf.getCurrentSession();
		try {
			t = s.beginTransaction();
			Query q = s.createQuery("Select s from Entity_Local s inner join s.barra c where c.empleado.legajo = :legajo");
			q.setParameter("legajo", emp.getLegajo());
			
			result = (Entity_Local) q.uniqueResult();
			
			t.commit();
			
		} catch (Exception e) {
			t.rollback();
			System.out.println(e);
			System.out.println("ErrorDAO: buscarSucursalEmpleadoBarra");
		}
		
		return result;
	}

	public Entity_Local buscarSucursalEmpleadoCafeteria(DTO_Empleado emp) {
		Entity_Local result = null;
		Transaction t = null;
		Session s = sf.getCurrentSession();
		try {
			t = s.beginTransaction();
			Query q = s.createQuery("Select s from Entity_Local s inner join s.cafeteria c where c.empleado.legajo = :legajo");
			q.setParameter("legajo", emp.getLegajo());
			
			result = (Entity_Local) q.uniqueResult();
			
			t.commit();
			
		} catch (Exception e) {
			t.rollback();
			System.out.println(e);
			System.out.println("ErrorDAO: buscarSucursalEmpleadoBarra");
		}
		
		return result;
	}

	public Entity_Local buscarSucursalCajero(DTO_Empleado emp) {
		Entity_Local result = null;
		Transaction t = null;
		Session s = sf.getCurrentSession();
		try {
			t = s.beginTransaction();
			Query q = s.createQuery("Select s from Entity_Local s where s.empleado.legajo = :legajo");
			q.setParameter("legajo", emp.getLegajo());
			
			result = (Entity_Local) q.uniqueResult();
			
			t.commit();
			
		} catch (Exception e) {
			t.rollback();
			System.out.println(e);
			System.out.println("ErrorDAO: buscarSucursalCajero");
		}
		
		return result;
	}

	public List<Entity_Mesa> buscarMesasSucursal(Entity_Local suc) {
		List<Entity_Mesa> result = null;
		Transaction t = null;
		Session s = sf.getCurrentSession();
		try {
			t = s.beginTransaction();
			Query q = s.createQuery("Select m from Entity_Local s inner join s.salon sa inner join sa.sectores sec "
					+ "inner join sec.mesas m where s.nombre = :nombre and m.estado =:estado");
			q.setParameter("nombre", suc.getNombre());
			q.setParameter("estado", "ocupada");
			result = q.list();
			
			t.commit();
			
		} catch (Exception e) {
			t.rollback();
			System.out.println(e);
			System.out.println("ErrorDAO: buscarMesasSucurusal");
		}
		
		return result;
	}

}