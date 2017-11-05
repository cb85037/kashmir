package dao;

import hibernate.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

//import com.sun.xml.internal.stream.Entity;

import entity.Entity_Mesa;
import entity.Entity_MesaCompuesta;
import entity.Entity_MesaSimple;

public class MesaDAO {
	private static MesaDAO instancia = null;
	private static SessionFactory sf = null;
	
	private MesaDAO(){
		sf = HibernateUtil.getSessionFactory();
	}
	
	public static MesaDAO getInstancia(){
		if(instancia == null)
			instancia = new MesaDAO();
		return instancia;
	}
	
	public void altaMesa(Entity_Mesa mesa){
		try {
			Session session=sf.getCurrentSession();
			session.beginTransaction();
			session.save(mesa);
			session.flush();
			session.getTransaction().commit();
		} catch (Exception e){
			System.out.println(e);
			System.out.println("Error MesaDAO. Alta Mesa");
		}
	}
	
	public void mergeMesa(Entity_Mesa mesa){
		try {
			Session session=sf.getCurrentSession();
			session.beginTransaction();
			session.merge(mesa);
			session.flush();
			session.getTransaction().commit();
		} catch (Exception e){
			System.out.println(e);
			System.out.println("Error MesaDAO. Merge Mesa");
		}
	}
	
	public void deleteMesa(Entity_MesaCompuesta mesa){
		try {
			Session session=sf.getCurrentSession();
			session.beginTransaction();
			session.delete(mesa);
			session.flush();
			session.getTransaction().commit();
		} catch (Exception e){
			System.out.println(e);
			System.out.println("Error MesaDAO. Merge Mesa");
		}
	}
	
	
	public Entity_MesaSimple buscarMesa(int idMesa){
		Entity_MesaSimple m = null;
		
		try {
			Session session=sf.getCurrentSession();
			session.beginTransaction();
			m = (Entity_MesaSimple) session.createQuery("from Entity_MesaSimple m where m.idMesa= :idMesa").setParameter("idMesa", idMesa).uniqueResult();
			session.flush();
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Error MesaDAO. Buscar Mesa");
		}
		
		return m;
	}
	
	public Entity_MesaCompuesta buscarMesaCompuesta(int idMesa){
		Entity_MesaCompuesta m = null;
		
		try {
			Session session=sf.getCurrentSession();
			session.beginTransaction();
			m= (Entity_MesaCompuesta) session.createQuery("from Entity_MesaCompuesta m where m.idMesa= :idMesa").setParameter("idMesa", idMesa).uniqueResult();
			session.flush();
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Error MesaDAO. Buscar Mesa");
		}
		
		return m;
	}
	
	@SuppressWarnings("unchecked")
	public List<Entity_MesaSimple> listarMesasLibresPorSector(int idSector) {
		List<Entity_MesaSimple> lista=new ArrayList<Entity_MesaSimple>();
		
		try {
			Session session=sf.getCurrentSession();
			session.beginTransaction();
			
			Query q=session.createQuery("select M from Entity_Sector S join S.mesas M"
					+ " where S.idSector=:idSector and M.estado=:estado");
			q.setParameter("idSector", idSector);
			q.setParameter("estado", "libre");
			lista = q.list();
		
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Error MesaDAO. listarMesasLibresPorSector");
		}
		
		return lista;
		
	}
	
	public List<Entity_MesaCompuesta> listarMesasCompuestas(){
		List<Entity_MesaCompuesta>  m = new ArrayList<Entity_MesaCompuesta>();
		
		try {
			Session session=sf.getCurrentSession();
			session.beginTransaction();
			m = session.createQuery("from Entity_MesaCompuesta").list();
			session.flush();
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Error MesaDAO. Buscar Mesa");
		}
		
		return m;
	}
}