package pos.logic;

import java.io.Serializable;

public class Linea implements Serializable {
    private Integer codigo;  //Creo que seria int por el tema de la base de datos
    private Producto producto;
    private Factura factura;
    private int cantidad;
    private float descuento; //2%, no 0.02. Si lo van a modificar es en getTotalLinea()

    public Linea(Producto producto, int cantidad, float descuento) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.descuento = descuento;
        this.factura = null;
        codigo=0;      //Creo que seria asi para que todas las lineas sean diferentes,aunque el problema es cuando se creen los objetos automaticos
    }
    public Linea(Factura factura, Producto producto, int cantidad, float descuento) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.descuento = descuento;
        this.factura = factura;
        codigo=0;
    }
    public Linea(Factura factura, Producto producto, int cantidad, float descuento,Integer cod) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.descuento = descuento;
        this.factura = factura;
        this.codigo=cod;
    }

    public Linea(Factura factura) {
        this.producto = null;
        this.cantidad = 0;
        this.descuento = 0;
        this.factura = factura;
        codigo=0;      //Creo que seria asi para que todas las lineas sean diferentes,aunque el problema es cuando se creen los objetos automaticos
    }

    public Linea() {
        producto=null;
        factura=null;
        cantidad=0;
        descuento=0;
        codigo=0;
    }
    public void setId(Integer str){ this.codigo = str; }
    public void setProducto(Producto producto) {this.producto = producto;}
    public void setFactura(Factura factura) {this.factura = factura;}
    public void setCantidad(int cantidad) {this.cantidad = cantidad;}
    public void setDescuento(float descuento) {this.descuento = descuento;}

    public Categoria getCategoria() {return producto.getCategoria();}
    public Factura getFactura() {return factura;}
    public Producto getProducto (){return producto;}
    public float getDescuento(){ return descuento; }
    public int getCantidad(){return cantidad; }
    public Integer getCodigo(){return codigo;}

    public float getTotalLinea(){
        return (producto.getPrecio()*cantidad)-((descuento/100)*producto.getPrecio()*cantidad);
    }
    public float getTotalDescuento(){
        return (descuento/100)*producto.getPrecio()*cantidad;
    }
    public float getTotalSinDescuento(){
        return producto.getPrecio()*cantidad;
    }

    public String toString(){
        if(producto != null){return codigo+" "+producto.getPrecio()+" "+cantidad+" "+descuento;}
        else return ""+codigo;
    }

}
