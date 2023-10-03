package model;

import java.util.Objects;

/**
 * Essa classe é responsável por extender atributos para o Administrador, Bibliotecario e Leitor;
 * @author Laiza Araujo Gordiano Oliveira
 * @see java.util.Objects
 */
public class Usuario {
    private String nome;
    private int numeroID;
    private String senha;

    public Usuario(String nome, String senha, int numeroID) {
        this.nome = nome;
        this.senha = senha;
        this.numeroID = numeroID;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNumeroID() {
        return numeroID;
    }

    public void setNumeroID(int numeroID) {
        this.numeroID = numeroID;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return numeroID == usuario.numeroID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroID);
    }
}

