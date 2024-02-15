import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Font;

public class VistaFunciones extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel panelOpciones;
	private JLabel lblPedidos;
	private JLabel lblPendiente;
	private JButton btnOtras;
	private JButton btnStock;
	private JButton btnCompras;
	private JButton btnFacturas;

	public VistaFunciones() {
		setLayout(new BorderLayout(0, 0));
		
		panelOpciones = new JPanel();
		add(panelOpciones, BorderLayout.CENTER);
		panelOpciones.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblNewLabel_2 = new JLabel("Información sobre Facturas");
		panelOpciones.add(lblNewLabel_2);
		
		btnFacturas = new JButton("Facturas");
		panelOpciones.add(btnFacturas);
		
		JLabel lblNewLabel_3 = new JLabel("Información sobre Compras");
		panelOpciones.add(lblNewLabel_3);
		
		btnCompras = new JButton("Compras");
		panelOpciones.add(btnCompras);
		
		JLabel lblNewLabel_4 = new JLabel("Información sobre Stock");
		panelOpciones.add(lblNewLabel_4);
		
		btnStock = new JButton("Stock");
		panelOpciones.add(btnStock);
		
		JLabel lblNewLabel_5 = new JLabel("Otras opciones");
		panelOpciones.add(lblNewLabel_5);
		
		btnOtras = new JButton("Otras");
		panelOpciones.add(btnOtras);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new GridLayout(0, 4, 0, 0));
		
		JLabel lblElementosTitulo = new JLabel("Nº Pedidos:");
		lblElementosTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_1.add(lblElementosTitulo);
		
		lblPedidos = new JLabel("0");
		lblPedidos.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_1.add(lblPedidos);
		
		JLabel lblElementosTitulo_2 = new JLabel("Nº Pendiente:");
		lblElementosTitulo_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_1.add(lblElementosTitulo_2);
		
		lblPendiente = new JLabel("0");
		lblPendiente.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_1.add(lblPendiente);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.WEST);
		
		JPanel panel_2 = new JPanel();
		add(panel_2, BorderLayout.EAST);
		
    }


    // Método main para probar la clase
    public static void main(String[] args) {
        JFrame frame = new JFrame("Ejemplo VistaFunciones");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        VistaFunciones vistaFunciones = new VistaFunciones();
        frame.getContentPane().add(vistaFunciones);

        frame.setVisible(true);
    }
}
