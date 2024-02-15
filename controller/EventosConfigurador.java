import java.sql.ResultSet;

public class EventosConfigurador {
	
	private VistaConfigurador vistaConfigurador;
	private ConexionDB conexion = new ConexionDB();
	
	public EventosConfigurador(VistaConfigurador vistaConfigurador) {
		this.vistaConfigurador = vistaConfigurador;
		ResultSet resultado = conexion.consultarReporteVentas(1);
		
	}

}
