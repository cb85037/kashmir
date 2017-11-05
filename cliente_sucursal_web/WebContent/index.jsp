<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
<h2>Ingreso al Sistema</h2>

<form>
<table border = "4" width = "22%">

	<tr>
		<td colspan="2" align="center"><b>Usuario</b> </td>
		<td><input type = "text" name = "usuario"></td>
	</tr>
	<tr>
		<td colspan="2" align="center"><b>Password</b> </td>
		<td><input type = "password" name = "password"></td>
	</tr>
	 <tr>
	 	<td align = "right" colspan = "8">
	      <input type="hidden" name="action" value="login" >
		  <input type="submit" value="Ingresar" name="loginBot">
		</td>  
    </tr> 
</table>




</form>



</body>
</html>