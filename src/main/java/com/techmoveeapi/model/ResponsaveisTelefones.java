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
@Table(name = "responsaveis_telefones")
public class ResponsaveisTelefones {
    @Id
    private String responsavel_cpf;
    @Id
    private int telefone_id;
    public ResponsaveisTelefones(String responsavel_cpf, int telefone_id) {
        this.responsavel_cpf = responsavel_cpf;
        this.telefone_id = telefone_id;
    }

    public ResponsaveisTelefones() {
    }
}
