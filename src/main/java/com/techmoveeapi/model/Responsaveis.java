package com.techmoveeapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "responsaveis")
public class Responsaveis {
    private LocalDate dt_nascimento;
    private String sexo;
    @Id
    private String cpf;
    private String foto;
    private String senha;
    private String nome;
    private int endereco_id;

    public LocalDate getDt_nascimento() {
        return dt_nascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public String getCpf() {
        return cpf;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getEndereco_id() {
        return endereco_id;
    }

    public void setEndereco_id(int endereco_id) {
        this.endereco_id = endereco_id;
    }

    public Responsaveis() {
    }

    @Override
    public String toString() {
        return "Responsaveis{" +
                "dt_nascimento: " + dt_nascimento +
                ", sexo: " + sexo + '\'' +
                ", cpf=" + cpf +
                ", foto: " + foto + '\'' +
                ", senha: " + senha + '\'' +
                ", nome: " + nome + '\'' +
                ", enderecos id: " + endereco_id +
                '}';
    }

    public Responsaveis(LocalDate dt_nascimento, String sexo, String cpf, String foto, String senha, String nome, int endereco_id) {
        this.dt_nascimento = dt_nascimento;
        this.sexo = sexo;
        this.cpf = cpf;
        this.foto = foto;
        this.senha = senha;
        this.nome = nome;
        this.endereco_id = endereco_id;
    }
}
