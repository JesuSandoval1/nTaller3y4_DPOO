package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.Aeropuerto;

public class CalculadoraTarifasTemporadaAlta extends CalculadoraTarifas {

	protected final int COSTO_POR_KM = 1000;

	public CalculadoraTarifasTemporadaAlta() {
		super();
	}

	public int calcularCostoBase​(Vuelo vuelo, Cliente cliente) {
		Aeropuerto origen = vuelo.getRuta().getOrigen();
		Aeropuerto destino = vuelo.getRuta().getDestino();
		int distancia = Aeropuerto.calcularDistancia(origen, destino);
		return distancia * COSTO_POR_KM;
	}
	
	public double calcularPorcentajeDescuento​(Cliente cliente) {
		return 0;
	}
}
