package administracion;

import java.util.ArrayList;
import java.util.List;

import dao.StockProductoDAO;
import entity.Entity_Deposito;
import entity.Entity_Lote;
import entity.Entity_Movimiento;
import entity.Entity_StockProducto;


public class Gestor_StockProducto {

	private static Gestor_StockProducto instancia;
	private StockProductoDAO dao = StockProductoDAO.getInstancia();
	
	private Gestor_StockProducto() {
		
	}
	
	public static Gestor_StockProducto getInstancia(){
		if(instancia == null)
			instancia = new Gestor_StockProducto();
		return instancia;
	}

	public List<Entity_StockProducto> actualizarStockEntrante(Entity_Deposito deposito, Entity_Movimiento m) {
		
		List<Entity_StockProducto> stockExistente = new ArrayList<Entity_StockProducto>();
		List<Entity_StockProducto> stockResultante = new ArrayList<Entity_StockProducto>();
		int lotesAsignados = 0;
		List<Entity_Lote> lotes = m.getLotes();
		List<Entity_Lote> lotesAux = new ArrayList<Entity_Lote>();
		
		if(!(deposito.getProductos().isEmpty())){
			stockExistente = deposito.getProductos();
		}
			
		for(Entity_Lote l: lotes){
			
			if(!(stockExistente.isEmpty())){
				
				for(Entity_StockProducto st1: stockExistente ){
					
					if(l.getProducto().getCodigo() == st1.getProducto().getCodigo()){ //Si el stockProducto existe
						
						lotesAux = st1.getLotes();
						lotesAux.add(l);
						st1.setLotes(lotesAux);
						
						stockResultante.add(st1);
						lotesAsignados++;
						
					}
					
				}
				
			}else{
				
				lotesAux = new ArrayList<Entity_Lote>();
				
				lotesAux.add(l);
				Entity_StockProducto sp = new Entity_StockProducto();
				sp.setProducto(l.getProducto());
				sp.setStockMinimo(100);
				sp.setLotes(lotesAux);
				
				StockProductoDAO.getInstancia().save(sp);
				
				stockResultante.add(sp);
				stockExistente.add(sp);
				lotesAsignados++;
			}
				
		}
	
		// Si queda alguno sin asignar
		if(lotesAsignados != lotes.size()){
			for(Entity_Lote l: lotes){
					int generado = 0;
					for(Entity_StockProducto st1: stockExistente ){
						
						if(l.getProducto().getCodigo() == st1.getProducto().getCodigo()){ //Si el stockProducto existe
							
							lotesAux = st1.getLotes();
							lotesAux.add(l);
							st1.setLotes(lotesAux);
							
							stockResultante.add(st1);
							
							generado = 1;
							
						}
					
					}
					
					if(generado != 1){
						
						lotesAux = new ArrayList<Entity_Lote>();
						
						lotesAux.add(l);
						Entity_StockProducto sp = new Entity_StockProducto();
						sp.setProducto(l.getProducto());
						sp.setStockMinimo(100);
						sp.setLotes(lotesAux);
						
						StockProductoDAO.getInstancia().save(sp);
						
						stockResultante.add(sp);
						stockExistente.add(sp);
					}
						
			}
		}
		
		return stockResultante;
	}

		
		
	
}
