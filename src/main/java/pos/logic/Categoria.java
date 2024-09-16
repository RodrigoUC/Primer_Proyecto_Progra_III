package pos.logic;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlID;

import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Categoria {
    @XmlID
    String id;
    String nombre;

    public Categoria() {this("");}

    public Categoria(String nombre){
        this.nombre = nombre;
        this.id = "CAT-" + nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = "CAT-" + id;
    }

    public String toString() {
        return nombre;
    }
}
