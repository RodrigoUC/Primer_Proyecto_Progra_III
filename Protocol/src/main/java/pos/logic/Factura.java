package pos.logic;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Factura {
    private int codigo;
    private Cajero cajero;
    private Cliente cliente;
    private Fecha fecha;

    public Factura() {
        LocalDate local = LocalDate.now();
        this.cajero = null;
        this.cliente = null;
        fecha = new Fecha(local.getYear(), local.getMonthValue(), local.getDayOfMonth());
    }

    public Factura(Cajero cajero, Cliente cliente) {
        LocalDate local = LocalDate.now();
        this.cajero = cajero;
        this.cliente = cliente;
        fecha = new Fecha(local.getYear(), local.getMonthValue(), local.getDayOfMonth());
    }


    public int getCodigo() { return codigo; }
    public void setCodigo(int codigo) { this.codigo += codigo; }
    public void setCajero(Cajero cajero) { this.cajero = cajero; }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public void setFecha(Fecha fecha) { this.fecha = fecha; }
    public Cajero getCajero() {
        return cajero;
    }
    public Cliente getCliente() {return cliente;}

    public Fecha getFecha() {return fecha;}

    public String getNombreCliente(){
        return cliente.getNombre();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Factura factura = (Factura) o;
        return Objects.equals(codigo, factura.codigo);
    }

}
