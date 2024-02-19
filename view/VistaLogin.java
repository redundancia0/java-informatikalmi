import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VistaLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField txtClave;
	private JTextField txtCorreo;
	private JButton btnIniciarSesion;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaLogin frame = new VistaLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VistaLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 440, 371);
		setTitle("InformatikAlmi | Login");
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("LOGIN");
		lblTitulo.setFont(new Font("Segoe UI Semibold", Font.BOLD, 22));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(114, 31, 188, 25);
		contentPane.add(lblTitulo);
		
		txtClave = new JPasswordField();
		txtClave.setBounds(132, 170, 143, 34);
		contentPane.add(txtClave);
		txtClave.setColumns(10);
		
		txtCorreo = new JTextField();
		txtCorreo.setColumns(10);
		txtCorreo.setBounds(132, 99, 143, 34);
		contentPane.add(txtCorreo);
		
		JLabel lblDNI = new JLabel("DNI");
		lblDNI.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDNI.setBounds(132, 78, 46, 14);
		contentPane.add(lblDNI);
		
		JLabel lblClave = new JLabel("Clave");
		lblClave.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblClave.setBounds(132, 145, 46, 14);
		contentPane.add(lblClave);
		
		btnIniciarSesion = new JButton("Iniciar Sesión");
		btnIniciarSesion.setFont(new Font("Nirmala UI", Font.BOLD, 13));
		btnIniciarSesion.setBounds(132, 233, 143, 34);
		contentPane.add(btnIniciarSesion);
	}

	public JTextField getTxtClave() {
		return txtClave;
	}

	public void setTxtClave(JPasswordField txtClave) {
		this.txtClave = txtClave;
	}

	public JTextField getTxtCorreo() {
		return txtCorreo;
	}

	public void setTxtCorreo(JTextField txtCorreo) {
		this.txtCorreo = txtCorreo;
	}

	public JButton getBtnIniciarSesion() {
		return btnIniciarSesion;
	}

	public void setBtnIniciarSesion(JButton btnIniciarSesion) {
		this.btnIniciarSesion = btnIniciarSesion;
	}
}
