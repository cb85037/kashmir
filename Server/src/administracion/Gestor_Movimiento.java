package administracion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.AdministracionCentralDAO;
import dao.DepositoDAO;
import dao.EmpleadoDAO;
import dao.LoteDAO;
import dao.MovimientoDAO;
import dao.StockProductoDAO;
import dto.DTO_Empleado;
import entity.Entity_AdministracionCentral;
import entity.Entity_Deposito;
import entity.Entity_DepositoArea;
import entity.Entity_DepositoLocal;
import entity.Entity_ElaboracionNoVenta;
import entity.Entity_Empleado;
import entity.Entity_Local;
import entity.Entity_Lote;
import entity.Entity_Movimiento;
import entity.Entity_StockProducto;


public class Gestor_Movimiento {

	private static Gestor_Movimiento instancia;
	private MovimientoDAO dao = MovimientoDAO.getInstancia();
	
	private Gestor_Movimiento() {
		
	}
	
	public static Gestor_Movimiento getInstancia(){
		if(instancia == null)
			instancia = new Gestor_Movimiento();
		return instancia;
	}

	public void altaMovimiento(Entity_Local sucursal, Entity_Empleado emp, Entity_ElaboracionNoVenta p, int cantidad) {
		
		List<Entity_Movimiento> mov = new ArrayList<Entity_Movimiento>();
		List<Entity_Movimiento> movSucursal = new ArrayList<Entity_Movimiento>();
		List<Entity_Lote> lotes = Gestor_Deposito.getInstancia().altaLotesPlanProduccion(p, cantidad);
		Entity_AdministracionCentral adm = AdministracionCentralDAO.getInstancia().getAdministracionCentral();
		Entity_Deposito d = adm.getDeposito();
		Entity_Deposito s = sucursal.getDeposito();
		int empty = 0;
		
		Entity_Movimiento m = new Entity_Movimiento();
		
		m.setArea(sucursal.getCocina().getDeposito());
		m.setDestino(sucursal.getDeposito());
		m.setEstado("baja");
		m.setFecha(new Date());
		m.setLotes(lotes);
		m.setMotivo("planDeProduccion");
		m.setOrigen(d);
		m.setPlanProduccion(true);
		m.setUsuario(adm.getEmpleado());
		
		mov = adm.getDeposito().getMovimientos();
		mov.add(m);
		//Agrego el movimiento de baja a la sucursal de origen
		adm.getDeposito().setMovimientos(mov);
		
		
		//Agrego el movimiento de alta de mercaderia a la sucursal destino
		movSucursal = s.getMovimientos();
		
		Entity_Movimiento m1 = new Entity_Movimiento();
		
		m1.setArea(sucursal.getCocina().getDeposito());
		m1.setDestino(s);
		m1.setEstado("alta");
		m1.setFecha(new Date());
		m1.setLotes(lotes);
		m1.setMotivo("planDeProduccion");
		m1.setOrigen(d);
		m1.setPlanProduccion(true);
		m1.setUsuario(adm.getEmpleado());
			
		movSucursal.add(m1);
		
		//Ahora lo que deberia hacer es actualizar los stocksProductos de la sucursal
		//con los respectivos lotes que fueron enviados por el movimiento.
		
		List<Entity_StockProducto> stock = Gestor_StockProducto.getInstancia().actualizarStockEntrante(s,m1);
		
		//Actualizo el stockProductos del Deposito
		for(Entity_StockProducto sp: stock){
			StockProductoDAO.getInstancia().merge(sp);
		}
		
		
		s.setMovimientos(movSucursal);
		
		DepositoDAO.getInstancia().merge(s);
		
		Entity_DepositoLocal depSucu = DepositoDAO.getInstancia().getDepositoSucursal(s.getIdDeposito());
		
		if(depSucu.getProductos().isEmpty()){
			empty = 1;
		}
		
		depSucu.setProductos(stock);
		
		if(empty == 1){
			DepositoDAO.getInstancia().merge(depSucu);
		}
		sucursal.setDeposito(depSucu);
		
		
		//SucursalManager.getInstancia().merge(sucursal);
		//sucursal.getDeposito().setProductos(stock);
		//sucursal.getDeposito().setMovimientos(movSucursal);
		
	}

	public void despacharMovSucursal(String idMovimiento, DTO_Empleado e) {
		
		int id = Integer.valueOf(idMovimiento);
		Entity_Movimiento m = dao.getMovimiento(id);
		Entity_Empleado empleado = EmpleadoDAO.getInstancia().getEmpleadoLegajo(e.getLegajo());
		
		//Este es el movimiento destino
		Entity_Movimiento d = new Entity_Movimiento();
		
		d.setOrigen(m.getDestino());
		d.setDestino(m.getArea());
		d.setArea(m.getArea());
		d.setEstado("alta");
		d.setFecha(new Date());
		d.setMotivo(m.getMotivo());
		d.setPlanProduccion(m.isPlanProduccion());
		d.setUsuario(empleado);
		//Le seteo los lotes de ese movimiento
		List<Entity_Lote> lotes = LoteDAO.getInstancia().getLotesMovimiento(m);
		d.setLotes(lotes);
		
		
		
		m.setEstado("baja");
		//Saco los lotes de ese movimiento
		m.setLotes(null);
		dao.updateMovimiento(m);
		
		//dao.save(d);
		
		Entity_DepositoArea deposito = DepositoDAO.getInstancia().getDepositoArea(d.getArea().getIdDeposito());
		
		List<Entity_Movimiento> mov = deposito.getMovimientos();
		mov.add(d);
		
		//Seteo los movimientos
		deposito.setMovimientos(mov);
		
		
		//Actualizo el stock del deposito con los lotes que vinieron en el movimiento
		List<Entity_StockProducto> stock = Gestor_StockProducto.getInstancia().actualizarStockEntrante(deposito,d);
		
		for(Entity_StockProducto sp: stock){
			StockProductoDAO.getInstancia().merge(sp);
		}
		
		int empty = 0;
		if(deposito.getProductos().isEmpty()){
			empty = 1;
		}
		
		deposito.setProductos(stock);
		
		if(empty == 1){
			DepositoDAO.getInstancia().merge(deposito);
		}
		
		
		
		
	}
	
}
