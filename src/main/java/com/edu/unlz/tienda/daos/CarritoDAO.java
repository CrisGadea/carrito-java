package com.edu.unlz.tienda.daos;

import com.edu.unlz.tienda.fabricas.Conexion;
import com.edu.unlz.tienda.modelos.Carrito;
import com.edu.unlz.tienda.modelos.Producto;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class CarritoDAO implements DAO<Carrito>{
    public CarritoDAO() {
        super();
    }

    @Override
    public void insert(Carrito carrito) throws SQLException {
        var con = Conexion.getConexion();

        String query = "INSERT INTO ordenes";
        query += " (id_usuario, ejecutada)";
        query += " VALUES(?,0);";

        var ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        //ps.setInt(1, carrito.getUsuario().getId());

        ps.executeUpdate();

        var generatedKeys = ps.getGeneratedKeys();
        generatedKeys.next();
        int id = generatedKeys.getInt(1);

        carrito.setId(id);

    }

    @Override
    public void update(Carrito modelo) throws SQLException {
        // TODO Auto-generated method stub

    }


    @Override
    public void delete(int id) throws SQLException {
        // TODO Auto-generated method stub
        //return null;
    }

    public void deleteProdCarrito(int id, int idOrden) throws SQLException {
        var con = Conexion.getConexion();

        //System.out.println("id: " + id + " idCarrito: " + idOrden);
        String query = "delete from ordenes_productos";
        query += " where id_producto = ? AND id_orden = ?";

        var ps = con.prepareStatement(query);

        ps.setInt(1, id);
        ps.setInt(2, idOrden);

        ps.executeUpdate();

        con.close();

    }

    public void actualizarCantidadProducto(Carrito carrito, Producto producto, int cantidad) throws SQLException {
        this.deleteProdCarrito(producto.getId(), carrito.getId());
        this.agregarProducto(carrito, producto, cantidad);
    }

    public static boolean ejecutarCarrito(int idCarrito) throws SQLException {
        var con = Conexion.getConexion();
        boolean resultado = true;
        String query = "UPDATE ordenes";
        query += " SET ejecutada = 1";
        query += " WHERE id_orden = ?;";

        var ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        ps.setInt(1, idCarrito);

        try {
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.toString());
            resultado = false;
        }
        return resultado;
    }

    @Override
    public Carrito getById(int id) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Carrito> getAll() throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    public void agregarProducto(Carrito carrito, Producto producto, int cantidad) throws SQLException {
        var con = Conexion.getConexion();

        String query = "INSERT INTO ordenes_productos";
        query += " (id_orden, id_producto, monto_producto, cantidad)";
        query += " VALUES(?,?,?,?);";

        var ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        ps.setInt(1, carrito.getId());
        ps.setInt(2, producto.getId());
        ps.setDouble(3, producto.getPrecio());
        ps.setInt(4, cantidad);

        System.out.println(query);
        ps.executeUpdate();
    }

//    public static Carrito getCarritoByUser(Usuario usuario) throws SQLException {
//        var con = Conexion.getConexion();
//        Carrito carrito = null;
//        String 	query = "CALL sp_getOrdenCarrito(?)";
//
//
//        var ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
//
//        ps.setInt(1, usuario.getId());
//
//        var rs = ps.executeQuery();
//        if(rs.next()) {
//            carrito = new Carrito();
//            carrito.setId(rs.getInt("idOrden"));
//        }
//        return carrito;
//
//
//    }

    public static Carrito getCarritoProductos(int orden) throws SQLException {
        var con = Conexion.getConexion();
        String 	query = "SELECT op.id_producto, op.monto_producto, op.cantidad, p.`codigo_producto`, p.`nombre_producto`, p.`descripcion_producto`, p.precio, p.`stock_qty`";
        query += " FROM ordenes_productos op";
        query += " LEFT JOIN productos p";
        query += " ON p.id = op.id_producto";
        query += " WHERE id_orden = ?";

        var ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        ps.setInt(1, orden);

        var rs = ps.executeQuery();

        Carrito carrito = new Carrito();
        Producto carritoProducto;
        carrito.setId(orden);
        System.out.println(query);
        int i = 0;
        while(rs.next()) {
            i++;
            System.out.println(i);
            carritoProducto = new Producto();
            carritoProducto.setId(rs.getInt("id"));
            carritoProducto.setNombre(rs.getString("nombre"));
            carritoProducto.setDescripcion(rs.getString("descripcion"));
            carritoProducto.setPrecio(rs.getInt("precio"));
            carritoProducto.setStock(rs.getInt("stock"));
            carrito.getProductos().add(carritoProducto);
        }


        return carrito;
    }
}
