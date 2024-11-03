package pos.presentation.Usuario;
import pos.Application.*;

import pos.logic.*;

import javax.swing.*;
import java.util.List;

import static pos.Application.facturacionController;

public class Controller implements ThreadListener {
    Model model;
    View view;
    SocketListener socketListener;
    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        view.setController(this);
        view.setModel(model);
        model.init();
        try {
            socketListener = new SocketListener(this, Service.instance().getSid());
            socketListener.start();
        } catch (Exception e) {
        }
    }
    @Override
    public void salioUsuario(Usuario usuario) {
        List<Usuario> usu = model.getUsuarios();
        for(Usuario u : usu) {
            if(u.getID() == usuario.getID()) {
                usu.remove(u);
                model.setUsuarios(usu);
                break;
            }
        }

    }

    @Override
    public void ingresoUsuario(Usuario usuario) {
        model.getUsuarios().add(usuario);
        model.setUsuarios(model.getUsuarios());
    }

    @Override
    public void recibirFactura(Factura factura, Usuario usuario) {
        List<Usuario> usu = model.getUsuarios();
        for(Usuario u : usu) {
            if(u.getID() == usuario.getID()) {
                u.getFacturas().add(factura);
                model.setUsuarios(model.getUsuarios());
                break;
            }
        }
    }
    public void edit(int row) {
        model.setCurrent(model.getUsuarios().get(row));
    }
    public void enviarFactura()throws Exception {
        if(!(facturacionController.getLineasModel().isEmpty())) {
            Service.instance().enviarFactura(facturacionController.transferirFactura(), model.getCurrent());
            model.setUsuarios(model.getUsuarios());
        }
        else{
            JOptionPane.showMessageDialog(null, "Debe tener lineas ingresadas para enviar una factura","Informacion" , JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public void recibirFactura() {
        Factura f = model.getCurrent().getFacturas().remove(0);
        model.setUsuarios(model.getUsuarios());
        facturacionController.recibirFactura(f);
    }
}
