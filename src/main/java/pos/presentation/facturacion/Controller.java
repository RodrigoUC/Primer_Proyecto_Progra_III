package pos.presentation.facturacion;

import pos.Application;
import pos.logic.*;

public class Controller {
    View view;
    Model model;

    public Controller(View view, Model model) {
        model.init(Service.instance().search(new Linea()), Service.instance().search(new Cajero()),Service.instance().search(new Cliente()));   //No se si para que se maneje mejor la actualizacion de las listas sea directamente la listas de Data
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
    }

    public Producto buscarProducto (String cod)throws Exception{
     Producto producto=new Producto();
     producto.setCodigo(cod);
     try{
         producto=Service.instance().read(producto);
         return producto;
     }
     catch(Exception e){
         throw new Exception("Producto no existe");
     }
    }
    public void save (Linea lin)throws Exception{
        Service.instance().create(lin);
        model.getListLinea().add(lin);
        search();
    }
    public void update(Linea linea)throws Exception{        //Talvez estos 2 metodos se pueden unir pero no entiendo bien como se actualiza el mode

    }
    public void search(){
//        model.setMode(Application.MODE_CREATE);
        model.setListLinea(model.getListLinea());
    }
    public void deleteAll(){
        model.getListLinea().clear();
        model.setListLinea(model.getListLinea());   //Asi se actualiza, aunque es como decir 1==1
    }
    public boolean listaLineasEstaVacia(){
        return model.getListLinea().isEmpty();
    }
    public boolean currentEsNulo(){
        return model.getCurrent() == null;
    }
    public void edit(int row){
        Linea linea=model.getListLinea().get(row);
        try{
//            model.setMode(Application.MODE_EDIT);
            model.setCurrent(linea);
        } catch (Exception ex) {}
    }
    public void delete(){
        try {
            Service.instance().delete(model.getCurrent());          //Se borra de la lista general de lineas y de la lista de la factura
            model.getListLinea().remove(model.getCurrent());
            model.setCurrent(null);
            model.setListLinea(model.getListLinea());
        }
        catch (Exception e) {}
    }
    void actualizarDescuento(int descuento){
        try {
            model.getCurrent().setDescuento((double)descuento/100);
            Service.instance().update(model.getCurrent());
            model.setListLinea(model.getListLinea());
        }
        catch (Exception e) {
        }
    }
    void actualizarCantidad(int cantidad){          //Recordar preguntar aca sobre existencias
        try {
            model.getCurrent().setCantidad(cantidad);
            Service.instance().update(model.getCurrent());
            model.setListLinea(model.getListLinea());
        }
        catch (Exception e) {}
    }

}
