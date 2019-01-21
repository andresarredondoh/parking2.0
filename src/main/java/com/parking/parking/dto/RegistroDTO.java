package com.parking.parking.dto;

import java.time.LocalDateTime;

public class RegistroDTO {

	private Long id;
	private String tipoVehiculo;
	private String placa;
	private String cilindraje;
	private LocalDateTime fechaIngreso;
	private LocalDateTime fechaSalida;
	private double precio;
	
	public RegistroDTO(Long id, String tipoVehiculo, String placa, String cilindraje, LocalDateTime fechaIngreso,
			LocalDateTime fechaSalida, double precio) {
		super();
		this.id = id;
		this.tipoVehiculo = tipoVehiculo;
		this.placa = placa;
		this.cilindraje = cilindraje;
		this.fechaIngreso = fechaIngreso;
		this.fechaSalida = fechaSalida;
		this.precio = precio;
	}

	public RegistroDTO() {
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getTipoVehiculo() {
		return tipoVehiculo;
	}
	public void setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getCilindraje() {
		return cilindraje;
	}
	public void setCilindraje(String cilindraje) {
		this.cilindraje = cilindraje;
	}
	public LocalDateTime getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(LocalDateTime fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	public LocalDateTime getFechaSalida() {
		return fechaSalida;
	}
	public void setFechaSalida(LocalDateTime fechaSalida) {
		this.fechaSalida = fechaSalida;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
}
