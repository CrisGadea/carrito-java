package com.edu.unlz.tienda.daos;

import com.edu.unlz.tienda.modelos.Cuenta;
import com.edu.unlz.tienda.modelos.Producto;
import com.edu.unlz.tienda.modelos.Venta;
import com.edu.unlz.tienda.fabricas.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VentaDao implements DAO<Venta> {
    @Override
    public void insert(Venta venta) {
        // TODO Auto-generated method stub

    }

    @Override
    public void update(Venta modelo) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(int id) {
        // TODO Auto-generated method stub

    }

    public Venta getById(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Venta> getAll() throws SQLException {
        /*Connection conn = Conexion.getConexion();
        String query = "SELECT o.id AS id, o.fecha_creacion as fecha,op.precio, o.usuario_id as usuarioId, u.email, op.cantidad,op.id_producto as productoId, p.nombre FROM " +
                "orden join usuarios u on (o.usuario_id = u.id) join orden_producto op on orden.id = op.id_orden join on productos p on (op.id_producto = p.id)";
        PreparedStatement ps = conn.prepareStatement(query);
        Venta cuenta = null;
        Map<Integer, Venta> ventaMap = new HashMap<>();
        var resultSet = ps.executeQuery();
        while (resultSet.next()) {
            int ventaId = resultSet.getInt("id");

            // Si la venta no está en el mapa, la creamos
            Venta venta = ventaMap.get(ventaId);
            if (venta == null) {
                venta.setId(resultSet.getInt("id"));
                venta.setIdUsuario(resultSet.getInt("usuarioId"));
                venta.setEmailUsuario(resultSet.getString("email"));
            }

            // Creamos el producto y lo agregamos a la venta
            Producto producto = new Producto();
            producto.setId(resultSet.getInt("productoId"));
            producto.setNombre(resultSet.getString("nombre"));
            producto.setPrecio(resultSet.getDouble("precio"));
            producto.setStock(resultSet.getInt("cantidad"));

            venta.getProductos().add(producto);
        }
        while (resultSet.next()){
            Venta venta=new Venta();
            venta.setId(resultSet.getInt("id"));
            venta.setIdUsuario(resultSet.getInt("usuarioId"));
            venta.setCantidad(resultSet.getInt("cantidad"));
            venta.setPrecio(resultSet.getDouble("precio"));
            venta.setEmailUsuario(resultSet.getString("email"));

            ventas.add(venta);
        }
        return ventas;

*/
        return new ArrayList<>();
    }

    public Venta getByUsuario(int idUsuario) {
        // TODO Auto-generated method stub
        return null;
    }
}
