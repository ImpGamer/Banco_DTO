package com.imp.banca_digital.mappers;

import com.imp.banca_digital.dtos.*;
import com.imp.banca_digital.entity.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class CuentaBancariaMapperImpl {
    //El mapper o mappers, son agregados para convertir de un objeto a otro sin necesidad aplicar la logica en el servicio
    public ClienteDTO mapearCliente_a_DTO(Cliente cliente) {
        ClienteDTO clienteDTO = new ClienteDTO();
        //Pasamos todas las propiedades de la entidad cliente al DTO
        BeanUtils.copyProperties(cliente,clienteDTO);
        return clienteDTO;
    }
    public Cliente mapearDTO_a_Cliente(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        BeanUtils.copyProperties(clienteDTO,cliente);
        return cliente;
    }
    public CuentaAhorroDTO mapearAhorro_a_DTO(CuentaAhorro cuentaAhorro) {
        CuentaAhorroDTO cuentaAhorroDTO = new CuentaAhorroDTO();
        BeanUtils.copyProperties(cuentaAhorro,cuentaAhorroDTO);
        //Obtenemos el cliente de la entidad y lo convertimos a un DTO para almcenarlo dentro de otro DTO
        cuentaAhorroDTO.setClienteDTO(mapearCliente_a_DTO(cuentaAhorro.getCliente()));
        return cuentaAhorroDTO;
    }
    public CuentaAhorro mapearDTO_a_Ahorro(CuentaAhorroDTO cuentaAhorroDTO) {
        CuentaAhorro cuentaAhorro = new CuentaAhorro();
        BeanUtils.copyProperties(cuentaAhorroDTO,cuentaAhorro);
        cuentaAhorro.setCliente(mapearDTO_a_Cliente(cuentaAhorroDTO.getClienteDTO()));
        return cuentaAhorro;
    }
    public CuentaActual mapearDTO_a_Actual(CuentaActualDTO cuentaActualDTO) {
        CuentaActual cuentaActual = new CuentaActual();
        BeanUtils.copyProperties(cuentaActualDTO,cuentaActual);
        cuentaActual.setCliente(mapearDTO_a_Cliente(cuentaActualDTO.getClienteDTO()));
        return cuentaActual;
    }
    public CuentaActualDTO mapearActual_a_DTO(CuentaActual cuentaActual) {
        CuentaActualDTO cuentaActualDTO = new CuentaActualDTO();
        BeanUtils.copyProperties(cuentaActual,cuentaActualDTO);
        cuentaActualDTO.setClienteDTO(mapearCliente_a_DTO(cuentaActual.getCliente()));
        return cuentaActualDTO;
    }
    public CuentaBancaria mapearDTO_a_Cuenta(CuentaBancariaDTO cuentaBancariaDTO) {
        CuentaBancaria cuentaBancaria = new CuentaActual();
        BeanUtils.copyProperties(cuentaBancariaDTO,cuentaBancaria);
        cuentaBancaria.setCliente(mapearDTO_a_Cliente(cuentaBancariaDTO.getClienteDTO()));
        return cuentaBancaria;
    }
    public CuentaBancariaDTO mapearCuenta_a_DTO(CuentaBancaria cuentaBancaria) {
        CuentaBancariaDTO cuentaBancariaDTO = new CuentaActualDTO();
        BeanUtils.copyProperties(cuentaBancaria,cuentaBancariaDTO);
        cuentaBancariaDTO.setClienteDTO(mapearCliente_a_DTO(cuentaBancaria.getCliente()));
        return cuentaBancariaDTO;
    }
    public OperacionCuentaDTO mapearOperacion_a_DTO(OperacionCuenta operacionCuenta) {
        OperacionCuentaDTO operacionCuentaDTO = new OperacionCuentaDTO();
        BeanUtils.copyProperties(operacionCuenta,operacionCuentaDTO);
        operacionCuentaDTO.setCuentaBancariaDTO(mapearCuenta_a_DTO(operacionCuenta.getCuentaBancaria()));
        return operacionCuentaDTO;
    }
    public OperacionCuenta mapearDTO_a_Cuenta(OperacionCuentaDTO operacionCuentaDTO) {
        OperacionCuenta operacionCuenta = new OperacionCuenta();
        BeanUtils.copyProperties(operacionCuentaDTO,operacionCuenta);
        operacionCuenta.setCuentaBancaria(mapearDTO_a_Cuenta(operacionCuentaDTO.getCuentaBancariaDTO()));
        return operacionCuenta;
    }
}