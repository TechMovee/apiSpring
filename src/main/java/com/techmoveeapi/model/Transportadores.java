package com.techmoveeapi.model;

import jakarta.persistence.*;
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
    private String email;//
    private String senha;//
    private LocalDate dt_nascimento;//
    private Integer foto_id;//
    private String cpf;//
    private String nome;//
    @Id
    private String cnh;

    private Integer telefone_id;//

    public Transportadores(String email, String senha, LocalDate dt_nascimento, Integer foto_id, String cpf, String nome, String cnh, Integer telefone_id) {
        this.telefone_id = telefone_id;
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
