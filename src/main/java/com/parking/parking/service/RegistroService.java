package com.parking.parking.service;


import java.util.List;

import com.parking.parking.dto.RegistroDTO;
import com.parking.parking.model.Registro;


public interface RegistroService {

//	Guardar usuario
	Registro save(Registro registro) throws Exception;

//	Listar todos los usuarios
	List<Registro> findAll();

//  Eliminar Registro
	Integer deleteUser(Registro registro);

// Listar usuarioDTO
	List<RegistroDTO> listUsers();



}
