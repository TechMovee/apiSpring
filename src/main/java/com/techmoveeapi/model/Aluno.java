package com.techmoveeapi.model;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Min;
import lombok.*;

@ToString
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "alunos")
public class Aluno {
    private String nome;
    private char sexo;

    @Min(value = 0 , message = "A quantidade deve ser pelo menos 0")
    private int idade;

    private String escola;
    private String turno;
    private String pcd;
    private String foto;
    private String responsavel_cpf;

    @Id
    private String cpf;

    public Aluno() {}
    public Aluno(String nome, char sexo, int idade, String escola, String turno, String pcd,  String foto, String cpf, String responsavel_cpf) {
        this.nome = nome;
        this.sexo = sexo;
        this.idade = idade;
        this.escola = escola;
        this.turno = turno;
        this.pcd = pcd;
        this.foto = foto;
        this.cpf = cpf;
        this.responsavel_cpf = responsavel_cpf;
    }

}
