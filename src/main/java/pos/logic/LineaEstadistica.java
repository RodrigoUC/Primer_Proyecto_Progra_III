package pos.logic;
import jakarta.xml.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class LineaEstadistica {
    @XmlID
    private String codigo;
    @XmlElementWrapper(name = "dates")
    @XmlElement(name = "date")
    private List<LocalDate> dates;
    @XmlIDREF
    private Categoria categoria;

    public LineaEstadistica() {
        this.codigo = "";
        this.categoria = new Categoria();
        this.dates = new ArrayList<>();
    }

    public LineaEstadistica(List<LocalDate> fechas, Categoria categoria,String codigo) {
        this.codigo = codigo;
        this.categoria = categoria;
        this.dates = fechas;
    }

    public String getCodigo() { return codigo;}
    public List<LocalDate> getDate() { return dates; }
    public Categoria getCategoria() { return categoria; }

    public void setCodigo(String codigo) { this.codigo = codigo; }
    public void setDate(List<LocalDate> fechas) { dates = fechas; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }

//    public Double totalDeVentas(List<Factura> facturas) {
//        if (facturas.contains()) ;
//        return Double.valueOf(0);
//    }

    public String toString(){
        return dates.toString() + " " + categoria.toString();
    }
}
