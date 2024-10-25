package pos.presentation.estadisticas;

import pos.logic.Categoria;
import pos.logic.Rango;
import pos.logic.Service;

import java.util.ArrayList;
import java.util.Arrays;
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

    public Float[][] createData(){
        return Service.instance().estadisticas(model.getCategorias(), model.getCols(), model.getRango());
    }

    public Boolean existeString(List<String> str, String string){
        for (int i = 0; i < str.size(); i++) {
            if (str.get(i).equals(string)) {
                return true;
            }
        }
        return false;
    }

    public void seleccionUnica(Categoria categoria){
        List<Categoria> categs;
        List<String> row;
        // Si esta lleno
        if(model.getCategorias().size() == model.getCategoriasAll().size()) {
            categs = new ArrayList<>();
            categs.add(categoria);
            row = new ArrayList<>();
            row.add(categoria.getNombre());
        }
        // Si se quieren agregar más categorias.
        else if(!existeString(model.getRows(), categoria.getNombre())){
            categs = model.getCategorias();
            categs.add(categoria);
            row = new ArrayList<>(model.getRows().size()+1);
            for (int i = 0; i < model.getRows().size(); i++) {
                if(!Objects.equals(model.getRows().get(i), categoria.getNombre())) {
                    row.add(model.getRows().get(i));
                }
            }
            row.add(row.size() - 1, categoria.getNombre());
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
        model.setCategoriasAll(Service.instance().search(new Categoria()));
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

            List<String> rows = model.getRows();
            List<String> nuevaFila = new ArrayList<>(rows.size()-1);

            int f = 0;
            for (int i = 0; i < rows.size(); i++) {
                if (i != posCateg) {
                    nuevaFila.add(f, rows.get(i));
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
        model.setRows(new ArrayList<>());
        model.setCols(new ArrayList<>());
        model.setData(new Float[0][0]);
    }

    public void shown(){
       actualizarInfo();
    }

}