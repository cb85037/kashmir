package administracion;

import java.util.ArrayList;
import java.util.List;

import dao.EmpleadoDAO;
import dao.PlatoDAO;
import dao.LocalDAO;
import dto.DTO_Empleado;
import dto.DTO_Plato;
import dto.DTO_RubroCarta;
import entity.Entity_Carta;
import entity.Entity_Empleado;
import entity.Entity_Plato;
import entity.Entity_RubroCarta;
import entity.Entity_Local;



public class Gestor_Plato {
	private PlatoDAO dao= PlatoDAO.getInstancia();
	private static Gestor_Plato instancia;

	//singleton
	public static Gestor_Plato getInstancia() {
		if(instancia == null)
			instancia = new Gestor_Plato();
		return instancia;
	}

	public DTO_Plato buscarPlato(DTO_Plato plato){
		Entity_Plato p=dao.buscarPlatoCodigo(plato);
		
		return p.getDTO();
		
	}
	
	public List<DTO_RubroCarta> listarPlatosRubro(DTO_Empleado emp) {
		
		Entity_Local e = LocalDAO.getInstancia().buscarSucursalEmpleado(emp);
		List<Entity_RubroCarta> lista = e.getCarta().getRubros();
		List<DTO_RubroCarta> res = new ArrayList<DTO_RubroCarta>();
		
		for(Entity_RubroCarta r: lista){
			res.add(r.getDTO());
		}
		
		return res;
		
	}

		
}
