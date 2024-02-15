import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JScrollPane;

public class VistaProductos extends JPanel {

    private static final long serialVersionUID = 1L;
    private JComboBox comboTipo;
    private JTextField txtBusqueda;
    private JButton btnBuscar;
    private JButton button;
    private JLabel labelImagen;
    private JLabel lblImagen = new JLabel();
	private JPanel panelProductos_1;
	private JButton btnCerrarSesion;
	private static JLabel lblContadorCesta;

    public VistaProductos() {
        setLayout(new BorderLayout());
        panelProductos_1 = new JPanel(new GridLayout(0, 2, 2, 10));
        JScrollPane scrollPane = new JScrollPane(panelProductos_1);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panelCentrado = new JPanel(new GridLayout(1, 1));
        add(panelCentrado, BorderLayout.SOUTH);

        JPanel panelNav = new JPanel();
        add(panelNav, BorderLayout.NORTH);
        panelNav.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        
        JLabel lblNewLabel_1 = new JLabel("                                 ");
        panelNav.add(lblNewLabel_1);

        JLabel lblTipoTitulo = new JLabel("Tipo de producto");
        lblTipoTitulo.setHorizontalAlignment(SwingConstants.LEFT);
        panelNav.add(lblTipoTitulo);

        comboTipo = new JComboBox();
        panelNav.add(comboTipo);

        JLabel lblBuscarTitulo = new JLabel("Buscar");
        panelNav.add(lblBuscarTitulo);

        txtBusqueda = new JTextField();
        panelNav.add(txtBusqueda);
        txtBusqueda.setColumns(10);

        btnBuscar = new JButton("🔎");
        panelNav.add(btnBuscar);
        
        JLabel lblNewLabel = new JLabel("                                                                        ");
        panelNav.add(lblNewLabel);
        
        JLabel lblNewLabel_2 = new JLabel("Cesta:");
        panelNav.add(lblNewLabel_2);
        
        lblContadorCesta = new JLabel("0");
        panelNav.add(lblContadorCesta);
        
        JLabel lblNewLabel_4 = new JLabel("              ");
        panelNav.add(lblNewLabel_4);
        
        btnCerrarSesion = new JButton("Cerrar Sesión");
        panelNav.add(btnCerrarSesion);
        
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String busqueda = txtBusqueda.getText().trim().toLowerCase();
                for (Component componente : panelProductos_1.getComponents()) {
                    if (componente instanceof JPanel) {
                        JPanel panelProducto = (JPanel) componente;
                        JLabel labelNombre = (JLabel) panelProducto.getComponent(0);
                        String nombreProducto = labelNombre.getText().toLowerCase();
                        if (nombreProducto.contains(busqueda)) {
                            panelProducto.setVisible(true);
                        } else {
                            panelProducto.setVisible(false);
                        }
                    }
                }
            }
        });
    }

    public static JLabel getLblContadorCesta() {
		return lblContadorCesta;
	}

	public void setLblContadorCesta(JLabel lblContadorCesta) {
		this.lblContadorCesta = lblContadorCesta;
	}

	public JButton getBtnCerrarSesion() {
		return btnCerrarSesion;
	}

	public void setBtnCerrarSesion(JButton btnCerrarSesion) {
		this.btnCerrarSesion = btnCerrarSesion;
	}

	public JComboBox getComboTipo() {
		return comboTipo;
	}

	public void setComboTipo(JComboBox comboTipo) {
		this.comboTipo = comboTipo;
	}

	public JPanel getPanelProductos_1() {
		return panelProductos_1;
	}

	public void setPanelProductos_1(JPanel panelProductos_1) {
		this.panelProductos_1 = panelProductos_1;
	}
   
	public JLabel getLblImagen() {
		return lblImagen;
	}

	public void setLblImagen(JLabel lblImagen) {
		this.lblImagen = lblImagen;
	}
}
