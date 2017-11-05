package dao;

import hibernate.HibernateUtil;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import dto.DTO_Plato;
import entity.Entity_Mozo;
import entity.Entity_Plato;
import entity.Entity_Receta;

public class PlatoDAO {
	private static PlatoDAO instancia = null;	//singleton
	private static SessionFactory sf = null; //hibernate

	
	private PlatoDAO(){
		sf = HibernateUtil.getSessionFactory();
	}
	
	//singleton
	public static PlatoDAO getInstancia() {
		if(instancia == null)
			instancia = new PlatoDAO();
		return instancia;
	}
	
	
	
	public Entity_Plato buscarPlatoCodigo(DTO_Plato plato){
		Session s = null;
		Entity_Plato p = null;
		try{
			s = sf.getCurrentSession();
			Transaction t = s.beginTransaction();
			Query q = s.createQuery("Select p from Entity_Plato p where p.codigo = :cod"); 
			q.setParameter("cod", plato.getCodigo());
			p = (Entity_Plato) q.uniqueResult();
			t.commit();
			
		}catch(Exception e){
			System.out.println(e);
			System.out.println("ErrorDAO: PlatoDAO.buscarPlatoCodigo");
		}
		
		return p;
	}

	
	public List<Entity_Plato> listarPlatos() {
		Session s = null;
		List<Entity_Plato> lista = null;
		
		try{
			s = sf.getCurrentSession();
			Transaction t = s.beginTransaction();
			
			lista = s.createQuery("from Entity_Plato").list();
			t.commit();
			
		}catch(Exception e){
			System.out.println(e);
			System.out.println("ErrorDAO: PlatoDAO.ListarPlatos");
		}
		
		return lista;
	}
}
