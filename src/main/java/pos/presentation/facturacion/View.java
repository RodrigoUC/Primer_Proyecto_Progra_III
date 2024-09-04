package pos.presentation.facturacion;

import pos.Application;
import pos.presentation.facturacion.TableModel;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class View implements PropertyChangeListener {

    public JPanel getPanel() {
        return panel;
    }


    //MVC
    pos.presentation.facturacion.Model model;
    pos.presentation.facturacion.Controller controller;
    private JComboBox cajeros;
    private JComboBox clientes;
    private JPanel panel;
    private JTable lista;

    public void setModel(Model model) {
        this.model = model;
    }

    public void setController(Controller controller) {
        this.controller = controller;
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
