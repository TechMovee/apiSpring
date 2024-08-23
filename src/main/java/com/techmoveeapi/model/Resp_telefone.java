package com.techmoveeapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "resp_telefone")
public class Resp_telefone {
    @Id
    private int id;
    private String num;

    public int getId() {
        return id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public Resp_telefone() {
    }

    @Override
    public String toString() {
        return "Resp_telefone{" +
                "id=" + id +
                ", num='" + num + '\'' +
                '}';
    }

    public Resp_telefone(int id, String num) {
        this.id = id;
        this.num = num;
    }
}
