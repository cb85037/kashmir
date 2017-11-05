package administracion;

import java.rmi.RemoteException;

import dao.BarraDAO;
import dto.DTO_Barra;
import dto.DTO_Local;
import entity.Entity_Barra;
import entity.Entity_Local;

public class Gestor_Barra {

	private static Gestor_Barra instancia;
	private BarraDAO dao = BarraDAO.getInstancia();
	
	private Gestor_Barra() {
		
	}
	
	public static Gestor_Barra getInstancia(){
		if(instancia == null)
			instancia = new Gestor_Barra();
		return instancia;
	}

	public void altaBarra(DTO_Local s, DTO_Barra b) throws RemoteException {
		Entity_Local sucursal = Gestor_Local.getInstancia().buscarSucursalNombre(s);
		
		if(sucursal != null){
			
			Entity_Barra ba = new Entity_Barra();
			ba.setNombre(b.getNombre());
			//ojo que es un Ba no un b
			b.setEmpleado(b.getEmpleado());
			b.setDeposito(b.getDeposito());
			
			dao.altaBarra(ba);
			
			sucursal.setBarra(ba);
			Gestor_Local.getInstancia().merge(sucursal);
		}
		
		
	}

	
	
	
	
	
}
