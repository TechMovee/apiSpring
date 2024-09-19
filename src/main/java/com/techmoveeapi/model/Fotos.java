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


}
