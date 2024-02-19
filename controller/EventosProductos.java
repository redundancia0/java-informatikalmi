import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
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
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

public class EventosProductos {
    private VistaProductos vistaProductos;
    private EventosCesta eventosCesta;
    private ConexionDB conexionDB;
    private Principal principal;
	private VistaLogin vistaLogin;
	private String tipoElemento;
	private String tipoProductoActual;
    private VistaCesta vistaCesta;
    private int contadorCesta;
    private double contadorPrecioTotal;
    
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
        tipoElemento = obtenerProductoSeleccionado();
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
                    String descripcion = resultado.getString("descripcion");
                    double precio = resultado.getDouble("precio");
                    int stock = resultado.getInt("stock");
                    int id_producto = resultado.getInt("id_producto");
                    String imagen = resultado.getString("imagen");

                    addProducto(vistaProductos.getPanelProductos_1(), id_producto, nombre, descripcion, precio, imagen, stock);
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
    private void addProducto(JPanel panelProductos, int id_producto, String nombre, String descripcion, double precio, String imagen, int stock) {
        JPanel panelProducto = new JPanel(new BorderLayout());
        JLabel labelNombre = new JLabel(nombre + " (" + precio + "€)");
        JLabel labelStock = new JLabel("Stock: " + Integer.toString(stock));
        labelNombre.setHorizontalAlignment(JLabel.CENTER);

        JLabel lblImagen = new JLabel("");
        URL url=null;
		try {
			url = new URL("https://redundancia0.duckdns.org/" + imagen);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        ImageIcon icono = new ImageIcon(url);
        Image imagenOriginal = icono.getImage();
        Image nuevaImagen = imagenOriginal.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        lblImagen.setIcon(new ImageIcon(nuevaImagen));

        JPanel panelBoton = new JPanel(new FlowLayout());
        JButton button = new JButton("Comprar");
        button.setBackground(Color.cyan);
        JButton buttonInfo = new JButton("!");
        buttonInfo.setBackground(Color.black);
        buttonInfo.setForeground(Color.white);
        panelBoton.add(buttonInfo);
        panelBoton.add(button);
        panelBoton.add(labelStock);
        if (stock <= 0) {
        	button.setEnabled(false);
        }
        lblImagen.setHorizontalAlignment(JLabel.CENTER);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String numero_str = JOptionPane.showInputDialog("Introduce la cantidad: ");
                    int numero = Integer.parseInt(numero_str);
                    if (numero > stock) {
                        JOptionPane.showMessageDialog(null, "No hay suficiente stock.");
                        return;
                    }
                    for (int x=0;x<numero;x++) {
                        eventosCesta.agregarProducto(id_producto, nombre, precio, imagen, descripcion, stock);
                        Producto producto = new Producto(id_producto, nombre, descripcion, precio, imagen, stock);
                        eventosCesta.getVistaCesta().getPanelProductos().add(eventosCesta.crearPanelProducto(producto));
                        contadorCesta = eventosCesta.getContadorCesta();
                        contadorCesta++;
                        contadorPrecioTotal = contadorPrecioTotal + precio;
                        eventosCesta.setContadorCesta(contadorCesta);
                        JLabel lblContador = vistaProductos.getLblContadorCesta();
                        lblContador.setText(Integer.toString(eventosCesta.getContadorCesta()));
                        vistaProductos.setLblContadorCesta(lblContador);
                        JLabel lblElementos = vistaCesta.getLblElementos();
                        JLabel lblImporte = vistaCesta.getLblImporte();
                        lblElementos.setText(Integer.toString(contadorCesta));
                        
                        DecimalFormat df=new DecimalFormat("#,##0.00");
                        
                        lblImporte.setText(df.format(contadorPrecioTotal));
                        vistaCesta.setLblElementos(lblElementos);
                        labelStock.setText("Stock: " + Integer.toString(stock-numero));
                        vistaCesta.setLblImporte(lblImporte);
                    }   
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "El valor debe ser numérico.");
                }
            }
        });
        
        buttonInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                	ResultSet rs = conexionDB.consultarProductosDescripcion(tipoElemento, id_producto);
                    try {
						while (rs.next()) {
							if ("Placas Base".equals(tipoElemento)) {
							    String nombre = rs.getString("nombre");
							    Double precio = rs.getDouble("precio");
							    int stock = rs.getInt("stock");
							    String memoria = rs.getString("memoria");
							    String velocidad = rs.getString("velocidad");
							    String tipo_memoria = rs.getString("tipo_memoria");

							    String mensaje = "Nombre: " + nombre + "\nPrecio: " + (Double.toString(precio) + "\nStock: " + (Integer.toString(stock)) + "\nMemoria: " + memoria + "\nVelocidad: " + velocidad + "\nTipo Memoria: " + tipo_memoria);

							    JOptionPane.showMessageDialog(null, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);	
							}
							if ("Tarjetas Gráficas".equals(tipoElemento)) {
							    String nombre = rs.getString("nombre");
							    Double precio = rs.getDouble("precio");
							    int stock = rs.getInt("stock");
							    String memoria_ram = rs.getString("memoria_ram");
							    int nucleos = rs.getInt("nucleos");
							    String tipo_memoria = rs.getString("tipo_memoria");

							    String mensaje = "Nombre: " + nombre + "\nPrecio: " + (Double.toString(precio) + "\nStock: " + (Integer.toString(stock)) + "\nMemoria: " + memoria_ram + "\nNúcleos: " + (Integer.toString(nucleos)) + "\nTipo Memoria: " + tipo_memoria);

							    JOptionPane.showMessageDialog(null, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);	
							}
							
							if ("Procesadores".equals(tipoElemento)) {
							    String nombre = rs.getString("nombre");
							    Double precio = rs.getDouble("precio");
							    int stock = rs.getInt("stock");
							    String velocidad = rs.getString("velocidad");
							    int nucleos = rs.getInt("nucleos");

							    String mensaje = "Nombre: " + nombre + "\nPrecio: " + (Double.toString(precio) + "\nStock: " + (Integer.toString(stock)) + "\nVelocidad: " + velocidad + "\nNúcleos: " + (Integer.toString(nucleos)));

							    JOptionPane.showMessageDialog(null, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);	
							}
							
							if ("Tarjetas Sonido".equals(tipoElemento)) {
							    String nombre = rs.getString("nombre");
							    Double precio = rs.getDouble("precio");
							    int stock = rs.getInt("stock");
							    String tipo_conexion = rs.getString("tipo_conexion");
							    String senal_ruido = rs.getString("senal_ruido");
							    

							    String mensaje = "Nombre: " + nombre + "\nPrecio: " + (Double.toString(precio) + "\nStock: " + (Integer.toString(stock)) + "\nTipo Conexión: " + tipo_conexion + "\nSeñal Ruido: " + senal_ruido);

							    JOptionPane.showMessageDialog(null, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);	
							}
							
							if ("Discos Duros".equals(tipoElemento)) {
							    String nombre = rs.getString("nombre");
							    Double precio = rs.getDouble("precio");
							    int stock = rs.getInt("stock");
							    String memoria = rs.getString("memoria");
							    String velocidad = rs.getString("velocidad");
							    String tipo_disco = rs.getString("tipo_disco");
							   
							    String mensaje = "Nombre: " + nombre + "\nPrecio: " + (Double.toString(precio) + "\nStock: " + (Integer.toString(stock)) + "\nMemoria: " + memoria + "\nTipo Disco: " + tipo_disco + "\nVelocidad: " + velocidad);

							    JOptionPane.showMessageDialog(null, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);	
							}
							
							if ("Memorias RAM".equals(tipoElemento)) {
							    String nombre = rs.getString("nombre");
							    Double precio = rs.getDouble("precio");
							    int stock = rs.getInt("stock");
							    String memoria = rs.getString("memoria");
							    String tipo_memoria = rs.getString("tipo_memoria");
							   
							    String mensaje = "Nombre: " + nombre + "\nPrecio: " + (Double.toString(precio) + "\nStock: " + (Integer.toString(stock)) + "\nMemoria: " + memoria + "\nTipo Memoria: " + tipo_memoria);

							    JOptionPane.showMessageDialog(null, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);	
							}
							
							if ("Refrigeraciones Liquidas".equals(tipoElemento)) {
							    String nombre = rs.getString("nombre");
							    Double precio = rs.getDouble("precio");
							    int stock = rs.getInt("stock");
							    String tamano = rs.getString("tamano");
							    String velocidad = rs.getString("velocidad");
							    String peso = rs.getString("peso");
							    String tipo_liquido = rs.getString("tipo_liquido");
							   
							    String mensaje = "Nombre: " + nombre + "\nPrecio: " + (Double.toString(precio) + "\nStock: " + (Integer.toString(stock)) + "\nTamano: " + tamano + "\nVelocidad: " + velocidad + "\nPeso: " + peso + "\nTipo Líquido: " + tipo_liquido);

							    JOptionPane.showMessageDialog(null, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);	
							}
							
							if ("Periféricos".equals(tipoElemento)) {
							    String nombre = rs.getString("nombre");
							    Double precio = rs.getDouble("precio");
							    int stock = rs.getInt("stock");
							    String tipo_conexion = rs.getString("tipo_conexion");
							   
							    String mensaje = "Nombre: " + nombre + "\nPrecio: " + (Double.toString(precio) + "\nStock: " + (Integer.toString(stock)) + "\nTipo Conexión: " + tipo_conexion);

							    JOptionPane.showMessageDialog(null, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);	
							}
							
							if ("Torres".equals(tipoElemento)) {
							    String nombre = rs.getString("nombre");
							    Double precio = rs.getDouble("precio");
							    int stock = rs.getInt("stock");
							    String tipo_conexion = rs.getString("tipo_conexion");
							    String tamano = rs.getString("tamano");
							    String peso = rs.getString("peso");
							   
							    String mensaje = "Nombre: " + nombre + "\nPrecio: " + (Double.toString(precio) + "\nStock: " + (Integer.toString(stock)) + "\nTipo Conexión: " + tipo_conexion + "\nTamaño: " + tamano + "\nPeso: " + peso);

							    JOptionPane.showMessageDialog(null, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);	
							}
							
							if ("Ventiladores".equals(tipoElemento)) {
							    String nombre = rs.getString("nombre");
							    Double precio = rs.getDouble("precio");
							    int stock = rs.getInt("stock");
							    String velocidad = rs.getString("velocidad");
							    String tamano = rs.getString("tamano");
							    String peso = rs.getString("peso");
							    
							    String mensaje = "Nombre: " + nombre + "\nPrecio: " + (Double.toString(precio) + "\nStock: " + (Integer.toString(stock)) + "\nVelocidad: " + velocidad + "\nTamaño: " + tamano + "\nPeso: " + peso);

							    JOptionPane.showMessageDialog(null, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);	
							}
							
							if ("Fuentes Alimentación".equals(tipoElemento)) {
							    String nombre = rs.getString("nombre");
							    Double precio = rs.getDouble("precio");
							    int stock = rs.getInt("stock");
							    String potencia = rs.getString("potencia");
							    
							    String mensaje = "Nombre: " + nombre + "\nPrecio: " + (Double.toString(precio) + "\nStock: " + (Integer.toString(stock)) + "\nPotencia: " + potencia);

							    JOptionPane.showMessageDialog(null, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);	
							}
						}
					} catch (HeadlessException e1) {
						e1.printStackTrace();
					} catch (SQLException e1) {
						e1.printStackTrace();
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
    
    public void setContadorCesta(int contadorCesta) {
    	this.contadorCesta = contadorCesta;
    }
    
    public double getContadorPrecioTotal() {
		return contadorPrecioTotal;
	}

	public void setContadorPrecioTotal(int contadorPrecioTotal) {
		this.contadorPrecioTotal = contadorPrecioTotal;
	}

    
    public int getContadorCesta() {
    	return this.contadorCesta;
    }
}