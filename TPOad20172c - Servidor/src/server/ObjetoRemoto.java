package server;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.hibernate.mapping.Array;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import dao.*;
import exceptions.AreaPreparacionException;
import exceptions.CajaException;
import exceptions.EntityInvalidaException;
import exceptions.MozoException;

import Negocio.*;
import repositorio.*;

public class ObjetoRemoto extends UnicastRemoteObject implements TDAControladorRemoto{

	private static final long serialVersionUID = 1L;
	private ArrayList<Integer> sessionID = new ArrayList<Integer>();
	private int restauranteActual;
	private static Controller instancia;
	private static MozoDAO mozoDAO;
	private static SectorDAO sectorDAO;
	private static UsuarioDAO usuarioDAO;
	private static RolDAO rolDAO;
	private static RestauranteDAO restauranteDAO;
	private static TurnoDAO turnoDAO;
	private static CajeroDAO cajeroDAO;
	private static JefeAreaPreparacionDAO jefeAreaDAO;
	private static DepositoDAO depositoDAO;
	private static AreaDePreparacionDAO areaDAO;
	private static ProductoDAO prodDao;
	private static MesaDAO mesaDAO;
	private static ProveedorDAO provDAO;
	private static PlatoDAO platoDAO;
	private static ItemPlanDeProduccionDAO itemppDAO;
	private static OrdenDeCompraDAO ordenDeCompraDAO;
	private static ItemLoteCantidadDAO itemLoteCantidadDAO;
	private static DepositoCentralDAO depositoCentralDAO;
	private static ItemAReponerDAO itemAReponerDAO;
	private static EmpleadoDAO empleadoDAO;

	public static Controller getInstancia() throws RemoteException {
		if (instancia == null) {
			instancia = new Controller();
			mozoDAO = MozoDAO.getMozoDAO();
			usuarioDAO = UsuarioDAO.getUsuarioDAO();
			rolDAO = RolDAO.getRolDAO();
			restauranteDAO = RestauranteDAO.getRestauranteDAO();
			turnoDAO = TurnoDAO.getTurnoDAO();
			cajeroDAO = CajeroDAO.getCajeroDAO();
			jefeAreaDAO = JefeAreaPreparacionDAO.getJefeAreaPreparacionDAO();
			depositoDAO = DepositoDAO.getDepositoDAO();
			areaDAO = AreaDePreparacionDAO.getAreaDePreparacionDAO();
			prodDao = ProductoDAO.getProductoDAO();
			mesaDAO = MesaDAO.getMesaDAO();
			sectorDAO = SectorDAO.getSectorDAO();
			provDAO = ProveedorDAO.getProveedorDAO();
			platoDAO = PlatoDAO.getPlatoDAO();
			itemppDAO = ItemPlanDeProduccionDAO.getItemPlanDeProduccionDAO();
			ordenDeCompraDAO = OrdenDeCompraDAO.getOrdenDeCompra();
			itemLoteCantidadDAO = ItemLoteCantidadDAO.getItemLoteCantidadDAO();
			depositoCentralDAO = DepositoCentralDAO.getDepositoCentralDAO();
			itemAReponerDAO = ItemAReponerDAO.getItemLoteCantidadDAO();
			empleadoDAO = EmpleadoDAO.getEmpleadoDAO();
		}
		return instancia;
	}

	public Controller() throws RemoteException {
		restauranteActual = 0;
	}

	@Override
	public String AsignarMozoAUnSector(String mozo, String sector,
			int restaurante) throws RemoteException {
		try {
			Mozo m = Controller.mozoDAO.getByNombre(mozo);
			if (m == null) {
				return "No existe el mozo indicado";
			}

			Restaurante r = Controller.restauranteDAO.getById(restaurante);
			Sector s = Controller.sectorDAO.getByNombreYRestaurante(sector,
					restaurante);
			if (s == null) {
				return "No existe el sector solicitado para el restaurante";
			}

			if (m.getRestaurante().getId() != r.getId()) {
				return "El Mozo " + m.getNombreEmpleado()
						+ " no es empleado del restaurante";
			}

			if (s.addMozo(m)) {
				m.setSector(s);
				Controller.mozoDAO.save(m);
			} else {
				return "El sector no permite mas mozos o el mozo ya se encuentra";
			}

			return "El mozo " + m.getNombreEmpleado()
					+ " ahora esta en el sector " + s.getNombre();
		} catch (EntityInvalidaException e) {
			return "Error";
		}
	}

	@Override
	public UsuarioDTO Login(String nombre, String contrasenia)
			throws RemoteException {
		try {
			Calendar.getInstance().getTime();
			Usuario u = usuarioDAO.GetUsuarioByNombre(nombre);
			u.setUltimaFechaIngreso(new Date(Calendar.getInstance().getTime()
					.getTime()));
			usuarioDAO.save(u);
			return u.generarDTOdeUsuario();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void cargaInicialDatos() throws RemoteException {
		this.CrearTurnos();

		// CREAR RESTAURANTES

		Restaurante restauranteBelgrano = new Restaurante();
		Restaurante restauranteCaballito = new Restaurante();
		Restaurante restaurantePuertoMadero = new Restaurante();

		this.CrearProductos(restauranteBelgrano, restauranteCaballito,
				restaurantePuertoMadero);
		this.CrearUsuarios(restauranteBelgrano, restauranteCaballito,
				restaurantePuertoMadero);
		this.CrearRestauranteBelgrano(restauranteBelgrano);
		this.CrearRestaurantePuertoMadero(restaurantePuertoMadero);
		this.CrearRestauranteCaballito(restauranteCaballito);
		this.CrearProveedores();
		this.CrearPlatosSemis();
		DepositoCentral dc = new DepositoCentral();
		dc.setItemsDeposito(this.GetCargaInicialDeposito(10));
		try {
			depositoDAO.save(dc);
			// Provedor pB = provDAO.getById(1);
			// pB.setRestaurante(restauranteBelgrano);
			// Provedor pC = provDAO.getById(2);
			// pC.setRestaurante(restauranteCaballito);
			// Provedor pM = provDAO.getById(3);
			// pM.setRestaurante(restaurantePuertoMadero);
			//
			// provDAO.save(pB);
			// provDAO.save(pC);
			// provDAO.save(pM);

		} catch (EntityInvalidaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// CREAR ADMIN
		Administrador adm = new Administrador();
		Usuario u = new Usuario();
		u.setContrasenia("admin");
		u.setNombre("admin");
		u.setRol(adm);
		adm.setUsuario(u);
		try {
			rolDAO.save(adm);
		} catch (EntityInvalidaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void CrearPlatosSemis() {
		// TODO Auto-generated method stub

		// SEMI-ELABORADOS
		Plato semi1 = new Plato();
		semi1.setComision(10);
		semi1.setNombre("Salsa");
		semi1.setSemiElaborado(true);
		List<ItemIngrediente> ings = new ArrayList<ItemIngrediente>();
		for (int i = 1; i < 4; i++) {
			ItemIngrediente ing = new ItemIngrediente();
			ing.setCantidad(3 + i);
			Producto p = Controller.prodDao
					.GetByNombre("CAU-BAJA-ORI-CYNV" + i);
			ing.setProducto(p);
			ings.add(ing);
		}
		semi1.setIngredientes(ings);

		Plato semi2 = new Plato();
		semi2.setComision(10);
		semi2.setNombre("Torta");
		semi2.setSemiElaborado(true);
		List<ItemIngrediente> ings2 = new ArrayList<ItemIngrediente>();
		for (int i = 1; i < 4; i++) {
			ItemIngrediente ing = new ItemIngrediente();
			ing.setCantidad(3 + i);
			Producto p = Controller.prodDao
					.GetByNombre("CAU-ALTA-ORI-CYNV" + i);
			ing.setProducto(p);
			ings2.add(ing);
		}
		semi2.setIngredientes(ings2);

		Plato semi3 = new Plato();
		semi3.setComision(10);
		semi3.setNombre("Muffin");
		semi3.setSemiElaborado(true);
		List<ItemIngrediente> ings3 = new ArrayList<ItemIngrediente>();
		for (int i = 2; i < 5; i++) {
			ItemIngrediente ing = new ItemIngrediente();
			ing.setCantidad(3 + i);
			Producto p = Controller.prodDao
					.GetByNombre("CAU-BAJA-ORI-CYNV" + i);
			ing.setProducto(p);
			ings3.add(ing);
		}
		semi3.setIngredientes(ings3);

		try {
			Controller.platoDAO.save(semi3);
			Controller.platoDAO.save(semi2);
			Controller.platoDAO.save(semi1);
		} catch (EntityInvalidaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private List<ItemDeposito> GetCargaInicialDeposito(int cantidad) {
		// TODO Auto-generated method stub
		List<ItemDeposito> items = new ArrayList<ItemDeposito>();
		for (int i = 0; i < 5; i++) {
			ItemDeposito itemLote = new ItemDeposito();
			// itemLote.setStock(0);
			itemLote.setStockDeseado(5 * cantidad);
			Producto p1 = Controller.prodDao.GetByNombre("CAU-BAJA-ORI-ELANV"
					+ i);
			itemLote.setProducto(p1);
			for (int j = 0; j < (5 * cantidad) - 1; j++) {
				ItemProducto itprod = new ItemProducto();
				Calendar c = Calendar.getInstance();
				c.add(Calendar.DAY_OF_MONTH, 120);
				Date d = new Date(c.getTimeInMillis());
				itprod.setFechaVencimiento(d);
				itprod.setLote("Lote");
				itprod.setProducto(p1);
				itemLote.AddItemProducto(itprod);
			}
			items.add(itemLote);
		}

		for (int i = 0; i < 5; i++) {
			ItemDeposito itemLote = new ItemDeposito();
			Producto p1 = Controller.prodDao.GetByNombre("CAU-BAJA-ORI-CYNV"
					+ i);
			itemLote.setProducto(p1);
			// itemLote.setStock(0);
			itemLote.setStockDeseado(10 * cantidad);
			for (int j = 0; j < (10 * cantidad) - 1; j++) {
				ItemProducto itprod = new ItemProducto();
				Calendar c = Calendar.getInstance();
				c.add(Calendar.DAY_OF_MONTH, 120);
				Date d = new Date(c.getTimeInMillis());
				itprod.setFechaVencimiento(d);
				itprod.setLote("Lote");
				itprod.setProducto(p1);
				itemLote.AddItemProducto(itprod);
			}
			items.add(itemLote);
		}

		for (int i = 0; i < 5; i++) {
			ItemDeposito itemLote = new ItemDeposito();
			Producto p1 = Controller.prodDao
					.GetByNombre("CAU-BAJA-ORI-CYV" + i);
			itemLote.setProducto(p1);
			// itemLote.setStock(0);
			itemLote.setStockDeseado(10 * cantidad);
			for (int j = 0; j < (10 * cantidad) - 1; j++) {
				ItemProducto itprod = new ItemProducto();
				Calendar c = Calendar.getInstance();
				c.add(Calendar.DAY_OF_MONTH, 120);
				Date d = new Date(c.getTimeInMillis());
				itprod.setFechaVencimiento(d);
				itprod.setLote("Lote");
				itprod.setProducto(p1);
				itemLote.AddItemProducto(itprod);
			}
			items.add(itemLote);
		}

		for (int i = 0; i < 4; i++) {

			ItemDeposito itemLote = new ItemDeposito();
			Producto p1 = Controller.prodDao.GetByNombre("CAU-MEDIA-ORI-ELAV"
					+ i);
			itemLote.setProducto(p1);
			// itemLote.setStock(0);
			itemLote.setStockDeseado(5 * 4);
			for (int j = 0; j < (5 * 4) - 1; j++) {
				ItemProducto itprod = new ItemProducto();
				Calendar c = Calendar.getInstance();
				c.add(Calendar.DAY_OF_MONTH, 60);
				Date d = new Date(c.getTimeInMillis());
				itprod.setFechaVencimiento(d);
				itprod.setLote("Lote");
				itprod.setProducto(p1);
				itemLote.AddItemProducto(itprod);
			}
			items.add(itemLote);
		}

		for (int i = 0; i < 10; i++) {

			ItemDeposito itemLote = new ItemDeposito();
			Producto p1 = Controller.prodDao.GetByNombre("CAU-ALTA-ORI-CYNV"
					+ i);
			itemLote.setProducto(p1);
			// itemLote.setStock(0);
			itemLote.setStockDeseado(8 * cantidad);
			for (int j = 0; j < (8 * cantidad) - 1; j++) {
				ItemProducto itprod = new ItemProducto();
				Calendar c = Calendar.getInstance();
				c.add(Calendar.DAY_OF_MONTH, 20);
				Date d = new Date(c.getTimeInMillis());
				itprod.setFechaVencimiento(d);
				itprod.setLote("Lote");
				itprod.setProducto(p1);
				itemLote.AddItemProducto(itprod);
			}
			items.add(itemLote);
		}
		return items;
	}

	private void CrearProveedores() {
		// TODO Auto-generated method stub
		Provedor p = new Provedor();
		p.setNombre("PBelgrano");
		p.setProductos(Controller.prodDao.getAll());
		p.setDisponible(true);
		p.setRestaurante(Controller.restauranteDAO.getById(1));

		Provedor p1 = new Provedor();
		p1.setNombre("PCaballito");
		p1.setProductos(Controller.prodDao.getAll());
		p1.setDisponible(true);
		p1.setRestaurante(Controller.restauranteDAO.getById(2));

		Provedor p3 = new Provedor();
		p3.setNombre("PMadero");
		p3.setProductos(Controller.prodDao.getAll());
		p3.setDisponible(true);
		p3.setRestaurante(Controller.restauranteDAO.getById(3));

		try {
			Controller.provDAO.save(p);
			Controller.provDAO.save(p1);
			Controller.provDAO.save(p3);
		} catch (EntityInvalidaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void CrearProductos(Restaurante restauranteBelgrano,
			Restaurante restauranteCaballito,
			Restaurante restaurantePuertoMadero) {

		for (int i = 0; i < 5; i++) {
			Producto p1 = new Producto();
			p1.setCauducidad(Cauducidad.BAJA);
			p1.setNombre("CAU-BAJA-ORI-ELANV" + i);
			p1.setOrigenDestino(OrigenDestino.ELABORACIONALANOVENTA);
			// p1.setProveedores(provs);
			try {
				prodDao.save(p1);
			} catch (EntityInvalidaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		for (int i = 0; i < 5; i++) {
			Producto p1 = new Producto();
			p1.setCauducidad(Cauducidad.BAJA);
			p1.setNombre("CAU-BAJA-ORI-CYNV" + i);
			p1.setOrigenDestino(OrigenDestino.COMPRAYNOVENTA);

			// p1.setProveedores(provs);
			try {
				prodDao.save(p1);
			} catch (EntityInvalidaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		for (int i = 0; i < 10; i++) {
			Producto p1 = new Producto();
			p1.setCauducidad(Cauducidad.MEDIA);
			p1.setNombre("CAU-BAJA-ORI-CYV" + i);
			p1.setOrigenDestino(OrigenDestino.COMPRAYVENTA);
			// p1.setProveedores(provs);
			try {
				prodDao.save(p1);
			} catch (EntityInvalidaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		for (int i = 0; i < 5; i++) {
			Producto p1 = new Producto();
			p1.setCauducidad(Cauducidad.MEDIA);
			p1.setNombre("CAU-MEDIA-ORI-ELAV" + i);
			p1.setOrigenDestino(OrigenDestino.ELABORACIONALAVENTA);
			// p1.setProveedores(provs);
			try {
				prodDao.save(p1);
			} catch (EntityInvalidaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		for (int i = 0; i < 10; i++) {
			Producto p1 = new Producto();
			p1.setCauducidad(Cauducidad.ALTA);
			p1.setNombre("CAU-ALTA-ORI-CYNV" + i);
			p1.setOrigenDestino(OrigenDestino.COMPRAYNOVENTA);
			// p1.setProveedores(provs);
			try {
				prodDao.save(p1);
			} catch (EntityInvalidaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void CrearTurnos() {
		Turno t1 = new Turno();
		t1.setHoraInicio(7);
		t1.setHoraFin(12);
		t1.setNombre("Maniana");

		Turno t2 = new Turno();
		t2.setHoraInicio(12);
		t2.setHoraFin(18);
		t2.setNombre("Tarde");

		Turno t3 = new Turno();
		t3.setHoraInicio(18);
		t3.setHoraFin(23);
		t3.setNombre("Noche");
		try {
			turnoDAO.save(t1);
			turnoDAO.save(t2);
			turnoDAO.save(t3);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void CrearUsuarios(Restaurante restauranteBelgrano,
			Restaurante restauranteCaballito,
			Restaurante restaurantePuertoMadero) {
		// CREAR MOZOS
		this.CargarMozos(restauranteBelgrano, restauranteCaballito,
				restaurantePuertoMadero);

		// CREATE CAJEROS
		this.CrearCajero(restauranteBelgrano, 1, "R1C1");
		this.CrearCajero(restauranteBelgrano, 2, "R1C2");
		this.CrearCajero(restauranteBelgrano, 3, "R1C3");

		this.CrearCajero(restauranteCaballito, 1, "R2C1");
		this.CrearCajero(restauranteCaballito, 2, "R2C2");
		this.CrearCajero(restauranteCaballito, 3, "R2C3");

		this.CrearCajero(restaurantePuertoMadero, 1, "R3C1");
		this.CrearCajero(restaurantePuertoMadero, 2, "R3C2");
		this.CrearCajero(restaurantePuertoMadero, 3, "R3C3");

		// CREAR JEFE PREPARACION
		this.CrearJefeAreaPreparacion(restauranteBelgrano, 1, "R1J1");
		this.CrearJefeAreaPreparacion(restauranteCaballito, 1, "R2J1");
		this.CrearJefeAreaPreparacion(restaurantePuertoMadero, 1, "R3J1");
	}

	private void CrearCajero(Restaurante r, int turno, String nombre) {
		Usuario uc1 = new Usuario();
		uc1.setContrasenia(nombre);
		uc1.setNombre(nombre);

		Cajero c = new Cajero();
		uc1.setRol(c);
		c.setNombreEmpleado(nombre);
		c.setRestaurante(r);
		c.setTurno(turnoDAO.getById(turno));
		c.setUsuario(uc1);
		r.addEmpleado(c);
	}

	private void CrearMozo(Restaurante restaurante, int turno, String nombre,
			float comision) {
		Usuario uc1 = new Usuario();
		uc1.setContrasenia(nombre);
		uc1.setNombre(nombre);

		Mozo c = new Mozo();
		uc1.setRol(c);
		c.setNombreEmpleado(nombre);
		c.setRestaurante(restaurante);
		c.setTurno(turnoDAO.getById(turno));
		c.setComision(comision);
		c.setUsuario(uc1);
		restaurante.addEmpleado(c);
	}

	private void CrearJefeAreaPreparacion(Restaurante r, int turno,
			String nombre) {
		Usuario uc1 = new Usuario();
		uc1.setContrasenia(nombre);
		uc1.setNombre(nombre);

		JefeAreaPreparacion c = new JefeAreaPreparacion();
		uc1.setRol(c);
		c.setNombreEmpleado(nombre);
		c.setRestaurante(r);
		c.setTurno(turnoDAO.getById(turno));
		c.setUsuario(uc1);

		r.addEmpleado(c);
	}

	private void CrearRestauranteBelgrano(Restaurante restauranteBelgrano) {
		// Cocina
		JefeAreaPreparacion emp = null;
		for (Empleado e : restauranteBelgrano.getEmpleados()) {
			if (e.soyJefeDeArea()) {
				emp = (JefeAreaPreparacion) e;
			}
		}
		Cocina C1 = new Cocina();
		C1.setHorasDisponibles(16);
		C1.setJefe(emp);
		C1.setItemsEstimados(this.CargaInicialItemEstimados());

		// DEPOSITO
		DepositoLocal dl = new DepositoLocal();
		dl.setItemsDeposito(this.GetCargaInicialDeposito(5));

		// Barra
		Barra b = new Barra();
		b.setJefe(emp);
		b.setItemsEstimados(this.CargaInicialItemEstimados());

		// Cafeteria
		Cafeteria caf = new Cafeteria();
		caf.setJefe(emp);
		caf.setItemsEstimados(this.CargaInicialItemEstimados());

		// Restaurante
		restauranteBelgrano.setBarra(b);
		restauranteBelgrano.setCafeteria(caf);
		restauranteBelgrano.setCocina(C1);
		restauranteBelgrano.setDeposito(dl);
		this.CargarMesasParaElRestaurante(restauranteBelgrano, 68, 16, 14, 10);
		try {
			restauranteDAO.save(restauranteBelgrano);
		} catch (EntityInvalidaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void CrearRestauranteCaballito(Restaurante restauranteCaballito) {
		// Cocina
		JefeAreaPreparacion emp = null;
		for (Empleado e : restauranteCaballito.getEmpleados()) {
			if (e.soyJefeDeArea()) {
				emp = (JefeAreaPreparacion) e;
			}
		}
		Cocina C1 = new Cocina();
		C1.setHorasDisponibles(16);
		C1.setJefe(emp);
		C1.setItemsEstimados(this.CargaInicialItemEstimados());

		// DEPOSITO
		DepositoLocal dl = new DepositoLocal();
		dl.setItemsDeposito(this.GetCargaInicialDeposito(5));

		// Barra
		Barra b = new Barra();
		b.setJefe(emp);
		b.setItemsEstimados(this.CargaInicialItemEstimados());

		// Cafeteria
		Cafeteria caf = new Cafeteria();
		caf.setJefe(emp);
		caf.setItemsEstimados(this.CargaInicialItemEstimados());

		// Restaurante
		restauranteCaballito.setBarra(b);
		restauranteCaballito.setCafeteria(caf);
		restauranteCaballito.setCocina(C1);
		restauranteCaballito.setDeposito(dl);
		this.CargarMesasParaElRestaurante(restauranteCaballito, 70, 20, 0, 9);
		try {
			restauranteDAO.save(restauranteCaballito);
		} catch (EntityInvalidaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private List<ItemLoteCantidad> CargaInicialItemEstimados() {
		// TODO Auto-generated method stub
		List<ItemLoteCantidad> items = new ArrayList<ItemLoteCantidad>();
		for (int i = 0; i < 5; i++) {
			ItemLoteCantidad itemLote = new ItemLoteCantidad();
			Producto p1 = Controller.prodDao.GetByNombre("CAU-BAJA-ORI-ELANV"
					+ i);
			itemLote.setProducto(p1);
			// itemLote.setCantidad(0);
			itemLote.setStockReposicion(50);
			for (int j = 0; j < 49; j++) {
				ItemProducto itprod = new ItemProducto();
				Calendar c = Calendar.getInstance();
				c.add(Calendar.DAY_OF_MONTH, 120);
				Date d = new Date(c.getTimeInMillis());
				itprod.setFechaVencimiento(d);
				itprod.setLote("Lote");
				itprod.setProducto(p1);
				itemLote.AddItemProducto(itprod);
			}
			items.add(itemLote);
		}

		for (int i = 0; i < 5; i++) {
			ItemLoteCantidad itemLote = new ItemLoteCantidad();
			Producto p1 = Controller.prodDao.GetByNombre("CAU-BAJA-ORI-CYNV"
					+ i);
			itemLote.setProducto(p1);
			// itemLote.setCantidad(0);
			itemLote.setStockReposicion(30);
			for (int j = 0; j < 29; j++) {
				ItemProducto itprod = new ItemProducto();
				Calendar c = Calendar.getInstance();
				c.add(Calendar.DAY_OF_MONTH, 120);
				Date d = new Date(c.getTimeInMillis());
				itprod.setFechaVencimiento(d);
				itprod.setLote("Lote");
				itprod.setProducto(p1);
				itemLote.AddItemProducto(itprod);
			}
			items.add(itemLote);
		}

		for (int i = 0; i < 8; i++) {
			ItemLoteCantidad itemLote = new ItemLoteCantidad();
			Producto p1 = Controller.prodDao
					.GetByNombre("CAU-BAJA-ORI-CYV" + i);
			itemLote.setProducto(p1);
			// itemLote.setCantidad(0);
			itemLote.setStockReposicion(30);
			for (int j = 0; j < 29; j++) {
				ItemProducto itprod = new ItemProducto();
				Calendar c = Calendar.getInstance();
				c.add(Calendar.DAY_OF_MONTH, 120);
				Date d = new Date(c.getTimeInMillis());
				itprod.setFechaVencimiento(d);
				itprod.setLote("Lote");
				itprod.setProducto(p1);
				itemLote.AddItemProducto(itprod);
			}
			items.add(itemLote);
		}

		for (int i = 0; i < 5; i++) {

			ItemLoteCantidad itemLote = new ItemLoteCantidad();
			Producto p1 = Controller.prodDao.GetByNombre("CAU-MEDIA-ORI-ELAV"
					+ i);
			itemLote.setProducto(p1);
			// itemLote.setCantidad(0);
			itemLote.setStockReposicion(5);
			for (int j = 0; j < 4; j++) {
				ItemProducto itprod = new ItemProducto();
				Calendar c = Calendar.getInstance();
				c.add(Calendar.DAY_OF_MONTH, 60);
				Date d = new Date(c.getTimeInMillis());
				itprod.setFechaVencimiento(d);
				itprod.setLote("Lote");
				itprod.setProducto(p1);
				itemLote.AddItemProducto(itprod);
			}
			items.add(itemLote);
		}

		for (int i = 0; i < 10; i++) {

			ItemLoteCantidad itemLote = new ItemLoteCantidad();
			Producto p1 = Controller.prodDao.GetByNombre("CAU-ALTA-ORI-CYNV"
					+ i);
			itemLote.setProducto(p1);
			// itemLote.setCantidad(0);
			itemLote.setStockReposicion(8);
			for (int j = 0; j < 7; j++) {
				ItemProducto itprod = new ItemProducto();
				Calendar c = Calendar.getInstance();
				c.add(Calendar.DAY_OF_MONTH, 10);
				Date d = new Date(c.getTimeInMillis());
				itprod.setFechaVencimiento(d);
				itprod.setLote("Lote");
				itprod.setProducto(p1);
				itemLote.AddItemProducto(itprod);
			}
			items.add(itemLote);
		}
		return items;
	}

	private void CrearRestaurantePuertoMadero(
			Restaurante restaurantePuertoMadero) {
		JefeAreaPreparacion emp = null;
		for (Empleado e : restaurantePuertoMadero.getEmpleados()) {
			if (e.soyJefeDeArea()) {
				emp = (JefeAreaPreparacion) e;
			}
		}

		// Cocina
		Cocina C1 = new Cocina();
		C1.setHorasDisponibles(32);
		C1.setJefe(emp);
		C1.setItemsEstimados(this.CargaInicialItemEstimados());
		// DEPOSITO
		DepositoLocal dl = new DepositoLocal();
		dl.setItemsDeposito(this.GetCargaInicialDeposito(5));

		// Barra
		Barra b = new Barra();
		b.setJefe(emp);
		C1.setItemsEstimados(this.CargaInicialItemEstimados());
		// Cafeteria
		Cafeteria caf = new Cafeteria();
		caf.setJefe(emp);
		caf.setItemsEstimados(this.CargaInicialItemEstimados());
		// Restaurante

		restaurantePuertoMadero.setBarra(b);
		restaurantePuertoMadero.setCafeteria(caf);
		restaurantePuertoMadero.setCocina(C1);
		restaurantePuertoMadero.setDeposito(dl);
		this.CargarMesasParaElRestaurante(restaurantePuertoMadero, 62, 13, 28,
				11);
		try {
			restauranteDAO.save(restaurantePuertoMadero);
		} catch (EntityInvalidaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void CargarMesasParaElRestaurante(Restaurante r, int mesas4,
			int mesas6, int mesas8, int cantidadSector) {
		List<Sector> sectores = new ArrayList<Sector>();
		for (int i = 0; i < cantidadSector - 1; i++) {
			Sector sector = new Sector();
			sector.setNombre("Sector" + i);

			List<Mesa> mesas = new ArrayList<Mesa>();
			for (int j = 1; j < 6 && mesas4 > 0; j++) {
				Mesa m = new Mesa();
				m.setMaxPersonas(4);
				m.setMinPersonas(4);
				m.setSector(sector);
				m.setEstado(true);
				mesas.add(m);
				mesas4--;
			}

			sector.addMesas(mesas);

			for (int j = 0; j < 2 && mesas6 > 0; j++) {
				Mesa m = new Mesa();
				m.setMaxPersonas(7);
				m.setMinPersonas(6);
				m.setSector(sector);
				m.setEstado(true);
				mesas.add(m);
				mesas6--;
			}

			sector.addMesas(mesas);

			for (int j = 0; j < 2 && mesas8 > 0; j++) {
				Mesa m = new Mesa();
				m.setMaxPersonas(10);
				m.setMinPersonas(8);
				m.setEstado(true);
				m.setSector(sector);
				mesas.add(m);
				mesas8--;
			}

			sector.addMesas(mesas);
			sectores.add(sector);
		}
		r.setSectores(sectores);
	}

	private void CargarMozos(Restaurante restauranteBelgrano,
			Restaurante restauranteCaballito,
			Restaurante restaurantePuertoMadero) {
		// CREAR MOZOS
		Random r = new Random();
		List<Restaurante> restaurantes = new ArrayList<Restaurante>();
		restaurantes.add(restauranteBelgrano);
		restaurantes.add(restauranteCaballito);
		restaurantes.add(restaurantePuertoMadero);

		for (int restaurante = 1; restaurante < 4; restaurante++) {
			for (int i = 0; i < 50; i++) {
				for (int turno = 1; turno < 4; turno++) {
					this.CrearMozo(restaurantes.get(restaurante - 1), turno,
							"R" + restaurante + "M" + i + "T" + turno,
							r.nextInt(20));
				}
			}
		}
	}

	@Override
	public ProveedorDTO buscarProveedor(String nombre) throws RemoteException {
		// TODO Auto-generated method stub
		Provedor prov = provDAO.GetByNombre(nombre);
		if (prov != null) {
			return prov.generarDTOdeProveedor();
		}
		return null;
	}

	@Override
	public ProductoDTO buscarProductoParaProveedor(String nombre)
			throws RemoteException {
		// TODO Auto-generated method stub

		Producto prod = prodDao.GetByNombre(nombre);

		if (prod != null) {
			if (prod.getOrigenDestino() == OrigenDestino.COMPRAYNOVENTA
					|| prod.getOrigenDestino() == OrigenDestino.COMPRAYVENTA) {
				return prod.generarDTOdeProducto();
			}
		}
		return null;
	}

	@Override
	public ProductoDTO buscarProductoParaPlato(String nombre)
			throws RemoteException {
		// TODO Auto-generated method stub

		Producto prod = prodDao.GetByNombre(nombre);

		if (prod != null) {
			return prod.generarDTOdeProducto();
		}
		return null;
	}

	@Override
	public ProductoDTO buscarProducto(String nombre) throws RemoteException {
		// TODO Auto-generated method stub

		Producto prod = prodDao.GetByNombre(nombre);

		if (prod != null) {
			return prod.generarDTOdeProducto();
		}
		return null;
	}

	@Override
	public void GuardarProveedor(ProveedorDTO prov) throws RemoteException {
		// TODO Auto-generated method stub

		try {
			Provedor p = Provedor.crearInstanciaDeProveedor(prov);
			p.setRestaurante(restauranteDAO.getById(prov.getRestaurante()));
			Controller.provDAO.save(p);
		} catch (EntityInvalidaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public PlatoDTO buscarPlato(String nombre) throws RemoteException {
		// TODO Auto-generated method stub

		Plato p = Controller.platoDAO.GetByNombre(nombre);

		if (p != null) {
			return p.generarDTOdePlato();
		}
		return null;
	}

	@Override
	public void GuardarPlato(PlatoDTO plato) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			List<Restaurante> rest = this.restauranteDAO.getAll();
			for (Restaurante restaurante : rest) {
				Plato p = Plato.crearInstanciaDePlato(plato);
				if (restaurante.getBarra().getNombreSector()
						.equals(plato.getNombreSector())) {
					p.setArea(restaurante.getBarra());
				} else if (restaurante.getCocina().getNombreSector()
						.equals(plato.getNombreSector())) {
					p.setArea(restaurante.getCocina());
				} else {
					p.setArea(restaurante.getCafeteria());
				}

				restaurante.getPlatos().add(p);
				this.restauranteDAO.save(restaurante);
			}

		} catch (EntityInvalidaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void GuardarProducto(ProductoDTO prod) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			Producto p = Producto.crearInstanciaDeProducto(prod);
			prodDao.save(p);
		} catch (EntityInvalidaException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	public PlatoDTO buscarSemiElaborado(String nombre) throws RemoteException {
		// TODO Auto-generated method stub
		Plato p = Controller.platoDAO.GetByNombre(nombre);

		if (p != null && p.isSemiElaborado()) {
			return p.generarDTOdePlato();
		}
		return null;
	}

	@Override
	public int CrearNuevoItemPlanDeProduccion(ItemPlanDeProduccionDTO item,
			boolean porPrimeraVez) throws RemoteException {
		// TODO Auto-generated method stub
		ItemPlanDeProduccion itemPlan = ItemPlanDeProduccion
				.crearInstanciaDeMesa(item);
		itemPlan.setRestaurante(restauranteDAO.getById(item.getRestaurante()));
		try {
			Cocina c = itemPlan.getRestaurante().getCocina();
			if (sePuedeAgregarUnNuevoItem(itemPlan, porPrimeraVez)) {
				for (ItemIngrediente itIng : itemPlan.getSemielaborado()
						.getIngredientes()) {
					ItemLoteCantidad itLoteCant = new ItemLoteCantidad();
					itLoteCant.setArea(itemPlan.getRestaurante().getCocina());
					itLoteCant.setProducto(itIng.getProducto());
					itLoteCant.setStockReposicion(0);
					itemLoteCantidadDAO.save(itLoteCant);
				}

				itemppDAO.save(itemPlan);
				return itemPlan.getId();
			}
		} catch (EntityInvalidaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public ItemPlanDeProduccionDTO buscarItemPlanProduccion(int id)
			throws RemoteException {
		// TODO Auto-generated method stub
		ItemPlanDeProduccion p = Controller.itemppDAO.getById(id);

		if (p != null) {
			return p.generarDTO();
		}
		return null;
	}

	@Override
	public boolean GuardarItemPlanDeProduccion(ItemPlanDeProduccionDTO item)
			throws RemoteException {
		// TODO Auto-generated method stub
		ItemPlanDeProduccion itemPlan = ItemPlanDeProduccion
				.crearInstanciaDeMesa(item);
		itemPlan.setRestaurante(restauranteDAO.getById(item.getRestaurante()));
		try {
			if (sePuedeModificarEltem(itemPlan)) {
				itemppDAO.save(itemPlan);
				return true;
			}
		} catch (EntityInvalidaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	private boolean sePuedeModificarEltem(ItemPlanDeProduccion itAModificar) {
		Iterator<ItemPlanDeProduccion> items = itemppDAO.getAll().iterator();

		float horasLibresEnRestauranteActual = itAModificar.getRestaurante()
				.getCocina().getHorasDisponibles();

		while (items.hasNext()) {
			ItemPlanDeProduccion it = items.next();

			int idRes = it.getRestaurante().getId();
			if (idRes == itAModificar.getRestaurante().getId()) {
				horasLibresEnRestauranteActual = horasLibresEnRestauranteActual
						- (it.getCantidad() * it.getTiempoPorUnidadEnHoras());
			}
		}

		boolean hayHorasDisponiblesParaElRestauranteIndicado = horasLibresEnRestauranteActual >= (itAModificar
				.getCantidad() * itAModificar.getTiempoPorUnidadEnHoras());

		return hayHorasDisponiblesParaElRestauranteIndicado;
	}

	private boolean sePuedeAgregarUnNuevoItem(ItemPlanDeProduccion itNuevo,
			boolean porPrimeraVez) {
		Iterator<ItemPlanDeProduccion> items = itemppDAO.getAll().iterator();
		Iterator<Restaurante> res = restauranteDAO.getAll().iterator();

		float horasLibres = 0;
		float horasTotales = 0;
		float horasLibresEnRestauranteActual = itNuevo.getRestaurante()
				.getCocina().getHorasDisponibles();

		while (items.hasNext()) {
			ItemPlanDeProduccion it = items.next();
			if (it.isLibre()) {
				horasLibres = (int) (it.getTiempoPorUnidadEnHoras() * it
						.getCantidad()) + horasLibres;
			}

			horasTotales = (int) (it.getTiempoPorUnidadEnHoras() * it
					.getCantidad()) + horasTotales;
			int idRes = it.getRestaurante().getId();
			if (idRes == itNuevo.getRestaurante().getId() && it.isLibre()) {
				horasLibresEnRestauranteActual = horasLibresEnRestauranteActual
						- (it.getCantidad() * it.getTiempoPorUnidadEnHoras());
			}
		}

		boolean hayHorasDisponiblesParaElRestauranteIndicado = horasLibresEnRestauranteActual >= (itNuevo
				.getCantidad() * itNuevo.getTiempoPorUnidadEnHoras());

		float horasCumplidas = horasTotales - horasLibres;
		boolean masDel60 = (horasCumplidas / horasTotales) >= 0.6;
		return hayHorasDisponiblesParaElRestauranteIndicado
				&& (porPrimeraVez || masDel60);
	}

	@Override
	public boolean BorrarItemPlanDeProduccion(ItemPlanDeProduccionDTO item)
			throws RemoteException {
		try {
			if (item.isLibre()) {
				ItemPlanDeProduccion it = ItemPlanDeProduccion
						.crearInstanciaDeMesa(item);
				GenericDAO.delete(it);
				return true;
			}
		} catch (EntityInvalidaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return false;
	}

	@Override
	public boolean Logout(int idUsuario) throws RemoteException {
		// TODO Auto-generated method stub
		Calendar.getInstance().getTime();
		Usuario u = this.usuarioDAO.getById(idUsuario);
		u.setUltimaFechaEgreso(new Date(Calendar.getInstance().getTime()
				.getTime()));
		try {
			usuarioDAO.save(u);
			return true;
		} catch (EntityInvalidaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public String GenerarOrdenesDeCompra(List<ItemCompraDTO> items,
			boolean porFaltante, String path, int idUsuario)
			throws RemoteException {
		List<OrdenDeCompra> ordenes = new ArrayList<OrdenDeCompra>();
		Usuario u = this.usuarioDAO.getById(idUsuario);
		for (Provedor p : GetProveedores(items)) {
			Iterator<ItemCompraDTO> itemIterator = items.iterator();
			Calendar.getInstance().getTime();
			Date d = new Date(Calendar.getInstance().getTime().getTime());

			OrdenDeCompra orden = new OrdenDeCompra();
			orden.setProveedor(p);
			orden.setFecha(d);
			orden.setNombre(this.GetNombreOrdenDeCompra(orden));

			OrdenDeCompra orden2 = Controller.ordenDeCompraDAO
					.GetOrdenDeCompraByNombre(orden.getNombre());
			if (orden2 == null) {
				orden.setEstado("PENDIENTE");
				orden.setPorFaltante(porFaltante);
				orden.setUsuario(u);
			} else {
				orden = orden2;
			}

			while (itemIterator.hasNext()) {
				ItemCompraDTO it = itemIterator.next();
				if (it.getProvedor().getProvId() == p.getId()) {
					orden.AddItem(ItemCompra.crearInstancia(it));
				}
			}

			generarXmldeOC(orden, path);
			try {
				ordenDeCompraDAO.save(orden);
				ordenes.add(orden);
			} catch (EntityInvalidaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return this.GetNombresConcatenados(ordenes);
	}

	private void generarXmldeOC(OrdenDeCompra ordenCompra, String path) {
		Element or = new Element("OrdenCompra");
		or.setAttribute(new Attribute("numero", String.valueOf(ordenCompra
				.getNombre())));
		Document doc = new Document(or);
		doc.setRootElement(or);

		// NODO DENTRO DE OrdenCompra
		Element emision = (new Element("Emision")).setText(ordenCompra
				.getFecha().toString());
		doc.getRootElement().addContent(emision);

		// NODO DENTRO DE OrdenCompra
		Element cliente = new Element("Proveedor");
		cliente.addContent(new Element("Nombre").setText(ordenCompra
				.getProveedor().getNombre()));
		cliente.addContent(new Element("Id").setText(Integer
				.toString(ordenCompra.getProveedor().getId())));
		doc.getRootElement().addContent(cliente);

		// NODO DENTRO DE OrdenCompra
		Element Compras = new Element("Compras");
		doc.getRootElement().addContent(Compras);

		// NODO DENTRO DE RODAMIENTOS
		List<ItemCompra> items = ordenCompra.getItems();
		Iterator<ItemCompra> i = items.listIterator();
		while (i.hasNext()) {
			ItemCompra item = (ItemCompra) i.next();
			Element itemElement = new Element("Item");
			itemElement.addContent(new Element("Nombre").setText(item
					.getProducto().getNombre()));
			itemElement.addContent(new Element("Calidad").setText(item
					.getCalidad()));
			itemElement.addContent(new Element("MinimosDiasAFechaVencimiento")
					.setText(String.valueOf(item
							.getMinimoDiasFechaVencimiento())));
			itemElement.addContent(new Element("Cantidad").setText(String
					.valueOf(item.getCantidad())));
			Compras.addContent(itemElement);
		}

		XMLOutputter xmlOutput = new XMLOutputter();
		xmlOutput.setFormat(Format.getPrettyFormat());
		String finalPath = path + File.separator + ordenCompra.getNombre()
				+ ".xml";
		try {
			xmlOutput.output(doc, new FileWriter(finalPath));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	private String GetNombresConcatenados(List<OrdenDeCompra> ordenes) {
		// TODO Auto-generated method stub
		String ordenesS = "";
		for (OrdenDeCompra ordenDeCompra : ordenes) {
			ordenesS = ordenesS + ordenDeCompra.getNombre() + ",";
		}

		return ordenesS.substring(0, ordenesS.length() - 2);
	}

	private String GetNombreOrdenDeCompra(OrdenDeCompra orden) {
		String nombre = orden.getProveedor().getNombre()
				+ orden.getFecha().toString();
		return nombre;
	}

	private List<Provedor> GetProveedores(List<ItemCompraDTO> items) {
		List<Provedor> provs = new ArrayList<Provedor>();
		List<ProveedorDTO> provsDTO = new ArrayList<ProveedorDTO>();
		Iterator<ItemCompraDTO> ite = items.iterator();

		while (ite.hasNext()) {
			ItemCompraDTO i = ite.next();
			if (i.getProvedor() != null) {
				provsDTO.add(i.getProvedor());
			}
		}

		Iterator<ProveedorDTO> ite1 = provsDTO.iterator();
		List<ProveedorDTO> sinDuplicados = new ArrayList<ProveedorDTO>();
		while (ite1.hasNext()) {
			ProveedorDTO p = ite1.next();
			if (sinDuplicados.size() == 0) {
				sinDuplicados.add(p);
			} else {
				Iterator<ProveedorDTO> sinDupI = sinDuplicados.iterator();
				boolean esDuplicado = false;
				while (sinDupI.hasNext()) {
					if (sinDupI.next().getProvId() == p.getProvId()) {
						esDuplicado = true;
					}
				}

				if (!esDuplicado) {
					sinDuplicados.add(p);
				}
			}
		}
		Iterator<ProveedorDTO> ite2 = sinDuplicados.iterator();
		while (ite2.hasNext()) {
			provs.add(Provedor.crearInstanciaDeProveedor(ite2.next()));
		}

		return provs;
	}

	@Override
	public List<ItemAReponerDTO> GenerarListaDeReposicion(int idUsuario)
			throws RemoteException {
		Usuario u = this.usuarioDAO.getById(idUsuario);
		List<ItemAReponerDTO> nuevaListaDTO = new ArrayList<ItemAReponerDTO>();
		List<ItemAReponer> nuevaLista = new ArrayList<ItemAReponer>();
		List<Restaurante> res = Controller.restauranteDAO.getAll();
		DepositoCentral dc = Controller.depositoCentralDAO.get();
		// SACO TODOS LOS ITEMS VENCIDOS DEL DEPOSITO CENTRAL
		dc.LimparItemsVencidos();

		for (Restaurante restaurante : res) {
			// SACO LOS ITEMS VENCIDOS
			this.LimpiarItemsVencidosEnRestaurante(restaurante);

			// OBTENGO LOS ITEMS A REPONER EN LOS RESTAURANTES Y LOS REPONGO CON
			// EL STOCK DEL DEPOSITO LOCAL
			this.ReponerEnRestaurantes(u, restaurante);

			// AGREGA LOS INGREDIENTES PARA EL PLAN DE PRODUCCION
			nuevaLista.addAll(this
					.ObtenerItemsParaPlanDeProduccion(restaurante));

			// INTENTO TERMINAR DE LLENAR EL STOCK DE LOS RESTAURANTES
			// UTILIZANDO EL DEPOSITO CENTRAL
			// Y OBTENGO LOS ITEMS QUE NO PUDIERON SER REPUESTOS POR SER DE
			// CAUDUCIDAD ALTA
			nuevaLista
					.addAll(dc.RealizarReposicionARestaurante(u, restaurante));

			// INTENTO REPONER LOS DEPOSITOS LOCALES DE LOS RESTAURANTES CON EL
			// DEPOSITO CENTRAL
			dc.RealizarReposicionADepositoLocal(restaurante);
			try {
				Controller.restauranteDAO.save(restaurante);
			} catch (EntityInvalidaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		nuevaLista.addAll(dc.getItemsAReponer());

		try {
			Controller.depositoCentralDAO.save(dc);
		} catch (EntityInvalidaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (!Controller.itemAReponerDAO.YaSeGeneroLaLista()) {
			try {

				Controller.itemAReponerDAO.GenerarListaParaHoy(nuevaLista);
			} catch (EntityInvalidaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			Date d = new Date(Calendar.getInstance().getTime().getTime());
			nuevaLista = Controller.itemAReponerDAO.getAllByDate(d);
		}

		// DEVUELVO TODOS LOS ITEMS
		for (ItemAReponer itemAReponer : nuevaLista) {
			nuevaListaDTO.add(itemAReponer.generarItemAReponerDTO());
		}

		return nuevaListaDTO;
	}

	private Collection<ItemAReponer> ObtenerItemsParaPlanDeProduccion(
			Restaurante restaurante) {
		// TODO Auto-generated method stub

		List<ItemAReponer> itReponer = new ArrayList<ItemAReponer>();
		List<ItemPlanDeProduccion> plan = Controller.itemppDAO.getAll();
		for (ItemPlanDeProduccion itemPlanDeProduccion : plan) {
			if (itemPlanDeProduccion.getRestaurante().getId() == restaurante
					.getId()
					&& !itemPlanDeProduccion.isReposicionDeIngredientes()) {
				Iterator<ItemIngrediente> ingredientes = itemPlanDeProduccion
						.getSemielaborado().getIngredientes().iterator();
				while (ingredientes.hasNext()) {
					ItemIngrediente it = ingredientes.next();
					ItemAReponer r = new ItemAReponer();
					r.setArea(restaurante.getCocina());
					r.setCantidad(it.getCantidad());
					r.setProducto(it.getProducto());
					r.setDepositoCentral(false);
					Date d = new Date(Calendar.getInstance().getTime()
							.getTime());
					r.setFecha(d);
					r.setRestaurante(restaurante);
					itReponer.add(r);
				}
				itemPlanDeProduccion.setReposicionDeIngredientes(true);
				try {
					Controller.itemppDAO.save(itemPlanDeProduccion);
				} catch (EntityInvalidaException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		return itReponer;
	}

	private void ReponerEnRestaurantes(Usuario u, Restaurante r) {
		// TODO Auto-generated method stub
		r.ReponerEnSectoresDeDepositoLocal();
	}

	private void LimpiarItemsVencidosEnRestaurante(Restaurante r) {
		// TODO Auto-generated method stub
		r.LimpiarItemsVencidos();
	}

	@Override
	public List<String> CargarRemito(String path, String oC)
			throws RemoteException {
		// TODO Auto-generated method stub
		File f = new File(path);
		List<String> errores = new ArrayList<String>();
		if (f != null) {
			Remito remito = Remito.parseXMLOrdenPedido(f);
			OrdenDeCompra oc = Controller.ordenDeCompraDAO
					.GetOrdenDeCompraByNombre(oC);

			if (oc != null && oc.getEstado().equals("PENDIENTE")) {
				String ocNombre = oc.getNombre().toUpperCase();
				String remitoOcNombre = remito.getOrdenDeCompra().toUpperCase();
				if (ocNombre.equals(remitoOcNombre)) {
					errores.addAll(this.ManejarRemito(oc, remito));
					oc.setEstado("COMPLETADA");
					try {
						this.ordenDeCompraDAO.save(oc);
					} catch (EntityInvalidaException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			} else {
				errores.add("El remito no concuerda con la OC");
			}
		} else {
			errores.add("No se encontro el repito");
		}
		return errores;
	}

	private List<String> ManejarRemito(OrdenDeCompra oc, Remito remito) {
		// TODO Auto-generated method stub
		List<String> errores = new ArrayList<String>();
		Iterator<ItemRemito> itemsRemito = remito.getItems().iterator();
		List<ItemRemito> itemsFaltantes = new ArrayList<ItemRemito>();
		List<ItemRemito> mercaderiaPerecederaDeMas = new ArrayList<ItemRemito>();
		List<ItemRemito> mercaderiaDeMasNoPorSustitucion = new ArrayList<ItemRemito>();
		List<ItemRemito> itemsConMercaderiaSustitucionPorFaltante = new ArrayList<ItemRemito>();
		List<ItemRemito> itemsACargar = new ArrayList<ItemRemito>();

		cargarListas(oc, errores, itemsRemito, itemsFaltantes,
				mercaderiaPerecederaDeMas, mercaderiaDeMasNoPorSustitucion,
				itemsConMercaderiaSustitucionPorFaltante, itemsACargar);

		this.CargarEnProximoPedido(itemsFaltantes, errores,
				remito.getProveedor());
		this.GenerarNotaDeDevolucion(mercaderiaDeMasNoPorSustitucion,
				mercaderiaPerecederaDeMas, errores, remito.getProveedor());
		this.AjustarItemsPorSustitucion(
				itemsConMercaderiaSustitucionPorFaltante, itemsACargar, errores);

		this.DistribuirItems(itemsACargar);
		return errores;
	}

	private void DistribuirItems(List<ItemRemito> itemsACargar) {
		// TODO Auto-generated method stub
		List<ItemAReponer> listaDeReposicion = Controller.itemAReponerDAO
				.getAll();
		if (itemsACargar.size() > 0) {
			for (ItemRemito itemRemito : itemsACargar) {
				Iterator<ItemAReponer> iterator = listaDeReposicion.iterator();
				while (iterator.hasNext()) {
					ItemAReponer itReponer = iterator.next();
					if (itReponer.getProducto().getId() == itemRemito.getProd()
							.getId()) {
						if (itReponer.getProducto().getCauducidad() == Cauducidad.ALTA) {
							this.RealizarReposicionCauducidadAlta(itReponer,
									itemRemito);
						} else {
							this.RealizarReposicionADepositoCentral(itReponer,
									itemRemito);
						}
					}
				}
			}
		}
	}

	private void RealizarReposicionADepositoCentral(ItemAReponer itReponer,
			ItemRemito itemRemito) {
		if (itemRemito.getCantidad() > 0) {
			DepositoCentral dc = Controller.depositoCentralDAO.get();

			int cantidad = itemRemito.getCantidad();
			int cantidadAReponer = itReponer.getCantidad();
			while (cantidad > 0 && cantidadAReponer > 0) {
				ItemProducto it = new ItemProducto();
				it.setFechaVencimiento(itemRemito.getFechaVencimiento());
				it.setLote("Lote");
				it.setProducto(itemRemito.getProd());
				dc.AgregarItem(it);
				cantidad--;
				cantidadAReponer--;
			}
			try {
				Controller.depositoCentralDAO.save(dc);
			} catch (EntityInvalidaException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			itemRemito.setCantidad(cantidad);
		}
	}

	private void RealizarReposicionCauducidadAlta(ItemAReponer itReponer,
			ItemRemito itemRemito) {
		// TODO Auto-generated method stub
		if (itemRemito.getCantidad() > 0) {
			AreaPreparacion area = itReponer.getArea();

			int cantidad = itemRemito.getCantidad();
			int cantidadAReponer = itReponer.getCantidad();
			while (cantidad > 0 && cantidadAReponer > 0) {
				ItemProducto it = new ItemProducto();
				it.setFechaVencimiento(itemRemito.getFechaVencimiento());
				it.setLote(null);
				it.setProducto(itemRemito.getProd());
				area.AgregarItemProducto(it);
				cantidad--;
				cantidadAReponer--;
			}
			try {
				Controller.areaDAO.save(area);
			} catch (EntityInvalidaException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			itemRemito.setCantidad(cantidad);
		}
	}

	private void AjustarItemsPorSustitucion(
			List<ItemRemito> itemsConMercaderiaSustitucionPorFaltante,
			List<ItemRemito> itemsACargar, List<String> errores) {
		// TODO Auto-generated method stub
		if (itemsConMercaderiaSustitucionPorFaltante.size() > 0) {
			for (ItemRemito itRemito : itemsConMercaderiaSustitucionPorFaltante) {
				itemsACargar.add(itRemito);
			}
		}
	}

	private void GenerarNotaDeDevolucion(
			List<ItemRemito> mercaderiaPerecederaDeMas,
			List<ItemRemito> mercaderiaVencida, List<String> errores,
			Provedor proveedor) {
		// TODO Auto-generated method stub
		mercaderiaPerecederaDeMas.addAll(mercaderiaVencida);
		if (mercaderiaPerecederaDeMas.size() > 0) {
			NotaDeDevolucion nt = new NotaDeDevolucion();
			nt.setFecha(Calendar.getInstance().getTime());
			nt.setProveedor(proveedor);
			nt.setItems(mercaderiaPerecederaDeMas);
			generarNotaDeDevolucionXML(nt, "C:");
			errores.add("Se genero la nota de Debito con la mercaderia a retirar");
		}
	}

	private void CargarEnProximoPedido(List<ItemRemito> itemsFaltantes,
			List<String> errores, Provedor p) {
		// TODO Auto-generated method stub
		if (itemsFaltantes.size() > 0) {
			Calendar.getInstance().getTime();
			Date d = new Date(Calendar.getInstance().getTime().getTime());

			OrdenDeCompra orden = new OrdenDeCompra();
			orden.setProveedor(p);
			orden.setFecha(d);
			orden.setNombre(this.GetNombreOrdenDeCompra(orden));

			OrdenDeCompra orden2 = Controller.ordenDeCompraDAO
					.GetOrdenDeCompraByNombre(orden.getNombre());
			if (orden2 != null) {
				orden = orden2;
			}

			for (ItemRemito itRemito : itemsFaltantes) {
				ItemCompra itCompra = new ItemCompra();
				itCompra.setCalidad(itRemito.getCalidad());
				itCompra.setCantidad(itRemito.getCantidad());

				java.util.Date hoy = Calendar.getInstance().getTime();
				java.util.Date fechaVencimiento = itRemito
						.getFechaVencimiento();
				Long diasParaFechaVencimiento = fechaVencimiento.getTime()
						- hoy.getTime();
				int diffDay = (int) (diasParaFechaVencimiento / (1000 * 60 * 60 * 24)) + 1;

				itCompra.setMinimoDiasFechaVencimiento(diffDay);
				itCompra.setProducto(itRemito.getProd());
				orden.AddItem(itCompra);
			}

			try {
				Controller.ordenDeCompraDAO.save(orden);
				errores.add("Se creo la orden de compra " + orden.getNombre()
						+ " para los items faltantes");
			} catch (EntityInvalidaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void cargarListas(OrdenDeCompra oc, List<String> errores,
			Iterator<ItemRemito> itemsRemito, List<ItemRemito> itemsFaltantes,
			List<ItemRemito> mercaderiaPerecederaDeMas,
			List<ItemRemito> mercaderiaDeMasNoPorSustitucion,
			List<ItemRemito> itemsConMercaderiaSustitucionPorFaltante,
			List<ItemRemito> itemsACargar) {
		while (itemsRemito.hasNext()) {
			ItemRemito actual = itemsRemito.next();

			Iterator<ItemCompra> itemsCompra = oc.getItems().iterator();
			boolean noEsLoPedido = false;
			while (itemsCompra.hasNext()) {
				ItemCompra itemCompra = itemsCompra.next();

				if (itemCompra.getProducto().getId() == actual.getProd()
						.getId()) {
					if (!esLaMercaderiaPedida(itemCompra, actual)) {
						mercaderiaDeMasNoPorSustitucion.add(actual);
						errores.add(actual.getProd().getNombre()
								+ " No cumple los requisitos de calidad");
						noEsLoPedido = false;
						break;
					} else if (esLaMercaderiaPedida(itemCompra, actual)
							&& hayDeMasPerecedera(itemCompra, actual)) {
						ItemRemito itRemitoCopia = new ItemRemito();
						itRemitoCopia.setCalidad(actual.getCalidad());
						itRemitoCopia.setCantidad(actual.getCantidad()
								- itemCompra.getCantidad());
						itRemitoCopia.setFechaVencimiento(actual
								.getFechaVencimiento());
						itRemitoCopia.setProd(actual.getProd());
						itRemitoCopia.setProdASustituir(actual
								.getProdASustituir());
						actual.setCantidad(itemCompra.getCantidad());
						mercaderiaPerecederaDeMas.add(itRemitoCopia);
						itemsACargar.add(actual);
						noEsLoPedido = false;
						break;
					} else if (esLaMercaderiaPedida(itemCompra, actual)
							&& hayDeMenos(itemCompra, actual)) {
						ItemRemito itRemitoCopia = new ItemRemito();
						itRemitoCopia.setCalidad(actual.getCalidad());
						itRemitoCopia.setCantidad(itemCompra.getCantidad()
								- actual.getCantidad());
						itRemitoCopia.setFechaVencimiento(actual
								.getFechaVencimiento());
						itRemitoCopia.setProd(actual.getProd());
						itRemitoCopia.setProdASustituir(actual
								.getProdASustituir());
						itemsFaltantes.add(itRemitoCopia);

						itemsACargar.add(actual);
						noEsLoPedido = false;
						break;
					} else if (esLaMercaderiaPedida(itemCompra, actual)
							&& actual.isPorFaltante()) {
						itemsConMercaderiaSustitucionPorFaltante.add(actual);
						break;
					} else {
						itemsACargar.add(actual);
						noEsLoPedido = false;
						break;
					}

				} else {
					noEsLoPedido = true;
				}
			}

			if (noEsLoPedido) {
				mercaderiaDeMasNoPorSustitucion.add(actual);
				errores.add(actual.getProd().getNombre()
						+ " No fue pedido en la OC");
			}

		}
	}

	private boolean hayDeMenos(ItemCompra itemCompra, ItemRemito actual) {
		// TODO Auto-generated method stub
		return (itemCompra.getCantidad() - actual.getCantidad()) > 0;
	}

	private boolean hayDeMasPerecedera(ItemCompra itemCompra, ItemRemito actual) {
		// TODO Auto-generated method stub
		boolean esPerecerea = actual.getProd().getCauducidad() == Cauducidad.ALTA;
		boolean hayDeMas = actual.getCantidad() > itemCompra.getCantidad();

		return esPerecerea && hayDeMas;
	}

	private boolean esLaMercaderiaPedida(ItemCompra itCompra,
			ItemRemito itRemito) {
		boolean calidad = itRemito.getCalidad().equals(itCompra.getCalidad());
		java.util.Date hoy = Calendar.getInstance().getTime();
		java.util.Date fechaVencimiento = itRemito.getFechaVencimiento();
		Long diasParaFechaVencimiento = fechaVencimiento.getTime()
				- hoy.getTime();
		int diffDay = (int) (diasParaFechaVencimiento / (1000 * 60 * 60 * 24));
		boolean diasMinimos = diffDay >= itCompra
				.getMinimoDiasFechaVencimiento();

		return diasMinimos && calidad;
	}

	private boolean EsPorSustitucion(ItemCompra itCompra, ItemRemito itRemito) {
		return itRemito.getProdASustituir() != null;
	}

	private void generarNotaDeDevolucionXML(NotaDeDevolucion ordenCompra,
			String path) {
		Element or = new Element("NotaDevolucion");

		Document doc = new Document(or);
		doc.setRootElement(or);

		// NODO DENTRO DE OrdenCompra
		Element emision = (new Element("Emision")).setText(ordenCompra
				.getFecha().toString());
		doc.getRootElement().addContent(emision);

		// NODO DENTRO DE OrdenCompra
		Element cliente = new Element("Proveedor");
		cliente.addContent(new Element("Nombre").setText(ordenCompra
				.getProveedor().getNombre()));
		cliente.addContent(new Element("Id").setText(Integer
				.toString(ordenCompra.getProveedor().getId())));
		doc.getRootElement().addContent(cliente);

		// NODO DENTRO DE OrdenCompra
		Element Compras = new Element("Compras");
		doc.getRootElement().addContent(Compras);

		// NODO DENTRO DE RODAMIENTOS
		List<ItemRemito> items = ordenCompra.getItems();
		Iterator<ItemRemito> i = items.listIterator();
		while (i.hasNext()) {
			ItemRemito item = (ItemRemito) i.next();
			Element itemElement = new Element("Item");
			itemElement.addContent(new Element("Nombre").setText(item.getProd()
					.getNombre()));
			itemElement.addContent(new Element("Calidad").setText(item
					.getCalidad()));
			itemElement.addContent(new Element("Cantidad").setText(String
					.valueOf(item.getCantidad())));
			Compras.addContent(itemElement);
		}

		XMLOutputter xmlOutput = new XMLOutputter();
		xmlOutput.setFormat(Format.getPrettyFormat());
		String finalPath = path + File.separator + "NotaDevolucion-"
				+ ordenCompra.getProveedor() + "-"
				+ ordenCompra.getFecha().getMonth() + "-"
				+ ordenCompra.getFecha().getDay() + ".xml";
		try {
			xmlOutput.output(doc, new FileWriter(finalPath));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public EmpleadoDTO GetEmpleadoByUsuario(int id) throws RemoteException {
		// TODO Auto-generated method stub
		Empleado emp = empleadoDAO.getEmpleadoByUserId(id);
		if (emp != null) {
			return emp.generarDTOEmpleado();
		}
		return null;
	}

	@Override
	public String AbrirTurnoCaja(int idUsuario) throws RemoteException {
		// TODO Auto-generated method stub
		String error = "";
		Cajero c = Controller.cajeroDAO.GetByUsuario(idUsuario);
		try {
			Restaurante r = c.getRestaurante();
			ItemTurnoCaja it = r.AbrirTurnoCaja(c);
			Controller.restauranteDAO.save(r);
			error = "Se ha abierto el turno " + it.getId();

		} catch (CajaException e) {
			// TODO Auto-generated catch block
			error = e.getMessage();
		} catch (EntityInvalidaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return error;
	}

	@Override
	public List<String> GetInformacionCierreCaja(int idUsuario)
			throws RemoteException {
		// TODO Auto-generated method stub
		List<String> dic = new ArrayList<String>();
		float total = 0;
		List<Turno> turnos = Controller.turnoDAO.getAll();
		Cajero c = Controller.cajeroDAO.GetByUsuario(idUsuario);
		Restaurante r = c.getRestaurante();
		Turno turnoFinal = turnos.get(turnos.size() - 1);
		if (turnoFinal.getId() == c.getTurno().getId()) {
			total = r.getTurnoActivo().getTotal();
			List<ItemComisionMozo> comisionMozos = r.getComisionMozosDelDia();
			float totalComMozo = 0;

			for (ItemComisionMozo itemComisionMozo : comisionMozos) {
				totalComMozo = totalComMozo + itemComisionMozo.getTotal();
			}
			dic.add("Total en caja: " + total);
			dic.add("Total prevision mozos al cierre del dia: " + totalComMozo);
		} else {
			total = r.getTurnoActivo().getTotal();
			dic.add("Total en caja: " + total);
		}

		return dic;
	}

	@Override
	public String CerrarCaja(int idUsuario) throws RemoteException {
		// TODO Auto-generated method stub
		String error = "";
		Cajero c = Controller.cajeroDAO.GetByUsuario(idUsuario);
		List<Turno> turnos = Controller.turnoDAO.getAll();
		Turno turnoFinal = turnos.get(turnos.size() - 1);
		Restaurante r = c.getRestaurante();
		try {
			if (turnoFinal.getId() == c.getTurno().getId()) {
				r.CerrarTurnoCaja(c, r.getComisionMozosDelDia());
			} else {
				r.CerrarTurnoCaja(c);
			}

			error = "La caja del dia " + r.getCajaActiva().getFechaInicio()
					+ " se ha cerrado por el usuario: "
					+ c.getUsuario().getNombre() + "con un total de : "
					+ r.getCajaActiva().getTotal();
			Controller.restauranteDAO.save(r);
			
		} catch (CajaException e) {
			// TODO Auto-generated catch block
			error = e.getMessage();
		} catch (EntityInvalidaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return error;
	}

	@Override
	public String RealizarReserva(int cantPersonas, java.util.Date fecha,
			int idUsuario) throws RemoteException {
		// TODO Auto-generated method stub
		Restaurante r = Controller.cajeroDAO.GetByUsuario(idUsuario)
				.getRestaurante();
		try {
			Reserva reserv = r.addReserva(cantPersonas, fecha);
			Controller.restauranteDAO.save(r);
			return "Se ha agregado la reserva satisfactoriamente con id: "
					+ reserv.getId();
		} catch (CajaException e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		} catch (EntityInvalidaException e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		}

	}

	@Override
	public String AbrirMesaConReserva(int reserva, int idUsuario)
			throws RemoteException {
		// TODO Auto-generated method stub
		Cajero c = Controller.cajeroDAO.GetByUsuario(idUsuario);
		Restaurante r = c.getRestaurante();
		Reserva res = r.getReservaById(reserva);
		if (res != null) {

			try {
				Mesa m = r.AbrirMesaConReserva(res);
				Mozo mozo = r.AsignarMozoAMesa(m, c.getTurno());

				if (mozo != null && m != null) {
					r.AbrirComanda(m, mozo);
				}

				Controller.restauranteDAO.save(r);
				return "se ha abierto la mesa : " + m.getId()
						+ " en el sector : " + m.getSector().getNombre()
						+ " por el mozo : " + mozo.getNombreEmpleado();
			} catch (CajaException e) {
				// TODO Auto-generated catch block
				return e.getMessage();
			} catch (EntityInvalidaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			return "No existe la reserva numero : " + reserva;
		}

		return "error";
	}

	@Override
	public String AbrirMesaSinReserva(int cantPersonas, java.util.Date fecha,
			int idUsuario) throws RemoteException {
		// TODO Auto-generated method stub
		Cajero c = Controller.cajeroDAO.GetByUsuario(idUsuario);
		Restaurante r = c.getRestaurante();
		try {
			Mesa m = r.AbrirMesaSinReserva(cantPersonas, fecha);
			Mozo mozo = r.AsignarMozoAMesa(m, c.getTurno());

			if (mozo != null && m != null) {
				r.AbrirComanda(m, mozo);
			}

			Controller.restauranteDAO.save(r);
			return "se ha abierto la mesa : " + m.getId() + " en el sector : "
					+ m.getSector().getNombre() + " por el mozo : "
					+ mozo.getNombreEmpleado();
		} catch (CajaException e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		} catch (EntityInvalidaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "error";
	}

	@Override
	public String GenerarFactura(int nroMesa, int idUsuario)
			throws RemoteException {
		// TODO Auto-generated method stub
		Cajero c = Controller.cajeroDAO.GetByUsuario(idUsuario);
		Restaurante r = c.getRestaurante();
		try {
			Factura f = r.GenerarFactura(nroMesa);
			this.restauranteDAO.save(r);
			return "Se ha generado la factura nro: " + f.getNumero();
		} catch (CajaException e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		} catch (EntityInvalidaException e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		}
	}

	@Override
	public String AgregarItemCobro(int factura, int cantidad, float valor,
			String origen, int idUsuario) throws RemoteException {
		// TODO Auto-generated method stub
		Cajero c = Controller.cajeroDAO.GetByUsuario(idUsuario);
		Restaurante r = c.getRestaurante();
		Factura f = r.getFacturaByNro(factura);

		if (f != null) {
			if ((origen.toUpperCase().equals("DEBITO") || origen.toUpperCase()
					.equals("CREDITO")) && f.getTotal() != (cantidad * valor)) {
				return "Si el pago es DEBITO/CREDITO el monto debe ser igual al total : "
						+ f.getTotal();
			}

			if (f.getTotalPago() >= f.getTotal()) {
				return "Ya se alcanzo el monto o se supero, no se pueden agregar mas items";
			}

			if (r.hayCajaAbierta(c)) {
				return "No hay ninguna caja abierta o la que hay no fue abierta por este cajero";
			}

			ItemCaja itCaja = new ItemCaja();
			itCaja.setCantidad(cantidad);
			itCaja.setOrigen(origen);
			itCaja.setValor(valor);

			f.addItemCaja(itCaja);

			try {
				this.restauranteDAO.save(r);
				float parcialCaja = f.getTotalPago();
				float faltaPagar = f.getTotal() - parcialCaja;
				if (faltaPagar < 0) {
					faltaPagar = 0;
				}
				return "se ha creado el nuevo item, el total parcial de pago es : "
						+ parcialCaja + " faltan pagar : " + faltaPagar;
			} catch (EntityInvalidaException e) {
				// TODO Auto-generated catch block
				return e.getMessage();
			}

		} else {
			return "No existe la factura";
		}
	}

	@Override
	public String ConfirmarCobro(int factura, boolean confirmacion,
			int idUsuario) throws RemoteException {
		// TODO Auto-generated method stub
		Cajero c = Controller.cajeroDAO.GetByUsuario(idUsuario);
		Restaurante r = c.getRestaurante();
		String mensaje = "";
		Factura f = r.getFacturaByNro(factura);
		if (f != null && !f.getEstado().equals(EstadoFactura.PAGA.toString())) {
			if (confirmacion) {
				ItemTurnoCaja itTurnoCaja = r.getCajaActiva().GetUltimoItem();
				f.setEstado(EstadoFactura.PAGA.toString());
				f.setFechaPago(Calendar.getInstance().getTime());

				try {
					float vuelto = itTurnoCaja.AgregarItemsPorFactura(f);
					if (vuelto > 0) {
						vuelto = r.GenerarVuelto(f, vuelto);
					}
					mensaje = "Su vuelto fue de : " + vuelto;
				} catch (CajaException e) {
					// TODO Auto-generated catch block
					return e.getMessage();
				}
			} else {
				r.RollBackPagos(f);
				mensaje = "Se han eliminado los pagos realizados hasta el momento";
			}

			try {
				this.restauranteDAO.save(r);

				return mensaje;
			} catch (EntityInvalidaException e) {
				// TODO Auto-generated catch block
				return e.getMessage();
			}
		} else {
			return "No existe la factura o esta paga";
		}
	}

	@Override
	public String CargarItemComanda(int mesa, String plato, int cantidad,
			boolean regular, int idUsuario) throws RemoteException {
		// TODO Auto-generated method stub
		Mozo c = Controller.mozoDAO.GetByUsuario(idUsuario);
		Restaurante r = c.getRestaurante();
		try {
			r.CargarItemAComanda(c, plato, cantidad, mesa, regular);
			this.restauranteDAO.save(r);

			return "Se cargo el item a la comanda";
		} catch (EntityInvalidaException e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		} catch (MozoException e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		} catch (AreaPreparacionException e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		}
	}

	@Override
	public String CerrarMesa(int nroMesa, int idUsuario) throws RemoteException {
		// TODO Auto-generated method stub
		Mozo c = Controller.mozoDAO.GetByUsuario(idUsuario);
		Restaurante r = c.getRestaurante();
		try {
			float tiempoPromedioACerrar = r.CerrarMesa(nroMesa);
			this.restauranteDAO.save(r);

			return "El tiempo promedio es de : " + tiempoPromedioACerrar;
		} catch (EntityInvalidaException e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		} catch (MozoException e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		}

	}

	@Override
	public String AbrirNuevaCarta(java.util.Date fechaVigencia, int idUsuario)
			throws RemoteException {
		// TODO Auto-generated method stub
		JefeAreaPreparacion jefe = this.jefeAreaDAO.GetByUsuario(idUsuario);
		Restaurante r = jefe.getRestaurante();

		try {
			r.GenerarNuevaCarta(fechaVigencia, jefe);
			this.restauranteDAO.save(r);
			r.setCartaActualASectores();
			this.restauranteDAO.save(r);
			return "Se ha generado la nueva carta con vigencia hasta : "
					+ fechaVigencia;
		} catch (EntityInvalidaException e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		} catch (AreaPreparacionException e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		}
	}

	@Override
	public String CargarItemCarta(String plato, float precio, int idUsuario)
			throws RemoteException {
		// TODO Auto-generated method stub
		JefeAreaPreparacion jefe = this.jefeAreaDAO.GetByUsuario(idUsuario);
		Restaurante r = jefe.getRestaurante();

		try {
			r.AgregarItemCarta(plato, precio);
			this.restauranteDAO.save(r);

			return "Se agrego el item a la carta";
		} catch (EntityInvalidaException e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		} catch (AreaPreparacionException e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		}
	}

	@Override
	public List<ItemPlanDeProduccionDTO> GetPlanDeProduccion(int idUsuario)
			throws RemoteException {
		List<ItemPlanDeProduccion> items = new ArrayList<ItemPlanDeProduccion>();
		JefeAreaPreparacion jefe = this.jefeAreaDAO.GetByUsuario(idUsuario);
		if (jefe != null) {
			items = itemppDAO.getAll(jefe.getRestaurante().getId());
		} else {
			items = itemppDAO.getAll();
		}

		Iterator<ItemPlanDeProduccion> itemsI = items.iterator();
		List<ItemPlanDeProduccionDTO> dtos = new ArrayList<ItemPlanDeProduccionDTO>();
		while (itemsI.hasNext()) {
			dtos.add(itemsI.next().generarDTO());
		}
		return dtos;
	}

	@Override
	public String LiberarTarea(int tarea, int idUsuario) throws RemoteException {
		// TODO Auto-generated method stub
		JefeAreaPreparacion jefe = this.jefeAreaDAO.GetByUsuario(idUsuario);
		Restaurante r = jefe.getRestaurante();
		List<ItemPlanDeProduccion> items = itemppDAO.getAll(jefe
				.getRestaurante().getId());

		if (items != null) {
			ItemPlanDeProduccion item = null;
			for (ItemPlanDeProduccion itemPlanDeProduccion : items) {
				if (itemPlanDeProduccion.getId() == tarea) {
					item = itemPlanDeProduccion;
				}
			}

			if (item != null && item.isLibre()) {

				try {
					r.LiberarItemPlan(item);
					this.itemppDAO.save(item);
					this.restauranteDAO.save(r);
					return "La tarea se ha liberado satisfactoriamente";
				} catch (EntityInvalidaException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (AreaPreparacionException e) {
					// TODO Auto-generated catch block
					return e.getMessage();
				}

				return "se ha finalizado el item, la mercadera ha sido agregada al deposito local";
			} else {
				return "no existe el item con id o la tarea no esta disponible: "
						+ tarea;
			}
		} else {
			return "no hay tareas disponibles para liberar";
		}
	}

	@Override
	public List<ItemCartaDTO> ConsultarCarta(int idUsuario)
			throws RemoteException {
		// TODO Auto-generated method stub
		Empleado emp = this.empleadoDAO.getEmpleadoByUserId(idUsuario);
		Restaurante r = emp.getRestaurante();
		List<ItemCarta> items = r.getCartaActual().getPlatos();
		List<ItemCartaDTO> itemsDTO = new ArrayList<ItemCartaDTO>();
		for (ItemCarta itemCarta : items) {
			itemsDTO.add(itemCarta.generarDTO());
		}
		return itemsDTO;
	}

	@Override
	public List<ItemAReponerDTO> ConsultarCompras(int idUsuario)
			throws RemoteException {
		// TODO Auto-generated method stub
		List<ItemAReponer> items = new ArrayList<ItemAReponer>();
		List<ItemAReponerDTO> itemsDTO = new ArrayList<ItemAReponerDTO>();
		JefeAreaPreparacion jefe = this.jefeAreaDAO.GetByUsuario(idUsuario);
		Restaurante r = jefe.getRestaurante();
		if (r.getBarra().getJefe().getId() == jefe.getId()) {
			items = r.getBarra().GetItemsAReponer();
		} else if (r.getCocina().getJefe().getId() == jefe.getId()) {
			items = r.getCocina().GetItemsAReponer();
		} else if (r.getCafeteria().getJefe().getId() == jefe.getId()) {
			items = r.getCafeteria().GetItemsAReponer();
		}

		for (ItemAReponer itemAReponer : items) {
			itemsDTO.add(itemAReponer.generarItemAReponerDTO());
		}

		return itemsDTO;
	}

	@Override
	public String RealizarPedido(String producto, int cantidad, int idUsuario)
			throws RemoteException {
		// TODO Auto-generated method stub
		JefeAreaPreparacion jefe = this.jefeAreaDAO.GetByUsuario(idUsuario);
		Restaurante r = jefe.getRestaurante();
		try {
			int cantidadMovida = r.RealizarMovimientoDeStock(producto,
					cantidad, jefe);
			this.restauranteDAO.save(r);
			return "Se ha realizado un movimiento de stock para el producto : "
					+ producto + " con una cantidad : " + cantidad;
		} catch (AreaPreparacionException e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		} catch (EntityInvalidaException e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		}
	}

	@Override
	public PlatoDTO buscarPlatoSemi(String nombre) throws RemoteException {
		// TODO Auto-generated method stub
		Plato p = Controller.platoDAO.GetByNombreSemi(nombre);

		if (p != null) {
			return p.generarDTOdePlato();
		}
		return null;
	}
}
