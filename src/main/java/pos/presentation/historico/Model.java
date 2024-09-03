package pos.presentation.historico;

import pos.Application;
import pos.logic.Cliente;
import pos.logic.Factura;
import pos.presentation.AbstractModel;
import java.beans.PropertyChangeListener;
import java.util.List;
//
public class Model extends AbstractModel {
    Cliente filter;
    List<Factura> listFacturas;
    List<Cliente> listLineas;
    Cliente current;
    int mode;
//
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        super.addPropertyChangeListener(listener);
        firePropertyChange(LISTFACTURAS);
        firePropertyChange(LISTLINEAS);
        firePropertyChange(CURRENT);
        firePropertyChange(FILTER);
    }
//
//    public Model() {
//    }
//
//    public void init(List<Cliente> listLineas, List<Factura> listFacturas) {
//        this.listLineas = listLineas;
//        this.listFacturas = listFacturas;
//        this.current = new Cliente();
//        this.filter = new Cliente();
//        this.mode = Application.MODE_CREATE;
//    }
//
    public List<Factura> getListFacturas() {
        return listFacturas;
    }
    public List<Cliente> getListLineas() {
        return listLineas;
    }
//
//    public void setListFacturas(List<Factura> listFacturas) {
//        this.listFacturas = listFacturas;
//        firePropertyChange(LISTFACTURAS);
//    }
//    public void setListLineas(List<Cliente> listLineas) {
//        this.listLineas = listLineas;
//    }
//
//    public Cliente getCurrent() {
//        return current;
//    }
//
//    public void setCurrent(Cliente current) {
//        this.current = current;
//        firePropertyChange(CURRENT);
//    }
//
//    public Cliente getFilter() {
//        return filter;
//    }
//
//    public void setFilter(Cliente filter) {
//        this.filter = filter;
//        firePropertyChange(FILTER);
//    }
//
//    public int getMode() {
//        return mode;
//    }
//
//    public void setMode(int mode) {
//        this.mode = mode;
//    }
//
    public static final String LISTFACTURAS = "listFacturas";
    public static final String LISTLINEAS = "listLineas";
    public static final String CURRENT = "current";
    public static final String FILTER = "filter";
}