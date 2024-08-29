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

    public Producto(String descripcion, String codigo) {
        this.descripcion = descripcion;
        this.codigo = codigo;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return Objects.equals(codigo,producto.getCodigo());
    }

    @Override
    public int hashCode() { return Objects.hash(codigo); }

    @Override
    public String toString() {
        return descripcion;
    }

}
