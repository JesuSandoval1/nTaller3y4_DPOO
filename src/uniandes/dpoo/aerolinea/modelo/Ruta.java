package uniandes.dpoo.aerolinea.modelo;

/**
 * Esta clase tiene la información de una ruta entre dos aeropuertos que cubre
 * una aerolínea.
 */
public class Ruta {
	// TODO completar
	private Aeropuerto origen;
	private Aeropuerto destino;
	private String horaSalida;
	private String horaLlegada;
	private String codigoRuta;


	public Ruta(Aeropuerto origen, Aeropuerto destino, String horaSalida, String horaLlegada, String codigoRuta) {
	}
	
	public String getCodigoRuta() {
		return null;
	}
	
	public Aeropuerto getOrigen() {
		return null;
	}
	
	public Aeropuerto getDestino() {
		return null;
	}
	
	public String getHoraSalida() {
		return null;
	}
	
	public String getHoraLlegada() {
		return null;
	}
	
	public int getDuracion() {
		return -1;
	}
	
	/**
	 * Dada una cadena con una hora y minutos, retorna los minutos.
	 * 
	 * Por ejemplo, para la cadena '715' retorna 15.
	 * 
	 * @param horaCompleta Una cadena con una hora, donde los minutos siempre ocupan
	 *                     los dos últimos caracteres
	 * @return Una cantidad de minutos entre 0 y 59
	 */

	public static int getMinutos(String horaCompleta) {
		int minutos = Integer.parseInt(horaCompleta) % 100;
		return minutos;
	}

	/**
	 * Dada una cadena con una hora y minutos, retorna las horas.
	 * 
	 * Por ejemplo, para la cadena '715' retorna 7.
	 * 
	 * @param horaCompleta Una cadena con una hora, donde los minutos siempre ocupan
	 *                     los dos últimos caracteres
	 * @return Una cantidad de horas entre 0 y 23
	 */
	public static int getHoras(String horaCompleta) {
		int horas = Integer.parseInt(horaCompleta) / 100;
		return horas;
	}

}
