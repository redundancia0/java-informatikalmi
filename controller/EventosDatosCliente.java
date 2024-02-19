import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

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
            	String nombre, apellido_1, apellido_2, correo, pais, calle, dni;
            	int codigo_postal = 0, telefono = 0;
            	dni = vistaDatosCliente.getTxtDNI().getText();
            	nombre = vistaDatosCliente.getTxtNombre().getText();
            	apellido_1 = vistaDatosCliente.getTxtApellido1().getText();
            	apellido_2 = vistaDatosCliente.getTxtApellido2().getText();
            	correo = vistaDatosCliente.getTxtCorreo().getText();
            	pais = vistaDatosCliente.getTxtPais().getText();
            	try {
            	telefono =Integer.parseInt( vistaDatosCliente.getTxtTelefono().getText());
            	} catch (Exception ex) {
            		JOptionPane.showMessageDialog(null, "El teléfono debe ser numérico");
            		return;
            	}
            	calle = vistaDatosCliente.getTxtCalle().getText();
            	try {
                	codigo_postal = Integer.parseInt(vistaDatosCliente.getTxtCodigoPostal().getText());	
            	} catch (Exception ex) {
            		JOptionPane.showMessageDialog(null, "El código postal debe ser numérico");
            		return;
            	}
            	if (conexionDB.insertarCliente(dni, nombre, apellido_1, apellido_2, pais, codigo_postal, calle, correo, telefono)) {
            		vistaDatosCliente.setVisible(false);
            		JOptionPane.showMessageDialog(null, ("EL cliente " + nombre + " " + apellido_1 + " " + apellido_2 + " ha sido insertado con éxito!"));
            	}
            	else {
            		JOptionPane.showMessageDialog(null, ("EL cliente " + nombre + " " + apellido_1 + " " + apellido_2 + " ha sido insertado con éxito!"));
            	}
            }
        });
        
        vistaDatosCliente.getBtnCerrar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	vistaDatosCliente.setVisible(false);
            }
        });
    }

}
