package pos.presentation.facturacion;

import pos.logic.*;

import javax.swing.*;
import java.util.List;

public class Controller {
    View view;
    Model model;
    ViewBuscar viewBuscar;
    ViewCobrar viewCobrar;

    public Controller(View view, Model model) {
        try {
            this.view = view;
            this.model = model;
            model.init(Service.instance().search(new Cajero()), Service.instance().search(new Cliente()));
            this.viewBuscar = new ViewBuscar();
            this.viewCobrar = new ViewCobrar();
            view.setController(this);
            view.setModel(model);
            viewBuscar.setController(this);
            viewBuscar.setModel(model);
            viewCobrar.setController(this);
            viewCobrar.setModel(model);
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
        model.getLineas().add(lin);
        model.setLineas(model.getLineas());
    }
    boolean prodYaEstaAgregado(Producto producto){
        if(listaLineasEstaVacia()){
            return false;
        }
        for(Linea lin : model.getLineas()){
            if(producto.getCodigo().equals(lin.getProducto().getCodigo())){
               return true;
            }
        }
        return false;
    }

    public boolean listaLineasEstaVacia() {
        return model.getLineas().isEmpty();
    }

    public boolean currentEsNulo() {
        return model.getCurrent() == null;
    }

    public void edit(int row) {
        Linea linea = model.getLineas().get(row);
        try {
            model.setCurrent(linea);
        } catch (Exception ex) {
        }
    }
    public Factura transferirFactura() {
        Factura factura = model.factura;
        model.setFactura(new Factura());
        return factura;
    }
    public void recibirFactura(Factura factura) {
        // Actualiza por si se han agregado nuevos
        model.setListCajeros(Service.instance().search(new Cajero()));
        model.setListClientes(Service.instance().search(new Cliente()));
        model.setFactura(factura);
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
                model.getLineas().remove(model.getCurrent());
                model.setCurrent(null);
                model.setLineas(model.getLineas());
            }
        }
            catch(Exception e){}
    }

    void actualizarDescuento(int descuento) {
        try {
            model.getCurrent().setDescuento(descuento);
            model.setCurrent(null);
            model.setLineas(model.getLineas());
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
            model.setLineas(model.getLineas());
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

    public void agregarProdctoActual(boolean opcion,float desc)throws Exception {
        if(opcion && !productoActualEsNulo()) {
            if(prodYaEstaAgregado(model.getActual())){
                model.setActual(null);
                throw new Exception("Producto ya esta agregado en la factura");
            }
            if(model.getActual().getExistencia() <= 0){
                throw new Exception("No hay suficientes existencias de ese producto");
            }
            Linea linea = new Linea(model.getActual(), 1, desc);
            linea.setFactura(model.getFactura());
            model.getLineas().add(linea);
            model.setLineas(model.getLineas());
            model.setActual(null);
        }
        else{
            model.setActual(null);
        }
    }
    public List<Linea> getListLinea() {
        return model.getLineas();
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
                model.getLineas().clear();
                model.setLineas(model.getLineas());
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
            Service.instance().create(factura);
            for(Linea linea : getListLinea()){
                linea.getProducto().setExistencia(linea.getProducto().getExistencia()-linea.getCantidad()); //Le quita la cantidad que se compraron a existencias
                linea.setFactura(factura);
                Service.instance().create(linea);
            }
            model.init(Service.instance().search(new Cajero()), Service.instance().search(new Cliente()));
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void actualizarCliente(Cliente selectedItem) {
        this.model.getFactura().setCliente(selectedItem);
    }
    public void actualizarCajero(Cajero selectedItem) {
        this.model.getFactura().setCajero(selectedItem);
    }
    public List<Linea> getLineasModel(){
        return model.getLineas();
    }

}
