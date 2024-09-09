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

    public void editFacturas(int row){
        Factura e = model.getListFacturasFilter().get(row); // o getListFacturas()
        try {
            model.setMode(Application.MODE_EDIT);
            model.setCurrentFactura(Service.instance().read(e));
        } catch (Exception ex) {}
    }

    public void search(Cliente filter) throws Exception {
        model.setFilter(filter);
        model.setMode(Application.MODE_CREATE);
        try{
            //buscarCliente(filter.getNombre());
            model.setCurrent(model.getFilter());
            model.setListFacturasFilter(buscarFacturas());
        }
        catch(Exception e){
            throw new Exception("Cliente no tiene Facturas");
        }
    }

    public List<Factura> buscarFacturas() throws Exception {
        List<Factura> facturas = new ArrayList<Factura>();
        Factura factura = new Factura();
        try {
            factura.setCliente(model.getFilter()); //antes getCurrent, testear
            facturas = Service.instance().search(factura);
            return facturas;

        } catch (Exception e) {
            throw new Exception("No hay facturas del cliente");
        }

    }

    Cliente buscarCliente(String nombre) throws Exception {
        Cliente cliente = new Cliente();
        cliente.setNombre(nombre);
        try {
            //cliente = Service.instance().read(cliente);
            cliente = Service.instance().readNombre(cliente);
            //cliente = (Cliente) Service.instance().search(cliente);
            return cliente;
        } catch (Exception e) {
            throw new Exception("Cliente no existe");
        }
    }

    //-----------------LISTA DE LINEAS DE UNA (1) FACTURA DEL CLIENTE----------




}