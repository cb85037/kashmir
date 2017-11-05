package administracion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.BarraDAO;
import dao.CajaDAO;
import dao.EmpleadoDAO;
import dao.LocalDAO;
import dto.DTO_Empleado;
import entity.Entity_Caja;
import entity.Entity_CajaDiaria;
import entity.Entity_Encargado;
import entity.Entity_Local;

public class Gestor_Caja {

	private static Gestor_Caja instancia;
	private CajaDAO dao = CajaDAO.getInstancia();
	
	private Gestor_Caja() {
		
	}
	
	public static Gestor_Caja getInstancia(){
		if(instancia == null)
			instancia = new Gestor_Caja();
		return instancia;
	}

	public boolean abrirCajaDiaria(DTO_Empleado emp) {
		boolean resp = false;
		Entity_Encargado cajero = (Entity_Encargado) EmpleadoDAO.getInstancia().getEmpleadoLegajo(emp.getLegajo());
		
		Entity_CajaDiaria c = new Entity_CajaDiaria();
		c.setCajero(cajero);
		c.setFechaApertura(new Date());
		c.setMontoInicial(2000);
		
		dao.altaCajaDiaria(c);
		
		Entity_Local suc = LocalDAO.getInstancia().buscarSucursalCajero(emp);
		Entity_Caja caja = suc.getCaja();
		List<Entity_CajaDiaria> diarias = new ArrayList<Entity_CajaDiaria>();
		
		for(Entity_CajaDiaria x: caja.getCajasDiarias()){
			diarias.add(x);
		}
		diarias.add(c);
		caja.setCajasDiarias(diarias);
		
		dao.mergeCaja(caja);

		resp = true;
		
		return resp;
	}
	
	public boolean cerrarCajaDiaria(DTO_Empleado emp, float montoCierre){
		String test;
		Entity_CajaDiaria cajaDiaria = dao.buscarPorEncargado(emp.getLegajo());
		if(cajaDiaria != null){
			cajaDiaria.setFechaCierre(new Date());
			cajaDiaria.setMontoCierre(montoCierre);
			dao.mergeCajaDiaria(cajaDiaria);
			return true;
		}
		return false;
	}
	
	
	
	
}
