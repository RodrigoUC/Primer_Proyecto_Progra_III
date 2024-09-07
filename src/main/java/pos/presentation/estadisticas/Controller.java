package pos.presentation.estadisticas;

import pos.Application;
import pos.logic.Fecha;
import pos.logic.Categoria;
import pos.logic.LineaEstadistica;
import pos.logic.Factura;
import pos.logic.Service;

import java.util.ArrayList;
import java.util.List;

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

    public List<Factura> buscarFacturas() throws Exception {
        Factura fact = view.takeFactura();
        Categoria categ = view.takeCategoria();
        try {
            List<Factura> facturas = Service.instance().readList(fact, categ);
            return facturas;
        } catch (Exception ex) {
            throw new Exception("No existe la factura");
        }
    }

    public Double getTotalVendido() throws Exception {
        try {
            List<Factura> facturas = buscarFacturas();
            Double total = 0.0;
            for (Factura factura : facturas) {
                total += factura.getTotal();
            }
            return total;
        } catch (Exception ex){
            throw new Exception("No existe");
        }
    }

    public void guardarLineaEstadistica(){
        try {
            LineaEstadistica estadistica =view.take();
            Service.instance().create(estadistica);
        }
        catch(Exception e) {
        }
    }

}
