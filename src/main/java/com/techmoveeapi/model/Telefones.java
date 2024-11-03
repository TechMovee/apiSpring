package com.techmoveeapi.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "telefones")
public class Telefones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length = 15, unique = true)
    private String numero;

    @Column(nullable = false, length = 20)
    private String tipo;

    public Telefones() {
    }

    public Telefones(Integer id, String numero, String tipo) {
        this.id = id;
        this.tipo = tipo;
        this.numero = numero;
    }
}
