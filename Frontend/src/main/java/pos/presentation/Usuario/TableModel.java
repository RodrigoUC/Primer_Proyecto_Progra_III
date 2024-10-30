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
    protected Object getPropertyAt(Usuario e, int col) {
        switch (cols[col]) {
            case ID:
                if(!(e.getFacturas().isEmpty())) {return e.getID() + " (" + e.getFacturas().size() + ")";}
                else{return e.getID();}
            case FACTURA:
                return !(e.getFacturas().isEmpty());
            default:
                return "";
        }
    }
    @Override
    protected void initColNames(){
        colNames = new String[2];
        colNames[ID]= "ID";
        colNames[FACTURA]= "Facturas?";

    }
}
