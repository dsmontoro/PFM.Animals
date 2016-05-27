<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="utf-8">
<title>Spring MVC.Registro de Usuario</title>
</head>

<body>
	<H1>Registro de usuario correcto</H1>

	<h3>El usuario ha sido registrado satisfactoriamente</h3>
	
	<a href="<c:url value="/home"/>">Home</a>
	
	<p>UPM-MIW --- ${now}</p>

</body>
</html>