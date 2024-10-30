package pos.presentation.Usuario;

import pos.logic.Factura;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class View implements PropertyChangeListener {
    private JPanel panel;
    private JTable list;
    private JScrollPane scrollPanel;
    private JButton enviarButton;
    private JButton recibirButton;

    private Model model;
    private Controller controller;

    public View() {
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = list.getSelectedRow();
                controller.edit(row);
            }
        });
        enviarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(model.getCurrent() != null){
                    try {
                        controller.enviarFactura();
                    }catch (Exception ex){JOptionPane.showMessageDialog(null, ex.getMessage());}
                }
            }
        });
        recibirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(model.getCurrent() != null && !(model.getCurrent().getFacturas().isEmpty())){
                    try {
                        controller.recibirFactura();
                    }catch (Exception ex){JOptionPane.showMessageDialog(null, ex.getMessage());}
                }
            }
        });
    }

    public JPanel getPanel(){
        return panel;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case Model.LIST:
                int[] cols = {TableModel.ID,TableModel.FACTURA};
                list.setModel(new TableModel(cols, model.getUsuarios()));
                list.setRowHeight(30);
                TableColumnModel columnModel = list.getColumnModel();
                columnModel.getColumn(0).setPreferredWidth(150);
                columnModel.getColumn(1).setPreferredWidth(150);

                break;
        }
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setModel(Model model) {
        this.model = model;
        model.addPropertyChangeListener(this);
    }
}
