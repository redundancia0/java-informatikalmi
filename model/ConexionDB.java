import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import oracle.jdbc.OracleTypes;

public class ConexionDB {
    private static final String URL = "jdbc:oracle:thin:@54.164.171.161:1521:ORCLCDB";
    private static final String USUARIO = "informatikalmi";
    private static final String CONTRASENA = "Almi12345";
    private Connection conexion;

    public ConexionDB() {
    }
    
    public Connection realizarConexion() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
            System.out.println("Conexión exitosa a la base de datos Oracle.");
        } catch (ClassNotFoundException e) {
            System.err.println("Error al cargar el controlador JDBC: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Error al establecer la conexión con la base de datos: " + e.getMessage());
        }
		return conexion;
    }

    public boolean consultarEmpleado(String dni, String password) {
    	conexion = realizarConexion();
        String consulta = "SELECT * FROM empleados WHERE dni = ? AND password = ?";
        try {
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setString(1, dni);
            statement.setString(2, password);
            ResultSet resultado = statement.executeQuery();
            if (resultado.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar la base de datos: " + e.getMessage());
        }
        return false;
    }
    
    public boolean insertarPedidoCliente(String dni, String password) {
    	conexion = realizarConexion();
        String consulta = "SELECT * FROM empleados WHERE dni = ? AND password = ?";
        try {
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setString(1, dni);
            statement.setString(2, password);
            ResultSet resultado = statement.executeQuery();
            if (resultado.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar la base de datos: " + e.getMessage());
        }
        return false;
    }
    
    
    public boolean insertarCliente(String dni, String nombre, String apellido_1, String apellido_2, String pais, int codigo_postal, String calle, String correo, int telefono) {
        conexion = realizarConexion();
        String procedimiento = "{CALL gestionarClientes(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        try {
            CallableStatement statement = conexion.prepareCall(procedimiento);
            statement.setString(1, dni);
            statement.setString(2, nombre);
            statement.setString(3, apellido_1);
            statement.setString(4, apellido_2);
            statement.setString(5, pais);
            statement.setInt(6, codigo_postal);
            statement.setString(7, calle);
            statement.setString(8, correo);
            statement.setInt(9, telefono);

            boolean resultado = statement.execute();
            return resultado;
        } catch (SQLException e) {
            System.err.println("Error al insertar cliente en la base de datos: " + e.getMessage());
        } finally {
            try {
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
        return false;
    }


    
    public ResultSet consultarProductos() {
    	conexion = realizarConexion();
        ResultSet resultado = null;
        String consulta = "SELECT nombre, precio, stock, descripcion, imagen, id_producto FROM productos";
        try {
            PreparedStatement statement = conexion.prepareStatement(consulta);
            resultado = statement.executeQuery();
        } catch (SQLException e) {
            System.err.println("Error al consultar la base de datos: " + e.getMessage());
        }
        return resultado;
    }
    
    
    public ResultSet consultarComponentesBruto() {
    	conexion = realizarConexion();
        ResultSet resultado = null;
        String consulta = "SELECT * FROM vista_componentes";
        try {
            PreparedStatement statement = conexion.prepareStatement(consulta);
            resultado = statement.executeQuery();
        } catch (SQLException e) {
            System.err.println("Error al consultar la base de datos: " + e.getMessage());
        }
        return resultado;
    }
    
    public double consultarPrecioProducto(String nombreProducto) {
        Connection conexion = null;
        ResultSet resultado = null;
        String consulta = "SELECT precio FROM productos WHERE nombre LIKE ?";
        try {
            conexion = realizarConexion();
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setString(1, nombreProducto);
            resultado = statement.executeQuery();
            
            if (resultado.next()) {
                double precio = resultado.getDouble("precio");
                return precio;
            } else {
                return 0;
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar la base de datos: " + e.getMessage());
            return -1;
        } finally {
            try {
                if (resultado != null) resultado.close();
                if (conexion != null) conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public int ejecutarProcedimientoProductos(int p_id_empleado, int p_id_cliente, int p_cantidad_producto, double p_precio_venta, int p_id_producto, int p_es_venta, int id_pedido) {        
        Connection conexion = realizarConexion(); // Assuming `conexion` is a Connection object declared outside this method.

        try {
            CallableStatement callableStatement = conexion.prepareCall("{ ? = call procesar_pedido2(?, ?, ?, ?, ?, ?, ?) }");

            callableStatement.registerOutParameter(1, Types.INTEGER);

            callableStatement.setInt(2, p_id_empleado);
            callableStatement.setInt(3, p_id_cliente);
            callableStatement.setInt(4, p_cantidad_producto);
            callableStatement.setDouble(5, p_precio_venta);
            callableStatement.setInt(6, p_id_producto);
            callableStatement.setInt(7, p_es_venta);
            callableStatement.setInt(8, id_pedido);

            callableStatement.execute();

            return callableStatement.getInt(1);
        } catch (SQLException e) {
            System.err.println("Error al ejecutar el procedimiento almacenado: " + e.getMessage());
            return -1;
        }
    }


    
    public ResultSet consultarProductos(String consultaSQL) {
        conexion = realizarConexion();
        ResultSet resultado = null;
        try {
            PreparedStatement statement = conexion.prepareStatement(consultaSQL);
            resultado = statement.executeQuery();
        } catch (SQLException e) {
            System.err.println("Error al consultar la base de datos: " + e.getMessage());
        }
        return resultado;
    }

    public ResultSet consultarReporteVentas(int id_venta) {
        conexion = realizarConexion();
        ResultSet resultado = null;
        System.out.print("entro");
        try {
        	CallableStatement callableStatement = conexion.prepareCall("{call GENERAR_REPORTE_VENTAS(?,?)}");
            callableStatement.setInt(1, id_venta);
            callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
            callableStatement.execute();
            resultado = (ResultSet) callableStatement.getObject(2);
            while (resultado.next()) {
            	String nombreProducto = resultado.getString("nombre_producto");
            	int cantidadProducto = resultado.getInt("cantidad_producto");
            	double precioVenta = resultado.getDouble("precio_venta");
            	System.out.print(nombreProducto + cantidadProducto + precioVenta);
            }
            System.out.print("salgo");
//        	PreparedStatement statement = conexion.prepareStatement(consulta);
//            resultado = statement.executeQuery();
        } catch (SQLException e) {
            System.err.println("Error al consultar la base de datos: " + e.getMessage());
        }
        return resultado;
    }
    
    public ResultSet consultarProcesadores() {
        String consulta = "SELECT nombre, precio FROM procesadores INNER JOIN productos ON productos.id_producto = procesadores.id_producto";
        return consultarProductos(consulta);
    }

    public ResultSet consultarRefrigeraciones() {
        String consulta = "SELECT nombre, precio FROM refrigeraciones_liquidas INNER JOIN productos ON productos.id_producto = refrigeraciones_liquidas.id_producto";
        return consultarProductos(consulta);
    }

    public ResultSet consultarMemoriasRam() {
        String consulta = "SELECT nombre, precio FROM memorias_ram INNER JOIN productos ON productos.id_producto = memorias_ram.id_producto";
        return consultarProductos(consulta);
    }

    public ResultSet consultarProductosSimple(String nombreTabla) {
    	conexion = realizarConexion();
        ResultSet resultado = null;
        String consulta = "SELECT p.id_producto, nombre, descripcion, precio, stock, imagen FROM " + nombreTabla + " p INNER JOIN productos pr ON p.id_producto = pr.id_producto";
        try {
            PreparedStatement statement = conexion.prepareStatement(consulta);
            resultado = statement.executeQuery();
        } catch (SQLException e) {
            System.err.println("Error al consultar la base de datos: " + e.getMessage());
        }
        return resultado;
    }
    
    public ResultSet consultarProductosDescripcion(String tipo_producto, int id_producto) {
    	conexion = realizarConexion();
        ResultSet resultado = null;
        String consulta = "";
        
        System.out.print("Tipo producto " + tipo_producto);

        switch (tipo_producto) {
	        case "Placas Base":
	            consulta = "SELECT productos.id_producto, productos.nombre, productos.precio, productos.stock, productos.descripcion, placas_bases.memoria, placas_bases.velocidad, placas_bases.tipo_memoria " +
	                    "FROM placas_bases " +
	                    "INNER JOIN productos ON productos.id_producto = placas_bases.id_producto " +
	                    "WHERE productos.id_producto = ?";
	            break;
	            
	        case "Tarjetas Gráficas":
	            consulta = "SELECT productos.id_producto, productos.nombre, productos.precio, productos.stock, productos.descripcion, tarjetas_graficas.memoria_ram, tarjetas_graficas.nucleos, tarjetas_graficas.tipo_memoria "
	            		+ "FROM tarjetas_graficas "
	            		+ "INNER JOIN productos ON productos.id_producto = tarjetas_graficas.id_producto "
	            		+ "WHERE productos.id_producto = ?";
	            break;
	            
	        case "Procesadores":
	            consulta = "SELECT productos.id_producto, productos.nombre, productos.precio, productos.stock, productos.descripcion,  procesadores.velocidad, procesadores.nucleos "
	            		+ "FROM procesadores "
	            		+ "INNER JOIN productos ON productos.id_producto = procesadores.id_producto "
	            		+ "WHERE productos.id_producto = ?";
	            break;
	            
	        case "Tarjetas Sonido":
	            consulta = "SELECT productos.id_producto, productos.nombre, productos.precio, productos.stock, productos.descripcion, tarjetas_sonidos.tipo_conexion, tarjetas_sonidos.senal_ruido "
	            		+ "FROM tarjetas_sonidos "
	            		+ "INNER JOIN productos ON productos.id_producto = tarjetas_sonidos.id_producto "
	            		+ "WHERE productos.id_producto = ?";
	            break;
	            
	        case "Discos Duros":
	            consulta = "SELECT productos.id_producto, productos.nombre, productos.precio, productos.stock, productos.descripcion,   discos_duros.memoria, discos_duros.velocidad, discos_duros.tipo_disco "
	            		+ "FROM discos_duros "
	            		+ "INNER JOIN productos ON productos.id_producto = discos_duros.id_producto "
	            		+ "WHERE productos.id_producto = ?";
	            break;
	            
	        case "Memorias RAM":
	            consulta = "SELECT productos.id_producto, productos.nombre, productos.precio, productos.stock, productos.descripcion, memorias_ram.memoria, memorias_ram.tipo_memoria "
	            		+ "FROM memorias_ram "
	            		+ "INNER JOIN productos ON productos.id_producto = memorias_ram.id_producto "
	            		+ "WHERE productos.id_producto = ?";
	            break;
	            
	        case "Refrigeraciones Liquidas":
	            consulta = "SELECT productos.id_producto, productos.nombre, productos.precio, productos.stock, productos.descripcion, refrigeraciones_liquidas.tamano, refrigeraciones_liquidas.velocidad, refrigeraciones_liquidas.peso, refrigeraciones_liquidas.tipo_liquido "
	            		+ "FROM refrigeraciones_liquidas "
	            		+ "INNER JOIN productos ON productos.id_producto = refrigeraciones_liquidas.id_producto "
	            		+ "WHERE productos.id_producto = ?";
	            break;
	            
	        case "Periféricos":
	            consulta = "SELECT productos.id_producto, productos.nombre, productos.precio, productos.stock, productos.descripcion, perifericos.tipo_conexion "
	            		+ "FROM perifericos "
	            		+ "INNER JOIN productos ON productos.id_producto = perifericos.id_producto "
	            		+ "WHERE productos.id_producto = ?";
	            break;
	            
	        case "Torres":
	            consulta = "SELECT productos.id_producto, productos.nombre, productos.precio, productos.stock, productos.descripcion, torres.tipo_conexion, torres.tamano, torres.peso "
	            		+ "FROM torres "
	            		+ "INNER JOIN productos ON productos.id_producto = torres.id_producto "
	            		+ "WHERE productos.id_producto = ?";
	            break;
	            
	        case "Ventiladores":
	            consulta = "SELECT productos.id_producto, productos.nombre, productos.precio, productos.stock, productos.descripcion, ventiladores.velocidad, ventiladores.tamano, ventiladores.peso "
	            		+ "FROM ventiladores "
	            		+ "INNER JOIN productos ON productos.id_producto = ventiladores.id_producto "
	            		+ "WHERE productos.id_producto = ?";
	            break;
	            
	        case "Fuentes Alimentación":
	            consulta = "SELECT productos.id_producto, productos.nombre, productos.precio, productos.stock, productos.descripcion, fuentes_alimentacion.potencia "
	            		+ "FROM fuentes_alimentacion "
	            		+ "INNER JOIN productos ON productos.id_producto = fuentes_alimentacion.id_producto "
	            		+ "WHERE productos.id_producto = ?";
	            break;
	
	        default:
	            System.err.println("Tipo de producto no reconocido.");
	            return null;
	    }
        try {
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setInt(1, id_producto);
            resultado = statement.executeQuery();
        } catch (SQLException e) {
            System.err.println("Error al consultar la base de datos: " + e.getMessage());
        }
        return resultado;
    }
    
    public ResultSet consultarClienteEspecifico(String criterio) {
        conexion = realizarConexion();
        ResultSet resultado = null;
        String consulta = "SELECT id_cliente, dni, nombre, apellido1, apellido2, pais FROM clientes WHERE LOWER(dni) LIKE ? OR LOWER(nombre) LIKE ? OR LOWER(apellido1) LIKE ? OR LOWER(apellido2) LIKE ?";
        try {
            PreparedStatement statement = conexion.prepareStatement(consulta);
            String parametro = "%" + criterio + "%";
            for (int i = 1; i <= 4; i++) {
                statement.setString(i, parametro);
            }
            resultado = statement.executeQuery();
        } catch (SQLException e) {
            System.err.println("Error al consultar la base de datos: " + e.getMessage());
        }
        return resultado;
    }
    
    public ResultSet consultarFacturaEspecifica(String criterio) {
        conexion = realizarConexion();
        ResultSet resultado = null;
        String consulta = "SELECT id_cliente, dni, nombre, apellido1, apellido2, pais FROM clientes WHERE LOWER(dni) LIKE ? OR LOWER(nombre) LIKE ? OR LOWER(apellido1) LIKE ? OR LOWER(apellido2) LIKE ?";
        try {
            PreparedStatement statement = conexion.prepareStatement(consulta);
            String parametro = "%" + criterio + "%";
            for (int i = 1; i <= 4; i++) {
                statement.setString(i, parametro);
            }
            resultado = statement.executeQuery();
        } catch (SQLException e) {
            System.err.println("Error al consultar la base de datos: " + e.getMessage());
        }
        return resultado;
    }
    
    public ResultSet consultarClientesTotal() {
        conexion = realizarConexion();
        ResultSet resultado = null;
        String consulta = "SELECT id_cliente, dni, nombre, apellido1, apellido2, pais FROM clientes";
        try {
            PreparedStatement statement = conexion.prepareStatement(consulta);
            resultado = statement.executeQuery();
        } catch (SQLException e) {
            System.err.println("Error al consultar la base de datos: " + e.getMessage());
        }
        return resultado;
    }
    
    
    public ResultSet obtenerDetallesProductos(int id_pedido_cliente) {
        conexion = realizarConexion();
        ResultSet resultado = null;
        String consulta = "SELECT productos.nombre, pedidos_clientes_productos.id_producto, pedidos_clientes_productos.cantidad_producto, pedidos_clientes_productos.precio_venta, (SELECT SUM(cantidad_producto * precio_venta) FROM pedidos_clientes_productos WHERE id_pedido_cliente = 118) AS total_precio_venta FROM pedidos_clientes_productos  INNER JOIN productos ON productos.id_producto = pedidos_clientes_productos.id_producto  WHERE id_pedido_cliente = ?";
        try {
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setInt(1, id_pedido_cliente);
            resultado = statement.executeQuery();
        } catch (SQLException e) {
            System.err.println("Error al consultar la base de datos: " + e.getMessage());
        }
        return resultado;
    }

    public ResultSet insertarCliente() {
    	conexion = realizarConexion();
        ResultSet resultado = null;
        String consulta = "SELECT * FROM placas_base";
        try {
            PreparedStatement statement = conexion.prepareStatement(consulta);
            resultado = statement.executeQuery();
        } catch (SQLException e) {
            System.err.println("Error al consultar la base de datos: " + e.getMessage());
        }
        return resultado;
    }
    
    public Connection obtenerConexion() {
        return conexion;
    }
    
    public ResultSet consultarClientes() {
    	conexion = realizarConexion();
        ResultSet resultado = null;
        String consulta = "SELECT id_cliente, nombre, apellido1, apellido2 FROM clientes";
        try {
            PreparedStatement statement = conexion.prepareStatement(consulta);
            resultado = statement.executeQuery();
        } catch (SQLException e) {
            System.err.println("Error al consultar la base de datos: " + e.getMessage());
        }
        return resultado;
    }
    
    public ResultSet consultarPedidos() {
    	conexion = realizarConexion();
        ResultSet resultado = null;
        String consulta = "SELECT id_pedido_cliente, id_empleado, id_cliente, fecha_pedido FROM pedidos_clientes";
        try {
            PreparedStatement statement = conexion.prepareStatement(consulta);
            resultado = statement.executeQuery();
        } catch (SQLException e) {
            System.err.println("Error al consultar la base de datos: " + e.getMessage());
        }
        return resultado;
    }
    
    public List<String[]> consultarPedidoEspecifico(int id_pedido_cliente) {
        conexion = realizarConexion();
        List<String[]> resultados = new ArrayList<>();
        String consulta = "SELECT id_pedido_cliente_producto, cantidad_producto, precio_venta, id_producto, id_pedido_cliente FROM pedidos_clientes_productos WHERE id_pedido_cliente = ?";
        try {
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setInt(1, id_pedido_cliente);
            ResultSet resultado = statement.executeQuery();

            while (resultado.next()) {
                String[] fila = new String[5]; 
                fila[0] = resultado.getString("id_pedido_cliente_producto");
                fila[1] = resultado.getString("cantidad_producto");
                fila[2] = resultado.getString("precio_venta");
                fila[3] = resultado.getString("id_producto");
                fila[4] = resultado.getString("id_pedido_cliente");
                resultados.add(fila);
            }

            System.out.println("Número de filas en el resultado: " + resultados.size());

        } catch (SQLException e) {
            System.err.println("Error al consultar la base de datos: " + e.getMessage());
        }
        return resultados;
    }
    
    
    public List<String[]> consultarClienteID(int id_pedido_cliente) {
        conexion = realizarConexion();
        List<String[]> resultados = new ArrayList<>();
        String consulta = "SELECT C.nombre, C.apellido1, C.apellido2, PC.fecha_pedido FROM pedidos_clientes PC INNER JOIN clientes C ON C.id_cliente = PC.id_cliente WHERE PC.id_pedido_cliente = ?";
        try {
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setInt(1, id_pedido_cliente);
            ResultSet resultado = statement.executeQuery();

            while (resultado.next()) {
                String[] fila = new String[5]; 
                fila[0] = resultado.getString("nombre");
                fila[1] = resultado.getString("apellido1");
                fila[2] = resultado.getString("apellido2");
                fila[3] = resultado.getString("fecha_pedido");
                System.out.print(fila[0]);
                resultados.add(fila);
            }

            System.out.println("Número de filas en el resultado: " + resultados.size());

        } catch (SQLException e) {
            System.err.println("Error al consultar la base de datos: " + e.getMessage());
        }
        return resultados;
    }
    
    public void cerrarConexion() {
        if (conexion != null) {
            try {
                conexion.close();
                System.out.println("Conexión cerrada correctamente.");
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
    	try {
            ConexionDB conexionDB = new ConexionDB();
            System.out.print(conexionDB.consultarEmpleado("12345678A", "1234"));;
            conexionDB.cerrarConexion();	
    	} catch (Exception exConDB) {
    		JOptionPane.showMessageDialog(null, "Fallo al conectase a la DB!");
    		System.exit(0);
    	}
    }
}
