package com.imp.banca_digital.entity;

import com.imp.banca_digital.enums.TipoOperacion;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class OperacionCuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fechaOperacion;

    private double monto;

    @ManyToOne
    @JoinColumn(name = "cuentaBancaria_id")
    private CuentaBancaria cuentaBancaria;

    @Enumerated(EnumType.STRING)
    private TipoOperacion tipoOperacion;

    @Override
    public String toString() {
        return "ID: "+this.id+"\nFecha de Operacion: "+this.fechaOperacion+"\nCliente de la cuenta: "+
                this.cuentaBancaria.getCliente().getNombre()+"\nTipo de Operacion: "+this.tipoOperacion
                +"\nMonto: "+this.monto;
    }
}