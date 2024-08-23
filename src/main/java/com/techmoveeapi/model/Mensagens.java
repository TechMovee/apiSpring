package com.techmoveeapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "mensagens")
public class Mensagens {
    private LocalDate dt_envio;
    private int conversar_id;
    private int remetente_id;
    private String conteudo;
    @Id
    private int id;

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public LocalDate getDt_envio() {
        return dt_envio;
    }

    public int getConversar_id() {
        return conversar_id;
    }

    public int getRemetente_id() {
        return remetente_id;
    }

    public String getConteudo() {
        return conteudo;
    }

    public int getId() {
        return id;
    }

    public Mensagens() {
    }

    @Override
    public String toString() {
        return "Mensagens{" +
                "dt_envio=" + dt_envio +
                ", conversar_id=" + conversar_id +
                ", remetente_id=" + remetente_id +
                ", conteudo='" + conteudo + '\'' +
                ", id=" + id +
                '}';
    }

    public Mensagens(LocalDate dt_envio, int conversar_id, int remetente_id, String conteudo, int id) {
        this.dt_envio = dt_envio;
        this.conversar_id = conversar_id;
        this.remetente_id = remetente_id;
        this.conteudo = conteudo;
        this.id = id;
    }
}
