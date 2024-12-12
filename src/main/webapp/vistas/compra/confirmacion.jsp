<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Confirmación de Compra</h1>
<p>Gracias por su compra</p>
<p class="border-dark"><%= request.getAttribute("mensaje") %></p>

<button type="button" class="btn btn-danger" onclick="window.location.href='./index.jsp'">Volver al Inicio</button>

</body>
</html>
