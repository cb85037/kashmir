<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import= "java.util.Iterator"%>
<%@ page import= "java.util.List"%>
<%@ page import= "dto.DTO_Empleado"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body style="height: 369px; background-color: #F7F8E0">

<%
	DTO_Empleado emp = (DTO_Empleado)request.getAttribute("empleado");
%>

</td>
<input type="text" name="nombre" readonly="readonly" value="<%=emp.getNombre() %>"><input type = "text" name = "legajo" readonly ="readonly" value ="<%=emp.getLegajo()%>"></td>

<table align="center" border="2" width="40%" style="color: #000000; background-color: #F5ECCE; font-family:; border-left-color: #000000; border-top-color: #000000; border-right-color: #000000; border-bottom-color: #000000; height: 275px; border-top-style: solid; border-right-style: solid">
    <tr>
        <td colspan="2" align="center" style="border-left-color: #000000; border-top-color: #000000; border-right-color: #000000; border-bottom-color: #000000; font-size: 24px"><b>Men� de Cajero</b></td>
   </tr>
   <tr>
        <td colspan="2" align="center" style="color: White; border-left-color: #000000; border-top-color: #000000; border-right-color: #000000; border-bottom-color: #000000">
        <form action="EmpleadoServlet?action=opcionCaja" method ="post">
	        	<input type="hidden" name="action" value="opcionCaja">
	        	<input type ="hidden" name = "nombre" readonly ="readonly" value ="<%=emp.getNombre()%>">
	        	<input type ="hidden" name = "legajo" readonly ="readonly" value ="<%=emp.getLegajo()%>">
	        	<input type="submit" value="Caja" name="caja" style="font-family:; font-style: normal; font-weight: bold; width: 203px; height: 30px; background-color: #F5ECCE">
        	</form>
        </td>
   </tr> 
   <tr>
   		
        <td colspan="2" align="center" style="border-left-color: #000000; border-top-color: #000000; border-right-color: #000000; border-bottom-color: #000000">
        	<form action="EmpleadoServlet?action=opcionDeposito" method ="post">
	        	<input type="hidden" name="action" value="opcionDeposito">
	        	<input type ="hidden" name = "nombre" readonly ="readonly" value ="<%=emp.getNombre()%>">
	        	<input type ="hidden" name = "legajo" readonly ="readonly" value ="<%=emp.getLegajo()%>">
	        	<input type="submit" value="Deposito" name="deposito" style="font-family:; font-style: normal; font-weight: bold; width: 203px; height: 30px; background-color: #F5ECCE">
        	</form>
        </td>
        
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