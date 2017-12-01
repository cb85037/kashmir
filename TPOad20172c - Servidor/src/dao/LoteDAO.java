package dao;

import java.util.List;

import hibernate.HibernateUtil;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entity.Entity_Lote;
import entity.Entity_Movimiento;
import entity.Entity_Local;

public class LoteDAO {

	private static LoteDAO instancia = null;
	private static SessionFactory sf = null;
	
	private LoteDAO(){
		sf = HibernateUtil.getSessionFactory();
	}
	
	public static LoteDAO getInstancia(){
		if(instancia == null)
			instancia = new LoteDAO();
		return instancia;
	}
	
	public void update(Entity_Lote l) {
		Transaction t = null;
		Session s = sf.getCurrentSession();
		try {
			t = s.beginTransaction();
			s.update(l);
			s.flush();
			t.commit();
			
		} catch (Exception e) {
			t.rollback();
			System.out.println(e);
			System.out.println("ErrorDAO: UpdateLote");
		}
	}

	public void save(Entity_Lote l) {
		Transaction t = null;
		Session s = sf.getCurrentSession();
		try {
			t = s.beginTransaction();
			s.save(l);
			s.flush();
			t.commit();
			
		} catch (Exception e) {
			t.rollback();
			System.out.println(e);
			System.out.println("ErrorDAO: saveLote");
		}
	}
	public int getID() {
		Transaction t = null;
		int result = 0;
		Session s = sf.getCurrentSession();
		try {
			t = s.beginTransaction();
			Query q = s.createQuery("Select max(idLote) from Entity_Lote");
			result = (int) q.uniqueResult();
			
		} catch (Exception e) {
			t.rollback();
			System.out.println(e);
			System.out.println("ErrorDAO: getIDLote");
		}
		
		return result;
	}

	public List<Entity_Lote> getLotesMovimiento(Entity_Movimiento m) {
		Session s = null;
		List<Entity_Lote> result = null;
		try{
			s = sf.getCurrentSession();
			Transaction t = s.beginTransaction();
			
			Query q = s.createQuery("Select l from Entity_Movimiento m inner join m.lotes l where m.idMovimiento = :identificador");
			q.setParameter("identificador", m.getIdMovimiento());
			result = q.list();
			
			t.commit();
		}catch(Exception e){
			System.out.println(e);
			System.out.println("ErrorDAO: SucursalDAO.buscarSucursalNombre");
		}
		
		return result;
	}
}
