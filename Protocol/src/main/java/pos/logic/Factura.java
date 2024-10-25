package pos.logic;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


public class Factura implements Serializable {
    private Integer codigo;
    private Cajero cajero;
    private Cliente cliente;
    private LocalDate fecha;

    public Factura() {
        this.codigo = 0;
        this.cajero = null;
        this.cliente = null;
        this.fecha = LocalDate.now();
    }

    public Factura(Cajero cajero, Cliente cliente) {
        this.codigo = 0;
        this.cajero = cajero;
        this.cliente = cliente;
        this.fecha = LocalDate.now();
    }


    public Integer getCodigo() { return codigo; }
    public void setCodigo(Integer codigo) { this.codigo = codigo; }
    public void setCajero(Cajero cajero) { this.cajero = cajero; }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public Cajero getCajero() {
        return cajero;
    }
    public Cliente getCliente() {return cliente;}
    public LocalDate getFecha() {return fecha;}
    public String getFechaString() { return fecha.getYear() + "-" + fecha.getMonth() + "-" + fecha.getDayOfMonth(); }
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
