package uniandes.dpoo.aerolinea.persistencia;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import uniandes.dpoo.aerolinea.exceptions.AeropuertoDuplicadoException;
import uniandes.dpoo.aerolinea.exceptions.InformacionInconsistenteException;
import uniandes.dpoo.aerolinea.modelo.Aerolinea;
import uniandes.dpoo.aerolinea.modelo.Aeropuerto;
import uniandes.dpoo.aerolinea.modelo.Avion;
import uniandes.dpoo.aerolinea.modelo.Ruta;
import uniandes.dpoo.aerolinea.modelo.Vuelo;

public class PersistenciaAerolineaJson implements IPersistenciaAerolinea {

	private static final String NOMBRE = "nombre";
    private static final String CAPACIDAD = "capacidad";
    private static final String HORAS = "horaSalida";
    private static final String HORAL = "horaLlegada";
    private static final String CODIGO = "codigo";
    private static final String FECHA = "fecha";
    private static final String CODIGO_RUTA = "codigoRuta";
    private static final String NOMBRE_CIUDAD = "nombreCiudad";
    private static final String LAT = "latitud";
    private static final String LON = "longitud";
	
	public PersistenciaAerolineaJson() {
		
	}

	public void cargarAerolinea​(String archivo, Aerolinea aerolinea)
			throws IOException, InformacionInconsistenteException{
		String jsonCompleto = new String(Files.readAllBytes(new File(archivo).toPath()));
        JSONObject raiz = new JSONObject(jsonCompleto);
        cargarAviones(aerolinea, raiz.getJSONArray("aviones"));
        try {
        	cargarVuelos(aerolinea, raiz.getJSONArray("vuelos"));
			cargarRutas(aerolinea, raiz.getJSONArray("rutas"));
		} catch (AeropuertoDuplicadoException e) {
			e.printStackTrace();
		}
	}

	public void salvarAerolinea​(String archivo, Aerolinea aerolinea) throws IOException {
		JSONObject jobject = new JSONObject();
        salvarAviones(aerolinea, jobject);
        salvarVuelos(aerolinea, jobject);
        salvarRutas(aerolinea, jobject);
		PrintWriter pW = new PrintWriter(archivo);
        jobject.write(pW, 2, 0);
        pW.close( );
	}
	
	public void cargarAviones(Aerolinea aerolinea, JSONArray jAviones) {
		int numAviones = jAviones.length();
        for( int i = 0; i < numAviones; i++ ) {
        	JSONObject avion = jAviones.getJSONObject(i);
        	Avion nuevoAvion= crearAvion(avion);
        	aerolinea.agregarAvion(nuevoAvion);
		}
	}
	
	public void cargarVuelos(Aerolinea aerolinea, JSONArray jVuelos) throws AeropuertoDuplicadoException {
		int numVuelos= jVuelos.length();
        for(int i = 0; i < numVuelos; i++) {
        	JSONObject jvuelo = jVuelos.getJSONObject(i);     
        	String fecha = jvuelo.getString(FECHA);
        	JSONObject jruta = jvuelo.getJSONObject("ruta");
        	Ruta nuevaRuta=crearRuta(jruta);
        	JSONObject javion = jvuelo.getJSONObject("avion");
        	Avion nuevoAvion=crearAvion(javion);
        	Vuelo nuevoVuelo= new Vuelo(nuevaRuta, fecha, nuevoAvion);
        	aerolinea.getVuelos().add(nuevoVuelo);
        }
	}

	public void cargarRutas(Aerolinea aerolinea, JSONArray jRutas) throws AeropuertoDuplicadoException {
    	int numRutas = jRutas.length();
        for(int i = 0; i < numRutas; i++) {
        	JSONObject ruta = jRutas.getJSONObject(i);        
        	Ruta nuevaRuta= crearRuta(ruta);
        	aerolinea.agregarRuta(nuevaRuta);
        }		
	}
	
	public Aeropuerto crearAeropuerto(JSONObject jAeropuerto) throws AeropuertoDuplicadoException {		
		String nombre = jAeropuerto.getString(NOMBRE);
		String codigo = jAeropuerto.getString(CODIGO);
		String nombreCiudad = jAeropuerto.getString(NOMBRE_CIUDAD);
		double latitud = jAeropuerto.getDouble(LAT);
		double longitud = jAeropuerto.getDouble(LON);
		Aeropuerto Aeropuerto= new Aeropuerto(nombre, codigo, nombreCiudad, latitud, longitud, null);
		return Aeropuerto;
	}
		
	public Avion crearAvion(JSONObject avion) {
		String nombre = avion.getString(NOMBRE);
    	int capacidad = avion.getInt(CAPACIDAD);
    	Avion nuevoAvion= new Avion(nombre, capacidad);
		return nuevoAvion;
	}

	public Ruta crearRuta(JSONObject ruta) throws AeropuertoDuplicadoException {
		String codigoR = ruta.getString(CODIGO_RUTA);
    	String horaS = ruta.getString(HORAS);
    	String horaL = ruta.getString(HORAL);
    	JSONObject jAeropuertoO= ruta.getJSONObject("aeropuertoOrigen");
    	Aeropuerto aeropuertoO= crearAeropuerto(jAeropuertoO);
    	JSONObject jAeropuertoD= ruta.getJSONObject("aeropuertoDestino");
    	Aeropuerto aeropuertoD= crearAeropuerto(jAeropuertoD);
    	Ruta nuevaRuta= new Ruta(aeropuertoO, aeropuertoD, horaS, horaL, codigoR);
		return nuevaRuta;
	}
	
	public void salvarRutas(Aerolinea aerolinea, JSONObject jobject) {		
		JSONArray jrutas =new JSONArray();
		for (Ruta ruta: aerolinea.getRutas()) {
			JSONObject jRuta= salvarRuta(ruta);
			jrutas.put(jRuta);
		}
		jobject.put("rutas", jrutas);		
	}

	private void salvarVuelos(Aerolinea aerolinea, JSONObject jobject) {
		JSONArray jvuelos =new JSONArray();		
		for (Vuelo vuelo :aerolinea.getVuelos()) {
			JSONObject jvuelo =new JSONObject ();
			jvuelo.put(FECHA,vuelo.getFecha());
			salvarAvion(vuelo.getAvion(),jvuelo);
			JSONObject jRuta= salvarRuta(vuelo.getRuta());
			jvuelo.put("ruta", jRuta);
			jvuelos.put(jvuelo);
		}
		jobject.put("vuelos", jvuelos);
	}

	private JSONObject salvarRuta(Ruta ruta) {		
		JSONObject jRuta =new JSONObject ();
		jRuta.put(HORAS, ruta.getHoraSalida());
		jRuta.put(HORAL, ruta.getHoraLlegada());
		jRuta.put(CODIGO_RUTA, ruta.getCodigoRuta());
		JSONObject aeropuertoO=salvarAeropuerto(ruta.getOrigen());
		jRuta.put("aeropuertoOrigen",aeropuertoO);
		JSONObject aeropuertoD=salvarAeropuerto(ruta.getDestino());
		jRuta.put("aeropuertoDestino",aeropuertoD);		
		return jRuta;
	}

	private JSONObject salvarAeropuerto(Aeropuerto aeropuerto) {		
		JSONObject jAeropuerto =new JSONObject ();
		jAeropuerto.put(NOMBRE, aeropuerto.getNombre());
		jAeropuerto.put(CODIGO, aeropuerto.getCodigo());
		jAeropuerto.put(NOMBRE_CIUDAD, aeropuerto.getNombreCiudad());
		jAeropuerto.put(LAT, aeropuerto.getLatitud());
		jAeropuerto.put(LON, aeropuerto.getLongitud());		
		return jAeropuerto;
	}

	private void salvarAvion(Avion avion, JSONObject jobject) {		
		JSONObject jAvion =new JSONObject ();
		jAvion.put(NOMBRE, avion.getNombre());
		jAvion.put(CAPACIDAD, avion.getCapacidad());		
		jobject.put("avion",jAvion);		
	}

	private void salvarAviones(Aerolinea aerolinea, JSONObject jobject) {
		JSONArray jAviones =new JSONArray();
		for (Avion avion : aerolinea.getAviones()) {
			JSONObject jAvion =new JSONObject ();
			jAvion.put(NOMBRE, avion.getNombre());
			jAvion.put(CAPACIDAD, avion.getCapacidad());			
			jAviones.put(avion);
		}
		jobject.put("aviones", jAviones);
	}
}
