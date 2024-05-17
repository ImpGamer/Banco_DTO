package com.imp.banca_digital.service;


import com.imp.banca_digital.dtos.CuentaActualDTO;
import com.imp.banca_digital.dtos.CuentaAhorroDTO;
import com.imp.banca_digital.dtos.CuentaBancariaDTO;
import com.imp.banca_digital.entity.*;
import com.imp.banca_digital.enums.TipoOperacion;
import com.imp.banca_digital.exceptions.BankAccountNotFoundException;
import com.imp.banca_digital.exceptions.ClientNotFoundException;
import com.imp.banca_digital.exceptions.InsufficientBalanceException;
import com.imp.banca_digital.mappers.CuentaBancariaMapperImpl;
import com.imp.banca_digital.repository.ClienteRepository;
import com.imp.banca_digital.repository.CuentaBancariaRepository;
import com.imp.banca_digital.repository.OperacionCuentaRepository;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
@Transactional
@Slf4j
public class CuentaBancariaService {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private CuentaBancariaRepository cuentaBancariaRepository;
    @Autowired
    private OperacionCuentaRepository operacionCuentaRepository;
    @Autowired
    private CuentaBancariaMapperImpl cuentaBancariaMapper;

    public CuentaActualDTO crearCuentaActual(Long clienteID, CuentaActual cuentaActual) throws ClientNotFoundException {
        Optional<Cliente> clienteBBDD = clienteRepository.findById(clienteID);
        if (clienteBBDD.isEmpty()) {
            throw new ClientNotFoundException("El cliente con ID: " + clienteBBDD + " no fue encontrado");
        }
        cuentaActual.setCliente(clienteBBDD.get());

        return cuentaBancariaMapper.mapearActual_a_DTO(cuentaBancariaRepository.save(cuentaActual));
    }

    public CuentaAhorroDTO crearCuentaAhorro(Long clienteID, CuentaAhorro cuentaAhorro) {
        Optional<Cliente> clienteBBDD = clienteRepository.findById(clienteID);
        if (clienteBBDD.isEmpty()) {
            throw new ClientNotFoundException("El cliente con ID: " + clienteBBDD + " no fue encontrado");
        }
        cuentaAhorro.setCliente(clienteBBDD.get());

        return cuentaBancariaMapper.mapearAhorro_a_DTO(cuentaBancariaRepository.save(cuentaAhorro));
    }

    public CuentaBancariaDTO obtenerCuenta_ID(String Id) throws BankAccountNotFoundException {
        Optional<CuentaBancaria> cuentaBBDD = cuentaBancariaRepository.findById(Id);
        if (cuentaBBDD.isEmpty()) {
            throw new BankAccountNotFoundException("Cuenta Bancaria no encontrada");
        }
        return cuentaBancariaMapper.mapearCuenta_a_DTO(cuentaBBDD.get());
    }

    public void retirar(double retiro, String cuentaID) throws InsufficientBalanceException, BankAccountNotFoundException {
        CuentaBancaria cuentaBBDD = cuentaBancariaRepository.findById(cuentaID)
                .orElseThrow(() -> new BankAccountNotFoundException("No se encontro la cuenta con ID: " + cuentaID));
        if (retiro > cuentaBBDD.getBalance()) {
            throw new InsufficientBalanceException("El retiro es mayor al saldo acutal. Intente de nuevo");
        }
        OperacionCuenta nuevaOperacion = new OperacionCuenta();
        nuevaOperacion.setTipoOperacion(TipoOperacion.DEBITO);
        nuevaOperacion.setFechaOperacion(LocalDate.now());
        nuevaOperacion.setCuentaBancaria(cuentaBBDD);
        nuevaOperacion.setMonto(retiro);

        operacionCuentaRepository.save(nuevaOperacion);

        cuentaBBDD.retirar(retiro);
        cuentaBancariaRepository.save(cuentaBBDD);
    }

    public void depositar(double deposito, String cuentaID) throws BankAccountNotFoundException {
        CuentaBancaria cuentaBBDD = cuentaBancariaRepository.findById(cuentaID)
                .orElseThrow(() -> new BankAccountNotFoundException("No se encontro la cuenta con ID: " + cuentaID));

        OperacionCuenta nuevaOperacion = new OperacionCuenta();
        nuevaOperacion.setTipoOperacion(TipoOperacion.CREDITO);
        nuevaOperacion.setFechaOperacion(LocalDate.now());
        nuevaOperacion.setCuentaBancaria(cuentaBBDD);
        nuevaOperacion.setMonto(deposito);

        operacionCuentaRepository.save(nuevaOperacion);

        cuentaBBDD.depositar(deposito);
        cuentaBancariaRepository.save(cuentaBBDD);
    }

    public void transferir(double monto, String cuentaID_prop, String cuentaID_dest) {
        retirar(monto, cuentaID_prop);
        depositar(monto, cuentaID_dest);
    }

    public List<CuentaBancariaDTO> listarCuentasBancarias() {
        List<CuentaBancaria> cuentasBancarias = cuentaBancariaRepository.findAll();
        return cuentasBancarias.stream()
                .map(cuentaBancaria -> {
                    if(cuentaBancaria instanceof CuentaAhorro) {
                        CuentaAhorroDTO cuentaAhorroDTO = new CuentaAhorroDTO();
                        BeanUtils.copyProperties(cuentaBancaria,cuentaAhorroDTO);
                        cuentaAhorroDTO.setClienteDTO(cuentaBancariaMapper
                                .mapearCliente_a_DTO(cuentaBancaria.getCliente()));
                        cuentaAhorroDTO.setTasaInteres(((CuentaAhorro) cuentaBancaria).getTasaInteres());
                        return cuentaAhorroDTO;
                    } else if(cuentaBancaria instanceof CuentaActual) {
                        CuentaActualDTO cuentaActualDTO = new CuentaActualDTO();
                        BeanUtils.copyProperties(cuentaBancaria,cuentaActualDTO);
                        cuentaActualDTO.setClienteDTO(cuentaBancariaMapper
                                .mapearCliente_a_DTO(cuentaBancaria.getCliente()));
                        cuentaActualDTO.setSobregiro(((CuentaActual) cuentaBancaria).getSobreGiro());
                        return cuentaActualDTO;
                    } else {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .toList();
    }

}