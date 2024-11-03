package com.techmoveeapi.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "escolas")
public class Escola {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    @ManyToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco_id;

    public Escola() {}
    public Escola(Integer id, String nome, Endereco endereco_id) {
        this.id = id;
        this.nome = nome;
        this.endereco_id = endereco_id;
    }

}
