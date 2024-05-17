package com.imp.banca_digital.entity;

import com.imp.banca_digital.enums.EstadoCuenta;
import com.imp.banca_digital.enums.TipoOperacion;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDate;


@EqualsAndHashCode(callSuper = true)
@Entity
@Data
//Se declara que valor ira en la columna creada por la clase padre
@DiscriminatorValue("Actual")
@AllArgsConstructor
@NoArgsConstructor
public class CuentaActual extends CuentaBancaria {

    public CuentaActual(String Id, double balance, LocalDate fechaCreacion, double sobreGiro, EstadoCuenta estadoCuenta) {
        this.setId(Id);
        this.setBalance(balance);
        this.setFechaCreacion(fechaCreacion);
        this.sobreGiro = sobreGiro;
        this.setEstadoCuenta(estadoCuenta);
    }

    private double sobreGiro;
    @Override
    public String toString() {
        return super.toString()+"\nSobreGiro: "+this.sobreGiro;
    }
}
