package com.edu.unlz.tienda.modelos;

import java.math.BigDecimal;

public class Transferencia {
    private int id;
    private int idCuentaOrigen;
    private int idCuentaDestino;
    private Double monto;
    private String fecha;

    public Transferencia() {
    }

    public Transferencia(int id, int idCuentaOrigen, int idCuentaDestino, Double monto, String fecha) {
        this.id = id;
        this.idCuentaOrigen = idCuentaOrigen;
        this.idCuentaDestino = idCuentaDestino;
        this.monto = monto;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public int getIdCuentaOrigen() {
        return idCuentaOrigen;
    }

    public int getIdCuentaDestino() {
        return idCuentaDestino;
    }

    public Double getMonto() {
        return monto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdCuentaOrigen(int idCuentaOrigen) {
        this.idCuentaOrigen = idCuentaOrigen;
    }

    public void setIdCuentaDestino(int idCuentaDestino) {
        this.idCuentaDestino = idCuentaDestino;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
