package dao;

import javax.persistence.Query;

import hibernate.HibernateUtil;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import entity.Entity_AdministracionCentral;

public class AdministracionCentralDAO {

	private static AdministracionCentralDAO instancia = null;
	private static SessionFactory sf = null;
	
	private AdministracionCentralDAO(){
		sf = HibernateUtil.getSessionFactory();
	}
	
	public static AdministracionCentralDAO getInstancia(){
		if(instancia == null)
			instancia = new AdministracionCentralDAO();
		return instancia;
	}
	
	public Entity_AdministracionCentral getAdministracionCentral(){
		
		Entity_AdministracionCentral a = null;
		Session s = sf.getCurrentSession();
		
		
		try{
			s = sf.getCurrentSession();
			Transaction t = s.beginTransaction();
			
			a = (Entity_AdministracionCentral) s.createQuery("from Entity_AdministracionCentral").uniqueResult();
			t.commit();
			
		}catch(Exception e){
			System.out.println(e);
			System.out.println("ErrorDAO: AdministracionCentralDAO.getAC");
		}
		
		return a;
		
		
	}
}
