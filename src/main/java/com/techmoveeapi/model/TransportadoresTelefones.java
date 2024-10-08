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
@Table(name = "Transportadores_telefones")
public class TransportadoresTelefones {
    @Id
    private String transportador_cnh;
    private int telefone_id;

    public TransportadoresTelefones(int telefone_id, String transportador_cnh) {
        this.telefone_id = telefone_id;
        this.transportador_cnh = transportador_cnh;
    }

    public TransportadoresTelefones() {
    }
}
