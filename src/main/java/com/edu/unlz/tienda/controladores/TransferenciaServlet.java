package com.edu.unlz.tienda.controladores;

import com.edu.unlz.tienda.daos.CuentaDAO;
import com.edu.unlz.tienda.daos.TransferenciaDAO;
import com.edu.unlz.tienda.modelos.Cuenta;
import com.edu.unlz.tienda.modelos.Transferencia;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/transferir")
public class TransferenciaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private TransferenciaDAO transferenciaDAO;
    private CuentaDAO cuentaDAO;


    public TransferenciaServlet() {
        this.transferenciaDAO = new TransferenciaDAO();
        this.cuentaDAO = new CuentaDAO();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var session = req.getSession();

        try {
            System.out.println(req.getParameter("cbuDestino"));
             Cuenta cuentaDestino=cuentaDAO.getByCbu(req.getParameter("cbuDestino"));
             if (cuentaDestino==null){
                 req.setAttribute("error", "La cuenta destino no existe");
                 req.getRequestDispatcher("transferencia.jsp").forward(req, resp);
                 return;
             }
             Cuenta cuentaOrigen=cuentaDAO.getById(Integer.parseInt(req.getParameter("idCuenta")));
             System.out.println("id cuenta origen: "+req.getParameter("idCuenta"));
            Transferencia transferencia =new Transferencia();
             transferencia.setMonto(Double.parseDouble(req.getParameter("monto")));
             transferencia.setIdCuentaOrigen(cuentaOrigen.getId());
             transferencia.setIdCuentaDestino(cuentaDestino.getId());
             System.out.println("trans: "+transferencia.getIdCuentaDestino() + transferencia.getIdCuentaOrigen());
             this.transferenciaDAO.insert(transferencia);
             cuentaOrigen.setSaldo(cuentaOrigen.getSaldo()-transferencia.getMonto());
             cuentaDestino.setSaldo(cuentaDestino.getSaldo()+transferencia.getMonto());
             cuentaDAO.update(cuentaOrigen);
             cuentaDAO.update(cuentaDestino);
             session.setAttribute("saldo", cuentaOrigen.getSaldo());
             resp.sendRedirect("vistas/usuario/cuenta.jsp");
        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }

}
