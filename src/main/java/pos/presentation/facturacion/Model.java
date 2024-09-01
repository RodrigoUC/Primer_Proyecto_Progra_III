package pos.presentation.facturacion;

import pos.Application;
import pos.logic.Cajero;
import pos.logic.Linea;
import pos.logic.Cliente;
import pos.presentation.AbstractModel;

import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class Model extends AbstractModel {
   private List<Linea> listLinea;
   private List<Cajero> listCajero;
   private List<Cliente> listCliente;

//DefaultComboBoxModel cajeros       //Para acordarme
//DefaultComboBoxModel clientes

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        super.addPropertyChangeListener(listener);
        firePropertyChange(LISTLINEA);
    }

    public Model() {
    }

    public void init(List<Linea> listLinea,List<Cajero> listCajero, List<Cliente> listCliente) {
         this.listLinea = listLinea;
        this.listCajero = listCajero;
        this.listCliente = listCliente;
    }

    public List<Linea> getListLinea() {
        return listLinea;
    }

    public void setListLinea(List<Linea> list) {
        this.listLinea = list;
        firePropertyChange(LISTLINEA);
    }

    public List<Cajero> getListCajero() {
        return listCajero;
    }

    public void setListCajero(List<Cajero> listCajero) {
        this.listCajero = listCajero;
        firePropertyChange(LISTCAJERO);
    }

    public List<Cliente> getListCliente() {
        return listCliente;
    }

    public void setListCliente(List<Cliente> listCliente) {
        this.listCliente = listCliente;
        firePropertyChange(LISTCLIENTE );
    }

    public static final String LISTLINEA = "listLinea";
    public static final String LISTCAJERO = "listCajero";
    public static final String LISTCLIENTE = "listCliente";
}
