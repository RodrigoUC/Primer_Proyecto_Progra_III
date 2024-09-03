package pos.logic;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlID;
import jakarta.xml.bind.annotation.XmlIDREF;

import java.util.Objects;


@XmlAccessorType(XmlAccessType.FIELD)
public class Factura {
   @XmlID
   private String codigo;
   private Linea[] vec;
   @XmlIDREF
   private Cajero cajero;
   @XmlIDREF
   private Cliente cliente;

   public Factura(Cajero cajero, Cliente cliente) {
      this.cajero = cajero;
      this.cliente = cliente;
   }
   public String getCodigo() { return codigo; }
   public void setCodigo(String codigo) { this.codigo = codigo; }
   public void setCajero(Cajero cajero) { this.cajero = cajero; }
   public void setCliente(Cliente cliente) {
      this.cliente = cliente;
   }
   public Cajero getCajero() {
      return cajero;
   }
   public Cliente getCliente() {
      return cliente;

   }
   public Linea[] getVec() {
      return vec;
   }

}
