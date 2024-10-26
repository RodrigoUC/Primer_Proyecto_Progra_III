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
                        controller.cerrar();
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

//    public void login(){
//        panel.setPreferredSize(new Dimension(400, 200));
//
//        dialog = new JDialog((JFrame) null, "Iniciar Sesión", true); // false para no modal
//        dialog.setContentPane(panel);
//        dialog.setIconImage((new ImageIcon(Application.class.getResource("presentation/icons/icon.png"))).getImage());
//        dialog.setMinimumSize(new Dimension(400, 200));
//        dialog.setResizable(false); // No permite redimensionar
//        dialog.setLocationRelativeTo(null); // Centrado
//        dialog.setVisible(true);
//    }
//
//    public void cerrar(){
//        if(dialog != null) {
//            dialog.dispose();
//        }
//    }



}