package com.imp.banca_digital.dtos;

import com.imp.banca_digital.enums.TipoOperacion;
import lombok.Data;

import java.time.LocalDate;

@Data
public class OperacionCuentaDTO {
    private Long id;
    private LocalDate fechaOperacion;
    private double monto;
    private TipoOperacion tipoOperacion;
    private CuentaBancariaDTO cuentaBancariaDTO;
}
