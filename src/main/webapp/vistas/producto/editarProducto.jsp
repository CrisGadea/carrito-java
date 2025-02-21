<%@ page import="com.edu.unlz.tienda.modelos.Producto" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: cristiangadea
  Date: 21/02/2025
  Time: 11:35 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar Productos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
    <style>
        body {
            background-color: #f8f9fa;
        }
        .card {
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
        }
        .btn-success {
            background-color: #28a745;
            border-color: #28a745;
        }
        .btn-success:hover {
            background-color: #218838;
            border-color: #218838;
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <div class="card p-4">
        <h2 class="text-center text-primary mb-4">Editar Productos</h2>

        <% List<Producto> productos = (List<Producto>) session.getAttribute("productos"); %>

        <% if (productos != null && !productos.isEmpty()) { %>
        <div class="table-responsive">
            <table class="table table-striped table-hover">
                <thead class="table-dark text-center">
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Precio</th>
                    <th>Stock</th>
                    <th>Imagen URL</th>
                    <th>Acciones</th>
                </tr>
                </thead>
                <tbody>
                <% for (Producto producto : productos) { %>
                <tr>
                    <form action="<%= request.getContextPath() %>/productos?method=update" method="POST">
                        <td class="text-center">
                            <input type="hidden" name="id" value="<%= producto.getId() %>">
                            <strong><%= producto.getId() %></strong>
                        </td>
                        <td>
                            <input type="text" class="form-control" name="nombre" value="<%= producto.getNombre() %>" required>
                        </td>
                        <td>
                            <input type="number" class="form-control" name="precio" value="<%= producto.getPrecio() %>" step="0.01" required>
                        </td>
                        <td>
                            <input type="number" class="form-control" name="stock" value="<%= producto.getStock() %>" required>
                        </td>
                        <td>
                            <input type="text" class="form-control" name="urlImg" value="<%= producto.getUrlImg() %>" required>
                        </td>
                        <td class="text-center">
                            <button type="submit" class="btn btn-success btn-sm">
                                <i class="fas fa-save"></i> Guardar
                            </button>
                        </td>
                    </form>
                </tr>
                <% } %>
                </tbody>
            </table>
        </div>
        <% } else { %>
        <div class="alert alert-warning text-center">No hay productos disponibles para editar.</div>
        <% } %>

        <div class="text-center mt-3">
            <a href="<%= request.getContextPath() %>/index.jsp" class="btn btn-primary">
                <i class="fas fa-arrow-left"></i> Volver
            </a>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
