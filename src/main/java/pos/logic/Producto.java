package pos.logic;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlID;

import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Producto {
    @XmlID
    private String descripcion;
    private String codigo;
    private double precio;
    private Categoria categoria;
    private String unidad;

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Producto()  {
        this("","",0.0, new Categoria());
    }

    public Producto(String descripcion, String codigo, double precio, Categoria categoria) {
        this.descripcion = descripcion;
        this.codigo = codigo;
        this.precio = precio;
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public String getStringCategoria(){
        return categoria.getNombre();
    }


    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void setCategoria(String nombre, String id) {
        this.categoria = new Categoria(nombre, id);
    }

    public void setUnidad(String unidad) {this.unidad = unidad;}

    public String getUnidad() {return unidad;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return Objects.equals(codigo, producto.getCodigo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    @Override
    public String toString() {
        return descripcion;
    }

}
