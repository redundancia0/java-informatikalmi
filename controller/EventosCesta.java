import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class EventosCesta {
    private VistaCesta vistaCesta;
    private EventosProductos eventosProductos;
    private VistaDatosCliente vistaDatosCliente;
    private VistaBuscadorCliente vistaBuscadorCliente;
    private ConexionDB conexionDB;
    private int contadorCesta=0;
	private int clienteID;


	public EventosCesta(VistaCesta vistaCesta, VistaDatosCliente vistaDatosCliente, VistaBuscadorCliente vistaBuscadorCliente) {
        this.vistaCesta = vistaCesta;
        this.vistaDatosCliente = vistaDatosCliente;
        this.vistaBuscadorCliente = vistaBuscadorCliente;
//        vistaCesta.getListaProductos().add(new Producto("Producto 1", new ImageIcon("img/cpu.png")));
//        vistaCesta.getListaProductos().add(new Producto("Producto 2", new ImageIcon("img/cpu.png")));
//        vistaCesta.getListaProductos().add(new Producto("Producto 3", new ImageIcon("img/cpu.png")));
//        vistaCesta.getListaProductos().add(new Producto("Producto 4", new ImageIcon("img/cpu.png")));
//        vistaCesta.getListaProductos().add(new Producto("Producto 5", new ImageIcon("img/cpu.png")));
        for (Producto producto : vistaCesta.getListaProductos()) {
        	vistaCesta.getPanelProductos().add(crearPanelProducto(producto));
        }
        
        escucharEventos();
    }

    public void mostrarListaClientes() {
        ConexionDB conexionDB = new ConexionDB();
        ResultSet resultado = conexionDB.consultarClientes();
        ArrayList<String> opciones = new ArrayList<>();

        try {
            while (resultado.next()) {
                int id_cliente = resultado.getInt("id_cliente");
                String nombre = resultado.getString("nombre");
                String apellido_1 = resultado.getString("apellido1");
                String apellido_2 = resultado.getString("apellido2");

                String nombreCompleto = "[" + id_cliente + "] " + nombre + " " + apellido_1 + " " + apellido_2;
                opciones.add(nombreCompleto);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener clientes: " + e.getMessage());
        }

        if (!opciones.isEmpty()) {
            Object[] arrayOpciones = opciones.toArray();

            String seleccion = (String) JOptionPane.showInputDialog(null, "Selecciona un cliente:",
                    "Selector de Clientes", JOptionPane.QUESTION_MESSAGE, null, arrayOpciones, arrayOpciones[0]);

            if (seleccion != null) {
                JOptionPane.showMessageDialog(null, "Seleccionaste: " + seleccion, "Cliente Seleccionado", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No seleccionaste ningún cliente", "Mensaje", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se encontraron clientes en la base de datos", "Mensaje", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void escucharEventos() {
        vistaCesta.getBtnProceder().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] opciones = {"Nuevo Cliente", "Cliente Existente"};

                String seleccion = (String) JOptionPane.showInputDialog(null, "Selecciona una opción:",
                        "Selector de Opciones", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

                if (seleccion != null) {
                	if (seleccion.contains("Nuevo Cliente")) {
                    	vistaDatosCliente.setVisible(true);
                	}
                	else {
//                		mostrarListaClientes();
                		vistaBuscadorCliente.setVisible(true);
                	}
//                    JOptionPane.showMessageDialog(null, "Seleccionaste: " + seleccion, "Selección", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "No seleccionaste ninguna opción", "Mensaje", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    public VistaCesta getVistaCesta() {
		return vistaCesta;
	}

	public void setVistaCesta(VistaCesta vistaCesta) {
		this.vistaCesta = vistaCesta;
	}

	public void agregarProducto(int id_producto, String nombre, double precio, String imagen, String descripcion, int stock) {
        List<Producto> listaProductos = vistaCesta.getListaProductos();
        listaProductos.add(new Producto(id_producto, nombre, precio, imagen, stock));
        vistaCesta.setListaProductos(listaProductos);
    }
    
	public JPanel crearPanelProducto(Producto producto) {
        JPanel panelProducto = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JLabel labelTexto = new JLabel(producto.getNombre());;
//        JLabel labelImagen = new JLabel(new ImageIcon(imagenProducto.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH)));

        panelProducto.add(labelTexto);
//        panelProducto.add(labelImagen);

        return panelProducto;
    }

    public void eliminarProducto(String producto) {
    }
    
    public int getContadorCesta() {
		return contadorCesta;
	}

	public void setContadorCesta(int contadorCesta) {
		this.contadorCesta = contadorCesta;
	}
    
    public int getClienteID() {
		return clienteID;
	}

	public void setClienteID(int clienteID) {
		this.clienteID = clienteID;
	}
	
    public JLabel getlblImporte() {
		return vistaCesta.getLblImporte();
	}
	
	public void setlblImporte(JLabel lblImporte) {
		vistaCesta.setLblImporte(lblImporte);
	}
}
