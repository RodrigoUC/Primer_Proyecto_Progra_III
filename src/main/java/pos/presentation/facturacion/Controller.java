package pos.presentation.facturacion;

import pos.Application;
import pos.logic.*;

public class Controller {
    View view;
    Model model;

    public Controller(View view, Model model) {
        model.init(Service.instance().search(new Linea()), Service.instance().search(new Cajero()),Service.instance().search(new Cliente()));
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
    }

    public Producto buscarProducto (String cod)throws Exception{
     Producto producto=new Producto();
     producto.setCodigo(cod);
     try{
         producto=Service.instance().read(producto);
         return producto;
     }
     catch(Exception e){
         throw new Exception("Producto no existe");
     }
    }
    public void save (Producto producto)throws Exception{
        Linea lin=new Linea(producto,1,0);
        model.getListLinea().add(lin);
//        System.out.println("Linea agregada");
    }
}
