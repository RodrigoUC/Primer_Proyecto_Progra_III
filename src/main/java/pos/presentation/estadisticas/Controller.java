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

    public Double[][] createData(){
        String[] rows = view.getCategorias();
        String[] cols = view.getFechas();
        Double[][] data = new Double[rows.length][cols.length];

        for (int i = 0; i < rows.length; i++) {
            for (int j = 0; j < cols.length; j++) {
                data[i][j] = (Double) Service.instance().totalDelMes(rows[i], Integer.parseInt(cols[j].substring(0,3)), Integer.parseInt(cols[j].substring(4)));
            }
        }
        return data;
    }

    public void seleccionUnica(Categoria categoria){
        List<Categoria> categs = new ArrayList<>();
        categs.add(categoria);
        String[] row = {categoria.getNombre()};
        model.setRango(view.getRango());
        model.setCategoriasAll(view.getCategoriasList());
        model.setCategorias(categs);
        model.setRows(row);
        model.setCols(view.getFechas());
        model.setData(createData());
    }

    public void actualizarInfo(){
        model.setRango(view.getRango());
        model.setCols(view.getFechas());
        model.setData(createData());
    }

    public void seleccionTotal() {
        model.setRango(view.getRango());
        model.setCategoriasAll(view.getCategoriasList());
        model.setCategorias(view.getCategoriasList());
        model.setRows(view.getCategorias());
        model.setCols(view.getFechas());
        model.setData(createData());
    }

    public void borrarCategoria(int posCateg){
        List<Categoria> categs = model.getCategorias();

        if(categs.size() > 1){
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
        } else{
            borrarTodo();
        }
    }

    public void borrarTodo(){
        model.setCategoriasAll(new ArrayList<>());
        model.setCategorias(new ArrayList<>());
        model.setRango(new Rango());
        model.setRows(new String[0]);
        model.setCols(new String[1]);
        model.setData(new Double[0][0]);
    }

}