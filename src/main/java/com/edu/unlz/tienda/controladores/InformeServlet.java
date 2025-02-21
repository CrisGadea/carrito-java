package com.edu.unlz.tienda.controladores;

import com.edu.unlz.tienda.daos.OrdenDAO;
import com.edu.unlz.tienda.daos.OrdenProductoDAO;
import com.edu.unlz.tienda.daos.ProductoDAO;
import com.edu.unlz.tienda.modelos.OrdenProducto;
import com.edu.unlz.tienda.modelos.Producto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@WebServlet("/informes")
public class InformeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ProductoDAO productoDAO;
    private OrdenProductoDAO ordenProductoDAO;


    public InformeServlet() {
        this.productoDAO = new ProductoDAO();
        this.ordenProductoDAO = new OrdenProductoDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)  {
        try {
            System.out.println("pasa por get informe");
            Producto producto = productoDAO.getById(Integer.parseInt(req.getParameter("id")));
            if (producto == null) {
                resp.sendRedirect("index.jsp");
                return;
            }
            Integer totalCantidad=0;
            Double totalRecaudado=0.0;
            List<OrdenProducto> ordenProductos = ordenProductoDAO.getAll();

            for (OrdenProducto ordenProducto : ordenProductos) {
                System.out.println("ordenProducto: " + ordenProducto.getIdProducto());
                if (ordenProducto.getIdProducto()==producto.getId()) {
                    totalCantidad += ordenProducto.getCantidad();
                    totalRecaudado += ordenProducto.getCantidad() * producto.getPrecio();
                }
            }
            System.out.println("total cantidad: " + totalCantidad);
            System.out.println("total recaudado: " + totalRecaudado);
            String jsonResponse = String.format("{\"totalCantidad\": %d, \"totalRecaudado\": %.2f}", totalCantidad, totalRecaudado);
            resp.getWriter().write(jsonResponse);


            } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
