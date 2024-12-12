package com.edu.unlz.tienda.controladores;

import com.edu.unlz.tienda.daos.OrdenDAO;
import com.edu.unlz.tienda.daos.OrdenProductoDAO;
import com.edu.unlz.tienda.modelos.Orden;
import com.edu.unlz.tienda.modelos.OrdenProducto;
import com.edu.unlz.tienda.modelos.ProductoCarrito;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/comprar")
public class CompraServlet extends HttpServlet {

    private OrdenDAO ordenDAO;
    private OrdenProductoDAO ordenProductoDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        this.ordenDAO = new OrdenDAO();
        this.ordenProductoDAO = new OrdenProductoDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Obtener el carrito de la sesión (lista de productos)
        List<ProductoCarrito> carrito = (ArrayList<ProductoCarrito>) session.getAttribute("productosCarrito");
        if (carrito == null || carrito.isEmpty()) {
            // Si el carrito está vacío, redirigir al usuario con un mensaje de error
            request.setAttribute("error", "El carrito está vacío. Agrega productos antes de comprar.");
            request.getRequestDispatcher("carrito.jsp").forward(request, response);
            return;
        }

        // Crear una nueva orden
        Orden nuevaOrden = new Orden();
        nuevaOrden.setFecha(java.time.LocalDate.now().toString()); // Fecha actual
        nuevaOrden.setTotal(calcularTotal(carrito)); // Calcular total del carrito
        nuevaOrden.setIdUsuario((int) session.getAttribute("idUsuario")); // Obtener ID del usuario desde la sesión
        System.out.println("usuario es: " + session.getAttribute("idUsuario"));
        try {
            // Insertar la orden en la base de datos
            ordenDAO.insert(nuevaOrden);

            // Vincular los productos del carrito con la orden
            carrito.stream().forEach(producto -> {
                try {
                    OrdenProducto ordenProducto = new OrdenProducto();
                    ordenProducto.setIdOrden(nuevaOrden.getId());
                    ordenProducto.setIdProducto(producto.getIdProducto());
                    ordenProducto.setCantidad(producto.getCantidad());
                    ordenProducto.setPrecio(producto.getPrecio());
                    ordenProductoDAO.insert(ordenProducto);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });

            // Vaciar el carrito después de completar la compra
            session.removeAttribute("productosCarrito");

            // Redirigir al usuario a la página de confirmación
            request.setAttribute("mensaje", "Compra realizada con éxito. Número de Orden : " + nuevaOrden.getId());
            session.setAttribute("totalCantidad", 0);
            request.getRequestDispatcher("vistas/compra/confirmacion.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            // En caso de error, redirigir con un mensaje de error
            request.setAttribute("error", "Hubo un problema al procesar la compra. Inténtalo de nuevo.");
            request.getRequestDispatcher("vistas/carrito/carrito.jsp").forward(request, response);
        }
    }

    // Método para calcular el total de la orden
    private double calcularTotal(List<ProductoCarrito> carrito) {
        return carrito.stream()
                .mapToDouble(producto -> producto.getPrecio() * producto.getCantidad())
                .sum();
    }
}
