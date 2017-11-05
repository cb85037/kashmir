<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import= "dto.DTO_Empleado"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cierre de caja</title>
</head>
<body style="background-color: #F7F8E0">


<%
	DTO_Empleado emp = (DTO_Empleado)request.getAttribute("empleado");
	boolean respuesta = (Boolean)request.getAttribute("respuesta");

%>


<form action="CajeroServlet?action=opcionVolver" method ="post">
      <input type ="hidden" name = "nombre" readonly ="readonly" value ="<%=emp.getNombre()%>">
	  <input type ="hidden" name = "legajo" readonly ="readonly" value ="<%=emp.getLegajo()%>">
	  <input type="submit" value="Menú" name="opcionVolver" style="font-family:; font-style: normal; font-weight: bold; width: 203px; height: 30px; background-color: #F7F8E0">
</form>
<input type = "text" name = "nombre" readonly ="readonly" value ="<%=emp.getNombre()%>"><input type="text" name="legajo" readonly="readonly" value="<%=emp.getLegajo()%>"></td>
</td>


<h1 align = "center"><b>Cierre de caja</b></h1>
<div style="height: 45px; "></div>	
<form action="./CajeroServlet" method="post" style="height: 355px; ">

<table align="center" border="2" width="30%" style="cursor: auto; background-color: #F5ECCE; border-bottom-style: solid; border-top-style: solid; border-left-style: solid; border-right-style: solid; font-family:; width: 495px; height: 194px">
   <% if(respuesta == false) {%>
   <tr style="height: 37px; ">
        <td colspan="3" align="left" style="background-color: #F5ECCE; height: 18px">
        	<b><label for="usuario">Monto cierre<b></b></label></b></td><b>
        </b><td style="background-color: #F5ECCE"><b><input type="text" name="montoCierre" style="border-bottom-style: solid; border-top-style: solid; border-left-style: solid; border-right-style: solid; border-bottom-width: thin; border-right-width: thin; border-left-width: thin; border-top-width: thin"></b></td><b>
   		</b></tr><b> 
    	</b><tr><b>
        </b><td colspan="8" align="center" style="background-color: #F5ECCE"><b>
        	
	        </b><b><input type="hidden" name="action" value="cerrarCajaDiaria"></b><b>
	        <input type ="hidden" name = "nombre" readonly ="readonly" value ="<%=emp.getNombre()%>">
	        <input type ="hidden" name = "legajo" readonly ="readonly" value ="<%=emp.getLegajo()%>">
	        </b><b><input type="submit" value="Cerrar Caja Diaria" name="validar" style="font-family:; font-style: normal; font-weight: bold; width: 133px; height: 30px; background-color: #F5ECCE"></b><b>
        </b></td><b>

    </b></tr><b> 
    <% }
    else if (respuesta == true) {%>
    	</b></tr><b> 
    	</b><tr><b>
    	  <td colspan="2" align="center" style="color: Black; border-left-color: #000000; border-top-color: #000000; border-right-color: #000000; border-bottom-color: #000000">
	        <form action="EmpleadoServlet?action=opcionVolver" method ="post">
	        	<input type="hidden" name="action" value="opcionVolver">
	        	<input type ="hidden" name = "nombre" readonly ="readonly" value ="<%=emp.getNombre()%>">
	        	<input type ="hidden" name = "legajo" readonly ="readonly" value ="<%=emp.getLegajo()%>">
	        	<label>La Caja ha sido cerrada</label>
        	</form>
        </td>
    <%}%>
</b></table><b>
</b></form><b>
</b></body><b>
</b></html>