<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import= "java.util.Iterator"%>
<%@ page import= "java.util.List"%>
<%@ page import= "dto.DTO_Empleado"%>
<%@ page import= "dto.DTO_Mesa"%>
<%@ page import= "dto.DTO_Comanda"%>
<%@ page import= "dto.DTO_ItemComanda"%>
<%@ page import= "dto.DTO_Factura"%>
<%@ page import= "dto.DTO_ItemFactura"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Facturar</title>
</head>
<body style="height: 369px; background-color: #F7F8E0">

<%
	DTO_Empleado emp = (DTO_Empleado)request.getAttribute("empleado");
	DTO_Factura f = (DTO_Factura)request.getAttribute("factura");
	boolean resp = (Boolean)request.getAttribute("resp");
%>

<%
	if(resp){
%>
	<table cellspacing="3" cellpadding="3" border="2" width="500" style="width: 1215px; background-color: #F5ECCE; border-bottom-style: solid; border-top-style: solid; border-left-style: solid; border-right-style: solid">
	<tr>
		<td colspan="4"><b>Factura <%=" " + f.getNumero()%></b></td>
		<td colspan="4"><b>Mesa <%=" " + f.getMesa().getNumero()%></b></td>
		<td colspan="4"><b>Factura <%=" " + f.getFecha()%></b></td>
		<td colspan="4"><b>Mozo <%=" " + f.getMesa().getComanda().getMozo().getNombre()%></b></td>
	</tr>
	<tr>
		<td style="width: 99px; "><b>Id Factura</b></td>
		<td style="width: 164px; "><b>Producto</b></td>
		<td style="width: 157px; "><b>Cantidad</b></td>
		<td style="width: 163px; "><b>precio</b></td>
	</tr>
<%
	DTO_ItemFactura aux;
	List<DTO_ItemFactura> items = f.getItems();
	
	if(items != null){
		for(Iterator<DTO_ItemFactura> i = items.iterator(); i.hasNext();)
		{
	aux = i.next();
%>
	<tr>
		<td><%=aux.getId()%></td>
		<td><%=aux.getProducto().getNombre()%></td>
		<td><%=aux.getCantidad()%></td>
		<td><%=aux.getPrecio()%></td>
	</tr>
	
	<% 		
	}
}%>
</table>

<%}%>
<b>Total <%=" " + f.getTotal() %></b>
		
</body>
</html>