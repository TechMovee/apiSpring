package com.techmoveeapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.time.LocalDate;

@ToString
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "alunos")
public class Aluno {
    private String nome;//

    @Min(value = 0 , message = "A quantidade deve ser pelo menos 0")
    private LocalDate dt_nascimento;//

    @ManyToOne
    @JoinColumn(name = "escola_id")
    private Escola escola_id;//
    private String turno;
    private String pcd;//
    private int id_foto;//
    private String responsavel_cpf;//
    private String serie;
    private int endereco_id;//
    @Id
    private String cpf;//



    public Aluno() {}
    public Aluno(String nome, LocalDate dt_nascimento, Escola escola_id, String turno, String pcd,  int foto, String cpf, String responsavel_cpf, String serie, int endereco_id) {
        this.nome = nome;
        this.dt_nascimento = dt_nascimento;
        this.escola_id = escola_id;
        this.turno = turno;
        this.pcd = pcd;
        this.id_foto = foto;
        this.cpf = cpf;
        this.responsavel_cpf = responsavel_cpf;
        this.serie = serie;
        this.endereco_id = endereco_id;
    }

}
