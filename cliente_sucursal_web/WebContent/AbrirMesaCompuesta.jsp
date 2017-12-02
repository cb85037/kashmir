<%@page import="dto.DTO_MesaSimple"%>
<%@page import="dto.DTO_MesaCompuesta"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import= "java.util.Iterator"%>
<%@ page import= "java.util.List"%>
<%@ page import= "dto.DTO_Empleado"%>
<%@ page import= "dto.DTO_Mesa"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body style="height: 369px; background-color: #2E9AFE">

<%
	DTO_Empleado emp = (DTO_Empleado)request.getAttribute("empleado");
	DTO_MesaCompuesta mesas = (DTO_MesaCompuesta)request.getAttribute("mesas");
	boolean respuesta = (Boolean)request.getAttribute("respuesta");
	String cantidad =(String) request.getAttribute("cantidad");
%>

<form action="EmpleadoServlet?action=opcionVolver" method ="post" style="width: 225px; height: 42px">
	   	<input type="submit" value="Menú" name="opcionVolver" style="font-family:; font-style: normal; font-weight: bold; width: 146px; height: 30px; background-color: #BDBDBD">
		<input type ="hidden" name = "nombre" readonly ="readonly" value ="<%=emp.getNombre()%>">
	    <input type ="hidden" name = "legajo" readonly ="readonly" value ="<%=emp.getLegajo()%>">
</form>
<input type="text" name="legajo" readonly value="<%=emp.getLegajo()%>" style="width: 109px; ">
<input align="right" type="text" name="nombre" readonly value="<%=emp.getNombre()%>" style="width: 111px; ">

<table align="center" border="2" width="51%" style="color: #000000; background-color: #A9D0F5; font-family:; border-left-color: #000000; border-top-color: #000000; border-right-color: #000000; border-bottom-color: #000000; height: 275px; border-top-style: solid; border-right-style: solid">
    <tr>
        <td colspan="2" align="center" style="border-left-color: #000000; border-top-color: #000000; border-right-color: #000000; border-bottom-color: #000000; font-size: 24px"><b>Abrir Mesa</b></td>
   </tr>
   <tr>
   <%
   	if(mesas.getComponentes().size() > 0 && respuesta == false){
   %>
        <td colspan="2" align="center" style="color: Black; border-left-color: #000000; border-top-color: #000000; border-right-color: #000000; border-bottom-color: #000000">
        	<form action="MozoServlet?action=abrirMesaCompuesta" method ="post">
	        	<input type="hidden" name="action" value="abrirMesaCompuesta">
	        	<input type ="hidden" name = "nombre" readonly ="readonly" value ="<%=emp.getNombre()%>">
	        	<input type ="hidden" name = "legajo" readonly ="readonly" value ="<%=emp.getLegajo()%>">
	        	<input type ="hidden" name = "cantidad" readonly ="readonly" value ="<%=cantidad%>">
	        	
	        	<label for="numeroMesa">Numero Mesa Compuesta<b></b></label>
        		<select name = "numeroMesa">
        		<option value="<%=mesas.getIdMesa()%>">Mesa <%=mesas.getNumero()%></option>
        		
        		
				</select>
				<input type ="hidden" name = "cantidad" readonly ="readonly" value ="<%=cantidad%>">
	        	<input type="submit" value="Abrir" name="abrirMesaCompuesta" style="font-family:; font-style: normal; font-weight: bold; width: 203px; height: 30px; background-color: #BDBDBD">
        	</form>
        </td>
    
   		 
        		
        <%
   	}else if(respuesta){ %>
         <td colspan="2" align="center" style="color: Black; border-left-color: #000000; border-top-color: #000000; border-right-color: #000000; border-bottom-color: #000000">
	        <b>La mesa ha sido abierta, puede unirlas!</b>
	        <form action="EmpleadoServlet?action=opcionVolver" method ="post">
	        
	        	<%for(DTO_MesaSimple m : mesas.getComponentes()){%>
				<tr>
					<td><%=m.getNumero()%></td>
				</tr>
				<%}%>
	        	
	        	<input type="hidden" name="action" value="opcionVolver">
	        	<input type ="hidden" name = "nombre" readonly ="readonly" value ="<%=emp.getNombre()%>">
	        	<input type ="hidden" name = "legajo" readonly ="readonly" value ="<%=emp.getLegajo()%>">
	        	
	        	<input type="submit" value="Menú" name="opcionVolver" style="font-family:; font-style: normal; font-weight: bold; width: 203px; height: 30px; background-color: #BDBDBD">
        	
        	
        	</form>
        	
        	
        	
        </td>
        
     	
     	
     	
     	
   	    
     	
     	

   <%} %>
   </tr>   
</table>

<div style="height: 45px; "></div>
<div style="height: 45px; "></div>
<div style="height: 45px; "></div>
<div style="height: 45px; "></div>
<div style="height: 45px; "></div>
<div style="height: 45px; "></div>
<div style="height: 45px; "></div>
<div style="height: 45px; "></div>





</body>
</html>