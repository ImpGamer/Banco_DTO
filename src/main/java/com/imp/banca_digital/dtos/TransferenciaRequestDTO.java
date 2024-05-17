package com.imp.banca_digital.dtos;

import lombok.Data;

@Data
public class TransferenciaRequestDTO {
    private String propietario_ID;
    private String destinatario_ID;
    private double monto;
    private String descripcion;

}
