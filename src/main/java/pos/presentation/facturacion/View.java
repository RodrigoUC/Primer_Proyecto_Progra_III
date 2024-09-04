package pos.presentation.facturacion;

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
            //Aqui tiene que verificar que exista al menos una linea
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
            }
        });
        quitarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Boton de quitar linea
            }
        });
        descuentoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Boton de descuento
            }
        });
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Boton de borrar todas las lineas
            }
        });


        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Boton para verificar si existe un producto con ese codigo
                try {
                    Producto prod = controller.buscarProducto(codProducto.getText());
                controller.save(prod);
                    JOptionPane.showMessageDialog(panel, "LINEA AGREGADA", "", JOptionPane.INFORMATION_MESSAGE);
                }
             catch (Exception ex) {
                JOptionPane.showMessageDialog(panel, ex.getMessage(), "Información", JOptionPane.INFORMATION_MESSAGE);
            }
            }
        });
        lista.addMouseListener(new MouseAdapter() {

        });
        lista.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = lista.getSelectedRow();
//                controller.edit(row);
            }
        });
    }

    //MVC
    pos.presentation.facturacion.Model model;
    pos.presentation.facturacion.Controller controller;
    private JComboBox cajeros;
    private JComboBox clientes;
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

//            case pos.presentation.clientes.Model.CURRENT:
//                codigo.setText(model.getCurrent().getCodigo());
//                descripcion.setText(model.getCurrent().getDescripcion());
//                precio.setText("" + model.getCurrent().getPrecio());
//                unidad.setText(model.getCurrent().getUnidad());
//                categorias.setSelectedItem(model.getCurrent().getCategoria());
//
//
//                if (model.getMode() == Application.MODE_EDIT) {
//                    codigo.setEnabled(false);
//                    delete.setEnabled(true);
//                } else {
//                    codigo.setEnabled(true);
//                    delete.setEnabled(false);
//                }
//
//                codigoLbl.setBorder(null);
//                codigoLbl.setToolTipText(null);
//                descripcionLbl.setBorder(null);
//                descripcionLbl.setToolTipText(null);
//                precioLbl.setBorder(null);
//                precioLbl.setToolTipText(null);
//                unidadLbl.setBorder(null);
//                unidadLbl.setToolTipText(null);
//                categoriaLbl.setBorder(null);
//                categoriaLbl.setToolTipText(null);
//
//                break;
//            case pos.presentation.clientes.Model.FILTER:
//                searchNombre.setText(model.getFilter().getDescripcion());
//                break;
//        }
//
//        this.panel1.revalidate();
        }
    }
}
