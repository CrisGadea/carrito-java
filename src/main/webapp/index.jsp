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
        <a class="navbar-brand" href="#">Tienda Scaloni</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                <%Long rolId = (Long) session.getAttribute("rolId");
                    var nombreUsuario = session.getAttribute("username");
                    var usuarioId = session.getAttribute("idUsuario");
                %>
                <% if (usuarioId == null) { %>
                <li class="nav-item"><a class="nav-link" href="vistas/usuario/registro.jsp">Registrarme</a></li>
                <li class="nav-item"><a class="nav-link" href="vistas/usuario/login.jsp">Loguearme</a></li>
                <% } else { %>
                <li class="nav-item"><a class="nav-link" href="logout">Cerrar Sesión</a></li>
                <% if (rolId != 2L) { %>
                <li class="nav-item"><a class="nav-link btn btn-primary" href="vistas/producto/crearProducto.jsp">Crear Producto</a></li>
                <% } %>
                <% } %>
            </ul>
            <div class="d-flex align-items-center">
                <% if (usuarioId != null && rolId == 2L) { %>
                <a href="vistas/usuario/cuenta.jsp" class="nav-link me-3 fw-bold text-primary">
                    <i class="bi bi-person-circle"></i> <%= nombreUsuario %>
                </a>
                <% } %>
                <form class="d-flex" action="<%= request.getContextPath() + "/carrito?method=show" %>" method="GET">
                    <button class="btn btn-outline-dark" type="submit">
                        <i class="bi bi-cart-fill me-1"></i>
                        Carrito
                        <span class="badge bg-dark text-white ms-1 rounded-pill">
                            <%= session.getAttribute("totalCantidad") != null ? session.getAttribute("totalCantidad") : 0 %>
                        </span>
                    </button>
                </form>
            </div>
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
            <% List<Producto> productos = (ArrayList<Producto>) session.getAttribute("productos"); %>
            <% if (productos != null && !productos.isEmpty()) { %>
            <% for (Producto producto : productos) { %>
            <div class="col mb-5">
                <div class="card h-100">
                    <img src="<%=producto.getUrlImg() %>" class="card-img-top img-fluid" style="height: 250px; object-fit: cover;">
                    <div class="card-body p-4">
                        <div class="text-center">
                            <h5 class="fw-bolder"><%= producto.getNombre() %></h5>
                            $<%= producto.getPrecio() %>
                        </div>
                    </div>
                    <% if (rolId != null && rolId != 2L) { %>
                    <div class="card-footer text-center bg-transparent">
                        <button class="btn btn-info ver-estadisticas"
                                data-product-id="<%= producto.getId() %>"
                                data-bs-toggle="modal"
                                data-bs-target="#verProductoModal">
                            Ver
                        </button>

                        <% if (rolId == 1L) { %>
                        <a href="<%= request.getContextPath() %>/vistas/producto/editarProducto.jsp" class="btn btn-warning">Editar</a>

                        <form action="<%= request.getContextPath() %>/productos?method=delete&id=<%= producto.getId() %>" method="POST" class="d-inline">
                            <button type="submit" class="btn btn-danger" onclick="return confirm('¿Estás seguro de eliminar este producto?');">Eliminar</button>
                        </form>
                        <% } %>
                    </div>
                    <% } %>

                    <% if (session.getAttribute("idUsuario") != null && rolId == 2L) { %>
                    <div class="card-footer text-center bg-transparent">
                        <form action="<%= request.getContextPath() + "/carrito?productId=" + producto.getId() %>" method="POST">
                            <button class="btn btn-outline-dark mt-auto" type="submit">Agregar a carrito</button>
                        </form>
                    </div>
                    <% } %>
                </div>
            </div>
            <% } %>
            <% } else { %>
            <div class="col mb-5 text-center">
                <p>No hay productos disponibles.</p>
            </div>
            <% } %>
        </div>
    </div>
</section>
<!-- Modal -->
<div class="modal fade" id="verProductoModal" tabindex="-1" aria-labelledby="verProductoModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="verProductoModalLabel">Detalles del Producto</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <p><strong>Cantidad Vendida:</strong> <span id="cantidadVendida">-</span></p>
                <p><strong>Total Recaudado:</strong> <span id="totalRecaudado">-</span></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
            </div>
        </div>
    </div>
</div>

<!-- Footer-->
<footer class="py-5 bg-dark">
    <div class="container">
        <p class="m-0 text-center text-white">Copyright &copy; Matias Quijano 2024</p>
    </div>
</footer>
<!-- Core theme JS-->
<script src="js/scripts.js"></script>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        document.querySelectorAll(".ver-estadisticas").forEach(button => {
            button.addEventListener("click", function () {
                const productId = this.getAttribute("data-product-id");
                const modalElement = document.getElementById("verProductoModal");
                const modal = new bootstrap.Modal(modalElement);

                fetch("<%= request.getContextPath() %>/informes?id=" + productId)
                    .then(response => response.json())
                    .then(data => {
                        document.getElementById("cantidadVendida").textContent = data.totalCantidad;
                        document.getElementById("totalRecaudado").textContent = "$" + data.totalRecaudado.toFixed(2);
                        modal.show();
                    })
                    .catch(error => {
                        console.error("Error al obtener estadísticas:", error);
                        alert("No se pudieron cargar los datos del producto.");
                    });

                // Asegurar que al cerrar el modal se eliminen restricciones de scroll
                modalElement.addEventListener("hidden.bs.modal", function () {
                    document.body.classList.remove("modal-open");
                    document.querySelectorAll(".modal-backdrop").forEach(el => el.remove());
                    document.body.style.overflow = "auto"; // Habilitar scroll
                });
            });
        });
    });
</script>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
