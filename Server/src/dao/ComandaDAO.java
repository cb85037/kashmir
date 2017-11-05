package dao;

import hibernate.HibernateUtil;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entity.Entity_Comanda;
import entity.Entity_ItemComanda;
import entity.Entity_Mesa;
import entity.Entity_MesaSimple;
import entity.Entity_Mozo;

public class ComandaDAO {
	
	private static ComandaDAO instancia = null;
	private static SessionFactory sf = null;

	
	public static ComandaDAO getInstancia(){
		if(instancia == null)
			instancia = new ComandaDAO();
		return instancia;
	}
	
	public ComandaDAO() {
		// TODO Auto-generated constructor stub
		sf = HibernateUtil.getSessionFactory();
	}
	
	public Entity_Comanda buscarComanda(int numero){
		Session s = null;
		Entity_Comanda c = null;
		try{
			s = sf.getCurrentSession();
			Transaction t = s.beginTransaction();
			
			Query q = s.createQuery("select C from Entity_Comanda C where C.numero =:numero"); 
			q.setParameter("numero", numero);
			c = (Entity_Comanda) q.uniqueResult();
			s.flush();
			t.commit();
			
		}catch(Exception e){
			System.out.println(e);
			System.out.println("ErrorDAO: ComandaDAO.buscarComanda");
		}
		
		return c;
	}
	
	public void updateComanda(Entity_Comanda comanda){
		Session s=null;
		try {
			s=sf.getCurrentSession(); 
			Transaction t=s.beginTransaction();
			s.merge(comanda);
			t.commit();
		} catch (Exception e){
			System.out.println(e);
			System.out.println("Error ComandaDAO. Update Comanda");
		}
	}

	public void save(Entity_Comanda comanda) {
		Session s=null;
		try {
			s=sf.getCurrentSession(); 
			Transaction t=s.beginTransaction();
			s.merge(comanda);
			s.flush();
			t.commit();
		} catch (Exception e){
			System.out.println(e);
			System.out.println("Error ComandaDAO. Save Comanda");
		}
	}

	public Entity_Comanda buscarComandaMesa(Entity_MesaSimple mesaSimp) {
		Session s = null;
		Entity_Comanda c = null;
		try{
			s = sf.getCurrentSession();
			Transaction t = s.beginTransaction();
			
			Query q = s.createQuery("select c from Entity_Comanda c where c.mesa.idMesa =:numero"); 
			q.setParameter("numero", mesaSimp.getIdMesa());
			c = (Entity_Comanda) q.uniqueResult();
			t.commit();
			
		}catch(Exception e){
			System.out.println(e);
			System.out.println("ErrorDAO: ComandaDAO.buscarComandaMesa");
		}
		
		return c;
	}

	public void eliminarItemComanda(Entity_ItemComanda item) {
		Session s = null;
		try{
			s = sf.getCurrentSession();
			Transaction t = s.beginTransaction();
			s.delete(item);
			t.commit();
			
		}catch(Exception e){
			System.out.println(e);
			System.out.println("ErrorDAO: ComandaDAO.eliminarItem");
		}
		
		
	}

	public Entity_ItemComanda buscarItemComanda(int id) {
		Session s = null;
		Entity_ItemComanda c = null;
		try{
			s = sf.getCurrentSession();
			Transaction t = s.beginTransaction();
			
			Query q = s.createQuery("select C from Entity_ItemComanda C where C.id =:numero"); 
			q.setParameter("numero", id);
			c = (Entity_ItemComanda) q.uniqueResult();
			t.commit();
			
		}catch(Exception e){
			System.out.println(e);
			System.out.println("ErrorDAO: ComandaDAO.buscarItemComanda");
		}
		
		return c;
	}

	public void updateItemComanda(Entity_ItemComanda item) {
		Session s=null;
		try {
			s=sf.getCurrentSession(); 
			Transaction t=s.beginTransaction();
			s.merge(item);
			t.commit();
		} catch (Exception e){
			System.out.println(e);
			System.out.println("Error ComandaDAO. Update ItemComanda");
		}
		
	}

	
}
