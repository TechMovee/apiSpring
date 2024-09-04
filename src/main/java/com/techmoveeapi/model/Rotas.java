package com.techmoveeapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public int setQuantidade_paradas() {
        return quantidade_paradas;
    }

    public void setQuantidade_paradas(int quantidade_paradas) {
        this.quantidade_paradas = quantidade_paradas;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public int getEndereco_id() {
        return endereco_id;
    }

    public void setEndereco_id(int endereco_id) {
        this.endereco_id = endereco_id;
    }

    @Override
    public String toString() {
        return "Rotas{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", periodo='" + periodo + '\'' +
                ", quantidade de paradas=" + quantidade_paradas +
                ", tempo=" + tempo +
                ", enderecos id=" + endereco_id +
                '}';
    }

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
