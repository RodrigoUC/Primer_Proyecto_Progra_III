package pos.logic;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlID;
import jakarta.xml.bind.annotation.XmlIDREF;

import java.lang.reflect.Field;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Linea {
    @XmlID
    private String codigo;
    @XmlIDREF
    private Producto producto;
    private int cantidad;
    private double descuento; //2%, no 0.02. Si lo van a modificar es en getTotalLinea()
    private static int id=0;

    public Linea(Producto producto, int cantidad, double descuento) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.descuento = descuento;
        codigo="LIN"+id++;      //Creo que seria asi para que todas las lineas sean diferentes,aunque el problema es cuando se creen los objetos automaticos
    }
    public Linea() {
    }
    public Linea (String codigo){
        this.codigo = codigo;   //Por ejemplo si se ocupa crear una linea a partir de otra para que no modifique "id"
    }

    public void setProducto(Producto producto) {this.producto = producto;}
    public void setCantidad(int cantidad) {this.cantidad = cantidad;}
    public void setDescuento(double descuento) {this.descuento = descuento;}

    public Categoria getCategoria() {return producto.getCategoria();}

    public Producto getProducto (){return producto;}
    public Double getDescuento(){ return descuento; }
    public int getCantidad(){return cantidad; }
    public String getCodigo(){return codigo;}

    public double getTotalLinea(){
        return (producto.getPrecio()*cantidad)*(descuento/100);
    }

}
