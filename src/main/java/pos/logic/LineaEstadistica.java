package pos.logic;
import jakarta.xml.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class LineaEstadistica {
    @XmlID
    private String codigo;
//    @XmlIDREF
    private Factura factura;
//    @XmlIDREF
    private Fecha date;
//    @XmlIDREF
    private Categoria categoria;
    private static int id = 0;

    public LineaEstadistica() {
        this.codigo = "";
        this.categoria = new Categoria();
        this.factura = new Factura();
        this.codigo = "FAC-"+id++;
    }

    public LineaEstadistica(Factura factura, Categoria categoria,String codigo) {
        this.codigo = codigo;
        this.categoria = categoria;
        this.factura = factura;
    }

    public String getCodigo() { return codigo;}
    public Factura getFactura() { return factura; }
    public Categoria getCategoria() { return categoria; }

    public void setCodigo(String codigo) { this.codigo = codigo; }
    public void setFactura(Factura factura) { factura = factura; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }

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
