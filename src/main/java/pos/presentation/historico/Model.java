package pos.presentation.historico;

import pos.logic.*;
import pos.presentation.AbstractModel;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class Model extends AbstractModel {

    Cliente filter;
    List<Factura> listFacturasFilter;
    List<Linea> listLineasListado; //Pestana Listado //selecciona numero de factura, y abajo muestra las lineas con ese numero
    List<Linea> listLineasNormales; //que coinciden con id del nombre de la factura
    Linea currentLineaFactura;      //linea Listado

    public void setCurrentLineaFactura(Linea currentLineaFactura) {
        this.currentLineaFactura = currentLineaFactura;
    firePropertyChange(CURRENTLINEAFACTURA);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        super.addPropertyChangeListener(listener);
        firePropertyChange(LISTFACTURASFILTER);
        firePropertyChange(LISTLINEASLISTADO);
        firePropertyChange(LISTLINEASNORMALES);
        firePropertyChange(CURRENTLINEAFACTURA);
        firePropertyChange(FILTER);
    }

    public Model() {
    }

    public void init() {
        this.listLineasListado = new ArrayList<Linea>();
        this.listLineasNormales = new ArrayList<Linea>();
        this.listFacturasFilter = new ArrayList<Factura>();
        this.currentLineaFactura = new Linea();
        this.filter = new Cliente();
    }

    public List<Factura> getListFacturasFilter() {
        return listFacturasFilter;
    }
    public List<Linea> getListLineasListado() {return listLineasListado;}
    public List<Linea> getListLineasNormales() {return listLineasNormales;}
    public void setListFacturasFilter(List<Factura> listFacturasFilter) {
        this.listFacturasFilter = listFacturasFilter;
        firePropertyChange(LISTFACTURASFILTER);
    }
    public void setListLineasListado(List<Linea> Lineaslistado) {
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


    public Linea getCurrentFactura() {return currentLineaFactura;}

    public static final String LISTFACTURASFILTER = "listFacturasFilter";
    public static final String LISTLINEASLISTADO = "listLineasListado";
    public static final String LISTLINEASNORMALES = "listLineasNormales";
    public static final String FILTER = "filter";
    public static final String CURRENTLINEAFACTURA = "currentLineaFactura";

}