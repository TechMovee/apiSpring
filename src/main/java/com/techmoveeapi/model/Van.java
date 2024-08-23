package com.techmoveeapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Van")
public class Van {
    private int modelo;
    private int ano;
    private int foto;
    @Id
    private int placa;
    private int transp_id;

    public int getModelo() {
        return modelo;
    }

    public void setModelo(int modelo) {
        this.modelo = modelo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public int getPlaca() {
        return placa;
    }

    public void setPlaca(int placa) {
        this.placa = placa;
    }

    public int getTransp_id() {
        return transp_id;
    }

    @Override
    public String toString() {
        return "Van{" +
                "modelo=" + modelo +
                ", ano=" + ano +
                ", foto=" + foto +
                ", placa=" + placa +
                ", transp_id=" + transp_id +
                '}';
    }

    public Van() {
    }

    public Van (int modelo, int ano, int foto, int placa, int transp_id) {
        this.modelo = modelo;
        this.ano = ano;
        this.foto = foto;
        this.placa = placa;
        this.transp_id = transp_id;
    }
}

