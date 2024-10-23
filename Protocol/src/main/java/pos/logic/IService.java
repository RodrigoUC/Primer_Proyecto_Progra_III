package pos.logic;

import java.util.List;

public interface IService {
    //================= CAJERO ==============
    public void create(Cajero e) throws Exception;

    public Cajero read(Cajero e) throws Exception;

    public void update(Cajero e) throws Exception;

    public void delete(Cajero e) throws Exception;

    public List<Cajero> search(Cajero e);

    //================= CLIENTE ==============
    public void create(Cliente e) throws Exception;

    public Cliente read(Cliente e) throws Exception;

    public void update(Cliente e) throws Exception;

    public void delete(Cliente e) throws Exception;

    public List<Cliente> search(Cliente e);

    //================= PRODUCTOS ============
    public void create(Producto e) throws Exception;

    public Producto read(Producto e) throws Exception;

    public void update(Producto e) throws Exception;

    public void delete(Producto e) throws Exception;

    public List<Producto> search(Producto e);

    //================= CATEGORIAS ============
    public List<Categoria> search(Categoria e);



    //================= LINEAS ============

    public void create(Linea e) throws Exception;

    public Linea read(Linea e) throws Exception;

    public void update(Linea e) throws Exception;

    public void delete(Linea e) throws Exception;

    public List<Linea> search(Linea e);

    public List<Linea> searchbyFactura(int codigoFactura);


    //================= FACTURAS ============

    public void create(Factura e) throws Exception;

    public Factura read(Factura e) throws Exception;

    public void update(Factura e) throws Exception;

    public void delete(Factura e) throws Exception;

    public List<Factura> search(Factura e);

}
