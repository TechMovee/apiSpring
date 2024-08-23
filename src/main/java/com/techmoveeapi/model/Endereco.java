package com.techmoveeapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "enderecos")
public class Endereco {

    @Id
    private int id;
    private String cep;
    private String bairro;
    private String rua;
    private int numCasa;

    public Endereco(){

    }
    public Endereco(int id, String cep, String bairro, String rua, int numCasa) {
        this.id = id;
        this.cep = cep;
        this.bairro = bairro;
        this.rua = rua;
        this.numCasa = numCasa;
    }

    public int getId() {
        return id;
    }

    public String getCep() {
        return cep;
    }
    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro(

    ) {
        return bairro;
    }
    public void setBairro(String bairro){
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }
    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumCasa() {
        return numCasa;
    }
    public void setNumCasa(int numCasa) {
        this.numCasa = numCasa;
    }

    @Override
    public String toString(){
        return "Endereco{" +
                "Id: "+id+
                "Cep: "+cep+
                "Bairro: "+bairro+
                "Rua: "+rua+
                "NumCasa: "+numCasa;
    }
}
