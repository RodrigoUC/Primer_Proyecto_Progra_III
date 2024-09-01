package pos.logic;

import java.util.Objects;

public class Linea {
    private Producto producto;
    private int cantidad;
    private double descuento;

    public double getDescuento() {
        return descuento;
    }
    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public Linea(Producto producto, int cantidad, double descuento) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.descuento = descuento/100;
    }
    public Linea(){
        this.producto = new Producto();
        this.cantidad = 0;
    }
    public Producto getProducto() {
        return producto;
    }
    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        else return false;
    }

}
