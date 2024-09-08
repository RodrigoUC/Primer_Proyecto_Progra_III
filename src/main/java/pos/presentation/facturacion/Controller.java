package pos.presentation.facturacion;

import pos.Application;
import pos.logic.*;

import java.util.List;

public class Controller {
    View view;
    Model model;
    ViewBuscar viewBuscar;
    ViewCobrar viewCobrar;

    public Controller(View view, Model model) {
        model.init(Service.instance().search(new Linea()), Service.instance().search(new Cajero()), Service.instance().search(new Cliente()));
        this.view = view;
        this.model = model;
        this.viewBuscar = new ViewBuscar();
        this.viewCobrar = new ViewCobrar();
        view.setController(this);
        view.setModel(model);
        viewBuscar.setController(this);
        viewBuscar.setModel(model);
        viewCobrar.setController(this);

    }

    public Producto buscarProducto(String cod) throws Exception {
        Producto producto = new Producto();
        producto.setCodigo(cod);
        try {
            producto = Service.instance().read(producto);
            return producto;
        } catch (Exception e) {
            throw new Exception("Producto no existe");
        }
    }

    public void save(Linea lin) throws Exception {
//        Service.instance().create(lin);
        model.getListLinea().add(lin);
        model.setListLinea(model.getListLinea());
    }

    public void update(Linea linea) throws Exception {        //Talvez estos 2 metodos se pueden unir pero no entiendo bien como se actualiza el mode

    }

    public void deleteAll() {
        model.getListLinea().clear();
        model.setListLinea(model.getListLinea());   //Asi se actualiza, aunque es como decir 1==1
    }

    public boolean listaLineasEstaVacia() {
        return model.getListLinea().isEmpty();
    }

    public boolean currentEsNulo() {
        return model.getCurrent() == null;
    }

    public void edit(int row) {
        Linea linea = model.getListLinea().get(row);
        try {
            model.setCurrent(linea);
        } catch (Exception ex) {
        }
    }

    public void editProd(int row) {
        Producto producto = model.getListProducto().get(row);
        try {
            model.setActual(producto);
        } catch (Exception e) {
        }
    }

    public void delete() {
        try {
            Service.instance().delete(model.getCurrent());          //Se borra de la lista general de lineas y de la lista de la factura
            model.getListLinea().remove(model.getCurrent());
            model.setCurrent(null);
            model.setListLinea(model.getListLinea());
        } catch (Exception e) {
        }
    }

    void actualizarDescuento(int descuento) {
        try {
            model.getCurrent().setDescuento((double) descuento / 100);
//            Service.instance().update(model.getCurrent());
            model.setListLinea(model.getListLinea());
        } catch (Exception e) {
        }
    }

    void actualizarCantidad(int cantidad) {          //Recordar preguntar aca sobre existencias
        try {
            model.getCurrent().setCantidad(cantidad);
//            Service.instance().update(model.getCurrent());
            model.setListLinea(model.getListLinea());
        } catch (Exception e) {
        }
    }

    public void actualizarComboBox() {
        model.actualizarComboBoxClientes(Service.instance().search(new Cliente()));
        model.actualizarComboBoxCajeros(Service.instance().search(new Cajero()));
    }

    public void searchProducto(Producto filter) {
        model.setFilter(filter);
        model.setActual(null);
        model.setListProducto(Service.instance().search(model.getFilter()));
    }

    public ViewBuscar getViewBuscar() {
        model.setListProducto(Service.instance().search(new Producto()));
        return viewBuscar;
    }
    public ViewCobrar getViewCobrar() {
        return viewCobrar;
    }

    public boolean productoActualEsNulo() {
        return model.getActual() == null;
    }

    public void agregarProdctoActual() {
        Linea linea = new Linea(model.getActual(), 1, 0);
        model.getListLinea().add(linea);
        model.setListLinea(model.getListLinea());
        model.setActual(null);
    }

    public double total() {
        double aux = 0;
        for (Linea linea : model.getListLinea()) {
            aux += (linea.getProducto().getPrecio() * linea.getCantidad());
        }
        return aux;
    }
    public List<Linea> getListLinea() {
        return model.getListLinea();
    }
    public void guardarFactura(){
        try {
            Factura factura = view.take();
            model.init(Service.instance().search(new Linea()), Service.instance().search(new Cajero()), Service.instance().search(new Cliente()));
            for(Linea linea : factura.getVec()){
                Service.instance().create(linea);
            }
            Service.instance().create(factura);
        }
        catch(Exception e) {
        }
    }

}
