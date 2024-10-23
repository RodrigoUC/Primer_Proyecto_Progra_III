package pos.presentation.estadisticas;

import pos.logic.Categoria;
import pos.presentation.estadisticas.Rango;
import pos.presentation.AbstractModel;
import pos.presentation.AbstractTableModel;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class Model extends AbstractModel {
    List<Categoria> categoriasAll;
    List<Categoria> categorias;
    Rango rango;
    List<String> rowsCat;
    List<String> colsFech;
    Float[][] data;

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        super.addPropertyChangeListener(listener);
        firePropertyChange(CATEGORIAS_ALL);
        firePropertyChange(RANGE);
        firePropertyChange(CATEGORIAS);
        firePropertyChange(ROWS);
        firePropertyChange(COLS);
        firePropertyChange(DATA);
    }

    public void init (){
        this.categoriasAll = new ArrayList<>();
        this.categorias = new ArrayList<>();
        this.rowsCat = new ArrayList<>();
        this.colsFech = new ArrayList<>();
        this.data = new Float[0][0];
    }

    public Model() {
    }

    public List<Categoria> getCategoriasAll() {  return this.categoriasAll;  }
    public List<Categoria> getCategorias() {  return this.categorias;  }
    public Rango getRango() {  return rango;  }
    public List<String> getRows() {  return this.rowsCat;  }
    public List<String> getCols() {  return this.colsFech;  }
    public Float[][] getData() { return this.data; }

    public void setData(Float[][] data){
        this.data = data;
        firePropertyChange(DATA);
    }
    public void setCategoriasAll(List<Categoria> categoriasAll) {
        this.categoriasAll = categoriasAll;
        firePropertyChange(CATEGORIAS_ALL);
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
        firePropertyChange(CATEGORIAS);
    }
    public void setRango(Rango rango) {
        this.rango = rango;
        firePropertyChange(RANGE);
    }
    public void setRows(List<String> rows) {
        this.rowsCat = rows;
        firePropertyChange(ROWS);
    }
    public void setCols(List<String> cols) {
        this.colsFech = cols;
        firePropertyChange(COLS);
    }

    public static final String CATEGORIAS_ALL = "categoriasAll";
    public static final String RANGE = "range";
    public static final String CATEGORIAS = "categorias";
    public static final String ROWS = "rows";
    public static final String COLS = "cols";
    public static final String DATA = "data";


    public void init(List<Categoria> categorias) {
        setCategoriasAll(categorias);
        this.categorias = new ArrayList<>();
        this.rowsCat = new ArrayList<>();
        this.colsFech = new ArrayList<>();
        this.data = new Float[0][0];
    }


    public TableModel getTableModel() {
        return new AbstractTableModel() {
            @Override
            public int getRowCount() {
                if(rowsCat != null)
                    return rowsCat.size();
                else
                    return 0;
            }

            @Override
            public int getColumnCount() {
                if(colsFech != null)
                    return colsFech.size();
                else
                    return 1;
            }

            @Override
            public Object getValueAt(int row, int col) {
                if (rowsCat == null || colsFech == null || data == null) {
                    return 0.0;
                }
                if (col == 0) {
                     return rowsCat.get(row);
                } else if (data != null && row < data.length && col - 1 < data[row].length) {
                     return data[row][col - 1];
                }
                return 0.0;
            }

            @Override
            public Class<?> getColumnClass(int col) {
                return Object.class; // Cambiar según el tipo de datos que necesita
            }


            @Override
            protected Object getPropertyAt(Object o, int col) {
                // Primera columna: el nombre de la categoría
                Categoria categoria = (Categoria)o;
                if (col == 0) {
                    return categoria.getNombre(); // Devuelve el nombre de la categoría
                }

                else if (col > 0 && col <= colsFech.size()) {
                    // Obtener el índice de la categoría en la lista de categorías
                    int rowIndex = categorias.indexOf(categoria);
                    // Devolver el valor de la matriz 'data' para esa fila y columna
                    return data[rowIndex][col - 1];
                }

                // En caso de un índice fuera de rango, devolver un valor vacío
                return "";
            }

            @Override
            protected void initColNames() {
                colNames = new String[getColumnCount()];
                colNames[0] = "Categoria"; // Primer nombre de columna
                for (int i = 1; i < colsFech.size(); i++) {
                    colNames[i] = colsFech.get(i-1); // Coloca los nombres de las fechas en las demás columnas
                }
            }

            public String getColumnName(int col) {
                if(col == 0)
                    return "Categoria";
                else
                    return colsFech.get(col-1);
            }
        };
    }

}

