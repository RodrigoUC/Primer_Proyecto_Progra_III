package pos.logic;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlID;

import java.util.Objects;


@XmlAccessorType(XmlAccessType.FIELD)
public class Factura {
   @XmlID
   private Linea[] vec;
   private Cajero cajero;
   private Cliente cliente;

   public Factura(Cajero cajero, Cliente cliente) {
      this.cajero = cajero;
      this.cliente = cliente;
   }
   public void setCajero(Cajero cajero) {
      this.cajero = cajero;
   }
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
