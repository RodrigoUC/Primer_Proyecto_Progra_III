package pos.presentation.estadisticas;

import pos.logic.Categoria;
import pos.logic.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
                data[i][j] = Service.instance().totalDelMes(rows[i], Integer.parseInt(cols[j].substring(0,4)), Integer.parseInt(cols[j].substring(5)));

                System.out.println(rows[i]);
                System.out.println(cols[j]);
                System.out.println(cols[j].substring(0,4));
                System.out.println(Integer.parseInt(cols[j].substring(5)));
            }
        }
        return data;
    }

    public Boolean existeString(String[] str, String string){
        for (int i = 0; i < str.length; i++) {
            if (str[i].equals(string)) {
                return true;
            }
        }
        return false;
    }

    public void seleccionUnica(Categoria categoria){
        List<Categoria> categs;
        String[] row;
        // Si esta lleno
        if(model.getCategorias().size() == model.getCategoriasAll().size()) {
            categs = new ArrayList<>();
            categs.add(categoria);
            row = new String[1];
            row[0] = categoria.getNombre();
        }
        // Si se quieren agregar más categorias.
        else if(!existeString(model.getRows(), categoria.getNombre())){
            categs = model.getCategorias();
            categs.add(categoria);
            row = new String[model.getRows().length+1];
            for (int i = 0; i < model.getRows().length; i++) {
                if(!Objects.equals(model.getRows()[i], categoria.getNombre())) {
                    row[i] = model.getRows()[i];
                }
            }
            row[row.length - 1] = categoria.getNombre();
        }else{ // Si ya esta agregada la categoria.
            categs = model.getCategorias();
            row = model.getRows();
        }
        // Se setean los valores necesarios
        model.setCategorias(categs);
        model.setRows(row);
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

        if(categs.size() > 1) {
            categs.remove(posCateg);
            model.setCategorias(categs);

            String[] rows = model.getRows();
            String[] nuevaFila = new String[rows.length - 1];

            int f = 0;
            for (int i = 0; i < rows.length; i++) {
                if (i != posCateg) {
                    nuevaFila[f] = rows[i];
                    f++;
                }
            }
            model.setRows(nuevaFila);
        } else{
            borrarTodo();   // Se borra todo lo que queda
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

    public void shown(){
       seleccionTotal();
    }

}