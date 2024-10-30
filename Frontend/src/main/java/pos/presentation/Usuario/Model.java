package pos.presentation.Usuario;

import pos.logic.Usuario;
import pos.presentation.AbstractModel;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class Model extends AbstractModel {
    private List<Usuario> usuarios;
    private Usuario current;

    public Model() {
    }
    public void init (){
        usuarios = new ArrayList<>();
        current = null;
        firePropertyChange(LIST);
    }
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        super.addPropertyChangeListener(listener);
        firePropertyChange(LIST);
    }
    public List<Usuario> getUsuarios() {
        return usuarios;
    }
    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
        current = null;
        firePropertyChange(LIST);
    }
    public Usuario getCurrent() {
        return current;
    }
    public void setCurrent(Usuario current) {
        this.current = current;
    }

    public static final String LIST="list";
}
