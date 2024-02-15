import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

public class EventosConfigurador {
	private double precioTotal;
	private double precioPlaca, precioProcesador, precioVentilador, precioRefrigeracion, precioMemoriaRam, precioDiscoDuro, precioTarjetaGrafica, precioFuenteAlimentacion, precioTorre;
	
	private VistaConfigurador vistaConfigurador;
	private ConexionDB conexion = new ConexionDB();
	
	private double sumaTotal() {
		return precioPlaca + precioProcesador + precioVentilador + precioRefrigeracion + precioMemoriaRam + precioDiscoDuro + precioTarjetaGrafica + precioFuenteAlimentacion + precioTorre;
	}
	

	private static double parsearPrecio(double precio) {
	    return Math.round(precio * 100.0) / 100.0;
	}
	
	private void escucharEventos() {
		vistaConfigurador.getComboPlacaBase().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String objetoSeleccionado =  (String) vistaConfigurador.getComboPlacaBase().getSelectedItem();
                System.out.print(objetoSeleccionado);
                if (objetoSeleccionado != "Selecciona...") {
                    double res_precio = conexion.consultarPrecioProducto(objetoSeleccionado);
                    precioPlaca = parsearPrecio(res_precio);
                    String precioTotal_str = Double.toString(sumaTotal());
                    vistaConfigurador.getLblPrecioTotal().setText(precioTotal_str);
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
                    precioProcesador = parsearPrecio(res_precio);
                    String precioTotal_str = Double.toString(sumaTotal());
                    vistaConfigurador.getLblPrecioTotal().setText(precioTotal_str);
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
                    precioVentilador = parsearPrecio(res_precio);
                    String precioTotal_str = Double.toString(sumaTotal());
                    vistaConfigurador.getLblPrecioTotal().setText(precioTotal_str);
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
                    precioRefrigeracion = parsearPrecio(res_precio);
                    String precioTotal_str = Double.toString(sumaTotal());
                    vistaConfigurador.getLblPrecioTotal().setText(precioTotal_str);
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
                    precioMemoriaRam = parsearPrecio(res_precio);
                    String precioTotal_str = Double.toString(sumaTotal());
                    vistaConfigurador.getLblPrecioTotal().setText(precioTotal_str);
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
                    precioDiscoDuro = parsearPrecio(res_precio);
                    String precioTotal_str = Double.toString(sumaTotal());
                    vistaConfigurador.getLblPrecioTotal().setText(precioTotal_str);
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
                    precioTarjetaGrafica = parsearPrecio(res_precio);
                    String precioTotal_str = Double.toString(sumaTotal());
                    vistaConfigurador.getLblPrecioTotal().setText(precioTotal_str);
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
                    precioFuenteAlimentacion = parsearPrecio(res_precio);
                    String precioTotal_str = Double.toString(sumaTotal());
                    vistaConfigurador.getLblPrecioTotal().setText(precioTotal_str);
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
                    precioTorre = parsearPrecio(res_precio);
                    String precioTotal_str = Double.toString(sumaTotal());
                    vistaConfigurador.getLblPrecioTotal().setText(precioTotal_str);
                    System.out.print("\n\n\n Precio:" + res_precio + "\n\n\n");                	
                }                
            }
        });
	}
	
	public EventosConfigurador(VistaConfigurador vistaConfigurador) {
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
		vistaConfigurador.getBtnCargar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResultSet resultado  = conexion.consultarProductos();
				try {
				    while (resultado.next()) {
				        String nombreProducto = resultado.getString("nombre");
				        double precioProducto = resultado.getDouble("precio");
				        int stockProducto = resultado.getInt("stock");
				        String imagenProducto = resultado.getString("imagen");
				        

				        System.out.println("Nombre: " + nombreProducto);
				        System.out.println("Precio: " + precioProducto);
				        System.out.println("Stock: " + stockProducto);
				        System.out.println("Imagen: " + imagenProducto);


				        if (imagenProducto.contains("img/placabase")) {
				            vistaConfigurador.getComboPlacaBase().addItem(nombreProducto);
				        }
				        else if (imagenProducto.contains("img/procesador")) {
				            vistaConfigurador.getComboProcesador().addItem(nombreProducto);
				        }
				        else if (imagenProducto.contains("img/ventilador")) {
				            vistaConfigurador.getComboVentilador().addItem(nombreProducto);
				        }
				        else if (imagenProducto.contains("img/refrigerancion")) {
				            vistaConfigurador.getComboRefrigeracion().addItem(nombreProducto);
				        }
				        else if (imagenProducto.contains("img/memoriasRam")) {
				            vistaConfigurador.getComboRam().addItem(nombreProducto);
				        }
				        else if (imagenProducto.contains("img/discosDuros")) {
				            vistaConfigurador.getComboDiscoDuro().addItem(nombreProducto);
				        }
				        else if (imagenProducto.contains("img/tarjetasGraficas")) {
				            vistaConfigurador.getComboGrafica().addItem(nombreProducto);
				        }
				        else if (imagenProducto.contains("img/fuente")) {
				            vistaConfigurador.getComboFuente().addItem(nombreProducto);
				        }
				        else if (imagenProducto.contains("img/torre")) {
				            vistaConfigurador.getComboCaja().addItem(nombreProducto);
				        }

				    }
				} catch (SQLException ee2) {
				    // Manejo de excepciones
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
