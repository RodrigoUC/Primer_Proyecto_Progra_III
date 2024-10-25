package pos.logic;

import java.io.Serializable;
import java.time.LocalDate;

public class LineaHistorico implements Serializable { //Listado
    private int numero;
    private Factura factura;
    private String clienteStr; //puse el string para no tener que jalar un obj
    private String cajeroStr;  //igual se filtra por el nombre del cliente en Busqueda
    private LocalDate fecha;
    private float importe;


    public LineaHistorico() {
        factura = new Factura();
    }

    public LineaHistorico(Factura factura) {
        setFactura(factura);
    }

    public int getNumero() { return numero;}
    public Factura getFactura() { return factura; }

    public void setFactura(Factura factura) {
        this.factura = factura;
        numero = factura.getCodigo();
        clienteStr =factura.getCliente().getNombre();
        cajeroStr=factura.getCajero().getNombre();
        fecha = factura.getFecha();
        importe = 0;
        //importe = factura.getTotal();
    }

    public String getNombreCliente() {return clienteStr;}
    public String getNombreCajero() {return cajeroStr;}
    public LocalDate getFecha() {return fecha;}
    public float getImporte() {return importe;}
    public String getFechaString() {
        return fecha.toString();
    }
    public String toString(){
        return "";
    }
    public void setImporte(float importe) {this.importe = importe;}

}
