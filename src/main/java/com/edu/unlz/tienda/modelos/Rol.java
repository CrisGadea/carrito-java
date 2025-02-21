package com.edu.unlz.tienda.modelos;

public class Rol {
    private int id;
    private String descripcion;

    public Rol() {
    }

    public Rol(String descripcion) {
        this.descripcion = descripcion;
    }

    public Rol(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return descripcion;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String descripcion) {
        this.descripcion = descripcion;
    }
}
