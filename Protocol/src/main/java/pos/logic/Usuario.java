package pos.logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Usuario implements Serializable {
    private String ID;
    private String contrasena;
    private List<Factura> facturas;

    public Usuario(String ID, String contrasena) {
        this.ID = ID;
        this.contrasena = contrasena;
        this.facturas = new ArrayList<Factura>();
    }
    public Usuario(){
        this.ID = "";
        this.contrasena = "";
        this.facturas= new ArrayList<Factura>();
    }
    public String getID() {
        return ID;
    }
    public void setID(String ID) {
        this.ID = ID;
    }
    public String getContrasena() {
        return contrasena;
    }
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    public List<Factura> getFacturas() {
        return facturas;
    }
    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }

    @Override
    public String toString() {
        return ID;
    }
}
