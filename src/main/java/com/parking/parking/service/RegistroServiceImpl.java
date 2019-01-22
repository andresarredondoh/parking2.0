package com.parking.parking.service;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parking.parking.dao.RegistroRepository;
import com.parking.parking.dto.RegistroDTO;
import com.parking.parking.model.Registro;
import com.parking.parking.util.MapeoDTO;


@Service
public class RegistroServiceImpl implements RegistroService {

	@Autowired
	protected RegistroRepository registroRepository;

	MapeoDTO mapeoDTO = new MapeoDTO();

	@Override
	public Registro save(Registro registro) {
		if (validator(registro) && cantidadDeVehiculos(registro) && dateValidator(registro) && placaRepetida(registro)) {
			return this.registroRepository.save(registro);
		}
		return null;

	}

	@Override
	public List<Registro> findAll() {
		return  this.registroRepository.findAll();
	}

	@Override
	public Integer deleteUser(Registro registro) {
		this.registroRepository.deleteById(registro.getId());
		int hora = 0;
		hora = cobro(registro);
		return hora;
	}

	public boolean validator(Registro registro) {
		boolean isValid = true;
		if (registro.getTipoVehiculo() == null || registro.getTipoVehiculo() == "") {
			isValid = false;
		} else if (registro.getPlaca() == null || registro.getPlaca() == "") {
			isValid = false;
		} else if (registro.getFechaIngreso() == null) {
			isValid = false;
		} else if ((registro.getTipoVehiculo().equals("Moto")) && (registro.getCilindraje() == null)) {
			isValid = false;
		}
		return isValid;
	}

	private boolean cantidadDeVehiculos(Registro vehiculo) {
		boolean permiso = false;
		Integer carro = 0;
		Integer motos = 0;
		List<Registro> cantidad = this.registroRepository.findAll();
		for (Registro registro : cantidad) {
			if (registro.getTipoVehiculo().equals("Carro")) {
				carro += 1;
			} else {
				motos += 1;
			}
		}
		if ((vehiculo.getTipoVehiculo().equals("Carro")) && (carro < 20)) {
			permiso = true;
		} else if ((motos < 10) && (vehiculo.getTipoVehiculo().equals("Moto"))) {
			permiso = true;
		}
		return permiso;
	}

	private boolean dateValidator(Registro registro) {
		LocalDate fechaActual = LocalDate.now();
		if ((registro.getPlaca().toUpperCase().charAt(0) == 'A')
				&& ((fechaActual.getDayOfWeek().equals(DayOfWeek.SUNDAY)
						|| fechaActual.getDayOfWeek().equals(DayOfWeek.MONDAY)))) {

			return true;
		} else if (registro.getPlaca().toUpperCase().charAt(0) != 'A') {
			return true;
		}
		return false;
	}

	private Integer cobro(Registro registro) {
		LocalDateTime fechaSalida = LocalDateTime.now(); ;
		LocalDateTime fechaIngreso = registro.getFechaIngreso();
		Duration seconds = Duration.between(fechaIngreso, fechaSalida);
		Long minutos = seconds.toMinutes();
		Double tiempoParqueo = minutos / 60D;
		Integer horasParqueo = tiempoParqueo.intValue();

		if (tiempoParqueo > horasParqueo) {
			horasParqueo += 1;
		}
		if(registro.getTipoVehiculo().equals("Carro")) {
			return totalAPagarCarro(horasParqueo);
		}else {
			return totalAPagarMoto(horasParqueo,registro.getCilindraje());
		}
		
	}

	private Integer totalAPagarMoto(Integer horasParqueo, Integer cilindraje) {
		Integer horaMoto = 500;
		Integer diaMoto = 4000;
		Integer totalHora=500;
		Integer dias =(int) Math.floor(horasParqueo/24);
		
		Integer horas =horasParqueo%24;
		if(horas==0) {
			horas=1;
		}
		Integer total=0;
		if(horas>=9 ) {
			dias=dias+1;
		}else {
			totalHora=horas*horaMoto;
		}
		if(cilindraje>500) {
			 total= (dias*diaMoto)+totalHora+2000;
		}else {
			 total= (dias*diaMoto)+totalHora;
		}
		

		return total;
	}

	private Integer totalAPagarCarro(Integer horasParqueo) {
		Integer horaCarro =1000;
		Integer diaCarro = 8000;
		Integer totalHora=1000;
		Integer dias =(int) Math.floor(horasParqueo/24);
		Integer horas =horasParqueo%24;
		
		if(horas==0) {
			horas=1;
		}
		if(horas>=9 ) {
			dias=dias+1;
		}else {
			totalHora=horas*horaCarro;
		}
		Integer total= (dias*diaCarro)+totalHora;

		return total;
	}


	@Override
	public List<RegistroDTO> listUsers() {
		List<RegistroDTO> list = new ArrayList<RegistroDTO>();
		for (Registro registro : this.registroRepository.findAll()) {
			list.add(mapeoDTO.convertirRegistroEntidad(registro));
		}
		
		return list;
	}
	
	public boolean placaRepetida(Registro registro) {
		boolean placa = true;
		for (Registro placas : this.registroRepository.findAll()) {
			if(placas.getPlaca().equals(registro.getPlaca())) {
				placa=false;
			}
		}
		return placa;
	}
}
