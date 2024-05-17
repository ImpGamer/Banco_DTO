package com.imp.banca_digital.dtos;

import lombok.Data;

@Data
public class ClienteDTO {
    //Los atributos del DTO solamente son aquellos atributos de la entidad que deseamos pasar
    private Long id;
    private String nombre;
    private String email;
}