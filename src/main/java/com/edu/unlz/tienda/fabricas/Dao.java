package com.edu.unlz.tienda.fabricas;

import com.edu.unlz.tienda.daos.UsuarioDAO;

public class Dao {
    public static UsuarioDAO getUsuariosDAO() {
        return new UsuarioDAO();
    }
}
