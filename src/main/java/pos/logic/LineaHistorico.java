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
    private String cliente; //puse el string para no tener que jalar un obj
    private String cajero;  //igual se filtra por el nombre del cliente en Busqueda
    private Fecha fecha;
    private double importe;


    public LineaHistorico() {
        this.numero = "";
        this.facturas = new ArrayList<>();
    }

    public LineaHistorico(List<Factura> facturas,String codigo) {
        this.numero = codigo;
        this.facturas = facturas;
    }

    public String getCodigo() { return numero;}
    public List<Factura> getFacturas() { return facturas; }

    public void setNumero(String numero) {
        this.numero = numero;
        for(Factura factura : facturas) {
            if(factura.getCodigo().equals(numero)) {
                cliente=factura.getCliente().getNombre();
                cajero=factura.getCajero().getNombre();
            }
        }
    }

    public void setFacturas(List<Factura> facturas) { facturas = facturas; }



    public String toString(){
        return "";
    }
}
