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


    //-------------------Lineas----------------------------------------------
    public void create(Linea e) throws Exception{
        e.setId(data.nextLinea());
        data.getLineas().add(e);
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

    public List<Factura> search(Factura e){
        return  data.getFacturas().stream()
                .filter(i -> i.getNombreCliente().equals(e.getNombreCliente()))
                .sorted(Comparator.comparing(Factura::getNombreCliente).thenComparing(Factura::getNombreCliente))
                .collect(Collectors.toList());
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
