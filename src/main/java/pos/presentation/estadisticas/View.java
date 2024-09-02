package pos.presentation.estadisticas;

import pos.Application;
import pos.logic.Cajero;
import pos.presentation.estadisticas.Controller;
import pos.presentation.estadisticas.Model;
import pos.presentation.estadisticas.TableModel;

import java.io.*;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartUtilities;

import java.awt.*;

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
    private JComboBox anioInicioCBX;
    private JComboBox mesInicioCBX;
    private JComboBox anioFinCBX;
    private JComboBox mesFinCBX;
    private JComboBox categoriaCBX;
    private JButton seleccionarTodoButton;
    private JButton seleccionUnicaButton;
    private JTable list;
    private JLabel inicioLbl;
    private JLabel finLbl;
    private JLabel categLbl;
    private JPanel graficoPanel;
    private DefaultCategoryDataset estadisticasDataset;
    private JFreeChart graficoChart;

    public JPanel getPanel() { return panel; }

    public View(){

//        // Grafica
//        estadisticasDataset = new DefaultCategoryDataset();
//
//        // Leer los datos y ingresarlos al estadistica dataset
//        // while()
//
//        graficoChart = ChartFactory.createLineChart("Ventas por mes", "Mes", "Ventas"
//                , estadisticasDataset, PlotOrientation.VERTICAL, true, true, false);
//
//
//        // Crear un ChartPanel con el gráfico
//        ChartPanel chartPanel = new ChartPanel(graficoChart);
//        chartPanel.setPreferredSize(new Dimension(800, 600));
//
//        // Añadir el ChartPanel al JPanel existente
//        graficoPanel.setLayout(new BorderLayout());
//        graficoPanel.add(chartPanel, BorderLayout.CENTER);
//        graficoPanel.validate();
//

        // Eventos
        seleccionarTodoButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });

        seleccionUnicaButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });

        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = list.getSelectedRow();
                controller.edit(row);
            }
        });
    }

    // MVC

    private pos.presentation.estadisticas.Controller controller;
    private pos.presentation.estadisticas.Model model;

    public void setModel(pos.presentation.estadisticas.Model model){
        this.model = model;
        model.addPropertyChangeListener(this);
    }

    public void setController(Controller controller){
        this.controller = controller;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case pos.presentation.estadisticas.Model.LIST:
                int[] cols = {TableModel.CATEGORIA, TableModel.FECHA};
                list.setModel(new TableModel(cols, model.getList()));
                list.setRowHeight(30);
                TableColumnModel columnModel = list.getColumnModel();
                columnModel.getColumn(0).setPreferredWidth(150);
//                columnModel.getColumn(1).setPreferredWidth(150);
                break;
            case pos.presentation.estadisticas.Model.CURRENT:

                break;
            case Model.FILTER:

                break;
        }
        this.panel.revalidate();
    }

}
