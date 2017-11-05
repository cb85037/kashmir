<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import= "java.util.Iterator"%>
<%@ page import= "java.util.List"%>
<%@ page import= "dto.DTO_Empleado"%>
<%@ page import= "dto.DTO_Mesa"%>
<%@ page import= "dto.DTO_Comanda"%>
<%@ page import= "dto.DTO_Plato"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Alta de comanda</title>
</head>
<body style="height: 369px; background-color: #F7F8E0">

<%
	DTO_Empleado emp = (DTO_Empleado)request.getAttribute("empleado");
	DTO_Comanda c = (DTO_Comanda)request.getAttribute("comanda");
	List<DTO_Plato> bebida = (List<DTO_Plato>)request.getAttribute("bebida");
	List<DTO_Plato> carne = (List<DTO_Plato>)request.getAttribute("carne");
	List<DTO_Plato> pescado = (List<DTO_Plato>)request.getAttribute("pescado");
	List<DTO_Plato> pasta = (List<DTO_Plato>)request.getAttribute("pasta");
	List<DTO_Plato> guarnicion = (List<DTO_Plato>)request.getAttribute("guarnicion");
%>
<form action="EmpleadoServlet?action=opcionVolver" method ="post" style="width: 225px; height: 42px">
	   	<input type="submit" value="Menú" name="opcionVolver" style="font-family:; font-style: normal; font-weight: bold; width: 146px; height: 30px; background-color: #F7F8E0">
		<input type ="hidden" name = "nombre" readonly ="readonly" value ="<%=emp.getNombre()%>">
	    <input type ="hidden" name = "legajo" readonly ="readonly" value ="<%=emp.getLegajo()%>">
</form>
<input type="text" name="legajo" readonly="readonly" value="<%=emp.getLegajo()%>" style="width: 109px; ">
<input align="right" type="text" name="nombre" readonly="readonly" value="<%=emp.getNombre()%>" style="width: 111px; ">





<h1 align = "center" >CARTA</h1>


	<table cellspacing="3" cellpadding="3" border="2" width="500" style="width: 1215px; background-color: #F5ECCE; border-bottom-style: solid; border-top-style: solid; border-left-style: solid; border-right-style: solid">
	<tr>
		<td colspan="2" align="center" style="border-left-color: #000000; border-top-color: #000000; border-right-color: #000000; border-bottom-color: #000000; font-size: 24px"><b>Bebidas</b></td>
	</tr>
	<tr>
		<td style="width: 99px; "><b>Codigo</b></td>
		<td style="width: 164px; "><b>Descripcion</b></td>
		<td style="width: 164px; "><b>Precio</b></td>
		<td style="width: 157px; "><b>Cantidad</b></td>
	</tr>
<%
	DTO_Plato aux;
	
	if(bebida != null){
		for(Iterator<DTO_Plato> i = bebida.iterator(); i.hasNext();)
		{
	aux = i.next();
%>
	<tr>
	<form action="MozoServlet?action=agregarItemComanda" method ="post" style="width: 344px; ">
		<td><%=aux.getCodigo()%></td>
		<td><%=aux.getNombre()%></td>
		<td><%=aux.getPrecio()%></td>
		<td><input type = "text" name ="cantidad"></td>
		<td>
	        	<input type="hidden" name="action" value="agregarItemComanda">
	        	<input type ="hidden" name = "comanda" readonly ="readonly" value ="<%=c.getNumero()%>">
	        	<input type ="hidden" name = "plato" readonly ="readonly" value ="<%=aux.getCodigo()%>">
	        	<input type ="hidden" name = "legajo" readonly ="readonly" value ="<%=emp.getLegajo()%>">
	        	<input type ="hidden" name = "nombre" readonly ="readonly" value ="<%=emp.getNombre()%>">
	        	<input type="submit" value="Agregar" name="agregarItemComanda" style="font-family:; font-style: normal; font-weight: bold; width: 203px; height: 30px; background-color: #F5ECCE">
		</td>
		</form>
	</tr>
	<%
		}
	}
	%>
</table>
<div style="height: 45px; "></div>
<table cellspacing="3" cellpadding="3" border="2" width="500" style="width: 1215px; background-color: #F5ECCE; border-bottom-style: solid; border-top-style: solid; border-left-style: solid; border-right-style: solid">
	<tr>
		<td colspan="2" align="center" style="border-left-color: #000000; border-top-color: #000000; border-right-color: #000000; border-bottom-color: #000000; font-size: 24px"><b>Carnes</b></td>
	</tr>
	<tr>
		<td style="width: 99px; "><b>Codigo</b></td>
		<td style="width: 164px; "><b>Descripcion</b></td>
		<td style="width: 164px; "><b>Precio</b></td>
		<td style="width: 157px; "><b>Cantidad</b></td>
	</tr>
<%
	DTO_Plato aux1;
	
	if(carne != null){
		for(Iterator<DTO_Plato> i = carne.iterator(); i.hasNext();)
		{
	aux1 = i.next();
%>
	<tr>
	<form action="MozoServlet?action=agregarItemComanda" method ="post" style="width: 344px; ">
		<td><%=aux1.getCodigo()%></td>
		<td><%=aux1.getNombre()%></td>
		<td><%=aux1.getPrecio()%></td>
		<td><input type = "text" name ="cantidad"></td>
		<td>
		
	        	<input type="hidden" name="action" value="agregarItemComanda">
	        	<input type ="hidden" name = "comanda" readonly ="readonly" value ="<%=c.getNumero()%>">
	        	<input type ="hidden" name = "plato" readonly ="readonly" value ="<%=aux1.getCodigo()%>">
	        	<input type ="hidden" name = "legajo" readonly ="readonly" value ="<%=emp.getLegajo()%>">
	        	<input type ="hidden" name = "nombre" readonly ="readonly" value ="<%=emp.getNombre()%>">
	        	<input type="submit" value="Agregar" name="agregarItemComanda" style="font-family:; font-style: normal; font-weight: bold; width: 203px; height: 30px; background-color: #F5ECCE">
        	
		
		</td>
		</form>
	</tr>
	<%
		}
	}
	%>
</table>
<div style="height: 45px; "></div>
<table cellspacing="3" cellpadding="3" border="2" width="500" style="width: 1215px; background-color: #F5ECCE; border-bottom-style: solid; border-top-style: solid; border-left-style: solid; border-right-style: solid">
	<tr>
		<td colspan="2" align="center" style="border-left-color: #000000; border-top-color: #000000; border-right-color: #000000; border-bottom-color: #000000; font-size: 24px"><b>Pescados</b></td>
	</tr>
	<tr>
		<td style="width: 99px; "><b>Codigo</b></td>
		<td style="width: 164px; "><b>Descripcion</b></td>
		<td style="width: 164px; "><b>Precio</b></td>
		<td style="width: 157px; "><b>Cantidad</b></td>
	</tr>
<%
	DTO_Plato aux2;
	
	if(pescado != null){
		for(Iterator<DTO_Plato> i = pescado.iterator(); i.hasNext();)
		{
	aux2 = i.next();
%>
	<tr>
	<form action="MozoServlet?action=agregarItemComanda" method ="post" style="width: 344px; ">
		<td><%=aux2.getCodigo()%></td>
		<td><%=aux2.getNombre()%></td>
		<td><%=aux2.getPrecio()%></td>
		<td><input type = "text" name ="cantidad"></td>
		<td>
		
	        	<input type="hidden" name="action" value="agregarItemComanda">
	        	<input type ="hidden" name = "comanda" readonly ="readonly" value ="<%=c.getNumero()%>">
	        	<input type ="hidden" name = "plato" readonly ="readonly" value ="<%=aux2.getCodigo()%>">
	        	<input type ="hidden" name = "legajo" readonly ="readonly" value ="<%=emp.getLegajo()%>">
	        	<input type ="hidden" name = "nombre" readonly ="readonly" value ="<%=emp.getNombre()%>">
	        	<input type="submit" value="Agregar" name="agregarItemComanda" style="font-family:; font-style: normal; font-weight: bold; width: 203px; height: 30px; background-color: #F5ECCE">
        	
		
		</td>
		</form>
	</tr>
	<%
		}
	}
	%>
</table>
<div style="height: 45px; "></div>
<table cellspacing="3" cellpadding="3" border="2" width="500" style="width: 1215px; background-color: #F5ECCE; border-bottom-style: solid; border-top-style: solid; border-left-style: solid; border-right-style: solid">
	<tr>
		<td colspan="2" align="center" style="border-left-color: #000000; border-top-color: #000000; border-right-color: #000000; border-bottom-color: #000000; font-size: 24px"><b>Pastas</b></td>
	</tr>
	<tr>
		<td style="width: 99px; "><b>Codigo</b></td>
		<td style="width: 164px; "><b>Descripcion</b></td>
		<td style="width: 164px; "><b>Precio</b></td>
		<td style="width: 157px; "><b>Cantidad</b></td>
	</tr>
<%
	DTO_Plato aux3;
	
	if(pasta != null){
		for(Iterator<DTO_Plato> i = pasta.iterator(); i.hasNext();)
		{
	aux3 = i.next();
%>
	<tr>
	<form action="MozoServlet?action=agregarItemComanda" method ="post" style="width: 344px; ">
		<td><%=aux3.getCodigo()%></td>
		<td><%=aux3.getNombre()%></td>
		<td><%=aux3.getPrecio()%></td>
		<td><input type = "text" name ="cantidad"></td>
		<td>
		
	        	<input type="hidden" name="action" value="agregarItemComanda">
	        	<input type ="hidden" name = "comanda" readonly ="readonly" value ="<%=c.getNumero()%>">
	        	<input type ="hidden" name = "plato" readonly ="readonly" value ="<%=aux3.getCodigo()%>">
	        	<input type ="hidden" name = "legajo" readonly ="readonly" value ="<%=emp.getLegajo()%>">
	        	<input type ="hidden" name = "nombre" readonly ="readonly" value ="<%=emp.getNombre()%>">
	        	<input type="submit" value="Agregar" name="agregarItemComanda" style="font-family:; font-style: normal; font-weight: bold; width: 203px; height: 30px; background-color: #F5ECCE">
        	
		</td>
		</form>
	</tr>
	<%
		}
	}
	%>
</table>
<div style="height: 45px; "></div>
<table cellspacing="3" cellpadding="3" border="2" width="500" style="width: 1215px; background-color: #F5ECCE; border-bottom-style: solid; border-top-style: solid; border-left-style: solid; border-right-style: solid">
	<tr>
		<td colspan="2" align="center" style="border-left-color: #000000; border-top-color: #000000; border-right-color: #000000; border-bottom-color: #000000; font-size: 24px"><b>Guarniciones</b></td>
	</tr>
	<tr>
		<td style="width: 99px; "><b>Codigo</b></td>
		<td style="width: 164px; "><b>Descripcion</b></td>
		<td style="width: 164px; "><b>Precio</b></td>
		<td style="width: 157px; "><b>Cantidad</b></td>
	</tr>
<%
	DTO_Plato aux4;
	
	if(guarnicion != null){
		for(Iterator<DTO_Plato> i = guarnicion.iterator(); i.hasNext();)
		{
	aux4 = i.next();
%>

	<tr>
	<form action="MozoServlet?action=agregarItemComanda" method ="post" style="width: 344px; ">
		<td><%=aux4.getCodigo()%></td>
		<td><%=aux4.getNombre()%></td>
		<td><%=aux4.getPrecio()%></td>
		<td><input type = "text" name ="cantidad"></td>
		<td>
		
	        	<input type="hidden" name="action" value="agregarItemComanda">
	        	<input type ="hidden" name = "comanda" readonly ="readonly" value ="<%=c.getNumero()%>">
	        	<input type ="hidden" name = "plato" readonly ="readonly" value ="<%=aux4.getCodigo()%>">
	        	<input type ="hidden" name = "legajo" readonly ="readonly" value ="<%=emp.getLegajo()%>">
	        	<input type ="hidden" name = "nombre" readonly ="readonly" value ="<%=emp.getNombre()%>">
	        	<input type="submit" value="Agregar" name="agregarItemComanda" style="font-family:; font-style: normal; font-weight: bold; width: 203px; height: 30px; background-color: #F5ECCE">
        	
		</td>
	</form>
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