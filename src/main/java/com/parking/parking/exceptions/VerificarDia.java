package com.parking.parking.exceptions;

public class VerificarDia extends RuntimeException {
	private static final long serialVersionUID = -3983964059405092565L;

	public VerificarDia(String mensaje) {
		super(mensaje);
	}
}
