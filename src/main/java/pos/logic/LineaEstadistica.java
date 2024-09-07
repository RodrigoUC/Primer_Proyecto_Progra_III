package pos.logic;
import jakarta.xml.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class LineaEstadistica {
    @XmlID
    private String codigo;
//    @XmlIDREF
    private List<Factura> factura;
//    @XmlIDREF
    private Double totalVendido;
    private Fecha date;
//    @XmlIDREF
    private Categoria categoria;
    private static int id = 0;

    public LineaEstadistica() {
        this.categoria = new Categoria();
        this.date = new Fecha();
        this.totalVendido = 0.0;
        this.factura = new ArrayList<>();
        this.codigo = "FAC-"+id++;
    }

    public LineaEstadistica(Categoria categoria, Fecha date) {
        this.categoria = categoria;
        this.date = date;
        this.codigo = "FAC-"+id++;
    }

    public String getCodigo() { return codigo;}
    public List<Factura> getFactura() { return factura; }
    public Categoria getCategoria() { return categoria; }
    public Fecha getDate() { return date; }
    public Double getTotalVendido() { return totalVendido; }

    public void setCodigo(String codigo) { this.codigo = codigo; }
    public void setFactura(List<Factura> factura) { factura = factura; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }
    public void setDate(Fecha date) { this.date = date; }
    public void setTotalVendido(Double totalVendido) { this.totalVendido = totalVendido; }

//    public Double totalDeVentas() {
//        double total = 0.0;
//        for (Factura factura : factura) {
//            total += factura.getTotal();
//        }
//        return total;
//    }

    public String toString(){
        return categoria.toString();
    }
}
