import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;

public class Principal extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private VistaProductos vistaProductos = new VistaProductos();
    private VistaLogin vistaLogin = new VistaLogin();
    private VistaConfigurador vistaConfigurador = new VistaConfigurador();
    private VistaPendientes vistaPendientes = new VistaPendientes();
    private VistaCesta vistaCesta = new VistaCesta();
    private VistaDatosCliente vistaDatosCliente = new VistaDatosCliente();
    private VistaBuscadorCliente vistaBuscadorCliente = new VistaBuscadorCliente();
    private VistaFunciones vistaFunciones = new VistaFunciones();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Principal frame = new Principal();
                    frame.setVisible(true);
                    frame.mostrarLogin();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Principal() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1118, 576);
        setTitle("InformatikAlmi");
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        contentPane.add(tabbedPane);

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();
        JPanel panel6 = new JPanel();
        
        tabbedPane.addTab("Productos", vistaProductos);
        tabbedPane.addTab("Configurador", vistaConfigurador);
        tabbedPane.addTab("Pendientes", vistaPendientes);
        tabbedPane.addTab("Funciones", vistaFunciones);
        tabbedPane.addTab("Cesta", vistaCesta);

        EventosLogin eventosLogin = new EventosLogin(vistaLogin, this);
        EventosCesta eventosCesta = new EventosCesta(vistaCesta, vistaDatosCliente, vistaBuscadorCliente);
        EventosBuscadorClientes eventosBuscadorClientes = new EventosBuscadorClientes(vistaBuscadorCliente, eventosCesta);
        EventosDatosCliente eventosDatosCliente = new EventosDatosCliente(vistaDatosCliente, vistaCesta, vistaBuscadorCliente);
        EventosProductos eventosProductos = new EventosProductos(vistaProductos, eventosCesta, this, vistaLogin, vistaCesta);
        EventosConfigurador eventosConfigurador = new EventosConfigurador(vistaConfigurador);
        EventosFacturas eventosFacturas = new EventosFacturas();
    }
    
    public void mostrarLogin() {
        vistaLogin.setVisible(true);
    }
    
    public Principal getFramePrincipal() {
        return this;
    }
}
