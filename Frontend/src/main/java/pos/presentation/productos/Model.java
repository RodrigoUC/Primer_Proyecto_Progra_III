package pos.presentation.productos;

import pos.Application;
import pos.logic.Categoria;
import pos.logic.Producto;
import pos.presentation.AbstractModel;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class Model extends AbstractModel {

    Producto filter;
    List<Producto> list;
    List<Categoria> categorias;
    Producto current;
    int mode;

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        super.addPropertyChangeListener(listener);
        firePropertyChange(LIST);
        firePropertyChange(CURRENT);
        firePropertyChange(FILTER);
        firePropertyChange(CATEGORIAS);
    }

    public Model() {
    }

    public void init() {
        filter = new Producto();
        List<Producto> rows = new ArrayList<Producto>();
        this.setList(rows);
        mode= Application.MODE_CREATE;
    }

    public List<Producto> getList() {
        return list;
    }

    public void setList(List<Producto> list) {
        this.list = list;
        firePropertyChange(LIST);
        setCurrent(new Producto());
    }

    public Producto getCurrent() {
        return current;
    }

    public void setCurrent(Producto current) {
        this.current = current;
        firePropertyChange(CURRENT);
    }

    public Producto getFilter() {
        return filter;
    }

    public void setFilter(Producto filter) {
        this.filter = filter;
        firePropertyChange(FILTER);
    }

    public int getMode() {
        return mode;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
        firePropertyChange(CATEGORIAS);
    }

    public int getIndexCategorias(){
        for (int i = 0; i < categorias.size(); i++){
            if(categorias.get(i).getNombre() == current.getCategoria().getNombre()){
                return i;
            }
        }
        return -1;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }
    public static final String LIST = "list";
    public static final String CURRENT = "current";
    public static final String CATEGORIAS="categorias";
    public static final String FILTER = "filter";
}
