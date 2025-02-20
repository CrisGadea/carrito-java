package com.edu.unlz.tienda.daos;

import com.edu.unlz.tienda.fabricas.Conexion;
import com.edu.unlz.tienda.modelos.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UsuarioDAO implements DAO<Usuario> {

    @Override
    public void insert(Usuario usuario) throws SQLException {

        Connection conn = Conexion.getConexion();

        String query = "insert into usuarios";
        query += " (username, password, activo, email, rol_id)";
        query += " values (?,?,?,?,?)";

        PreparedStatement ps = conn.prepareStatement(query);

        ps.setString(1, usuario.getUsername());
        ps.setString(2, usuario.getPassword());
        ps.setInt(3, usuario.isActivo() ? 1 : 0);
        ps.setString(4, usuario.getEmail());
        ps.setLong(5, usuario.getIdRol());

        ps.executeUpdate();

        conn.close();

    }

    @Override
    public void update(Usuario modelo) throws SQLException {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(int id) throws SQLException {
        // TODO Auto-generated method stub

    }

    public Usuario getByEmail(String email) throws SQLException {

        Usuario usuario = null;

        Connection conn = Conexion.getConexion();


/*		String query = "select id, username, password, activo, categoria, nickname";
		query += " from usuarios";
		query += " where username = ?"; */

        String query = "select u.id,u.username, u.password, u.activo,u.rol_id, u.email";
        query += " from usuarios u";
        query += " where u.email = ?";

        PreparedStatement ps = conn.prepareStatement(query);

        ps.setString(1, email);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            usuario = new Usuario();
            usuario.setUsername(rs.getString("username"));
            usuario.setPassword(rs.getString("password"));
            usuario.setActivo(rs.getInt("activo") == 1);
            usuario.setIdRol(rs.getLong("rol_id"));
            usuario.setEmail(rs.getString("email"));
            usuario.setId(rs.getInt("id"));
        }
        System.out.println("usuario de bd es: " + usuario + " email: " + email);

        return usuario;

    }

    @Override
    public Usuario getById(int id) throws SQLException {

        Usuario usuario = null;

        Connection conn = Conexion.getConexion();

        String query = "select u.id, u.username, u.password, u.activo, u.categoria, u.email";
        query += " from usuarios";
        query += " where u.id = ?";

        PreparedStatement ps = conn.prepareStatement(query);

        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            usuario = new Usuario();
            usuario.setId(rs.getInt("id"));
            usuario.setUsername(rs.getString("username"));
            usuario.setPassword(rs.getString("password"));
            usuario.setActivo(rs.getInt("activo") == 1);
            usuario.setIdRol(rs.getLong("idRol"));
            usuario.setEmail(rs.getString("email"));
        }

        return usuario;

    }

    @Override
    public List<Usuario> getAll() throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    public boolean existsUsername(String email) throws SQLException {
        return this.getByEmail(email) != null;
    }
}
