import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Font;

public class VistaPendientes extends JPanel {

	private static final long serialVersionUID = 1L;

	public VistaPendientes() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 4, 0, 0));
		
		JLabel lblNPendientes = new JLabel("Nº Pendientes:");
		lblNPendientes.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(lblNPendientes);
		
		JLabel lblElementos = new JLabel("0");
		lblElementos.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(lblElementos);

	}

}
