package uniandes.dpoo.aerolinea.modelo;

import java.util.Map;
import java.util.Collection;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.exceptions.VueloSobrevendidoException;
import uniandes.dpoo.aerolinea.modelo.tarifas.CalculadoraTarifas;

public class Vuelo {

	private Ruta ruta;
	private String fecha;
	private Avion avion;
	private Map<String,Tiquete> tiquetes;
	
	public Vuelo(Ruta ruta, String fecha, Avion avion) {
		
	}

	public Ruta getRuta() {
		return this.ruta;
	}

	public String getFecha() {
		return this.fecha;
	}

	public Avion getAvion() {
		return this.avion;
	}

	public Collection<Tiquete> getTiquetes() {
		return null;
	}

	public int venderTiquetes​(Cliente cliente, CalculadoraTarifas calculadora, int cantidad)
			throws VueloSobrevendidoException {
		return -1;
	}
	
	public boolean equals​(Object obj) {
		return false;
	}
}
