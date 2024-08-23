package com.techmoveeapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "conversas")
public class Conversas {

    @Id
    private int id;

    public Conversas(int id) {
        this.id = id;
    }

    public Conversas() {
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString(){
        return "Conversas{ " +
                "Id: "+id;
    }
}
