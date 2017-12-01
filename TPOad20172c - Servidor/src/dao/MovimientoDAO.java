package dao;

import hibernate.HibernateUtil;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entity.Entity_Movimiento;


public class MovimientoDAO {

	private static MovimientoDAO instancia = null;
	private static SessionFactory sf = null;
	
	private MovimientoDAO(){
		sf = HibernateUtil.getSessionFactory();
	}
	
	public static MovimientoDAO getInstancia(){
		if(instancia == null)
			instancia = new MovimientoDAO();
		return instancia;
	}

	public void altaMovimiento(Entity_Movimiento m) {
		try{
			Session session = sf.openSession();
			session.beginTransaction();
			session.save(m);
			session.flush();
			session.getTransaction().commit();
			session.close();
		}catch(Exception e){
			System.out.println(e);
			System.out.println("ErrorDAO: Error al Insertar Movimiento");
		}
		
	}

	public Entity_Movimiento getMovimiento(int id) {
		Session s = null;
		Entity_Movimiento result = null;
		try{
			s = sf.getCurrentSession();
			Transaction t = s.beginTransaction();
			
			Query q = s.createQuery("from Entity_Movimiento m where m.idMovimiento = :identificador");
			q.setParameter("identificador", id);
			result = (Entity_Movimiento) q.uniqueResult();
			t.commit();
		}catch(Exception e){
			System.out.println(e);
			System.out.println("ErrorDAO: getMovimientoID");
		}
		
		return result;
	}

	public void updateMovimiento(Entity_Movimiento m) {
		Transaction t = null;
		Session s = sf.getCurrentSession();
		try {
			t = s.beginTransaction();
			s.update(m);
			s.flush();
			t.commit();
			
		} catch (Exception e) {
			t.rollback();
			System.out.println(e);
			System.out.println("ErrorDAO: UpdateMovimiento");
		}
		
	}

	public void save(Entity_Movimiento d) {
		Transaction t = null;
		Session s = sf.getCurrentSession();
		try {
			t = s.beginTransaction();
			s.merge(d);
			s.flush();
			t.commit();
			
		} catch (Exception e) {
			t.rollback();
			System.out.println(e);
			System.out.println("ErrorDAO: SaveMovimiento");
		}
		
	}
	
}
