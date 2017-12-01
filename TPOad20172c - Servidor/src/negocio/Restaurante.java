package Negocio;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import dao.ProductoDAO;

import exceptions.AreaPreparacionException;
import exceptions.CajaException;
import exceptions.EntityInvalidaException;
import exceptions.MozoException;

import repositorio.Cauducidad;
import repositorio.EstadoFactura;
import repositorio.OrigenDestino;
import repositorio.RestauranteDTO;
import repositorio.TurnoDTO;

@Entity
@Table(name="restaurantes")
public class Restaurante implements Serializable {

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_cocina")
	private Cocina cocina;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_barra")
	private Barra barra;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_cafeteria")
	private Cafeteria cafeteria;
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="id_restaurante")
	private List<Sector> sectores;
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="id_restaurante")
	private List<Empleado> empleados;
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="id_restaurante")
	private List<Caja> historicoCaja;
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="id_restaurante")
	private List<Comanda> comandas;
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="id_restaurante")
	private List<Factura> facturas;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_deposito")
	private DepositoLocal deposito;
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="id_restaurante")
	private List<Carta> cartas;
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="id_restaurante")
	private List<Plato> platos;
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="id_restaurante")
	private List<Reserva> reservas;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_restaurante")
	private int id;
	
	public Restaurante() {
		super();
	}

	public Cocina getCocina() {
		return cocina;
	}

	public void setCocina(Cocina cocina) {
		this.cocina = cocina;
	}

	public Barra getBarra() {
		return barra;
	}

	public void setBarra(Barra barra) {
		this.barra = barra;
	}

	public Cafeteria getCafeteria() {
		return cafeteria;
	}

	public void setCafeteria(Cafeteria cafeteria) {
		this.cafeteria = cafeteria;
	}

	public List<Sector> getSectores() {
		return sectores;
	}

	public void setSectores(List<Sector> sectores) {
		this.sectores = sectores;
	}

	public List<Empleado> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}

	public List<Caja> getHistoricoCaja() {
		return historicoCaja;
	}

	public void setHistoricoCaja(List<Caja> historicoCaja) {
		this.historicoCaja = historicoCaja;
	}

	public List<Comanda> getComandas() {
		if(this.comandas == null){
			this.comandas = new ArrayList<Comanda>();
		}
		return comandas;
	}

	public void setComandas(List<Comanda> comandas) {
		this.comandas = comandas;
	}

	public List<Factura> getFacturas() {
		if(this.facturas == null){
			this.facturas = new ArrayList<Factura>();
		}
		return facturas;
	}

	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}

	public DepositoLocal getDeposito() {
		return deposito;
	}

	public void setDeposito(DepositoLocal deposito) {
		this.deposito = deposito;
	}

	public List<Carta> getCartas() {
		return cartas;
	}

	public void setCartas(List<Carta> cartas) {
		this.cartas = cartas;
	}

	public List<Plato> getPlatos() {
		if(this.platos == null){
			this.platos = new ArrayList<Plato>();
		}
		
		return this.platos;
	}

	public void setPlatos(List<Plato> platos) {
		this.platos = platos;
	}

	public List<Reserva> getReservas() {
		if(this.reservas == null){
			this.reservas = new ArrayList<Reserva>();
		}
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	/***********************************
	 ******METODOS DTO******************
	 ***********************************/
	
	public RestauranteDTO generarDTOdeRestaurante(){
		RestauranteDTO dto = new RestauranteDTO();
		if(!(this.id != 0)){
			dto.setId(id);
		}
		return dto;
	}
	
	public static Restaurante crearInstanciaDeRestaurante(RestauranteDTO dto){
		Restaurante restaurante = new Restaurante();
		restaurante.setId(dto.getId());
		return restaurante;
	}

	public void addEmpleado(Empleado c) {
		if(this.empleados == null){
			this.empleados = new ArrayList<Empleado>();
		}
		this.empleados.add(c);
	}

	public void LimpiarItemsVencidos() {
		// TODO Auto-generated method stub
		this.barra.LimpiarItemsVencidos();
		this.cocina.LimpiarItemsVencidos();
		this.cafeteria.LimpiarItemsVencidos();
		this.deposito.LimparItemsVencidos();
	}

	public void ReponerEnSectoresDeDepositoLocal() {
		// TODO Auto-generated method stub
		List<ItemAReponer> total = new ArrayList<ItemAReponer>();
		List<ItemAReponer> itemsBarra = this.barra.GetItemsAReponer();
		List<ItemAReponer> itemsCocina = this.cocina.GetItemsAReponer();
		List<ItemAReponer> itemsCafeteria = this.cafeteria.GetItemsAReponer();
		
		for (ItemAReponer itemAReponer : total) {
			itemAReponer.setRestaurante(this);
		}
		
		this.deposito.RealizarMovimientoDeStock(itemsBarra, this.barra);
		this.deposito.RealizarMovimientoDeStock(itemsCocina, this.cocina);
		this.deposito.RealizarMovimientoDeStock(itemsCafeteria, this.cafeteria);
	}

	public ItemTurnoCaja AbrirTurnoCaja(Cajero c) throws CajaException {
		// TODO Auto-generated method stub
		ItemTurnoCaja item = null;
		if(this.historicoCaja == null || this.historicoCaja.size() == 0){
			return this.CargarPrimeraCaja(c);
		} 
		
		Caja cajaDelDia = this.GetCajaDelDia();
		
		if(cajaDelDia != null){
			item = cajaDelDia.IniciarNuevoTurno(c, null);
		} else {
			cajaDelDia = new Caja();
			Date d = new Date(Calendar.getInstance().getTime().getTime());
			cajaDelDia.setFechaInicio(d);
			Caja ayer = this.GetCajaAnterior(cajaDelDia);
			item = cajaDelDia.IniciarNuevoTurno(c, ayer);
			this.historicoCaja.add(cajaDelDia); 
		}
		
		return item;
	}
	
	private Caja GetCajaAnterior(Caja cajaDelDia) {
		// TODO Auto-generated method stub
		Iterator<Caja> itCaja = this.getHistoricoCaja().iterator();
		while(itCaja.hasNext()){
			Caja c1 = itCaja.next();
			if(c1.getFechaInicio().before(cajaDelDia.getFechaInicio()) && !itCaja.hasNext()){
				return c1;
			}
		}
		
		return itCaja.next();
	}

	public ItemTurnoCaja CerrarTurnoCaja(Cajero c) throws CajaException{
		ItemTurnoCaja item = null;
		if(this.historicoCaja == null || this.historicoCaja.size() == 0){
			throw new CajaException("no hay cajas abieras en el sistema");
		} 
		
		Caja cajaDelDia = this.GetCajaDelDia();
		if(cajaDelDia != null){
			item = cajaDelDia.CerrarItemCaja(c);
		} else{
			throw new CajaException("no hay cajas abiertas para el dia de hoy");
		}
		return item;
	}

	private Caja GetCajaDelDia() {
		// TODO Auto-generated method stub

		int year = Calendar.getInstance().get(Calendar.YEAR);
		int month = Calendar.getInstance().get(Calendar.MONTH);
		int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		Date d = new Date(year - 1900,
				month,
				day);
		for (Caja c : this.historicoCaja) {
			if(c.getFechaInicio().compareTo(d) == 0){
				return c;
			}
		}
		return null;
	}

	private ItemTurnoCaja CargarPrimeraCaja(Cajero cajero) {
		// TODO Auto-generated method stub
		this.historicoCaja = new ArrayList<Caja>();
		Caja c = new Caja();
		Date d = new Date(Calendar.getInstance().getTime().getTime());
		c.setFechaInicio(d);
		ItemTurnoCaja item = new ItemTurnoCaja();
		item.setCajeroEncargado(cajero);
		item.setFinalizado(false);
		this.CargarItemsPrimeraVez(item);
		c.AddItem(item);
		this.historicoCaja.add(c);
		return item;
	}

	private void CargarItemsPrimeraVez(ItemTurnoCaja item) {
		// TODO Auto-generated method stub
		ItemCaja it1 = new ItemCaja();
		it1.setCantidad(100);
		it1.setOrigen("Efectivo");
		it1.setValor(10);
		
		ItemCaja it2 = new ItemCaja();
		it2.setCantidad(500);
		it2.setOrigen("Efectivo");
		it2.setValor(5);
		
		ItemCaja it3 = new ItemCaja();
		it3.setCantidad(1000);
		it3.setOrigen("Efectivo");
		it3.setValor(1);
		
		item.AgregarItem(it3);
		item.AgregarItem(it2);
		item.AgregarItem(it1);
	}

	public Caja getCajaActiva() {
		// TODO Auto-generated method stub
		return this.GetCajaDelDia();
	}

	public ItemTurnoCaja getTurnoActivo() {
		// TODO Auto-generated method stub
		Caja c = this.GetCajaDelDia();
		for (ItemTurnoCaja it : c.getItems()) {
			if(!it.isFinalizado()){
				return it;
			}
		}
		
		return new ItemTurnoCaja();
	}

	public List<ItemComisionMozo> getComisionMozosDelDia() {
		// TODO Auto-generated method stub
		Date d = new Date(Calendar.getInstance().getTime().getTime());
		List<ItemComisionMozo> items = new ArrayList<ItemComisionMozo>();
		for (Comanda c : this.GetComandasCerradasDelDia()) {
			ItemComisionMozo it = new ItemComisionMozo();
			it.setMozo(c.getMozo());
			it.setFecha(d);
			it.setTotal(c.calcularComisionMozo());
			items.add(it);
		}
		
		return items;
	}

	public void crearComisionMozoDelDia(){
		List<ItemComisionMozo> items = new ArrayList<ItemComisionMozo>();
		items = this.getComisionMozosDelDia();
		Caja c = this.getCajaActiva();
		c.setItemsComisionMozo(items);
	}
	
	private List<Comanda> GetComandasCerradasDelDia() {
		// TODO Auto-generated method stub
		int year = Calendar.getInstance().get(Calendar.YEAR);
		int month = Calendar.getInstance().get(Calendar.MONTH);
		int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		Date d = new Date(year - 1900,
				month,
				day);
		
		List<Comanda> comandasCerradas = new ArrayList<Comanda>();
		for (Comanda comanda : this.comandas) {
			if(comanda.isFinalizada()){
				comandasCerradas.add(comanda);
			}
		}
		
		return comandasCerradas;
	}

	public ItemTurnoCaja CerrarTurnoCaja(Cajero c,
			List<ItemComisionMozo> comisionMozosDelDia) throws CajaException {
		// TODO Auto-generated method stub
		
		ItemTurnoCaja item = null;
		if(this.historicoCaja == null || this.historicoCaja.size() == 0){
			throw new CajaException("no hay cajas abieras en el sistema");
		} 
		
		Caja cajaDelDia = this.GetCajaDelDia();
		if(cajaDelDia != null && cajaDelDia.getFechaCierre() == null){
			item = cajaDelDia.CerrarItemCaja(c);
			cajaDelDia.setItemsComisionMozo(comisionMozosDelDia);
			Date d = new Date(Calendar.getInstance().getTime().getTime());
			cajaDelDia.setFechaCierre(d);
		} else{
			throw new CajaException("no hay cajas abiertas para el dia de hoy");
		}
		return item;
		
	}

	public Reserva addReserva(int cantPersonas, java.util.Date fecha) throws CajaException {
		// TODO Auto-generated method stub
		Reserva res = new Reserva();
		res.setHora(fecha);
		res.setCantidadHoras(cantPersonas);
		int cantPersonasReserva = 0;
		int cantPersonasPosibles = 0;
		for (Reserva r : this.GetReservasDelDia(fecha)) {
			cantPersonasReserva = r.getCantidadHoras() + cantPersonasReserva;
		}
		
		cantPersonasPosibles = this.GetCantidadDePersonasSimultaneasEnRestaurante();
		if((cantPersonasPosibles - cantPersonasReserva) >= cantPersonas){
			this.getReservas().add(res);
			return res;
		} else{
			throw new CajaException("La reserva no puede realizarse, no hay lugar disponible");
		}
	}
	
	private int GetCantidadDePersonasSimultaneasEnRestaurante() {
		// TODO Auto-generated method stub
		int total = 0;
		for (Sector sec : this.getSectores()) {
			for (Mesa m : sec.getMesas()) {
				total = total + m.getMaxPersonas();
			}
		}
		return total;
	}

	private List<Reserva> GetReservasDelDia(java.util.Date fecha){
		List<Reserva> delDia = new ArrayList<Reserva>();
		Iterator<Reserva> reservas = this.reservas.iterator();
		while(reservas.hasNext()){
			Reserva r = reservas.next();

			if(r.getHora().compareTo(fecha) == 0){
				delDia.add(r);
			}
		}
		return delDia;
	}

	public Reserva getReservaById(int reserva) {
		// TODO Auto-generated method stub
		for (Reserva r : this.getReservas()) {
			if(r.getId() == reserva){
				return r;
			}
		}
		
		return null;
	}

	public Mesa AbrirMesaConReserva(Reserva res) throws CajaException {
		// TODO Auto-generated method stub
//		if(res.getHora().compareTo(Calendar.getInstance().getTime()) != 0){
//			throw new CajaException("La reserva no es para la fecha solicitada : " + res.getHora().toString());
//		}
		
		return this.AbrirMesa(res.getCantidadHoras());
	}
	
	public Mesa AbrirMesa(int cantComensales) throws CajaException{
		Mesa m = null;
		for (Sector s : this.getSectores()) {
			m = s.AbrirMesa(cantComensales);
			if(m != null){
				m.setEstado(false);
				break;
			}
		}
		
		if(m == null){
			throw new CajaException("No hay mesas disponibles");
		}
		
		return m;
	}

	public Mozo AsignarMozoAMesa(Mesa m, Turno t) throws CajaException {
		// TODO Auto-generated method stub
		List<Mozo> mozos = this.getMozosPorSectorYTurno(m.getSector(), t);
		for (Mozo mozo : mozos) {
			if(getComandasPorMozoAbierta(mozo).size() <= 5){
				return mozo;
			}
		}
		throw new CajaException("No se ha encontrado un mozo disponible");
	}
	
	private List<Comanda> getComandasPorMozoAbierta(Mozo m){
		List<Comanda> comandas = new ArrayList<Comanda>();
		for (Comanda c : this.getComandas()) {
			if(!c.isFinalizada() && c.getMozo().getId() == m.getId()){
				comandas.add(c);
			}
		}
		return comandas;
	}
	
	private List<Mozo> getMozosPorSectorYTurno(Sector s, Turno t){
		List<Mozo> mozos = new ArrayList<Mozo>();
		for (Mozo emp : this.getMozos()) {
			if(emp.getSector() != null && emp.getSector().getId() == s.getId()
					&& emp.getTurno().getId() == t.getId()){
				mozos.add(emp);
			}
		}
		return mozos;
	}
	
	private List<Mozo> getMozos(){
		List<Mozo> mozos = new ArrayList<Mozo>();
		for (Empleado emp : this.empleados) {
			if(emp.soyMozo()){
				mozos.add((Mozo) emp);
			}
		}
		return mozos;
	}

	public void AbrirComanda(Mesa mesa, Mozo mozo) {
		// TODO Auto-generated method stub
		Comanda comanda = new Comanda();
		comanda.setFinalizada(false);
		comanda.setMesa(mesa);
		comanda.setMozo(mozo);
		
		if(this.comandas == null){
			this.comandas = new ArrayList<Comanda>();
		}
		
		this.comandas.add(comanda);
	}

	public Mesa AbrirMesaSinReserva(int cantPersonas, java.util.Date fecha) throws CajaException {
		// TODO Auto-generated method stub
		int cantpersonasreserva = 0;
		Mesa mesa = null;
		for (Reserva reserva : this.GetReservasDelDia(fecha)) {
			cantpersonasreserva = cantpersonasreserva + reserva.getCantidadHoras();
		}
		
		for (Sector s : this.getSectores()) {
			for (Mesa m : s.getMesasLibres()) {
				cantpersonasreserva = cantpersonasreserva - m.getMinPersonas();
			}
		}
		
		if(cantpersonasreserva + cantPersonas <= 0){
			mesa = this.AbrirMesa(cantPersonas);
		} else{
			throw new CajaException("No hay lugar disponible");
		}
		
		return mesa;
	}

	public Factura GenerarFactura(int nroMesa) throws CajaException {
		// TODO Auto-generated method stub
		Comanda c = this.getComandaByMesa(nroMesa);
		if(c != null){
			
			if(c.getItemsComanda() == null || c.getItemsComanda().size() == 0){
				throw new CajaException("No hay items para facturar");
			}
			Factura f = new Factura();
			f.setComanda(c);
			f.setEstado(EstadoFactura.PENDIENTE.toString());
			Iterator<ItemComanda> items = c.getItemsComanda().iterator();
			while(items.hasNext()){
				ItemComanda item = items.next();
				ItemFactura itF = new ItemFactura(item.getPlatoPrecio().getPlato().getNombre(), item.getPlatoPrecio().getPrecio());
				f.addItem(itF);
			}
			
			this.getFacturas().add(f);
			
			return f;
		} else {
			throw new CajaException("No existe la comanda indicada");
		}
	}
	
	private Comanda getComandaByMesa(int nroMesa) {
		// TODO Auto-generated method stub
		for (Comanda c : this.getComandas()) {
			if(!c.isFinalizada() && c.getMesa().getId() == nroMesa){
				return c;
			}
		}
		return null;
	}

	private Comanda getComandaById(int nroComanda){
		for (Comanda c : this.getComandas()) {
			if(c.getId() == nroComanda){
				return c;
			}
		}
		
		return null;
	}

	public Factura getFacturaByNro(int factura) {
		// TODO Auto-generated method stub
		for (Factura  f : this.getFacturas()) {
			if(f.getNumero() == factura){
				return f;
			}
		}
		
		return null;
	}

	public void RollBackPagos(Factura f) {
		// TODO Auto-generated method stub
		f.setItemsCaja(null);
	}

	public float GenerarVuelto(Factura f, float totalADevolver) throws CajaException {
		// TODO Auto-generated method stub
		float vuelto = totalADevolver;
		Caja c = this.getCajaActiva();
		ItemTurnoCaja itTurnoCaja = c.GetUltimoItem();
		
			
			while(totalADevolver > 0){
				Collections.sort(itTurnoCaja.getItems(), Collections.reverseOrder());
				Iterator<ItemCaja> itemsCajaIt = itTurnoCaja.getItems().iterator();
				while(itemsCajaIt.hasNext()){
					ItemCaja next = itemsCajaIt.next();
					if(next.getOrigen().toUpperCase().equals("EFECTIVO")){
						if(totalADevolver >= 50 && next.getValor() == 50 && next.getCantidad() > 0){
								totalADevolver = totalADevolver - 50;
								next.setCantidad(next.getCantidad() - 1);
						} else if(totalADevolver >= 20 && next.getValor() == 20 && next.getCantidad() > 0){
							totalADevolver = totalADevolver - 20;
							next.setCantidad(next.getCantidad() - 1);
						} else if(totalADevolver >= 10 && next.getValor() == 10 && next.getCantidad() > 0){
								totalADevolver = totalADevolver - 10;
								next.setCantidad(next.getCantidad() - 1);	
						} else if(totalADevolver >= 5 && next.getValor() == 5 && next.getCantidad() > 0){
								totalADevolver = totalADevolver - 5;
								next.setCantidad(next.getCantidad() - 1);
			
						} else if(totalADevolver >= 2 && next.getValor() == 2 && next.getCantidad() > 0){
								totalADevolver = totalADevolver - 2;
								next.setCantidad(next.getCantidad() - 1);
						} else if(totalADevolver >= 1 && next.getValor() == 1 && next.getCantidad() > 0){
								totalADevolver = totalADevolver - 1;
								next.setCantidad(next.getCantidad() - 1);
					  }
					}
				}
				
				}
				
		return vuelto - totalADevolver;
	}

	public void CargarItemAComanda(Mozo m ,String p, int cantidad, int mesa, boolean regular) throws MozoException, AreaPreparacionException {
		// TODO Auto-generated method stub
		ItemCarta itCarga = this.getCocina().getItemCartaByNombre(p);
		if(itCarga == null){
			throw new MozoException("No existe el plato en la carta");
		}
		
		Comanda c = this.buscarComandaPorMesa(mesa);
		if(c == null){
				throw new MozoException("No existe la comanda");
		}
		
		if(c.getMozo().getId() != m.getId()){
			throw new MozoException("El mozo no es el asignado a la comanda");
		}
		
	    itCarga.getPlato().getArea().prepararPlato(itCarga.getPlato(), cantidad);
		
		for (int i = 0; i <= cantidad - 1; i++) {
			ItemComanda itComanda = new ItemComanda();
			itComanda.setPlatoPrecio(itCarga);
			itComanda.setRegular(regular);
			c.AgregarItem(itComanda);
		}
	}

	public float CerrarMesa(int nroMesa) throws MozoException {
		// TODO Auto-generated method stub
		Comanda c = this.buscarComandaPorMesa(nroMesa);
		if(c == null){
			throw new MozoException("La mesa no tiene ninguna comanda asignada");
		}
		Factura f = this.getFacturaByComanda(c);
		
		if(f == null){
			throw new MozoException("La mesa todavia no fue facturada");
		}
		
		if(f.getEstado() == EstadoFactura.PENDIENTE.toString()){
			throw new MozoException("La factura no fue pagada todavia");
		}
		
		Date d = new Date(Calendar.getInstance().getTimeInMillis());
		c.setFechaLiberacionMesa(d);
		c.setFinalizada(true);
		
		this.LiberarMesa(c.getMesa());
		
		return this.getPromedioLiberacion();
	}

	private void LiberarMesa(Mesa mesa) {
		// TODO Auto-generated method stub
		Sector s = mesa.getSector();
		s.LiberarMesa(mesa);
	}

	private float getPromedioLiberacion() {
		// TODO Auto-generated method stub
		float tiempoLiberacion = 15;
		if(this.getFacturas().size() > 0){
			for (Factura f : this.getFacturasPagas()) {
				java.util.Date dateFactura = f.getFechaPago();
				java.util.Date fechaLiberacion = f.getComanda().getFechaLiberacionMesa();
				Long dateInLong = fechaLiberacion.getTime() - dateFactura.getTime();
				int minutes = (int)TimeUnit.MILLISECONDS.toMinutes(dateInLong);
				tiempoLiberacion = minutes + tiempoLiberacion;
			}
			return tiempoLiberacion / this.getFacturas().size();
		}
		return tiempoLiberacion;
	}

	private List<Factura> getFacturasPagas() {
		// TODO Auto-generated method stub
		List<Factura> pagas = new ArrayList<Factura>();
		for (Factura factura : this.getFacturas()) {
			if(factura.getComanda().isFinalizada()){
				pagas.add(factura);
			}
		}
		return pagas;
	}

	private Factura getFacturaByComanda(Comanda c) {
		// TODO Auto-generated method stub
		for (Factura f : this.getFacturas()) {
			if(f.getComanda().getId() == c.getId()){
				return f;
			}
		}
		
		return null;
	}

	private Comanda buscarComandaPorMesa(int nroMesa) {
		// TODO Auto-generated method stub
		for (Comanda c : this.getComandas()) {
			if(!c.isFinalizada() && c.getMesa().getId() == nroMesa){
				return c;
			}
		}
		
		return null;
	}

	public void GenerarNuevaCarta(java.util.Date fechaVigencia, JefeAreaPreparacion jefe) throws AreaPreparacionException {
		// TODO Auto-generated method stub
		if(jefe.getId() != this.getCocina().getJefe().getId()){
			throw new AreaPreparacionException("Solo el jefe de la cocina puede abrir una carta");
		}
		
		Carta cartaActual = this.getCocina().getCartaActual();
		java.util.Date date = Calendar.getInstance().getTime(); 
		if(cartaActual != null && date.compareTo(cartaActual.getFechaDeFin()) < 0 ){
			throw new AreaPreparacionException("Todavia hay una caja en vigencia, vence el : " + cartaActual.getFechaDeFin());
		}
		
		Carta nuevaCarta = new Carta();
		nuevaCarta.setFechaDeFin(fechaVigencia);
		nuevaCarta.setDisponible(true);
		
		this.getCartas().add(nuevaCarta);
		if(cartaActual != null){
			cartaActual.setDisponible(false);	
		}
		
		
	}
	
	public void setCartaActualASectores(){
		Carta c = this.getCartaActual();
		this.getCafeteria().setCartaActual(c);
		this.getBarra().setCartaActual(c);
		this.getBarra().setCartaActual(c);
		this.getCocina().setCartaActual(c);
	}

	public Carta getCartaActual() {
		// TODO Auto-generated method stub
		for (Carta c : this.getCartas()) {
		if(c.isDisponible()){
			return c;
		}
		}
		
		return null;
	}

	public void AgregarItemCarta(String plato, float precio) throws AreaPreparacionException {
		// TODO Auto-generated method stub
		Plato p = this.getPlatoByNombre(plato);
		if(p == null){
			throw new AreaPreparacionException("No existe el plato indicado");
		}
		
		ItemCarta itemCarta = new ItemCarta();
		itemCarta.setDisponible(true);
		itemCarta.setPlato(p);
		itemCarta.setPrecio(precio);
		
		Carta cActual = this.getCocina().getCartaActual();
		if(cActual == null){
			throw new AreaPreparacionException("Cree una carta primero");
		}
		
		cActual.AgregarNuevoItem(itemCarta);
	}

	private Plato getPlatoByNombre(String plato) {
		// TODO Auto-generated method stub
		for (Plato p : this.getPlatos()) {
			if(p.getNombre().equals(plato)){
				return p;
			}
		}
		return null;
	}

	public void LiberarItemPlan(ItemPlanDeProduccion item) throws AreaPreparacionException, EntityInvalidaException {
		// TODO Auto-generated method stub
		ProductoDAO prodDao = ProductoDAO.getProductoDAO();
		this.getCocina().prepararPlato(item.getSemielaborado(), item.getCantidad());
		Producto p = prodDao.GetByNombre(item.getSemielaborado().getNombre());
		if(p == null){
			p = new Producto();
			p.setCauducidad(Cauducidad.ALTA);
			p.setNombre(item.getSemielaborado().getNombre());
			p.setOrigenDestino(OrigenDestino.ELABORACIONALAVENTA);
			prodDao.save(p);
		}
		
		ItemLoteCantidad itemLote = this.getCocina().buscarItemLote(p);
		if(itemLote == null){
			itemLote = new ItemLoteCantidad();
			itemLote.setArea(this.getCocina());
			itemLote.setProducto(p);
			itemLote.setStockReposicion(0);

			this.getCocina().AgregarItemEstimado(itemLote);
		}
		
		for (int i = 0; i < item.getCantidad() - 1; i++) {
			ItemProducto itProducto = new ItemProducto();
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DAY_OF_MONTH, 5);
			itProducto.setFechaVencimiento(c.getTime());
			itProducto.setLote("LoteLocal");
			itProducto.setProducto(p);
			itemLote.AddItemProducto(itProducto);
		}
		
		Plato plato = this.getPlatoByNombre(itemLote.getProducto().getNombre());
		if(plato == null){
			ItemIngrediente itIng = new ItemIngrediente();
			itIng.setCantidad(1);
			itIng.setProducto(itemLote.getProducto());
			plato = new Plato();
			plato.setArea(this.getCocina());
			plato.setComision(0);
			plato.AddIngrediente(itIng);
			plato.setSemiElaborado(false);
			plato.setNombre(itemLote.getProducto().getNombre());
		}
		
		this.getPlatos().add(plato);
		item.setLibre(false);
	}

	public int RealizarMovimientoDeStock(String producto, int cantidad, JefeAreaPreparacion jefe) throws AreaPreparacionException {
		// TODO Auto-generated method stub
		ProductoDAO prodDao = ProductoDAO.getProductoDAO();
		Producto p = prodDao.GetByNombre(producto);
		if(p == null){
			throw new AreaPreparacionException("No existe el producto indicado");
		}
		
		AreaPreparacion area = this.getArea(jefe);
		
		ItemLoteCantidad itLote = area.buscarItemLote(p);
		int cantidadAntes = itLote.getCantidad();
		if(itLote == null){
			throw new AreaPreparacionException("No existe el producto configurado para ser utilizado en el area, hable con el administrador");
		}
		List<ItemAReponer> its = new ArrayList<ItemAReponer>();
		ItemAReponer it = new ItemAReponer();
		it.setArea(area);
		it.setCantidad(cantidad);
		it.setProducto(p);
		it.setRestaurante(this);
		its.add(it);
		this.deposito.RealizarMovimientoDeStock(its, area);
		int cantidadDespues = itLote.getCantidad();
		
		if(cantidadAntes == cantidadDespues){
			throw new AreaPreparacionException("No se realizo ningun moviento dado que no hay stock");
		} else {
			MovimientoStock mov = new MovimientoStock();
			mov.setFechaYHora(Calendar.getInstance().getTime());
			mov.setMotivo("se realizo un pedido al deposito local del area" + area.getNombreSector() + " por el jefe " + jefe.getNombreEmpleado());
			mov.setUsuario(jefe.getUsuario());
			this.deposito.AgregarMovimientoStock(mov);
		}
		
		return cantidadDespues - cantidadAntes;
	}

	private AreaPreparacion getArea(JefeAreaPreparacion jefe) throws AreaPreparacionException {
		// TODO Auto-generated method stub
		if(this.getBarra().getJefe().getId() == jefe.getId()){
			return this.getBarra();
		} else if(this.getCocina().getJefe().getId() == jefe.getId()){
			return getCocina();
		}else if(this.getCafeteria().getJefe().getId() == jefe.getId()){
			return getCafeteria();
		}
		
		throw new AreaPreparacionException("El usuario no tiene permisos para realizar mov de stock");
	}

	public boolean hayCajaAbierta(Cajero c) {
		// TODO Auto-generated method stub
		Caja cajaAc = this.getCajaActiva();
		if(cajaAc != null){
			for (ItemTurnoCaja it : cajaAc.getItems()) {
				if(!it.isFinalizado() && it.getCajeroEncargado().getId() == c.getId()){
					return false;
				}
			}
		}
		return true;
	}
}
