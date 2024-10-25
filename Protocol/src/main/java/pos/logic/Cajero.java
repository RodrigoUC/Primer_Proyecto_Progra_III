package pos.logic;
import java.io.Serializable;
import java.util.Objects;

public class Cajero implements Serializable {
    private String id;
    private String nombre;

    public Cajero() {this("", "");}

    public Cajero(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre;}

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Cajero Cajero = (Cajero) o;
        return Objects.equals(id, Cajero.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }

    @Override
    public String toString() { return nombre; }

}
