import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventosLogin {
    private VistaLogin vistaLogin;
    private Principal principal;
    private ConexionDB conexionDB;

    public EventosLogin(VistaLogin vistaLogin, Principal principal) {
        this.vistaLogin = vistaLogin;
        this.principal = principal;
        escucharEventos();
    }

    private boolean confirmarSesion() {
        String correo = vistaLogin.getTxtCorreo().getText();
        String clave = vistaLogin.getTxtClave().getText();
        conexionDB = new ConexionDB();
        if (conexionDB.consultarEmpleado(correo, clave)) {
            return true;
        } else {
            return false;
        }
    }
    
    private  void escucharEventos() {
        vistaLogin.getBtnIniciarSesion().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	System.out.print("asd");

                if (confirmarSesion()) {
                    mostrarFramePrincipal();
                }
            }
        });
    }
    
    private void mostrarFramePrincipal() {
    	vistaLogin.setVisible(false);
        principal.setVisible(true);
    }
}
