package com.edu.unlz.tienda.daos;

import com.edu.unlz.tienda.fabricas.Conexion;
import com.edu.unlz.tienda.modelos.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        public Producto getById(int id) throws SQLException {
            // TODO Auto-generated method stub
            Connection conn = Conexion.getConexion();
            String query = "select * from productos where id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            Producto producto=null;
            ps.setString(1, String.valueOf(id));
            var resultSet=ps.executeQuery();
            if (resultSet.next()){
                producto=new Producto();
                producto.setId(resultSet.getInt("id"));
                producto.setNombre(resultSet.getString("nombre"));
                producto.setDescripcion(resultSet.getString("descripcion"));
                producto.setPrecio(resultSet.getDouble("precio"));
                producto.setStock(resultSet.getInt("stock"));
            }

            return producto;

        }

        @Override
        public List<Producto> getAll() throws SQLException {
            // TODO Auto-generated method stub
            List<Producto>productos=new ArrayList<>();
            Connection conn = Conexion.getConexion();

            String query = "select * from productos";

            PreparedStatement ps = conn.prepareStatement(query);


            ResultSet resultSet=ps.executeQuery();
            while (resultSet.next()){
                Producto producto=new Producto();
                producto.setId(resultSet.getInt("id"));
                producto.setNombre(resultSet.getString("nombre"));
                producto.setDescripcion(resultSet.getString("descripcion"));
                producto.setPrecio(resultSet.getDouble("precio"));
                producto.setStock(resultSet.getInt("stock"));
                productos.add(producto);
            }

            conn.close();
            return productos;
        }

}
