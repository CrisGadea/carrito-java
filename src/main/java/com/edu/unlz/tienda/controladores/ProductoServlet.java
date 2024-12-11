package com.edu.unlz.tienda.controladores;

import com.edu.unlz.tienda.daos.ProductoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet({"/productos", ""})
public class ProductoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductoDAO pdao;

    @Override
    public void init() throws ServletException {
        super.init();
        this.pdao = new ProductoDAO(); // Inicializa pdao aquí
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        var session = request.getSession();


        // Solo cargar productos si no están en la sesión
        if (session.getAttribute("productos") == null) {
            try {
                var productos = pdao.getAll();
                session.setAttribute("productos", productos);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        // Redirigir o reenviar al index.jsp
        var rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);
    }


}
