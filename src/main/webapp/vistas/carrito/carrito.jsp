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

            AtomicReference<Double> subtotal;
            if (productosCarrito != null && !productosCarrito.isEmpty()) {
                subtotal = new AtomicReference<>((double) 0);
                productosCarrito.forEach(product -> {
                    subtotal.lazySet(subtotal.get() + product.getPrecio());
                });
                for (ProductoCarrito productoCarrito : productosCarrito) {
        %>

        <!-- Card de Bootstrap para cada producto -->
        <div class="col-md-4 mb-4">
            <div class="card" style="width: 18rem;">
                <img src="https://www.tienda-albiceleste.com/cdn/shop/products/CAMISETA-ARGENTINA-MESSI-3-ESTRELLAS-ADULTOS-2022-2023.jpg?v=1671492233"
                     class="card-img-top" alt="<%=productoCarrito.getNombre()%>">
                <div class="card-body">
                    <h5 class="card-title"><%=productoCarrito.getNombre()%></h5>
                    <p class="card-text">Precio: $<%=productoCarrito.getPrecio()%></p>

                    <!-- Cantidad con botones de sumar/restar -->
                    <p class="card-text d-flex align-items-center">
                        Cantidad:
                        <a href="
                        <%=request.getContextPath() + "/carrito?productoId=" + productoCarrito.getIdProducto() + "&accion=restar&method=updateCantidad"%>"
                           class="btn btn-secondary btn-sm mx-2">-</a>
                        <span><%=productoCarrito.getCantidad()%></span>
                        <a href="
                        <%=request.getContextPath() + "/carrito?productoId=" + productoCarrito.getIdProducto() + "&accion=sumar&method=updateCantidad"%>"
                           class="btn btn-secondary btn-sm mx-2">+</a>
                    </p>

                    <!-- Botón de eliminar producto -->
                    <a href="
                        <%=request.getContextPath() + "/carrito?productoId=" + productoCarrito.getIdProducto() + "&method=delete"%>"
                       class="btn btn-danger mt-2">Eliminar</a>
                </div>
            </div>
        </div>

        <%
            }

        } else {
            subtotal = null;
        %>
        <p>No hay productos en el carrito.</p>
        <%
            }
        %>
    </div>
    <% if (subtotal != null) { %>
    <div class="mt-2">
        <p class="text-center">Subtotal: $<%=subtotal%></p>
    </div>
    <!-- Formulario para proceder con la compra -->
<%--    <form action="<%=request.getContextPath() + "/procesarCompra"%>" method="POST" class="mt-4">--%>
    <button type="button" class="btn btn-primary"  onclick="window.location.href='./comprar'">Proceder a la compra</button>
<%--    </form>--%>
    <% } %>

    <button type="button" class="btn btn-danger" onclick="window.location.href='./index.jsp'">Volver al Inicio</button>
</div>


<!-- Incluir Bootstrap JS (opcional para interactividad como modales, tooltips, etc.) -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js" crossorigin="anonymous"></script>

</body>
</html>
