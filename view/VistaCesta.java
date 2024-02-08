import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class VistaCesta extends JPanel {

    private static final long serialVersionUID = 1L;
    private JPanel panelProductos;
    private JPanel panelContadores;
    private List<Producto> listaProductos;
    private JButton btnProceder;
    private JLabel lblImporte;
    private JLabel lblElementos;

    public VistaCesta() {
        setLayout(new BorderLayout(0, 0));

        panelContadores = new JPanel();
        add(panelContadores, BorderLayout.NORTH);
        panelContadores.setLayout(new GridLayout(1, 0, 0, 0));

        JLabel lblElementosTitulo = new JLabel("Nº Elementos:");
        lblElementosTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
        panelContadores.add(lblElementosTitulo);

        lblElementos = new JLabel("0");
        lblElementos.setFont(new Font("Tahoma", Font.BOLD, 14));
        panelContadores.add(lblElementos);

        JLabel lblImporteTitulo = new JLabel("Importe Total:");
        lblImporteTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
        panelContadores.add(lblImporteTitulo);

        lblImporte = new JLabel("0");
        lblImporte.setFont(new Font("Tahoma", Font.BOLD, 14));
        panelContadores.add(lblImporte);

        panelProductos = new JPanel();
        panelProductos.setLayout(new GridLayout(0, 1, 0, 0));

        listaProductos = new ArrayList<>();

        JScrollPane scrollPane = new JScrollPane(panelProductos);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        add(panel, BorderLayout.SOUTH);

        btnProceder = new JButton("Proceder");
        btnProceder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        panel.add(btnProceder);
    }

    public JButton getBtnProceder() {
		return btnProceder;
	}

	public void setBtnProceder(JButton btnProceder) {
		this.btnProceder = btnProceder;
	}

	public List<Producto> getListaProductos() {
		return listaProductos;
	}

	public JLabel getLblImporte() {
		return lblImporte;
	}

	public void setLblImporte(JLabel lblImporte) {
		this.lblImporte = lblImporte;
	}

	public JLabel getLblElementos() {
		return lblElementos;
	}

	public void setLblElementos(JLabel lblElementos) {
		this.lblElementos = lblElementos;
	}

	public void setListaProductos(List<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}

    public JPanel getPanelProductos() {
		return panelProductos;
	}

	public void setPanelProductos(JPanel panelProductos) {
		this.panelProductos = panelProductos;
	}

	public JPanel getPanelContadores() {
		return panelContadores;
	}

	public void setPanelContadores(JPanel panelContadores) {
		this.panelContadores = panelContadores;
	}

}
