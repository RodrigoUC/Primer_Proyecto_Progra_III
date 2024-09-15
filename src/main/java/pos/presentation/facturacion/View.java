package pos.presentation.facturacion;

import pos.logic.*;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
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
                if(clientes.getSelectedItem() == null || cajeros.getSelectedItem() == null){
                    JOptionPane.showMessageDialog(panel, "Debe Ingresar un cajero y un cliente para Cobrar","Informacion" , JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    controller.cobrar();
                }
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
                try {
                    if (clientes.getSelectedItem() == null || cajeros.getSelectedItem() == null) {
                        JOptionPane.showMessageDialog(panel, "Debe Ingresar un cajero y un cliente para empezar a agregar Productos", "Informacion", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        int option = JOptionPane.showOptionDialog(null, controller.getViewBuscar().getPanel(), "Buscar",
                                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
                        if (option == JOptionPane.OK_OPTION) {
                            controller.agregarProdctoActual(true, ((Cliente) (clientes.getSelectedItem())).getDescuento());
                        } else {
                            controller.agregarProdctoActual(false, 0);
                        }

                    }
                }
                catch(Exception ex){
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        });
        cantidadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Boton de cantidad
                try {
                    if (!controller.currentEsNulo()) {
                        String texto = controller.pedirCantidad();
                        if (texto != null && validarInts(texto)) {
                            int cantidad = Integer.parseInt(texto);
                            controller.actualizarCantidad(cantidad);
                            JOptionPane.showMessageDialog(null, "Se ingreso exitosamente la cantidad");
                        } else {
                            JOptionPane.showMessageDialog(null, "No se ingreso ha ingresado un valor valido");
                        }
                    }
                }
                catch(Exception ex){
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        quitarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Boton de quitar linea
                controller.delete();
            }
        });
        descuentoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                //Boton de descuento
                if (!controller.currentEsNulo()) {
                   String texto= controller.pedirDescuento();
                    if (texto != null && verificarDescuento(texto)) {
                        int descuento = Integer.parseInt(texto);
                        JOptionPane.showMessageDialog(null, "Se ingreso exitosamente el descuento");
                controller.actualizarDescuento(descuento);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "No se ingreso un valor valido");
                    }
                }
            }
        });
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Boton de borrar todas las lineas
               controller.deleteAll();
            }
        });


        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Boton para verificar si existe un producto con ese codigo
                if (clientes.getSelectedItem() == null || cajeros.getSelectedItem() == null) {
                    JOptionPane.showMessageDialog(panel, "Debe Ingresar un cajero y un cliente para empezar a agregar Productos","Informacion" , JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    try {
                        Producto prod = controller.buscarProducto(codProducto.getText());
                        Linea lin = new Linea(prod, 1, ((Cliente) clientes.getSelectedItem()).getDescuento());
                        controller.save(lin);
                        JOptionPane.showMessageDialog(panel, "LINEA AGREGADA", "", JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(panel, ex.getMessage(), "Información", JOptionPane.INFORMATION_MESSAGE);
                    }
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
    private JLabel cantidadArt;
    private JLabel subTotal;
    private JLabel descuentos;
    private JLabel total;

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
                cantidadArt.setText(Integer.toString(controller.getCantidadProductos()));
                subTotal.setText(Double.toString(controller.total()));
                descuentos.setText(Double.toString(controller.getDescuentoTotal()));
                total.setText(Double.toString(controller.total()));

                break;
            case Model.LISTCAJERO:
                cajeros.setModel(controller.getCajeros());
                break;
            case Model.LISTCLIENTE:
                clientes.setModel(controller.getClientes());
                break;
        }
    }
    public Factura take(){
        try {
            Cajero cajero = (Cajero) cajeros.getSelectedItem();
            Cliente cliente = (Cliente) clientes.getSelectedItem();
            Factura factura = new Factura(cajero, cliente);
            factura.setVec(controller.getListLinea());  //Pasar a controller

            return factura;
        }
        catch(Exception e){
            return null;
        }
    }


}
