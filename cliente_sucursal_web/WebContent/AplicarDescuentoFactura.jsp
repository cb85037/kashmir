<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import= "java.util.Iterator"%>
<%@ page import= "java.util.List"%>
<%@ page import= "dto.DTO_Empleado"%>
<%@ page import= "dto.DTO_ItemFactura"%>
<%@ page import= "dto.DTO_Factura"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Aplicacion de Descuentos</title>
</head>
<body style="height: 369px; background-color: #F7F8E0">

<%
	DTO_Empleado emp = (DTO_Empleado)request.getAttribute("empleado");
	DTO_Factura f = (DTO_Factura)request.getAttribute("factura");
		List<DTO_ItemFactura> items = (List<DTO_ItemFactura>)request.getAttribute("items");
%>
<h1 align = "center" >Facturación</h1>


	<table cellspacing="3" cellpadding="3" border="2" width="500" style="width: 1215px; background-color: #F5ECCE; border-bottom-style: solid; border-top-style: solid; border-left-style: solid; border-right-style: solid">
	<tr>
		<td colspan="2" align="center" style="border-left-color: #000000; border-top-color: #000000; border-right-color: #000000; border-bottom-color: #000000; font-size: 24px"><b>Factura <%=" " + f.getNumero()%></b></td>
		<td colspan="4"><b>Mesa <%=" " + f.getMesa().getNumero()%></b></td>
	</tr>
	<tr>
		<td style="width: 99px; "><b>Codigo</b></td>
		<td style="width: 164px; "><b>Producto</b></td>
		<td style="width: 164px; "><b>Precio</b></td>
		<td style="width: 157px; "><b>Cantidad</b></td>
	</tr>
<%
	DTO_ItemFactura aux;
	
	if(f != null){
		for(Iterator<DTO_ItemFactura> i = items.iterator(); i.hasNext();)
		{
	aux = i.next();
%>
	<tr>
	<form action="CajeroServlet?action=AplicarDescuento" method ="post" style="width: 344px; ">
		<td><%=aux.getId()%></td>
		<td><%=aux.getProducto()%></td>
		<td><%=aux.getPrecio()%></td>
		<td><input type = "text" name ="descuento"></td>
		<td>
	        	<input type="hidden" name="action" value="agregarDescuento">
	        	<input type ="hidden" name = "factura" readonly ="readonly" value ="<%=f.getNumero()%>">
	        	<input type ="hidden" name = "itemComanda" readonly ="readonly" value ="<%=aux.getId()%>">
	        	<input type ="hidden" name = "legajo" readonly ="readonly" value ="<%=emp.getLegajo()%>">
	        	<input type ="hidden" name = "nombre" readonly ="readonly" value ="<%=emp.getNombre()%>">
	        	<input type="submit" value="Agregar" name="agregarDescuento" style="font-family:; font-style: normal; font-weight: bold; width: 203px; height: 30px; background-color: #F5ECCE">
		</td>
		</form>
	</tr>
	<% 		
	}
}%>
