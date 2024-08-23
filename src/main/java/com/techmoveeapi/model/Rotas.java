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
    private int qntdparadas;
    private int tempo;
    private int enderecos;

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

    public int getQntdparadas() {
        return qntdparadas;
    }

    public void setQntdparadas(int qntdparadas) {
        this.qntdparadas = qntdparadas;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public int getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(int enderecos) {
        this.enderecos = enderecos;
    }

    @Override
    public String toString() {
        return "Rotas{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", periodo='" + periodo + '\'' +
                ", qntdparadas=" + qntdparadas +
                ", tempo=" + tempo +
                ", enderecos=" + enderecos +
                '}';
    }

    public Rotas() {
    }

    public Rotas(int id, String nome, String periodo, int qntdparadas, int tempo, int enderecos) {
        this.id = id;
        this.nome = nome;
        this.periodo = periodo;
        this.qntdparadas = qntdparadas;
        this.tempo = tempo;
        this.enderecos = enderecos;
    }
}
