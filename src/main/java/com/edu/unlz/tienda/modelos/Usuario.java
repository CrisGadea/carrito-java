package com.edu.unlz.tienda.modelos;

public class Usuario {
    private int id;
    private String username;
    private String email;
    private String password;
    private Long idRol;
    private boolean activo;

    public Usuario(String username, String password, boolean activo, String email,Long idRol) {
        super();
        this.username = username;
        this.password = password;
        this.activo = activo;
        this.email = email;
        this.idRol = idRol;
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

    public Long getIdRol() {
        return idRol;
    }
    public void setIdRol(Long idRol) {
        this.idRol = idRol;
    }

    public boolean validarClave(String clave) {
        return this.password.equals(clave);
    }
}
