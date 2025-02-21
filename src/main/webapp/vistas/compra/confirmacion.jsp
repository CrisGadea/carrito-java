<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<% if (request.getAttribute("error") == null || request.getAttribute("error").toString().isEmpty()) { %>
<h1>Confirmación de Compra</h1>
<p>Gracias por su compra</p>
<p class="border-dark"><%= request.getAttribute("mensaje") %></p>
<% } %>

<% if (request.getAttribute("error") != null && !request.getAttribute("error").toString().isEmpty()) { %>
<p class="alert alert-danger"><%= request.getAttribute("error") %></p>
<% } %>

<button type="button" class="btn btn-danger" onclick="window.location.href='./index.jsp'">Volver al Inicio</button>

</body>

</html>
