package pos.presentation.productos;

import pos.Application;
import pos.logic.Producto;
import pos.presentation.AbstractTableModel;

import javax.swing.*;
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
//    public static final int EXISTENCIA = 5;
    public static final int IMAGEN = 5;

    @Override
    public Class<?> getColumnClass(int col) {
        switch (col) {
            case IMAGEN: return Icon.class;
            default: return super.getColumnClass(col);
        }
    }

    @Override
    protected Object getPropertyAt(Producto e, int col) {
        return switch (cols[col]) {
            case DESCRIPCION -> e.getDescripcion();
            case CODIGO -> e.getCodigo();
            case PRECIO -> e.getPrecio();
            case CATEGORIA -> e.getCategoria().getNombre();
            case UNIDAD -> e.getUnidad();
//            case EXISTENCIA -> e.getExistencia();
            case IMAGEN -> getImagen(e.getCategoria().getNombre());
            default -> "";
        };
    }


    private Icon getImagen(String nombre){
        try{
            System.out.println(nombre + ".png");
            return new ImageIcon(pos.Application.class.getResource("presentation/icons/" + nombre + ".png"));
        }
        catch(Exception e){ return null; }
    }

    @Override
    protected void initColNames(){
        colNames = new String[6];
        colNames[CODIGO]= "Codigo";
        colNames[DESCRIPCION]= "Descrpicion";
        colNames[UNIDAD]= "Unidad";
        colNames[PRECIO]= "Precio";
        colNames[CATEGORIA]= "Categoria";
//        colNames[EXISTENCIA]= "Existencia";
        colNames[IMAGEN] = "Imagen";
    }

}
