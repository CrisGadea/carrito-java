<%--
  Created by IntelliJ IDEA.
  User: cristiangadea
  Date: 18/07/2024
  Time: 7:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Empleado</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
    <script src="<%=request.getContextPath()%>/js/jquery-3.6.0.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/empleado.js"></script>
</head>
<body>
<div class="container">
    <h1>Empleados</h1>
    <div class="row">
        <div class="col-md-6">
            <form action="<%=request.getContextPath()%>/empleado/guardar" method="post">
                <div class="form-group
                ">
                    <label for="nombre">Nombre</label>
                    <input type="text" id="nombre" name="nombre" class="form-control">
                </div>
                <div class="form-group
                ">
                    <label for="apellido">Apellido</label>
                    <input type="text" id="apellido" name="apellido" class="form-control">
                </div>
                <div class="form-group
                ">
                    <label for="correo">Correo</label>
                    <input type="email" id="correo" name="correo" class="form-control">
                </div>
                <div class="form-group
                ">
                    <label for="telefono">Telefono</label>
                    <input type="text" id="telefono" name="telefono" class="form-control">
                </div>
                <div class="form-group
                ">
                    <label for="direccion">Direccion</label>
                    <input type="text" id="direccion" name="direccion" class="form-control">
                </div>
                <div class="form-group
                ">
                    <label for="usuario">Usuario</label>
                    <input type="text" id="usuario" name="usuario" class="form-control">
                </div>
                <div class="form-group
                ">
                    <label for="clave">Clave</label>
                    <input type="password" id="clave" name="clave" class="form-control">
                </div>
                <div class="form-group
                ">
                    <button type="submit" class="btn btn-primary">Guardar</button>
                </div>
            </form>
        </div>

        <div class="col-md-6">
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Nombre</th>
                        <th>Apellido</th>
                        <th>Correo</th>
                        <th>Telefono</th>
                        <th>Direccion</th>
                        <th>Usuario</th>
                        <th>Clave</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${empleados}" var="empleado">
                        <tr>
                            <td>${empleado.nombre}</td>
                            <td>${empleado.apellido}</td>
                            <td>${empleado.correo}</td>
                            <td>${empleado.telefono}</td>
                            <td>${empleado.direccion}</td>
                            <td>${empleado.usuario}</td>
                            <td>${empleado.clave}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
