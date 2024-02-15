import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

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
        String consulta = "SELECT \r\n"
        		+ "    p.nombre AS producto_nombre,\r\n"
        		+ "    p.precio AS producto_precio,\r\n"
        		+ "    p.stock AS producto_stock,\r\n"
        		+ "    p.imagen AS producto_imagen,\r\n"
        		+ "    pb.id_placa_base AS placa_id,\r\n"
        		+ "    pb.memoria AS placa_memoria,\r\n"
        		+ "    pb.velocidad AS placa_velocidad,\r\n"
        		+ "    pb.tipo_memoria AS placa_tipo_memoria,\r\n"
        		+ "    p.id_producto AS placa_id_producto,\r\n"
        		+ "    NULL AS procesador_id,\r\n"
        		+ "    NULL AS procesador_memoria,\r\n"
        		+ "    NULL AS procesador_velocidad,\r\n"
        		+ "    NULL AS procesador_nucleos,\r\n"
        		+ "    NULL AS procesador_id_producto,\r\n"
        		+ "    NULL AS ventilador_id,\r\n"
        		+ "    NULL AS ventilador_velocidad,\r\n"
        		+ "    NULL AS ventilador_tamano, \r\n"
        		+ "    NULL AS ventilador_peso,\r\n"
        		+ "    NULL AS ventilador_id_producto,\r\n"
        		+ "     NULL AS refrigeracion_liquida_id, \r\n"
        		+ "   NULL AS refrigeracion_liquida_tamano,\r\n"
        		+ "    NULL AS refrigeracion_liquida_velocidad,\r\n"
        		+ "   NULL AS refrigeracion_liquida_peso,\r\n"
        		+ "   NULL AS refrigeracion_liquida_tipo_liquido,\r\n"
        		+ "   NULL AS refrigeracion_liquida_id_producto,\r\n"
        		+ "    NULL AS   memorias_ram_id,\r\n"
        		+ "    NULL AS  memorias_ram_memoria,\r\n"
        		+ "    NULL AS  memorias_ram_tipo_memoria,\r\n"
        		+ "    NULL AS  memorias_ram_id_producto,\r\n"
        		+ "      NULL AS discos_duros_id, \r\n"
        		+ "    NULL AS discos_duros_memoria, \r\n"
        		+ "   NULL AS discos_duros_velocidad, \r\n"
        		+ "    NULL AS discos_duros_tipo_disco, \r\n"
        		+ "      NULL AS discos_duros_id_producto,\r\n"
        		+ "    NULL AS tarjetas_graficas_id, \r\n"
        		+ "    NULL AS tarjetas_graficas_memoria_ram,\r\n"
        		+ "    NULL AS tarjetas_graficas_nucleos,\r\n"
        		+ "    NULL AS tarjetas_graficas_tipo_memoria,\r\n"
        		+ "    NULL AS tarjetas_graficas_id_producto,\r\n"
        		+ "  NULL AS fuentes_alimentacion_id,\r\n"
        		+ "    NULL AS fuentes_alimentacion_potencia,\r\n"
        		+ "    NULL AS fuentes_alimentacion_id_producto,\r\n"
        		+ "     NULL AS torres_id_torre,\r\n"
        		+ "    NULL AS torres_tipo_conexion,\r\n"
        		+ "     NULL AS torres_peso,\r\n"
        		+ "     NULL AS torres_id_producto,\r\n"
        		+ "  NULL AS perifericos_id_periferico, \r\n"
        		+ "      NULL AS perifericos_tipo_conexion,\r\n"
        		+ "      NULL AS perifericos_id_producto\r\n"
        		+ "FROM placas_bases pb\r\n"
        		+ "INNER JOIN productos p ON p.id_producto = pb.id_placa_base\r\n"
        		+ "UNION ALL\r\n"
        		+ "SELECT \r\n"
        		+ "    p.nombre AS producto_nombre,\r\n"
        		+ "    p.precio AS producto_precio,\r\n"
        		+ "    p.stock AS producto_stock,\r\n"
        		+ "    p.imagen AS producto_imagen,\r\n"
        		+ "    NULL AS placa_id,\r\n"
        		+ "    NULL AS placa_memoria,\r\n"
        		+ "    NULL AS placa_velocidad,\r\n"
        		+ "    NULL AS placa_tipo_memoria,\r\n"
        		+ "    NULL AS placa_id_producto,\r\n"
        		+ "    pc.id_procesador AS procesador_id,\r\n"
        		+ "    pc.memoria AS procesador_memoria,\r\n"
        		+ "    pc.velocidad AS procesador_velocidad,\r\n"
        		+ "    pc.nucleos AS procesador_nucleos,\r\n"
        		+ "    pc.id_producto AS procesador_id_producto,\r\n"
        		+ "    NULL AS ventilador_id,\r\n"
        		+ "    NULL AS ventilador_velocidad,\r\n"
        		+ "    NULL AS ventilador_tamano, \r\n"
        		+ "    NULL AS ventilador_peso,\r\n"
        		+ "    NULL AS ventilador_id_producto,\r\n"
        		+ "    NULL AS refrigeracion_liquida_id, \r\n"
        		+ "    NULL AS refrigeracion_liquida_tamano,\r\n"
        		+ "    NULL AS refrigeracion_liquida_velocidad,\r\n"
        		+ "    NULL AS refrigeracion_liquida_peso,\r\n"
        		+ "    NULL AS refrigeracion_liquida_tipo_liquido,\r\n"
        		+ "    NULL AS refrigeracion_liquida_id_producto,\r\n"
        		+ "    NULL AS   memorias_ram_id,\r\n"
        		+ "    NULL AS  memorias_ram_memoria,\r\n"
        		+ "    NULL AS  memorias_ram_tipo_memoria,\r\n"
        		+ "    NULL AS  memorias_ram_id_producto,\r\n"
        		+ "      NULL AS discos_duros_id, \r\n"
        		+ "    NULL AS discos_duros_memoria, \r\n"
        		+ "   NULL AS discos_duros_velocidad, \r\n"
        		+ "    NULL AS discos_duros_tipo_disco, \r\n"
        		+ "      NULL AS discos_duros_id_producto,\r\n"
        		+ "    NULL AS tarjetas_graficas_id, \r\n"
        		+ "    NULL AS tarjetas_graficas_memoria_ram,\r\n"
        		+ "    NULL AS tarjetas_graficas_nucleos,\r\n"
        		+ "    NULL AS tarjetas_graficas_tipo_memoria,\r\n"
        		+ "    NULL AS tarjetas_graficas_id_producto,\r\n"
        		+ "  NULL AS fuentes_alimentacion_id,\r\n"
        		+ "    NULL AS fuentes_alimentacion_potencia,\r\n"
        		+ "    NULL AS fuentes_alimentacion_id_producto,\r\n"
        		+ "     NULL AS torres_id_torre,\r\n"
        		+ "    NULL AS torres_tipo_conexion,\r\n"
        		+ "     NULL AS torres_peso, \r\n"
        		+ "    NULL AS torres_id_producto,\r\n"
        		+ "  NULL AS perifericos_id_periferico, \r\n"
        		+ "      NULL AS perifericos_tipo_conexion,\r\n"
        		+ "      NULL AS perifericos_id_producto\r\n"
        		+ "FROM procesadores pc \r\n"
        		+ "INNER JOIN productos p ON p.id_producto = pc.id_procesador\r\n"
        		+ "UNION ALL\r\n"
        		+ "SELECT\r\n"
        		+ "    p.nombre AS producto_nombre,\r\n"
        		+ "    p.precio AS producto_precio,\r\n"
        		+ "    p.stock AS producto_stock,\r\n"
        		+ "    p.imagen AS producto_imagen,\r\n"
        		+ "    NULL AS placa_id,\r\n"
        		+ "    NULL AS placa_memoria,\r\n"
        		+ "    NULL AS placa_velocidad,\r\n"
        		+ "    NULL AS placa_tipo_memoria,\r\n"
        		+ "    NULL AS placa_id_producto,\r\n"
        		+ "    NULL AS procesador_id,\r\n"
        		+ "    NULL AS procesador_memoria,\r\n"
        		+ "    NULL AS procesador_velocidad,\r\n"
        		+ "    NULL AS procesador_nucleos,\r\n"
        		+ "    NULL AS procesador_id_producto,\r\n"
        		+ "    vnt.id_ventilador AS ventilador_id,\r\n"
        		+ "    vnt.velocidad AS ventilador_velocidad,\r\n"
        		+ "    vnt.tamano AS ventilador_tamano, \r\n"
        		+ "    vnt.peso AS ventilador_peso,\r\n"
        		+ "    vnt.id_producto AS ventilador_id_producto,\r\n"
        		+ "    NULL AS refrigeracion_liquida_id, \r\n"
        		+ "    NULL AS refrigeracion_liquida_tamano,\r\n"
        		+ "    NULL AS refrigeracion_liquida_velocidad,\r\n"
        		+ "    NULL AS refrigeracion_liquida_peso,\r\n"
        		+ "    NULL AS refrigeracion_liquida_tipo_liquido,\r\n"
        		+ "    NULL AS refrigeracion_liquida_id_producto,\r\n"
        		+ "    NULL AS   memorias_ram_id,\r\n"
        		+ "    NULL AS  memorias_ram_memoria,\r\n"
        		+ "    NULL AS  memorias_ram_tipo_memoria,\r\n"
        		+ "    NULL AS  memorias_ram_id_producto,\r\n"
        		+ "      NULL AS discos_duros_id, \r\n"
        		+ "    NULL AS discos_duros_memoria, \r\n"
        		+ "   NULL AS discos_duros_velocidad, \r\n"
        		+ "    NULL AS discos_duros_tipo_disco, \r\n"
        		+ "      NULL AS discos_duros_id_producto,\r\n"
        		+ "    NULL AS tarjetas_graficas_id, \r\n"
        		+ "    NULL AS tarjetas_graficas_memoria_ram,\r\n"
        		+ "    NULL AS tarjetas_graficas_nucleos,\r\n"
        		+ "    NULL AS tarjetas_graficas_tipo_memoria,\r\n"
        		+ "    NULL AS tarjetas_graficas_id_producto,\r\n"
        		+ "  NULL AS fuentes_alimentacion_id,\r\n"
        		+ "    NULL AS fuentes_alimentacion_potencia,\r\n"
        		+ "    NULL AS fuentes_alimentacion_id_producto,\r\n"
        		+ "     NULL AS torres_id_torre,\r\n"
        		+ "    NULL AS torres_tipo_conexion,\r\n"
        		+ "     NULL AS torres_peso,\r\n"
        		+ "    NULL AS torres_id_producto, \r\n"
        		+ "  NULL AS perifericos_id_periferico, \r\n"
        		+ "      NULL AS perifericos_tipo_conexion,\r\n"
        		+ "      NULL AS perifericos_id_producto\r\n"
        		+ "    FROM ventiladores vnt \r\n"
        		+ "    INNER JOIN productos p ON p.id_producto = vnt.id_ventilador\r\n"
        		+ "    UNION ALL\r\n"
        		+ "    SELECT\r\n"
        		+ "    p.nombre AS producto_nombre,\r\n"
        		+ "    p.precio AS producto_precio,\r\n"
        		+ "    p.stock AS producto_stock,\r\n"
        		+ "    p.imagen AS producto_imagen,\r\n"
        		+ "    NULL AS placa_id,\r\n"
        		+ "    NULL AS placa_memoria,\r\n"
        		+ "    NULL AS placa_velocidad,\r\n"
        		+ "    NULL AS placa_tipo_memoria,\r\n"
        		+ "    NULL AS placa_id_producto,\r\n"
        		+ "    NULL AS procesador_id,\r\n"
        		+ "    NULL AS procesador_memoria,\r\n"
        		+ "    NULL AS procesador_velocidad,\r\n"
        		+ "    NULL AS procesador_nucleos,\r\n"
        		+ "    NULL AS procesador_id_producto,\r\n"
        		+ "     NULL AS ventilador_id,\r\n"
        		+ "    NULL AS ventilador_velocidad,\r\n"
        		+ "    NULL AS ventilador_tamano, \r\n"
        		+ "    NULL AS ventilador_peso,\r\n"
        		+ "    NULL AS ventilador_id_producto,\r\n"
        		+ "    rfr.id_refrigeracion_liquida as refrigeracion_liquida_id, \r\n"
        		+ "    rfr.tamano as refrigeracion_liquida_tamano,\r\n"
        		+ "    rfr.velocidad as refrigeracion_liquida_velocidad,\r\n"
        		+ "    rfr.peso as refrigeracion_liquida_peso,\r\n"
        		+ "    rfr.tipo_liquido as refrigeracion_liquida_tipo_liquido,\r\n"
        		+ "    rfr.id_producto as refrigeracion_liquida_id_producto,\r\n"
        		+ "    NULL AS   memorias_ram_id,\r\n"
        		+ "    NULL AS  memorias_ram_memoria,\r\n"
        		+ "    NULL AS  memorias_ram_tipo_memoria,\r\n"
        		+ "    NULL AS  memorias_ram_id_producto,\r\n"
        		+ "      NULL AS discos_duros_id, \r\n"
        		+ "    NULL AS discos_duros_memoria, \r\n"
        		+ "   NULL AS discos_duros_velocidad, \r\n"
        		+ "    NULL AS discos_duros_tipo_disco, \r\n"
        		+ "      NULL AS discos_duros_id_producto,\r\n"
        		+ "    NULL AS tarjetas_graficas_id, \r\n"
        		+ "    NULL AS tarjetas_graficas_memoria_ram,\r\n"
        		+ "    NULL AS tarjetas_graficas_nucleos,\r\n"
        		+ "    NULL AS tarjetas_graficas_tipo_memoria,\r\n"
        		+ "    NULL AS tarjetas_graficas_id_producto,\r\n"
        		+ "  NULL AS fuentes_alimentacion_id,\r\n"
        		+ "    NULL AS fuentes_alimentacion_potencia,\r\n"
        		+ "    NULL AS fuentes_alimentacion_id_producto,\r\n"
        		+ "     NULL AS torres_id_torre,\r\n"
        		+ "    NULL AS torres_tipo_conexion,\r\n"
        		+ "     NULL AS torres_peso, \r\n"
        		+ "    NULL AS torres_id_producto,\r\n"
        		+ "  NULL AS perifericos_id_periferico, \r\n"
        		+ "      NULL AS perifericos_tipo_conexion,\r\n"
        		+ "      NULL AS perifericos_id_producto\r\n"
        		+ "FROM refrigeraciones_liquidas rfr \r\n"
        		+ "INNER JOIN productos p ON p.id_producto = rfr.id_refrigeracion_liquida\r\n"
        		+ "    UNION ALL\r\n"
        		+ "  SELECT\r\n"
        		+ "    p.nombre AS producto_nombre,\r\n"
        		+ "    p.precio AS producto_precio,\r\n"
        		+ "    p.stock AS producto_stock,\r\n"
        		+ "    p.imagen AS producto_imagen,\r\n"
        		+ "    NULL AS placa_id,\r\n"
        		+ "    NULL AS placa_memoria,\r\n"
        		+ "    NULL AS placa_velocidad,\r\n"
        		+ "    NULL AS placa_tipo_memoria,\r\n"
        		+ "    NULL AS placa_id_producto,\r\n"
        		+ "    NULL AS procesador_id,\r\n"
        		+ "    NULL AS procesador_memoria,\r\n"
        		+ "    NULL AS procesador_velocidad,\r\n"
        		+ "    NULL AS procesador_nucleos,\r\n"
        		+ "    NULL AS procesador_id_producto,\r\n"
        		+ "     NULL AS ventilador_id,\r\n"
        		+ "    NULL AS ventilador_velocidad,\r\n"
        		+ "    NULL AS ventilador_tamano, \r\n"
        		+ "    NULL AS ventilador_peso,\r\n"
        		+ "    NULL AS ventilador_id_producto,\r\n"
        		+ "    NULL AS refrigeracion_liquida_id, \r\n"
        		+ "    NULL AS refrigeracion_liquida_tamano,\r\n"
        		+ "    NULL AS refrigeracion_liquida_velocidad,\r\n"
        		+ "    NULL AS refrigeracion_liquida_peso,\r\n"
        		+ "    NULL AS refrigeracion_liquida_tipo_liquido,\r\n"
        		+ "    NULL AS refrigeracion_liquida_id_producto,\r\n"
        		+ "mr.id_memoria_ram AS  memorias_ram_id,\r\n"
        		+ " mr.memoria  AS memorias_ram_memoria,\r\n"
        		+ " mr.tipo_memoria AS memorias_ram_tipo_memoria,\r\n"
        		+ " mr.id_producto AS memorias_ram_id_producto,\r\n"
        		+ "      NULL AS discos_duros_id, \r\n"
        		+ "    NULL AS discos_duros_memoria, \r\n"
        		+ "   NULL AS discos_duros_velocidad, \r\n"
        		+ "    NULL AS discos_duros_tipo_disco, \r\n"
        		+ "      NULL AS discos_duros_id_producto,\r\n"
        		+ "    NULL AS tarjetas_graficas_id, \r\n"
        		+ "    NULL AS tarjetas_graficas_memoria_ram,\r\n"
        		+ "    NULL AS tarjetas_graficas_nucleos,\r\n"
        		+ "    NULL AS tarjetas_graficas_tipo_memoria,\r\n"
        		+ "    NULL AS tarjetas_graficas_id_producto,\r\n"
        		+ "  NULL AS fuentes_alimentacion_id,\r\n"
        		+ "    NULL AS fuentes_alimentacion_potencia,\r\n"
        		+ "    NULL AS fuentes_alimentacion_id_producto,\r\n"
        		+ "     NULL AS torres_id_torre,\r\n"
        		+ "    NULL AS torres_tipo_conexion,\r\n"
        		+ "     NULL AS torres_peso, \r\n"
        		+ "    NULL AS torres_id_producto,\r\n"
        		+ "  NULL AS perifericos_id_periferico, \r\n"
        		+ "      NULL AS perifericos_tipo_conexion,\r\n"
        		+ "      NULL AS perifericos_id_producto\r\n"
        		+ "FROM memorias_ram mr \r\n"
        		+ "INNER JOIN productos p ON p.id_producto = mr.id_producto\r\n"
        		+ "    UNION ALL\r\n"
        		+ "  SELECT\r\n"
        		+ "    p.nombre AS producto_nombre,\r\n"
        		+ "    p.precio AS producto_precio,\r\n"
        		+ "    p.stock AS producto_stock,\r\n"
        		+ "    p.imagen AS producto_imagen,\r\n"
        		+ "    NULL AS placa_id,\r\n"
        		+ "    NULL AS placa_memoria,\r\n"
        		+ "    NULL AS placa_velocidad,\r\n"
        		+ "    NULL AS placa_tipo_memoria,\r\n"
        		+ "    NULL AS placa_id_producto,\r\n"
        		+ "    NULL AS procesador_id,\r\n"
        		+ "    NULL AS procesador_memoria,\r\n"
        		+ "    NULL AS procesador_velocidad,\r\n"
        		+ "    NULL AS procesador_nucleos,\r\n"
        		+ "    NULL AS procesador_id_producto,\r\n"
        		+ "     NULL AS ventilador_id,\r\n"
        		+ "    NULL AS ventilador_velocidad,\r\n"
        		+ "    NULL AS ventilador_tamano, \r\n"
        		+ "    NULL AS ventilador_peso,\r\n"
        		+ "    NULL AS ventilador_id_producto,\r\n"
        		+ "    NULL AS refrigeracion_liquida_id, \r\n"
        		+ "    NULL AS refrigeracion_liquida_tamano,\r\n"
        		+ "    NULL AS refrigeracion_liquida_velocidad,\r\n"
        		+ "    NULL AS refrigeracion_liquida_peso,\r\n"
        		+ "    NULL AS refrigeracion_liquida_tipo_liquido,\r\n"
        		+ "    NULL AS refrigeracion_liquida_id_producto,\r\n"
        		+ "    NULL AS   memorias_ram_id,\r\n"
        		+ "    NULL AS  memorias_ram_memoria,\r\n"
        		+ "    NULL AS  memorias_ram_tipo_memoria,\r\n"
        		+ "    NULL AS  memorias_ram_id_producto,\r\n"
        		+ "    dd.id_disco_duro as discos_duros_id, \r\n"
        		+ "    dd.memoria as discos_duros_memoria, \r\n"
        		+ "    dd.velocidad as discos_duros_velocidad, \r\n"
        		+ "    dd.tipo_disco as discos_duros_tipo_disco, \r\n"
        		+ "    dd.id_producto as discos_duros_id_producto,\r\n"
        		+ "    NULL AS tarjetas_graficas_id, \r\n"
        		+ "    NULL AS tarjetas_graficas_memoria_ram,\r\n"
        		+ "    NULL AS tarjetas_graficas_nucleos,\r\n"
        		+ "    NULL AS tarjetas_graficas_tipo_memoria,\r\n"
        		+ "    NULL AS tarjetas_graficas_id_producto,\r\n"
        		+ "  NULL AS fuentes_alimentacion_id,\r\n"
        		+ "    NULL AS fuentes_alimentacion_potencia,\r\n"
        		+ "    NULL AS fuentes_alimentacion_id_producto,\r\n"
        		+ "     NULL AS torres_id_torre,\r\n"
        		+ "    NULL AS torres_tipo_conexion,\r\n"
        		+ "     NULL AS torres_peso,\r\n"
        		+ "     NULL AS torres_id_producto,\r\n"
        		+ "  NULL AS perifericos_id_periferico, \r\n"
        		+ "      NULL AS perifericos_tipo_conexion,\r\n"
        		+ "      NULL AS perifericos_id_producto\r\n"
        		+ "    FROM discos_duros dd\r\n"
        		+ "    INNER JOIN productos p ON p.id_producto = dd.id_producto\r\n"
        		+ "  UNION ALL\r\n"
        		+ "  SELECT\r\n"
        		+ "    p.nombre AS producto_nombre,\r\n"
        		+ "    p.precio AS producto_precio,\r\n"
        		+ "    p.stock AS producto_stock,\r\n"
        		+ "    p.imagen AS producto_imagen,\r\n"
        		+ "    NULL AS placa_id,\r\n"
        		+ "    NULL AS placa_memoria,\r\n"
        		+ "    NULL AS placa_velocidad,\r\n"
        		+ "    NULL AS placa_tipo_memoria,\r\n"
        		+ "    NULL AS placa_id_producto,\r\n"
        		+ "    NULL AS procesador_id,\r\n"
        		+ "    NULL AS procesador_memoria,\r\n"
        		+ "    NULL AS procesador_velocidad,\r\n"
        		+ "    NULL AS procesador_nucleos,\r\n"
        		+ "    NULL AS procesador_id_producto,\r\n"
        		+ "     NULL AS ventilador_id,\r\n"
        		+ "    NULL AS ventilador_velocidad,\r\n"
        		+ "    NULL AS ventilador_tamano, \r\n"
        		+ "    NULL AS ventilador_peso,\r\n"
        		+ "    NULL AS ventilador_id_producto,\r\n"
        		+ "    NULL AS refrigeracion_liquida_id, \r\n"
        		+ "    NULL AS refrigeracion_liquida_tamano,\r\n"
        		+ "    NULL AS refrigeracion_liquida_velocidad,\r\n"
        		+ "    NULL AS refrigeracion_liquida_peso,\r\n"
        		+ "    NULL AS refrigeracion_liquida_tipo_liquido,\r\n"
        		+ "    NULL AS refrigeracion_liquida_id_producto,\r\n"
        		+ "    NULL AS   memorias_ram_id,\r\n"
        		+ "    NULL AS  memorias_ram_memoria,\r\n"
        		+ "    NULL AS  memorias_ram_tipo_memoria,\r\n"
        		+ "    NULL AS  memorias_ram_id_producto,\r\n"
        		+ "      NULL AS discos_duros_id, \r\n"
        		+ "    NULL AS discos_duros_memoria, \r\n"
        		+ "   NULL AS discos_duros_velocidad, \r\n"
        		+ "    NULL AS discos_duros_tipo_disco, \r\n"
        		+ "      NULL AS discos_duros_id_producto,\r\n"
        		+ "     tg.id_tarjeta_grafica as tarjetas_graficas_id, \r\n"
        		+ "    tg.memoria_ram as tarjetas_graficas_memoria_ram,\r\n"
        		+ "    tg.nucleos as tarjetas_graficas_nucleos,\r\n"
        		+ "    tg.tipo_memoria as tarjetas_graficas_tipo_memoria,\r\n"
        		+ "     tg.id_producto as tarjetas_graficas_id_producto,\r\n"
        		+ "  NULL AS fuentes_alimentacion_id,\r\n"
        		+ "    NULL AS fuentes_alimentacion_potencia,\r\n"
        		+ "    NULL AS fuentes_alimentacion_id_producto,\r\n"
        		+ "     NULL AS torres_id_torre,\r\n"
        		+ "    NULL AS torres_tipo_conexion,\r\n"
        		+ "     NULL AS torres_peso,\r\n"
        		+ "     NULL AS torres_id_producto,\r\n"
        		+ "  NULL AS perifericos_id_periferico, \r\n"
        		+ "      NULL AS perifericos_tipo_conexion,\r\n"
        		+ "      NULL AS perifericos_id_producto\r\n"
        		+ "     FROM tarjetas_graficas tg\r\n"
        		+ "    INNER JOIN productos p ON p.id_producto = tg.id_producto\r\n"
        		+ "  UNION ALL\r\n"
        		+ "  SELECT\r\n"
        		+ "    p.nombre AS producto_nombre,\r\n"
        		+ "    p.precio AS producto_precio,\r\n"
        		+ "    p.stock AS producto_stock,\r\n"
        		+ "    p.imagen AS producto_imagen,\r\n"
        		+ "    NULL AS placa_id,\r\n"
        		+ "    NULL AS placa_memoria,\r\n"
        		+ "    NULL AS placa_velocidad,\r\n"
        		+ "    NULL AS placa_tipo_memoria,\r\n"
        		+ "    NULL AS placa_id_producto,\r\n"
        		+ "    NULL AS procesador_id,\r\n"
        		+ "    NULL AS procesador_memoria,\r\n"
        		+ "    NULL AS procesador_velocidad,\r\n"
        		+ "    NULL AS procesador_nucleos,\r\n"
        		+ "    NULL AS procesador_id_producto,\r\n"
        		+ "     NULL AS ventilador_id,\r\n"
        		+ "    NULL AS ventilador_velocidad,\r\n"
        		+ "    NULL AS ventilador_tamano, \r\n"
        		+ "    NULL AS ventilador_peso,\r\n"
        		+ "    NULL AS ventilador_id_producto,\r\n"
        		+ "    NULL AS refrigeracion_liquida_id, \r\n"
        		+ "    NULL AS refrigeracion_liquida_tamano,\r\n"
        		+ "    NULL AS refrigeracion_liquida_velocidad,\r\n"
        		+ "    NULL AS refrigeracion_liquida_peso,\r\n"
        		+ "    NULL AS refrigeracion_liquida_tipo_liquido,\r\n"
        		+ "    NULL AS refrigeracion_liquida_id_producto,\r\n"
        		+ "    NULL AS   memorias_ram_id,\r\n"
        		+ "    NULL AS  memorias_ram_memoria,\r\n"
        		+ "    NULL AS  memorias_ram_tipo_memoria,\r\n"
        		+ "    NULL AS  memorias_ram_id_producto,\r\n"
        		+ "      NULL AS discos_duros_id, \r\n"
        		+ "    NULL AS discos_duros_memoria, \r\n"
        		+ "   NULL AS discos_duros_velocidad, \r\n"
        		+ "    NULL AS discos_duros_tipo_disco, \r\n"
        		+ "      NULL AS discos_duros_id_producto,\r\n"
        		+ "      NULL AS tarjetas_graficas_id, \r\n"
        		+ "    NULL AS tarjetas_graficas_memoria_ram,\r\n"
        		+ "    NULL AS tarjetas_graficas_nucleos,\r\n"
        		+ "    NULL AS tarjetas_graficas_tipo_memoria,\r\n"
        		+ "    NULL AS tarjetas_graficas_id_producto,\r\n"
        		+ "    fa.id_fuente_alimentacion as fuentes_alimentacion_id,\r\n"
        		+ "    fa.potencia as fuentes_alimentacion_potencia,\r\n"
        		+ "    fa.id_producto as fuentes_alimentacion_id_producto,\r\n"
        		+ "     NULL AS torres_id_torre,\r\n"
        		+ "    NULL AS torres_tipo_conexion,\r\n"
        		+ "     NULL AS torres_peso,\r\n"
        		+ "     NULL AS torres_id_producto,\r\n"
        		+ "  NULL AS perifericos_id_periferico, \r\n"
        		+ "      NULL AS perifericos_tipo_conexion,\r\n"
        		+ "      NULL AS perifericos_id_producto\r\n"
        		+ "    FROM fuentes_alimentacion fa\r\n"
        		+ "    INNER JOIN productos p ON p.id_producto = fa.id_producto\r\n"
        		+ "  UNION ALL\r\n"
        		+ "  SELECT\r\n"
        		+ "    p.nombre AS producto_nombre,\r\n"
        		+ "    p.precio AS producto_precio,\r\n"
        		+ "    p.stock AS producto_stock,\r\n"
        		+ "    p.imagen AS producto_imagen,\r\n"
        		+ "    NULL AS placa_id,\r\n"
        		+ "    NULL AS placa_memoria,\r\n"
        		+ "    NULL AS placa_velocidad,\r\n"
        		+ "    NULL AS placa_tipo_memoria,\r\n"
        		+ "    NULL AS placa_id_producto,\r\n"
        		+ "    NULL AS procesador_id,\r\n"
        		+ "    NULL AS procesador_memoria,\r\n"
        		+ "    NULL AS procesador_velocidad,\r\n"
        		+ "    NULL AS procesador_nucleos,\r\n"
        		+ "    NULL AS procesador_id_producto,\r\n"
        		+ "     NULL AS ventilador_id,\r\n"
        		+ "    NULL AS ventilador_velocidad,\r\n"
        		+ "    NULL AS ventilador_tamano, \r\n"
        		+ "    NULL AS ventilador_peso,\r\n"
        		+ "    NULL AS ventilador_id_producto,\r\n"
        		+ "    NULL AS refrigeracion_liquida_id, \r\n"
        		+ "    NULL AS refrigeracion_liquida_tamano,\r\n"
        		+ "    NULL AS refrigeracion_liquida_velocidad,\r\n"
        		+ "    NULL AS refrigeracion_liquida_peso,\r\n"
        		+ "    NULL AS refrigeracion_liquida_tipo_liquido,\r\n"
        		+ "    NULL AS refrigeracion_liquida_id_producto,\r\n"
        		+ "    NULL AS   memorias_ram_id,\r\n"
        		+ "    NULL AS  memorias_ram_memoria,\r\n"
        		+ "    NULL AS  memorias_ram_tipo_memoria,\r\n"
        		+ "    NULL AS  memorias_ram_id_producto,\r\n"
        		+ "      NULL AS discos_duros_id, \r\n"
        		+ "    NULL AS discos_duros_memoria, \r\n"
        		+ "   NULL AS discos_duros_velocidad, \r\n"
        		+ "    NULL AS discos_duros_tipo_disco, \r\n"
        		+ "      NULL AS discos_duros_id_producto,\r\n"
        		+ "      NULL AS tarjetas_graficas_id, \r\n"
        		+ "    NULL AS tarjetas_graficas_memoria_ram,\r\n"
        		+ "    NULL AS tarjetas_graficas_nucleos,\r\n"
        		+ "    NULL AS tarjetas_graficas_tipo_memoria,\r\n"
        		+ "    NULL AS tarjetas_graficas_id_producto,\r\n"
        		+ "    NULL AS fuentes_alimentacion_id,\r\n"
        		+ "    NULL AS fuentes_alimentacion_potencia,\r\n"
        		+ "    NULL AS fuentes_alimentacion_id_producto,\r\n"
        		+ "    tor.id_torre as torres_id_torre,\r\n"
        		+ "    tor.tipo_conexion as torres_tipo_conexion,\r\n"
        		+ "    tor.peso as torres_peso,\r\n"
        		+ "    tor.id_producto as torres_id_producto,\r\n"
        		+ "     NULL AS perifericos_id_periferico, \r\n"
        		+ "      NULL AS perifericos_tipo_conexion,\r\n"
        		+ "      NULL AS perifericos_id_producto\r\n"
        		+ "    FROM torres tor\r\n"
        		+ "    INNER JOIN productos p ON p.id_producto = tor.id_producto\r\n"
        		+ "  UNION ALL\r\n"
        		+ "  SELECT\r\n"
        		+ "    p.nombre AS producto_nombre,\r\n"
        		+ "    p.precio AS producto_precio,\r\n"
        		+ "    p.stock AS producto_stock,\r\n"
        		+ "    p.imagen AS producto_imagen,\r\n"
        		+ "    NULL AS placa_id,\r\n"
        		+ "    NULL AS placa_memoria,\r\n"
        		+ "    NULL AS placa_velocidad,\r\n"
        		+ "    NULL AS placa_tipo_memoria,\r\n"
        		+ "    NULL AS placa_id_producto,\r\n"
        		+ "    NULL AS procesador_id,\r\n"
        		+ "    NULL AS procesador_memoria,\r\n"
        		+ "    NULL AS procesador_velocidad,\r\n"
        		+ "    NULL AS procesador_nucleos,\r\n"
        		+ "    NULL AS procesador_id_producto,\r\n"
        		+ "     NULL AS ventilador_id,\r\n"
        		+ "    NULL AS ventilador_velocidad,\r\n"
        		+ "    NULL AS ventilador_tamano, \r\n"
        		+ "    NULL AS ventilador_peso,\r\n"
        		+ "    NULL AS ventilador_id_producto,\r\n"
        		+ "    NULL AS refrigeracion_liquida_id, \r\n"
        		+ "    NULL AS refrigeracion_liquida_tamano,\r\n"
        		+ "    NULL AS refrigeracion_liquida_velocidad,\r\n"
        		+ "    NULL AS refrigeracion_liquida_peso,\r\n"
        		+ "    NULL AS refrigeracion_liquida_tipo_liquido,\r\n"
        		+ "    NULL AS refrigeracion_liquida_id_producto,\r\n"
        		+ "    NULL AS   memorias_ram_id,\r\n"
        		+ "    NULL AS  memorias_ram_memoria,\r\n"
        		+ "    NULL AS  memorias_ram_tipo_memoria,\r\n"
        		+ "    NULL AS  memorias_ram_id_producto,\r\n"
        		+ "      NULL AS discos_duros_id, \r\n"
        		+ "    NULL AS discos_duros_memoria, \r\n"
        		+ "   NULL AS discos_duros_velocidad, \r\n"
        		+ "    NULL AS discos_duros_tipo_disco, \r\n"
        		+ "      NULL AS discos_duros_id_producto,\r\n"
        		+ "      NULL AS tarjetas_graficas_id, \r\n"
        		+ "    NULL AS tarjetas_graficas_memoria_ram,\r\n"
        		+ "    NULL AS tarjetas_graficas_nucleos,\r\n"
        		+ "    NULL AS tarjetas_graficas_tipo_memoria,\r\n"
        		+ "    NULL AS tarjetas_graficas_id_producto,\r\n"
        		+ "    NULL AS fuentes_alimentacion_id,\r\n"
        		+ "    NULL AS fuentes_alimentacion_potencia,\r\n"
        		+ "    NULL AS fuentes_alimentacion_id_producto,\r\n"
        		+ "     NULL AS torres_id_torre,\r\n"
        		+ "    NULL AS torres_tipo_conexion,\r\n"
        		+ "     NULL AS torres_peso,\r\n"
        		+ "     NULL AS torres_id_producto,\r\n"
        		+ "     per.id_periferico as perifericos_id_periferico, \r\n"
        		+ "     per.tipo_conexion as perifericos_tipo_conexion,\r\n"
        		+ "     per.id_producto as perifericos_id_producto\r\n"
        		+ "   FROM perifericos per\r\n"
        		+ "INNER JOIN productos p ON p.id_producto = per.id_producto;\r\n"
        		+ "\r\n"
        		+ "\r\n"
        		+ "\r\n"
        		+ "\r\n"
        		+ "\r\n"
        		+ "\r\n"
        		+ "\r\n"
        		+ "\r\n"
        		+ "\r\n"
        		+ "\r\n"
        		+ "\r\n"
        		+ "\r\n"
        		+ "\r\n"
        		+ "\r\n"
        		+ "\r\n"
        		+ "";
        try {
            PreparedStatement statement = conexion.prepareStatement(consulta);
            resultado = statement.executeQuery();
        } catch (SQLException e) {
            System.err.println("Error al consultar la base de datos: " + e.getMessage());
        }
        return resultado;
    }
    
    public void ejecutarProcedimientoProductos(int p_id_empleado, int p_id_cliente, int p_cantidad_producto, double p_precio_venta, int p_id_producto) {        
        conexion = realizarConexion();
        
        try {
            CallableStatement callableStatement = conexion.prepareCall("{call procesar_pedido(?, ?, ?, ?, ?)}");

            callableStatement.setInt(1, p_id_empleado);
            callableStatement.setInt(2, p_id_cliente);
            callableStatement.setInt(3, p_cantidad_producto);
            callableStatement.setDouble(4, p_precio_venta);
            callableStatement.setInt(5, p_id_producto);
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
