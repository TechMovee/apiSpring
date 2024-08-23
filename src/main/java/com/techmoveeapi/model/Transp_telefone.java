package com.techmoveeapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "num")
public class Transp_telefone {
    @Id
    private int id;
    private String num;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public int getId() {
        return id;
    }

    public Transp_telefone(int id, String num) {
        this.id = id;
        this.num = num;
    }

    public Transp_telefone() {
    }

    @Override
    public String toString() {
        return "Transp_telefone{" +
                "id=" + id +
                ", num='" + num + '\'' +
                '}';
    }
}
