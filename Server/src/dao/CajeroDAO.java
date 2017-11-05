package dao;

import hibernate.HibernateUtil;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entity.Entity_Encargado;
import entity.Entity_Mozo;

public class CajeroDAO {
	private static CajeroDAO instancia = null;
	private static SessionFactory sf = null;
	private CajeroDAO(){
		sf = HibernateUtil.getSessionFactory();
	}
	
	public static CajeroDAO getInstancia(){
		if(instancia == null)
			instancia = new CajeroDAO();
		return instancia;
	}

	public Entity_Encargado buscarCajeroCodigo(String legajo) {
		Session s = null;
		Entity_Encargado c = null;
		try{
			s = sf.getCurrentSession();
			Transaction t = s.beginTransaction();
			
			Query q = s.createQuery("select c from Entity_Empleado c where c.legajo =:leg"); 
			q.setParameter("leg", legajo);
			c = (Entity_Encargado) q.uniqueResult();
			t.commit();
			
		}catch(Exception e){
			System.out.println(e);
			System.out.println("ErrorDAO: CajeroDAO.buscarCajeroCodigo");
		}
		
		return c;
	}
	
}
