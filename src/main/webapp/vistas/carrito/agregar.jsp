<%--
  Created by IntelliJ IDEA.
  User: cristiangadea
  Date: 18/07/2024
  Time: 7:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Carrito</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
    <script src="<%=request.getContextPath()%>/js/jquery-3.6.0.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/carrito.js"></script>
    <script>
        $(document).ready(function () {
            $("#btnAgregar").click(function () {
                var idProducto = $("#idProducto").val();
                var cantidad = $("#cantidad").val();
                $.ajax({
                    url: "<%=request.getContextPath()%>/carrito/agregar",
                    type: "POST",
                    data: {
                        idProducto: idProducto,
                        cantidad: cantidad
                    },
                    success: function (data) {
                        $("#mensaje").html(data);
                    }
                });
            });
        });
    </script>
</head>
<body>

</body>
</html>
