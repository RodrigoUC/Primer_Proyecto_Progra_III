package pos.presentation.facturacion;

import pos.Application;
import pos.logic.Cajero;
import pos.logic.Linea;
import pos.logic.Cliente;
import pos.logic.Producto;
import pos.presentation.AbstractModel;

import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.util.List;

public class Model extends AbstractModel {
    private List<Linea> listLinea;
    private List<Cajero> listCajeros;
    private List<Cliente> listClientes;
    DefaultComboBoxModel<Cajero> cajeros  ;
    DefaultComboBoxModel<Cliente> clientes;
    Linea current;
//    int mode;

    //View Buscar
    Producto filter;
    List<Producto> list;


    public void addPropertyChangeListener(PropertyChangeListener listener) {
        super.addPropertyChangeListener(listener);
        firePropertyChange(LISTLINEA);
        firePropertyChange(LISTCLIENTE);
        firePropertyChange(LISTCAJERO);
        firePropertyChange(CURRENT);
    }

    public Model() {
    }

    public void init(List<Linea> listLinea,List<Cajero> listCajero, List<Cliente> listCliente) {
        this.listLinea = listLinea;
        this.listCajeros = listCajero;
        this.listClientes = listCliente;
        cajeros=new DefaultComboBoxModel<Cajero>();
        clientes=new DefaultComboBoxModel<Cliente>();
        current=null;
//        this.mode = Application.MODE_CREATE;

        if(listCliente != null) {
            for (Cliente cliente : listCliente) {
                clientes.addElement(cliente);
            }
        }
        if(listCajero != null) {
            for (Cajero cajero : listCajero) {
                cajeros.addElement(cajero);
            }
        }
    }

    public List<Linea> getListLinea() {
        return listLinea;
    }

    public void setListLinea(List<Linea> list) {
        this.listLinea = list;
        firePropertyChange(LISTLINEA);
    }

    public void setListCajeros(List<Cajero> list) {
        this.listCajeros = list;
        firePropertyChange(LISTCAJERO);
    }
    public List<Cajero> getListCajeros() {
        return listCajeros;
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

//    public int getMode() {
//        return mode;
//    }
//
//    public void setMode(int mode) {
//        this.mode = mode;
//    }

    public List<Cliente> getListClientes() {
        return listClientes;
    }
    public DefaultComboBoxModel<Cajero> getCajeros() {
        return cajeros;
    }

    public DefaultComboBoxModel<Cliente> getClientes() {
        return clientes;
    }

    public void actualizarComboBoxCajeros(List<Cajero> lis){
        cajeros.removeAllElements();
        listCajeros=lis;
        for(Cajero cajero: listCajeros){
            cajeros.addElement(cajero);
        }
    }

    public void actualizarComboBoxClientes(List<Cliente> lis) {
        clientes.removeAllElements();
        listClientes=lis;
        for(Cliente cliente: listClientes){
            clientes.addElement(cliente);
        }
    }

    public static final String LISTLINEA = "listLinea";
    public static final String LISTCAJERO = "listCajero";
    public static final String LISTCLIENTE = "listCliente";
    public static final String CURRENT = "current";
}

