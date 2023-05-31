package io.github.charlesanjos.listatarefas.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.github.charlesanjos.listatarefas.enums.Categoria;

public class Afazer implements Serializable {
    private int id;
    private String titulo;
    private LocalDateTime dataCriado;
    private Categoria categoria;
    private boolean isFavorito;
    private boolean isCompleto;
    private LocalDateTime dataCompleto;

    public Afazer() {}

    public Afazer(int id, String titulo, LocalDateTime dataCriado, Categoria categoria, boolean isFavorito, boolean isCompleto, LocalDateTime dataCompleto) {
        this.id = id;
        this.titulo = titulo;
        this.dataCriado = dataCriado;
        this.categoria = categoria;
        this.isFavorito = isFavorito;
        this.isCompleto = isCompleto;
        this.dataCompleto = dataCompleto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDateTime getDataCriado() {
        return dataCriado;
    }

    public void setDataCriado(LocalDateTime dataCriado) {
        this.dataCriado = dataCriado;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public boolean isFavorito() {
        return isFavorito;
    }

    public void setFavorito(boolean favorito) {
        isFavorito = favorito;
    }

    public boolean isCompleto() {
        return isCompleto;
    }

    public void setCompleto(boolean completo) {
        isCompleto = completo;
    }

    public LocalDateTime getDataCompleto() {
        return dataCompleto;
    }

    public void setDataCompleto(LocalDateTime dataCompleto) {
        this.dataCompleto = dataCompleto;
    }
}
