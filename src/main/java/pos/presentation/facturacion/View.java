package pos.presentation.facturacion;

import javax.swing.*;

public class View {









    //MVC
    pos.presentation.facturacion.Model model;
    pos.presentation.facturacion.Controller controller;
    private JComboBox comboBox1;
    private JComboBox comboBox2;

    public void setModel(Model model) {
        this.model = model;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}
