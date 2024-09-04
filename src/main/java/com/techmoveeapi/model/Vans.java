package com.techmoveeapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "vans")
public class Vans {
    private String modelo;
    private int ano;
    private int foto_id;
    @Id
    private String placa;
    private String transportador_cnh;

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getFoto_id() {
        return foto_id;
    }

    public void setFoto_id(int foto_id) {
        this.foto_id = foto_id;
    }

    public String getPlaca() {
        return placa;
    }

    public String getTransportador_cnh() {
        return transportador_cnh;
    }

    public void setTransportador_cnh(String transportador_cnh) {
        this.transportador_cnh = transportador_cnh;
    }

    @Override
    public String toString() {
        return "Vans{" +
                "modelo='" + modelo + '\'' +
                ", ano=" + ano +
                ", foto_id=" + foto_id +
                ", placa='" + placa + '\'' +
                ", transportador_cnh='" + transportador_cnh + '\'' +
                '}';
    }

    public Vans() {
    }

    public Vans(String modelo, int ano, int foto_id, String placa, String transportador_cnh) {
        this.modelo = modelo;
        this.ano = ano;
        this.foto_id = foto_id;
        this.placa = placa;
        this.transportador_cnh = transportador_cnh;
    }
}

