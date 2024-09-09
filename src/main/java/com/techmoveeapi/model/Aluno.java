package com.techmoveeapi.model;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;

@Entity
@Table(name = "aluno")
public class Aluno {
    private String nome;
    private char sexo;
    private int idade;
    private String escola;
    private String turno;
    private boolean pcd;
    private  String foto;

    @Id
    private String cpf;

    public Aluno() {}
    public Aluno(String nome, char sexo, int idade, String escola, String turno, boolean pcd,  String foto, String cpf) {
        this.nome = nome;
        this.sexo = sexo;
        this.idade = idade;
        this.escola = escola;
        this.turno = turno;
        this.pcd = pcd;
        this.foto = foto;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public char getSexo(){
        return this.sexo;
    }
    public void setSexo(char sexo){
        this.sexo = sexo;
    }

    public int getIdade() {
        return idade;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEscola() {
        return escola;
    }
    public void setEscola(String escola) {
        this.escola = escola;
    }

    public String getTurno() {
        return turno;
    }
    public void setTurno(String turno) {
        this.turno = turno;
    }

    public boolean isPcd() {
        return pcd;
    }
    public void setPcd(boolean pcd) {
        this.pcd = pcd;
    }

    public String getFoto() {
        return foto;
    }
    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getCpf(){
        return cpf;
    }
    public void setCpf(String cpf){

        this.cpf = cpf;
    }

    //Metodo toString
    @Override
    public String toString() {
        return "Aluno{"+
                "nome: "+nome+
                "sexo: "+sexo+
                "idade: "+idade+
                "escola: "+escola+
                "turno: "+turno+
                "pcd: "+pcd+
                "foto: "+foto+
                "cpf: "+cpf;
    }



}