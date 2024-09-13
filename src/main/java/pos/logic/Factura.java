package pos.logic;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlID;
import jakarta.xml.bind.annotation.XmlIDREF;

import java.util.List;
import java.util.Objects;


@XmlAccessorType(XmlAccessType.FIELD)
public class Factura {
//   @XmlID
   private String codigo;

   public void setVec(List<Linea> vec) {
      this.vec = vec;
   }

   private List<Linea> vec;
//   @XmlIDREF
   private Cajero cajero;
//   @XmlIDREF
   private Cliente cliente;
//   @XmlIDREF
   private Fecha fecha;

   public Factura() {
      this.cajero = null;
      this.cliente = null;
      fecha = new Fecha();
   }

   public Factura(Fecha fecha, List<Linea> vec) {
      this.fecha = fecha;
      this.vec = vec;
      this.cajero = null;
      this.cliente = null;
   }

   public Factura(Cajero cajero, Cliente cliente) {
      this.cajero = cajero;
      this.cliente = cliente;
      fecha = new Fecha();
   }
   public String getCodigo() { return codigo; }
   public void setCodigo(String codigo) { this.codigo = codigo; }
   public void setCajero(Cajero cajero) { this.cajero = cajero; }
   public void setCliente(Cliente cliente) {
      this.cliente = cliente;
   }
   public void setFecha(Fecha fecha) { this.fecha = fecha; }
   public Cajero getCajero() {
      return cajero;
   }
   public Cliente getCliente() {return cliente;}
   public List<Linea> getVec() {
        return vec;
   }
   public Fecha getFecha() {return fecha;}

   public String getNombreCliente(){
      return cliente.getNombre();
   }

   public double getTotal() {
      double total = 0.0;
      for (Linea l : vec) {
         total += l.getTotalLinea();
      }
      return total*(cliente.getDescuento()/100);
   }

   public double getSubTotal() {
      double total = 0.0;
      for (Linea l : vec) {
         total += l.getTotalLinea();
      }
      return total;
   }
   public double getDescuentos() {
      double desc = 0.0;
      for (Linea l : vec) {
         desc += l.getDescuento();
      }
      return (desc+cliente.getDescuento())/100;
   }

 public List<Linea> getLineas() { return vec; }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Factura factura = (Factura) o;
      return Objects.equals(codigo, factura.codigo);
   }

}
