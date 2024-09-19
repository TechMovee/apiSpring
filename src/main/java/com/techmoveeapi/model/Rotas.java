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
@Table(name = "rotas")
public class Rotas {
    @Id
    private int id;
    private String nome;
    private String periodo;
    private int quantidade_paradas;
    private String tempo;
    private int endereco_id;



    public Rotas() {
    }

    public Rotas(int id, String nome, String periodo, int quantidade_paradas, String tempo, int enderecos_id) {
        this.id = id;
        this.nome = nome;
        this.periodo = periodo;
        this.quantidade_paradas = quantidade_paradas;
        this.tempo = tempo;
        this.endereco_id = enderecos_id;
    }
}
