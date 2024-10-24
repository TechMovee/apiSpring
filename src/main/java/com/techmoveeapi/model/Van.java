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
@Table(name = "vans")
public class Van {
    private String modelo;
    private int foto_id;
    @Id
    private String placa;
    private String transportador_cnh;
    private boolean acessibilidade;
    private double mensalidade;
    public Van() {
    }

    public Van(String modelo, int foto_id, String placa, String transportador_cnh, boolean acessibilidade, double mensalidade) {
        this.modelo = modelo;
        this.foto_id = foto_id;
        this.placa = placa;
        this.transportador_cnh = transportador_cnh;
        this.acessibilidade = acessibilidade;
        this.mensalidade = mensalidade;
    }
}

