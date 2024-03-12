package uniandes.dpoo.aerolinea.modelo;

import java.util.Map;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import uniandes.dpoo.aerolinea.tiquetes.GeneradorTiquetes;
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
		this.fecha = fecha;
		this.avion = avion;
		this.ruta = ruta;
		this.tiquetes = new HashMap<String, Tiquete>();
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
		return this.tiquetes.values();
	}

	public int venderTiquetes​(Cliente cliente, CalculadoraTarifas calculadora, int cantidad)
			throws VueloSobrevendidoException {
		int nTotalTiquetes = this.tiquetes.size() + cantidad;
	    if(nTotalTiquetes <= this.avion.getCapacidad()){
	        int tarifaIndividual = calculadora.calcularTarifa​(this, cliente);
	        int tarifaTotal = tarifaIndividual * cantidad;
	        
	        for (int i = 0; i < cantidad; i++) {
	            Tiquete nTiquete = GeneradorTiquetes.generarTiquete(this, cliente, tarifaIndividual);
	            this.tiquetes.put(nTiquete.getCodigo(), nTiquete);
	            cliente.agregarTiquete​(nTiquete);
	            GeneradorTiquetes.registrarTiquete(nTiquete);
	        }
	        return tarifaTotal;
	    }else{
	        throw new VueloSobrevendidoException(this);
	    }
	}
	
	public boolean equals​(Object obj) {
		return false;
	}
}
