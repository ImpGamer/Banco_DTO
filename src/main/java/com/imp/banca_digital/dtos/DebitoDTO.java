package com.imp.banca_digital.dtos;

import lombok.Data;

@Data
public class DebitoDTO {
    private String cuentaID;
    private double monto;
    private String descripcion;
}
