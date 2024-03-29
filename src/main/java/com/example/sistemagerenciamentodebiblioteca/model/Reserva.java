package com.example.sistemagerenciamentodebiblioteca.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Essa classe é responsável pelas iformações passadas na criação das reservas.
 * @author Laiza Araujo Gordiano Oliveira
 * @see Objects
 */
public class Reserva implements Serializable {
    private Leitor leitor;
    private Livro livro;
    private int id;

    public Reserva(Leitor leitor, Livro livro ){
        this.setLeitor(leitor);
        this.setLivro(livro);
        livro.setReserva(true);
    }

    public Leitor getLeitor() {
        return leitor;
    }

    public void setLeitor(Leitor leitor) {
        this.leitor = leitor;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reserva reserva = (Reserva) o;
        return id == reserva.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "leitor=" + leitor +
                ", livro=" + livro +
                '}';
    }
}
