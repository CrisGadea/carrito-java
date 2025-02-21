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

        try {
            var cuenta = cdao.getByUserId(Integer.parseInt(req.getParameter("usuarioId")));
            var saldo = Double.parseDouble(req.getParameter("saldo"));
            Double saldoActual = Double.parseDouble(String.valueOf(session.getAttribute("saldo")));
            if (cuenta != null) {
                System.out.println(saldoActual + " " + saldo);
                cuenta.setSaldo(saldoActual+saldo);
                cdao.update(cuenta);
            } else {
                cuenta = new Cuenta();
                cuenta.setIdUsuario(Integer.parseInt(req.getParameter("usuarioId")));
                cuenta.setSaldo(saldo);
                var cbu=generarCBU();
                cuenta.setCbu(cbu);
                cdao.insert(cuenta);
                Cuenta cuentaCreada = cdao.getByCbu(cbu);
                session.setAttribute("idCuenta", cuentaCreada.getId());
            }

            session.setAttribute("saldo", cuenta.getSaldo());
            resp.sendRedirect("vistas/usuario/cuenta.jsp");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
