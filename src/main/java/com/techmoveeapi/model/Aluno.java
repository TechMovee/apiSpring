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

    @Min(value = 0 , message = "A quantidade deve ser pelo menos 0")
    private int idade;

    private String escola;
    private String turno;
    private String pcd;
    private int id_foto;
    private String responsavel_cpf;
    private String serie;
    private int endereco_id;
    @Id
    private String cpf;



    public Aluno() {}
    public Aluno(String nome, int idade, String escola, String turno, String pcd,  int foto, String cpf, String responsavel_cpf, String serie, int endereco_id) {
        this.nome = nome;
        this.idade = idade;
        this.escola = escola;
        this.turno = turno;
        this.pcd = pcd;
        this.id_foto = foto;
        this.cpf = cpf;
        this.responsavel_cpf = responsavel_cpf;
        this.serie = serie;
        this.endereco_id = endereco_id;
    }

}
