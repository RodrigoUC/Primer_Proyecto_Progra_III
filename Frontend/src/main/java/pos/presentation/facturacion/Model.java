package pos.presentation.facturacion;

import pos.logic.*;
import pos.presentation.AbstractModel;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class Model extends AbstractModel {
    private List<Cajero> listCajeros;
    private List<Cliente> listClientes;
    Linea current;
    Factura factura;

    public Producto getFilter() {
        return filter;
    }

    public void setFilter(Producto filter) {
        this.filter = filter;
    }

    Producto filter;
    Producto actual;
    List<Producto> listProducto;

    public Producto getActual() {
        return actual;
    }

    public void setActual(Producto actual) {
        this.actual = actual;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
        firePropertyChange(CLIENTE);
        firePropertyChange(CAJERO);
        firePropertyChange(LISTLINEA);
    }

    public List<Producto> getListProducto() {
        return listProducto;
    }

    public void setListProducto(List<Producto> listProducto) {
        this.listProducto = listProducto;
        firePropertyChange(LISTPRODUCTO);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        super.addPropertyChangeListener(listener);
        firePropertyChange(LISTLINEA);
        firePropertyChange(LISTCLIENTE);
        firePropertyChange(LISTCAJERO);
        firePropertyChange(CURRENT);
        firePropertyChange(LISTPRODUCTO);
        firePropertyChange(FILTER);
        firePropertyChange(CLIENTE);
        firePropertyChange(CAJERO);
    }

    public Model() {
    }

    public void init(List<Cajero> listCajero, List<Cliente> listCliente) {
        setFactura(new Factura());
        setListCajeros(listCajero);
        setListClientes(listCliente);
        current = null;
        listProducto = new ArrayList<Producto>();
        filter = new Producto();
    }

    public void setCliente(Cliente cliente) {
        factura.setCliente(cliente);
        firePropertyChange(CLIENTE);
    }
    public void setCajero(Cajero cajero) {
        factura.setCajero(cajero);
        firePropertyChange(CAJERO);
    }

    public void setListCajeros(List<Cajero> list) {
        this.listCajeros = list;
        firePropertyChange(LISTCAJERO);
    }
    public void setListClientes(List<Cliente> list) {
        this.listClientes = list;
        firePropertyChange(LISTCLIENTE);
    }

    public Linea getCurrent() {
        return current;
    }

    public void setCurrent(Linea current) {
        this.current = current;
        firePropertyChange(CURRENT);
    }

    public List<Cajero> getCajeros() {
        return listCajeros;
    }

    public List<Cliente> getClientes() {
        return listClientes;
    }

    public void setLineas(List<Linea> list) {
        this.factura.setLineas(list);
        firePropertyChange(LISTLINEA);
    }

    public List<Linea> getLineas() {
        return factura.getLineas();
    }

    public static final String LISTLINEA = "listLinea";
    public static final String LISTCAJERO = "listCajero";
    public static final String LISTCLIENTE = "listCliente";
    public static final String CURRENT = "current";
    public static final String LISTPRODUCTO = "listProducto";
    public static final String FILTER = "filter";
    public static final String CAJERO = "cajero";
    public static final String CLIENTE = "cliente";

}

