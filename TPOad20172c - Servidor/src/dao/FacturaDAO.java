package dao;

import hibernate.HibernateUtil;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entity.Entity_Comanda;
import entity.Entity_Factura;
import entity.Entity_Liquidacion;
import entity.Entity_Mozo;

public class FacturaDAO {

	private static FacturaDAO instancia = null;
	private static SessionFactory sf = null;
		
	public static FacturaDAO getInstancia(){
		if(instancia == null)
			instancia = new FacturaDAO();
		return instancia;
	}
	
	public FacturaDAO() {
		// TODO Auto-generated constructor stub
		sf = HibernateUtil.getSessionFactory();
	}
	
	public Entity_Factura buscarFactura(int numero){
		Session s = null;
		Entity_Factura f = null;
		try{
			s = sf.getCurrentSession();
			Transaction t = s.beginTransaction();
			
			Query q = s.createQuery("select F from Entity_Factura F where F.numero =:numero"); 
			q.setParameter("numero", numero);
			f = (Entity_Factura) q.uniqueResult();
			t.commit();
			
		}catch(Exception e){
			System.out.println(e);
			System.out.println("ErrorDAO: FacturaDAO.buscarFactura");
		}
		
		return f;
	}
	public void updateFactura(Entity_Factura factura){
		Session s=null;
		try {
			s=sf.getCurrentSession(); 
			Transaction t=s.beginTransaction();
			s.merge(factura);
			t.commit();
		} catch (Exception e){
			System.out.println(e);
			System.out.println("Error FacturaDAO. Update Factura");
		}
	}
	public void updateLiquidacion(Entity_Liquidacion liquidacion){
		Session s=null;
		try {
			s=sf.getCurrentSession(); 
			Transaction t=s.beginTransaction();
			s.merge(liquidacion);
			t.commit();
		} catch (Exception e){
			System.out.println(e);
			System.out.println("Error FacturaDAO. Update liquidacion");
		}
	}
	public Entity_Factura buscarFacturaCodigo(int numero){
		Session s = null;
		Entity_Factura f = null;
		try{
			s = sf.getCurrentSession();
			Transaction t = s.beginTransaction();
			
			Query q = s.createQuery("select f from Entity_Factura f where f.numero =:nro"); 
			q.setParameter("nro", numero);
			f = (Entity_Factura) q.uniqueResult();
			t.commit();
			
		}catch(Exception e){
			System.out.println(e);
			System.out.println("ErrorDAO: FacturaDAO.buscarFacturaCodigo");
		}
		return f;
	}
	public void altaLiquidacion(Entity_Liquidacion liquidacion){
		Session s=null;
		try {
			s=sf.getCurrentSession(); 
			Transaction t=s.beginTransaction();
			s.save(liquidacion);
			t.commit();
		} catch (Exception e){
			System.out.println(e);
			System.out.println("Error FacturaDAO. Update liquidacion");
		}
	}
	public void altaFactura(Entity_Factura factura){
		Session s=null;
		try {
			s=sf.getCurrentSession(); 
			Transaction t=s.beginTransaction();
			s.save(factura);
			t.commit();
		} catch (Exception e){
			System.out.println(e);
			System.out.println("Error FacturaDAO. Update liquidacion");
		}
	}
}
