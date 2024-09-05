package pos.presentation.facturacion;

import pos.logic.Cajero;
import pos.logic.Cliente;
import pos.logic.Linea;
import pos.logic.Producto;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
            }
        });
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Boton de buscar
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
                        JOptionPane.showMessageDialog(null, "Se ingreo exitosamente la cantidad");
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
                        JOptionPane.showMessageDialog(null, "Se ingreo exitosamente el descuento");
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
            return true;
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
                int[] cols = {pos.presentation.facturacion.TableModel.CODIGO, pos.presentation.facturacion.TableModel.ARTICULO, pos.presentation.facturacion.TableModel.CATEGORIA, pos.presentation.facturacion.TableModel.CANTIDAD, pos.presentation.facturacion.TableModel.PRECIO, pos.presentation.facturacion.TableModel.DESCUENTO, pos.presentation.facturacion.TableModel.NETO, pos.presentation.facturacion.TableModel.IMPORTE};
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
}
