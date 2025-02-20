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

public class RegistroServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UsuarioDAO udao;

    public RegistroServlet() {
        this.udao = Dao.getUsuariosDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean activo = request.getParameter("activo").equals("Si");
        String email = request.getParameter("email");
        Long idRol= Long.valueOf(request.getParameter("rolId"));

//        try {
//            if (udao.existsUsername(username)) {
//                response.sendError(500, "Ya existe el usuario");
//                return;
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

        Usuario usuario = new Usuario(username, password,activo, email,idRol);

        try {
            udao.insert(usuario);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect("/tp_final_war_exploded/vistas/usuario/login.jsp");
    }

}
