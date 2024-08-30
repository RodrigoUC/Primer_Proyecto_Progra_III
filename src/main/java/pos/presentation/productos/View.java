package pos.presentation.productos;

import pos.presentation.productos.Controller;
import pos.presentation.productos.Model;

import javax.swing.*;
import java.beans.PropertyChangeListener;
import pos.Application;
import pos.logic.Producto;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class View implements PropertyChangeListener {
    private JPanel panel1;

    public JPanel getPanel() {
        return panel1;
    }

    public View(){

    }


    // MVC
    pos.presentation.productos.Model model;
    pos.presentation.productos.Controller controller;

    public void setModel(Model model) {
        this.model = model;
        model.addPropertyChangeListener(this);
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
