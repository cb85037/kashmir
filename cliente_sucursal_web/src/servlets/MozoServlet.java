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
import dto.DTO_Comanda;
import dto.DTO_DepositoLocal;
import dto.DTO_Empleado;
import dto.DTO_Mesa;
import dto.DTO_MesaCompuesta;
import dto.DTO_MesaSimple;
import dto.DTO_Movimiento;
import dto.DTO_Plato;
import dto.DTO_RubroCarta;


public class MozoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public MozoServlet() {
        super();
        
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 try {
	        		
			 	if(request.getParameter("action").equalsIgnoreCase("opcionAbrirMesaSimple")){
			 		
			 			//SEGUNDA VEZ
		        		String nombre = request.getParameter("nombre");
		        		String legajo = request.getParameter("legajo");
		        		String cant = request.getParameter("cantidad");
		        		int cantComensales = Integer.parseInt(cant);
		        		
		        		
		        		DTO_Empleado e = new DTO_Empleado();
		        		e.setNombre(nombre);
		        		e.setLegajo(legajo);
		        		
		        		List<DTO_Mesa> mesas = BussinesDelegate.getInstancia().mesasSimplesDisponibles(legajo,cantComensales);
		        		
		        		RequestDispatcher dispatcher;
		        		request.setAttribute("empleado", e);
		        		request.setAttribute("mesas", mesas);
		        		request.setAttribute("respuesta", false);
		        		request.setAttribute("cantidad", cant);
		        		//ACA SE LE VAN A LISTAR LAS MESAS AL USUARIO 
	        			dispatcher=request.getRequestDispatcher("/AbrirMesaSimple.jsp");
		        		dispatcher.forward(request, response);
		        		
	            }
			 	if(request.getParameter("action").equalsIgnoreCase("opcionMesaCompuesta")){
	        		String nombre = request.getParameter("nombre");
	        		String legajo = request.getParameter("legajo");
	        		String cant = request.getParameter("cantidad");
	        		int cantComensales = Integer.parseInt(cant);
	        		
	        		
	        		DTO_Empleado e = new DTO_Empleado();
	        		e.setNombre(nombre);
	        		e.setLegajo(legajo);
	        		
	        		DTO_MesaCompuesta mesas = BussinesDelegate.getInstancia().mesasCompuestasDisponibles(legajo, cantComensales);
	        		
	        		RequestDispatcher dispatcher;
	        		request.setAttribute("empleado", e);
	        		request.setAttribute("mesas", mesas);
	        		request.setAttribute("respuesta", false);
	        		request.setAttribute("cantidad", cant);
	        		
	        		
        			dispatcher=request.getRequestDispatcher("/AbrirMesaCompuesta.jsp");
	        		dispatcher.forward(request, response);
	        		
			 	}
			 	if(request.getParameter("action").equalsIgnoreCase("opcionCerrarMesa")){
	            	
	        		String nombre = request.getParameter("nombre");
	        		String legajo = request.getParameter("legajo");
	        		
	        		DTO_Empleado e = new DTO_Empleado();
	        		e.setNombre(nombre);
	        		e.setLegajo(legajo);
	        		
	        		List<DTO_Mesa> mesas = BussinesDelegate.getInstancia().mesasAbiertasMozo(e);
	        		
	        		RequestDispatcher dispatcher;
	        		request.setAttribute("empleado", e);
	        		request.setAttribute("mesas", mesas);
	        		
        			dispatcher=request.getRequestDispatcher("/CerrarMesaSimple.jsp");
	        		dispatcher.forward(request, response);
	        		
			 	}
				 	else if(request.getParameter("action").equalsIgnoreCase("cerrarMesaSimple")){
	            	
	        		String nombre = request.getParameter("nombre");
	        		String legajo = request.getParameter("legajo");
	        		String[] numeroMesa = request.getParameterValues("numeroMesa");
	        		String aux = numeroMesa[0];
	        		int idMesa = Integer.parseInt(aux);
	        		
	        		DTO_Empleado e = new DTO_Empleado();
	        		e.setNombre(nombre);
	        		e.setLegajo(legajo);
	        		
	        		List<DTO_Mesa> mesas = new ArrayList<DTO_Mesa>();
	        		boolean respuesta = false;
	        		
	        		respuesta = BussinesDelegate.getInstancia().cerrarMesaSimple(idMesa, legajo);
	        		
	        		RequestDispatcher dispatcher;
	        		request.setAttribute("empleado", e);
	        		request.setAttribute("resp", respuesta);
	        		
	    			dispatcher=request.getRequestDispatcher("/MensajeCerrarMesa.jsp");
	        		dispatcher.forward(request, response);
        		
				 }
				 	else if(request.getParameter("action").equalsIgnoreCase("opcionAbrirMesa")){
	            	//PRIMERO PASA POR ACA 
	        		String nombre = request.getParameter("nombre");
	        		String legajo = request.getParameter("legajo");
	        		
	        		DTO_Empleado e = new DTO_Empleado();
	        		e.setNombre(nombre);
	        		e.setLegajo(legajo);
	        		
	        		RequestDispatcher dispatcher;
	        		request.setAttribute("empleado", e);
	        		//EN ESTA PANTALLA EL USUARIO VA A PODER PONER LA CANTIDAD DE PERSONAS PARA COMER
        			dispatcher=request.getRequestDispatcher("/AbrirMesa.jsp");
	        		dispatcher.forward(request, response);
	        		
			}else if(request.getParameter("action").equalsIgnoreCase("abrirMesaSimple")){
            	//ACA VENGO AL TERCERA VEZ Y PONGO TRUE EN LA RESPUESTA, ASI PUEDO MOSTRAR EL MENSAJE
            	//MESA ABIERTA CORRECTAMENTE
        		String nombre = request.getParameter("nombre");
        		String legajo = request.getParameter("legajo");
        		String[] numeroMesa = request.getParameterValues("numeroMesa");
        		String aux = numeroMesa[0];
        		int idMesa = Integer.parseInt(aux);
        		
        		DTO_Empleado e = new DTO_Empleado();
        		e.setNombre(nombre);
        		e.setLegajo(legajo);
        		
        		List<DTO_Mesa> mesas = new ArrayList<DTO_Mesa>();
        		boolean respuesta = BussinesDelegate.getInstancia().abrirMesaSimple(idMesa, legajo);
        		
        		RequestDispatcher dispatcher;
        		request.setAttribute("empleado", e);
        		request.setAttribute("mesas", mesas);
        		request.setAttribute("respuesta", respuesta);
        		//VUELVE A LLAMAR A LA MISMA PANTALLA QUE VALIDA QUE LA RESPUESTA SEA TRUE, PARA QUE HAYA ABIERTO CON EXITO
    			dispatcher=request.getRequestDispatcher("/AbrirMesaSimple.jsp");
        		dispatcher.forward(request, response);
        		
        }else if(request.getParameter("action").equalsIgnoreCase("abrirMesaCompuesta")){
        	
    		String nombre = request.getParameter("nombre");
    		String legajo = request.getParameter("legajo");
    		//String[] numeroMesa = request.getParameterValues("numeroMesa");
    		String cant = request.getParameter("cantidad");
    		//String aux = numeroMesa[0];
    		int cantComensales = Integer.parseInt(cant);
    		
    		DTO_Empleado e = new DTO_Empleado();
    		e.setNombre(nombre);
    		e.setLegajo(legajo);
    		
    		DTO_MesaCompuesta mesas = BussinesDelegate.getInstancia().mesasCompuestasDisponibles(legajo, cantComensales);
    		
    		boolean respuesta = BussinesDelegate.getInstancia().abrirMesaCompuesta(mesas, legajo);
    		
    		RequestDispatcher dispatcher;
    		request.setAttribute("empleado", e);
    		request.setAttribute("mesas", mesas);
    		request.setAttribute("respuesta", respuesta);
    		
			dispatcher=request.getRequestDispatcher("/AbrirMesaCompuesta.jsp");
    		dispatcher.forward(request, response);
    		
        	}
			else if(request.getParameter("action").equalsIgnoreCase("opcionComanda")){
        	
    		String nombre = request.getParameter("nombre");
    		String legajo = request.getParameter("legajo");
    		
    		List<DTO_Mesa> mesas = BussinesDelegate.getInstancia().mesasComandasNoFacturadas(legajo);
    		
    		DTO_Empleado e = new DTO_Empleado();
    		e.setNombre(nombre);
    		e.setLegajo(legajo);
    		
    		RequestDispatcher dispatcher;
    		request.setAttribute("mesas", mesas);
    		request.setAttribute("empleado", e);
    		
			dispatcher=request.getRequestDispatcher("/OpcionComanda.jsp");
    		dispatcher.forward(request, response);
    		
        }
		else if(request.getParameter("action").equalsIgnoreCase("abrirComanda")){
		        	
		    		String nombre = request.getParameter("nombre");
		    		String legajo = request.getParameter("legajo");
		    		String[] numeroMesa = request.getParameterValues("numeroComanda");
	        		String aux = numeroMesa[0];
	        		int idComanda = Integer.parseInt(aux);
		    		
		    		DTO_Comanda comanda = BussinesDelegate.getInstancia().getComanda(idComanda);
		    		
		    		DTO_Empleado e = new DTO_Empleado();
		    		e.setNombre(nombre);
		    		e.setLegajo(legajo);
		    		
		    		List<DTO_RubroCarta> rubros = BussinesDelegate.getInstancia().getPlatos(e);
		    		
		    		List<DTO_Plato> bebida = new ArrayList<DTO_Plato>();
		    		List<DTO_Plato> carne = new ArrayList<DTO_Plato>();
		    		List<DTO_Plato> pescado = new ArrayList<DTO_Plato>();
		    		List<DTO_Plato> pasta = new ArrayList<DTO_Plato>();
		    		List<DTO_Plato> guarnicion = new ArrayList<DTO_Plato>();
		    		
		    		for(DTO_RubroCarta r: rubros){
		    			if(r.getNombre().equalsIgnoreCase("Bebidas")){
		    				bebida = r.getPlatos();
		    			}
		    			if(r.getNombre().equalsIgnoreCase("Carnes")){
		    				carne = r.getPlatos();
		    			}
		    			if(r.getNombre().equalsIgnoreCase("Pescados")){
		    				pescado = r.getPlatos();
		    			}
		    			if(r.getNombre().equalsIgnoreCase("Pastas")){
		    				pasta = r.getPlatos();
		    			}
		    			if(r.getNombre().equalsIgnoreCase("Guarniciones")){
		    				guarnicion = r.getPlatos();
		    			}
		    		}
		    		
		    		RequestDispatcher dispatcher;
		    		request.setAttribute("bebida", bebida);
		    		request.setAttribute("carne", carne);
		    		request.setAttribute("pescado", pescado);
		    		request.setAttribute("pasta", pasta);
		    		request.setAttribute("guarnicion", guarnicion);
		    		request.setAttribute("comanda", comanda);
		    		request.setAttribute("empleado", e);
		    		
					dispatcher=request.getRequestDispatcher("/AbrirComanda.jsp");
					
		    		dispatcher.forward(request, response);
		    		
		}else if(request.getParameter("action").equalsIgnoreCase("modificarComanda")){
        	
    		String nombre = request.getParameter("nombre");
    		String legajo = request.getParameter("legajo");
    		String[] numeroMesa = request.getParameterValues("numeroComanda");
    		String aux = numeroMesa[0];
    		int idComanda = Integer.parseInt(aux);
    		
    		DTO_Comanda comanda = BussinesDelegate.getInstancia().getComanda(idComanda);
    		
    		DTO_Empleado e = new DTO_Empleado();
    		e.setNombre(nombre);
    		e.setLegajo(legajo);
    		
    		RequestDispatcher dispatcher;
    		request.setAttribute("comanda", comanda);
    		request.setAttribute("empleado", e);
    		
			dispatcher=request.getRequestDispatcher("/ModificarComanda.jsp");
    		dispatcher.forward(request, response);
    		
		}else if(request.getParameter("action").equalsIgnoreCase("eliminarItemComanda")){
        	
    		String nombre = request.getParameter("nombre");
    		String legajo = request.getParameter("legajo");
    		String com = request.getParameter("comanda");
    		String item = request.getParameter("itemComanda");
    		
    		int idComanda = Integer.parseInt(com);
    		int idItem = Integer.parseInt(item);
    		
    		BussinesDelegate.getInstancia().eliminarItemComanda(idItem);
    		
    		DTO_Comanda comanda = BussinesDelegate.getInstancia().getComanda(idComanda);
    		
    		DTO_Empleado e = new DTO_Empleado();
    		e.setNombre(nombre);
    		e.setLegajo(legajo);
    		
    		RequestDispatcher dispatcher;
    		request.setAttribute("comanda", comanda);
    		request.setAttribute("empleado", e);
    		
			dispatcher=request.getRequestDispatcher("/ModificarComanda.jsp");
    		dispatcher.forward(request, response);
    		
		}else if(request.getParameter("action").equalsIgnoreCase("agregarItemComanda")){
        	
    		String nombre = request.getParameter("nombre");
    		String legajo = request.getParameter("legajo");
    		String com = request.getParameter("comanda");
    		String plato = request.getParameter("plato");
    		String cantidad = request.getParameter("cantidad");
    		
    		int canti = Integer.parseInt(cantidad); 
    		int idComanda = Integer.parseInt(com);
    		int idPlato = Integer.parseInt(plato);
    		boolean resp = false;
    		
    		DTO_Empleado e = new DTO_Empleado();
    		e.setNombre(nombre);
    		e.setLegajo(legajo);
    		
    		//Crear un nuevo itemComanda con el plato, la cantidad, y la comanda a la que va
    		
    		resp = BussinesDelegate.getInstancia().agregarItemComanda(idComanda,canti,idPlato,legajo);
    		
    		
    		//Recuperar la comanda para mostrarla por pantalla
    		DTO_Comanda comanda = BussinesDelegate.getInstancia().getComanda(idComanda);
    		
    		RequestDispatcher dispatcher;
    		request.setAttribute("comanda", comanda);
    		request.setAttribute("empleado", e);
    		request.setAttribute("resp", resp);
    		
			dispatcher=request.getRequestDispatcher("/MostrarComanda.jsp");
    		dispatcher.forward(request, response);
    		
		}else if(request.getParameter("action").equalsIgnoreCase("volverAbrirComanda")){
        	
    		String nombre = request.getParameter("nombre");
    		String legajo = request.getParameter("legajo");
    		String com = request.getParameter("comanda");
    		
    		int idComanda = Integer.parseInt(com);
    		
    		DTO_Comanda comanda = BussinesDelegate.getInstancia().getComanda(idComanda);
    		
    		DTO_Empleado e = new DTO_Empleado();
    		e.setNombre(nombre);
    		e.setLegajo(legajo);
    		
    		List<DTO_RubroCarta> rubros = BussinesDelegate.getInstancia().getPlatos(e);
    		
    		List<DTO_Plato> bebida = new ArrayList<DTO_Plato>();
    		List<DTO_Plato> carne = new ArrayList<DTO_Plato>();
    		List<DTO_Plato> pescado = new ArrayList<DTO_Plato>();
    		List<DTO_Plato> pasta = new ArrayList<DTO_Plato>();
    		List<DTO_Plato> guarnicion = new ArrayList<DTO_Plato>();
    		
    		for(DTO_RubroCarta r: rubros){
    			if(r.getNombre().equalsIgnoreCase("Bebidas")){
    				bebida = r.getPlatos();
    			}
    			if(r.getNombre().equalsIgnoreCase("Carnes")){
    				carne = r.getPlatos();
    			}
    			if(r.getNombre().equalsIgnoreCase("Pescados")){
    				pescado = r.getPlatos();
    			}
    			if(r.getNombre().equalsIgnoreCase("Pastas")){
    				pasta = r.getPlatos();
    			}
    			if(r.getNombre().equalsIgnoreCase("Guarniciones")){
    				guarnicion = r.getPlatos();
    			}
    		}
    		
    		RequestDispatcher dispatcher;
    		request.setAttribute("bebida", bebida);
    		request.setAttribute("carne", carne);
    		request.setAttribute("pescado", pescado);
    		request.setAttribute("pasta", pasta);
    		request.setAttribute("guarnicion", guarnicion);
    		request.setAttribute("comanda", comanda);
    		request.setAttribute("empleado", e);
    		
			dispatcher=request.getRequestDispatcher("/AbrirComanda.jsp");
			
    		dispatcher.forward(request, response);
    		
		}
			 	
        } catch(Exception e){
            e.printStackTrace();
        }
	}

}
