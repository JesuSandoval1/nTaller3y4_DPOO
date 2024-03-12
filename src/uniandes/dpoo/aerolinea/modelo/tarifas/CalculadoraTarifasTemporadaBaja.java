package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.Aeropuerto;
import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.cliente.ClienteCorporativo;

public class CalculadoraTarifasTemporadaBaja extends CalculadoraTarifas {

	protected final int COSTO_POR_KM_NATURAL = 600;
	protected final int COSTO_POR_KM_CORPORATIVO = 900;
	protected final double DESCUENTO_PEQ = 0.02;
	protected final double DESCUENTO_MEDIANAS = 0.1;
	protected final double DESCUENTO_GRANDES = 0.2;

	public CalculadoraTarifasTemporadaBaja() {
		super();
	}

	public int calcularCostoBase​(Vuelo vuelo, Cliente cliente) {
		Aeropuerto origen = vuelo.getRuta().getOrigen();
	    Aeropuerto destino = vuelo.getRuta().getDestino();
	    int distancia = Aeropuerto.calcularDistancia(origen, destino);
	    int costoBase;
	    if (cliente.getTipoCliente().equals("Corporativo")) {
	        costoBase = distancia * COSTO_POR_KM_CORPORATIVO;
	    } else {
	        costoBase = distancia * COSTO_POR_KM_NATURAL;
	    }
	    return costoBase;
	}
	
	public double calcularPorcentajeDescuento​(Cliente cliente) {
	    if (cliente.getTipoCliente().equals("Corporativo")) {
	    	double porcentajeDescuento = 0;
	        ClienteCorporativo clienteCorporativo = (ClienteCorporativo) cliente;
	        int tamanoEmpresa = clienteCorporativo.getTamanoEmpresa();
	        if (tamanoEmpresa == 1) {
	            porcentajeDescuento = DESCUENTO_GRANDES;
	        } else if (tamanoEmpresa == 2) {
	            porcentajeDescuento = DESCUENTO_MEDIANAS;
	        } else if (tamanoEmpresa == 3) {
	            porcentajeDescuento = DESCUENTO_PEQ;
	        }
	        return porcentajeDescuento;
	    } else {
	    	return (double) 0;
	    }
	}
	
}
