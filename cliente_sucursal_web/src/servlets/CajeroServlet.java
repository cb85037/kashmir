package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
/*import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
*/
import controlador.BussinesDelegate;
import dto.DTO_DepositoLocal;
import dto.DTO_Empleado;
import dto.DTO_Encargado;
import dto.DTO_Factura;
import dto.DTO_Mesa;
import dto.DTO_Movimiento;


public class CajeroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public CajeroServlet() {
        super();
       
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("action").equalsIgnoreCase("despachar")){
    		String idMovimiento = request.getParameter("movimiento");
    		String legajo = request.getParameter("legajo");
    		String nombre = request.getParameter("nombre");
    		
    		
    		DTO_Empleado e = new DTO_Empleado();
    		e.setLegajo(legajo);
    		e.setNombre(nombre);
    		
    		BussinesDelegate.getInstancia().despacharMovSucursal(idMovimiento, e);
    		
    		DTO_DepositoLocal deposito = BussinesDelegate.getInstancia().getSucursalUsuario(legajo);
    		
    		List<DTO_Movimiento> movimientos1 = deposito.getMovimientos();
    		List<DTO_Movimiento> movimientos = new ArrayList<DTO_Movimiento>();
    		for(DTO_Movimiento m: movimientos1){
    			if (m.getEstado().equalsIgnoreCase("alta")){
    				movimientos.add(m);
    			}
    		}
    		
    		RequestDispatcher dispatcher;
    		request.setAttribute("empleado", e);
    		request.setAttribute("movimientosSucursal", movimientos);
			dispatcher=request.getRequestDispatcher("/Despacho.jsp");
			
    		dispatcher.forward(request, response);
		}
		
		 else if(request.getParameter("action").equalsIgnoreCase("opcionfacturacion")){
	 		String nombre = request.getParameter("nombre");
	 		String legajo = request.getParameter("legajo");
	 		
	 		DTO_Empleado e = new DTO_Empleado();
	 		e.setNombre(nombre);
	 		e.setLegajo(legajo);
	 		
	 		List<DTO_Mesa> mesasOcupadas = BussinesDelegate.getInstancia().getMesasOcupadas(legajo);
	 		
	 		RequestDispatcher dispatcher;
	 		request.setAttribute("empleado", e);
	 		request.setAttribute("mesasOcupadas", mesasOcupadas);
	 		dispatcher=request.getRequestDispatcher("/FacturarMesa.jsp");
	
	 		dispatcher.forward(request, response);
	 		
	 		
		 } 
		 
		 else if(request.getParameter("action").equalsIgnoreCase("opcionLiqComiMozo")){
		 		String nombre = request.getParameter("nombre");
		 		String legajo = request.getParameter("legajo");
		 		
		 		
		 		DTO_Encargado emp = new DTO_Encargado();
		 		emp.setNombre(nombre);
		 		emp.setLegajo(legajo);
		 		
		 		boolean resp = false;
		 		
		 		resp = BussinesDelegate.getInstancia().generarLiquidaciones(emp);
		 		RequestDispatcher dispatcher;
		 		dispatcher=request.getRequestDispatcher("/Mensaje.jsp");
    			HttpSession session = request.getSession();
		 		if (resp){
		            session.setAttribute("mensaje", "Se genero la liquidacion correctamente");
		            session.setAttribute("usuario", "");
		 		}
		 		else{
		            session.setAttribute("mensaje", "No se genero la liquidacion");
		 		}
		 		
		 		/*request.setAttribute("empleado", emp);
		 		request.setAttribute("resp", resp);
		 		dispatcher=request.getRequestDispatcher("/CajaDiaria.jsp");
				*/
		 		dispatcher.forward(request, response);
			 }
		 
		 else if(request.getParameter("action").equalsIgnoreCase("opcionAbrirCaja")){
		 		String nombre = request.getParameter("nombre");
		 		String legajo = request.getParameter("legajo");
		 		
		 		
		 		DTO_Empleado emp = new DTO_Empleado();
		 		emp.setNombre(nombre);
		 		emp.setLegajo(legajo);
		 		
		 		boolean resp = false;
		 		
		 		resp = BussinesDelegate.getInstancia().abrirCajaDiaria(emp);
		 		
		 		
		 		RequestDispatcher dispatcher;
		 		request.setAttribute("empleado", emp);
		 		request.setAttribute("resp", resp);
		 		dispatcher=request.getRequestDispatcher("/CajaDiaria.jsp");
		
		 		dispatcher.forward(request, response);
			 }
		
		 else if(request.getParameter("action").equalsIgnoreCase("facturarMesa")){
		 		String nombre = request.getParameter("nombre");
		 		String legajo = request.getParameter("legajo");
		 		boolean resp = true;
		 		
		 		DTO_Empleado e = new DTO_Empleado();
		 		e.setNombre(nombre);
		 		e.setLegajo(legajo);
		 		DTO_Factura factura = new DTO_Factura();
		 		
		 		String[] numeroMesa = request.getParameterValues("numeroMesa");
        		String aux = numeroMesa[0];
        		int idMesa = Integer.parseInt(aux);
		 		
		 		factura = BussinesDelegate.getInstancia().facturarMesa(idMesa, legajo);
		 		
		 		RequestDispatcher dispatcher;
		 		request.setAttribute("empleado", e);
		 		request.setAttribute("factura", factura);
		 		request.setAttribute("resp", resp);
		 		dispatcher=request.getRequestDispatcher("/MostrarFactura.jsp");
		
		 		dispatcher.forward(request, response);
			 }
		 else if(request.getParameter("action").equalsIgnoreCase("opcionCerrarCaja")){
		 		String nombre = request.getParameter("nombre");
		 		String legajo = request.getParameter("legajo");
		 		boolean resp = false;
		 		
		 		DTO_Empleado e = new DTO_Empleado();
		 		e.setNombre(nombre);
		 		e.setLegajo(legajo);
		 		
		 		
		 		RequestDispatcher dispatcher;
		 		request.setAttribute("empleado", e);
		 		request.setAttribute("respuesta", resp);
		 		dispatcher=request.getRequestDispatcher("/CerrarCaja.jsp");
		
		 		dispatcher.forward(request, response);
			 }
		
		 else if(request.getParameter("action").equalsIgnoreCase("cerrarCajaDiaria")){
		 		String nombre = request.getParameter("nombre");
		 		String legajo = request.getParameter("legajo");
		 		String montoCierre = request.getParameter("montoCierre");
		 		boolean resp = true;
		 		
		 		DTO_Empleado e = new DTO_Empleado();
		 		e.setNombre(nombre);
		 		e.setLegajo(legajo);
		 		
		 		//Deberia buscar la ultima caja!!
		 		resp = BussinesDelegate.getInstancia().cerrarCajaDiaria(e, Integer.parseInt(montoCierre));
		 		
		 		RequestDispatcher dispatcher;
		 		request.setAttribute("empleado", e);
		 		request.setAttribute("respuesta", resp);

		 		dispatcher=request.getRequestDispatcher("/CerrarCaja.jsp");
		
		 		dispatcher.forward(request, response);
			 }
		
		
		 else if(request.getParameter("action").equalsIgnoreCase("opcionVolver")){
	 		String nombre = request.getParameter("nombre");
	 		String legajo = request.getParameter("legajo");
	 		
	 		
	 		DTO_Empleado e = new DTO_Empleado();
	 		e.setNombre(nombre);
	 		e.setLegajo(legajo);
	 		
	 		RequestDispatcher dispatcher;
	 		request.setAttribute("empleado", e);
				dispatcher=request.getRequestDispatcher("/MenuCajero.jsp");
	 		dispatcher.forward(request, response);
		 }
			
		 /*else if(request.getParameter("action").equalsIgnoreCase("AplicarDescuento")){
		 		String nombre = request.getParameter("nombre");
		 		String legajo = request.getParameter("legajo");
		 		
		 		
		 		DTO_Empleado e = new DTO_Empleado();
		 		e.setNombre(nombre);
		 		e.setLegajo(legajo);
		 		
		 		String[] numeroMesa = request.getParameterValues("numeroMesa");
				String aux = numeroMesa[0];
				int idMesa = Integer.parseInt(aux);
		 		//aca  debo cambiar el metodo para que aplique los descuentos
		 		DTO_Factura factura=BussinesDelegate.getInstancia().facturarMesa(idMesa, legajo);
		 		
		 		RequestDispatcher dispatcher;
		 		request.setAttribute("empleado", e);
		 		request.setAttribute("factura", factura);
		 		dispatcher=request.getRequestDispatcher("/AplicarDescuentoFactura.jsp");

		 		dispatcher.forward(request, response);
		 }*/
		
		
		 
	} 
}
	

