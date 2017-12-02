<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import= "java.util.Iterator"%>
<%@ page import= "java.util.List"%>
<%@ page import= "dto.DTO_Empleado"%>
<%@ page import= "dto.DTO_Mesa"%>
<%@ page import= "dto.DTO_Comanda"%>
<%@ page import= "dto.DTO_ItemComanda"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body style="height: 369px; background-color: #2E9AFE">

<%
	DTO_Empleado emp = (DTO_Empleado)request.getAttribute("empleado");
	DTO_Comanda c = (DTO_Comanda)request.getAttribute("comanda");
%>

<form action="EmpleadoServlet?action=opcionVolver" method ="post" style="width: 225px; height: 42px">
	   	<input type="submit" value="Menú" name="opcionVolver" style="font-family:; font-style: normal; font-weight: bold; width: 146px; height: 30px; background-color: #BDBDBD">
		<input type ="hidden" name = "nombre" readonly ="readonly" value ="<%=emp.getNombre()%>">
	    <input type ="hidden" name = "legajo" readonly ="readonly" value ="<%=emp.getLegajo()%>">
</form>

<input type="text" name="legajo" readonly value="<%=emp.getLegajo()%>" style="width: 109px; ">
<input align="right" type="text" name="nombre" readonly value="<%=emp.getNombre()%>" style="width: 111px; ">
	<table cellspacing="3" cellpadding="3" border="2" width="500" style="width: 1215px; background-color: #A9D0F5; border-bottom-style: solid; border-top-style: solid; border-left-style: solid; border-right-style: solid">
	<tr>
		<td colspan="4"><b>Detalle Comanda</b></td>
	</tr>
	<tr>
		<td style="width: 99px; "><b>Id</b></td>
		<td style="width: 164px; "><b>Plato</b></td>
		<td style="width: 157px; "><b>Cantidad</b></td>
		<td style="width: 163px; "><b>Estado</b></td>
	</tr>
<%
	DTO_ItemComanda aux;
	String id; 	
	List<DTO_ItemComanda> items = c.getItems();
	
	if(items != null){
		for(Iterator<DTO_ItemComanda> i = items.iterator(); i.hasNext();)
		{
	aux = i.next();
%>
	<tr>
		<td><%=aux.getId()%></td>
		<td><%=aux.getPlato().getNombre()%></td>
		<td><%=aux.getCantidad()%></td>
		<td><%=aux.getEstado()%></td>
		<td>
		
			<form action="MozoServlet?action=eliminarItemComanda" method ="post" style="width: 344px; ">
	        	<input type="hidden" name="action" value="eliminarItemComanda">
	        	<input type ="hidden" name = "comanda" readonly ="readonly" value ="<%=c.getNumero()%>">
	        	<input type ="hidden" name = "itemComanda" readonly ="readonly" value ="<%=aux.getId()%>">
	        	<input type ="hidden" name = "legajo" readonly ="readonly" value ="<%=emp.getLegajo()%>">
	        	<input type ="hidden" name = "nombre" readonly ="readonly" value ="<%=emp.getNombre()%>">
	        	<input type="submit" value="Eliminar" name="eliminarItemComanda" style="font-family:; font-style: normal; font-weight: bold; width: 203px; height: 30px; background-color: #BDBDBD">
        	</form>
		
		</td>
	</tr>
	<% 		
	}
}%>
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