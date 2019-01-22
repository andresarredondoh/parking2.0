package com.parking.parking;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.parking.parking.dao.RegistroRepository;
import com.parking.parking.model.Registro;
import com.parking.parking.service.RegistroService;
import com.parking.parking.service.RegistroServiceImpl;
import com.parking.parking.util.MapeoDTO;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ParkingApplicationTests {
	
	@InjectMocks
	RegistroServiceImpl registroServiceImpl;

	
	@InjectMocks
	RegistroServiceImpl registroServiceImplSpy = Mockito.spy(RegistroServiceImpl.class);

	@Mock
	private RegistroRepository registroRepository;

	@Mock
	private MapeoDTO mapeoDTO;
	
	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);
	}

	
	@Test
	public void save() {
		Registro registro = new Registro("Carro","OMK944");
		registro.setFechaIngreso(LocalDateTime.now());
		registro=registroRepository.save(registro);
		assertTrue(registro==null);
		
	}
	
	@Test
	public void findAll() {
		List<Registro> lista = this.registroRepository.findAll();
		assertTrue(lista!=null);
	}
//	
	@Test
	public void deleteUser() {
		Registro registro = new Registro((long) 1, "Carro", "OMK944");
		registro.setFechaIngreso(LocalDateTime.now());
		
		when(registroRepository.getOne(registro.getId())).thenReturn(registro);
        Integer a = registroServiceImpl.deleteUser(registro);

        assertTrue(a!=null);
		
	}
//	
	@Test
	public void validator() {
		Registro registro = new Registro("Carro", "OMK944");
		registro.setFechaIngreso(LocalDateTime.now());
		boolean validate = registroServiceImpl.validator(registro);
		assertTrue(validate);
		
		
	}
//	
	@Test
	public void cantidadDeVehiculos() {
	Integer carros= 20;
	Integer motos =10;
	}
//	
//	@Test
//	public void dateValidator() {
//	}
//	
//	@Test
//	public void cobro() {
//	}
//	
//	@Test
//	public void totalAPagarMoto() {
//	}
//	
//	@Test
//	public void totalAPagarCarro() {
//	}
//	
//	@Test
//	public void listUsers() {
//	}
//	
//	@Test
//	public void placaRepetida() {
//		
//	}
//	
	

}
