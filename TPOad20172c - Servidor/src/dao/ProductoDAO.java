package dao;

import hibernate.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entity.Entity_CompraNoVenta;
import entity.Entity_ElaboracionNoVenta;
import entity.Entity_ElaboracionVenta;
import entity.Entity_Producto;

public class ProductoDAO {

	private static ProductoDAO instancia = null;
	private static SessionFactory sf = null;
	
	private ProductoDAO(){
		sf = HibernateUtil.getSessionFactory();
	}
	
	public static ProductoDAO getInstancia(){
		if(instancia == null)
			instancia = new ProductoDAO();
		return instancia;
	}

	public List<Entity_Producto> listarProductos() {
		Session s = sf.getCurrentSession();
		Transaction t = s.beginTransaction();
		List<Entity_Producto> p = s.createQuery("from Entity_Producto").list();
		t.commit();
		
		return p;
		
	}

	public Entity_ElaboracionVenta buscarElaboradoVentaCodigo (int codigo) {
		Entity_ElaboracionVenta p = null;
		Session s = sf.getCurrentSession();
		Transaction t = s.beginTransaction();
		Query q = s.createQuery("Select p from Entity_Producto p where p.codigo = :codigo ");
		q.setParameter("codigo", codigo);
		
		p = (Entity_ElaboracionVenta) q.uniqueResult();
		
		t.commit();
		
		return p;
		
	}
	
	public Entity_Producto buscarProductoCodigo(int codigo) {
		Entity_Producto p = null;
		Session s = sf.getCurrentSession();
		Transaction t = s.beginTransaction();
		Query q = s.createQuery("Select p from Entity_Producto p where p.codigo = :codigo ");
		q.setParameter("codigo", codigo);
		
		
		p = (Entity_Producto) q.uniqueResult();
		if(p instanceof Entity_ElaboracionNoVenta)
			p = (Entity_ElaboracionNoVenta) p;
		else if (p instanceof Entity_ElaboracionVenta){
			p = (Entity_ElaboracionVenta) p;
		}
		t.commit();
		
		return p;
		
	}
	public void altaProductoEV(Entity_ElaboracionVenta ev) {
			try{
				Session session = sf.openSession();
				session.beginTransaction();
				session.save(ev);
				session.flush();
				session.getTransaction().commit();
				session.close();
			}catch(Exception e){
				System.out.println(e);
				System.out.println("ErrorDAO: Error al Insertar EV");
			}
	 }
	
	public void altaProductoENV(Entity_ElaboracionNoVenta env) {
		try{
			Session session = sf.openSession();
			session.beginTransaction();
			session.save(env);
			session.flush();
			session.getTransaction().commit();
			session.close();
		}catch(Exception e){
			System.out.println(e);
			System.out.println("ErrorDAO: Error al Insertar ENV");
		}
	}
	
	public void altaProductoCNV(Entity_CompraNoVenta cnv) {
		try{
			Session session = sf.openSession();
			session.beginTransaction();
			session.save(cnv);
			session.flush();
			session.getTransaction().commit();
			session.close();
		}catch(Exception e){
			System.out.println(e);
			System.out.println("ErrorDAO: Error al Insertar CNV");
		}
	}
		
	public List<Entity_Producto> listarProductosParaPlanProd() {
		
		
		Session s = null;
		List<Entity_Producto> lista = new ArrayList<Entity_Producto>();
		
		try{
			s = sf.getCurrentSession();
			Transaction t = s.beginTransaction();
			
			lista = s.createQuery("from Entity_ElaboracionNoVenta").list();
			s.flush();
			t.commit();
			
		}catch(Exception e){
			System.out.println(e);
			System.out.println("ErrorDAO: SucursalDAO.ListarSucursales");
		}
		
		return lista;
		
	}
}
