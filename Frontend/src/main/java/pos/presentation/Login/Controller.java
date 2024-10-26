package pos.presentation.Login;

import pos.logic.Service;
import pos.logic.Usuario;

public class Controller {
private ViewLogin viewLogin;
private Model model;

public Controller(ViewLogin viewLogin, Model model) {
    this.viewLogin = viewLogin;
    this.model = model;
    model.init();
    viewLogin.setModel(model);
    viewLogin.setController(this);
}
public boolean usuarioLogeado() {return model.isLoged();}

    public Model getModel() {
    return model;
    }

    public void verficarUsuario(Usuario u) throws Exception {
    try {
        Usuario usu = Service.instance().read(u);
        model.setUsuario(usu);
        model.setLog(true);
    }
    catch(Exception e) {
        model.setLog(false);
        model.setUsuario(null);
        throw e;
    }
    }

    public void cerrar() {
        Service.instance().stop();
        System.exit(0);
    }
}
