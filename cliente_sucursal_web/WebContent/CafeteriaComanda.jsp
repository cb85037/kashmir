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
<body style="height: 369px; background-color: #F7F8E0">

<%
	DTO_Empleado emp = (DTO_Empleado)request.getAttribute("empleado");
	List<DTO_Comanda> c = (List<DTO_Comanda>)request.getAttribute("comandas");
%>
<form action="EmpleadoServlet?action=opcionVolverP" method ="post">
      <input type ="hidden" name = "nombre" readonly ="readonly" value ="<%=emp.getNombre()%>">
	  <input type ="hidden" name = "legajo" readonly ="readonly" value ="<%=emp.getLegajo()%>">
	  <input type="submit" value="Volver al Menú" name="opcionVolver" style="font-family:; font-style: normal; font-weight: bold; width: 203px; height: 30px; background-color: #74B335">
</form>
<input type = "text" name = "nombre" readonly ="readonly" value ="<%=emp.getNombre()%>"><input type="text" name="legajo" readonly="readonly" value="<%=emp.getLegajo()%>">


<h1 align = "center">Comandas de la Cafeteria</h1>

	<table cellspacing="3" cellpadding="3" border="2" width="500" style="width: 1215px; background-color: #F5ECCE; border-bottom-style: solid; border-top-style: solid; border-left-style: solid; border-right-style: solid">
	<tr>
		<td colspan="4"><b>Comandas</b></td>
	</tr>
	<tr>
		<td style="width: 99px; "><b>Id Comanda</b></td>
		<td style="width: 164px; "><b>Plato</b></td>
		<td style="width: 157px; "><b>Cantidad</b></td>
		<td style="width: 163px; "><b>Estado</b></td>
	</tr>
<%
	DTO_ItemComanda auxIt;
	DTO_Comanda auxC;
		for(Iterator<DTO_Comanda> com = c.iterator(); com.hasNext();)
		{
	auxC = com.next(); // Comanda
	
	for(Iterator<DTO_ItemComanda> it = auxC.getItems().iterator(); it.hasNext();)
	{
	
		auxIt = it.next();
		
		if(auxIt.getEstado().equalsIgnoreCase("pendiente") && auxIt.getPlato().getRubro().equalsIgnoreCase("cafeteria"))
		{
%>
	<tr>
		<td><%=auxC.getNumero()%></td>
		<td><%=auxIt.getPlato().getNombre()%></td>
		<td><%=auxIt.getCantidad()%></td>
		<td><%=auxIt.getEstado()%></td>
		<td>
		
			<form action="ProduccionServlet?action=prepararItemComanda" method ="post" style="width: 344px; ">
	        	<input type="hidden" name="action" value="prepararItemComanda">
	        	<input type ="hidden" name = "comanda" readonly ="readonly" value ="<%=auxC.getNumero()%>">
	        	<input type ="hidden" name = "itemComanda" readonly ="readonly" value ="<%=auxIt.getId()%>">
	        	<input type ="hidden" name = "legajo" readonly ="readonly" value ="<%=emp.getLegajo()%>">
	        	<input type ="hidden" name = "nombre" readonly ="readonly" value ="<%=emp.getNombre()%>">
	        	<input type ="hidden" name = "url" readonly ="readonly" value ="/CafeteriaComanda.jsp">
	        	<input type="submit" value="Realizar Pedido" name="prepararItemComanda" style="font-family:; font-style: normal; font-weight: bold; width: 203px; height: 30px; background-color: #F5ECCE">
        	</form>
		
		</td>
	</tr>
	<% 	
				}
			}
		}	
%>
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