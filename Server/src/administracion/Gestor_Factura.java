package administracion;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.CajaDAO;
import dao.CajeroDAO;
import dao.FacturaDAO;
import dao.LocalDAO;
import dao.MesaDAO;
import dao.MozoDAO;
import dto.DTO_Encargado;
import dto.DTO_Factura;
import dto.DTO_ItemFactura;
import dto.DTO_Mesa;
import entity.Entity_CajaDiaria;
import entity.Entity_Encargado;
import entity.Entity_Factura;
import entity.Entity_ItemComanda;
import entity.Entity_ItemFactura;
import entity.Entity_Liquidacion;
import entity.Entity_Local;
import entity.Entity_Mesa;
import entity.Entity_Mozo;

public class Gestor_Factura {

	private static Gestor_Factura instancia;
	private FacturaDAO dao = FacturaDAO.getInstancia();
		
	public Gestor_Factura() {
		super();
	}
		
	public static Gestor_Factura getInstancia(){
		if(instancia == null)
			instancia = new Gestor_Factura();
		return instancia;
	}
	
	public DTO_Factura altaFactura(DTO_Mesa m, DTO_Encargado ca) throws RemoteException {
		Entity_Encargado caj =CajeroDAO.getInstancia().buscarCajeroCodigo(ca.getLegajo());
		Entity_Mesa mes= MesaDAO.getInstancia().buscarMesa(m.getIdMesa());
		Entity_CajaDiaria cajaDiaria = CajaDAO.getInstancia().buscarPorEncargado(ca.getLegajo());
		if(mes == null){
			mes = MesaDAO.getInstancia().buscarMesaCompuesta(m.getIdMesa());
		}
		
		Entity_Factura f=new Entity_Factura();
		f.setEmpleado(caj);
		f.setMesa(mes);
		f.setFecha(new Date());
		//genero un item factura por cada item comanda.
		List<Entity_ItemFactura> itemN=new ArrayList<Entity_ItemFactura>();
		
		float total=0;
		for(Entity_ItemComanda i: mes.getComanda().getItems()){
			Entity_ItemFactura it = new Entity_ItemFactura();
			it.setCantidad(i.getCantidad());
			it.setPrecio(i.getPlato().getPrecio()*it.getCantidad());
			it.setProducto(i.getPlato());
			itemN.add(it);
			total=total+it.getPrecio();
		}
		f.setTotal(total);
		f.setItems(itemN);
		
		FacturaDAO.getInstancia().altaFactura(f);
		DTO_Factura fact= new DTO_Factura();
		fact.setEmpleado(ca);
		fact.setFecha(f.getFecha());
		fact.setMesa(mes.getDTO()); 
		fact.setNumero(f.getNumero());
		fact.setTotal(f.getTotal());
		
		List<Entity_ItemFactura> item=new ArrayList<Entity_ItemFactura>();
		item=f.getItems();
		List<DTO_ItemFactura> itemsDTO=new ArrayList<DTO_ItemFactura>();
		for (Entity_ItemFactura i: item){
			DTO_ItemFactura itemDTO=new DTO_ItemFactura();
			itemDTO.setCantidad(i.getCantidad());
			itemDTO.setDescuento(0);
		//	i.setDescuento(0);
			itemDTO.setId(i.getId());
			itemDTO.setPrecio(i.getPrecio());
			itemDTO.setProducto(i.getProducto().getDTO());
			itemsDTO.add(itemDTO);
		}
		fact.setItems(itemsDTO);
		FacturaDAO.getInstancia().altaFactura(f);
		cajaDiaria.getFacturas().add(f);
		CajaDAO.getInstancia().mergeCajaDiaria(cajaDiaria);
		return fact;
	}
	
	public boolean generarLiquidaciones(DTO_Encargado emp){
		Entity_Local local = LocalDAO.getInstancia().buscarSucursalCajero(emp);
		Entity_CajaDiaria cajaDiria = CajaDAO.getInstancia().buscarPorEncargado(emp.getLegajo());
		List<Entity_Factura> facturas = cajaDiria.getFacturas();
		for(Entity_Factura f : facturas){
			float comision=0;
			for(Entity_ItemFactura i : f.getItems()){
				comision=comision+i.getPrecio()*i.getProducto().getComision();
			}
			Entity_Liquidacion l=new Entity_Liquidacion();
			l.setMonto(comision);
			l.setFecha(new Date());
			//FacturaDAO.getInstancia().altaLiquidacion(l);
			
			Entity_Mozo m= f.getMesa().getComanda().getMozo();
			List<Entity_Liquidacion> liqui= new ArrayList<Entity_Liquidacion>();
			liqui = m.getLiquidaciones();
			liqui.add(l);
			m.setLiquidaciones(liqui);
			MozoDAO.getInstancia().updateMozo(m);
		}
		return true;
	}
	
	public DTO_Factura confirmarFactura(DTO_Factura factura){
		Entity_Factura f =FacturaDAO.getInstancia().buscarFactura(factura.getNumero());
		f.setFecha(new Date());
		List <Entity_ItemFactura> items = f.getItems();
		//float comision=0;
		float total=0;
		for(Entity_ItemFactura i: items){
			i.setPrecio(i.getCantidad()*(1-i.getDescuento())*i.getProducto().getPrecio());
			//comision=comision+i.getPrecio()*i.getProducto().getComision();
			total=total+i.getPrecio();
		}
		//actualizo el total de la factura
		f.setTotal(total);
		
		//genero la liqui
		/*Entity_Liquidacion l=new Entity_Liquidacion();
		l.setMonto(comision);
		l.setFecha(new Date());*/
		
		//persistir esto
		FacturaDAO.getInstancia().updateFactura(f);
		//FacturaDAO.getInstancia().altaLiquidacion(l);
		
		
		
		//busco el mozo
		/*Entity_Mozo m= f.getMesa().getComanda().getMozo();
		List<Entity_Liquidacion> liqui= new ArrayList<Entity_Liquidacion>();
		liqui = m.getLiquidaciones();
		liqui.add(l);
		m.setLiquidaciones(liqui);
		MozoDAO.getInstancia().updateMozo(m);*/
		
		dao.updateFactura(f);
		
		//devuelvo una factura DTO
		DTO_Factura fact= new DTO_Factura();
		fact.setEmpleado((DTO_Encargado) f.getEmpleado().getDTO());
		fact.setFecha(f.getFecha());
		fact.setMesa(f.getMesa().getDTO());
		fact.setNumero(f.getNumero());
		fact.setTotal(f.getTotal());
		List<Entity_ItemFactura> item=new ArrayList<Entity_ItemFactura>();
		item=f.getItems();
		List<DTO_ItemFactura> itemsDTO=new ArrayList<DTO_ItemFactura>();
		for (Entity_ItemFactura i: item){
			DTO_ItemFactura itemDTO=new DTO_ItemFactura();
			itemDTO.setCantidad(i.getCantidad());
			i.setDescuento(0);
			itemDTO.setId(i.getId());
			itemDTO.setPrecio(i.getPrecio());
			itemDTO.setProducto(i.getProducto().getDTO());
			itemsDTO.add(itemDTO);
		}
		fact.setItems(itemsDTO);
		FacturaDAO.getInstancia().altaFactura(f);
		return fact;
		
		 
	}
//	//tengo que por cada dto actualizar su entity.
//	//no se como hacer para actualizar desde los dtos a los entitys de item factura que son los que tienen el dto.
//	public void actualizarFactura(FacturaDTO factura){
//		Factura f =FacturaDAO.getInstancia().buscarFactura(factura.getNumero());
//		List <ItemFacturaDTO> itemsDTO =factura.getItems();
//		
//		
//		float comision=0;
//		float total=0;
//		
//		factura.getItems();
//		
//		
//		//actualizo el total de la factura ojo que esta en cero 
//		f.setTotal(total);
//		dao.updateFactura(f);
//		 
//	}
//	
//	private Comanda buscarFacturaId(int numero) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
