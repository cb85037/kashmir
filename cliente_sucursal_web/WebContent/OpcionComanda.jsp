<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import= "java.util.Iterator"%>
<%@ page import= "java.util.List"%>
<%@ page import= "dto.DTO_Empleado"%>
<%@ page import= "dto.DTO_Mesa"%>
<%@ page import= "dto.DTO_Comanda"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body style="height: 369px; background-color: #2E9AFE">

<%
	DTO_Empleado emp = (DTO_Empleado)request.getAttribute("empleado");
	List<DTO_Mesa> mesas = (List<DTO_Mesa>)request.getAttribute("mesas");
	//List<ComandaDTO> comandas = (List<ComandaDTO>)request.getAttribute("comandas");
%>

<form action="EmpleadoServlet?action=opcionVolver" method ="post" style="width: 225px; height: 42px">
	   	<input type="submit" value="Menú" name="opcionVolver" style="font-family:; font-style: normal; font-weight: bold; width: 146px; height: 30px; background-color: #BDBDBD">
		<input type ="hidden" name = "nombre" readonly ="readonly" value ="<%=emp.getNombre()%>">
	    <input type ="hidden" name = "legajo" readonly ="readonly" value ="<%=emp.getLegajo()%>">
</form>
<input type="text" name="legajo" readonly value="<%=emp.getLegajo()%>" style="width: 109px; ">
<input align="right" type="text" name="nombre" readonly value="<%=emp.getNombre()%>" style="width: 111px; ">

<table align="center" border="2" width="40%" style="color: #000000; background-color: #A9D0F5; font-family:; border-left-color: #000000; border-top-color: #000000; border-right-color: #000000; border-bottom-color: #000000; height: 275px; border-top-style: solid; border-right-style: solid">
    <tr>
        <td colspan="2" align="center" style="border-left-color: #000000; border-top-color: #000000; border-right-color: #000000; border-bottom-color: #000000; font-size: 24px"><b>Gestor Comanda</b></td>
   </tr>
   <tr>
   <%
   	if(mesas.size() > 0){
   %>
        <td colspan="2" align="center" style="color: Black; border-left-color: #000000; border-top-color: #000000; border-right-color: #000000; border-bottom-color: #000000">
        <form action="MozoServlet?action=abrirComanda" method ="post">
	        	<input type="hidden" name="action" value="abrirComanda">
	        	<input type ="hidden" name = "nombre" readonly ="readonly" value ="<%=emp.getNombre()%>">
	        	<input type ="hidden" name = "legajo" readonly ="readonly" value ="<%=emp.getLegajo()%>">
	        	<label for="numeroComanda">Comandas<b></b></label>
        		<select name = "numeroComanda">
        		<%
        			for(DTO_Mesa m: mesas){
        		        		        				DTO_Comanda c = m.getComanda();
        		%>
			  		<option value="<%=c.getNumero()%>">Mesa <%=m.getNumero()%> - Comanda <%=c.getNumero()%></option>
			
			  	<%
						  		}
						  	%>
				</select>
	        	<input type="submit" value="Agregar Item" name="abrirComanda" style="font-family:; font-style: normal; font-weight: bold; width: 203px; height: 30px; background-color: #BDBDBD">
        	</form>
        </td>
        <td colspan="2" align="center" style="color: Black; border-left-color: #000000; border-top-color: #000000; border-right-color: #000000; border-bottom-color: #000000">
        <form action="MozoServlet?action=modificarComanda" method ="post">
	        	<input type="hidden" name="action" value="modificarComanda">
	        	<input type ="hidden" name = "nombre" readonly ="readonly" value ="<%=emp.getNombre()%>">
	        	<input type ="hidden" name = "legajo" readonly ="readonly" value ="<%=emp.getLegajo()%>">
	        	<label for="numeroComanda">Comandas<b></b></label>
        		<select name = "numeroComanda">
        		<%
        			for(DTO_Mesa m: mesas){
        		        		        				DTO_Comanda c = m.getComanda();
        		%>
			  		<option value="<%=c.getNumero()%>">Mesa <%=m.getNumero()%> - Comanda <%=c.getNumero()%></option>
			
			  	<%
        			}
			  	%>
				</select>
	        	<input type="submit" value="Editar Comanda" name="modificarComanda" style="font-family:; font-style: normal; font-weight: bold; width: 203px; height: 30px; background-color: #BDBDBD">
        	</form>
        </td>
        <%
   	}else if(mesas.size() <= 0){
        %>
         <td colspan="2" align="center" style="color: Black; border-left-color: #000000; border-top-color: #000000; border-right-color: #000000; border-bottom-color: #000000">
	        	<label>No existe la comanda, compruebe si abrio bien la mesa</label></td>
        <%
   	}
        %>
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