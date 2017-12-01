package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


import entity.Entity_Receta;


public class RecetaDAO {
	private static RecetaDAO instancia = null;	//singleton
	private static SessionFactory sf = null; //hibernate
	
	
	//singleton
	public static RecetaDAO getInstancia() {
		if(instancia == null)
			instancia = new RecetaDAO();
		return instancia;
	}



	public void altaReceta(Entity_Receta receta) {
		try{
			/*Abro sesion y grabo el objeto*/
			Session session = sf.openSession();
			session.beginTransaction();
			session.save(receta);
			session.flush();
			session.getTransaction().commit();
			session.close();
		}catch(Exception e){
			System.out.println(e);
			System.out.println("ErrorDAO: Error al Insertar Receta");
		}
		
	}



	public List<Entity_Receta> listarRecetas() {
		Session s = null;
		List<Entity_Receta> lista = null;
		
		try{
			s = sf.getCurrentSession();
			Transaction t = s.beginTransaction();
			
			lista = s.createQuery("from Entity_Receta").list();
			t.commit();
			
		}catch(Exception e){
			System.out.println(e);
			System.out.println("ErrorDAO: RecetaDAO.ListarRecetas");
		}
		
		return lista;
	}
	

}
