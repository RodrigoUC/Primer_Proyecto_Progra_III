package pos.presentation.historico;

import pos.logic.LineaHistorico;
import pos.presentation.AbstractTableModel;

import java.util.List;

public class TableModelFacturas extends AbstractTableModel<LineaHistorico> implements javax.swing.table.TableModel {

    public TableModelFacturas(int[] cols, List<LineaHistorico> rows) {
        super(cols, rows);
    }

    public static final int NUMERO = 0;
    public static final int CLIENTE = 1;
    public static final int CAJERO = 2;
    public static final int FECHA = 3;
    public static final int IMPORTE = 4;

    @Override
    protected Object getPropertyAt(LineaHistorico e, int col){
        switch (cols[col]){
            case NUMERO: return e.getNumero();
            case CLIENTE: return e.getNombreCliente();
            case CAJERO: return e.getNombreCajero();
            case FECHA: return e.getFechaString();
            case IMPORTE: return e.getImporte();
            default: return "";
        }
    }

    @Override
    protected void initColNames(){
        colNames = new String[5];
        colNames[NUMERO] = "Numero";
        colNames[CLIENTE] = "Cliente";
        colNames[CAJERO] = "Cajero";
        colNames[FECHA] = "Fecha";
        colNames[IMPORTE] = "Importe";
    }
}