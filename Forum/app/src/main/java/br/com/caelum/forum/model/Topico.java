package br.com.caelum.forum.model;

import java.io.Serializable;

public class Topico implements Serializable{

    private String titulo;
    private String descricao;
    private String autor;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return this.titulo + " - " + this.descricao;
    }
}
