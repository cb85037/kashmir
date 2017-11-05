package administracion;

import java.util.ArrayList;
import java.util.List;

import auxiliares.ProductoCantidad;
import dao.DepositoDAO;
import dao.LocalDAO;
import dao.LoteDAO;
import dao.MozoDAO;
import dao.PlatoDAO;
import dao.StockProductoDAO;
import dto.DTO_Empleado;
import dto.DTO_Plato;
import entity.Entity_Deposito;
import entity.Entity_DepositoCentral;
import entity.Entity_ElaboracionNoVenta;
import entity.Entity_ElaboracionVenta;
import entity.Entity_ItemReceta;
import entity.Entity_Local;
import entity.Entity_Lote;
import entity.Entity_Mozo;
import entity.Entity_Plato;
import entity.Entity_Receta;
import entity.Entity_StockProducto;

public class Gestor_Deposito {

	private static Gestor_Deposito instancia;
	private DepositoDAO dao = DepositoDAO.getInstancia();
	
	private Gestor_Deposito() {
		
	}
	
	public static Gestor_Deposito getInstancia(){
		if(instancia == null)
			instancia = new Gestor_Deposito();
		return instancia;
	}


	public boolean verificarStockDepositoCentral(Entity_ElaboracionNoVenta p, int cantidad) {
		
		Entity_DepositoCentral d = dao.getInstancia().getDepositoCentral();
		Entity_ElaboracionVenta ev = null;
		int cantProductos = 0;
		
		//Recupero los productos con sus stocks
		List<Entity_StockProducto> stock = StockProductoDAO.getInstancia().getStockProducto(d);
		
		//Genero una lista para llenarla con todos los productos y sus cantidades necesarias para la receta
		List<ProductoCantidad> prod = new ArrayList<ProductoCantidad>();
		
		// Buscar el producto de cada ItemReceta y cuanta cantidad necesito de cada uno
		for(Entity_ItemReceta item : p.getReceta().getItems()){
			
			ProductoCantidad pc = new ProductoCantidad();
			pc.setProducto(item.getProducto());
			pc.setCantidad(item.getCantidad());
			prod.add(pc);
			
			if(item.getProducto() instanceof Entity_ElaboracionNoVenta){ //tiene receta, tengo que llegar al producto base
				Entity_Receta r = ((Entity_ElaboracionNoVenta) item.getProducto()).getReceta();
				for(Entity_ItemReceta item2 : r.getItems()){
					pc.setProducto(item2.getProducto());
					pc.setCantidad(item2.getCantidad());
					prod.add(pc);
				}
			}else{
				prod.add(pc);
			}
		}
		//Para cada stock que tengo en el deposito central checkeo que tenga la cantidad que necesito para el plan de prod
		for(Entity_StockProducto sp: stock){
			for(ProductoCantidad pr: prod){
				if(sp.getProducto().getCodigo() == pr.getProducto().getCodigo()){
					for(Entity_Lote l: sp.getLotes()){
						if(l.getCantidad() >= (pr.getCantidad()*cantidad)){ //tengo stock para ese producto
							cantProductos ++;
						}
						else{
							return false;	
						}
					}
				}
			}
		}
		
		return true;
	}

	public List<Entity_Lote> altaLotesPlanProduccion(Entity_ElaboracionNoVenta p, int cantidad) {
		
List<Entity_Lote> lotes = new ArrayList<Entity_Lote>();
		
		Entity_DepositoCentral d = dao.getInstancia().getDepositoCentral();
		
		//Recupero los productos con sus stocks
		List<Entity_StockProducto> stock = StockProductoDAO.getInstancia().getStockProducto(d);
		
		//Genero una lista para llenarla con todos los productos y sus cantidades necesarias para la receta
		List<ProductoCantidad> prod = new ArrayList<ProductoCantidad>();
		
		// Buscar el producto de cada ItemReceta y verificar su disponibilidad
		for(Entity_ItemReceta item : p.getReceta().getItems()){
			
			ProductoCantidad pc = new ProductoCantidad();
			pc.setProducto(item.getProducto());
			pc.setCantidad(item.getCantidad());
			prod.add(pc);
		}
		
		for(Entity_StockProducto sp: stock){
			
			for(ProductoCantidad pr: prod){
				
				if(sp.getProducto().getCodigo() == pr.getProducto().getCodigo()){
					for(Entity_Lote l: sp.getLotes()){
						if(l.getCantidad() >= (pr.getCantidad()*cantidad)){ //tengo stock para ese producto
							
							Entity_Lote lot = new Entity_Lote();
							lot.setCantidad(pr.getCantidad()*cantidad);
							lot.setNumero(l.getNumero());
							lot.setPlanProduccion(true);
							lot.setProducto(pr.getProducto());
							lot.setVencimiento(l.getVencimiento());
							lot.setIdLote((LoteDAO.getInstancia().getID()) + 1);
							lotes.add(lot);
							
							LoteDAO.getInstancia().save(lot);
							
							l.setCantidad(l.getCantidad() - pr.getCantidad()*cantidad);
							LoteDAO.getInstancia().update(l);
							
						}
					}
				}
			}
		}
		
		return lotes;
		
	}
	
	public boolean verificarStockDeposito(DTO_Plato plato, int cantidad, DTO_Empleado e) {
		
		int cantProductos = 0;
		Entity_Deposito d = new Entity_Deposito();
		Entity_Plato p = PlatoDAO.getInstancia().buscarPlatoCodigo(plato);
		
		Entity_Mozo m = null;
		m = MozoDAO.getInstancia().buscarMozoCodigo(e.getLegajo());
		
		if(m == null){
			if(p.getRubro().equalsIgnoreCase("cocina")){
				Entity_Local s = LocalDAO.getInstancia().buscarSucursalEmpleadoCocina(e);
				d = s.getCocina().getDeposito();
			}else if(p.getRubro().equalsIgnoreCase("cafeteria")){
				Entity_Local s = LocalDAO.getInstancia().buscarSucursalEmpleadoCafeteria(e);
				d = s.getCafeteria().getDeposito();
			}else if(p.getRubro().equalsIgnoreCase("barra")){
				Entity_Local s = LocalDAO.getInstancia().buscarSucursalEmpleadoBarra(e);
				d = s.getBarra().getDeposito();
			}
		}else{
			if(p.getRubro().equalsIgnoreCase("cocina")){
				Entity_Local s = LocalDAO.getInstancia().buscarSucursalEmpleado(e);
				d = s.getCocina().getDeposito();
			}else if(p.getRubro().equalsIgnoreCase("cafeteria")){
				Entity_Local s = LocalDAO.getInstancia().buscarSucursalEmpleado(e);
				d = s.getCafeteria().getDeposito();
			}else if(p.getRubro().equalsIgnoreCase("barra")){
				Entity_Local s = LocalDAO.getInstancia().buscarSucursalEmpleado(e);
				d = s.getBarra().getDeposito();
			}
		}
		
		//Recupero los productos con sus stocks
		List<Entity_StockProducto> stock = StockProductoDAO.getInstancia().getStockProductoArea(d);
		
		//Genero una lista para llenarla con todos los productos y sus cantidades necesarias para la receta
		List<ProductoCantidad> prod = new ArrayList<ProductoCantidad>();
		
		// Buscar el producto de cada ItemReceta y verificar su disponibilidad
		
		if(p.getReceta() != null)
			for(Entity_ItemReceta item : p.getReceta().getItems()){
				
				ProductoCantidad pc = new ProductoCantidad();
				pc.setProducto(item.getProducto());
				pc.setCantidad(item.getCantidad());
				prod.add(pc);
				
			}
		else{
			ProductoCantidad pc = new ProductoCantidad();
			pc.setProducto(p.getProducto());
			pc.setCantidad(cantidad);
			prod.add(pc);
		}
		
		for(Entity_StockProducto sp: stock){
			
			for(ProductoCantidad pr: prod){
				// me fijo si sumando la cantidad de los lotes me alcanza para el pedido.
				if(sp.getProducto().getCodigo() == pr.getProducto().getCodigo()){
					int cantidadTotal = 0;
					for(Entity_Lote l: sp.getLotes()){
						cantidadTotal = cantidadTotal + l.getCantidad();
					}
					if(cantidadTotal >= (pr.getCantidad()*cantidad)){ //tengo stock para ese producto
						cantProductos ++;
					}
				}
			}
		}
		
		return cantProductos == prod.size();
	}

	public void prepararItemComanda(DTO_Plato plato, int cantidad, DTO_Empleado e) {
		
		Entity_Deposito d = new Entity_Deposito();
		Entity_Plato p = PlatoDAO.getInstancia().buscarPlatoCodigo(plato);
		
		if(p.getRubro().equalsIgnoreCase("cocina")){
			Entity_Local s = LocalDAO.getInstancia().buscarSucursalEmpleadoCocina(e);
			d = s.getCocina().getDeposito();
		}else if(p.getRubro().equalsIgnoreCase("cafeteria")){
			Entity_Local s = LocalDAO.getInstancia().buscarSucursalEmpleadoCafeteria(e);
			d = s.getCafeteria().getDeposito();
		}else if(p.getRubro().equalsIgnoreCase("barra")){
			Entity_Local s = LocalDAO.getInstancia().buscarSucursalEmpleadoBarra(e);
			d = s.getBarra().getDeposito();
		}
		
		//Recupero los productos con sus stocks
		List<Entity_StockProducto> stock = StockProductoDAO.getInstancia().getStockProductoArea(d);
		
		//Genero una lista para llenarla con todos los productos y sus cantidades necesarias para la receta
		List<ProductoCantidad> prod = new ArrayList<ProductoCantidad>();
		
		// Buscar el producto de cada ItemReceta y verificar su disponibilidad
		for(Entity_ItemReceta item : p.getReceta().getItems()){
			
			ProductoCantidad pc = new ProductoCantidad();
			pc.setProducto(item.getProducto());
			pc.setCantidad(item.getCantidad());
			prod.add(pc);
			
		}
		
		for(Entity_StockProducto sp: stock){
			
			for(ProductoCantidad pr: prod){
				
				if(sp.getProducto().getCodigo() == pr.getProducto().getCodigo()){
					
					int cantConsumida = 0;
					
					for(Entity_Lote l: sp.getLotes()){
						
						cantConsumida = cantConsumida + l.getCantidad();
						
						if(cantConsumida < pr.getCantidad()*cantidad){ //Tengo que agarrar del siguiente lote
							
							l.setCantidad(0);
							LoteDAO.getInstancia().update(l);
							
						}else if(cantConsumida >= pr.getCantidad()*cantidad){// Con lo que tengo me alcanza
							
							l.setCantidad(cantConsumida - pr.getCantidad()*cantidad);
							LoteDAO.getInstancia().update(l);
						}
						
					}
					
				}
			}
		}
		
		
	}

	
}
