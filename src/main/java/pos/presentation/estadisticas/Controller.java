package pos.presentation.estadisticas;

import pos.Application;
import pos.logic.LineaEstadistica;
import pos.logic.Service;

public class Controller {

    private pos.presentation.estadisticas.View view;
    private pos.presentation.estadisticas.Model model;

    public Controller(View view, Model model){
        model.init(Service.instance().search(new LineaEstadistica()));
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
    }


    public void edit(int row) {
        LineaEstadistica e = model.getList().get(row);
        try{
            model.setMode(Application.MODE_EDIT);
            model.setCurrent(Service.instance().read(e));
        } catch (Exception ex) {}
    }

    public void search(LineaEstadistica filter) throws Exception {
        model.setFilter(filter);
        model.setMode(Application.MODE_CREATE);
        model.setCurrent(new LineaEstadistica());
        model.setList(Service.instance().search(model.getFilter()));
    }

}
