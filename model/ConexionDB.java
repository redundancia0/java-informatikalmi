import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
        String consulta = "INSERT INTO clientes (dni, nombre, apellido1, apellido2, pais, codigopostal, calle, correo, telefono) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setString(1, dni);
            statement.setString(2, nombre);
            statement.setString(3, apellido_1);
            statement.setString(4, apellido_2);
            statement.setString(5, pais);
            statement.setInt(6, codigo_postal);
            statement.setString(7, calle);
            statement.setString(8, correo);
            statement.setInt(9, telefono);

            int filasInsertadas = statement.executeUpdate();

            if (filasInsertadas > 0) {
                return true;
            }
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
        String consulta = "SELECT nombre, precio, stock, descripcion, imagen FROM productos";
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
            // Establecer conexión
            conexion = realizarConexion();
            PreparedStatement statement = conexion.prepareStatement(consulta);
            // Asignar parámetro
            statement.setString(1, nombreProducto);
            // Ejecutar consulta
            resultado = statement.executeQuery();
            
            // Si hay resultados, obtener el precio y retornar como String
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
            // Cerrar recursos
            try {
                if (resultado != null) resultado.close();
                if (conexion != null) conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void ejecutarProcedimientoProductos(int p_id_empleado, int p_id_cliente, int p_cantidad_producto, double p_precio_venta, int p_id_producto, int p_es_venta) {        
        conexion = realizarConexion();
        
        try {
            CallableStatement callableStatement = conexion.prepareCall("{call procesar_pedido(?, ?, ?, ?, ?, ?)}");

            callableStatement.setInt(1, p_id_empleado);
            callableStatement.setInt(2, p_id_cliente);
            callableStatement.setInt(3, p_cantidad_producto);
            callableStatement.setDouble(4, p_precio_venta);
            callableStatement.setInt(5, p_id_producto);
            callableStatement.setInt(6, p_es_venta);
            callableStatement.execute();

            System.out.println("Procedimiento ejecutado correctamente.");

            callableStatement.close();
            conexion.close();
        } catch (SQLException e) {
            System.err.println("Error al ejecutar el procedimiento almacenado: " + e.getMessage());
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
        String consulta = "SELECT p.id_producto, nombre, precio, stock, imagen FROM " + nombreTabla + " p INNER JOIN productos pr ON p.id_producto = pr.id_producto";
        try {
            PreparedStatement statement = conexion.prepareStatement(consulta);
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
