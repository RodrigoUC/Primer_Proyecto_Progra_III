package pos.logic;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class LineaHistorico { //Listado
    @XmlID
    private String numero;
    @XmlIDREF
    private Factura factura;
    private String clienteStr; //puse el string para no tener que jalar un obj
    private String cajeroStr;  //igual se filtra por el nombre del cliente en Busqueda
    @XmlIDREF
    private Fecha fecha;
    private double importe;


    public LineaHistorico() {
        this.numero = "HIST-";
        factura = new Factura();
    }

    public LineaHistorico(Factura factura) {
        setFactura(factura);
    }

    public String getNumero() { return numero;}
    public Factura getFactura() { return factura; }

    public void setFactura(Factura factura) {
        this.factura = factura;
        clienteStr =factura.getCliente().getNombre();
        cajeroStr=factura.getCajero().getNombre();
        fecha = factura.getFecha();
    }

    public String getNombreCliente() {return clienteStr;}
    public String getNombreCajero() {return cajeroStr;}
    public Fecha getFecha() {return fecha;}
    public double getImporte() {return importe;}
    public void setImporte(double importe) {this.importe = importe;}
    public String getFechaString() {
        return fecha.toString();
    }
    public String toString(){
        return "";
    }
}
