<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import= "java.util.Iterator"%>
<%@ page import= "java.util.List"%>
<%@ page import= "dto.DTO_Movimiento"%>
<%@ page import= "dto.DTO_Local"%>
<%@ page import= "dto.DTO_Empleado"%>
<html>
<head>
	<title>Despacho</title>
</head>
<body style="height: 369px; background-color: #2E9AFE">

<%
	DTO_Empleado emp = (DTO_Empleado)request.getAttribute("empleado");
%>
<form action="EmpleadoServlet?action=opcionVolverC" method ="post">
      <input type ="hidden" name = "nombre" readonly ="readonly" value ="<%=emp.getNombre()%>">
	  <input type ="hidden" name = "legajo" readonly ="readonly" value ="<%=emp.getLegajo()%>">
	   <input type="submit" value="Volver al Menú" name="opcionVolver" style="font-family:; font-style: normal; font-weight: bold; width: 203px; height: 30px; background-color: #BDBDBD">
</form>
<input type = "text" name = "nombre" readonly ="readonly" value ="<%=emp.getNombre() %>"><input type="text" name="legajo" readonly value="<%=emp.getLegajo()%>"></td>
</td>



<table cellspacing="3" cellpadding="3" border="2" width="500" style="width: 1215px; background-color: #A9D0F5; border-bottom-style: solid; border-top-style: solid; border-left-style: solid; border-right-style: solid">
	<tr>
		<td colspan="4"><b>Movimientos</b></td>
	</tr>
	<tr>
		<td style="width: 99px; "><b>Id</b></td>
		<td style="width: 164px; "><b>Motivo</b></td>
		<td style="width: 157px; "><b>Origen</b></td>
		<td style="width: 163px; "><b>Destino</b></td>
		<td style="width: 191px; "><b>Area Destino</b></td>
	</tr>
<%
	DTO_Movimiento aux;
	String id; 	
	List<DTO_Movimiento> movimientos = (List<DTO_Movimiento>)request.getAttribute("movimientosSucursal");
	
	if(movimientos != null){
		for(Iterator<DTO_Movimiento> i = movimientos.iterator(); i.hasNext();)
		{
	aux = i.next();
%>
	<tr>
		<td><%=aux.getIdMovimiento()%></td>
		<td><%=aux.getMotivo()%></td>
		<td><%=aux.getOrigen().getDescripcion()%></td>
		<td><%=aux.getDestino().getDescripcion()%></td>
		<td><%=aux.getArea().getDescripcion()%></td>
		<td>
		
			<form action="CajeroServlet?action=despachar" method ="post" style="width: 344px; ">
	        	<input type="hidden" name="action" value="despachar">
	        	<input type ="hidden" name = "movimiento" readonly ="readonly" value ="<%=aux.getIdMovimiento()%>">
	        	<input type ="hidden" name = "legajo" readonly ="readonly" value ="<%=emp.getLegajo()%>">
	        	<input type ="hidden" name = "nombre" readonly ="readonly" value ="<%=emp.getNombre()%>">
	        	<input type="submit" value="Despachar" name="despachar" style="font-family:; font-style: normal; font-weight: bold; width: 203px; height: 30px; background-color: #BDBDBD">
        	</form>
		
		</td>
	</tr>
<% 		}
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