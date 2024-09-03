package pos.presentation.facturacion;

import pos.logic.Cajero;
import pos.logic.Linea;
import pos.logic.Cliente;
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

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        super.addPropertyChangeListener(listener);
        firePropertyChange(LISTLINEA);
    }

    public Model() {
    }

    public void init(List<Linea> listLinea,List<Cajero> listCajero, List<Cliente> listCliente) {
        this.listLinea = listLinea;
        this.listCajeros = listCajero;
        this.listClientes = listCliente;
        cajeros=new DefaultComboBoxModel<Cajero>();
        clientes=new DefaultComboBoxModel<Cliente>();

        if(listCliente != null) {
            for (Cliente cliente : listCliente) {
                clientes.addElement(cliente);
            }
        }
        if(listLinea != null) {
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
    }
    public List<Cajero> getListCajeros() {
        return listCajeros;
    }
    public void setListClientes(List<Cliente> list) {
        this.listClientes = list;
    }
    public List<Cliente> getListClientes() {
        return listClientes;
    }
    public DefaultComboBoxModel<Cajero> getCajeros() {
        return cajeros;
    }

    public DefaultComboBoxModel<Cliente> getClientes() {
        return clientes;
    }

    public void actualizarComboBox(){
        clientes.removeAllElements();
        cajeros.removeAllElements();
        for(Cajero cajero: listCajeros){
            cajeros.addElement(cajero);
        }
        for(Cliente cliente: listClientes){
            clientes.addElement(cliente);
        }
    }

    public static final String LISTLINEA = "listLinea";
    public static final String LISTCAJERO = "listCajero";
    public static final String LISTCLIENTE = "listCliente";
}

