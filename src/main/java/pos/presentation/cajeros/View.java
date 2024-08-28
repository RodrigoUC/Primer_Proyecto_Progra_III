package pos.presentation.cajeros;

import pos.Application;
import pos.logic.Cajero;
import pos.logic.Cajero;
import pos.presentation.cajeros.Controller;
import pos.presentation.cajeros.Model;
import pos.presentation.cajeros.TableModel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class View implements PropertyChangeListener {
    private JPanel panel;
    private JTextField searchNombre;
    private JButton search;
    private JButton save;
    private JTable list;
    private JButton delete;
    private JTextField id;
    private JTextField nombre;
    private JTextField email;
    private JLabel idLbl;
    private JLabel nombreLbl;
    private JLabel emailLbl;
    private JButton clear;
    private JLabel telefonoLbl;
    private JTextField telefono;
    private JLabel descuentoLbl;
    private JTextField descuento;
    private JLabel searchNombreLbl;
    private JButton report;

    public JPanel getPanel() {
        return panel;
    }

    public View() {
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Cajero filter = new Cajero();
                    filter.setNombre(searchNombre.getText());
                    controller.search(filter);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Información", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validate()) {
                    Cajero n = take();
                    try {
                        controller.save(n);
                        JOptionPane.showMessageDialog(panel, "REGISTRO APLICADO", "", JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(panel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
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

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.delete();
                    JOptionPane.showMessageDialog(panel, "REGISTRO BORRADO", "", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.clear();
            }
        });
    }

    private boolean validate() {
        boolean valid = true;
        if (id.getText().isEmpty()) {
            valid = false;
            idLbl.setBorder(Application.BORDER_ERROR);
            idLbl.setToolTipText("Codigo requerido");
        } else {
            idLbl.setBorder(null);
            idLbl.setToolTipText(null);
        }

        if (nombre.getText().isEmpty()) {
            valid = false;
            nombreLbl.setBorder(Application.BORDER_ERROR);
            nombreLbl.setToolTipText("Nombre requerido");
        } else {
            nombreLbl.setBorder(null);
            nombreLbl.setToolTipText(null);
        }

        if (telefono.getText().isEmpty()) {
            valid = false;
            telefonoLbl.setBorder(Application.BORDER_ERROR);
            telefonoLbl.setToolTipText("Telefono requerido");
        } else {
            telefonoLbl.setBorder(null);
            telefonoLbl.setToolTipText(null);
        }

        if (email.getText().isEmpty()) {
            valid = false;
            emailLbl.setBorder(Application.BORDER_ERROR);
            emailLbl.setToolTipText("Unidad requerida");
        } else {
            emailLbl.setBorder(null);
            emailLbl.setToolTipText(null);
        }

        try {
            Float.parseFloat(descuento.getText());
            descuentoLbl.setBorder(null);
            descuentoLbl.setToolTipText(null);
        } catch (Exception e) {
            valid = false;
            descuentoLbl.setBorder(Application.BORDER_ERROR);
            descuentoLbl.setToolTipText("Descuento invalido");
        }

        return valid;
    }

    public Cajero take() {
        Cajero e = new Cajero();
        e.setId(id.getText());
        e.setNombre(nombre.getText());
        e.setTelefono(telefono.getText());
        e.setEmail(email.getText());
        e.setDescuento(Float.parseFloat(descuento.getText()));
        return e;
    }

    // MVC
    private pos.presentation.cajeros.Model model;
    private pos.presentation.cajeros.Controller controller;

    public void setModel(pos.presentation.cajeros.Model model) {
        this.model = model;
        model.addPropertyChangeListener(this);
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case pos.presentation.cajeros.Model.LIST:
                int[] cols = {pos.presentation.cajeros.TableModel.ID, pos.presentation.cajeros.TableModel.NOMBRE, pos.presentation.cajeros.TableModel.TELEFONO, pos.presentation.cajeros.TableModel.EMAIL, pos.presentation.cajeros.TableModel.DESCUENTO};
                list.setModel(new TableModel(cols, model.getList()));
                list.setRowHeight(30);
                TableColumnModel columnModel = list.getColumnModel();
                columnModel.getColumn(1).setPreferredWidth(150);
                columnModel.getColumn(3).setPreferredWidth(150);
                break;
            case pos.presentation.cajeros.Model.CURRENT:
                id.setText(model.getCurrent().getId());
                nombre.setText(model.getCurrent().getNombre());
                telefono.setText(model.getCurrent().getTelefono());
                email.setText(model.getCurrent().getEmail());
                descuento.setText("" + model.getCurrent().getDescuento());

                if (model.getMode() == Application.MODE_EDIT) {
                    id.setEnabled(false);
                    delete.setEnabled(true);
                } else {
                    id.setEnabled(true);
                    delete.setEnabled(false);
                }

                idLbl.setBorder(null);
                idLbl.setToolTipText(null);
                nombreLbl.setBorder(null);
                nombreLbl.setToolTipText(null);
                emailLbl.setBorder(null);
                emailLbl.setToolTipText(null);
                telefonoLbl.setBorder(null);
                telefonoLbl.setToolTipText(null);
                descuentoLbl.setBorder(null);
                descuentoLbl.setToolTipText(null);
                break;
            case Model.FILTER:
                searchNombre.setText(model.getFilter().getNombre());
                break;
        }

        this.panel.revalidate();
    }

}