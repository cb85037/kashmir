<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import= "java.util.Iterator"%>
<%@ page import= "java.util.List"%>
<%@ page import= "dto.DTO_Empleado"%>
<%@ page import= "dto.DTO_Mesa"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body style="height: 369px; background-color: #2E9AFE">

<%
	DTO_Empleado emp = (DTO_Empleado)request.getAttribute("empleado");
	List<DTO_Mesa> mesas = (List<DTO_Mesa>)request.getAttribute("mesasOcupadas");
%>


<form action="CajeroServlet?action=opcionVolver" method ="post">
      <input type ="hidden" name = "nombre" readonly ="readonly" value ="<%=emp.getNombre()%>">
	  <input type ="hidden" name = "legajo" readonly ="readonly" value ="<%=emp.getLegajo()%>">
	  <input type="submit" value="Men�" name="opcionVolver" style="font-family:; font-style: normal; font-weight: bold; width: 203px; height: 30px; background-color: #BDBDBD">
</form>
<input type = "text" name = "nombre" readonly ="readonly" value ="<%=emp.getNombre()%>"><input type="text" name="legajo" readonly value="<%=emp.getLegajo()%>"></td>
</td>

<table align="center" border="2" width="40%" style="color: #000000; background-color: #A9D0F5; font-family:; border-left-color: #000000; border-top-color: #000000; border-right-color: #000000; border-bottom-color: #000000; height: 275px; border-top-style: solid; border-right-style: solid">
    <tr>
        <td colspan="2" align="center" style="border-left-color: #000000; border-top-color: #000000; border-right-color: #000000; border-bottom-color: #000000; font-size: 24px"><b>Facturar Mesa</b></td>
   </tr>
   <tr>
        <td colspan="2" align="center" style="color: Black; border-left-color: #000000; border-top-color: #000000; border-right-color: #000000; border-bottom-color: #000000">
        <form action="CajeroServlet?action=facturarMesa" method ="post">
	        	<input type="hidden" name="action" value="facturarMesa">
	        	<input type ="hidden" name = "nombre" readonly ="readonly" value ="<%=emp.getNombre()%>">
	        	<input type ="hidden" name = "legajo" readonly ="readonly" value ="<%=emp.getLegajo()%>">
	        	<label for="numeroMesa">Mesas<b></b></label>
        		<select name = "numeroMesa">
        		
        		<%
        		        			if (mesas != null) {
        		        		        				for(DTO_Mesa m: mesas){
        		        		%> 
			  		<option value="<%=m.getIdMesa()%>">Mesa <%=m.getNumero()%></option>  
			
			  	<%
        				}
        			}	
			  	%>
				</select>
	        	<input type="submit" value="Facturar Mesa" name="facturarMesa" style="font-family:; font-style: normal; font-weight: bold; width: 203px; height: 30px; background-color: #BDBDBD">
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