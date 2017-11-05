package dao;

import java.util.List;

import hibernate.HibernateUtil;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

import dto.DTO_Mozo;
import entity.Entity_MesaSimple;
import entity.Entity_Sector;

public class SectorDAO {
	
	private static SectorDAO instancia=null;
	private SessionFactory sf=null;
		
	public SectorDAO() {
		sf=HibernateUtil.getSessionFactory();	
	}

	public static SectorDAO getInstancia(){
		if (instancia==null){
			return instancia=new SectorDAO();
		}
		return instancia;
	}
	
	public void altaSector(Entity_Sector sector){
		try {
			Session session=sf.openSession();
			session.beginTransaction();
			session.save(sector);
			session.flush();
			session.getTransaction().commit();
			session.close();
		} catch (Exception e){
			System.out.println(e);
			System.out.println("Error SectorDAO. Alta Sector");
		}
	}
	
	public Entity_Sector buscarSector(int idSector){
		Entity_Sector s=new Entity_Sector();
		
		try {
			Session session=sf.openSession();
			session.beginTransaction();
			s= (Entity_Sector) session.createQuery("from Entity_Sector where idSector=:idSector").setParameter("idSector", idSector).uniqueResult();
			session.flush();
			session.close();
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Error SectorDAO. Buscar Sector");
		}
		
		return s;
	}

	public Entity_Sector buscarSectorMozo(DTO_Mozo mozo) {
		Entity_Sector s = null;
		
		try {
			Session session=sf.openSession();
			session.beginTransaction();
			Query q = session.createQuery("Select s from Entity_Sector s inner join s.mozos m where m.legajo = :legajo");
			q.setParameter("legajo", mozo.getLegajo());
			s = (Entity_Sector) q.uniqueResult();
			session.flush();
			session.close();
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Error SectorDAO. Buscar Sector del Mozo");
		}
		
		return s;
	}	
}
