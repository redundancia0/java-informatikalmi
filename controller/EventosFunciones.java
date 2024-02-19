import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class EventosFunciones {

	private VistaFunciones vistaFunciones;
	private int id_factura;
	
	private void escucharEventos() {
		vistaFunciones.getBtnFacturas().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        String input = JOptionPane.showInputDialog(null, "Introduce la ID del pedido:");
		        id_factura = Integer.parseInt(input);
				EventosFacturas eventosFacturas = new EventosFacturas(id_factura);
				String rutaFactura = eventosFacturas.getRutaFactura();
				System.out.print(rutaFactura);
			}
		});
		
		vistaFunciones.getBtnOtras().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VistaInformacionOtras vistaInformacionOtras = new VistaInformacionOtras();
				vistaInformacionOtras.setEnabled(true);
				vistaInformacionOtras.setVisible(true);
			}
		});
	}
	
	public EventosFunciones(VistaFunciones vistaFunciones) {
		this.vistaFunciones = vistaFunciones;
		escucharEventos();
		
	}

}
