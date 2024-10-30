package com.edu.unlz.tienda.controladores;

import com.edu.unlz.tienda.daos.UsuarioDAO;
import com.edu.unlz.tienda.fabricas.Dao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class HomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UsuarioDAO udao;

    public HomeServlet() {
        this.udao = Dao.getUsuariosDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // sesion
        var session = request.getSession();
//        if (session.getAttribute("usuario") == null) {
//            response.sendRedirect("login");
//            return;
//        }
        if (session.getAttribute("categoria").equals(1)) {
            // redirigir a la vista de "vistas/home/empleado.jsp"
            var rd = request.getRequestDispatcher("/vistas/home/empleado.jsp");
            rd.forward(request, response);
        } else {
            // redirigir a la vista de "vistas/home/cliente.jsp"
            var rd = request.getRequestDispatcher("/vistas/home/cliente.jsp");
            rd.forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
