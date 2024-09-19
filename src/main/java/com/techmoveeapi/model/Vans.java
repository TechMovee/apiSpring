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
public class Vans {
    private String modelo;
    private int ano;
    private int foto_id;
    @Id
    private String placa;
    private String transportador_cnh;
    public Vans() {
    }

    public Vans(String modelo, int ano, int foto_id, String placa, String transportador_cnh) {
        this.modelo = modelo;
        this.ano = ano;
        this.foto_id = foto_id;
        this.placa = placa;
        this.transportador_cnh = transportador_cnh;
    }
}

