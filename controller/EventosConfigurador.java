import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

public class EventosConfigurador {
	private double precioTotal;
	private double precioPlaca, precioProcesador, precioVentilador, precioRefrigeracion, precioMemoriaRam, precioDiscoDuro, precioTarjetaGrafica, precioFuenteAlimentacion, precioTorre;
	private String nombrePlaca, nombreProcesador, nombreVentilador, nombreRefrigeracion, nombreMemoriaRam, nombreDiscoDuro, nombreTarjetaGrafica, nombreFuenteAlimentacion, nombreTorre;
	private int idPlaca, idProcesador, idVentilador, idRefrigeracion, idMemoriaRam, idDiscoDuro, idTarjetaGrafica, idFuenteAlimentacion, idTorre;
	private ArrayList<Producto> productos = new ArrayList<>();;
	private VistaConfigurador vistaConfigurador;
	private ArrayList<Integer> idsComponentes = new ArrayList<>();
	private ConexionDB conexion = new ConexionDB();
	
	private double sumaTotal() {
		return precioPlaca + precioProcesador + precioVentilador + precioRefrigeracion + precioMemoriaRam + precioDiscoDuro + precioTarjetaGrafica + precioFuenteAlimentacion + precioTorre;
	}
	
	private static double parsearPrecio(double precio) {
	    return Math.round(precio * 100.0) / 100.0;
	}
	
	public int buscarIdPorNombre(String nombreProductoBuscado) {
	    for (Producto producto : productos) {
	        if (producto.getNombre().contains(nombreProductoBuscado)) {
	            return producto.getId_producto();
	        }
	    }
	    return -1;
	}
	
	public double buscarPrecioPorId(int id_producto) {
	    for (Producto producto : productos) {
	        if (producto.getId_producto() == id_producto) {
	            return producto.getPrecio();
	        }
	    }
	    return -1;
	}
    DecimalFormat df=new DecimalFormat("#,##0.00");

	private void escucharEventos() {
		vistaConfigurador.getComboPlacaBase().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String objetoSeleccionado =  (String) vistaConfigurador.getComboPlacaBase().getSelectedItem();
                System.out.print(objetoSeleccionado);
                if (objetoSeleccionado != "Selecciona...") {
                    double res_precio = conexion.consultarPrecioProducto(objetoSeleccionado);
                    precioPlaca = res_precio;
                    nombrePlaca = objetoSeleccionado;
                    String precioTotal_str = Double.toString(sumaTotal());
                    vistaConfigurador.getLblPrecioTotal().setText(df.format(sumaTotal()));
                    System.out.print("\n\n\n Precio:" + res_precio + "\n\n\n");                	
                }                
            }
        });
		
		vistaConfigurador.getComboProcesador().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String objetoSeleccionado =  (String) vistaConfigurador.getComboProcesador().getSelectedItem();
                System.out.print(objetoSeleccionado);
                if (objetoSeleccionado != "Selecciona...") {
                    double res_precio = conexion.consultarPrecioProducto(objetoSeleccionado);
                    precioProcesador = res_precio;
                    nombreProcesador = objetoSeleccionado;
                    String precioTotal_str = Double.toString(sumaTotal());
                    vistaConfigurador.getLblPrecioTotal().setText(df.format(sumaTotal()));
                    System.out.print("\n\n\n Precio:" + res_precio + "\n\n\n");                	
                }                
            }
        });

		vistaConfigurador.getComboVentilador().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String objetoSeleccionado =  (String) vistaConfigurador.getComboVentilador().getSelectedItem();
                System.out.print(objetoSeleccionado);
                if (objetoSeleccionado != "Selecciona...") {
                    double res_precio = conexion.consultarPrecioProducto(objetoSeleccionado);
                    precioVentilador = res_precio;
                    nombreVentilador = objetoSeleccionado;
                    String precioTotal_str = Double.toString(sumaTotal());
                    vistaConfigurador.getLblPrecioTotal().setText(df.format(sumaTotal()));
                    System.out.print("\n\n\n Precio:" + res_precio + "\n\n\n");                	
                }                
            }
        });
		
		vistaConfigurador.getComboRefrigeracion().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String objetoSeleccionado =  (String) vistaConfigurador.getComboRefrigeracion().getSelectedItem();
                System.out.print(objetoSeleccionado);
                if (objetoSeleccionado != "Selecciona...") {
                    double res_precio = conexion.consultarPrecioProducto(objetoSeleccionado);
                    precioRefrigeracion = res_precio;
                    nombreRefrigeracion = objetoSeleccionado;
                    String precioTotal_str = Double.toString(sumaTotal());
                    vistaConfigurador.getLblPrecioTotal().setText(df.format(sumaTotal()));
                    System.out.print("\n\n\n Precio:" + res_precio + "\n\n\n");                	
                }                
            }
        });
		
		vistaConfigurador.getComboRam().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String objetoSeleccionado =  (String) vistaConfigurador.getComboRam().getSelectedItem();
                System.out.print(objetoSeleccionado);
                if (objetoSeleccionado != "Selecciona...") {
                    double res_precio = conexion.consultarPrecioProducto(objetoSeleccionado);
                    precioMemoriaRam = res_precio;
                    nombreMemoriaRam = objetoSeleccionado;
                    String precioTotal_str = Double.toString(sumaTotal());
                    vistaConfigurador.getLblPrecioTotal().setText(df.format(sumaTotal()));
                    System.out.print("\n\n\n Precio:" + res_precio + "\n\n\n");                	
                }                
            }
        });
		
		vistaConfigurador.getComboDiscoDuro().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String objetoSeleccionado =  (String) vistaConfigurador.getComboDiscoDuro().getSelectedItem();
                System.out.print(objetoSeleccionado);
                if (objetoSeleccionado != "Selecciona...") {
                    double res_precio = conexion.consultarPrecioProducto(objetoSeleccionado);
                    precioDiscoDuro = res_precio;
                    nombreDiscoDuro = objetoSeleccionado;
                    String precioTotal_str = Double.toString(sumaTotal());
                    vistaConfigurador.getLblPrecioTotal().setText(df.format(sumaTotal()));
                    System.out.print("\n\n\n Precio:" + res_precio + "\n\n\n");                	
                }                
            }
        });
		
		vistaConfigurador.getComboGrafica().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String objetoSeleccionado =  (String) vistaConfigurador.getComboGrafica().getSelectedItem();
                System.out.print(objetoSeleccionado);
                if (objetoSeleccionado != "Selecciona...") {
                    double res_precio = conexion.consultarPrecioProducto(objetoSeleccionado);
                    precioTarjetaGrafica = res_precio;
                    nombreTarjetaGrafica = objetoSeleccionado;
                    String precioTotal_str = Double.toString(sumaTotal());
                    vistaConfigurador.getLblPrecioTotal().setText(df.format(sumaTotal()));
                    System.out.print("\n\n\n Precio:" + res_precio + "\n\n\n");                	
                }                
            }
        });
		
		vistaConfigurador.getComboFuente().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String objetoSeleccionado =  (String) vistaConfigurador.getComboFuente().getSelectedItem();
                System.out.print(objetoSeleccionado);
                if (objetoSeleccionado != "Selecciona...") {
                    double res_precio = conexion.consultarPrecioProducto(objetoSeleccionado);
                    precioFuenteAlimentacion = res_precio;
                    nombreFuenteAlimentacion = objetoSeleccionado;
                    String precioTotal_str = Double.toString(sumaTotal());
                    vistaConfigurador.getLblPrecioTotal().setText(df.format(sumaTotal()));
                    System.out.print("\n\n\n Precio:" + res_precio + "\n\n\n");                	
                }                
            }
        });
		
		vistaConfigurador.getComboCaja().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String objetoSeleccionado =  (String) vistaConfigurador.getComboCaja().getSelectedItem();
                System.out.print(objetoSeleccionado);
                if (objetoSeleccionado != "Selecciona...") {
                    double res_precio = conexion.consultarPrecioProducto(objetoSeleccionado);
                    precioTorre = res_precio;
                    nombreTorre = objetoSeleccionado;
                    vistaConfigurador.getLblPrecioTotal().setText(df.format(sumaTotal()));
                    System.out.print("\n\n\n Precio:" + res_precio + "\n\n\n");                	
                }                
            }
        });
	}
	
	private void asignarIdSiNoSelecciona(String nombre, String nombreSelecciona, int id) {
	    if (nombre != null && !nombre.equals(nombreSelecciona) && !nombre.isEmpty()) {
	        id = buscarIdPorNombre(nombre);
	        idsComponentes.add(id);
	        System.out.print(id);
	    }
	}
	
	private void mostrarArrayIds() {
		int i =0;
		for (Integer id : idsComponentes) {
			System.out.print("[" + i + "] " + id);
			i++;
		}
	}
	
	public EventosConfigurador(VistaConfigurador vistaConfigurador, VistaDatosCliente vistaDatosCliente, VistaBuscadorCliente vistaBuscadorCliente) {
		this.vistaConfigurador = vistaConfigurador;
        vistaConfigurador.getComboPlacaBase().addItem("Selecciona...");
        vistaConfigurador.getComboProcesador().addItem("Selecciona...");
        vistaConfigurador.getComboVentilador().addItem("Selecciona...");
        vistaConfigurador.getComboRefrigeracion().addItem("Selecciona...");
        vistaConfigurador.getComboRam().addItem("Selecciona...");
        vistaConfigurador.getComboDiscoDuro().addItem("Selecciona...");
        vistaConfigurador.getComboGrafica().addItem("Selecciona...");
        vistaConfigurador.getComboFuente().addItem("Selecciona...");
        vistaConfigurador.getComboCaja().addItem("Selecciona...");
		escucharEventos();
        
		ResultSet resultado = conexion.consultarReporteVentas(1);
		vistaConfigurador.getBtnProceder().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					asignarIdSiNoSelecciona(nombrePlaca, "Selecciona...", idPlaca);
					asignarIdSiNoSelecciona(nombreProcesador, "Selecciona...", idProcesador);
					asignarIdSiNoSelecciona(nombreVentilador, "Selecciona...", idVentilador);
					asignarIdSiNoSelecciona(nombreRefrigeracion, "Selecciona...", idRefrigeracion);
					asignarIdSiNoSelecciona(nombreMemoriaRam, "Selecciona...", idMemoriaRam);
					asignarIdSiNoSelecciona(nombreDiscoDuro, "Selecciona...", idDiscoDuro);
					asignarIdSiNoSelecciona(nombreTarjetaGrafica, "Selecciona...", idTarjetaGrafica);
					asignarIdSiNoSelecciona(nombreFuenteAlimentacion, "Selecciona...", idFuenteAlimentacion);
					asignarIdSiNoSelecciona(nombreTorre, "Selecciona...", idTorre);
					
					mostrarArrayIds();

				} catch(Exception exErrorProceder) {
					System.out.print(exErrorProceder);
				}
				
                String[] opciones = {"Nuevo Cliente", "Cliente Existente", "Invitado"};

                String seleccion = (String) JOptionPane.showInputDialog(null, "Selecciona una opción:",
                        "Selector de Opciones", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

                if (seleccion != null) {
                	if (seleccion.contains("Nuevo Cliente")) {
                    	vistaDatosCliente.setVisible(true);
                	}
                	else if (seleccion.contains("Cliente Existente")) {
//                		mostrarListaClientes();
                		vistaBuscadorCliente.setVisible(true);
                	}
                	else if (seleccion.contains("Invitado")) {
//                		mostrarListaClientes();
                		System.out.print("Ejecutando directamente");
                        int id_cliente = 12;;
                        int id_empleado = 1;

                        int id_pedido = 0;
                        for (int id : idsComponentes) {
                        	int id_componente = id;
                        	int cantidad = 1;
                        	double precio = buscarPrecioPorId(id_componente);
                            double precio_redondeado = Math.round(precio * 100.0) / 100.0;
                            id_pedido = conexion.ejecutarProcedimientoProductos(id_empleado, id_cliente, cantidad, precio_redondeado, id_componente, 1, id_pedido);
                        }	
                	}
//                    JOptionPane.showMessageDialog(null, "Seleccionaste: " + seleccion, "Selección", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "No seleccionaste ninguna opción", "Mensaje", JOptionPane.WARNING_MESSAGE);
                }
            }
//		        for (Producto producto_t : productos) {
//		            System.out.println(producto_t.getNombre());
//		        }

		});
		vistaConfigurador.getBtnCargar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResultSet resultado  = conexion.consultarProductos();
				try {
				    while (resultado.next()) {
				        String nombreProducto = resultado.getString("nombre");
				        double precioProducto = resultado.getDouble("precio");
				        int stockProducto = resultado.getInt("stock");
				        String imagenProducto = resultado.getString("imagen");
				        int id_producto = resultado.getInt("id_producto");
		                Producto producto = new Producto(id_producto, nombreProducto, "-", precioProducto, imagenProducto, stockProducto);
		                productos.add(producto);

				        System.out.println("ID: " + id_producto);
				        System.out.println("Nombre: " + nombreProducto);
				        System.out.println("Precio: " + precioProducto);
				        System.out.println("Stock: " + stockProducto);
				        System.out.println("Imagen: " + imagenProducto);
				        Map<String, Runnable> comboMap = new HashMap<>();
				        comboMap.put("img/placabase", () -> vistaConfigurador.getComboPlacaBase().addItem(nombreProducto));
				        comboMap.put("img/procesador", () -> vistaConfigurador.getComboProcesador().addItem(nombreProducto));
				        comboMap.put("img/ventilador", () -> vistaConfigurador.getComboVentilador().addItem(nombreProducto));
				        comboMap.put("img/refrigerancion", () -> vistaConfigurador.getComboRefrigeracion().addItem(nombreProducto));
				        comboMap.put("img/memoriasRam", () -> vistaConfigurador.getComboRam().addItem(nombreProducto));
				        comboMap.put("img/discosDuros", () -> vistaConfigurador.getComboDiscoDuro().addItem(nombreProducto));
				        comboMap.put("img/tarjetasGraficas", () -> vistaConfigurador.getComboGrafica().addItem(nombreProducto));
				        comboMap.put("img/fuente", () -> vistaConfigurador.getComboFuente().addItem(nombreProducto));
				        comboMap.put("img/torre", () -> vistaConfigurador.getComboCaja().addItem(nombreProducto));
				        
				        for (Map.Entry<String, Runnable> entry : comboMap.entrySet()) {
				            String prefijo = entry.getKey();
				            Runnable addItemToCombo = entry.getValue();
				            if (imagenProducto.contains(prefijo)) {
				                addItemToCombo.run();
				                break;
				            }
				        }
				    }
				} catch (SQLException ee2) {
				    ee2.printStackTrace();
				} finally {
				    if (resultado != null) {
				        try {
				            resultado.close();
				        } catch (SQLException ee) {
				            ee.printStackTrace();
				        }
				    }
				}

			}
		});
	}
	
	

}
