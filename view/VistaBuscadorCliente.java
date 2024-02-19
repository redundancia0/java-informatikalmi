import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VistaBuscadorCliente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelListaClientes;
	private JPanel panelSuperior;
	private JPanel panel;
	private JTextField txtDNI;
	private JButton btnBuscar;
	private JLabel lblNewLabel;
	private JButton btnCerrar;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaBuscadorCliente frame = new VistaBuscadorCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VistaBuscadorCliente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 469, 398);
		setTitle("InformatikAlmi | Buscador Clientes");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panelListaClientes = new JPanel();
		contentPane.add(panelListaClientes, BorderLayout.CENTER);
		panelListaClientes.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		panelSuperior = new JPanel();
		contentPane.add(panelSuperior, BorderLayout.NORTH);
		
		lblNewLabel = new JLabel("DNI");
		panelSuperior.add(lblNewLabel);
		
		txtDNI = new JTextField();
		txtDNI.setColumns(10);
		panelSuperior.add(txtDNI);
		
		btnBuscar = new JButton("Buscar");
		panelSuperior.add(btnBuscar);
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		btnCerrar = new JButton("Cerrar");
		panel.add(btnCerrar);
	}

	public JButton getBtnCerrar() {
		return btnCerrar;
	}

	public void setBtnCerrar(JButton btnCerrar) {
		this.btnCerrar = btnCerrar;
	}

	public JButton getBtnBuscar() {
		return btnBuscar;
	}

	public void setBtnBuscar(JButton btnBuscar) {
		this.btnBuscar = btnBuscar;
	}

	public JTextField getTxtDNI() {
		return txtDNI;
	}

	public void setTxtDNI(JTextField txtDNI) {
		this.txtDNI = txtDNI;
	}

	public JPanel getPanelListaClientes() {
		return panelListaClientes;
	}

	public void setPanelListaClientes(JPanel panelListaClientes) {
		this.panelListaClientes = panelListaClientes;
	}

	public JPanel getPanelSuperior() {
		return panelSuperior;
	}

	public void setPanelSuperior(JPanel panelSuperior) {
		this.panelSuperior = panelSuperior;
	}
}
