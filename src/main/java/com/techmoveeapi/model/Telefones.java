package com.techmoveeapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "telefones")
public class Telefones {
    @Id
    private int id;
    private String numero;


    public Telefones() {
    }

    public Telefones(int id, String numero) {
        this.id = id;
        this.numero = numero;
    }
}
