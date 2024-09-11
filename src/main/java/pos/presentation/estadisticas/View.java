package pos.presentation.estadisticas;

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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

    public JPanel getPanel() { return panel; }

    public View() throws Exception{
        // Eventos
        deleteOne.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    controller.borrarCategoria(list.getSelectedRow());
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Information", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        deleteAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    controller.borrarTodo();
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Information", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        seleccionarTodoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    controller.seleccionTotal();
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(panel, ex.getMessage(),"Information", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        seleccionUnicaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Categoria seleccion = new Categoria(categoriaCBX.getSelectedItem().toString());
                    controller.seleccionUnica(seleccion);
                } catch(Exception ex){
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Informacion", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                    int row = list.getSelectedRow();
            }
        });
    }

    // MVC
    private Controller controller;
    private Model model;

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
            case Model.CATEGORIAS:
                list.setModel(model.getTableModel());
                list.setRowHeight(30);
                TableColumnModel columnModel = list.getColumnModel();

                for (int i = 0; i < columnModel.getColumnCount(); i++) {
                    TableColumn column = columnModel.getColumn(i);
                    column.setPreferredWidth(150); // Establece un ancho base
                }
                // Redibujar la tabla para ajustar el tamaño
                list.getTableHeader().setResizingAllowed(true);

                break;
            case Model.DATA:
                // Inicializar el dataset
                DefaultCategoryDataset dataset = new DefaultCategoryDataset();

                // Obtener los datos desde el modelo
                Double[][] data = model.getData();
                String[] rows = model.getRows();
                String[] cols = model.getCols();

                // Validar que los datos y las dimensiones de filas y columnas sean correctas
                if (rows != null && cols != null && data != null && rows.length > 0 && cols.length > 0 && data.length > 0) {
                    // Añadir los valores al dataset
                    for (int i = 0; i < rows.length; i++) {
                        for (int j = 0; j < cols.length; j++) {
                            dataset.addValue(data[i][j], rows[i], cols[j]);

                        }
                    }

                    // Crear el gráfico de líneas
                    JFreeChart chart = ChartFactory.createLineChart(
                            "Ventas por mes",    // Título del gráfico
                            "Mes",               // Etiqueta del eje X
                            "Ventas",            // Etiqueta del eje Y
                            dataset,             // Dataset
                            PlotOrientation.VERTICAL,  // Orientación del gráfico
                            true,                // Incluir leyenda
                            true,                // Incluir tooltips
                            false                // No usar URLs
                    );

                    // Configurar el renderizador para mostrar los puntos en las líneas
                    CategoryPlot plot = chart.getCategoryPlot();
                    LineAndShapeRenderer renderer = new LineAndShapeRenderer();
                    renderer.setBaseShapesVisible(true);  // Mostrar los puntos en las líneas
                    plot.setRenderer(renderer);           // Asignar el renderizador

                    // Crear el panel del gráfico
                    ChartPanel chartPanel = new ChartPanel(
                            chart,
                            530, 320,               // Tamaño preferido
                            512, 200,               // Tamaño mínimo
                            1024, 768,              // Tamaño máximo
                            true,                   // Propiedades del gráfico
                            false,                  // Propiedades de Zoom
                            false,                  // Propiedades de estilo de líneas
                            true,                   // Propiedades de tooltips
                            false,                  // Propiedades de URLs
                            false                   // Propiedades de guardar imagen
                    );

                    // Limpiar y actualizar el panel gráfico
                    graficoPanel.removeAll();
                    graficoPanel.add(chartPanel);
                    graficoPanel.revalidate();  // Revalidar para refrescar la interfaz
                    graficoPanel.repaint();     // Repintar el panel
                } else {
                    // Opcional: manejar el caso en que los datos no sean válidos
                    System.out.println("Datos insuficientes o inválidos para crear el gráfico.");
                }
                break;
        }
        this.panel.revalidate();
    }

    public List<Categoria> getCategoriasList(){
        List<Categoria> categorias = new ArrayList<>();
        String[] categs = getCategorias();
        for(int i=0; i < categs.length; i++){
            Categoria cat = new Categoria(categs[i]);
            categorias.add(cat);
        }
        return categorias;
    }

    public String[] getCategorias(){
        try {
            int cantCat = categoriaCBX.getItemCount();
            String[] categorias = new String[cantCat];

            for (int i = 0; i < cantCat; i++) {
                categorias[i] = (String) categoriaCBX.getItemAt(i);
            }

            return categorias;
        } catch(Exception ex){
            JOptionPane.showMessageDialog(panel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public String[] getCategoria(){
        String[] categorias = {(String) categoriaCBX.getSelectedItem().toString()};
        return categorias;
    }

    public String[] getFechas(){
        Rango rango = getRango();
        String[] fechas = new String[rango.cantidadDeMeses()];

        for (int i = 0; i < rango.cantidadDeMeses(); i++) {
            fechas[i] = rango.getAnioMes(i);
        }
        return fechas;
    }

    public Rango getRango(){
        try{
            int anioInicio = Integer.parseInt(Objects.requireNonNull(anioInicioCBX.getSelectedItem()).toString());
            String mesInicio = Objects.requireNonNull(mesInicioCBX.getSelectedItem()).toString();
            int anioFin = Integer.parseInt(Objects.requireNonNull(anioFinCBX.getSelectedItem()).toString());
            String mesFin = Objects.requireNonNull(mesFinCBX.getSelectedItem()).toString();

            return new Rango(anioInicio,getMes(mesInicio), anioFin, getMes(mesFin));
        } catch(Exception ex){
            JOptionPane.showMessageDialog(panel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public int getMes(String mes){
        switch (mes){
            case "Enero": return 1;
            case "Febrero": return 2;
            case "Marzo": return 3;
            case "Abril": return 4;
            case "Mayo": return 5;
            case "Junio": return 6;
            case "Julio": return 7;
            case "Agosto": return 8;
            case "Setiembre": return 9;
            case "Octubre": return 10;
            case "Noviembre": return 11;
            case "Diciembre": return 12;
            default: return -1;
        }
    }

//    public View() throws Exception {
//
//        // Grafica
//        estadisticasDataset = new DefaultCategoryDataset();
//
//        // Leer los datos y ingresarlos al estadistica dataset
//        estadisticasDataset.addValue(0, "Aceite", "2024-7");
//        estadisticasDataset.addValue(1000, "Aceite", "2024-8");
//        estadisticasDataset.addValue(1000, "Aguas", "2024-7");
//        estadisticasDataset.addValue(2000, "Aguas", "2024-8");
//
//        graficoChart = ChartFactory.createLineChart("Ventas por mes", "Mes", "Ventas"
//                , estadisticasDataset, PlotOrientation.VERTICAL, true, true, false);
//
//
//        //      Crear un ChartPanel con el gráfico
//        ChartPanel chartPanel = new ChartPanel(graficoChart);
//        chartPanel.setPreferredSize(new Dimension(800, 600));
//
//        //      Añadir el ChartPanel al JPanel existente
//        graficoPanel.setLayout(new BorderLayout());
//        graficoPanel.add(chartPanel, BorderLayout.CENTER);
//        graficoPanel.validate();
//
//

}
