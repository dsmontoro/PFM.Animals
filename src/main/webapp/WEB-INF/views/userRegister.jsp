<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="utf-8">
<title>Spring MVC. Registrar Usuario</title>
<style>.error { color: red;}</style>
</head>
<body>
    <h1>Registrar un usuario</h1>
    <form:form modelAttribute="user" action="user-register" >
        <p>Nombre de usuario:
            <form:input path="username" placeholder="Username" required="required" />
            <form:errors path="username" cssClass="error" />
        </p>        
        <p>Email:
            <form:input path="email" placeholder="Email" required="required" />
            <form:errors path="email" cssClass="error" />
        </p>
        <p>Password:
            <form:password path="password" placeholder="Password" required="required" showPassword="true" />
        </p>
        <p>Confirmar Password:
            <form:password path="confirmedPassword" required="required" showPassword="true" />
            <form:errors path="confirmedPassword" cssClass="error" />
        </p>
        <p>Nombre Completo:
            <form:input path="name" required="required" />
        </p>        
        <p><input type="submit" value="Registrar"></p>
    </form:form>

    <a href="<c:url value="/home"/>">Home</a>

    <p>UPM-MIW --- ${now}</p>

</body>
</html>