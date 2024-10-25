package pos.presentation.facturacion;

import pos.logic.Producto;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ViewBuscar implements PropertyChangeListener {

    private JTextField descripcion;
    private JTable lista;
    private JPanel pan;
    private JButton Buscar;
    private JPanel panel;

    public ViewBuscar() {
        lista.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int row = lista.getSelectedRow();
                controller.editProd(row);
            }
        });

        Buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Producto filter = new Producto();
                    filter.setDescripcion(descripcion.getText());
                    controller.searchProducto(filter);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(pan, ex.getMessage(), "Información", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        }

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            switch (evt.getPropertyName()) {
                case Model.LISTPRODUCTO:
                    int[] cols = {TableModelProd.DESCRIPCION, TableModelProd.UNIDAD, TableModelProd.PRECIO, TableModelProd.CATEGORIA};
                    lista.setModel(new TableModelProd(cols, model.getListProducto()));
                    lista.setRowHeight(30);
                    TableColumnModel columnModel = lista.getColumnModel();
                    columnModel.getColumn(1).setPreferredWidth(150);
                    columnModel.getColumn(3).setPreferredWidth(150);
                    break;
                case pos.presentation.facturacion.Model.FILTER:
                    descripcion.setText(model.getFilter().getDescripcion());
                    break;
            }
    }

    private Model model;
    private Controller controller;

    JPanel getPanel() {
        return panel;
    }
    public void setModel(Model model) {
        this.model = model;
        model.addPropertyChangeListener(this);
    }
    public void setController(Controller controller) {
        this.controller = controller;
    }

}
