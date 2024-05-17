package com.imp.banca_digital.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
//Se declara que valor ira en la columna creada por la clase padre
@DiscriminatorValue("Ahorro")
@NoArgsConstructor
@AllArgsConstructor

public class CuentaAhorro extends CuentaBancaria {

    @Getter
    @Setter
    private double tasaInteres;
    @Override
    public String toString() {
        return super.toString()+"\nTasa Interes: "+this.tasaInteres;
    }

}
