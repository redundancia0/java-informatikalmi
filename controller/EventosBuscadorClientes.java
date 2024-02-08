import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;

public class EventosBuscadorClientes {
	private VistaBuscadorCliente vistaBuscadorCliente;
	private ConexionDB conexionDB;
	private int clienteSeleccionado;
	
	public EventosBuscadorClientes(VistaBuscadorCliente vistaBuscadorCliente) {
		this.vistaBuscadorCliente = vistaBuscadorCliente;
		registrarEventos();
	}
	
	public void registrarEventos() {
		vistaBuscadorCliente.getBtnBuscar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dni = vistaBuscadorCliente.getTxtDNI().getText();
				mostrarResultadoClientes(dni);
			}
		});
	}
	
	public void limpiar() {
		vistaBuscadorCliente.getPanelListaClientes().removeAll();
		vistaBuscadorCliente.getPanelListaClientes().revalidate();
		vistaBuscadorCliente.getPanelListaClientes().repaint();
    }

	public void agregarCliente(int id_cliente, String nombre, String apellido1, String apellido2) {
        JLabel labelCliente = new JLabel("ID: " + id_cliente + ", Nombre: " + nombre + ", Apellidos: " + apellido1 + " " + apellido2);
        JButton btnSeleccionar = new JButton("Seleccionar");
        btnSeleccionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Cliente seleccionado: " + id_cliente);
                btnSeleccionar.setEnabled(false);
                clienteSeleccionado = id_cliente;
            }
        });
        vistaBuscadorCliente.getPanelListaClientes().add(labelCliente);
        vistaBuscadorCliente.getPanelListaClientes().add(btnSeleccionar);
        vistaBuscadorCliente.getPanelListaClientes().revalidate();
        vistaBuscadorCliente.getPanelListaClientes().repaint();
    }
    
	public void mostrarResultadoClientes(String dni) {
	    limpiar();

	    conexionDB = new ConexionDB();
	    ResultSet resultadoClientes = conexionDB.consultarClienteEspecifico(dni);
	    try {
	        if (resultadoClientes != null) {
	            while (resultadoClientes.next()) {
	                int id_cliente = resultadoClientes.getInt("id_cliente");
	                String nombre = resultadoClientes.getString("nombre");
	                String apellido1 = resultadoClientes.getString("apellido1");
	                String apellido2 = resultadoClientes.getString("apellido2");
	                agregarCliente(id_cliente, nombre, apellido1, apellido2);
	            }
	        }
	    } catch (SQLException e) {
	        System.err.println("Error al obtener clientes: " + e.getMessage());
	    } finally {
	        if (resultadoClientes != null) {
	            try {
	                resultadoClientes.close();
	            } catch (SQLException e) {
	                System.err.println("Error al cerrar el ResultSet: " + e.getMessage());
	            }
	        }
	    }
	}


}
