package com.edu.unlz.tienda.controladores;

import com.edu.unlz.tienda.daos.CuentaDAO;
import com.edu.unlz.tienda.daos.UsuarioDAO;
import com.edu.unlz.tienda.fabricas.Dao;
import com.edu.unlz.tienda.modelos.Cuenta;
import com.edu.unlz.tienda.modelos.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;

public class RegistroServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UsuarioDAO udao;
    private CuentaDAO cdao;

    public RegistroServlet() {
        this.udao = Dao.getUsuariosDAO();
        this.cdao =new CuentaDAO();
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
           Usuario usuarioFound= udao.getByEmail(email);
           if (usuarioFound!=null){
               request.setAttribute("error","Ya existe el usuario");
              request.getRequestDispatcher("vistas/usuario/registro.jsp").forward(request, response);
               return;

           }
            udao.insert(usuario);
           Usuario usuarioInsertado=udao.getByEmail(email);
            Cuenta cuenta = new Cuenta();
            cuenta.setIdUsuario(usuarioInsertado.getId());
            var cbu=generarCBU();
            cuenta.setCbu(cbu);
            cdao.insert(cuenta);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect("/tp_final_war_exploded/vistas/usuario/login.jsp");
    }
    public static String generarCBU() {
        Random random = new Random();

        // Prefijo fijo (banco y sucursal ficticios)
        String prefijo = "12345678";

        // Generar un número de cuenta aleatorio de 13 dígitos
        String numeroCuenta = String.format("%013d", random.nextLong(9999999999999L));

        // Calcular un dígito de verificación simple (módulo 10)
        int digitoVerificador = calcularDigitoVerificador(prefijo + numeroCuenta);

        // Formar el CBU completo
        return prefijo + numeroCuenta + digitoVerificador;
    }

    private static int calcularDigitoVerificador(String cbuBase) {
        int suma = 0;
        for (char c : cbuBase.toCharArray()) {
            suma += Character.getNumericValue(c);
        }
        return suma % 10; // Usa módulo 10 como verificación simple
    }


}
