<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!doctype html>
<html lang="es">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Registrate</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body class="bg-gray text-secondary">
<h1 class="text-center mt-2">Registrate</h1>
<form action="../../registro" method="post" class="container text-center border-dark mt-4">
<%--    <input name="accion" value="register" type="hidden">--%>
    <div class="mb-3">
        <input type="text" placeholder="Ingrese su nombre" name="username">
    </div>
    <div class="mb-3">
        <input type="email" placeholder="Ingrese su email" name="email">
    </div>
    <div class="mb-3">
        <input type="password" placeholder="Ingrese su password" name="password">
    </div>
    <div class="mb-3">
        <select class="c" name="activo">
            <option value=true>Activo</option>
            <option value=false>No Activo</option>
        </select>
        <select class="c" name="rolId">
            <option value="1">Empleado</option>
            <option value="2">Cliente</option>
        </select>
    </div>
    <input type="submit" class="btn btn-success m-1" value="Registrarse">
    <a type="button" href="./login.jsp" class="btn btn-light text-bg-dark m-1">Ir al Login</a>

</form>


<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
</body>
</html>
