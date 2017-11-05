package dao;

import java.util.List;

import hibernate.HibernateUtil;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entity.Entity_DepositoArea;
import entity.Entity_DepositoCentral;
import entity.Entity_DepositoLocal;
import entity.Entity_Deposito;


public class DepositoDAO {

	private static DepositoDAO instancia = null;
	private static SessionFactory sf = null;
	
	private DepositoDAO(){
		sf = HibernateUtil.getSessionFactory();
	}
	
	public static DepositoDAO getInstancia(){
		if(instancia == null)
			instancia = new DepositoDAO();
		return instancia;
	}

	public Entity_DepositoCentral getDepositoCentral() {
		
		Session s = null;
		Entity_DepositoCentral d = null;
		
		try{
			s = sf.getCurrentSession();
			Transaction t = s.beginTransaction();
			
			d = (Entity_DepositoCentral) s.createQuery("from Entity_DepositoCentral").uniqueResult();
			t.commit();
			
		}catch(Exception e){
			System.out.println(e);
			System.out.println("ErrorDAO: DepositoDAO.getDepositoCentral");
		}
		
		return d;
		
	}

	public void merge(Entity_Deposito deposito) {
			Transaction t = null;
		
			Session s = sf.getCurrentSession();
			
			try {
				t = s.beginTransaction();
				s.merge(deposito);
				s.flush();
				t.commit();
			
			} catch (Exception e) {
				t.rollback();
				System.out.println(e);
				System.out.println("ErrorDAO: MergeDeposito");
			}
		
		
	}

	public Entity_DepositoLocal getDepositoSucursal(int idDeposito) {
		Session s = null;
		Entity_DepositoLocal d = null;
		
		try{
			s = sf.getCurrentSession();
			Transaction t = s.beginTransaction();
			
			Query q = s.createQuery("select d from Entity_DepositoLocal d where d.idDeposito = :identificador");
			q.setParameter("identificador", idDeposito);
			d = (Entity_DepositoLocal) q.uniqueResult();
			t.commit();
			
		}catch(Exception e){
			System.out.println(e);
			System.out.println("ErrorDAO: DepositoDAO.getDepositoSucursal");
		}
		
		return d;
	}

	public Entity_DepositoArea getDepositoArea(int idDeposito) {
		Session s = null;
		Entity_DepositoArea d = null;
		
		try{
			s = sf.getCurrentSession();
			Transaction t = s.beginTransaction();
			
			Query q = s.createQuery("select d from Entity_DepositoArea d where d.idDeposito = :identificador");
			q.setParameter("identificador", idDeposito);
			d = (Entity_DepositoArea) q.uniqueResult();
			t.commit();
			
		}catch(Exception e){
			System.out.println(e);
			System.out.println("ErrorDAO: DepositoDAO.getDepositoArea");
		}
		
		return d;
	}

	
}