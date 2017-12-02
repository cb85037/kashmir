<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bienvenido al Restaurante!</title>
</head>
<body style="background-color: #2E9AFE">

<h1 align = "center"><b>Bienvenido a Alle Gute Wurst!</b></h1>
<div style="height: 45px; "></div>	
<form action="./EmpleadoServlet" method="post" style="height: 355px; ">

<table align="center" border="2" width="30%" style="cursor: auto; background-color: #A9D0F5; border-bottom-style: solid; border-top-style: solid; border-left-style: solid; border-right-style: solid; font-family:; width: 495px; height: 194px">
    <tr>
        <td colspan="8" align="center" style="background-color: #A9D0F5; height: 31px"><b>Ingreso al Sistema</b></td>
        
   </tr>
   <tr style="height: 37px; ">
        <td colspan="3" align="left" style="background-color: #A9D0F5; height: 18px">
        	<b><label for="usuario">Usuario<b></b></label></b></td><b>
        </b><td style="background-color: #A9D0F5"><b><input type="text" name="usuario" style="border-bottom-style: solid; border-top-style: solid; border-left-style: solid; border-right-style: solid; border-bottom-width: thin; border-right-width: thin; border-left-width: thin; border-top-width: thin"></b></td><b>
   </b></tr><b> 
   </b><tr><b>
        </b><td colspan="3" align="left" style="background-color: #A9D0F5"><b>
        	</b><label for= "password"><b>Contraseña</b></label></td><b>
        </b><td style="background-color: #A9D0F5"><b><input type="password" name="password" style="border-bottom-style: solid; border-bottom-width: thin; border-top-style: solid; border-left-style: solid; border-right-width: thin; border-right-style: solid; border-left-width: thin; border-top-width: thin; width: 144px"></b></td><b>
    </b></tr><b> 
    </b><tr><b>
        </b><td colspan="8" align="center" style="background-color: #A9D0F5"><b>
        	<label for="tipo">Usuario<b></b></label>
        	<select name = "tipo">
			  <option value="mozo">Mozo</option>
			  <option value="produccion">Produccion</option>
			  <option value="cajero">Cajero</option>
			</select>
			
	        </b><b><input type="hidden" name="action" value="validarEmpleado"></b><b>
	        </b><b><input type="submit" value="Entrar" name="validar" style="font-family:; font-style: normal; font-weight: bold; width: 133px; height: 30px; background-color: #BDBDBD"></b><b>
        </b></td><b>

    </b></tr><b> 
</b></table><b>
</b></form><b>
</b></body><b>
</b></html>