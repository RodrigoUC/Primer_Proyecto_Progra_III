//package pos.logic;
//
//public class LineaHistorico { //Listado
//    private String numero;
//    private Factura factura;
//    private String clienteStr; //puse el string para no tener que jalar un obj
//    private String cajeroStr;  //igual se filtra por el nombre del cliente en Busqueda
//    private Fecha fecha;
//    private float importe;
//
//
//    public LineaHistorico() {
//        factura = new Factura();
//    }
//
//    public LineaHistorico(Factura factura) {
//        setFactura(factura);
//    }
//
//    public String getNumero() { return numero;}
//    public Factura getFactura() { return factura; }
//
//    public void setFactura(Factura factura) {
//        this.factura = factura;
//        numero = factura.getCodigo();
//        clienteStr =factura.getCliente().getNombre();
//        cajeroStr=factura.getCajero().getNombre();
//        fecha = factura.getFecha();
//        //importe = factura.getTotal();
//    }
//
//    public String getNombreCliente() {return clienteStr;}
//    public String getNombreCajero() {return cajeroStr;}
//    public Fecha getFecha() {return fecha;}
//    public float getImporte() {return importe;}
//    public String getFechaString() {
//        return fecha.toString();
//    }
//    public String toString(){
//        return "";
//    }
//}
