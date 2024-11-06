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
@Table(name = "responsaveis")
public class Responsaveis {
    @Id
    @Column(length = 14)
    private String cpf;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(name = "dt_nascimento", nullable = false)
    private LocalDate dt_nascimento;

    @Column(nullable = false, length = 100, unique = true)
    private String email;

    @Column(nullable = false, length = 100)
    private String senha;

    @Column(nullable = false, length = 100)
    private Integer foto_id;

    @Column(nullable = false, length = 100)
    private Integer endereco_id;

    @Column(nullable = false, length = 100)
    private Integer telefone_id;

    public Responsaveis() {
    }
    public Responsaveis(LocalDate dt_nascimento, String email, String cpf, Integer foto_id, String senha, String nome, Integer endereco_id, Integer telefone_id) {
        this.dt_nascimento = dt_nascimento;
        this.email = email;
        this.cpf = cpf;
        this.foto_id = foto_id;
        this.senha = senha;
        this.nome = nome;
        this.endereco_id = endereco_id;
        this.telefone_id = telefone_id;
    }
}
