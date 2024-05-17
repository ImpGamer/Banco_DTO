package com.imp.banca_digital.dtos;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CuentaAhorroDTO extends CuentaBancariaDTO{

    private double tasaInteres;
}
