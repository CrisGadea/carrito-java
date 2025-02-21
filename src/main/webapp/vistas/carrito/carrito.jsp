        <%@ page import="com.edu.unlz.tienda.modelos.Producto" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.edu.unlz.tienda.modelos.ProductoCarrito" %>
<%@ page import="java.util.concurrent.atomic.AtomicReference" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="es">
<head>

    <meta charset="UTF-8">
    <title>Carrito de Compras</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
</head>
<body>
<h2>Carrito de Compras</h2>

<div class="container">
    <div class="row">
        <%
            List<ProductoCarrito> productosCarrito = (ArrayList<ProductoCarrito>) request.getAttribute("productosCarrito");
            String error = (String) request.getAttribute("error");

            // Inicializa subtotal en 0 para evitar errores de variable no inicializada
            AtomicReference<Double> subtotal = new AtomicReference<>(0.0);

            if (productosCarrito != null && !productosCarrito.isEmpty()) {
                productosCarrito.forEach(product -> {
                    subtotal.set(subtotal.get() + product.getPrecio());
                });

                for (ProductoCarrito productoCarrito : productosCarrito) {
        %>

        <!-- Card de Bootstrap para cada producto -->
        <div class="col-md-4 mb-4">
            <div class="card" style="width: 18rem;">
                <img src="../<%=productoCarrito.getUrlImagen()%>"
                     class="card-img-top" alt="<%=productoCarrito.getNombre()%>">
                <div class="card-body">
                    <h5 class="card-title"><%=productoCarrito.getNombre()%></h5>
                    <p class="card-text">Precio: $<%=productoCarrito.getPrecio()%></p>

                    <!-- Cantidad con botones de sumar/restar -->
                    <p class="card-text d-flex align-items-center">
                        Cantidad:
                        <a href="<%=request.getContextPath() + "/carrito?productoId=" + productoCarrito.getIdProducto() + "&accion=restar&method=updateCantidad"%>"
                           class="btn btn-secondary btn-sm mx-2">-</a>
                        <span><%=productoCarrito.getCantidad()%></span>
                        <a href="<%=request.getContextPath() + "/carrito?productoId=" + productoCarrito.getIdProducto() + "&accion=sumar&method=updateCantidad"%>"
                           class="btn btn-secondary btn-sm mx-2">+</a>
                    </p>

                    <!-- Botón de eliminar producto -->
                    <a href="<%=request.getContextPath() + "/carrito?productoId=" + productoCarrito.getIdProducto() + "&method=delete"%>"
                       class="btn btn-danger mt-2">Eliminar</a>
                </div>
            </div>
        </div>

        <%
            } // Fin del for
        } else { // Si el carrito está vacío
        %>

        <!-- Mostrar error si está presente -->
        <% if (error != null) { %>
        <div class="alert alert-danger">
            <%= error %>
        </div>
        <% } else { %>
        <p>No hay productos en el carrito.</p>
        <% } %>

        <%
            } // Fin del else
        %>
    </div>

    <!-- Mostrar subtotal solo si hay productos en el carrito -->
    <% if (productosCarrito != null && !productosCarrito.isEmpty()) { %>
    <div class="mt-2">
        <p class="text-center">Subtotal: $<%=subtotal.get()%></p>
    </div>
    <button type="button" class="btn btn-primary" onclick="window.location.href='./comprar'">Proceder a la compra</button>
    <% } %>

    <button type="button" class="btn btn-danger" onclick="window.location.href='./index.jsp'">Volver al Inicio</button>
</div>

<!-- Incluir Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js" crossorigin="anonymous"></script>

</body>

</html>
