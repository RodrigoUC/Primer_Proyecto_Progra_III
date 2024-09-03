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
         cajeros=new DefaultComboBoxModel<Cajero>();
         clientes=new DefaultComboBoxModel<Cliente>();

       for(Cliente cliente: listCliente){
           clientes.addElement(cliente);
       }
       for(Cajero cajero: listCajero){
            cajeros.addElement(cajero);
        }
    }

    public List<Linea> getListLinea() {
        return listLinea;
    }

    public void setListLinea(List<Linea> list) {
        this.listLinea = list;
        firePropertyChange(LISTLINEA);
    }



    public static final String LISTLINEA = "listLinea";
    public static final String LISTCAJERO = "listCajero";
    public static final String LISTCLIENTE = "listCliente";
}
