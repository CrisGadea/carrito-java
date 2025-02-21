
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String nombreUsuario = (String) session.getAttribute("username");
    var saldoCuenta =  session.getAttribute("saldo");
    var idUsuario = session.getAttribute("idUsuario");
    var idCuenta = session.getAttribute("idCuenta");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <meta charset="UTF-8">
    <title>Mi Cuenta</title>
    <link rel="stylesheet" href="styles.css"> <!-- Agrega tu CSS -->
</head>
<body>
<div class="container">
    <h1>Bienvenido, <%= nombreUsuario %></h1>
    <p>Saldo disponible: <strong>$<%= saldoCuenta %></strong></p>

    <!-- Botón para abrir el modal de ingreso de dinero -->
    <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#ingresarDineroModal">
        Ingresar Dinero
    </button>

    <!-- Botón para abrir el modal de transferencia -->
    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#transferirDineroModal">
        Transferir
    </button>

    <!-- Botón para abrir el modal de extracción de dinero -->
    <button type="button" class="btn btn-info" data-bs-toggle="modal" data-bs-target="#extraerDineroModal">
        Extraer
    </button>
    <button type="button" class="btn btn-danger" onclick="window.location.href='../../index.jsp'">Volver al Inicio</button>
</div>

<!-- Modal de ingreso de dinero -->
<div class="modal fade" id="ingresarDineroModal" tabindex="-1" aria-labelledby="ingresarDineroLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="ingresarDineroLabel">Ingresar Dinero</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="../../cuentas?method=ingresar" method="POST">
                    <div class="mb-3">
                        <label for="monto" class="form-label">Monto a ingresar:</label>
                        <input type="number" class="form-control" id="monto" name="saldo" min="1" step="0.01" required>
                        <input type="hidden" name="usuarioId" value="<%= idUsuario %>">
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                        <button type="submit" class="btn btn-success">Ingresar</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- Modal de transferencia de dinero -->
<div class="modal fade" id="transferirDineroModal" tabindex="-1" aria-labelledby="transferirDineroLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="transferirDineroLabel">Transferir Dinero</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="../../transferir" method="POST">
                    <div class="mb-3">
                        <label for="montoTransferencia" class="form-label">Monto a transferir:</label>
                        <input type="number" class="form-control" id="montoTransferencia" name="monto" min="1" max="<%= saldoCuenta %>" step="0.01" required>
                    </div>
                    <input type="hidden" name="usuarioId" value="<%= idUsuario %>">
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                        <button type="submit" class="btn btn-primary">Transferir</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- Modal de extracción de dinero -->
<div class="modal fade" id="extraerDineroModal" tabindex="-1" aria-labelledby="extraerDineroLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="extraerDineroLabel">Extraer Dinero</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="../../cuentas?method=extraer" method="POST">
                    <div class="mb-3">
                        <label for="montoExtraer" class="form-label">Monto a extraer:</label>
                        <input type="number" class="form-control" id="montoExtraer" name="monto" min="1" max="<%= saldoCuenta %>" step="0.01" required>
                    </div>

                    <input type="hidden" name="usuarioId" value="<%= idUsuario %>">
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                        <button type="submit" class="btn btn-primary">Extraer</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

</body>


</body>
</html>
