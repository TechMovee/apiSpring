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


    private Integer escola_id;//
    private String turno;
    private String pcd;//
    private Integer id_foto;//
    private String responsavel_cpf;//
    @Id
    private String cpf;//



    public Aluno() {}
    public Aluno(String nome, LocalDate dt_nascimento, Integer escola_id, String turno, String pcd,  Integer foto, String cpf, String responsavel_cpf) {
        this.nome = nome;
        this.dt_nascimento = dt_nascimento;
        this.escola_id = escola_id;
        this.turno = turno;
        this.pcd = pcd;
        this.id_foto = foto;
        this.cpf = cpf;
        this.responsavel_cpf = responsavel_cpf;
    }

}
