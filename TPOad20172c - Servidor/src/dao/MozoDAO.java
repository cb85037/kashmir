package dao;

import hibernate.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entity.Entity_Mesa;
import entity.Entity_Mozo;
import entity.Entity_Tarea;

public class MozoDAO {
	private static MozoDAO instancia = null;
	private static SessionFactory sf = null;
	private MozoDAO(){
		sf = HibernateUtil.getSessionFactory();
	}
	
	public static MozoDAO getInstancia(){
		if(instancia == null)
			instancia = new MozoDAO();
		return instancia;
	}
	
	public void updateMozo(Entity_Mozo mozo){
		Transaction t = null;

		try {
			Session s = sf.getCurrentSession();
			t = s.beginTransaction();
			s.merge(mozo);
			s.flush();
			t.commit();
			
		} catch (Exception e) {
			t.rollback();
			System.out.println(e);
			System.out.println("ErrorDAO: Merge Mozo");
		}
	}

	public List<Entity_Mozo> listarMozos() {
		Session s = null;
		List<Entity_Mozo> lista = null;
		
		try{
			s = sf.getCurrentSession();
			Transaction t = s.beginTransaction();
			
			lista = s.createQuery("from Entity_Mozo").list();
			t.commit();
			
		}catch(Exception e){
			System.out.println(e);
			System.out.println("ErrorDAO: TareaDAO.ListarMozos");
		}
		
		return lista;
	}

	public List<Entity_Mozo> listarMozosDeSucursal(String nombre) {
		Session s = null;
		List<Entity_Mozo> lista = null;
		
		try{
			s = sf.getCurrentSession();
			Transaction t = s.beginTransaction();
			
			Query q = s.createQuery("select t from Mozo t.sucursal.nombre = :name"); 
			q.setParameter("name", nombre);
			lista = q.list();
			
			t.commit();
			
		}catch(Exception e){
			System.out.println(e);
			System.out.println("ErrorDAO: TareaDAO.listarMozosDeSucursal");
		}
		
		return lista;
	}
	public Entity_Mozo buscarMozoCodigo(String legajo){
		Session s = null;
		Entity_Mozo m = null;
		try{
			s = sf.getCurrentSession();
			Transaction t = s.beginTransaction();
			
			Query q = s.createQuery("select m from Entity_Mozo m where m.legajo = :leg"); 
			q.setParameter("leg", legajo);
			m = (Entity_Mozo) q.uniqueResult();
			t.commit();
			
		}catch(Exception e){
			System.out.println(e);
			System.out.println("ErrorDAO: MozoDAO.buscarMozoCodigo");
		}
		
		return m;
	}
	
	@SuppressWarnings("unchecked")
	public List<Entity_Mesa> listarMesasPorMozo(String legajo){
		Session s = null;
		List<Entity_Mesa> mesas = new ArrayList<Entity_Mesa>();
		try{
			s = sf.getCurrentSession();
//			Transaction t = s.beginTransaction();
			
			Query q = s.createQuery("select II from Entity_Mozo I join I.mesasAsignadas II where I.legajo =:leg"); 
			q.setParameter("leg", legajo);
			mesas = q.list();
//			t.commit();
			
		}catch(Exception e){
			System.out.println(e);
			System.out.println("ErrorDAO: MozoDAO.listarMesasPorMozo");
		}
		
		return mesas;
		
	}
	
	
	public void altaMozo(Entity_Mozo mozo) {
		// TODO Auto-generated method stub
		
	}
}
