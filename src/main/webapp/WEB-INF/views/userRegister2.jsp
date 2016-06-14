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
    <form action="api/v0/users" method="post" enctype="multipart/form-data" >
        <p>Nombre de usuario:
           
             <input name="username" type="text"/>  
        </p>        
        <p>Email:
            
             <input name="email" type="text"/>  
        </p>
        <p>Password:
            
             <input name="password" type="text"/>  
        </p>
        <p>Confirmar Password:
           
             <input name="confirmedPassword" type="text"/>  
        </p>
        <p>Nombre Completo:
            
              <input name="name" type="text"/> 
        </p>  
        <p>
            Image (in JPEG format only):
            <input name="image" type="file"/>  
            </p>
                
             <p> <input type="submit" value="Registrar"></p>
    </form>

    <a href="<c:url value="/home"/>">Home</a>

    <p>UPM-MIW --- ${now}</p>

</body>
</html>