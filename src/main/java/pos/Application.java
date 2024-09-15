package pos;

import pos.logic.Service;
import pos.presentation.cajeros.Controller;
import pos.presentation.cajeros.Model;
import pos.presentation.cajeros.View;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Application {
    public static void main(String[] args) throws Exception {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        }
        catch (Exception ex) {};

        window = new JFrame();
        JTabbedPane tabbedPane = new JTabbedPane();
        window.setContentPane(tabbedPane);

        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                Service.instance().stop();
            }
        });

        //Facturar
        pos.presentation.facturacion.Model facturacionModel = new pos.presentation.facturacion.Model();
        pos.presentation.facturacion.View facturacionView = new pos.presentation.facturacion.View();
        facturacionController = new pos.presentation.facturacion.Controller(facturacionView, facturacionModel);
        Icon facturarIcon = new ImageIcon(Application.class.getResource("/pos/presentation/icons/facturar.png"));

        tabbedPane.addTab("Facturar  ", facturarIcon, facturacionView.getPanel());

        // Clientes
        pos.presentation.clientes.Model clientesModel= new pos.presentation.clientes.Model();
        pos.presentation.clientes.View clientesView = new pos.presentation.clientes.View();
        clientesController = new pos.presentation.clientes.Controller(clientesView,clientesModel);
        Icon clientesIcon= new ImageIcon(Application.class.getResource("/pos/presentation/icons/client.png"));

        tabbedPane.addTab("Clientes  ",clientesIcon,clientesView.getPanel());

        // Cajeros
        Model cajerosModel = new Model();
        View cajerosView = new View();
        cajerosController = new Controller(cajerosView, cajerosModel);
        Icon cajerosIconc = new ImageIcon(Application.class.getResource("/pos/presentation/icons/cashier.png"));

        tabbedPane.addTab("Cajeros  ",cajerosIconc, cajerosView.getPanel());

        //Productos
        pos.presentation.productos.Model productosModel = new pos.presentation.productos.Model();
        pos.presentation.productos.View productosView = new pos.presentation.productos.View();
        productosController = new pos.presentation.productos.Controller(productosView, productosModel);
        Icon productosIcon = new ImageIcon(Application.class.getResource("/pos/presentation/icons/products.png"));

        tabbedPane.addTab("Productos  ", productosIcon, productosView.getPanel());

        // Estadisticas
        pos.presentation.estadisticas.Model estadisticasModel = new pos.presentation.estadisticas.Model();
        pos.presentation.estadisticas.View estadisticasView = new pos.presentation.estadisticas.View();
        estadisticasController = new pos.presentation.estadisticas.Controller(estadisticasView, estadisticasModel);
        Icon estadisticasIcon = new ImageIcon(Application.class.getResource("presentation/icons/statisctics.png"));
        tabbedPane.addTab("Estadisticas ", estadisticasIcon, estadisticasView.getPanel());

        // Historico
        pos.presentation.historico.Model historicoModel = new pos.presentation.historico.Model();
        pos.presentation.historico.View historicoView = new pos.presentation.historico.View();
        historicoController = new pos.presentation.historico.Controller(historicoView, historicoModel);
        Icon historicoIcon = new ImageIcon(Application.class.getResource("presentation/icons/history.png"));

        tabbedPane.addTab("Historico ", historicoIcon, historicoView.getPanel());

        // Ventana
        window.setSize(1000,550);
        window.setResizable(false);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setIconImage((new ImageIcon(Application.class.getResource("presentation/icons/icon.png"))).getImage());
        window.setTitle("POS: Point Of Sale");
        window.setVisible(true);

    }
    public static pos.presentation.clientes.Controller clientesController;
    public static Controller cajerosController;
    public static pos.presentation.productos.Controller productosController;
    public static pos.presentation.facturacion.Controller facturacionController;
    public static pos.presentation.estadisticas.Controller estadisticasController;
    public static pos.presentation.historico.Controller historicoController;

    public static JFrame window;

    public final static int MODE_CREATE=1;
    public final static int MODE_EDIT=2;
    public static Border BORDER_ERROR = BorderFactory.createMatteBorder(0, 0, 2, 0, Color.RED);

}
