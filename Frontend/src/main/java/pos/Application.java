package pos;

import pos.logic.Service;
import pos.presentation.Login.ViewLogin;
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
        tabbedPane.setBounds(10,10,700,550);

        //Login
        ViewLogin viewLogin = new ViewLogin();
        pos.presentation.Login.Model modelLogin = new pos.presentation.Login.Model();
        loginController=new pos.presentation.Login.Controller(viewLogin, modelLogin);

        pos.presentation.Usuario.Model modelUsuarios = new pos.presentation.Usuario.Model();
        pos.presentation.Usuario.View viewUsuarios = new pos.presentation.Usuario.View();
        usuariosController = new pos.presentation.Usuario.Controller(modelUsuarios, viewUsuarios);

        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                Service.instance().stop();
            }
        });

        viewLogin.setTitle("Iniciar Sesión");
        viewLogin.setModal(true);
        viewLogin.setContentPane(viewLogin.getPanel());
        viewLogin.setIconImage((new ImageIcon(Application.class.getResource("presentation/icons/icon.png"))).getImage());
        viewLogin.setMinimumSize(new Dimension(400, 200));
        viewLogin.setResizable(false); // No permite redimensionar
        viewLogin.setLocationRelativeTo(null); // Centrado
        viewLogin.setVisible(true);
        if(!modelLogin.isLoged()){
            Service.instance().stop();
            System.exit(0);
        }


        //Facturar
        pos.presentation.facturacion.Model facturacionModel = new pos.presentation.facturacion.Model();
        pos.presentation.facturacion.View facturacionView = new pos.presentation.facturacion.View();
        facturacionController = new pos.presentation.facturacion.Controller(facturacionView, facturacionModel);
        Icon facturarIcon = new ImageIcon(Application.class.getResource("/pos/presentation/icons/facturar.png"));

        tabbedPane.addTab("Facturar  ", facturarIcon, facturacionView.getPanel());

        // Clientes
        pos.presentation.clientes.Model clientesModel = new pos.presentation.clientes.Model();
        pos.presentation.clientes.View clientesView = new pos.presentation.clientes.View();
        clientesController = new pos.presentation.clientes.Controller(clientesView, clientesModel);
        Icon clientesIcon = new ImageIcon(Application.class.getResource("/pos/presentation/icons/client.png"));

        tabbedPane.addTab("Clientes  ", clientesIcon, clientesView.getPanel());

        // Cajeros
        Model cajerosModel = new Model();
        View cajerosView = new View();
        cajerosController = new Controller(cajerosView, cajerosModel);
        Icon cajerosIconc = new ImageIcon(Application.class.getResource("/pos/presentation/icons/cashier.png"));

        tabbedPane.addTab("Cajeros  ", cajerosIconc, cajerosView.getPanel());

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


        //Usuarios

        // Ventana
        JPanel usuarios = viewUsuarios.getPanel();
        usuarios.setBounds(730,10,250,550);


        window.add(tabbedPane);
        window.add(usuarios);

        window.setLayout(null);
            window.setSize(1000, 610);
            window.setResizable(false);
            window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            window.setIconImage((new ImageIcon(Application.class.getResource("presentation/icons/icon.png"))).getImage());
            window.setTitle("POS: Point Of Sale:"+modelLogin.getUsuario().getID());
            window.setVisible(true);


    }
    public static pos.presentation.clientes.Controller clientesController;
    public static Controller cajerosController;
    public static pos.presentation.productos.Controller productosController;
    public static pos.presentation.facturacion.Controller facturacionController;
    public static pos.presentation.estadisticas.Controller estadisticasController;
    public static pos.presentation.historico.Controller historicoController;
    public static pos.presentation.Login.Controller loginController;
    public static pos.presentation.Usuario.Controller usuariosController;

    public static JFrame window;

    public final static int MODE_CREATE=1;
    public final static int MODE_EDIT=2;
    public static Border BORDER_ERROR = BorderFactory.createMatteBorder(0, 0, 2, 0, Color.RED);

}
