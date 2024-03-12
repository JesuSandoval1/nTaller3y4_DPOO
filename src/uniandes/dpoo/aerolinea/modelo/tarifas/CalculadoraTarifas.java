package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;

public abstract class CalculadoraTarifas {

	public static final double IMPUESTO = 0.28;

	public int calcularTarifa​(Vuelo vuelo, Cliente cliente) {
		int costoBase = calcularCostoBase​(vuelo, cliente);
	    double porcentajeDescuento = calcularPorcentajeDescuento​(cliente);
	    double costoDespuesDeDescuento = costoBase*(1-porcentajeDescuento);
	    double tarifa = costoDespuesDeDescuento + calcularValorImpuestos​((int)costoDespuesDeDescuento);
		return (int) tarifa;
	}

	protected abstract int calcularCostoBase​(Vuelo vuelo, Cliente cliente);

	protected abstract double calcularPorcentajeDescuento​(Cliente cliente);

	protected int calcularValorImpuestos​(int costoBase) {
		double impuesto = (double) costoBase * CalculadoraTarifas.IMPUESTO;
	    return (int) impuesto;
	}
}
