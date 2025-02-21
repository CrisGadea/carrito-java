package com.edu.unlz.tienda.modelos;

import java.math.BigDecimal;

public class Cuenta {
    private int id;
    private Double saldo;
    private int idUsuario;
    private String cbu;

    public Cuenta(int id, Double saldo, int idUsuario) {
        this.id = id;
        this.saldo = saldo;
        this.idUsuario = idUsuario;
    }

    public Cuenta() {

    }


    public String getCbu() {
        return cbu;
    }
    public void setCbu(String cbu) {
        this.cbu = cbu;
    }
    public Double getSaldo() {
        return this.saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
