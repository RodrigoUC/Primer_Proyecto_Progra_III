package pos.logic;
import jakarta.xml.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class LineaEstadistica {
    @XmlID
    private String codigo;
    @XmlElementWrapper(name = "facturas")
    @XmlElement(name = "factura")
    private List<Factura> facturas;
    @XmlIDREF
    private Categoria categoria;

    public LineaEstadistica() {
        this.codigo = "";
        this.categoria = new Categoria();
        this.facturas = new ArrayList<>();
    }

    public LineaEstadistica(List<Factura> facturas, Categoria categoria,String codigo) {
        this.codigo = codigo;
        this.categoria = categoria;
        this.facturas = facturas;
    }

    public String getCodigo() { return codigo;}
    public List<Factura> getFacturas() { return facturas; }
    public Categoria getCategoria() { return categoria; }

    public void setCodigo(String codigo) { this.codigo = codigo; }
    public void setFacturas(List<Factura> facturas) { facturas = facturas; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }

//    public Double totalDeVentas() {
//       Double total = 0.0;
//        for(Factura factura : facturas) {
//           total += factura.getTotal();
//       }
//        return total;
//    }

    public String toString(){
        return categoria.toString();
    }
}
