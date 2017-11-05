package administracion;

import java.util.ArrayList;
import java.util.List;

import dao.TareaDAO;
import dto.DTO_Tarea;
import entity.Entity_Tarea;


public class Gestor_Tarea {

	private static Gestor_Tarea instancia;
	private TareaDAO dao = TareaDAO.getInstancia();
	
	private Gestor_Tarea() {
		
	}
	
	public static Gestor_Tarea getInstancia(){
		if(instancia == null)
			instancia = new Gestor_Tarea();
		return instancia;
	}

	public List<DTO_Tarea> listarTareasActivas() {
		List<Entity_Tarea> lista = dao.listarTareas();
		List<DTO_Tarea> result = new ArrayList<DTO_Tarea>();
		
		for(Entity_Tarea t: lista){
			if(t.getEstado().equalsIgnoreCase("activa")){
				result.add(t.getDTO());
			}
			
		}
		return result;
	}

	public List<DTO_Tarea> listarTareasDeSucursal(String nombre) {
		List<Entity_Tarea> lista = dao.listarTareasDeSucursal(nombre);
		List<DTO_Tarea> result = new ArrayList<DTO_Tarea>();
		
		for(Entity_Tarea t: lista){
			result.add(t.getDTO());
		}
		return result;
	}

	
	
}
