package com.imp.banca_digital;

import com.imp.banca_digital.entity.*;
import com.imp.banca_digital.enums.EstadoCuenta;
import com.imp.banca_digital.enums.TipoOperacion;
import com.imp.banca_digital.repository.ClienteRepository;
import com.imp.banca_digital.repository.CuentaBancariaRepository;
import com.imp.banca_digital.repository.OperacionCuentaRepository;
import com.imp.banca_digital.service.CuentaBancariaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.core.Local;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class BancaDigitalApplication {

	public static void main(String[] args) {
		SpringApplication.run(BancaDigitalApplication.class, args);

	}


	//Clase que nos permite escribir codigo Java para pruebas
	//@Bean
	CommandLineRunner start(ClienteRepository clienteRepository, CuentaBancariaRepository
							cuentaBancariaRepository, OperacionCuentaRepository operacionCuentaRepository) {
		return args -> {
			Stream.of("Christian","Julen","Biaggio","Lanudo").forEach(nombre -> {
				Cliente cliente = new Cliente();
				cliente.setNombre(nombre);
				cliente.setEmail(nombre+"@gmail.com");
				clienteRepository.save(cliente);
			});

			//Le asignamos cuentas bancarias
			clienteRepository.findAll().forEach(cliente -> {
				int random = (int) (Math.random() * 2) + 1;
				if(random == 1) {
					CuentaActual cuentaActual = new CuentaActual();
					cuentaActual.setId(UUID.randomUUID().toString());
					cuentaActual.setBalance(Math.random()*9000);
					cuentaActual.setFechaCreacion(LocalDate.now());
					cuentaActual.setEstadoCuenta(EstadoCuenta.CREADA);
					cuentaActual.setSobreGiro(9000);
					cuentaActual.setCliente(cliente);
					cuentaBancariaRepository.save(cuentaActual);
				} else if(random == 2) {
					CuentaAhorro cuentaAhorro = new CuentaAhorro();
					cuentaAhorro.setId(UUID.randomUUID().toString());
					cuentaAhorro.setBalance(Math.random()*9000);
					cuentaAhorro.setFechaCreacion(LocalDate.now());
					cuentaAhorro.setEstadoCuenta(EstadoCuenta.CREADA);
					cuentaAhorro.setTasaInteres(5.5);
					cuentaAhorro.setCliente(cliente);
					cuentaBancariaRepository.save(cuentaAhorro);
				}

			});

			cuentaBancariaRepository.findAll().forEach(cuentaBancaria -> {
				for(int i=0;i<10;i++) {
					OperacionCuenta operacionCuenta = new OperacionCuenta();
					operacionCuenta.setFechaOperacion(LocalDate.now());
					operacionCuenta.setMonto(Math.random()*12000);
					operacionCuenta.setTipoOperacion(Math.random()*10 > 5?TipoOperacion.CREDITO:
							TipoOperacion.DEBITO);
					operacionCuenta.setCuentaBancaria(cuentaBancaria);
					operacionCuentaRepository.save(operacionCuenta);
				}
			});
		};
	}

}
