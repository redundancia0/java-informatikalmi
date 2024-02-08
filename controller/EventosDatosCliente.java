import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventosDatosCliente {
    private VistaCesta vistaCesta;
    private VistaDatosCliente vistaDatosCliente;
    private VistaBuscadorCliente vistaBuscadorCliente;
    private ConexionDB conexionDB;

    public EventosDatosCliente(VistaDatosCliente vistaDatosCliente, VistaCesta vistaCesta, VistaBuscadorCliente vistaBuscadorCliente) {
        this.vistaCesta = vistaCesta;
        this.vistaDatosCliente = vistaDatosCliente;
        this.vistaBuscadorCliente = vistaBuscadorCliente;
        escucharEventos();
    }
    
    public void escucharEventos() {
        vistaDatosCliente.getBtnAceptar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	conexionDB = new ConexionDB();
            	System.out.print("asd");
            	String nombre, apellido_1, apellido_2, correo, pais, telefono, calle;
            	int codigo_postal;
            	nombre = vistaDatosCliente.getTxtNombre().getText();
            	apellido_1 = vistaDatosCliente.getTxtApellido1().getText();
            	apellido_2 = vistaDatosCliente.getTxtApellido2().getText();
            	correo = vistaDatosCliente.getTxtCorreo().getText();
            	pais = vistaDatosCliente.getTxtPais().getText();
            	telefono = vistaDatosCliente.getTxtTelefono().getText();
            	calle = vistaDatosCliente.getTxtCalle().getText();
            	codigo_postal = Integer.parseInt(vistaDatosCliente.getTxtCodigoPostal().getText());
            	conexionDB.consultarClientes();
//            	vistaDatosCliente.setVisible(false);
            }
        });
    }

}
