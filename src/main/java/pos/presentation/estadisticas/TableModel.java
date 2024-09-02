package pos.presentation.estadisticas;

import pos.logic.LineaEstadistica;
import pos.presentation.AbstractTableModel;

import java.util.List;

public class TableModel extends AbstractTableModel<LineaEstadistica> implements javax.swing.table.TableModel {

    public TableModel(int[] cols, List<LineaEstadistica> rows) {
        super(cols, rows);
    }

    public static final int CATEGORIA = 0;
    public static final int FECHA = 1;

    @Override
    protected Object getPropetyAt(LineaEstadistica e, int col){
        switch (cols[col]){
            case CATEGORIA: return e.getCategoria();
            case FECHA: return e.getDate();
            default: return "";
        }
    }

    @Override
    protected void initColNames(){
        colNames = new String[2];
        colNames[CATEGORIA] = "Categoria";
        colNames[FECHA] = "02/09/2024";
    }
}