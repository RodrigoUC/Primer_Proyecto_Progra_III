package pos.presentation.historico;

import pos.Application;
import pos.logic.*;
import pos.presentation.AbstractModel;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class Model extends AbstractModel {

    Cliente filter;
    List<Factura> listFacturasFilter;
    List<LineaHistorico> listLineasListado; //Pestana Listado
    List<Linea> listLineasNormales; // Lineas normales segun una(1) Factura
    LineaHistorico currentLineaFactura;

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        super.addPropertyChangeListener(listener);
        firePropertyChange(LISTFACTURASFILTER);
        firePropertyChange(LISTLINEASLISTADO);
        firePropertyChange(LISTLINEASNORMALES);
        firePropertyChange(FILTER);
        firePropertyChange(CURRENTFACTURA);
    }

    public Model() {
    }

    public void init() {
        this.listLineasListado = new ArrayList<LineaHistorico>();
        this.listLineasNormales = new ArrayList<Linea>();
        this.listFacturasFilter = new ArrayList<Factura>();
        this.currentLineaFactura = new LineaHistorico();
        this.filter = new Cliente();
    }

    public List<Factura> getListFacturasFilter() {
        return listFacturasFilter;
    }
    public List<LineaHistorico> getListLineasListado() {return listLineasListado;}
    public List<Linea> getListLineasNormales() {return listLineasNormales;}
    public void setListFacturasFilter(List<Factura> listFacturasFilter) {
        this.listFacturasFilter = listFacturasFilter;
        firePropertyChange(LISTFACTURASFILTER);
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
    public void setCurrentLineaFacturaFactura(LineaHistorico currentListaFactura) {
        this.currentLineaFactura = currentListaFactura;
        firePropertyChange(CURRENTFACTURA);
    }
    public LineaHistorico getCurrentFactura() {return currentLineaFactura;}

    public static final String LISTFACTURASFILTER = "listFacturasFilter";
    public static final String LISTLINEASLISTADO = "listLineasListado";
    public static final String LISTLINEASNORMALES = "listLineasNormales";
    public static final String FILTER = "filter";
    public static final String CURRENTFACTURA = "currentFactura";
}