package com.imp.banca_digital.service;

import com.imp.banca_digital.dtos.OperacionCuentaDTO;
import com.imp.banca_digital.entity.CuentaBancaria;
import com.imp.banca_digital.entity.OperacionCuenta;
import com.imp.banca_digital.exceptions.OperationNotFoundException;
import com.imp.banca_digital.mappers.CuentaBancariaMapperImpl;
import com.imp.banca_digital.repository.OperacionCuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OperacionCuentasService {
    @Autowired
    private OperacionCuentaRepository operacionCuentaRepository;
    @Autowired
    private CuentaBancariaMapperImpl cuentaBancariaMapper;

    public List<OperacionCuentaDTO> listarHistorial_porCuenta(String cuentaID) {
        List<OperacionCuenta> operacionesDeCuenta = operacionCuentaRepository.findOperacionesByCuentaID(cuentaID);
        return operacionesDeCuenta.stream()
                .map(operacion -> cuentaBancariaMapper.mapearOperacion_a_DTO(operacion))
                .toList();
    }
    public List<OperacionCuentaDTO> listarOperaciones() {
        List<OperacionCuenta> operacionesCuentas = operacionCuentaRepository.findAll();
        return operacionesCuentas.stream()
                .map(operacion -> cuentaBancariaMapper.mapearOperacion_a_DTO(operacion))
                .toList();
    }
    public String eliminarOperacion(Long operacionID)throws OperationNotFoundException {
        Optional<OperacionCuenta> operacionBBDD = operacionCuentaRepository.findById(operacionID);
        if(operacionBBDD.isEmpty()) {
            throw new OperationNotFoundException("Operacion no encontrada en los registros");
        }
        operacionCuentaRepository.delete(operacionBBDD.get());
        return "La operacion ha sido eliminada de la cuenta de: "
                +operacionBBDD.get().getCuentaBancaria().getCliente().getNombre();
    }

}
