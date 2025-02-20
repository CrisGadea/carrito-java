package com.edu.unlz.tienda.modelos;

import java.math.BigDecimal;

public class Orden {
    private int id;
    private String fecha;
    private Double total;
    private String estado;
    private int idUsuario;

    public Orden() {
    }

    public Orden(int id, String fecha, Double total, String estado, int idUsuario) {
        this.id = id;
        this.fecha = fecha;
        this.total = total;
        this.estado = estado;
        this.idUsuario = idUsuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return "Orden{" +
                "id=" + id +
                ", fecha='" + fecha + '\'' +
                ", total=" + total +
                ", estado='" + estado + '\'' +
                ", idUsuario=" + idUsuario +
                '}';
    }
}
