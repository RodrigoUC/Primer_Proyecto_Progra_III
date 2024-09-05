package pos.presentation.estadisticas;

import pos.logic.Categoria;
import pos.logic.LineaEstadistica;

import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.JFreeChart;

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
    private JButton deleteOne;
    private JButton deleteAll;
    private DefaultCategoryDataset estadisticasDataset;
    private JFreeChart graficoChart;

    public JPanel getPanel() { return panel; }

    public View(){

        // Grafica
        estadisticasDataset = new DefaultCategoryDataset();

    // Leer los datos y ingresarlos al estadistica dataset
        estadisticasDataset.addValue(0, "Aceite", "2024-7");
        estadisticasDataset.addValue(1000, "Aceite", "2024-8");
        estadisticasDataset.addValue(1000, "Aguas", "2024-7");
        estadisticasDataset.addValue(2000, "Aguas", "2024-8");

        graficoChart = ChartFactory.createLineChart("Ventas por mes", "Mes", "Ventas"
                , estadisticasDataset, PlotOrientation.VERTICAL, true, true, false);


//         Crear un ChartPanel con el gráfico
        ChartPanel chartPanel = new ChartPanel(graficoChart);
        chartPanel.setPreferredSize(new Dimension(800, 600));

//         Añadir el ChartPanel al JPanel existente
        graficoPanel.setLayout(new BorderLayout());
        graficoPanel.add(chartPanel, BorderLayout.CENTER);
        graficoPanel.validate();


        // Eventos
        deleteOne.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{

                } catch (Exception ex){
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Information", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        deleteAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{

                } catch (Exception ex){
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Information", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        seleccionarTodoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{

                } catch (Exception ex){
                    JOptionPane.showMessageDialog(panel, ex.getMessage(),"Information", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        seleccionUnicaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    LineaEstadistica filter = new LineaEstadistica();
                    filter.setCategoria((Categoria) categoriaCBX.getSelectedItem());
                    controller.search(filter);
                } catch(Exception ex){
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Informacion", JOptionPane.INFORMATION_MESSAGE);
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
            case Model.LIST:
                int[] cols = {TableModel.CATEGORIA, TableModel.FECHA};
                list.setModel(new TableModel(cols, model.getList()));
                list.setRowHeight(30);
                TableColumnModel columnModel = list.getColumnModel();
                columnModel.getColumn(0).setPreferredWidth(100);
                columnModel.getColumn(1).setPreferredWidth(100);
                break;
            case Model.CURRENT:
                break;
            case Model.FILTER:
                categoriaCBX.setSelectedItem(model.getFilter().getCategoria());
                break;
        }
        this.panel.revalidate();
    }

}
