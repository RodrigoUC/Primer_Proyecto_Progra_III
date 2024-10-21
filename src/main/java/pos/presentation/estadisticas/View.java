package pos.presentation.estadisticas;

import pos.Application;
import pos.logic.Categoria;

import java.awt.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableColumn;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Objects;
import java.util.List;

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
    private JScrollPane scrollPane;
    private JPanel datosPanel;

    public JPanel getPanel() {
        return panel;
    }

    public View() throws Exception {
        // Eventos

        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                super.componentShown(e);
                controller.shown();
            }
        });

        anioInicioCBX.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    if(validate()){
                        controller.actualizarInfo();
                    }
                }
            }
        });

        anioFinCBX.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    if(validate()){
                        controller.actualizarInfo();
                    }
                }
            }
        });

        mesInicioCBX.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    if(validate()){
                        controller.actualizarInfo();
                    }
                }
            }
        });

        mesFinCBX.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    if(validate()){
                        controller.actualizarInfo();
                    }
                }
            }
        });

        deleteOne.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(list.getSelectedRow() != -1) {
                        controller.borrarCategoria(list.getSelectedRow());
                        controller.actualizarInfo();
                        list.setModel(model.getTableModel());
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panel, "No se ha podido eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        deleteAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.borrarTodo();
                    controller.actualizarInfo();
                    list.setModel(model.getTableModel());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panel, "No se han podido eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        seleccionarTodoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (validate()) {
                        controller.seleccionTotal();
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panel, "No se ha podido seleccionar todo.", "Information", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        seleccionUnicaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (validate()) {
                        Categoria seleccion = new Categoria(categoriaCBX.getSelectedItem().toString());
                        controller.seleccionUnica(seleccion);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panel, "No se pudo seleccionar", "Informacion", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }

    // MVC
    private Controller controller;
    private Model model;

    public void setModel(pos.presentation.estadisticas.Model model) {
        this.model = model;
        model.addPropertyChangeListener(this);
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case Model.DATA:
                //Lista
                list.setModel(model.getTableModel());
                list.setRowHeight(30);
                TableColumnModel columnModel = list.getColumnModel();

                for (int i = 0; i < columnModel.getColumnCount(); i++) {
                    TableColumn column = columnModel.getColumn(i);
                    column.setPreferredWidth(75); // Establece un ancho base
                }
                if (model.getCols().length > 3) {
                    list.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                }

                //Gráfico
                DefaultCategoryDataset dataset = getDefaultCategoryDataset();
                JFreeChart chart = ChartFactory.createLineChart("Ventas por mes", "Mes", "Ventas", dataset, PlotOrientation.VERTICAL, true, true, false);
                CategoryPlot plot = (CategoryPlot) chart.getPlot();
                LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
                renderer.setBaseShapesVisible(true);

                // Crear el panel del gráfico
                ChartPanel chartPanel = new ChartPanel(chart);

                // Limpiar el panel gráfico y añadir el nuevo gráfico
                graficoPanel.removeAll();         // Elimina cualquier gráfico anterior
                graficoPanel.setLayout(new BorderLayout());  // Asegura que el gráfico ocupa todo el espacio.
                graficoPanel.add(chartPanel, BorderLayout.CENTER);  // Añadir el gráfico al centro

                // Revalida y repinta el panel para refrescar la interfaz
                graficoPanel.revalidate();
                graficoPanel.repaint();
                break;
        }
        this.panel.revalidate();
    }

    private DefaultCategoryDataset getDefaultCategoryDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Float[][] data = model.getData();
        String[] rows = model.getRows();
        String[] cols = model.getCols();
        if (rows.length > 0 && cols.length > 0 && data.length > 0) {
            for (int i = 0; i < rows.length; i++) {
                for (int j = 1; j < cols.length; j++) {
                    dataset.addValue(data[i][j-1].intValue(), rows[i], cols[j-1]);
                }
            }
        }
        return dataset;
    }

    public List<Categoria> getCategoriasList() {
        List<Categoria> categorias = new ArrayList<>();
        String[] categs = getCategorias();
        for (int i = 0; i < categs.length; i++) {
            Categoria cat = new Categoria(categs[i]);
            categorias.add(cat);
        }
        return categorias;
    }

    public String[] getCategorias() {
        try {
            int cantCat = categoriaCBX.getItemCount();
            String[] categorias = new String[cantCat];

            for (int i = 0; i < cantCat; i++) {
                categorias[i] = (String) categoriaCBX.getItemAt(i);
            }

            return categorias;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(panel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public String getCategoria() {
        String categoria = (String) categoriaCBX.getSelectedItem().toString();
        return categoria;
    }

    public String[] getFechas() {
        Rango rango = model.getRango();
        String[] fechas = new String[rango.cantidadDeMeses() + 1];

        for (int i = 0; i <= rango.cantidadDeMeses(); i++) {
            fechas[i] = rango.getAnioMes(i);
        }
        return fechas;
    }

    private boolean validate() {
        boolean valid = true;
        // Si el año de inicio es mayor al año final.
        if (Integer.parseInt(anioInicioCBX.getSelectedItem().toString()) > Integer.parseInt(anioFinCBX.getSelectedItem().toString())) {
            valid = false;
            anioInicioCBX.setBorder(Application.BORDER_ERROR);
        } else {
            anioInicioCBX.setBorder(null);
        }
        // Si el mes de inicio es mayor al mes final en el mismo año
        if(Integer.parseInt(anioInicioCBX.getSelectedItem().toString()) == Integer.parseInt(anioFinCBX.getSelectedItem().toString()) && getMes(mesInicioCBX.getSelectedItem().toString()) > getMes(mesFinCBX.getSelectedItem().toString())){
            valid = false;
            mesInicioCBX.setBorder(Application.BORDER_ERROR);
        } else {
            mesInicioCBX.setBorder(null);
        }
        return valid;
    }

    public Rango getRango() {
        try {
            int anioInicio = Integer.parseInt(Objects.requireNonNull(anioInicioCBX.getSelectedItem()).toString());
            String mesInicio = Objects.requireNonNull(mesInicioCBX.getSelectedItem()).toString();
            int anioFin = Integer.parseInt(Objects.requireNonNull(anioFinCBX.getSelectedItem()).toString());
            String mesFin = Objects.requireNonNull(mesFinCBX.getSelectedItem()).toString();

            return new Rango(anioInicio, getMes(mesInicio), anioFin, getMes(mesFin));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(panel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public int getMes(String mes) {
        switch (mes) {
            case "Enero":
                return 1;
            case "Febrero":
                return 2;
            case "Marzo":
                return 3;
            case "Abril":
                return 4;
            case "Mayo":
                return 5;
            case "Junio":
                return 6;
            case "Julio":
                return 7;
            case "Agosto":
                return 8;
            case "Setiembre":
                return 9;
            case "Octubre":
                return 10;
            case "Noviembre":
                return 11;
            case "Diciembre":
                return 12;
            default:
                return 0;
        }
    }
}
