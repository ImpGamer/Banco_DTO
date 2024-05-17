package com.imp.banca_digital.entity;

import com.imp.banca_digital.enums.EstadoCuenta;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
//Todas las clases que hereden de esta se almacenaran como un valor en esta misma tabla
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//Creacion de la columna del tipo de cuenta bancaria (clase hija)
@DiscriminatorColumn(name = "TIPO",length = 6)
//Otro tipo de herencia: Donde creara una tabla por cada clase hija
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

@Data
@NoArgsConstructor

public abstract class CuentaBancaria {

    @Id
    private String id;

    private double balance;
    private LocalDate fechaCreacion;

    @Enumerated(EnumType.STRING) //Indicarle que el ENUM o constante es de tipo String
    private EstadoCuenta estadoCuenta;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "cuentaBancaria",fetch = FetchType.LAZY)
    private List<OperacionCuenta> operacionesRealizadas;

    public void depositar(double deposito) {
        balance += deposito;
    }

    public void retirar(double retiro) {this.balance -= retiro;}
    @Override
    public String toString() {
        return "ID: "+this.id+"\nBalance: "+this.balance+"\nFecha Creacion: "+this.fechaCreacion+
                "\nEstado: "+this.estadoCuenta+"\nCliente de la cuenta: "+this.cliente.getNombre();
    }
    @PrePersist
    void setValues() {
        this.id = UUID.randomUUID().toString();
        this.fechaCreacion = LocalDate.now();
        this.estadoCuenta = EstadoCuenta.CREADA;
    }
}
