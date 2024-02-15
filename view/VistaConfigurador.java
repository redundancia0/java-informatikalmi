import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class VistaConfigurador extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton btnCargar;
	private JButton btnProceder;
	private JComboBox comboCaja;
	private JComboBox comboFuente;
	private JComboBox comboDiscoDuro;
	private JComboBox comboGrafica;
	private JComboBox comboRam;
	private JComboBox comboProcesador;
	private JComboBox comboPlacaBase;
	private JComboBox comboRefrigeracion;
	private JComboBox comboVentilador;
	private JLabel lblPrecioTotal;

	public VistaConfigurador() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new GridLayout(9, 4, 0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("Placa base");
		panel.add(lblNewLabel_1);
		
		comboPlacaBase = new JComboBox();
		panel.add(comboPlacaBase);
		
		JLabel lblNewLabel_2 = new JLabel("Procesador");
		panel.add(lblNewLabel_2);
		
		comboProcesador = new JComboBox();
		panel.add(comboProcesador);
		
		JLabel lblNewLabel_4_2 = new JLabel("Ventilador");
		panel.add(lblNewLabel_4_2);
		
		comboVentilador = new JComboBox();
		panel.add(comboVentilador);
		
		JLabel lblNewLabel_4_2_1 = new JLabel("Refrigeración Líquida");
		panel.add(lblNewLabel_4_2_1);
		
		comboRefrigeracion = new JComboBox();
		panel.add(comboRefrigeracion);
		
		JLabel lblNewLabel_2_1 = new JLabel("Memoria RAM");
		panel.add(lblNewLabel_2_1);
		
		comboRam = new JComboBox();
		panel.add(comboRam);
		
		JLabel lblNewLabel_4_1 = new JLabel("Disco Duro");
		panel.add(lblNewLabel_4_1);
		
		comboDiscoDuro = new JComboBox();
		panel.add(comboDiscoDuro);
		
		JLabel lblNewLabel_3 = new JLabel("Tarjeta gráfica");
		panel.add(lblNewLabel_3);
		
		comboGrafica = new JComboBox();
		panel.add(comboGrafica);
		
		JLabel lblNewLabel_4 = new JLabel("Fuente de Alimentación");
		panel.add(lblNewLabel_4);
		
		comboFuente = new JComboBox();
		panel.add(comboFuente);
		
		JLabel lblNewLabel_5 = new JLabel("Caja");
		panel.add(lblNewLabel_5);
		
		comboCaja = new JComboBox();
		panel.add(comboCaja);
		
		JPanel panel_3 = new JPanel();
		add(panel_3, BorderLayout.NORTH);
		
		JLabel lblPrecioTotalTitulo = new JLabel("Precio Total:");
		lblPrecioTotalTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_3.add(lblPrecioTotalTitulo);
		
		lblPrecioTotal = new JLabel("0");
		lblPrecioTotal.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_3.add(lblPrecioTotal);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new GridLayout(0, 4, 0, 0));
		
		JLabel lblNewLabel = new JLabel("");
		panel_1.add(lblNewLabel);
		
		btnCargar = new JButton("Cargar");
		panel_1.add(btnCargar);
		
		btnProceder = new JButton("Proceder");
		panel_1.add(btnProceder);
		
		JPanel panel_2 = new JPanel();
		add(panel_2, BorderLayout.WEST);

	}
	
	

	public JComboBox getComboCaja() {
		return comboCaja;
	}



	public void setComboCaja(JComboBox comboCaja) {
		this.comboCaja = comboCaja;
	}



	public JComboBox getComboFuente() {
		return comboFuente;
	}



	public void setComboFuente(JComboBox comboFuente) {
		this.comboFuente = comboFuente;
	}



	public JComboBox getComboDiscoDuro() {
		return comboDiscoDuro;
	}



	public void setComboDiscoDuro(JComboBox comboDiscoDuro) {
		this.comboDiscoDuro = comboDiscoDuro;
	}



	public JComboBox getComboGrafica() {
		return comboGrafica;
	}



	public void setComboGrafica(JComboBox comboGrafica) {
		this.comboGrafica = comboGrafica;
	}



	public JComboBox getComboRam() {
		return comboRam;
	}



	public void setComboRam(JComboBox comboRam) {
		this.comboRam = comboRam;
	}



	public JComboBox getComboProcesador() {
		return comboProcesador;
	}



	public void setComboProcesador(JComboBox comboProcesador) {
		this.comboProcesador = comboProcesador;
	}



	public JComboBox getComboPlacaBase() {
		return comboPlacaBase;
	}



	public void setComboPlacaBase(JComboBox comboPlacaBase) {
		this.comboPlacaBase = comboPlacaBase;
	}



	public JComboBox getComboRefrigeracion() {
		return comboRefrigeracion;
	}



	public void setComboRefrigeracion(JComboBox comboRefrigeracion) {
		this.comboRefrigeracion = comboRefrigeracion;
	}



	public JComboBox getComboVentilador() {
		return comboVentilador;
	}



	public void setComboVentilador(JComboBox comboVentilador) {
		this.comboVentilador = comboVentilador;
	}



	public JButton getBtnCargar() {
		return btnCargar;
	}

	public void setBtnCargar(JButton btnCargar) {
		this.btnCargar = btnCargar;
	}

	public JButton getBtnProceder() {
		return btnProceder;
	}

	public void setBtnProceder(JButton btnProceder) {
		this.btnProceder = btnProceder;
	}
}
