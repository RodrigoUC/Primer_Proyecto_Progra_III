package pos.presentation.Usuario;

import pos.logic.Cliente;
import pos.logic.Factura;
import pos.logic.Usuario;
import pos.presentation.AbstractModel;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class Model extends AbstractModel {
    private List<Factura> listaFacturas;
    private Usuario usuario;
    private boolean log;


    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        super.addPropertyChangeListener(listener);

    }

    public Model() {
    }
    public void init(){
        log = false;
        listaFacturas = new ArrayList<Factura>();
        usuario = null;
    }

    public boolean isLog() {
        return log;
    }

    public void setLog(boolean log) {
        this.log = log;
    }

    public void setUsuario(Usuario u) {
        this.usuario = u;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public List<Factura> getListaFacturas() {
        return listaFacturas;
    }
    public void setListaFacturas(List<Factura> listaFacturas) {
        this.listaFacturas = listaFacturas;
    }
}
