package com.edu.unlz.tienda.modelos;

public class Usuario {
    private int id;
    private String username;
    private String email;
    private String password;
    private String categoria;
    private boolean activo;

    public Usuario(String username, String password, String categoria, boolean activo, String email) {
        super();
        this.username = username;
        this.password = password;
        this.categoria = categoria;
        this.activo = activo;
        this.email = email;
    }

    public Usuario() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean validarClave(String clave) {
        return this.password.equals(clave);
    }
}
