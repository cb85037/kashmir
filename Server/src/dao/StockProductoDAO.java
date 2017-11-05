package dao;

import java.util.List;

import hibernate.HibernateUtil;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entity.Entity_DepositoCentral;
import entity.Entity_Deposito;
import entity.Entity_StockProducto;
import entity.Entity_Local;


public class StockProductoDAO {

	private static StockProductoDAO instancia = null;
	private static SessionFactory sf = null;
	
	private StockProductoDAO(){
		sf = HibernateUtil.getSessionFactory();
	}
	
	public static StockProductoDAO getInstancia(){
		if(instancia == null)
			instancia = new StockProductoDAO();
		return instancia;
	}
	
	public List<Entity_StockProducto> getStockProducto(Entity_DepositoCentral d) {
		Session s = null;
		List<Entity_StockProducto> lista = null;
		
		try{
			s = sf.getCurrentSession();
			Transaction t = s.beginTransaction();
			
			Query q = s.createQuery("Select d.productos from Entity_DepositoCentral d where d.idDeposito = :id");
			q.setParameter("id", d.getIdDeposito());
			
			lista = q.list();
			
			t.commit();
			
		}catch(Exception e){
			System.out.println(e);
			System.out.println("ErrorDAO: StockProductoDAO.getStockProducto");
		}
		
		return lista;
	}

	public void merge(Entity_StockProducto stock) {
		
		Transaction t = null;
		Session s = sf.getCurrentSession();
		try {
			t = s.beginTransaction();
			s.merge(stock);
			s.flush();
			t.commit();
			
		} catch (Exception e) {
			t.rollback();
			System.out.println(e);
			System.out.println("ErrorDAO: UpdateLote");
		}
		
	}

	public void save(Entity_StockProducto sp) {
		Transaction t = null;
		Session s = sf.getCurrentSession();
		try {
			t = s.beginTransaction();
			s.save(sp);
			s.flush();
			t.commit();
			
		} catch (Exception e) {
			t.rollback();
			System.out.println(e);
			System.out.println("ErrorDAO: UpdateLote");
		}
		
	}

	public List<Entity_StockProducto> getStockProductoArea(Entity_Deposito d) {
		Session s = null;
		List<Entity_StockProducto> lista = null;
		
		try{
			s = sf.getCurrentSession();
			Transaction t = s.beginTransaction();
			
			Query q = s.createQuery("Select d.productos from Entity_DepositoArea d where d.idDeposito = :id");
			q.setParameter("id", d.getIdDeposito());
			
			lista = q.list();
			
			t.commit();
			
		}catch(Exception e){
			System.out.println(e);
			System.out.println("ErrorDAO: StockProductoDAO.getStockProductoArea");
		}
		
		return lista;
	}

	

	
}
