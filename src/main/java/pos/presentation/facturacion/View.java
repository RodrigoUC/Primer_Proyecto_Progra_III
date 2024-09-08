package pos.presentation.facturacion;

import pos.logic.*;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class View implements PropertyChangeListener {

    public JPanel getPanel() {
        return panel;
    }
    public View(){
        cobrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            //Aqui se tiene que verificar que exista al menos una linea (Notitas para mi esquizofrenia)
                controller.getViewCobrar().Cobrar();
            }
        });
        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                controller.actualizarComboBox();
            }
        });

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Boton de buscar

                int option = JOptionPane.showOptionDialog(null, controller.getViewBuscar().getPanel(), "Título del Diálogo",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
            if(option == JOptionPane.OK_OPTION && !controller.productoActualEsNulo()){
                controller.agregarProdctoActual();
            }

            }
        });
        cantidadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Boton de cantidad
                if (!controller.currentEsNulo()) {
                    ImageIcon icono = new ImageIcon(getClass().getResource("/pos/presentation/icons/cantidad.png"));
                    String texto = (String) JOptionPane.showInputDialog(null, "Cantidad?", model.getCurrent().getProducto().getDescripcion(), JOptionPane.PLAIN_MESSAGE, icono, null, "");       //No estoy seguro si eso se puede hacer (acceder al model desde view)
                    if (texto != null && validarInts(texto)) {
                        int cantidad = Integer.parseInt(texto);
                        JOptionPane.showMessageDialog(null, "Se ingreso exitosamente la cantidad");
                        controller.actualizarCantidad(cantidad);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "No se ingreso nada o se ingreso algun caracter invalido");
                    }
                }
            }
        });
        quitarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Boton de quitar linea
                if(!controller.currentEsNulo()) {
                    controller.delete();
                }
            }
        });
        descuentoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                //Boton de descuento        Preguntar como hacer para que se desmarque despues de darle a esto
                if (!controller.currentEsNulo()) {
                    ImageIcon icono = new ImageIcon(getClass().getResource("/pos/presentation/icons/descuento.png"));
                    String texto = (String) JOptionPane.showInputDialog(null, "Descuento?", model.getCurrent().getProducto().getDescripcion(), JOptionPane.PLAIN_MESSAGE, icono, null, "");       //No estoy seguro si eso se puede hacer (acceder al model desde view)
                    if (texto != null && verificarDescuento(texto)) {
                        int descuento = Integer.parseInt(texto);
                        JOptionPane.showMessageDialog(null, "Se ingreso exitosamente el descuento");
                controller.actualizarDescuento(descuento);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "No se ingreso nada o se ingreso algun caracter invalido");
                    }
                }
            }
        });
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Boton de borrar todas las lineas
                if(!controller.listaLineasEstaVacia()) {
                    int confirm = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea eliminar todas las lineas de la factura?", "Cancelar", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {    //No se si este metodo debe llamarse aca o en controller, en todo caso seria un metodo bool
                        // Código para eliminar todo
                        controller.deleteAll();
                    }
                }
            }
        });


        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Boton para verificar si existe un producto con ese codigo
                try {
                    Producto prod = controller.buscarProducto(codProducto.getText());
                    Linea lin=new Linea(prod,1,0);
                controller.save(lin);
                    JOptionPane.showMessageDialog(panel, "LINEA AGREGADA", "", JOptionPane.INFORMATION_MESSAGE);
                }
             catch (Exception ex) {
                JOptionPane.showMessageDialog(panel, ex.getMessage(), "Información", JOptionPane.INFORMATION_MESSAGE);
            }
            }
        });
        lista.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = lista.getSelectedRow();
             controller.edit(row);
            }
        });
    }

    //MVC
    pos.presentation.facturacion.Model model;
    pos.presentation.facturacion.Controller controller;
    private JComboBox<Cajero> cajeros;
    private JComboBox<Cliente> clientes;
    private JPanel pan;
    private JTextField codProducto;
    private JButton agregarButton;
    private JButton cobrar;
    private JButton buscarButton;
    private JButton cantidadButton;
    private JButton quitarButton;
    private JButton descuentoButton;
    private JButton cancelarButton;
    private JTable lista;
    private JPanel panel;

    public void setModel(Model model) {
        this.model = model;
        model.addPropertyChangeListener(this);
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void agregarLinea(Producto p){
        model.getListLinea().add(new Linea(p,1,0)); //No estoy seguro si se manda 0% de descuento
    }
    public boolean validarInts(String texto){
        try {
            Integer.parseInt(texto);
            return (0 < Integer.parseInt(texto));
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public boolean verificarDescuento(String desc){
        if(validarInts(desc)){
            int descuento = Integer.parseInt(desc);
            return (0<descuento && descuento<=100);
        }
        return false;
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case Model.LISTLINEA:
                int[] cols = {TableModel.CODIGO, TableModel.ARTICULO, TableModel.CATEGORIA, TableModel.CANTIDAD, TableModel.PRECIO, TableModel.DESCUENTO, TableModel.NETO, TableModel.IMPORTE};
                lista.setModel(new TableModel(cols, model.getListLinea()));
                lista.setRowHeight(30);
                TableColumnModel columnModel = lista.getColumnModel();
                columnModel.getColumn(1).setPreferredWidth(150);
                columnModel.getColumn(3).setPreferredWidth(150);
                break;
            case Model.LISTCAJERO:
                cajeros.setModel(model.getCajeros());
                break;
            case Model.LISTCLIENTE:
                clientes.setModel(model.getClientes());
                break;
        }
    }
    public Factura take(){
        Cajero cajero= (Cajero)cajeros.getSelectedItem();
        Cliente cliente= (Cliente)clientes.getSelectedItem();
        Factura factura = new Factura(cajero,cliente);
        factura.setVec(controller.getListLinea());  //Pasar a controller

        return factura;
    }

}
