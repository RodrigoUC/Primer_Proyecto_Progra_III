package pos.presentation.historico;

import org.jfree.util.PublicCloneable;
import pos.logic.Cliente;
import pos.presentation.AbstractTableModel;

import java.util.List;

public class TableModelLineas extends AbstractTableModel<Cliente> implements javax.swing.table.TableModel {

    public TableModelLineas(int[] cols, List<Cliente> rows) {
        super(cols, rows);
    }

    public static final int CODIGO = 0;
    public static final int ARTICULO = 1;
    public static final int CATEGORIA = 2;
    public static final int CANTIDAD = 3;
    public static final int PRECIO = 4;
    public static final int DESCUENTO = 5;
    public static final int NETO = 6;
    public static final int IMPORTE = 7;

    @Override
    protected Object getPropertyAt(Cliente e, int col){
        switch (cols[col]){
            case CODIGO: return e.getId();
            case ARTICULO: return e.getNombre();
            case CATEGORIA: return "";
            case CANTIDAD: return "";
            case PRECIO: return "";
            case DESCUENTO: return "";
            case NETO: return "";
            case IMPORTE: return "";
            default: return "";
        }
    }

    @Override
    protected void initColNames(){
        colNames = new String[8];
        colNames[CODIGO] = "Codigo";
        colNames[ARTICULO] = "Articulo";
        colNames[CATEGORIA] = "Categoria";
        colNames[CANTIDAD] = "Cantidad";
        colNames[PRECIO] = "Precio";
        colNames[DESCUENTO] = "Descuento";
        colNames[NETO] = "Neto";
        colNames[IMPORTE] = "Importe";
    }
}