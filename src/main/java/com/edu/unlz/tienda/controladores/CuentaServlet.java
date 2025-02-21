package com.edu.unlz.tienda.controladores;

import com.edu.unlz.tienda.daos.CuentaDAO;
import com.edu.unlz.tienda.modelos.Cuenta;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Random;


@WebServlet({"/cuentas"})
public class CuentaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CuentaDAO cdao;

    public CuentaServlet() {
        this.cdao = new CuentaDAO();
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var session = req.getSession();
        Cuenta cuenta = null;
        try {
            cuenta = cdao.getByUserId(Integer.parseInt(req.getParameter("usuarioId")));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            if(req.getParameter("method").equals("extraer")){
                var saldo = Double.parseDouble(req.getParameter("monto"));
                Double saldoActual = Double.parseDouble(String.valueOf(session.getAttribute("saldo")));
                if (cuenta != null) {
                    System.out.println(saldoActual + " " + saldo);
                    cuenta.setSaldo(saldoActual-saldo);
                    cdao.update(cuenta);
                    session.setAttribute("saldo", cuenta.getSaldo());
                }

                resp.sendRedirect("vistas/usuario/cuenta.jsp");
                return;
            }

            var saldo = Double.parseDouble(req.getParameter("saldo"));
            Double saldoActual = Double.parseDouble(String.valueOf(session.getAttribute("saldo")));
            if (cuenta != null) {
                System.out.println(saldoActual + " " + saldo);
                cuenta.setSaldo(saldoActual+saldo);
                cdao.update(cuenta);
                session.setAttribute("saldo", cuenta.getSaldo());
            }

            resp.sendRedirect("vistas/usuario/cuenta.jsp");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }




    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var session = req.getSession();

        System.out.println("pasa por get cuentas");

        // Solo cargar cuentas si no están en la sesión

        try {
            var cuenta= cdao.getById((int) session.getAttribute("idUsuario"));
            session.setAttribute("cuenta", cuenta);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Redirigir o reenviar al index.jsp
        var rd = req.getRequestDispatcher("index.jsp");
        rd.forward(req, resp);
    }
}
