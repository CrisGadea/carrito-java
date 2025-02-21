package com.edu.unlz.tienda.daos;

import com.edu.unlz.tienda.fabricas.Conexion;
import com.edu.unlz.tienda.modelos.Transferencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class TransferenciaDAO implements DAO<Transferencia> {
    public TransferenciaDAO() {
        super();
    }

    @Override
    public void insert(Transferencia transferencia) throws SQLException {
        Connection conn = Conexion.getConexion();
        String query = "INSERT INTO transferencia";
        query += " (id_cuenta_origen, id_cuenta_destino, monto)";
        query += " VALUES(?,?,?);";

        PreparedStatement ps = ((Connection) conn).prepareStatement(query);

        ps.setString(1, String.valueOf(transferencia.getIdCuentaOrigen()));
        ps.setString(2, String.valueOf(transferencia.getIdCuentaDestino()));
        ps.setDouble(3, transferencia.getMonto());

        ps.executeUpdate();

        conn.close();
    }

    @Override
    public void update(Transferencia modelo) throws SQLException {

    }


    @Override
    public void delete(int id) throws SQLException {
        Connection conn = Conexion.getConexion();
        String query = "DELETE FROM transferencia";
        query += " WHERE id = ?;";

        PreparedStatement ps = conn.prepareStatement(query);

        ps.setInt(1, id);

        ps.executeUpdate();

        conn.close();
    }

    @Override
    public Transferencia getById(int id) throws SQLException {
        Connection conn = Conexion.getConexion();
        String query = "SELECT * FROM transferencia";
        query+=" WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        Transferencia transferencia = null;
        ps.setString(1, String.valueOf(id));
        ps.executeQuery();
        if (ps.getResultSet().next()){
            transferencia = new Transferencia();
            transferencia.setId(ps.getResultSet().getInt("id"));
            transferencia.setIdCuentaOrigen(ps.getResultSet().getInt("id_cuenta_origen"));
            transferencia.setIdCuentaDestino(ps.getResultSet().getInt("id_cuenta_destino"));
            transferencia.setMonto(ps.getResultSet().getDouble("monto"));
            transferencia.setFecha(ps.getResultSet().getString("fecha"));
        }
        return transferencia;

    }

    @Override
    public List<Transferencia> getAll() throws SQLException {
        return List.of();
    }
}
