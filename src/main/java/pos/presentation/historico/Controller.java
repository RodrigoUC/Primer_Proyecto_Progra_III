package pos.presentation.historico;

import pos.Application;
import pos.logic.*;
import pos.presentation.historico.Model;
import pos.presentation.historico.View;


public class Controller {
    View view;
    Model model;

    public Controller(View view, Model model) {
        model.init(Service.instance().search(new LineaHistorico()), model.getListFacturas(),Service.instance().search(new Cliente()));
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
    }

    //hacer un read que reciba el String de nombre y busque sus facturas
    //retorna una lista de facturas? y de ahi saca la List<> de LineaHistorico que reciben (Factura)
//    public Factura buscarFacturaCliente (String nombreCliente)throws Exception{
//
//    }
//    public void save (Producto producto)throws Exception{
//
//    }
}