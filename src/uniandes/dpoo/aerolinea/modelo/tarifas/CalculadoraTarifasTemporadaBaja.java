package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;

public class CalculadoraTarifasTemporadaBaja {

	protected final int COSTO_POR_KM_NATURAL = 600;
	protected final int COSTO_POR_KM_CORPORATIVO = 900;
	protected final double DESCUENTO_PEQ = 0.02;
	protected final double DESCUENTO_MEDIANAS = 0.1;
	protected final double DESCUENTO_GRANDES = 0.2;

	public CalculadoraTarifasTemporadaBaja() {

	}

	public int calcularCostoBase​(Vuelo vuelo, Cliente cliente) {
		return -1;
	}
	
	public double calcularPorcentajeDescuento​(Cliente cliente) {
		return -1;
	}
	
}
