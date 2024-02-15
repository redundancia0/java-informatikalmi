import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class EventosFacturas {
	
	private ConexionDB conexion = new ConexionDB();
	private int id_pedido_cliente;
	private int cantidad;
	private double precio_venta;
	private int id_producto;
	private int id_pedido_cliente_producto;
	private String nombre, apellido1, apellido2, fecha_pedido;

    public  EventosFacturas(){
        try {
            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);
            sacarDatosPedido(48);
            sacarDatosCliente(48);
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 18);
            contentStream.beginText();
            contentStream.newLineAtOffset(100, 750);
            contentStream.showText("Factura");
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("--------------------------------------------");
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText(("Cliente: " + nombre + " " + apellido1 + " " + apellido2));
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText(("Fecha: " + fecha_pedido.substring(0, 8)));
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("--------------------------------------------");
            contentStream.newLineAtOffset(0, -20);
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.showText("Descripción");
            contentStream.newLineAtOffset(200, 0);
            contentStream.showText("Precio");
            contentStream.newLineAtOffset(-200, -20);
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.showText("Producto 1");
            contentStream.newLineAtOffset(200, 0);
            contentStream.showText("$50.00");
            contentStream.newLineAtOffset(-200, -20);
            contentStream.showText("Producto 2");
            contentStream.newLineAtOffset(200, 0);
            contentStream.showText("$30.00");
            contentStream.newLineAtOffset(-200, -20);
            contentStream.showText("--------------------------------------------");
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Total: $80.00");
            contentStream.endText();
            contentStream.close();

            document.save(new File("factura.pdf"));
            document.close();

            System.out.println("Factura generada exitosamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void sacarDatosFacturas() {
    	ResultSet resultado = conexion.consultarPedidos();
	   	 try {
	            if (resultado != null) {
	                while (resultado.next()) {
	                    int id_pedido_cliente = resultado.getInt("id_pedido_cliente");
	                    int id_empleado = resultado.getInt("id_empleado");
	                    int id_cliente = resultado.getInt("id_cliente");
	                    String fecha_pedido = resultado.getString("fecha_pedido");
	                    System.out.print(id_pedido_cliente + id_empleado + id_cliente + fecha_pedido);
	                }
	            }
	        } catch (SQLException e) {
	            System.err.println("Error al obtener productos: " + e.getMessage());
	        }
    }
    public void sacarDatosPedido(int id_pedido) {
        List<String[]> resultados = conexion.consultarPedidoEspecifico(id_pedido);
        try {
            if (resultados != null && !resultados.isEmpty()) {
                for (String[] fila : resultados) {
                    System.out.print("\n-------------\n------------");
                    id_pedido_cliente_producto = Integer.parseInt(fila[0]);
                    cantidad = Integer.parseInt(fila[1]);
                    precio_venta = Double.parseDouble(fila[2]);
                    id_producto = Integer.parseInt(fila[3]);
                    id_pedido_cliente = Integer.parseInt(fila[4]);
                    System.out.print("\nid_pedido_cliente_producto: " + id_pedido_cliente_producto);
                    System.out.print("\nCantidad: " + cantidad);
                    System.out.print("\nPrecio venta: " + precio_venta);
                    System.out.print("\nID producto: " + id_producto);
                    System.out.print("\nID pedido cliente: " + id_pedido_cliente);
                }
            }
        } catch (NumberFormatException e) {
            System.err.println("Error al convertir datos: " + e.getMessage());
        }
    }
    
    public void sacarDatosCliente(int id_pedido) {
        List<String[]> resultados = conexion.consultarClienteID(id_pedido);
        try {
            if (resultados != null && !resultados.isEmpty()) {
                for (String[] fila : resultados) {
                    System.out.print("\n-------------\n------------");
                    nombre = fila[0];
                    apellido1 = fila[1];
                    apellido2 = fila[2];
                    fecha_pedido = fila[3];
                }
            }
        } catch (NumberFormatException e) {
            System.err.println("Error al convertir datos: " + e.getMessage());
        }
    }

}
