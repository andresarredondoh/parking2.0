package com.parking.parking.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parking.parking.model.Registro;

public interface RegistroRepository extends JpaRepository<Registro, Long>{

	@SuppressWarnings("unchecked" )
	Registro save(Registro registro);

	
}
