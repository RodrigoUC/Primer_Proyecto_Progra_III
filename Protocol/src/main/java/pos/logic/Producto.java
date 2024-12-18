package pos.logic;

import java.io.Serializable;
import java.util.Objects;

public class Producto implements Serializable {
    private String codigo;
    private String descripcion;
    private Float precio;
    private Categoria categoria;
    private String unidad;
    private Integer existencia;

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public Producto()  {
        this("","",0,0.f, new Categoria());
    }

    public boolean disminuirExistenciaEn(int n){
        return existencia - n >= 0;
    }

    public void setExistencia(Integer existencia) {this.existencia = existencia;}

    public Integer getExistencia(){ return existencia; }

    public Producto(String descripcion, String codigo, Integer existencia, Float precio, Categoria categoria) {
        this.descripcion = descripcion;
        this.codigo = codigo;
        this.existencia = existencia;
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

    public void setCategoria(String nombre) {
        this.categoria.setNombre(nombre);
        this.categoria.setId(nombre);
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
