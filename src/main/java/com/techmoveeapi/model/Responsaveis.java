package com.techmoveeapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "responsáveis")
public class Responsaveis {
    private int dt_nascimento;
    private int telefone;
    private String sexo;
    @Id
    private int cpf;
    private String foto;
    private String senha;
    private String nome;
    private int enderecos;

    public int getDt_nascimento() {
        return dt_nascimento;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public String getSexo() {
        return sexo;
    }

    public int getCpf() {
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

    public int getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(int enderecos) {
        this.enderecos = enderecos;
    }

    public Responsaveis() {
    }

    @Override
    public String toString() {
        return "Responsaveis{" +
                "dt_nascimento=" + dt_nascimento +
                ", telefone=" + telefone +
                ", sexo='" + sexo + '\'' +
                ", cpf=" + cpf +
                ", foto='" + foto + '\'' +
                ", senha='" + senha + '\'' +
                ", nome='" + nome + '\'' +
                ", enderecos=" + enderecos +
                '}';
    }

    public Responsaveis(int dt_nascimento, int telefone, String sexo, int cpf, String foto, String senha, String nome, int enderecos) {
        this.dt_nascimento = dt_nascimento;
        this.telefone = telefone;
        this.sexo = sexo;
        this.cpf = cpf;
        this.foto = foto;
        this.senha = senha;
        this.nome = nome;
        this.enderecos = enderecos;
    }
}
