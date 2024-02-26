package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.Ruta;
import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;

public abstract class CalculadoraTarifas {

	public static final double IMPUESTO = 0.28;

	public int calcularTarifa​(Vuelo vuelo, Cliente cliente) {
		return -1;
	}

	protected abstract int calcularCostoBase​(Vuelo vuelo, Cliente cliente);

	protected abstract double calcularPorcentajeDescuento​(Cliente cliente);

	protected int calcularDistanciaVuelo​(Ruta ruta) {
		return -1;
	}

	protected int calcularValorImpuestos​(int costoBase) {
		return -1;
	}
}
