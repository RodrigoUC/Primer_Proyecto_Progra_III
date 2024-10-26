package pos.presentation.Login;

import pos.logic.Factura;
import pos.logic.Usuario;
import pos.presentation.AbstractModel;

import java.beans.PropertyChangeListener;
import java.util.List;

public class Model {
    private Usuario usuario;
    private boolean log;

    public Model() {
    }
    public void init(){
        log = false;
        usuario = null;
    }

    public boolean isLoged() {
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
}
