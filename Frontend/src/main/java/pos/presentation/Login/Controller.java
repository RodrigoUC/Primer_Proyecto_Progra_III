package pos.presentation.Login;

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
    viewLogin.log();
}
public void usuarioExiste(Usuario u,boolean log) {
    model.setLog(log);
    model.setUsuario(u);
}
public boolean usuarioLogeado() {return model.isLog();}

    public Model getModel() {
    return model;
    }
}
