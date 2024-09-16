package pos.logic;

import pos.data.Data;
import pos.data.XmlPersister;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Service {
    private static Service theInstance;

    public static Service instance(){
        if (theInstance == null) theInstance = new Service();
        return theInstance;
    }
    private Data data;

    private Service(){
        try{
            data= XmlPersister.instance().load();
        }
        catch(Exception e){
            data =  new Data();
        }
    }

    public void stop(){
        try {
            XmlPersister.instance().store(data);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

//================= CLIENTES ============

    public void create(Cliente e) throws Exception{
        Cliente result = data.getClientes().stream().filter(i->i.getId().equals(e.getId())).findFirst().orElse(null);
        if (result==null) data.getClientes().add(e);
        else throw new Exception("Cliente ya existe");
    }

    public Cliente read(Cliente e) throws Exception{
        Cliente result = data.getClientes().stream().filter(i->i.getId().equals(e.getId())).findFirst().orElse(null);
        if (result!=null) return result;
        else throw new Exception("Cliente no existe");
    }

    public Cliente readNombre(Cliente e) throws Exception{ //equals ya tiene override con id, ocupa nombre
        Cliente result = data.getClientes().stream().filter(i->i.getNombre().equals(e.getNombre())).findFirst().orElse(null);
        if (result!=null) return result;
        else throw new Exception("Cliente no existe");
    }

    public void update(Cliente e) throws Exception{
        Cliente result;
        try{
            result = this.read(e);
            data.getClientes().remove(result);
            data.getClientes().add(e);
        }catch (Exception ex) {
            throw new Exception("Cliente no existe");
        }
    }

    public void delete(Cliente e) throws Exception{
        data.getClientes().remove(e);
    }

    public List<Cliente> search(Cliente e){
        return data.getClientes().stream()
                .filter(i->i.getNombre().contains(e.getNombre()))
                .sorted(Comparator.comparing(Cliente::getNombre))
                .collect(Collectors.toList());
    }

    // ----------------------CAJEROS-------------------------
    public void create(Cajero e) throws Exception{
        Cajero result = data.getCajeros().stream().filter(i->i.getId().equals(e.getId())).findFirst().orElse(null);
        if (result==null) data.getCajeros().add(e);
        else throw new Exception("Cajero ya existe");
    }

    public Cajero read(Cajero e) throws Exception{
        Cajero result = data.getCajeros().stream().filter(i->i.getId().equals(e.getId())).findFirst().orElse(null);
        if (result!=null) return result;
        else throw new Exception("Cajero no existe");
    }

    public void update(Cajero e) throws Exception{
        Cajero result;
        try{
            result = this.read(e);
            data.getCajeros().remove(result);
            data.getCajeros().add(e);
        }catch (Exception ex) {
            throw new Exception("Cajero no existe");
        }
    }

    public void delete(Cajero e) throws Exception{
        data.getCajeros().remove(e);
    }

    public List<Cajero> search(Cajero e){
        return  data.getCajeros().stream()
                .filter(i -> i.getNombre().contains(e.getNombre()) && i.getId().contains(e.getId()))
                .sorted(Comparator.comparing(Cajero::getNombre).thenComparing(Cajero::getId))
                .collect(Collectors.toList());
    }

    // ----------------------PRODUCTOS-------------------------
    public void create(Producto e) throws Exception {
        Producto result = data.getProductos().stream().filter(i -> i.getCodigo().equals(e.getCodigo())).findFirst().orElse(null);
        if (result == null) {
            create(e.getCategoria());
            data.getProductos().add(e);
        }
        else throw new Exception("Producto ya existe");
    }

    public Producto read(Producto e) throws Exception{
        Producto result = data.getProductos().stream().filter(i->i.getCodigo().equals(e.getCodigo())).findFirst().orElse(null);
        if (result!=null) {
            result.setCategoria(read(result.getCategoria()).getNombre());
            return result;
        }
        else throw new Exception("Producto no existe");
    }

    public void update(Producto e) throws Exception{
        Producto result;
        try{
            result = this.read(e);
            data.getProductos().remove(result);
            data.getProductos().add(e);
        }catch (Exception ex) {
            throw new Exception("Producto no existe");
        }
    }

    public void delete(Producto e) throws Exception{
        data.getProductos().remove(e);
    }

    public List<Producto> search(Producto e){
        return  data.getProductos().stream()
                .filter(i -> i.getDescripcion().contains(e.getDescripcion()) && i.getCodigo().contains(e.getCodigo()))
                .sorted(Comparator.comparing(Producto::getDescripcion).thenComparing(Producto::getCodigo))
                .collect(Collectors.toList());
    }

    //-------------------Fechas----------------------------------------------

    public void create(Fecha e) throws Exception{
        Fecha result = data.getFechas().stream().filter(i->i.getCodigo().equals(e.getCodigo())).findFirst().orElse(null);
        if (result == null) data.getFechas().add(e);
    }

    public List<Fecha> search(Fecha e){
        return data.getFechas();
    }

    public Fecha read(Fecha e){
        return data.getFechas().stream().filter(i->i.getCodigo().equals(e.getCodigo())).findFirst().orElse(null);
    }


    //-------------------Lineas----------------------------------------------
    public void create(Linea e) throws Exception{
        e.setId(data.nextLinea());
        data.getLineas().add(e);
    }

    public Linea read(Linea e) throws Exception{
        Linea result = data.getLineas().stream().filter(i->i.getCodigo().equals(e.getCodigo())).findFirst().orElse(null);
        if (result!=null) return result;
        else throw new Exception("Linea no existe");
    }

    public void update(Linea e) throws Exception{
        Linea result;
        try{
            result = this.read(e);
            data.getLineas().remove(result);
            data.getLineas().add(e);
        }catch (Exception ex) {
            throw new Exception("Linea no existe");
        }
    }

    public void delete(Linea e) throws Exception{
    data.getLineas().remove(e);
    }

    public List<Linea> search(Linea e){
        return  data.getLineas().stream()
                .filter(i->i.getCodigo().contains(e.getCodigo()))
                .sorted(Comparator.comparing(Linea::getCodigo))
                .collect(Collectors.toList());
    }

    public List<Linea> searchAll(Linea e){
        return data.getLineas();
    }

    //------------------------Factura---------------------------------

    public void create(Factura e) throws Exception{
        Factura result = data.getFacturas().stream().filter(i->i.getCodigo().equals(e.getCodigo())).findFirst().orElse(null);
        if (result==null) {
            e.setCodigo(data.nextFactura());
            create(e.getFecha());
            data.getFacturas().add(e);
        }
        else throw new Exception("Factura ya existe (codigo)");
    }

    public Factura read(Factura e) throws Exception{
        //compara codigo de factura
        Factura result = data.getFacturas().stream().filter(i->i.getCodigo().equals(e.getCodigo())).findFirst().orElse(null);
        if (result!=null){
            result.setCajero(read(e.getCajero()));
            return result;
        }
        else throw new Exception("Factura no existe");
    }

    public List<Factura> readList(Factura e, Categoria c) throws Exception {
        // Filtra las facturas por fecha
        List<Factura> facturasFiltradas = data.getFacturas().stream()
                .filter(f -> f.getFecha().getAnio() == e.getFecha().getAnio() && f.getFecha().getMes() == e.getFecha().getMes())  // Filtra por mes y anio
                .collect(Collectors.toList());

        // Si no se encuentran facturas, lanza excepción
        if (facturasFiltradas == null || facturasFiltradas.isEmpty()) {
            throw new Exception("Factura no existe");
        }

        // Para cada factura filtrada, filtra las líneas por categoría
        List<Factura> facturasConLineasFiltradas = facturasFiltradas.stream()
                .map(factura -> {
                    // Filtrar las líneas de la factura por la categoría c
                    List<Linea> lineasFiltradas = factura.getVec().stream()
                            .filter(linea -> linea.getCategoria().equals(c))
                            .collect(Collectors.toList());

                    // Crear una nueva factura con las líneas filtradas (o modificar las líneas de la existente)
                    Factura nuevaFactura = new Factura(factura.getFecha(), lineasFiltradas);
                    return nuevaFactura;
                })
                // Mantener solo las facturas que tienen líneas en la categoría
                .filter(factura -> !factura.getVec().isEmpty())
                .collect(Collectors.toList());

        // Si la lista de facturas filtradas por líneas está vacía, lanza excepción
        if (facturasConLineasFiltradas.isEmpty()) {
            throw new Exception("No hay facturas con líneas en la categoría especificada");
        }
        return facturasConLineasFiltradas;
    }

    public List<Factura> search(Factura e){
//        return  data.getFacturas().stream()
//                .filter(i -> i.getNombreCliente().contains(e.getNombreCliente()))
//                .sorted(Comparator.comparing(Factura::getNombreCliente).thenComparing(Factura::getNombreCliente))
//                .collect(Collectors.toList());
        return data.getFacturas().stream().filter(i->i.getNombreCliente().equals(e.getNombreCliente())).sorted().collect(Collectors.toList());
    }

    public List<Factura> searchAll(Factura e){
        return data.getFacturas();
    }

    // ----------------------Categorias-------------------------

    public void create(Categoria e) throws Exception{
        Categoria result = data.getCategorias().stream().filter(i->i.getId().equals(e.getId())).findFirst().orElse(null);
        if (result == null) data.getCategorias().add(e);
    }

    public List<Categoria> search(Categoria e){
        return data.getCategorias();
    }

    public Categoria read(Categoria e){
        return data.getCategorias().stream().filter(i->i.getId().equals(e.getId())).findFirst().orElse(null);
    }

//    // ----------------------Linea historico (Listado)-------------------------
    public void create(LineaHistorico e) throws Exception{
        LineaHistorico result = data.getLineasHistoricas().stream().filter(i -> i.equals(e)).findFirst().orElse(null);
        if (result==null) data.getLineasHistoricas().add(e);
        else throw new Exception("Linea historica ya existe");
    }

    public LineaHistorico read(LineaHistorico e) throws Exception{
        LineaHistorico result = data.getLineasHistoricas().stream().filter(i->i.getNumero().equals(e.getNumero())).findFirst().orElse(null);
        if (result!=null) return result;
        else throw new Exception("Linea historica no existe");
    }

    public void update(LineaHistorico e) throws Exception{
        LineaHistorico result;
        try{
            result = this.read(e);
            data.getLineasHistoricas().remove(result);
            data.getLineasHistoricas().add(e);
        }catch (Exception ex) {
            throw new Exception("Linea historica no existe");
        }
    }

    public List<LineaHistorico> search(LineaHistorico e){
        return data.getLineasHistoricas().stream()
                .filter(i->i.getNumero().contains(e.getNumero()))
                .sorted(Comparator.comparing(LineaHistorico::getNumero))
                .collect(Collectors.toList());
    }

    //-------------------------------Calculos--------------------------------------

    public Double totalDelMes(String categoria, int anio, int mes){
        double total = 0d;

        List<Factura> facturas = data.getFacturas().stream().filter(factura -> factura.getFecha().getAnio() == anio && factura.getFecha().getMes() == mes).toList();

        for (Factura factura : facturas) {
            total += factura.getTotalPorCategoria(categoria);
        }

        return total;

    }

}
