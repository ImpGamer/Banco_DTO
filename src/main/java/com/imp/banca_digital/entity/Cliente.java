package com.imp.banca_digital.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor

public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String nombre;

    //Un cliente puede tener muchas cuentas bancarias, pero una cuenta solo le pertenenece
    //A un cliente
    @OneToMany(mappedBy = "cliente")
    //Propiedad que le indica que solamente vamos a escribir sobre este atributo, no se mostrara en el JSON
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<CuentaBancaria> cuentasBancarias;
}
