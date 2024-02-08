import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConexionDB {
    private static final String URL = "jdbc:oracle:thin:@54.164.171.161:1521:ORCLCDB";
    private static final String USUARIO = "informatikalmi";
    private static final String CONTRASENA = "Almi12345";

    private Connection conexion;

    public ConexionDB() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
            System.out.println("Conexión exitosa a la base de datos Oracle.");
        } catch (ClassNotFoundException e) {
            System.err.println("Error al cargar el controlador JDBC: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Error al establecer la conexión con la base de datos: " + e.getMessage());
        }
    }

    public boolean consultarEmpleado(String dni, String password) {
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
    
    public ResultSet consultarProductos() {
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
    
    public ResultSet consultarProductosSimple(String nombreTabla) {
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
    
    public ResultSet consultarClienteEspecifico(String DNI) {
        ResultSet resultado = null;
        String consulta = "SELECT id_cliente, dni, nombre, apellido1, apellido2, pais FROM clientes WHERE dni = ?";
        try {
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setString(1, DNI);
            resultado = statement.executeQuery();
        } catch (SQLException e) {
            System.err.println("Error al consultar la base de datos: " + e.getMessage());
        }
        return resultado;
    }

    
    public ResultSet insertarCliente() {
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
