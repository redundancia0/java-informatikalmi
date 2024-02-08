import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;

public class VistaConfigurador extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton btnComprobar;
	private JButton btnProceder;

	public VistaConfigurador() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new GridLayout(8, 4, 0, 0));
		
		JLabel lblZocalo_1 = new JLabel("Zócalo");
		panel.add(lblZocalo_1);
		
		JComboBox comboZocalo = new JComboBox();
		panel.add(comboZocalo);
		
		JLabel lblNewLabel_1 = new JLabel("Placa base");
		panel.add(lblNewLabel_1);
		
		JComboBox comboPlacaBase = new JComboBox();
		panel.add(comboPlacaBase);
		
		JLabel lblNewLabel_2 = new JLabel("Procesador");
		panel.add(lblNewLabel_2);
		
		JComboBox comboProcesador = new JComboBox();
		panel.add(comboProcesador);
		
		JLabel lblNewLabel_2_1 = new JLabel("Memoria RAM");
		panel.add(lblNewLabel_2_1);
		
		JComboBox comboRam = new JComboBox();
		panel.add(comboRam);
		
		JLabel lblNewLabel_3 = new JLabel("Tarjeta gráfica");
		panel.add(lblNewLabel_3);
		
		JComboBox comboGrafica = new JComboBox();
		panel.add(comboGrafica);
		
		JLabel lblNewLabel_4 = new JLabel("Fuente de Alimentación");
		panel.add(lblNewLabel_4);
		
		JComboBox comboFuente = new JComboBox();
		panel.add(comboFuente);
		
		JLabel lblNewLabel_5 = new JLabel("Caja");
		panel.add(lblNewLabel_5);
		
		JComboBox comboCaja = new JComboBox();
		panel.add(comboCaja);
		
		JPanel panel_3 = new JPanel();
		add(panel_3, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new GridLayout(0, 4, 0, 0));
		
		JLabel lblNewLabel = new JLabel("");
		panel_1.add(lblNewLabel);
		
		btnComprobar = new JButton("Comprobar");
		panel_1.add(btnComprobar);
		
		btnProceder = new JButton("Proceder");
		panel_1.add(btnProceder);
		
		JPanel panel_2 = new JPanel();
		add(panel_2, BorderLayout.WEST);

	}
}
