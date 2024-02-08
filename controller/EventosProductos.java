import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EventosProductos {
    private VistaProductos vistaProductos;
    private EventosCesta eventosCesta;
    private ConexionDB conexionDB;
    private Principal principal;
    private VistaLogin vistaLogin;
    private VistaCesta vistaCesta;
    private int contadorCesta;
    
    public EventosProductos(VistaProductos vistaProductos, EventosCesta eventosCesta, Principal principal, VistaLogin vistaLogin, VistaCesta vistaCesta) {
        this.vistaProductos = vistaProductos;
        this.eventosCesta = eventosCesta;
        this.principal = principal;
        this.vistaLogin = vistaLogin;
        this.vistaCesta = vistaCesta;
        conexionDB = new ConexionDB();
        ResultSet resultado = conexionDB.consultarProductos();
        agregarTiposCombobox();
        comprobarTipoSeleccionado();
        escucharEventos();
    }

    public EventosCesta getEventosCesta() {
        return eventosCesta;
    }

    public void eliminarProducto(String producto) {
    }

    
    private void agregarTiposCombobox() {
        JComboBox<String> comboBox = vistaProductos.getComboTipo();
        DefaultComboBoxModel<String> modelo = (DefaultComboBoxModel<String>) comboBox.getModel();
        modelo.addElement("Placas Base");
        modelo.addElement("Procesadores");
        modelo.addElement("Discos Duros");
        modelo.addElement("Tarjetas Gráficas");
        modelo.addElement("Memorias RAM");
        modelo.addElement("Refrigeraciones Liquidas");
        modelo.addElement("Periféricos");
        modelo.addElement("Tarjetas Sonido");
        modelo.addElement("Torres");
        modelo.addElement("Ventiladores");
        modelo.addElement("Fuentes Alimentación");
    }
    
    private String obtenerProductoSeleccionado() {
        JComboBox<String> comboBox = vistaProductos.getComboTipo();
        String elementoSeleccionado = (String) comboBox.getSelectedItem();
		return elementoSeleccionado;
    }
    
    public void limpiarCombobox() {
    	vistaProductos.getComboTipo().removeAllItems();;
    }
    
    private void comprobarTipoSeleccionado() {
        String tipoElemento = obtenerProductoSeleccionado();
        System.out.print(tipoElemento);

        ResultSet resultado = null;
        switch (tipoElemento) {
            case "Placas Base":
//                resultado = conexionDB.consultarPlacasBaseSimple();
            	resultado = conexionDB.consultarProductosSimple("placas_bases");
                break;
            case "Tarjetas Gráficas":
            	resultado = conexionDB.consultarProductosSimple("tarjetas_graficas");
                break;
            case "Tarjetas Sonido":
            	resultado = conexionDB.consultarProductosSimple("tarjetas_sonidos");
                break;
            case "Procesadores":
            	resultado = conexionDB.consultarProductosSimple("procesadores");
                break;
            case "Discos Duros":
            	resultado = conexionDB.consultarProductosSimple("discos_duros");
                break;
            case "Memorias RAM":
            	resultado = conexionDB.consultarProductosSimple("memorias_ram");
                break;
            case "Refrigeraciones Liquidas":
            	resultado = conexionDB.consultarProductosSimple("refrigeraciones_liquidas");
                break;
            case "Periféricos":
            	resultado = conexionDB.consultarProductosSimple("perifericos");
                break;
            case "Torres":
            	resultado = conexionDB.consultarProductosSimple("torres");
                break;
            case "Ventiladores":
            	resultado = conexionDB.consultarProductosSimple("ventiladores");
                break;
            case "Fuentes Alimentación":
            	resultado = conexionDB.consultarProductosSimple("fuentes_alimentacion");
                break;
            default:
                System.err.println("Tipo de producto no reconocido: " + tipoElemento);
                return;
        }

        try {
            if (resultado != null) {
                while (resultado.next()) {
                    String nombre = resultado.getString("nombre");
                    double precio = resultado.getDouble("precio");
                    int stock = resultado.getInt("stock");
                    int id_producto = resultado.getInt("id_producto");
                    String imagen = resultado.getString("imagen");

                    addProducto(vistaProductos.getPanelProductos_1(), id_producto, nombre, precio, imagen, stock);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener productos: " + e.getMessage());
        } finally {
            if (resultado != null) {
                try {
                    resultado.close();
                } catch (SQLException e) {
                    System.err.println("Error al cerrar el ResultSet: " + e.getMessage());
                }
            }
        }
    }

    private void removeAllProductos(JPanel panelProductos) {
        panelProductos.removeAll();
        panelProductos.revalidate();
        panelProductos.repaint();
    }

    
    public void escucharEventos() {
    	vistaProductos.getComboTipo().addActionListener(new ActionListener() {
    	    @Override
    	    public void actionPerformed(ActionEvent e) {
    	    	removeAllProductos(vistaProductos.getPanelProductos_1());
    	        comprobarTipoSeleccionado();
    	    }
    	});
    	
    	vistaProductos.getBtnCerrarSesion().addActionListener(new ActionListener() {
    	    @Override
    	    public void actionPerformed(ActionEvent e) {
    	    	vistaLogin.setVisible(true);
    	    	principal.setVisible(false);
    	    }
    	});
    }
    
    private void addProducto(JPanel panelProductos, int id_producto, String nombre, double precio, String imagen, int stock) {
        JPanel panelProducto = new JPanel(new BorderLayout());
        JLabel labelNombre = new JLabel(nombre);
        labelNombre.setHorizontalAlignment(JLabel.CENTER);

        JLabel lblImagen = new JLabel();

//        final ImageIcon[] finalImagen = {null}; 

//        if (imagen != null) {
//            Image img = imagen.getImage();
//            Image imgEscalada = img.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
//            finalImagen[0] = new ImageIcon(imgEscalada); 
//            lblImagen.setIcon(finalImagen[0]);
//        }

        JPanel panelBoton = new JPanel();
        JButton button = new JButton("Comprar");
        button.setBackground(Color.cyan);
        // "Info" button with light gray background
        JButton buttonInfo = new JButton("!");
        buttonInfo.setBackground(Color.black);
        buttonInfo.setForeground(Color.white);
        panelBoton.add(buttonInfo);

        panelBoton.add(button);
        lblImagen.setHorizontalAlignment(JLabel.CENTER);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                	try {
	            		String numero_str = JOptionPane.showInputDialog("Introduce la cantidad: ");
	            		int numero = Integer.parseInt(numero_str);
	            		for (int x=0;x<numero;x++) {
	                        eventosCesta.agregarProducto(id_producto, nombre, precio, imagen, "-", stock);
	                        Producto producto = new Producto(id_producto, nombre, precio, imagen, stock);
	                        eventosCesta.getVistaCesta().getPanelProductos().add(eventosCesta.crearPanelProducto(producto));
	                    	contadorCesta = eventosCesta.getContadorCesta();
	                    	contadorCesta++;
	                    	eventosCesta.setContadorCesta(contadorCesta);
	                    	JLabel lblContador = vistaProductos.getLblContadorCesta();
	                    	lblContador.setText(Integer.toString(eventosCesta.getContadorCesta()));
	                    	vistaProductos.setLblContadorCesta(lblContador);
	                    	JLabel lblImporte = vistaCesta.getLblImporte();
	                    	lblImporte.setText(Integer.toString(contadorCesta));
	                    	vistaCesta.setLblImporte(lblImporte);
	            		}	
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "El valor debe ser numérico.");
                    }
                }
        });

        panelProducto.add(labelNombre, BorderLayout.NORTH);
        panelProducto.add(lblImagen, BorderLayout.CENTER);
        panelProducto.add(panelBoton, BorderLayout.SOUTH);

        panelProductos.add(panelProducto);
    }


}
