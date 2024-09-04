package pos.logic;
import jakarta.xml.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class LineaHistorico { //Listado
    @XmlID
    private String numero;

    private Factura factura;
    private String clienteStr; //puse el string para no tener que jalar un obj
    private String cajeroStr;  //igual se filtra por el nombre del cliente en Busqueda
    private Fecha fecha;
    private double importe;


    public LineaHistorico() {
        this.numero = "";
        factura = new Factura();
    }

    public LineaHistorico(Factura factura) {
        setFactura(factura);
    }

    public String getNumero() { return numero;}
    public Factura getFactura() { return factura; }

    public void setFactura(Factura factura) {
        this.factura = factura;
        numero = factura.getCodigo();
        clienteStr =factura.getCliente().getNombre();
        cajeroStr=factura.getCajero().getNombre();
        fecha = factura.getFecha();
    }

    public String getNombreCliente() {return clienteStr;}
    public String getNombreCajero() {return cajeroStr;}
    public Fecha getFecha() {return fecha;}

    public String toString(){
        return "";
    }
}
