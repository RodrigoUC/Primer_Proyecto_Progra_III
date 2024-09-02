package pos.presentation.estadisticas;

import pos.Application;
import pos.logic.LineaEstadistica;
import pos.presentation.AbstractModel;
import java.beans.PropertyChangeListener;
import java.util.List;

public class Model extends AbstractModel {
    LineaEstadistica filter;
    List<LineaEstadistica> list;
    LineaEstadistica current;
    int mode;

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        super.addPropertyChangeListener(listener);
        firePropertyChange(LIST);
        firePropertyChange(CURRENT);
        firePropertyChange(FILTER);
    }

    public Model(){
    }

    public void init(List<LineaEstadistica> list){
        this.list = list;
        this.current = new LineaEstadistica();
        this.filter = new LineaEstadistica();
        this.mode = Application.MODE_CREATE;
    }

    public List<LineaEstadistica> getList() {
        return list;
    }

    public void setList(List<LineaEstadistica> list){
        this.list = list;
        firePropertyChange(LIST);
    }

    public LineaEstadistica getCurrent(){
        return current;
    }

    public void setCurrent(LineaEstadistica current){
        this.current = current;
        firePropertyChange(CURRENT);
    }

    public LineaEstadistica getFilter(){
        return filter;
    }

    public void setFilter(LineaEstadistica filter){
        this.filter = filter;
        firePropertyChange(FILTER);
    }

    public int getMode(){ return mode; }

    public void setMode(int mode){ this.mode = mode; }

    public static final String LIST = "list";
    public static final String CURRENT = "current";
    public static final String FILTER = "filter";


}
