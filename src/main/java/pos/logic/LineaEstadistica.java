package pos.logic;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlID;

import javax.sound.sampled.Line;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class LineaEstadistica {
    @XmlID
    private Fecha date;
    private Categoria categoria;
    private Double totalVentas;

    public LineaEstadistica() {
        this.date = new Fecha();
        this.categoria = new Categoria();
        this.totalVentas = 0.0;
    }

    public LineaEstadistica(Fecha fecha, Categoria categoria, Double totalVentas) {
        this.date = fecha;
        this.categoria = categoria;
        this.totalVentas = totalVentas;
    }

    public Fecha getDate() { return date; }
    public Categoria getCategoria() { return categoria; }
    public Double getTotalVentas() { return totalVentas; }

    public void setDate(Fecha fecha) { date = fecha; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }
    public void setTotalVentas(Double totalVentas) { this.totalVentas = totalVentas; }

    public String toString(){
        return date.toString() + " " + categoria.toString() + " " + totalVentas;
    }
}
