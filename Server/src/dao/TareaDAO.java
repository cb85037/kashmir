package dao;

import java.util.List;

import hibernate.HibernateUtil;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


import entity.Entity_Tarea;

public class TareaDAO {

	private static TareaDAO instancia = null;
	private static SessionFactory sf = null;
	
	private TareaDAO(){
		sf = HibernateUtil.getSessionFactory();
	}
	
	public static TareaDAO getInstancia(){
		if(instancia == null)
			instancia = new TareaDAO();
		return instancia;
	}

	public List<Entity_Tarea> listarTareas() {
		Session s = null;
		List<Entity_Tarea> lista = null;
		
		try{
			s = sf.getCurrentSession();
			Transaction t = s.beginTransaction();
			
			lista = s.createQuery("from Entity_Tarea").list();
			t.commit();
			
		}catch(Exception e){
			System.out.println(e);
			System.out.println("ErrorDAO: TareaDAO.ListarTareas");
		}
		
		return lista;
	}

	public List<Entity_Tarea> listarTareasDeSucursal(String nombre) {
		Session s = null;
		List<Entity_Tarea> lista = null;
		
		try{
			s = sf.getCurrentSession();
			Transaction t = s.beginTransaction();
			
			Query q = s.createQuery("select t from Entity_Tarea t where t.estado = :status and t.sucursal.nombre = :name"); 
			q.setParameter("name", nombre);
			q.setParameter("status", "activa");
			lista = q.list();
			
			t.commit();
			
		}catch(Exception e){
			System.out.println(e);
			System.out.println("ErrorDAO: TareaDAO.ListarTareas");
		}
		
		return lista;
	}

	public void altaTarea(Entity_Tarea tarea) {

		try{
			Session session = sf.openSession();
			session.beginTransaction();
			session.save(tarea);
			session.flush();
			session.getTransaction().commit();
			session.close();
		}catch(Exception e){
			System.out.println(e);
			System.out.println("ErrorDAO: Error al Insertar Tarea");
		}
		
	}
	
	
}
