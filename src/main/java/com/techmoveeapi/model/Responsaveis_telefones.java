package com.techmoveeapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "responsaveis_telefones")
public class Responsaveis_telefones {
    @Id
    private String responsavel_cpf;
    @Id
    private int telefone_id;

    public String getResponsavel_cpf() {
        return responsavel_cpf;
    }

    public int getTelefone_id() {
        return telefone_id;
    }

    @Override
    public String toString() {
        return "Responsaveis_telefones{" +
                "responsaveis_cpf='" + responsavel_cpf + '\'' +
                ", telefone_id=" + telefone_id +
                '}';
    }

    public Responsaveis_telefones(String responsavel_cpf, int telefone_id) {
        this.responsavel_cpf = responsavel_cpf;
        this.telefone_id = telefone_id;
    }

    public Responsaveis_telefones() {
    }
}
