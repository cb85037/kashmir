package administracion;

import java.util.ArrayList;
import java.util.List;

import dao.RecetaDAO;
import dto.DTO_Receta;
import entity.Entity_Receta;

public class Gestor_Receta {
	private RecetaDAO dao= RecetaDAO.getInstancia();
	private static Gestor_Receta instancia;
	
	private Gestor_Receta() {
	
	}
	public static Gestor_Receta getInstancia(){
		if (instancia==null)
			return instancia=new Gestor_Receta();
		return instancia;
	}
	
	public void altaReceta(DTO_Receta r){
		//Modificar la creacion, no tiene sentido crear una Receta sin ITEMS
		//Receta receta = new Receta(r.getNumero(),r.getNombre(), r.getDescripcion());
		Entity_Receta receta = new Entity_Receta();
		dao.altaReceta(receta);
	}
	public List<DTO_Receta> listarRecetas() {
		List<Entity_Receta> lista = dao.listarRecetas();
		List<DTO_Receta> result = new ArrayList<DTO_Receta>();
		
		//por cada receta la agrega al vector
		for(Entity_Receta r: lista){
			result.add(this.toDTO(r));
		}
		
		return result;
		
	}

	public DTO_Receta toDTO(Entity_Receta r){
		DTO_Receta rec = new DTO_Receta();
		rec.setNombre(r.getNombre());
		rec.setNumero(r.getNumero());
		rec.setDescripcion(r.getDescripcion());
		return rec;
	}
	
	
}
