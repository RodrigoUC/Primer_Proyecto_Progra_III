package pos.logic;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlID;

import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Cajero {
    @XmlID
    String id;
    String nombre;
    String telefono;
    String email;
    float descuento;

    public Cajero() {this("","");}

    public Cajero(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = "";
        this.email = "";
        this.descuento = 0;
    }

    public String getId() {return id;}

    public void setId(String id) {this.id = id;}

    public String getNombre() {return nombre;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getTelefono() {return telefono;}
    public void setTelefono(String telefono) {this.telefono = telefono;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public float getDescuento() {return descuento;}
    public void setDescuento(float descuento) {this.descuento = descuento;}

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Cajero cajero = (Cajero) o;
        return Objects.equals(id, cajero.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }

    @Override
    public String toString() { return nombre; }

}
