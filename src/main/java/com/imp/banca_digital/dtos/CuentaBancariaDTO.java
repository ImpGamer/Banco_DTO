package com.imp.banca_digital.dtos;

import com.imp.banca_digital.enums.EstadoCuenta;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CuentaBancariaDTO {
    private String id;

    private double balance;
    private LocalDate fechaCreacion;

    private EstadoCuenta estadoCuenta;

    private ClienteDTO clienteDTO;
}
