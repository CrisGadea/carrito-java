package com.edu.unlz.tienda.controladores;

import com.edu.unlz.tienda.daos.VentaDao;
import com.edu.unlz.tienda.modelos.Venta;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class VentaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private VentaDao ventaDao;

    public VentaServlet() {
        this.ventaDao = new VentaDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            System.out.println("pasa por get ventas");
            List<Venta> ventas = ventaDao.getAll();

            for (Venta venta : ventas) {
                System.out.println("venta: " + venta.getId());

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
