package pos.presentation.historico;

import pos.Application;
import pos.logic.*;
import pos.presentation.historico.Model;
import pos.presentation.historico.View;

import javax.sound.sampled.Line;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


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


    //retorna una lista de facturas del cliente, segun su nombre
    //y la setea en el model

    public void search(Cliente filter) throws Exception {
        model.setFilter(filter);
        model.setMode(Application.MODE_CREATE);
        model.setCurrent(new Cliente());
        try{
            listaFacturasCliente(); //AKA: model.setListFacturasFilter
        }
        catch(Exception e){
            throw new Exception("Cliente no tiene Facturas");
        }
    }

    //AKA: setea el filter de Facturas con el filter de Cliente
    public void listaFacturasCliente ()throws Exception{
        Factura factura = new Factura();
        factura.setCliente(model.getFilter()); //como un facturaFilter
        List<Factura> listFacturasCliente;
        try{
            listFacturasCliente=Service.instance().search(factura);
            model.setListFacturasFilter(listFacturasCliente);
        }
        catch(Exception e){
            throw new Exception("Cliente no tiene Facturas");
        }
    }

    //--------------------LISTADO DE LAS FACTURAS DEL CLIENTE---------------
    //lista LineaHistorico a partir de listaFacturasCliente
    //cada LineaHistorico se hace con el numero/codigo de Factura, sacado de listaFacturasCliente

    void listadoFacturasCliente(String nombreCliente)throws Exception{
        List<LineaHistorico> listadoHistorico = new ArrayList<LineaHistorico>();
        try {
            int can = model.getListFacturas().size()-1;
            for(int i=0;i<can;i++){
                listadoHistorico.add(new LineaHistorico(model.getListFacturasFilter().get(i)));
            }

        } catch (Exception e) {
            throw new Exception("Cliente no tiene Facturas");
        }
        model.setListLineasListado(listadoHistorico);
    }

    //-----------------LISTA DE LINEAS DE UNA (1) FACTURA DEL CLIENTE----------
    void lineasFacturaCliente(Factura factura)throws Exception{
        List<Linea> listLineas = new ArrayList<Linea>(Arrays.asList(factura.getVec()));
        model.setListLineasNormales(listLineas);
    }


//    public void save ()throws Exception{
//
//    }
}