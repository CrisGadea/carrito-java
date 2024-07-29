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
//        if (usuario != null) {
//            response.sendRedirect("home");
//            return;
//        }
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
//        try {
//            var accion = request.getParameter("accion");
//            accion = Optional.ofNullable(accion).orElse("");
//
//            switch (accion) {
//                case "login" -> postLogin(request, response);
//                case "register" -> postRegister(request, response);
//                default -> response.sendError(404, "No se encontro la accion: " + accion + "pero entro al controlador.");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            response.sendError(500, "Algo salio mal pero entro al controlador. Mensaje error: " + e.toString());
//        }
//
//        response.sendRedirect("login");
        var session = request.getSession();
        String email = request.getParameter("email");
        String password = request.getParameter("password");


        try {
            Usuario usuario = udao.getByEmail(email);
            // Si el usuario existe y la clave es correcta se guarda en la sesion los datos requeridos
            session.setAttribute("username", usuario.getUsername());
            session.setAttribute("categoria", usuario.getCategoria());
            session.setAttribute("email", usuario.getEmail());
            session.setAttribute("activo", usuario.isActivo());
            session.setAttribute("id", usuario.getId());

            if (!usuario.validarClave(password)) {
                response.sendError(500, "Credenciales invalidas");
                return;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect("index.jsp");
    }

//    private void postLogin(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
//        var session = request.getSession();
//
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//
//        var usuario = udao.getByEmail(username);
//
//        if (usuario == null) {
//            response.sendError(500, "No existe el usuario");
//            return;
//        } else if (!usuario.validarClave(password)) {
//            response.sendError(500, "Credenciales invalidas");
//            return;
//        }
//
//        session.setAttribute("usuario", usuario);
//        session.setAttribute("username", usuario.getUsername());
//        session.setAttribute("categoria", usuario.getCategoria());
//        session.setAttribute("email", usuario.getEmail());
//        session.setAttribute("activo", usuario.isActivo());
//
//        response.sendRedirect("index.jsp");
//
//    }
//
//    private void postRegister(HttpServletRequest request, HttpServletResponse response)
//            throws IOException, SQLException {
//
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        boolean activo = request.getParameter("activo").equals("S");
//        String categoria = request.getParameter("categoria");
//        String email = request.getParameter("email");
//
//        if (udao.existsUsername(username)) {
//            response.sendError(500, "Ya existe el usuario");
//            return;
//        }
//
//        Usuario usuario = new Usuario(username, password, categoria, activo, email);
//
//        udao.insert(usuario);
//
//        response.sendRedirect("login");
//
//    }
}
