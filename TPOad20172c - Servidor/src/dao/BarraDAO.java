package dao;

import hibernate.HibernateUtil;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entity.Entity_Barra;

public class BarraDAO {

	private static BarraDAO instancia = null;
	private static SessionFactory sf = null;
	
	private BarraDAO(){
		sf = HibernateUtil.getSessionFactory();
	}
	
	public static BarraDAO getInstancia(){
		if(instancia == null)
			instancia = new BarraDAO();
		return instancia;
	}

	 public Entity_Barra buscarPorId(int id) {
		
		Session s = null;
		Entity_Barra result = null;
		try{
			s = sf.getCurrentSession();
			Transaction t = s.beginTransaction();
			
			Query q = s.createQuery("from Entity_Barra b where b.id = :identificador");
			q.setParameter("identificador", id);
			result = (Entity_Barra) q.uniqueResult();
			t.commit();
		}catch(Exception e){
			System.out.println(e);
			System.out.println("ErrorDAO: BarraDAO.buscarPorId");
		}
		
		return result;
	}

	 public void altaBarra(Entity_Barra b){
		
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
			System.out.println("ErrorDAO: Error al Insertar Barra");
		}
	 }
	 
	 
}