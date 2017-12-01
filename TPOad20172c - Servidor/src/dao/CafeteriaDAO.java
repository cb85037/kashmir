package dao;

import hibernate.HibernateUtil;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entity.Entity_Cafeteria;

public class CafeteriaDAO {

	private static CafeteriaDAO instancia = null;
	private static SessionFactory sf = null;
	
	private CafeteriaDAO(){
		sf = HibernateUtil.getSessionFactory();
	}
	
	public static CafeteriaDAO getInstancia(){
		if(instancia == null)
			instancia = new CafeteriaDAO();
		return instancia;
	}

	 public Entity_Cafeteria buscarPorId(int id) {
		
		Session s = null;
		Entity_Cafeteria result = null;
		try{
			s = sf.getCurrentSession();
			Transaction t = s.beginTransaction();
			
			Query q = s.createQuery("from Entity_Cafeteria c where c.id = :identificador");
			q.setParameter("identificador", id);
			result = (Entity_Cafeteria) q.uniqueResult();
			t.commit();
		}catch(Exception e){
			System.out.println(e);
			System.out.println("ErrorDAO: CafeteriaDAO.buscarPorId");
		}
		
		return result;
	}

	 public void altaBarra(Entity_Cafeteria b){
		
		try{
			/*Abro sesion y grabo el objeto*/
			Session session = sf.openSession();
			session.beginTransaction();
			session.save(b);
			session.flush();
			session.getTransaction().commit();
			session.close();
		}catch(Exception e){
			System.out.println(e);
			System.out.println("ErrorDAO: Error al Insertar Cafeteria");
		}
	 }
	 
	 
}