package pos.presentation.estadisticas;

import pos.Application;
import pos.logic.Fecha;
import pos.logic.Categoria;
import pos.logic.Factura;
import pos.logic.Service;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    private View view;
    private Model model;

    public Controller(View view, Model model) {
        model.init();
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
    }

    public void createData(){
        String[] rows = model.getRows();
        String[] cols = model.getCols();
        Double[][] data = new Double[rows.length][cols.length];

        for (int i = 0; i < rows.length; i++) {
            for (int j = 0; j < cols.length; j++) {
                data[i][j] = (Double) Service.instance().totalDelMes(rows[i], Integer.parseInt(cols[j].substring(0,3)), Integer.parseInt(cols[j].substring(4)));
            }
        }
        model.setData(data);
    }

    public void seleccionUnica(Categoria categoria){
        model.setCategoriasAll(view.getCategoriasList());
        List<Categoria> categs = new ArrayList<>();
        categs.add(categoria);
        model.setCategorias(categs);
        model.setRows(view.getCategoria());
        model.setCols(view.getFechas());
    }

    public void seleccionTotal() {
        model.setCategoriasAll(view.getCategoriasList());
        model.setCategorias(view.getCategoriasList());
        model.setRows(view.getCategorias());
        model.setCols(view.getFechas());
    }

    public void borrarCategoria(int posCateg){
        List<Categoria> categs = model.getCategorias();
        categs.remove(posCateg);

        model.setCategorias(categs);

        String[] rows = model.getRows();
        String[] nuevaFila = new String[rows.length - 1];

        int f = 0;
        for(int i = 0; i < rows.length; i++){
            if(i != posCateg){
                nuevaFila[f] = rows[i];
                f++;
            }
        }

        model.setRows(nuevaFila);
    }

    public void borrarTodo(){
        model.setCategoriasAll(new ArrayList<>());
        model.setCategorias(new ArrayList<>());
        model.setRows(new String[0]);
        model.setCols(new String[1]);
    }

}

