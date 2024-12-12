package com.edu.unlz.tienda.controladores;

import com.edu.unlz.tienda.daos.UsuarioDAO;
import com.edu.unlz.tienda.fabricas.Dao;
import com.edu.unlz.tienda.modelos.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

public class UsuarioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UsuarioDAO udao;

    public UsuarioServlet() {
        this.udao = Dao.getUsuariosDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        var session = request.getSession();
//
//        Usuario usuario = (Usuario) session.getAttribute("usuario");
//

//
//        var accion = request.getParameter("accion");
//        accion = Optional.ofNullable(accion).orElse("login");
//
//        switch (accion) {
//            case "login" -> getLogin(request, response);
//            case "register" -> getRegister(request, response);
//
//            default -> response.sendError(404, "No se encontro la accion: " + accion);
//        }

    }

//    private void getRegister(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        var categorias = Stream.of("cliente", "empleado").toList();
//
//        request.setAttribute("categorias", categorias);
//
//        var rd = request.getRequestDispatcher("/vistas/usuario/register.jsp");
//        rd.forward(request, response);
//    }
//
//    private void getLogin(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        var rd = request.getRequestDispatcher("/vistas/usuario/login.jsp");
//        rd.forward(request, response);
//
//    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        var session = request.getSession();
        String email = request.getParameter("email");
        String password = request.getParameter("password");


        try {
            Usuario usuario = udao.getByEmail(email);
            System.out.println("usuario es: " + usuario);
            // Si el usuario existe y la clave es correcta se guarda en la sesion los datos requeridos
            session.setAttribute("username", usuario.getUsername());
            session.setAttribute("categoria", usuario.getCategoria());
            session.setAttribute("email", usuario.getEmail());
            session.setAttribute("activo", usuario.isActivo());
            session.setAttribute("idUsuario", usuario.getId());
            System.out.println("usuario es: " + session.getAttribute("idUsuario"));
            if (!usuario.validarClave(password)) {
                response.sendError(500, "Credenciales invalidas");
                return;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect(request.getContextPath() + "/productos");
    }

}
