package pos.presentation.productos;

import pos.logic.Producto;
import pos.presentation.AbstractTableModel;

import java.util.List;

public class TableModel extends AbstractTableModel<Producto> implements javax.swing.table.TableModel {

    public TableModel(int[] cols, List<Producto> rows) {
        super(cols, rows);
    }

    public static final int DESCRIPCION = 0;
    public static final int CODIGO = 1;
    public static final int PRECIO = 2;
    public static final int CATEGORIA = 3;
    public static final int UNIDAD = 4;
    public static final int EXISTENCIA = 5;


    @Override
    protected Object getPropertyAt(Producto e, int col) {
        return switch (cols[col]) {
            case DESCRIPCION -> e.getDescripcion();
            case CODIGO -> e.getCodigo();
            case PRECIO -> e.getPrecio();
            case CATEGORIA -> e.getStringCategoria();
            case UNIDAD -> e.getUnidad();
            case EXISTENCIA -> e.getExistencia();
            default -> "";
        };
    }


    @Override
    protected void initColNames(){
        colNames = new String[6];
        colNames[CODIGO]= "Codigo";
        colNames[DESCRIPCION]= "Descrpicion";
        colNames[UNIDAD]= "Unidad";
        colNames[PRECIO]= "Precio";
        colNames[CATEGORIA]= "Categoria";
        colNames[EXISTENCIA]= "Existencia";

    }

}
