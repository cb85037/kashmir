package server;

import hibernate.HibernateUtil;

public class test {

	//Main del servidor
		public static void main(String[] args)
		{
			HibernateUtil.getSessionFactory().openSession();
			HibernateUtil.getSessionFactory().close();
		}
	
}
