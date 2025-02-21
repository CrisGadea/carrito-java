package com.edu.unlz.tienda.daos;

import com.edu.unlz.tienda.fabricas.Conexion;
import com.edu.unlz.tienda.modelos.Carrito;
import com.edu.unlz.tienda.modelos.Cuenta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class CuentaDAO implements DAO<Cuenta> {
    public CuentaDAO() {
        super();
    }


    @Override
    public void insert(Cuenta cuenta) throws SQLException {
        Connection conn = Conexion.getConexion();
        String query = "INSERT INTO cuenta";
        query += " (saldo, usuario_id, cbu)";
        query += " VALUES(0,?,?);";

        PreparedStatement ps = conn.prepareStatement(query);

        ps.setString(1, String.valueOf(cuenta.getIdUsuario()));
        ps.setString(2, cuenta.getCbu());

        ps.executeUpdate();

        conn.close();


    }

    @Override
    public void update(Cuenta cuenta) throws SQLException {
        Connection conn = Conexion.getConexion();
        String query = "UPDATE cuenta";
        query += " SET saldo = ?";
        query += " WHERE usuario_id = ?;";


        PreparedStatement ps = conn.prepareStatement(query);

        ps.setDouble(1, cuenta.getSaldo());
        ps.setInt(2, cuenta.getIdUsuario());

        ps.executeUpdate();

        conn.close();
    }

    @Override
    public void delete(int id) throws SQLException {
        Connection conn = Conexion.getConexion();
        String query = "DELETE FROM cuenta";
        query += " WHERE id_usuario = ?;";

        PreparedStatement ps = conn.prepareStatement(query);

        ps.setInt(1, id);

        ps.executeUpdate();

        conn.close();
    }

    @Override
    public Cuenta getById(int id) throws SQLException {
        Connection conn = Conexion.getConexion();
        String query = "SELECT * FROM cuenta";
        query += " WHERE id= ?;";
        PreparedStatement ps = conn.prepareStatement(query);
        Cuenta cuenta = null;
        ps.setInt(1, id);
        var resultSet = ps.executeQuery();
        if (resultSet.next()) {
            cuenta = new Cuenta();
            cuenta.setIdUsuario(resultSet.getInt("usuario_id"));
            cuenta.setSaldo(resultSet.getDouble("saldo"));
            cuenta.setCbu(resultSet.getString("cbu"));
            cuenta.setId(resultSet.getInt("id"));
        }
        return cuenta;
    }


    @Override
    public List<Cuenta> getAll() throws SQLException {
        return List.of();
    }

    public Cuenta getByUserId(int usuarioId) throws SQLException{
        Connection conn = Conexion.getConexion();
        String query = "SELECT * FROM cuenta WHERE usuario_id= ?;";
        PreparedStatement ps = conn.prepareStatement(query);
        Cuenta cuenta = null;
        ps.setInt(1, usuarioId);
        var resultSet = ps.executeQuery();
        if (resultSet.next()) {
            cuenta = new Cuenta();
            cuenta.setIdUsuario(resultSet.getInt("usuario_id"));
            cuenta.setSaldo(resultSet.getDouble("saldo"));
            cuenta.setCbu(resultSet.getString("cbu"));
            cuenta.setId(resultSet.getInt("id"));
        }
        return cuenta;

    }
    public  Cuenta getByCbu(String cbu) throws SQLException{
        Connection conn = Conexion.getConexion();
        String query = "SELECT * FROM cuenta WHERE cbu= ?;";
        PreparedStatement ps = conn.prepareStatement(query);
        Cuenta cuenta = null;
        ps.setString(1, cbu);
        var resultSet = ps.executeQuery();
        if (resultSet.next()) {
            cuenta = new Cuenta();
            cuenta.setIdUsuario(resultSet.getInt("usuario_id"));
            cuenta.setSaldo(resultSet.getDouble("saldo"));
            cuenta.setCbu(resultSet.getString("cbu"));
            cuenta.setId(resultSet.getInt("id"));
        }

        return cuenta;
    }
}
