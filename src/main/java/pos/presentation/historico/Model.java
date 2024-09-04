package pos.presentation.historico;

import pos.Application;
import pos.logic.*;
import pos.presentation.AbstractModel;

import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.util.List;

public class Model extends AbstractModel {
    Cliente filter;
    List<Factura> listFacturas;
    List<Cliente> listClientes;
    List<LineaHistorico> listLineasListado; //Pestana Listado
    LineaHistorico current;
    int mode;

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        super.addPropertyChangeListener(listener);
        firePropertyChange(LISTFACTURAS);
        firePropertyChange(LISTCLIENTES);
        firePropertyChange(LISTLINEASLISTADO);
        firePropertyChange(CURRENT);
        firePropertyChange(FILTER);
    }

    public Model() {
    }

    public void init(List<LineaHistorico> listLineasListado, List<Factura> listFacturas, List<Cliente> listClientes) {
        this.listLineasListado = listLineasListado;
        this.listFacturas = listFacturas;
        this.listClientes = listClientes;
        current=new LineaHistorico();

    }



    public List<Factura> getListFacturas() {
        return listFacturas;
    }
    public List<Cliente> getListClientes() {return listClientes;}
    public List<LineaHistorico> getListLineasListado() {return listLineasListado;}



    public static final String LISTFACTURAS = "listFacturas";
    public static final String LISTCLIENTES = "listClientes";
    public static final String LISTLINEASLISTADO = "listLineasListado";
    public static final String CURRENT = "current";
    public static final String FILTER = "filter";
}