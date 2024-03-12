package uniandes.dpoo.aerolinea.modelo.cliente;

import java.util.List;

import uniandes.dpoo.aerolinea.tiquetes.Tiquete;

public class ClienteNatural extends Cliente{
	
	public static final String PERSONAL = "Natural";
	private String nombre;
	private String id;
	
	public ClienteNatural(String nombre) {
		super();
		this.nombre = nombre;
		this.id = generadorIds();
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public String getIdentificador() {
		return this.id;
	}
	
	public String getTipoCliente() {
		return ClienteNatural.PERSONAL;
	}
	
	@Override
	public String generadorIds() {
		String codigo;
		do {
		int numero = (int) (Math.random() * 10e7);
		codigo = String.format("N%07d", numero);
		} while (identificadores.contains(codigo));
		identificadores.add(codigo); 
		return codigo;
	}
}
