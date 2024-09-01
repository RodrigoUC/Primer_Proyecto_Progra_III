package pos.presentation.facturacion;

public class View {






//Iba a ponerme a hacer la "pantalla" de view, pero de DefaultComboBox habia que heredar o se utilizaba directamente?


    //MVC
    pos.presentation.facturacion.Model model;
    pos.presentation.facturacion.Controller controller;

    public void setModel(Model model) {
        this.model = model;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}
