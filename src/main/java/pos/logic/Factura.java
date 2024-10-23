package pos.logic;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


public class Factura {
   private Integer codigo;
//   private List<Linea> vec;
   private Cajero cajero;
   private Cliente cliente;
   private LocalDate fecha;

   public Factura() {
      this.codigo = 0;
      this.cajero = null;
      this.cliente = null;
//      vec = new ArrayList<>();
      this.fecha = LocalDate.now();
   }

   public Factura(Cajero cajero, Cliente cliente) {
      this.codigo = 0;
      this.cajero = cajero;
      this.cliente = cliente;
//      vec = new ArrayList<>();
      this.fecha = LocalDate.now();
   }

//   public void setVec(List<Linea> vec) {
//      this.vec = vec;
//   }
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
//   public List<Linea> getVec() {
//        return vec;
//   }
   public LocalDate getFecha() { return fecha; }

   public String getFechaString() { return fecha.getYear() + "-" + fecha.getMonth() + "-" + fecha.getDayOfMonth(); }

   public String getNombreCliente(){
      return cliente.getNombre();
   }

//   public float getTotal() {
//      if(cliente.getDescuento() != 0)
//         return getSubTotal() + getSubTotal()*(cliente.getDescuento()/100);
//      else
//         return getSubTotal();
//   }

//   public float getSubTotal() {
//      float total = 0f;
//      for (Linea l : vec) {
//         total += (float) l.getTotalLinea();
//      }
//      return total;
//   }
//   public int cantidadArticulos(){
//      int cantidad = 0;
//      if(!vec.isEmpty()) {
//         for (Linea linea : vec) {
//            cantidad += linea.getCantidad();
//         }
//      }
//      return cantidad;
//   }
//   public double getTotalDescuento(){
//      Double descuento = 0.0;
//      if(!vec.isEmpty()) {
//         for (Linea linea : vec) {
//            descuento += (linea.getDescuento()/100)*linea.getCantidad()*linea.getProducto().getPrecio();
//         }
//      }
//      return descuento;
//   }
//   public Double getTotalPorCategoria(String categoria){
//      double total = 0.0d;
//      for (Linea l : vec) {
//         if(Objects.equals(categoria, l.getCategoria().getNombre())){
//            total += l.getTotalLinea();
//         }
//      }
//      return total;
//   }

//   public List<Linea> getLineas() { return vec; }
//
//   public void setLineas(List<Linea> vec) { this.vec = vec; }




   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Factura factura = (Factura) o;
      return Objects.equals(codigo, factura.codigo);
   }

}
