package com.edu.unlz.tienda.modelos;

import java.util.ArrayList;

public class Carrito {

    private int id;
    private ArrayList<Producto> productos;


    public Carrito() {
        productos = new ArrayList<Producto>();
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public ArrayList<Producto> getProductos() {
        return productos;
    }
    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public ArrayList<Producto> getProductosCarrito() {
        ArrayList<Producto> lista = new ArrayList<Producto>();
        for(Producto lc : this.getProductos()) {
            lista.add(lc);
        }
        return lista;
    }

    @Override
    public String toString() {
        return "Carrito [id=" + id + ", productos=" + productos + "]";
    }

    public Double getTotal() {
        double total = 0;
        for(Producto lc : this.getProductos()) {
            total += lc.getPrecio()*lc.getStock();
        }
        return total;
    }
}
