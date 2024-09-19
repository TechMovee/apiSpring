package com.techmoveeapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "enderecos")
public class Endereco {

    @Id
    private int id;
    private String cep;
    private String bairro;
    private String rua;
    private String numero;

    public Endereco(){

    }
    public Endereco(int id, String cep, String bairro, String rua, String numero) {
        this.id = id;
        this.cep = cep;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
    }
}
