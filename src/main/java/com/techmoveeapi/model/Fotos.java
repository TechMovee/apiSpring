package com.techmoveeapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "fotos")
public class Fotos {
    @Id
    private int id;
    private String url;

    public Fotos() {}
    public Fotos(int id, String url) {
        this.id = id;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Fotos{"+
                "Id: "+id+
                "Url: "+url;
    }
}
