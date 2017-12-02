<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body style="background-color: #81BEF7;text-align:center;">
<h2 style="font-family:verdana;" >Bienvenido a Alles Gute Wurst </h2> 
<p style="font-family:courier;">Por favor, ingrese su usuario y su contrase&ntilde;a para poder acceder al sistema</p>

<form>
<table border = "4" width = "100%" style="background-color:#F5DA81;">

	<tr>
		<td colspan="2" align="left"><b>Usuario</b> </td>
		<td width="90%"><input type = "text" name = "usuario"></td>
	</tr>
	<tr>
		<td colspan="2" align="left"><b>Password</b> </td>
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