package administracion;

import java.util.ArrayList;
import java.util.List;

import dao.ProductoDAO;
import dto.DTO_Producto;
import entity.Entity_ElaboracionNoVenta;
import entity.Entity_ElaboracionVenta;
import entity.Entity_Producto;

public class Gestor_Producto {

	private static Gestor_Producto instancia;
	private ProductoDAO dao = ProductoDAO.getInstancia();
	
	private Gestor_Producto() {
		
	}
	
	public static Gestor_Producto getInstancia(){
		if(instancia == null)
			instancia = new Gestor_Producto();
		return instancia;
	}

	public List<DTO_Producto> listarProductos() {
		List<Entity_Producto> lista = dao.listarProductos();
		List<DTO_Producto> result = new ArrayList<DTO_Producto>();
		
		for(Entity_Producto p: lista){
			result.add(p.getDTO());
		}
		return result;
	}

	public List<DTO_Producto> listarProductosElaborados() {
		List<Entity_Producto> lista = dao.listarProductos();
		List<DTO_Producto> result = new ArrayList<DTO_Producto>();
		
		for(Entity_Producto p: lista){
			if(p instanceof Entity_ElaboracionVenta || p instanceof Entity_ElaboracionNoVenta){
				result.add(p.getDTO());
			}
			
		}
		return result;
	}
	
	public List<DTO_Producto> listarProductosElabNoVenta() {
		List<Entity_Producto> lista = dao.listarProductosParaPlanProd();
		List<DTO_Producto> result = new ArrayList<DTO_Producto>();
		
		for(Entity_Producto p: lista){
			//if(p instanceof Entity_ElaboracionNoVenta){
				result.add(p.getDTO());
			//}
			
		}
		return result;
	}
}

