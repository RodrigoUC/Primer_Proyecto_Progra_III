package pos.logic;

import java.util.Objects;

public class Linea {
    private Producto producto;
    private int cantidad;
    private double descuento;

    public Linea(Producto producto, int cantidad, double descuento) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.descuento = descuento;
    }
    public Linea() {
    }


    public Producto getProducto (){return producto;}
    public Double getDescuento(){ return descuento; }
    public int getCantidad(){return cantidad; }
}
