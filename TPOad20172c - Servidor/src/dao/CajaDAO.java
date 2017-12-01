package dao;

import hibernate.HibernateUtil;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entity.Entity_Caja;
import entity.Entity_CajaDiaria;

public class CajaDAO {

	private static CajaDAO instancia = null;
	private static SessionFactory sf = null;
	
	private CajaDAO(){
		sf = HibernateUtil.getSessionFactory();
	}
	
	public static CajaDAO getInstancia(){
		if(instancia == null)
			instancia = new CajaDAO();
		return instancia;
	}

	 public Entity_Caja buscarPorId(int id) {
		
		Session s = null;
		Entity_Caja result = null;
		try{
			s = sf.getCurrentSession();
			Transaction t = s.beginTransaction();
			
			Query q = s.createQuery("from Entity_Caja c where c.id = :identificador");
			q.setParameter("identificador", id);
			result = (Entity_Caja) q.uniqueResult();
			t.commit();
		}catch(Exception e){
			System.out.println(e);
			System.out.println("ErrorDAO: CajaDAO.buscarPorId");
		}
		
		return result;
	}
	 
	 public Entity_CajaDiaria buscarPorEncargado(String legajo) {
			
			Session s = null;
			List<Entity_CajaDiaria> result = null;
			try{
				s = sf.getCurrentSession();
				Transaction t = s.beginTransaction();
				
				Query q = s.createQuery("Select c from Entity_CajaDiaria c join c.cajero e where e.legajo = :identificador");
				q.setParameter("identificador", legajo);
				result = q.list();
				for(Entity_CajaDiaria c : result){
					if(c.getFechaCierre()==null){
						return c;
					}
				}
				t.commit();
			}catch(Exception e){
				System.out.println(e);
				System.out.println("ErrorDAO: CajaDAO.buscarPorId");
			}
			
			return null;
		}

	 public void altaCaja(Entity_Caja c){
		
		try{
			/*Abro sesion y grabo el objeto*/
			Session session = sf.openSession();
			session.beginTransaction();
			session.save(c);
			session.flush();
			session.getTransaction().commit();
			session.close();
		}catch(Exception e){
			System.out.println(e);
			System.out.println("ErrorDAO: Error al Insertar Caja");
		}
	 }

	public void altaCajaDiaria(Entity_CajaDiaria c) {
		try{
			/*Abro sesion y grabo el objeto*/
			Session session = sf.openSession();
			session.beginTransaction();
			session.save(c);
			session.flush();
			session.getTransaction().commit();
			session.close();
		}catch(Exception e){
			System.out.println(e);
			System.out.println("ErrorDAO: Error al Insertar CajaDiaria");
		}
		
	}

	public void mergeCaja(Entity_Caja caja) {
		Transaction t = null;
		Session s = sf.getCurrentSession();
		try {
			t = s.beginTransaction();

			s.merge(caja);
			t.commit();
			
		} catch (Exception e) {
			t.rollback();
			System.out.println(e);
			System.out.println("ErrorDAO: Merge Caja");
		}
		
	}
	
	public void mergeCajaDiaria(Entity_CajaDiaria caja) {
		Transaction t = null;
		Session s = sf.getCurrentSession();
		try {
			t = s.beginTransaction();
			s.merge(caja);
			s.flush();
			t.commit();
			
		} catch (Exception e) {
			t.rollback();
			System.out.println(e);
			System.out.println("ErrorDAO: Merge Caja");
		}
		
	}
	 
	 
}