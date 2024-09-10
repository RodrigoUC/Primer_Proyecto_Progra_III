package pos.presentation.historico;

import pos.Application;
import pos.logic.*;
import pos.presentation.AbstractModel;

import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.util.List;

public class Model extends AbstractModel {
    Cliente filter;
    List<Factura> listFacturasFilter;
    List<Factura> listFacturas;
    List<Cliente> listClientes;
    List<LineaHistorico> listLineasListado; //Pestana Listado
    List<Linea> listLineasNormales; // Lineas normales segun una(1) Factura
    Cliente current;
    Factura currentFactura;
    int mode;

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        super.addPropertyChangeListener(listener);
        firePropertyChange(LISTFACTURASFILTER);
        firePropertyChange(LISTCLIENTES);
        firePropertyChange(LISTLINEASLISTADO);
        firePropertyChange(LISTLINEASNORMALES);
        firePropertyChange(CURRENT);
        firePropertyChange(FILTER);
        firePropertyChange(CURRENTFACTURA);
    }

    public Model() {
    }

    public void init(List<LineaHistorico> listLineasListado, List<Linea> listLineasNormales, List<Factura> listFacturas, List<Cliente> listClientes) {
        this.listLineasListado = listLineasListado;
        this.listLineasNormales = listLineasNormales;
        this.listFacturas = listFacturas;
        this.listClientes = listClientes;
        this.listFacturasFilter = listFacturas;
        this.current = new Cliente();
        this.filter = new Cliente();
        this.currentFactura = new Factura();
        this.mode = Application.MODE_CREATE;
    }



    public List<Factura> getListFacturas() {
        return listFacturas;
    }
    public List<Factura> getListFacturasFilter() {
        return listFacturasFilter;
    }
    public List<Cliente> getListClientes() {return listClientes;}
    public List<LineaHistorico> getListLineasListado() {return listLineasListado;}
    public List<Linea> getListLineasNormales() {return listLineasNormales;}

    public void setListFacturas(List<Factura> listFacturas) {
        this.listFacturas = listFacturas;
    firePropertyChange(LISTFACTURAS);
    }
    public void setListFacturasFilter(List<Factura> listFacturasFilter) {
        this.listFacturasFilter = listFacturasFilter;
        firePropertyChange(LISTFACTURASFILTER);
    }

    public void setListClientes(List<Cliente> listClientes) {
        this.listClientes = listClientes;
        firePropertyChange(LISTCLIENTES);
    }

    public void setListLineasListado(List<LineaHistorico> Lineaslistado) {
        this.listLineasListado = Lineaslistado;
        firePropertyChange(LISTLINEASLISTADO);
    }
    public void setListLineasNormales(List<Linea> listLineasNormales) {
        this.listLineasNormales = listLineasNormales;
        firePropertyChange(LISTLINEASNORMALES);
    }

    public Cliente getFilter() {
        return filter;
    }

    public void setFilter(Cliente filter) {
        this.filter = filter;
        firePropertyChange(FILTER);
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public Cliente getCurrent() {
        return current;
    }

    public void setCurrent(Cliente current) {
        this.current = current;
        firePropertyChange(CURRENT);
    }

    public void setCurrentFactura(Factura currentFactura) {
        this.currentFactura = currentFactura;
        firePropertyChange(CURRENTFACTURA);
    }
    public Factura getCurrentFactura() {return currentFactura;}

    public static final String LISTFACTURASFILTER = "listFacturasFilter";
    public static final String LISTFACTURAS = "listFacturas";
    public static final String LISTCLIENTES = "listClientes";
    public static final String LISTLINEASLISTADO = "listLineasListado";
    public static final String LISTLINEASNORMALES = "listLineasNormales";
    public static final String CURRENT = "current";
    public static final String FILTER = "filter";
    public static final String CURRENTFACTURA = "currentFactura";
}