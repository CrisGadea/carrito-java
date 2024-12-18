<%@ page import="com.edu.unlz.tienda.modelos.Producto" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Tienda Seleccion - Scaloneta Page</title>
    <!-- Favicon-->
    <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
    <!-- Bootstrap icons-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="css/styles.css" rel="stylesheet" />
</head>
<body>
<!-- Navigation-->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container px-4 px-lg-5">
        <a class="navbar-brand" href="#!">Tienda Scaloni</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                <%--<li class="nav-item"><a class="nav-link active" aria-current="page" href="#!">Home</a></li>--%>
                <!-- if user is loged, show this tag -->
                <% if (session.getAttribute("idUsuario") == null) {%>
                <li class="nav-item"><a class="nav-link" href="vistas/usuario/registro.jsp">Registrarme</a></li>
                <li class="nav-item"><a class="nav-link" href="vistas/usuario/login.jsp">Loguearme</a></li>
                <%}%>
                <!-- if user is not loged, show this tag -->
                <% if (session.getAttribute("idUsuario") != null) {%>
                <li class="nav-item"><a class="nav-link" href="logout">Cerrar Sesion</a></li>
                <%}%>

            </ul>
            <form class="d-flex" action="<%= request.getContextPath() + "/carrito?method=show" %>" method="GET">
                <button class="btn btn-outline-dark" type="submit">
                    <i class="bi-cart-fill me-1"></i>
                    Carrito
                    <span class="badge bg-dark text-white ms-1 rounded-pill">
                        <!-- Obtener la cantidad de productos en el carrito -->
                        <%= session.getAttribute("totalCantidad") != null ? session.getAttribute("totalCantidad") : 0 %>
                    </span>
                </button>
            </form>
        </div>
    </div>
</nav>
<!-- Header-->
<header class="bg-dark py-5">
    <div class="container px-4 px-lg-5 my-5">
        <div class="text-center text-white">
            <h1 class="display-4 fw-bolder">Tienda Scaloni</h1>
            <p class="lead fw-normal text-white-50 mb-0">Compra la ropa oficial de la Scaloneta al mejor precio!</p>
        </div>
    </div>
</header>
<!-- Section-->
<section class="py-5">
    <div class="container px-4 px-lg-5 mt-5">
        <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
            <%
                // Obtener la lista de productos desde la sesión
                List<Producto> productos = (ArrayList<Producto>) session.getAttribute("productos");

                // Verificar si hay productos en la lista
                if (productos != null && !productos.isEmpty()) {
                    for (Producto producto : productos) {
            %>
            <div class="col mb-5">
                <div class="card h-100">
                    <!-- Imagen del producto -->
                    <img src="https://www.tienda-albiceleste.com/cdn/shop/products/CAMISETA-ARGENTINA-MESSI-3-ESTRELLAS-ADULTOS-2022-2023.jpg?v=1671492233">
                    <!-- Detalles del producto -->
                    <div class="card-body p-4">
                        <div class="text-center">
                            <!-- Nombre del producto -->
                            <h5 class="fw-bolder"><%= producto.getNombre() %></h5>

                            <!-- Precio del producto -->
                            $<%= producto.getPrecio() %>
                        </div>
                    </div>
                    <%if (session.getAttribute("idUsuario") != null) {%>
                    <!-- Acción para agregar al carrito -->
                    <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                        <div class="text-center">
                            <!-- Formulario con método POST -->
                            <form action="<%= request.getContextPath() + "/carrito?productId="+producto.getId() %>" method="POST">
                                <!-- Campo oculto para enviar el productId -->
                                <input type="hidden" name="productId" />
                                <button class="btn btn-outline-dark mt-auto" type="submit">
                                    Agregar a carrito

                                </button>
                            </form>
                        </div>
                    </div>
                    <%}%>

                </div>
            </div>
            <%
                }
            } else {
            %>
            <div class="col mb-5">
                <p>No hay productos disponibles.</p>
            </div>
            <%
                }
            %>
        </div>
    </div>
</section>
<!-- Footer-->
<footer class="py-5 bg-dark">
    <div class="container"><p class="m-0 text-center text-white">Copyright &copy; Matias Quijano 2024</p></div>
</footer>
<!-- Bootstrap core JS-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- Core theme JS-->
<script src="js/scripts.js"></script>

</body>
</html>
