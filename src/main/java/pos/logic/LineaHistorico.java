package pos.logic;
import jakarta.xml.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class LineaHistorico {
    @XmlID
    private String numero;
    @XmlElementWrapper(name = "facturas")
    @XmlElement(name = "factura")
    private List<Factura> facturas;
    private String clienteStr; //puse el string para no tener que jalar un obj
    private String cajeroStr;  //igual se filtra por el nombre del cliente en Busqueda
    private Fecha fecha;
    private double importe;


    public LineaHistorico() {
        this.numero = "";
        this.facturas = new ArrayList<>();
    }

    public LineaHistorico(List<Factura> facturas,String numero) {
        this.numero = numero;
        this.facturas = facturas;
    }

    public LineaHistorico(List<Factura> facturas) {
        this.facturas = facturas;
    }

    public String getNumero() { return numero;}
    public List<Factura> getFacturas() { return facturas; }

    public void setNumero(String numero) {
        this.numero = numero;
        for(Factura factura : facturas) {
            if(factura.getCodigo().equals(numero)) {
                clienteStr =factura.getCliente().getNombre();
                cajeroStr=factura.getCajero().getNombre();
                fecha = factura.getFecha();
            }
        }
    }

    public void setFacturas(List<Factura> facturas) { facturas = facturas; }

    public String getNombreCliente() {return clienteStr;}
    public String getNombreCajero() {return cajeroStr;}
    public Fecha getFecha() {return fecha;}

    public String toString(){
        return "";
    }
}
