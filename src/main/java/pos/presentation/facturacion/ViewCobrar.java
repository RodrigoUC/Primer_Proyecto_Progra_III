package pos.presentation.facturacion;

import pos.logic.Producto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ViewCobrar implements PropertyChangeListener {
    private JPanel panel;
    private JTextField efectivo;
    private JTextField tarjeta;
    private JTextField cheque;
    private JTextField sinpe;
    private JButton cancelButton;
    private JButton OKButton;
    private JPanel pan;
    private JLabel efect;
    private JLabel tarj;
    private JLabel cheq;
    private JLabel sinp;
    private JLabel precio;

    ViewCobrar(){
        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                precio.setText(String.valueOf(controller.total()));
            }
        });
    OKButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            try {
                if(validarNumero(efectivo.getText()) && validarNumero(tarjeta.getText()) && validarNumero(cheque.getText()) && validarNumero(sinpe.getText())) {
                    double total = Double.parseDouble(efectivo.getText()) + Double.parseDouble(tarjeta.getText()) + Double.parseDouble(cheque.getText() ) + Double.parseDouble(sinpe.getText());
                    if(Double.parseDouble(precio.getText()) <= total){
                    controller.guardarFactura();
                        JOptionPane.showMessageDialog(null, "Se ha efectuado la compra", "Información", JOptionPane.INFORMATION_MESSAGE);
                       panel.setVisible(false);
                    }
                }
                 JOptionPane.showMessageDialog(null, "Se ha introducido algun digito invalido", "Información", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
            }
        }
    });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
    public boolean validarNumero(String num){
        try{
            Double.parseDouble(num);
            return true;
        }
        catch(Exception ex){
            return false;
        }
    }
    Controller controller;
    Model model;
}
