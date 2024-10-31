package pos.presentation.productos;

import pos.Application;
import pos.logic.Producto;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;

import java.beans.PropertyChangeEvent;
import java.io.File;
import java.util.Objects;

public class View implements PropertyChangeListener {
    private JPanel panel1;
    private JLabel codigoLbl;
    private JTextField codigo;
    private JTextField descripcion;
    private JTextField unidad;
    private JLabel precioLbl;
    private JLabel categoriaLbl;
    private JTextField precio;
    private JLabel unidadLbl;
    private JLabel descripcionLbl;
    private JButton save;
    private JButton delete;
    private JButton clear;
    private JTextField searchNombre;
    private JLabel searchNombreLbl;
    private JButton search;
    private JButton reporteButton;
    private JTable list;
    private JComboBox categorias;
    private JTextField existencias;
    private JLabel existenciaLbl;

    public JPanel getPanel() {
        return panel1;
    }

    public View(){

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validate()) {
                    try {
                        Producto n = take();
                        controller.save(n);
                        JOptionPane.showMessageDialog(panel1, "REGISTRO APLICADO", "", JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(panel1, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.clear();
            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.delete();
                    JOptionPane.showMessageDialog(panel1, "REGISTRO BORRADO", "", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panel1, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Producto filter = new Producto();
                    filter.setDescripcion(searchNombre.getText());
                    controller.search(filter);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panel1, ex.getMessage(), "Información", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = list.getSelectedRow();
                controller.edit(row);
            }
        });

        reporteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.print();
                    if(Desktop.isDesktopSupported()){
                        File myFile = new File("productos.pdf");
                        Desktop.getDesktop().open(myFile);
                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    private boolean validate() {
        boolean valid = true;
        if (codigo.getText().isEmpty()) {
            valid = false;
            codigoLbl.setBorder(Application.BORDER_ERROR);
            codigoLbl.setToolTipText("Codigo requerido");
        } else {
            codigoLbl.setBorder(null);
            codigoLbl.setToolTipText(null);
        }

        if (descripcion.getText().isEmpty()) {
            valid = false;
            descripcionLbl.setBorder(Application.BORDER_ERROR);
            descripcionLbl.setToolTipText("Descripcion requerida");
        } else {
            descripcionLbl.setBorder(null);
            descripcionLbl.setToolTipText(null);
        }

        if (unidad.getText().isEmpty()) {
            valid = false;
            unidadLbl.setBorder(Application.BORDER_ERROR);
            unidadLbl.setToolTipText("Unidad requerida");
        } else {
            descripcionLbl.setBorder(null);
            descripcionLbl.setToolTipText(null);
        }

        try {
            Integer.parseInt(existencias.getText());
            existenciaLbl.setBorder(null);
            existenciaLbl.setToolTipText(null);
        } catch (Exception ex) {
            valid = false;
            existenciaLbl.setBorder(Application.BORDER_ERROR);
            existenciaLbl.setToolTipText("Existencia invalida");
        }

        try {
            Float.parseFloat(precio.getText());
            precioLbl.setBorder(null);
            precioLbl.setToolTipText(null);
        } catch (Exception e) {
            valid = false;
            precioLbl.setBorder(Application.BORDER_ERROR);
            precioLbl.setToolTipText("Precio invalido");
        }

        return valid;
    }


    public Producto take() throws Exception{
        Producto e = new Producto();
        e.setDescripcion(descripcion.getText());
        e.setCodigo(codigo.getText());
        if(Double.parseDouble(precio.getText()) <= 0){
            throw new Exception("Se ha digitado una cantidad invalida para el precio");
        }
        e.setPrecio(Float.parseFloat(precio.getText()));
        e.setCategoria(Objects.requireNonNull(categorias.getSelectedItem()).toString());
        e.setUnidad(unidad.getText());
        if(Integer.parseInt(existencias.getText()) <= 0){
            throw new Exception("Se ha digitado una cantidad invalida para existencia");
        }
        e.setExistencia(Integer.parseInt(existencias.getText()));
        return e;
    }

    // MVC
    private pos.presentation.productos.Model model;
    private pos.presentation.productos.Controller controller;

    public void setModel(Model model) {
        this.model = model;
        model.addPropertyChangeListener(this);
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        searchNombre.setText(model.getFilter().getDescripcion());
        switch (evt.getPropertyName()) {
            case Model.LIST:
                int[] cols = {TableModel.CODIGO, TableModel.DESCRIPCION, TableModel.UNIDAD, TableModel.PRECIO, TableModel.CATEGORIA, TableModel.IMAGEN};
                list.setModel(new TableModel(cols, model.getList()));
                list.setRowHeight(30);
                TableColumnModel columnModel = list.getColumnModel();
                columnModel.getColumn(1).setPreferredWidth(150);
                break;
            case Model.CURRENT:
                codigo.setText(model.getCurrent().getCodigo());
                descripcion.setText(model.getCurrent().getDescripcion());
                unidad.setText(model.getCurrent().getUnidad());
                precio.setText(String.format("%.0f", model.getCurrent().getPrecio()));
                categorias.setSelectedItem(model.getCurrent().getCategoria());
                existencias.setText(model.getCurrent().getExistencia().toString());

                codigoLbl.setBorder(null);
                codigoLbl.setToolTipText(null);
                descripcionLbl.setBorder(null);
                descripcionLbl.setToolTipText(null);
                precioLbl.setBorder(null);
                precioLbl.setToolTipText(null);
                unidadLbl.setBorder(null);
                unidadLbl.setToolTipText(null);
                categoriaLbl.setBorder(null);
                categoriaLbl.setToolTipText(null);
                existenciaLbl.setBorder(null);
                existenciaLbl.setToolTipText(null);
                break;
            case Model.CATEGORIAS:
                categorias.setModel(new DefaultComboBoxModel(model.getCategorias().toArray()));
                break;
        }
        if (model.getMode() == Application.MODE_EDIT) {
            codigo.setEnabled(false);
            delete.setEnabled(true);
        } else {
            codigo.setEnabled(true);
            delete.setEnabled(false);
        }

        this.panel1.revalidate();
    }

}
