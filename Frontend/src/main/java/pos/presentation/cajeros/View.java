package pos.presentation.cajeros;

import pos.Application;
import pos.logic.Cajero;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

public class View implements PropertyChangeListener {
    private JPanel panel;
    private JTextField nombre;
    private JButton save;
    private JButton delete;
    private JLabel nombreLbl;
    private JLabel idLbl;
    private JTextField id;
    private JButton clear;
    private JTextField searchNombre;
    private JButton search;
    private JButton report;
    private JLabel searchNombreLbl;
    private JTable list;
    private JTextField searchId;
    private JLabel searchIdLbl;

    public JPanel getPanel() { return panel; }

    public View(){
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Cajero filter = new Cajero();
                    filter.setNombre(searchNombre.getText());
                    filter.setId(searchId.getText());
                    controller.search(filter);
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Información", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(validate()){
                    Cajero n = take();
                    try{
                        controller.save(n);
                        JOptionPane.showMessageDialog(panel, "REGISTRO APLICADO", "", JOptionPane.INFORMATION_MESSAGE);
                    } catch(Exception ex){
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
                try{
                    controller.delete();
                    JOptionPane.showMessageDialog(panel, "REGISTRO BORRADO", "", JOptionPane.INFORMATION_MESSAGE);
                }   catch (Exception ex){
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { controller.clear(); }
        });

        report.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.print();
                    if(Desktop.isDesktopSupported()){
                        File myFile = new File("cajeros.pdf");
                        Desktop.getDesktop().open(myFile);
                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    private boolean validate(){
        boolean valid = true;
        if(nombre.getText().isEmpty()){
            valid = false;
            idLbl.setBorder(Application.BORDER_ERROR);
            idLbl.setToolTipText("Codigo requerido");
        } else {
            idLbl.setBorder(null);
            idLbl.setToolTipText(null);
        }
        if(nombre.getText().isEmpty()){
            valid = false;
            nombreLbl.setBorder(Application.BORDER_ERROR);
            nombreLbl.setToolTipText("Nombre requerido");
        } else{
            nombreLbl.setBorder(null);
            nombreLbl.setToolTipText(null);
        }

        return valid;
    }

    public Cajero take(){
        Cajero e = new Cajero();
        e.setId(id.getText());
        e.setNombre(nombre.getText());
        return e;
    }

    // MVC

    private Controller controller;
    private Model model;

    public void setModel(Model model){
        this.model = model;
        model.addPropertyChangeListener(this);
    }

    public void setController(Controller controller){
        this.controller = controller;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case Model.LIST:
                int[] cols = {TableModel.ID, TableModel.NOMBRE};
                list.setModel(new TableModel(cols, model.getList()));
                list.setRowHeight(30);
                TableColumnModel columnModel = list.getColumnModel();
                columnModel.getColumn(0).setPreferredWidth(150);
                columnModel.getColumn(1).setPreferredWidth(150);
                break;
            case Model.CURRENT:
                id.setText(model.getCurrent().getId());
                nombre.setText(model.getCurrent().getNombre());

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
                break;
            case Model.FILTER:
                searchNombre.setText(model.getFilter().getNombre());
                break;
        }
        this.panel.revalidate();
    }
}
