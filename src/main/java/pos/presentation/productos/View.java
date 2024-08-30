package pos.presentation.productos;

import pos.Application;
import pos.logic.Producto;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;

import java.beans.PropertyChangeEvent;

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

    public JPanel getPanel() {
        return panel1;
    }

    public View(){

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validate()) {
                    Producto n = take();
                    try {
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
                super.mouseClicked(e);
                int row = list.getSelectedRow();
                controller.edit(row);
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

        try {
            Float.parseFloat(unidad.getText());
            unidadLbl.setBorder(null);
            unidadLbl.setToolTipText(null);
        } catch (Exception e) {
            valid = false;
            unidadLbl.setBorder(Application.BORDER_ERROR);
            unidadLbl.setToolTipText("Unidad invalida");
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


    public Producto take() {
        Producto e = new Producto();
        e.setDescripcion(descripcion.getText());
        e.setCodigo(codigo.getText());
        e.setPrecio(Float.parseFloat(precio.getText()));
        e.setCategoria("CAT", (String) categorias.getSelectedItem());

        return e;
    }

    // MVC
    pos.presentation.productos.Model model;
    pos.presentation.productos.Controller controller;

    public void setModel(Model model) {
        this.model = model;
        model.addPropertyChangeListener(this);
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case pos.presentation.productos.Model.LIST:                        //FALTA UNIDAD, pongo 2 categorias por ahora
                int[] cols = {TableModel.CODIGO,TableModel.DESCRIPCION, TableModel.PRECIO, TableModel.CATEGORIA, TableModel.CATEGORIA};
                list.setModel(new TableModel(cols, model.getList()));
                list.setRowHeight(30);
                TableColumnModel columnModel = list.getColumnModel();
                columnModel.getColumn(1).setPreferredWidth(150);
                columnModel.getColumn(3).setPreferredWidth(150);
                break;
            case pos.presentation.clientes.Model.CURRENT:
                codigo.setText(model.getCurrent().getCodigo());
                descripcion.setText(model.getCurrent().getDescripcion());
                precio.setText("" + model.getCurrent().getPrecio());
                //Unidad
                categorias.setSelectedItem(model.getCurrent().getCategoria());


                if (model.getMode() == Application.MODE_EDIT) {
                    codigo.setEnabled(false);
                    delete.setEnabled(true);
                } else {
                    codigo.setEnabled(true);
                    delete.setEnabled(false);
                }

                codigoLbl.setBorder(null);
                codigoLbl.setToolTipText(null);
                descripcionLbl.setBorder(null);
                descripcionLbl.setToolTipText(null);
                precioLbl.setBorder(null);
                precioLbl.setToolTipText(null);
                //unidadLbl
                //unidadLbl
                categoriaLbl.setBorder(null);
                categoriaLbl.setToolTipText(null);

                break;
            case pos.presentation.clientes.Model.FILTER:
                searchNombre.setText(model.getFilter().getDescripcion());
                break;
        }

        this.panel1.revalidate();
    }
}
