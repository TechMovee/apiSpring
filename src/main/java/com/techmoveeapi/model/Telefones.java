package com.techmoveeapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "telefones")
public class Telefones {
    @Id
    private int id;
    private String numero;

    public int getId() {
        return id;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNumero() {
        return numero;
    }

    public Telefones() {
    }

    public Telefones(int id, String numero) {
        this.id = id;
        this.numero = numero;
    }
}
