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
<body class="bg-light text-secondary">
    <%
        String error = (String) request.getAttribute("error");
    %>

<div class="container mt-4">
    <h1 class="text-center text-dark">Regístrate</h1>

    <% if (error != null) { %>
    <div class="alert alert-danger text-center">
        <strong>Error:</strong> <%= error %>
    </div>
    <% } %>

    <div class="card shadow-lg mx-auto mt-4" style="max-width: 500px;">
        <div class="card-body">
            <form action="../../registro" method="post">
                <div class="mb-3">
                    <label for="username" class="form-label">Nombre</label>
                    <input type="text" class="form-control" id="username" name="username" placeholder="Ingrese su nombre" required>
                </div>
                <div class="mb-3">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" class="form-control" id="email" name="email" placeholder="Ingrese su email" required>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Contraseña</label>
                    <input type="password" class="form-control" id="password" name="password" placeholder="Ingrese su contraseña" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Estado</label>
                    <select name="activo" class="form-select">
                        <option value="true">Activo</option>
                        <option value="false">No Activo</option>
                    </select>
                </div>
                <div class="mb-3">
                    <label class="form-label">Rol</label>
                    <select name="rolId" class="form-select">
                        <option value="1">Empleado</option>
                        <option value="2">Cliente</option>
                    </select>
                </div>
                <div class="text-center">
                    <input type="submit" class="btn btn-success w-100 mb-2" value="Registrarse">
                    <a href="<%= request.getContextPath() %>/vistas/usuario/login.jsp" class="btn btn-outline-dark w-100">Ir al Login</a>
                </div>
            </form>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</html>
