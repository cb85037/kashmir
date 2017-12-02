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
<body>

<%
	DTO_Empleado emp = (DTO_Empleado)request.getAttribute("empleado");
	boolean resp = (Boolean)request.getAttribute("resp");
%>

<form action="EmpleadoServlet?action=opcionVolver" method ="post" style="width: 225px; height: 42px">
	   	<input type="submit" value="Menú" name="opcionVolver" style="font-family:; font-style: normal; font-weight: bold; width: 146px; height: 30px; background-color: #BDBDBD">
		<input type ="hidden" name = "nombre" readonly ="readonly" value ="<%=emp.getNombre()%>">
	    <input type ="hidden" name = "legajo" readonly ="readonly" value ="<%=emp.getLegajo()%>">
</form>
<input type="text" name="legajo" readonly value="<%=emp.getLegajo()%>" style="width: 109px; ">
<input align="right" type="text" name="nombre" readonly value="<%=emp.getNombre()%>" style="width: 111px; ">

<%if(resp){ %>

<h1 style="font-family:verdana; color: black;"  align="center">La Mesa se ha cerrado satisfactoriamente</h1>
<%}else{ %>
<h1 style="font-family:verdana; color: black;"  align="center">La Mesa no se ha podido cerrar, verifique si no tiene comandas pendientes!</h1>
<%} %>


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