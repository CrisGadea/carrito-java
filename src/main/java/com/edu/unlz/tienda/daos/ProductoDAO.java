package com.edu.unlz.tienda.daos;

import com.edu.unlz.tienda.fabricas.Conexion;
import com.edu.unlz.tienda.modelos.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO implements DAO<Producto> {

        @Override
        public void insert(Producto producto) throws SQLException {
            Connection conn = Conexion.getConexion();

            String query = "insert into productos";
            query += " (nombre, descripcion, precio, stock)";
            query += " values (?,?,?,?)";

            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1,producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setDouble(3, producto.getPrecio());
            ps.setInt(4, producto.getStock());

            ps.executeUpdate();

            conn.close();

        }

        @Override
        public void update(Producto modelo) throws SQLException {
            // TODO Auto-generated method stub

        }

        @Override
        public void delete(int id) {
            // TODO Auto-generated method stub

        }

        public Producto getByNombre(String nombre) throws SQLException {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public Producto getById(int id) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public List<Producto> getAll() throws SQLException {
            Connection connection = Conexion.getConexion();
            List<Producto> productos = new ArrayList<>();
            String query = "select * from productos";
            try (Statement stmt = connection.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    Producto producto = new Producto();
                    producto.setId(rs.getInt("id"));
                    producto.setNombre(rs.getString("nombre"));
                    producto.setPrecio(rs.getDouble("precio"));
                    producto.setDescripcion(rs.getString("descripcion"));
                    productos.add(producto);
                }
            }
            return productos;
        }

}
