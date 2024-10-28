package pos.logic;

import pos.Data.*;

import pos.Data.CategoriaDao;
import pos.Data.ProductoDao;
import pos.logic.Rango;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Service implements IService {
//    private static Service theInstance;

//    public static Service instance(){
//        if (theInstance == null) theInstance = new Service();
//        return theInstance;
//    }
    private ProductoDao productoDao;
    private CategoriaDao categoriaDao;
    private ClienteDao clienteDao;
    private CajeroDao cajeroDao;
    private pos.data.FacturaDao facturaDao;
    private pos.data.LineaDao lineaDao;
    private UsuarioDao usuarioDao;

    public Service(){
        try{
            categoriaDao = new CategoriaDao();
            productoDao = new ProductoDao();
            clienteDao = new ClienteDao();
            cajeroDao = new CajeroDao();
            facturaDao = new pos.data.FacturaDao();
            lineaDao = new pos.data.LineaDao();
            usuarioDao = new UsuarioDao();
        }
        catch(Exception e){

        }
    }
    public void stop(){
    }

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


    //    -------------------Lineas----------------------------------------------

    public void create(Linea e) throws Exception {
        lineaDao.create(e);
    }

    public Linea read(Linea e) throws Exception {
        //return lineaDao.read(e.getCodigo());
        return null;
    }

    public void update(Linea e) throws Exception {
    }

    public void delete(Linea e) throws Exception {

    }

    public List<Linea> search(Linea e) {
        try {
            return lineaDao.search(e);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Linea> searchbyFactura(int i) {
        try {
            return lineaDao.searchByFactura(i);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

//    ------------------------Factura---------------------------------

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
            return facturaDao.searchByClienteID(e);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    //-------------------------------Usuario--------------------------------------
    public Usuario read(Usuario e) throws Exception {
        return usuarioDao.read(e.getID(),e.getContrasena());
    }

    //-------------------------------Calculos--------------------------------------

    public Float[][] estadisticas(List<Categoria> rows, List<String> cols, Rango rango){
        try{
            return lineaDao.estadisticas(rows,cols,rango);
        }catch(Exception e){
            throw new RuntimeException();
        }
    }
}
