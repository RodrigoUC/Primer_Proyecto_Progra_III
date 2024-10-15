package pos.logic;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlID;
import jakarta.xml.bind.annotation.XmlIDREF;

public class Linea {
    private String codigo;
    private Producto producto;
    private Factura factura;
    private int cantidad;
    private float descuento; //2%, no 0.02. Si lo van a modificar es en getTotalLinea()

    public Linea(Producto producto, int cantidad, float descuento) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.descuento = descuento;
        this.factura = null;
        codigo="LIN-";      //Creo que seria asi para que todas las lineas sean diferentes,aunque el problema es cuando se creen los objetos automaticos
    }
    public Linea() {
        producto=null;
        factura=null;
        cantidad=0;
        descuento=0;
        codigo="LIN-";
    }

    public float getTotal(){ return producto.getPrecio() + (producto.getPrecio() * descuento); }

    public void setId(String str){ codigo += str; }
    public void setProducto(Producto producto) {this.producto = producto;}
    public void setFactura(Factura factura) {this.factura = factura;}
    public void setCantidad(int cantidad) {this.cantidad = cantidad;}
    public void setDescuento(float descuento) {this.descuento = descuento;}

    public Categoria getCategoria() {return producto.getCategoria();}
    public Factura getFactura() {return factura;}
    public Producto getProducto (){return producto;}
    public float getDescuento(){ return descuento; }
    public int getCantidad(){return cantidad; }
    public String getCodigo(){return codigo;}

    public float getTotalLinea(){
        return (producto.getPrecio()*cantidad)-((descuento/100)*producto.getPrecio()*cantidad);
    }

    public String toString(){
        return codigo+" "+producto.getPrecio()+" "+cantidad+" "+descuento;
    }

}
