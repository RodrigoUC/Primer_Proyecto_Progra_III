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


    @Override
    protected Object getPropetyAt(Producto e, int col) {
        switch (cols[col]) {
            case DESCRIPCION:
                return e.getDescripcion();
            case CODIGO:
                return e.getCodigo();
            case PRECIO:
                return e.getPrecio();
            case CATEGORIA:
                return e.getStringCategoria();
            case UNIDAD:
                return e.getUnidad();

            default:
                return "";
        }
    }


    @Override
    protected void initColNames(){
        colNames = new String[5];
        colNames[CODIGO]= "Codigo";
        colNames[DESCRIPCION]= "Descrpicion";
        colNames[UNIDAD]= "Unidad";
        colNames[PRECIO]= "Precio";
        colNames[CATEGORIA]= "Categoria";

    }

}
