package pos.logic;

import com.mysql.cj.xdevapi.Client;
import pos.data.*;

import pos.data.CategoriaDao;
import pos.data.ProductoDao;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Service {
    private static Service theInstance;

    public static Service instance(){
        if (theInstance == null) theInstance = new Service();
        return theInstance;
    }
//    private Data data;
    private ProductoDao productoDao;
    private CategoriaDao categoriaDao;
    private ClienteDao clienteDao;
    private CajeroDao cajeroDao;
    private FacturaDao facturaDao;
    private LineaDao lineaDao;

    private Service(){
        try{
            categoriaDao = new CategoriaDao();
            productoDao = new ProductoDao();
            clienteDao = new ClienteDao();
            cajeroDao = new CajeroDao();
            facturaDao = new FacturaDao();
            lineaDao = new LineaDao();
//            data = new Data();
        }
        catch(Exception e){
//            data = new Data();
        }
    }

    public void stop(){
    }

    //================= CLIENTES ============

//    public void create(Cliente e) throws Exception{
//        Cliente result = data.getClientes().stream().filter(i->i.getId().equals(e.getId())).findFirst().orElse(null);
//        if (result==null) data.getClientes().add(e);
//        else throw new Exception("Cliente ya existe");
//    }

//    public Cliente read(Cliente e) throws Exception{
//        Cliente result = data.getClientes().stream().filter(i->i.getId().equals(e.getId())).findFirst().orElse(null);
//        if (result!=null) return result;
//        else throw new Exception("Cliente no existe");
//    }

//    public Cliente readNombre(Cliente e) throws Exception{ //equals ya tiene override con id, ocupa nombre
//        Cliente result = data.getClientes().stream().filter(i->i.getNombre().equals(e.getNombre())).findFirst().orElse(null);
//        if (result!=null) return result;
//        else throw new Exception("Cliente no existe");
//    }

//    public void update(Cliente e) throws Exception{
//        Cliente result;
//        try{
//            result = this.read(e);
//            data.getClientes().remove(result);
//            data.getClientes().add(e);
//        }catch (Exception ex) {
//            throw new Exception("Cliente no existe");
//        }
//    }
//
//    public void delete(Cliente e) throws Exception{
//        data.getClientes().remove(e);
//    }
//
//    public List<Cliente> search(Cliente e){
//        return data.getClientes().stream()
//                .filter(i->i.getNombre().contains(e.getNombre()))
//                .sorted(Comparator.comparing(Cliente::getNombre))
//                .collect(Collectors.toList());
//    }

//     ----------------------CAJEROS-------------------------
//    public void create(Cajero e) throws Exception{
//        Cajero result = data.getCajeros().stream().filter(i->i.getId().equals(e.getId())).findFirst().orElse(null);
//        if (result==null) data.getCajeros().add(e);
//        else throw new Exception("Cajero ya existe");
//    }
//
//    public Cajero read(Cajero e) throws Exception{
//        Cajero result = data.getCajeros().stream().filter(i->i.getId().equals(e.getId())).findFirst().orElse(null);
//        if (result!=null) return result;
//        else throw new Exception("Cajero no existe");
//    }

//    public void update(Cajero e) throws Exception{
//        Cajero result;
//        try{
//            result = this.read(e);
//            data.getCajeros().remove(result);
//            data.getCajeros().add(e);
//        }catch (Exception ex) {
//            throw new Exception("Cajero no existe");
//        }
//    }

//    public void delete(Cajero e) throws Exception{
//        data.getCajeros().remove(e);
//    }
//
//    public List<Cajero> search(Cajero e){
//        return  data.getCajeros().stream()
//                .filter(i -> i.getNombre().contains(e.getNombre()) && i.getId().contains(e.getId()))
//                .sorted(Comparator.comparing(Cajero::getNombre).thenComparing(Cajero::getId))
//                .collect(Collectors.toList());
//    }

//    // ----------------------PRODUCTOS-------------------------
//    public void create(Producto e) throws Exception {
//        Producto result = data.getProductos().stream().filter(i -> i.getCodigo().equals(e.getCodigo())).findFirst().orElse(null);
//        if (result == null) {
//            create(e.getCategoria());
//            data.getProductos().add(e);
//        }
//        else throw new Exception("Producto ya existe");
//    }
//
//    public Producto read(Producto e) throws Exception{
//        Producto result = data.getProductos().stream().filter(i->i.getCodigo().equals(e.getCodigo())).findFirst().orElse(null);
//        if (result!=null) {
//            result.setCategoria(read(result.getCategoria()).getNombre());
//            return result;
//        }
//        else throw new Exception("Producto no existe");
//    }
//
//    public void update(Producto e) throws Exception{
//        Producto result;
//        try{
//            result = this.read(e);
//            data.getProductos().remove(result);
//            data.getProductos().add(e);
//        }catch (Exception ex) {
//            throw new Exception("Producto no existe");
//        }
//    }
//
//    public void delete(Producto e) throws Exception{
//        data.getProductos().remove(e);
//    }
//
//    public List<Producto> search(Producto e){
//        return  data.getProductos().stream()
//                .filter(i -> i.getDescripcion().contains(e.getDescripcion()) && i.getCodigo().contains(e.getCodigo()))
//                .sorted(Comparator.comparing(Producto::getDescripcion).thenComparing(Producto::getCodigo))
//                .collect(Collectors.toList());
//    }

    //================= CAJERO ==============
    public void create(Cajero e) throws Exception {
        cajeroDao.create(e);
    }

    public Cajero read(Cajero e) throws Exception {
        return cajeroDao.read(e.getId());
    }

    public void update(Cajero e) throws Exception {
        cajeroDao.update(e);
    }

    public void delete(Cajero e) throws Exception {
        cajeroDao.delete(e);
    }

    public List<Cajero> search(Cajero e) {
        try {
            return cajeroDao.search(e);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    //================= CLIENTE ==============
    public void create(Cliente e) throws Exception {
        clienteDao.create(e);
    }

    public Cliente read(Cliente e) throws Exception {
        return clienteDao.read(e.getId(), e.getNombre());
    }

    public void update(Cliente e) throws Exception {
        clienteDao.update(e);
    }

    public void delete(Cliente e) throws Exception {
        clienteDao.delete(e);
    }

    public List<Cliente> search(Cliente e) {
        try {
            return clienteDao.search(e);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    //================= PRODUCTOS ============
    public void create(Producto e) throws Exception {
        productoDao.create(e);
    }

    public Producto read(Producto e) throws Exception {
        return productoDao.read(e.getCodigo());
    }

    public void update(Producto e) throws Exception {
        productoDao.update(e);
    }

    public void delete(Producto e) throws Exception {
        productoDao.delete(e);
    }

    public List<Producto> search(Producto e) {
        try {
            return productoDao.search(e);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    //================= CATEGORIAS ============
    public List<Categoria> search(Categoria e) {
        try {
            return categoriaDao.search(e);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

//    -------------------Fechas----------------------------------------------

//    public void create(Fecha e) throws Exception{
//        Fecha result = data.getFechas().stream().filter(i->i.getCodigo().equals(e.getCodigo())).findFirst().orElse(null);
//        if (result == null) data.getFechas().add(e);
//    }
//
//    public List<Fecha> search(Fecha e){
//        return data.getFechas();
//    }


//    -------------------Lineas----------------------------------------------
//    public void create(Linea e) throws Exception{
//        e.setId(data.nextLinea());
//        data.getLineas().add(e);
//    }
//
//    public void delete(Linea e) throws Exception{
//    data.getLineas().remove(e);
//    }
//
//    public List<Linea> search(Linea e){
//        return  data.getLineas().stream()
//                .filter(i->i.getCodigo().contains(e.getCodigo()))
//                .sorted(Comparator.comparing(Linea::getCodigo))
//                .collect(Collectors.toList());
//    }
    public void create(Linea e) throws Exception {
        lineaDao.create(e);
    }

    public Linea read(Linea e) throws Exception {
        return lineaDao.read(e.getCodigo());
    }

    public void update(Linea e) throws Exception {
        lineaDao.update(e);
    }

    public void delete(Linea e) throws Exception {
        lineaDao.delete(e);
    }

    public List<Linea> search(Linea e) {
        try {
            return lineaDao.search(e);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<Linea> searchbyFactura(String codigoFactura) {
        try {
            return lineaDao.searchByFactura(codigoFactura);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

//    ------------------------Factura---------------------------------

//    public void create(Factura e) throws Exception{
//        Factura result = data.getFacturas().stream().filter(i->i.getCodigo().equals(e.getCodigo())).findFirst().orElse(null);
//        if (result==null) {
//            e.setCodigo(data.nextFactura());
//            create(e.getFecha());
//            data.getFacturas().add(e);
//        }
//        else throw new Exception("Factura ya existe (codigo)");
//    }
//
//    public List<Factura> search(Factura e){
//        return  data.getFacturas().stream()
//                .filter(i -> i.getNombreCliente().equals(e.getNombreCliente()))
//                .sorted(Comparator.comparing(Factura::getNombreCliente).thenComparing(Factura::getNombreCliente))
//                .collect(Collectors.toList());
//    }
    public void create(Factura e) throws Exception {
      facturaDao.create(e);
    }

    public Factura read(Factura e) throws Exception {
        return facturaDao.read(e.getCodigo());
    }

    public void update(Factura e) throws Exception {
        facturaDao.update(e);
    }

    public void delete(Factura e) throws Exception {
        facturaDao.delete(e);
    }

    public List<Factura> search(Factura e) {
        try {
            return facturaDao.search(e);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    //-------------------------------Calculos--------------------------------------

    public Double totalDelMes(String categoria, int anio, int mes){
        double total = 0d;

//        List<Factura> facturas = data.getFacturas().stream().filter(factura -> factura.getFecha().getAnio() == anio && factura.getFecha().getMes() == mes).toList();
//
//        for (Factura factura : facturas) {
//            total += factura.getTotalPorCategoria(categoria);
//        }

        return total;

    }

}
