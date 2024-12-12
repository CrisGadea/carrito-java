package com.edu.unlz.tienda.daos;

import com.edu.unlz.tienda.fabricas.Conexion;
import com.edu.unlz.tienda.modelos.Orden;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrdenDAO implements DAO<Orden> {
    @Override
    public void insert(Orden orden) throws SQLException {
        Connection conn = Conexion.getConexion();

        String query = "INSERT INTO Orden (fecha, total, Orden.usuario_id)";
        query += " VALUES (?, ?, ?)";

        PreparedStatement ps = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

        ps.setString(1, orden.getFecha());
        ps.setDouble(2, orden.getTotal());
        ps.setInt(3, orden.getIdUsuario());

        ps.executeUpdate();

        // Obtener el ID generado para la orden
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            orden.setId(rs.getInt(1)); // Asignar el ID generado a la orden
        }

        conn.close();
    }

    @Override
    public void update(Orden modelo) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }

    @Override
    public Orden getById(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Orden> getAll() throws SQLException {
        return List.of();
    }
}
