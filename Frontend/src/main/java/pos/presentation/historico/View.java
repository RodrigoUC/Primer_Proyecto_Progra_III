package pos.presentation.historico;

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
    private JTextField Cliente;
    private JButton reporteButton;
    private JButton searchButton;
    private JTable listFacturas;
    private JTable listLineas;
    private JLabel clienteLbl;

    public JPanel getPanel() { return panel; }

    public View(){
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    controller.search(Cliente.getText());
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Información", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });


        listFacturas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = listFacturas.getSelectedRow();
                try {
                    controller.listaLineasNormales(row);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        reporteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.print();
                    if(Desktop.isDesktopSupported()){
                        File myFile = new File("historico.pdf");
                        Desktop.getDesktop().open(myFile);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panel, "No se han hecho busquedas.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    // MVC

    private pos.presentation.historico.Controller controller;
    private pos.presentation.historico.Model model;

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
            case Model.LISTLINEASLISTADO:
                int[] colsf = {TableModelFacturas.NUMERO, TableModelFacturas.CLIENTE, TableModelFacturas.CAJERO, TableModelFacturas.FECHA};
                listFacturas.setModel(new TableModelFacturas(colsf, model.getListLineasListado()));
                listFacturas.setRowHeight(30);

                break;
            case Model.LISTLINEASNORMALES:
                int[] colsl = {TableModelLineas.CODIGO, TableModelLineas.ARTICULO, TableModelLineas.CATEGORIA, TableModelLineas.CANTIDAD, TableModelLineas.PRECIO, TableModelLineas.DESCUENTO, TableModelLineas.NETO, TableModelLineas.IMPORTE};
                listLineas.setModel(new TableModelLineas(colsl, model.getListLineasNormales()));
                listLineas.setRowHeight(30);
                break;
            case Model.FILTER:
               Cliente.setText(model.getFilter().getNombre());
                break;

        }
        this.panel.revalidate();
    }
}
