package pos.presentation.historico;

import pos.logic.Linea;
import pos.presentation.AbstractTableModel;
import pos.presentation.historico.Controller;

import java.util.List;

public class TableModelFacturas extends AbstractTableModel<Linea> implements javax.swing.table.TableModel {

    public TableModelFacturas(int[] cols, List<Linea> rows) {
        super(cols, rows);
    }

    public static final int NUMERO = 0;
    public static final int CLIENTE = 1;
    public static final int CAJERO = 2;
    public static final int FECHA = 3;

    @Override
    protected Object getPropertyAt(Linea e, int col){
        switch (cols[col]){
            case NUMERO: return e.getFactura().getCodigo();
            case CLIENTE: return e.getFactura().getNombreCliente();
            case CAJERO: return e.getFactura().getCajero().getNombre();
            case FECHA: return e.getFactura().getFecha().toString();
            default: return "";
        }
    }

    @Override
    protected void initColNames(){
        colNames = new String[4];
        colNames[NUMERO] = "Numero";
        colNames[CLIENTE] = "Cliente";
        colNames[CAJERO] = "Cajero";
        colNames[FECHA] = "Fecha";
    }

}