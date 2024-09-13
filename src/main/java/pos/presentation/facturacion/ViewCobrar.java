package pos.presentation.facturacion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    //No se que tan legal es esto
    private JDialog dialog;

    ViewCobrar(){
    OKButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            try {
                if(validarNumero(efectivo.getText()) && validarNumero(tarjeta.getText()) && validarNumero(cheque.getText()) && validarNumero(sinpe.getText())) {
                    double total = Double.parseDouble(efectivo.getText()) + Double.parseDouble(tarjeta.getText()) + Double.parseDouble(cheque.getText() ) + Double.parseDouble(sinpe.getText());
                    if(Double.parseDouble(precio.getText()) <= total){
                    controller.guardarFactura();
                    cerrar();
                        JOptionPane.showMessageDialog(null, "Se ha efectuado la compra", "Información", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "No se ha pagado lo suficiente para cancelar la compra", "Información", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "Se ha introducido algun digito invalido", "Información", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (Exception ex) {
            }
        }
    });
    }

    public JPanel getPanel() {
        return panel;
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
    public void setController(Controller controller) {
        this.controller = controller;
    }
    public void Cobrar(){
        precio.setText(String.valueOf(controller.total()));
        dialog = new JDialog((JFrame)null, "Cobrar",true);
        dialog.setContentPane(panel);
        dialog.pack();      //Ajusta el tamano automaticamente
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
    public void cerrar(){
        if(dialog != null) {
            dialog.dispose();
        }
    }

    Controller controller;
    Model model;
}
