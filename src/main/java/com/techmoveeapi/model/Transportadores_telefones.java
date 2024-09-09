package com.techmoveeapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Transportadores_telefones")
public class Transportadores_telefones {
    @Id
    private String transportador_cnh;
    private int telefone_id;

    public int getTelefone_id() {
        return telefone_id;
    }

    public void setTelefone_id(int num) {
        this.telefone_id = telefone_id;
    }

    public String getTransportador_cnh() {
        return transportador_cnh;
    }

    public Transportadores_telefones(int telefone_id, String transportador_cnh) {
        this.telefone_id = telefone_id;
        this.transportador_cnh = transportador_cnh;
    }

    public Transportadores_telefones() {
    }

    @Override
    public String toString() {
        return "Transportadores_telefones{" +
                "id=" + telefone_id +
                ", transportadores cnh='" + transportador_cnh + '\'' +
                '}';
    }
}
