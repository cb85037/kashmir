package dao;

import hibernate.HibernateUtil;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entity.Entity_PlanProdSemiElab;

public class PlanProdSemiElabDAO {

	private static PlanProdSemiElabDAO instancia = null;
	private static SessionFactory sf = null;
	
	private PlanProdSemiElabDAO(){
		sf = HibernateUtil.getSessionFactory();
	}
	
	public static PlanProdSemiElabDAO getInstancia(){
		if(instancia == null)
			instancia = new PlanProdSemiElabDAO();
		return instancia;
	}

	public Entity_PlanProdSemiElab getPlanProduccion() {
		
		Session s = null;
		Entity_PlanProdSemiElab result = null;
		try{
			s = sf.getCurrentSession();
			Transaction t = s.beginTransaction();
			
			Query q = s.createQuery("from Entity_PlanProdSemiElab p where p.estado = :identificador");
			q.setParameter("identificador", "activo");
			result = (Entity_PlanProdSemiElab) q.uniqueResult();
			t.commit();
		}catch(Exception e){
			System.out.println(e);
			System.out.println("ErrorDAO: PlanProduccionDAO.getPlanProduccion");
		}
		
		return result;
	}

	public void update(Entity_PlanProdSemiElab p) {
			Transaction t = null;
			Session s = sf.getCurrentSession();
			try {
				t = s.beginTransaction();

				s.merge(p);
				t.commit();
				
			} catch (Exception e) {
				t.rollback();
				System.out.println(e);
				System.out.println("ErrorDAO: Update PlanProduccion");
			}
		}
}
