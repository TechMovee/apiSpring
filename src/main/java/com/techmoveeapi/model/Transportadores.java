package com.techmoveeapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "transportadores")
public class Transportadores {
    private String cep;
    private String email;
    private String senha;
    private LocalDate dt_nascimento;
    private String foto;
    private String cpf;
    private String nome;
    private char sexo;
    @Id
    private String cnh;

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDate getDt_nascimento() {
        return dt_nascimento;
    }

    public void setDt_nascimento(LocalDate dt_nascimento) {
        this.dt_nascimento = dt_nascimento;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public String getCnh() {
        return cnh;
    }

    public Transportadores(String cep, String email, String senha, LocalDate dt_nascimento, String foto, String cpf, String nome, char sexo, String cnh) {
        this.cep = cep;
        this.email = email;
        this.senha = senha;
        this.dt_nascimento = dt_nascimento;
        this.foto = foto;
        this.cpf = cpf;
        this.nome = nome;
        this.sexo = sexo;
        this.cnh = cnh;
    }

    public Transportadores() {
    }

    @Override
    public String toString() {
        return "Transportadores{" +
                "cep=" + cep +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", dt_nascimento=" + dt_nascimento +
                ", foto='" + foto + '\'' +
                ", cpf='" + cpf + '\'' +
                ", nome='" + nome + '\'' +
                ", sexo='" + sexo + '\'' +
                ", cnh=" + cnh +
                '}';
    }
}
