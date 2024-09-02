package pos.presentation.estadisticas;

import pos.Application;
import pos.logic.LineaEstadistica;
import pos.logic.Service;
import pos.presentation.estadisticas.Model;
import pos.presentation.estadisticas.View;

public class Controller {

    private pos.presentation.estadisticas.View view;
    private pos.presentation.estadisticas.Model model;

    public Controller(View view, Model model){
//        model.init(Service.instance().search(new LineaEstadistica()));
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

}
