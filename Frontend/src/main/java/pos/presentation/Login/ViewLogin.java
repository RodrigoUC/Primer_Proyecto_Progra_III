package pos.presentation.Login;

import pos.Application;
import pos.logic.Service;
import pos.logic.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ViewLogin extends JDialog{
    private JPanel panel;
    private JTextField usuario;
    private JPasswordField contrasena;
    private JButton iniciarSesionButton;
    private Controller controller;
    private Model model;

    public ViewLogin() {
        iniciarSesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(verificarEspacios()){
                    try {
                        Usuario u = new Usuario(usuario.getText(), contrasena.getText());
                        controller.verficarUsuario(u);
                        dispose();
                    }
                    catch(Exception ex){
                        JOptionPane.showMessageDialog(panel, ex.getMessage()+"Se cerrara el programa","Informacion" , JOptionPane.ERROR_MESSAGE);
                        dispose();
                    }
                }
            }
        });
    }

    private boolean verificarEspacios() {
        boolean valido = true;
        if (usuario.getText().equals("")) {
            valido = false;
            usuario.setBorder(Application.BORDER_ERROR);
            usuario.setToolTipText("Usuario requerido");
        }
        if (contrasena.getText().equals("")) {
            valido = false;
            contrasena.setBorder(Application.BORDER_ERROR);
            contrasena.setToolTipText("Contraseña requerida");
        }
        return valido;
    }

    public void setModel(Model m){
        model = m;
    }
    public void setController(Controller controller) {
        this.controller = controller;
    }
    public JPanel getPanel() {
        return panel;
    }

}
