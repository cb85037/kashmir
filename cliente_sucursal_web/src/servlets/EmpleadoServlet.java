package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controlador.BussinesDelegate;
import dto.DTO_DepositoLocal;
import dto.DTO_Empleado;
import dto.DTO_Mesa;
import dto.DTO_Movimiento;
import dto.DTO_Local;

public class EmpleadoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EmpleadoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 try {
	        
	        	 if(request.getParameter("action").equals("validarEmpleado")){
	        		//DEPENDIENDO QUE EMPLEADO SEA, ABRO UN MENU PROPIO
	        		String usuario = request.getParameter("usuario");
	        		String password = request.getParameter("password");
	        		String[] tipo = request.getParameterValues("tipo");
	        		String tipoUsuario = tipo[0];
	        		
	        		DTO_Empleado e = BussinesDelegate.getInstancia().validarUsuario(usuario, password, tipoUsuario);
	        		
	        		RequestDispatcher dispatcher = null;
	        		
        			if(e!= null){
        				
        				if(tipoUsuario.equalsIgnoreCase("mozo")){
        					request.setAttribute("empleado", e);
    	        			dispatcher=request.getRequestDispatcher("/MenuMozo.jsp");
        				}else if(tipoUsuario.equalsIgnoreCase("produccion")){
        					request.setAttribute("empleado", e);
    	        			dispatcher=request.getRequestDispatcher("/MenuProduccion.jsp");
        				}else if(tipoUsuario.equalsIgnoreCase("cajero")){
        					request.setAttribute("empleado", e);
    	        			dispatcher=request.getRequestDispatcher("/MenuCajero.jsp");
        				}
	        			
	        		}else{
	        			dispatcher=request.getRequestDispatcher("/Mensaje.jsp");
	        			HttpSession session = request.getSession();
	 	                session.setAttribute("mensaje", "Por favor verifique los datos ingresados");
	 	                session.setAttribute("usuario", usuario);
	        		}
 
	        		dispatcher.forward(request, response);
	        		
	        	 }else if(request.getParameter("action").equalsIgnoreCase("opcionDeposito")){
	        		 //DEPOSITO DEL CAJERO
	        		 String nombre = request.getParameter("nombre");
		        		String legajo = request.getParameter("legajo");
		        		
		        		
		        		DTO_Empleado e = new DTO_Empleado();
		        		e.setNombre(nombre);
		        		e.setLegajo(legajo);
		        		
		        		RequestDispatcher dispatcher;
		        		request.setAttribute("empleado", e);
	        			dispatcher=request.getRequestDispatcher("/Deposito.jsp");
		        		dispatcher.forward(request, response);
	        	 
	        	 }else if(request.getParameter("action").equalsIgnoreCase("opcionCaja")){
	        		 //CAJA DEL CAJERO
		        		String nombre = request.getParameter("nombre");
		        		String legajo = request.getParameter("legajo");
		        		
		        		
		        		DTO_Empleado e = new DTO_Empleado();
		        		e.setNombre(nombre);
		        		e.setLegajo(legajo);
		        		
		        		RequestDispatcher dispatcher;
		        		request.setAttribute("empleado", e);
	        			dispatcher=request.getRequestDispatcher("/Caja.jsp");
		        		dispatcher.forward(request, response);
	        	 
	        	 }else if(request.getParameter("action").equalsIgnoreCase("opcionPP")){
		        		String nombre = request.getParameter("nombre");
		        		String legajo = request.getParameter("legajo");
		        		
		        		
		        		DTO_Empleado e = new DTO_Empleado();
		        		e.setNombre(nombre);
		        		e.setLegajo(legajo);
		        		
		        		RequestDispatcher dispatcher;
		        		request.setAttribute("empleado", e);
	        			dispatcher=request.getRequestDispatcher("/PlanProduccion.jsp");
		        		dispatcher.forward(request, response);
	        	 
	        	 }else if(request.getParameter("action").equalsIgnoreCase("opcionReposicion")){
		        		String nombre = request.getParameter("nombre");
		        		String legajo = request.getParameter("legajo");
		        		
		        		
		        		DTO_Empleado e = new DTO_Empleado();
		        		e.setNombre(nombre);
		        		e.setLegajo(legajo);
		        		
		        		RequestDispatcher dispatcher;
		        		request.setAttribute("empleado", e);
	        			dispatcher=request.getRequestDispatcher("/ReposicionInsumos.jsp");
		        		dispatcher.forward(request, response);
	        	 
	        	 }else if(request.getParameter("action").equalsIgnoreCase("opcionDepacho")){
		        		String nombre = request.getParameter("nombre");
		        		String legajo = request.getParameter("legajo");
		        		
		        		
		        		DTO_Empleado e = new DTO_Empleado();
		        		e.setNombre(nombre);
		        		e.setLegajo(legajo);
		        		
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
	        	 
	            		
	        	 }else if(request.getParameter("action").equalsIgnoreCase("opcionListaReposicion")){
		        		String nombre = request.getParameter("nombre");
		        		String legajo = request.getParameter("legajo");
		        		
		        		
		        		DTO_Empleado e = new DTO_Empleado();
		        		e.setNombre(nombre);
		        		e.setLegajo(legajo);
		        		
		        		RequestDispatcher dispatcher;
		        		request.setAttribute("empleado", e);
	        			dispatcher=request.getRequestDispatcher("/ReposicionInsumos.jsp");
		        		dispatcher.forward(request, response);
	        	 
	        	 }else if(request.getParameter("action").equalsIgnoreCase("opcionAbrirMesa")){
		        		String nombre = request.getParameter("nombre");
		        		String legajo = request.getParameter("legajo");
		        		
		        		
		        		DTO_Empleado e = new DTO_Empleado();
		        		e.setNombre(nombre);
		        		e.setLegajo(legajo);
		        		
		        		RequestDispatcher dispatcher;
		        		request.setAttribute("empleado", e);
	        			dispatcher=request.getRequestDispatcher("/AbrirMesa.jsp");
		        		dispatcher.forward(request, response);
	        	 
	        	 }else if(request.getParameter("action").equalsIgnoreCase("opcionCerrarMesa")){
		        		String nombre = request.getParameter("nombre");
		        		String legajo = request.getParameter("legajo");
		        		
		        		
		        		DTO_Empleado e = new DTO_Empleado();
		        		e.setNombre(nombre);
		        		e.setLegajo(legajo);
		        		
		        		RequestDispatcher dispatcher;
		        		request.setAttribute("empleado", e);
	        			dispatcher=request.getRequestDispatcher("/CerrarMesa.jsp");
		        		dispatcher.forward(request, response);
	        	 
	        	 }else if(request.getParameter("action").equalsIgnoreCase("opcionComanda")){
		        		String nombre = request.getParameter("nombre");
		        		String legajo = request.getParameter("legajo");
		        		
		        		
		        		DTO_Empleado e = new DTO_Empleado();
		        		e.setNombre(nombre);
		        		e.setLegajo(legajo);
		        		
		        		RequestDispatcher dispatcher;
		        		request.setAttribute("empleado", e);
	        			dispatcher=request.getRequestDispatcher("/Comandas.jsp");
		        		dispatcher.forward(request, response);
	        	 
	        	 }else if(request.getParameter("action").equalsIgnoreCase("opcionCocina")){
		        		String nombre = request.getParameter("nombre");
		        		String legajo = request.getParameter("legajo");
		        		
		        		
		        		DTO_Empleado e = new DTO_Empleado();
		        		e.setNombre(nombre);
		        		e.setLegajo(legajo);
		        		
		        		boolean validacion = BussinesDelegate.getInstancia().validarEmpleadoCocina(e);
		        		
		        		RequestDispatcher dispatcher;
		        		request.setAttribute("validacion", validacion);
		        		request.setAttribute("empleado", e);
	        			dispatcher=request.getRequestDispatcher("/OpcionCocina.jsp");
		        		dispatcher.forward(request, response);
	        	 
	        	 }else if(request.getParameter("action").equalsIgnoreCase("opcionBarra")){
		        		String nombre = request.getParameter("nombre");
		        		String legajo = request.getParameter("legajo");
		        		
		        		
		        		DTO_Empleado e = new DTO_Empleado();
		        		e.setNombre(nombre);
		        		e.setLegajo(legajo);
		        		
		        		boolean validacion = BussinesDelegate.getInstancia().validarEmpleadoBarra(e);
		        		
		        		RequestDispatcher dispatcher;
		        		request.setAttribute("validacion", validacion);
		        		request.setAttribute("empleado", e);
	        			dispatcher=request.getRequestDispatcher("/OpcionBarra.jsp");
		        		dispatcher.forward(request, response);
	        	 
	        	 }else if(request.getParameter("action").equalsIgnoreCase("opcionCafeteria")){
		        		String nombre = request.getParameter("nombre");
		        		String legajo = request.getParameter("legajo");
		        		
		        		
		        		DTO_Empleado e = new DTO_Empleado();
		        		e.setNombre(nombre);
		        		e.setLegajo(legajo);
		        		
		        		boolean validacion = BussinesDelegate.getInstancia().validarEmpleadoCafeteria(e);
		        		
		        		RequestDispatcher dispatcher;
		        		request.setAttribute("validacion", validacion);
		        		request.setAttribute("empleado", e);
	        			dispatcher=request.getRequestDispatcher("/OpcionCafeteria.jsp");
		        		dispatcher.forward(request, response);
	        	 
	        	 }else if(request.getParameter("action").equalsIgnoreCase("opcionVolver")){
		        		String nombre = request.getParameter("nombre");
		        		String legajo = request.getParameter("legajo");
		        		
		        		
		        		DTO_Empleado e = new DTO_Empleado();
		        		e.setNombre(nombre);
		        		e.setLegajo(legajo);
		        		
		        		RequestDispatcher dispatcher;
		        		request.setAttribute("empleado", e);
	        			dispatcher=request.getRequestDispatcher("/MenuMozo.jsp");
		        		dispatcher.forward(request, response);
	        	 
	        	 }else if(request.getParameter("action").equalsIgnoreCase("opcionVolverC")){
		        		String nombre = request.getParameter("nombre");
		        		String legajo = request.getParameter("legajo");
		        		
		        		
		        		DTO_Empleado e = new DTO_Empleado();
		        		e.setNombre(nombre);
		        		e.setLegajo(legajo);
		        		
		        		RequestDispatcher dispatcher;
		        		request.setAttribute("empleado", e);
	        			dispatcher=request.getRequestDispatcher("/MenuCajero.jsp");
		        		dispatcher.forward(request, response);
	        	 
	        	 }else if(request.getParameter("action").equalsIgnoreCase("opcionVolverP")){
		        		String nombre = request.getParameter("nombre");
		        		String legajo = request.getParameter("legajo");
		        		
		        		
		        		DTO_Empleado e = new DTO_Empleado();
		        		e.setNombre(nombre);
		        		e.setLegajo(legajo);
		        		
		        		RequestDispatcher dispatcher;
		        		request.setAttribute("empleado", e);
	        			dispatcher=request.getRequestDispatcher("/MenuProduccion.jsp");
		        		dispatcher.forward(request, response);
		        }
	        } catch(Exception e){
	            e.printStackTrace();
	        }
	}

}
