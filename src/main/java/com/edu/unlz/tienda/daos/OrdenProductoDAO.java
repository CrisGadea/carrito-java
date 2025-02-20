package com.edu.unlz.tienda.daos;

import com.edu.unlz.tienda.fabricas.Conexion;
import com.edu.unlz.tienda.modelos.OrdenProducto;
import com.edu.unlz.tienda.modelos.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrdenProductoDAO implements DAO<OrdenProducto> {
    @Override
    public void insert(OrdenProducto ordenProducto) throws SQLException {
        Connection conn = Conexion.getConexion();

        String query = "INSERT INTO Orden_Producto (id_orden, id_producto, cantidad,precio)";
        query += " VALUES (?, ?, ?,?)";

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, ordenProducto.getIdOrden()); // ID de la orden
        ps.setInt(2, ordenProducto.getIdProducto()); // ID del producto
        ps.setInt(3, ordenProducto.getCantidad()); // Cantidad comprada
        ps.setDouble(4, ordenProducto.getPrecio()); // Precio del producto
        ps.executeUpdate();
    }


    @Override
    public void update(OrdenProducto modelo) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }

    @Override
    public OrdenProducto getById(int id) throws SQLException {
        return null;
    }

    @Override
    public List<OrdenProducto> getAll() throws SQLException {
        Connection conn = Conexion.getConexion();
        List<OrdenProducto> ordenProductos = new ArrayList<>();
        String query = "SELECT * FROM Orden_Producto";
        PreparedStatement ps = conn.prepareStatement(query);
       var rs= ps.executeQuery();
        while (rs.next()){
            OrdenProducto ordenProducto=new OrdenProducto();
            ordenProducto.setIdProducto(rs.getInt("id_producto"));
            ordenProducto.setIdOrden(rs.getInt("id_orden"));
            ordenProducto.setCantidad(rs.getInt("cantidad"));
            ordenProducto.setPrecio(rs.getDouble("precio"));
            ordenProductos.add(ordenProducto);
        }

        conn.close();
        return ordenProductos;
    }



}
