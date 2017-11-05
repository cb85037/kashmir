package administracion;

import dao.AdministracionCentralDAO;
import entity.Entity_ElaboracionNoVenta;



public class Gestor_AdministracionCentral {

	private static Gestor_AdministracionCentral instancia;
	private AdministracionCentralDAO dao = AdministracionCentralDAO.getInstancia();
	
	private Gestor_AdministracionCentral() {
		
	}
	
	public static Gestor_AdministracionCentral getInstancia(){
		if(instancia == null)
			instancia = new Gestor_AdministracionCentral();
		return instancia;
	}
	
	public boolean verificarStockDepositoCentral(Entity_ElaboracionNoVenta p, int cantidad){
		
		boolean resp = Gestor_Deposito.getInstancia().verificarStockDepositoCentral(p,cantidad);
		
		return resp;
	}
	
}
