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
@Table(name = "transportadores")
public class Transportadores {
    private String cep;
    private String email;
    private String senha;
    private LocalDate dt_nascimento;
    private int foto_id;
    private String cpf;
    private String nome;
    @Id
    private String cnh;

    public Transportadores(String cep, String email, String senha, LocalDate dt_nascimento, int foto_id, String cpf, String nome, String cnh) {
        this.cep = cep;
        this.email = email;
        this.senha = senha;
        this.dt_nascimento = dt_nascimento;
        this.foto_id = foto_id;
        this.cpf = cpf;
        this.nome = nome;
        this.cnh = cnh;
    }

    public Transportadores() {
    }

}
