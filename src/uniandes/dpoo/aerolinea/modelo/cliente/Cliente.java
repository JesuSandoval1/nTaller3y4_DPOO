package uniandes.dpoo.aerolinea.modelo.cliente;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;

public abstract class Cliente {
	
	private List<Tiquete> tiquetesSinUsar;
	private List<Tiquete> tiquetesUsados;
	protected static Set<String> identificadores = new HashSet<String>();
	
	public Cliente() {
		this.tiquetesSinUsar = new ArrayList<>(); 
	    this.tiquetesUsados = new ArrayList<>();
	}
	
	public abstract String generadorIds();
	
	public abstract String getTipoCliente();
	
	public abstract String getIdentificador();
	
	public List<Tiquete> getTiquetesSinUsar(){
		return this.tiquetesSinUsar;
	}
	
	public List<Tiquete> getTiquetesUsados(){
		return this.tiquetesUsados;
	}
	
	public void agregarTiquete​(Tiquete tiquete) {
		this.tiquetesSinUsar.add(tiquete);
	}
	
	public int calcularValorTotalTiquetes() {
		int totalPrecio = 0;
		for (Tiquete tiquete : this.tiquetesUsados) {
			totalPrecio += tiquete.getTarifa();
	    }
	    for (Tiquete tiquete : this.tiquetesSinUsar) {
	    	totalPrecio += tiquete.getTarifa();
	    }
	    return totalPrecio;
	}
	
	public void usarTiquetes​(Vuelo vuelo) {
		for (Tiquete tiquete: vuelo.getTiquetes()) {
			if (tiquetesSinUsar.contains(tiquete)) {
				tiquete.marcarComoUsado();
				tiquetesSinUsar.remove(tiquete);
				tiquetesUsados.add(tiquete);
			}
		}
	}
	
}
