package pos.presentation.Usuario;

import pos.logic.Linea;
import pos.logic.Usuario;
import pos.presentation.AbstractTableModel;

import javax.swing.*;
import java.util.List;

public class TableModel extends AbstractTableModel<Usuario> implements javax.swing.table.TableModel{

    public static final int ID = 0;
    public static final int FACTURA = 1;

    public TableModel(int[] cols, List<Usuario> rows) {
        super(cols, rows);
    }

    @Override
    public Class<?> getColumnClass(int col) {
        switch (col) {
            case FACTURA: return Boolean.class;
            default: return super.getColumnClass(col);
        }
    }

    @Override
    protected Object getPropertyAt(Usuario usuario, int col) {
        return switch (cols[col]) {
            case ID -> usuario.getID();
            case FACTURA -> false;
            default -> throw new IllegalStateException("Unexpected value: " + cols[col]);
        };
    }

    @Override
    protected void initColNames() {
        colNames = new String[2];
        colNames[0] = "ID";
        colNames[1] = "Facturas?";
    }
}
