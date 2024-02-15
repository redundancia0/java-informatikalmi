import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class EventosBuscadorClientes {
	private VistaBuscadorCliente vistaBuscadorCliente;
	private EventosCesta eventosCesta;
	private ConexionDB conexionDB;
	private int clienteSeleccionado;
	private List<Producto> listaProductos;
	
	public EventosBuscadorClientes(VistaBuscadorCliente vistaBuscadorCliente, EventosCesta eventosCesta) {
		this.vistaBuscadorCliente = vistaBuscadorCliente;
		this.eventosCesta = eventosCesta;
		registrarEventos();
	}
	
	public void registrarEventos() {
		vistaBuscadorCliente.getBtnBuscar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dni = vistaBuscadorCliente.getTxtDNI().getText();
				mostrarResultadoClientes(dni);
			}
		});
		
		vistaBuscadorCliente.getBtnCerrar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vistaBuscadorCliente.setVisible(false);
			}
		});
	}
	
	public void limpiar() {
		vistaBuscadorCliente.getPanelListaClientes().removeAll();
		vistaBuscadorCliente.getPanelListaClientes().revalidate();
		vistaBuscadorCliente.getPanelListaClientes().repaint();
    }
	
    public static Timestamp obtenerFechaActual() {
        long currentTimeMillis = System.currentTimeMillis();

        Date currentDate = new Date(currentTimeMillis);

        return new Timestamp(currentDate.getTime());
    }

	public void agregarCliente(int id_cliente, String nombre, String apellido1, String apellido2) {
        JLabel labelCliente = new JLabel("ID: " + id_cliente + ", Nombre: " + nombre + ", Apellidos: " + apellido1 + " " + apellido2);
        JButton btnSeleccionar = new JButton("Seleccionar");
        ConexionDB conexion = new ConexionDB();
        btnSeleccionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	int respuesta = JOptionPane.showConfirmDialog(null, "¿Estás seguro?", "Confirmar", JOptionPane.YES_NO_OPTION);
            	if (respuesta == JOptionPane.YES_OPTION) {
                    System.out.println("Cliente seleccionado: " + id_cliente);
                    clienteSeleccionado = id_cliente;
                    int id_empleado = 1;
            		Timestamp fechaActual = obtenerFechaActual();
                    System.out.println("Fecha actual en formato datetime: " + fechaActual);
            		listaProductos = eventosCesta.getListaProductos();

                    for (Producto producto : listaProductos) {
                    	int id_producto = producto.getId_producto();
                    	String nombre_producto = producto.getNombre();
                        System.out.println(nombre_producto + id_producto);
                    }
                    
                    Map<Integer, Integer> conteoProductos = new HashMap<>();
                    Map<Integer, Double> precioTotalProductos = new HashMap<>();

                    for (Producto producto : listaProductos) {
                        int id_producto = producto.getId_producto();
                        double precio_producto = producto.getPrecio();
                        
                        conteoProductos.put(id_producto, conteoProductos.getOrDefault(id_producto, 0) + 1);
                        
                        precioTotalProductos.put(id_producto, precioTotalProductos.getOrDefault(id_producto, 0.0) + precio_producto);
                    }

                    for (Map.Entry<Integer, Integer> entry : conteoProductos.entrySet()) {
                        int id_producto = entry.getKey();
                        int cantidad = entry.getValue();
                        double precio_total = precioTotalProductos.get(id_producto);
                        double precio_redondeado = Math.round(precio_total * 100.0) / 100.0;
                        conexion. ejecutarProcedimientoProductos(id_empleado, clienteSeleccionado, cantidad, precio_redondeado, id_producto, 1);
                    }	
            	}
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
