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
import dto.DTO_Empleado;
import dto.DTO_Mesa;

public class ProduccionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ProduccionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
	        
			
		 	if(request.getParameter("action").equalsIgnoreCase("opcionComandasCocina")){
        		String nombre = request.getParameter("nombre");
        		String legajo = request.getParameter("legajo");
        		
        		DTO_Empleado e = new DTO_Empleado();
        		e.setNombre(nombre);
        		e.setLegajo(legajo);
        		
        		List<DTO_Comanda> comandas = BussinesDelegate.getInstancia().comandaPendienteCocina(e);
        		
        		
        		RequestDispatcher dispatcher;
        		request.setAttribute("empleado", e);
        		request.setAttribute("comandas", comandas);
        		
    			dispatcher=request.getRequestDispatcher("/CocinaComanda.jsp");
        		dispatcher.forward(request, response);
	        		
            }if(request.getParameter("action").equalsIgnoreCase("opcionComandasBarra")){
        		String nombre = request.getParameter("nombre");
        		String legajo = request.getParameter("legajo");
        		
        		DTO_Empleado e = new DTO_Empleado();
        		e.setNombre(nombre);
        		e.setLegajo(legajo);
        		
        		List<DTO_Comanda> comandas = BussinesDelegate.getInstancia().comandaPendienteBarra(e);
        		
        		
        		RequestDispatcher dispatcher;
        		request.setAttribute("empleado", e);
        		request.setAttribute("comandas", comandas);
        		
    			dispatcher=request.getRequestDispatcher("/BarraComanda.jsp");
        		dispatcher.forward(request, response);
        		
	        }if(request.getParameter("action").equalsIgnoreCase("opcionComandasCafeteria")){
	    		String nombre = request.getParameter("nombre");
	    		String legajo = request.getParameter("legajo");
	    		
	    		DTO_Empleado e = new DTO_Empleado();
	    		e.setNombre(nombre);
	    		e.setLegajo(legajo);
	    		
	    		List<DTO_Comanda> comandas = BussinesDelegate.getInstancia().comandaPendienteCafeteria(e);
	    		
	    		
	    		RequestDispatcher dispatcher;
	    		request.setAttribute("empleado", e);
	    		request.setAttribute("comandas", comandas);
	    		
				dispatcher=request.getRequestDispatcher("/CafeteriaComanda.jsp");
	    		dispatcher.forward(request, response);
	    		
	        }if(request.getParameter("action").equalsIgnoreCase("prepararItemComanda")){
	    		
	        	String nombre = request.getParameter("nombre");
	    		String legajo = request.getParameter("legajo");
	    		String comanda = request.getParameter("comanda");
	    		String itemComanda = request.getParameter("itemComanda");
	    		String url = request.getParameter("url");
	    		
	    		int idComanda = Integer.parseInt(comanda);
	    		int idItem = Integer.parseInt(itemComanda);
	    		boolean resp = false;
	    		
	    		DTO_Empleado e = new DTO_Empleado();
	    		e.setNombre(nombre);
	    		e.setLegajo(legajo);
	    		
	    		resp = BussinesDelegate.getInstancia().prepararItemComanda(idComanda,idItem,e);
	    		
	    		if(resp){
		    		if(url.equalsIgnoreCase("/CocinaComanda.jsp")){
		    			List<DTO_Comanda> comandas = BussinesDelegate.getInstancia().comandaPendienteCocina(e);
		    			
		    			RequestDispatcher dispatcher;
			    		request.setAttribute("empleado", e);
			    		request.setAttribute("comandas", comandas);
			    		
						dispatcher=request.getRequestDispatcher(url);
			    		dispatcher.forward(request, response);
			    		
		    		}else if(url.equalsIgnoreCase("/BarraComanda.jsp")){
		    			List<DTO_Comanda> comandas = BussinesDelegate.getInstancia().comandaPendienteBarra(e);
		    			
		    			RequestDispatcher dispatcher;
			    		request.setAttribute("empleado", e);
			    		request.setAttribute("comandas", comandas);
			    		
						dispatcher=request.getRequestDispatcher(url);
			    		dispatcher.forward(request, response);
			    		
		    		}else if(url.equalsIgnoreCase("/CafeteriaComanda.jsp")){
		    			List<DTO_Comanda> comandas = BussinesDelegate.getInstancia().comandaPendienteCafeteria(e);
		    			
		    			RequestDispatcher dispatcher;
			    		request.setAttribute("empleado", e);
			    		request.setAttribute("comandas", comandas);
			    		
						dispatcher=request.getRequestDispatcher(url);
			    		dispatcher.forward(request, response);
		    		}
	    		}else{
	    			RequestDispatcher dispatcher;
		    		request.setAttribute("empleado", e);
		    		request.setAttribute("comanda", " de la comanda Nº " + idComanda);
		    		request.setAttribute("mensaje", "no pudo ser realizado por falta de STOCK");
		    		request.setAttribute("item", "El Item Nº " + idItem);
		    		
					dispatcher=request.getRequestDispatcher("/MensajeComanda.jsp");
		    		dispatcher.forward(request, response);
	    		}
	    		
	    		
	        }
	        	
	        } catch(Exception e){
	            e.printStackTrace();
	        }
	}

}
