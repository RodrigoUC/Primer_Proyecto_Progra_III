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
        model.init(Service.instance().search(new LineaHistorico()), model.getListLineasNormales(), model.getListFacturas(),Service.instance().search(new Cliente()));
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
            model.setListLineasListado(listadoHistorico());
            model.setListLineasNormales(listaLineasNormales());
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
            System.out.println(factura.getNombreCliente()); //busca la lista de factuas del cliente pero retorna lista vacia
            System.out.println(facturas.size());
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

    //-----------------LISTADO DE LINEAS DE FACTURAS DEL CLIENTE----------

    List<LineaHistorico> listadoHistorico() throws Exception {
        try{
            List<LineaHistorico> listado = new ArrayList<LineaHistorico>();
            for(Factura factura : model.getListFacturasFilter()){
                listado.add(new LineaHistorico(factura));
            }
            return listado;
        }catch (Exception e) {
            throw new Exception("Error listado historico");
        }

    }

    //--------------LISTA DE LINEAS NORMALES SEGUN LA CURRENT FACTURA--------

    List<Linea> listaLineasNormales() throws Exception {
        try{
            return model.getCurrentFactura().getVec();
        }catch (Exception e) {
            throw new Exception("Error lista lineas");
        }

    }



}