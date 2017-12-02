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
<body style="height: 369px; background-color: #2E9AFE">

<%
	DTO_Empleado emp = (DTO_Empleado)request.getAttribute("empleado");
	boolean resp = (Boolean)request.getAttribute("resp");
%>
<form action="EmpleadoServlet?action=opcionVolverC" method ="post">
      <input type ="hidden" name = "nombre" readonly ="readonly" value ="<%=emp.getNombre()%>">
	  <input type ="hidden" name = "legajo" readonly ="readonly" value ="<%=emp.getLegajo()%>">
	  <input type="submit" value="Menú" name="opcionVolver" style="font-family:; font-style: normal; font-weight: bold; width: 203px; height: 30px; background-color: #BDBDBD">
</form>
<input type = "text" name = "nombre" readonly ="readonly" value ="<%=emp.getNombre()%>"><input type="text" name="legajo" readonly value="<%=emp.getLegajo()%>"></td>
</td>


<%if(resp){ %>

<h1 style="font-family:verdana; color: BLACK;"  align="center">La Caja fue abierta!</h1>
<%}else{ %>
<h1 style="font-family:verdana; color: BLACK;"  align="center">No se pudo abrir la Caja!</h1>
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