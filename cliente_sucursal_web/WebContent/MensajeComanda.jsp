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
	String item = (String)request.getAttribute("item");
	String comanda = (String)request.getAttribute("comanda");
	String mensaje = (String)request.getAttribute("mensaje");
%>

<form action="EmpleadoServlet?action=opcionVolverP" method ="post">
      <input type ="hidden" name = "nombre" readonly ="readonly" value ="<%=emp.getNombre()%>">
	  <input type ="hidden" name = "legajo" readonly ="readonly" value ="<%=emp.getLegajo()%>">
	  <input type="submit" value="Menú Principal" name="regresar" style="font-family:; font-style: normal; font-weight: bold; width: 203px; height: 30px; background-color: "#F7F8E0">
</form>
<input type = "text" name = "nombre" readonly ="readonly" value ="<%=emp.getNombre()%>"><input type="text" name="legajo" readonly="readonly" value="<%=emp.getLegajo()%>"></td>
</td>


<h1 style="font-family:verdana; color: green;"  align="center"><%=item%></h1>
<h1 style="font-family:verdana; color: green;"  align="center"><%=comanda%></h1>
<h1 style="font-family:verdana; color: green;"  align="center"><%=mensaje%></h1> 

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