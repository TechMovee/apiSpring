package com.techmoveeapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
@ToString
@Getter
@Setter
@EqualsAndHashCode
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
    public Responsaveis() {
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
