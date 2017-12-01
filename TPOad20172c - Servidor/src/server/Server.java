package server;

import hibernate.HibernateUtil;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jdbc.ConnectionManager;

import Controlador.*;
import repositorio.*;

package server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import org.hibernate.Hibernate;

import rmi.RecursosRMI;
import hibernate.HibernateUtil;
import interfaces.InterfazRemotaAdministracion;

public class Server {

	InterfazRemotaAdministracion objetoRemoto;
	
	//Constructor del servidor
	public Server() {
		iniciar();
	}
	
	//Main del servidor
	public static void main(String[] args)
	{
		new Server();
	}
	
	 public void iniciar() {
	    	try {
	    		LocateRegistry.createRegistry(1099);	
	            objetoRemoto = new RecursosRMI();
	            //Vincula el objeto con un nombre en el registry
	            Naming.rebind (InterfazRemotaAdministracion.url, objetoRemoto);
	            System.out.println("Servidor inicializado correctamente...");
			} catch (Exception e) {
				e.printStackTrace();
			}
	 }
}