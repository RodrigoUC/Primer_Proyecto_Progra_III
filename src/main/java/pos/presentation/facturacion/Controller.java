package pos.presentation.facturacion;

import pos.logic.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    View view;
    Model model;
    ViewBuscar viewBuscar;
    ViewCobrar viewCobrar;

    public Controller(View view, Model model) {
        try {
            model.init(new ArrayList<>(), Service.instance().search(new Cajero()), Service.instance().search(new Cliente()));
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
        catch(Exception e) {
        }
    }

    public Producto buscarProducto(String cod) throws Exception {
        Producto producto = new Producto();
        producto.setCodigo(cod);
        try {
            producto = Service.instance().read(producto);
            if(prodYaEstaAgregado(producto)){
                throw new IllegalArgumentException("Producto ya esta agregado en la factura");
            }
            if(producto.getExistencia() <= 0){
                throw new IllegalArgumentException("No hay suficientes existencias de ese producto");
            }
                return producto;

        }
        catch(IllegalArgumentException e){
            throw e;
        }
        catch (Exception e) {
            throw new Exception("Producto no existe");
        }
    }

    public void save(Linea lin) throws Exception {
        model.getListLinea().add(lin);
        model.setListLinea(model.getListLinea());
    }
    boolean prodYaEstaAgregado(Producto producto){
        if(listaLineasEstaVacia()){
            return false;
        }
        for(Linea lin : model.getListLinea()){
            if(producto == lin.getProducto()){
               return true;
            }
        }
        return false;
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
            if (!currentEsNulo()) {
                model.getListLinea().remove(model.getCurrent());
                model.setCurrent(null);
                model.setListLinea(model.getListLinea());
            }
        }
            catch(Exception e){}
    }

    void actualizarDescuento(int descuento) {
        try {
            model.getCurrent().setDescuento(descuento);
            model.setCurrent(null);
            model.setListLinea(model.getListLinea());
        } catch (Exception e) {
        }
    }

    void actualizarCantidad(int cantidad) throws Exception{
        try {
            if(cantidad > model.getCurrent().getProducto().getExistencia()){
    throw new Exception("No hay suficientes existencias de ese producto");
            }
            model.getCurrent().setCantidad(cantidad);
            model.setCurrent(null);
            model.setListLinea(model.getListLinea());
        } catch (Exception e) {
throw e;
        }
    }

    public void actualizarComboBox() {
        model.setListClientes(Service.instance().search(new Cliente()));
        model.setListCajeros(Service.instance().search(new Cajero()));
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
    public boolean productoActualEsNulo() {
        return model.getActual() == null;
    }

    public void agregarProdctoActual(boolean opcion,double desc)throws Exception {
        if(opcion && !productoActualEsNulo()) {
            if(prodYaEstaAgregado(model.getActual())){
                model.setActual(null);
                throw new Exception("Producto ya esta agregado en la factura");
            }
            if(model.getActual().getExistencia() <= 0){
                throw new Exception("No hay suficientes existencias de ese producto");
            }
            Linea linea = new Linea(model.getActual(), 1, desc);
            model.getListLinea().add(linea);
            model.setListLinea(model.getListLinea());
            model.setActual(null);
        }
        else{
            model.setActual(null);
        }
    }

    public Double total() {
        Double aux = 0.0;
        for (Linea linea : model.getListLinea()) {
            aux += (linea.getProducto().getPrecio() * linea.getCantidad())-(linea.getProducto().getPrecio() * linea.getCantidad()*(linea.getDescuento()/100));
        }
        return aux;
    }
    public List<Linea> getListLinea() {
        return model.getListLinea();
    }
    String pedirDescuento(){
        ImageIcon icono = new ImageIcon(getClass().getResource("/pos/presentation/icons/descuento.png"));
        String texto = (String) JOptionPane.showInputDialog(null, "Descuento?", model.getCurrent().getProducto().getDescripcion(), JOptionPane.PLAIN_MESSAGE, icono, null, "");
    return texto;
    }
    public void deleteAll(){
        if(!listaLineasEstaVacia()) {
            int confirm = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea eliminar todas las lineas de la factura?", "Cancelar", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                model.getListLinea().clear();
                model.setListLinea(model.getListLinea());
            }
        }
    }
    public void cobrar(){
        if(!listaLineasEstaVacia()) {
            viewCobrar.Cobrar();
        }
    }


    String pedirCantidad(){
        ImageIcon icono = new ImageIcon(getClass().getResource("/pos/presentation/icons/cantidad.png"));
        String texto = (String) JOptionPane.showInputDialog(null, "Cantidad?", model.getCurrent().getProducto().getDescripcion(), JOptionPane.PLAIN_MESSAGE, icono, null, "");
   return texto;
    }
    public void guardarFactura(){
        try {
            Factura factura = view.take();
            factura.setVec(getListLinea());
            model.init(new ArrayList<>(), Service.instance().search(new Cajero()), Service.instance().search(new Cliente()));
            for(Linea linea : factura.getVec()){
                linea.getProducto().setExistencia(linea.getProducto().getExistencia()-linea.getCantidad()); //Le quita la cantidad que se compraron a existencias
                Service.instance().create(linea);
            }

            Service.instance().create(factura);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public Integer getCantidadProductos(){
        int cantidad = 0;
        if(!listaLineasEstaVacia()) {
            for (Linea linea : model.getListLinea()) {
            cantidad += linea.getCantidad();
            }
        }
        return cantidad;
    }
    public Double getDescuentoTotal(){
        Double descuento = 0.0;
        if(!listaLineasEstaVacia()) {
            for (Linea linea : model.getListLinea()) {
             descuento += (linea.getDescuento()/100)*linea.getCantidad()*linea.getProducto().getPrecio();
            }
        }
        return descuento;
    }

    public void actualizarCliente(Cliente selectedItem) {
        this.model.getCurrentFactura().setCliente(selectedItem);
    }
    public void actualizarCajero(Cajero selectedItem) {
        this.model.getCurrentFactura().setCajero(selectedItem);
    }
}
