package com.example.sistemagerenciamentodebiblioteca.model;
/**
 * Essa classe é extendida da classe usuário e é responsavél pelas informações na criação do bibliotecario.
 * @author Laiza Araujo Gordiano Oliveira
 */
public class Bibliotecario extends Usuario{
    private String cargo;
    public Bibliotecario(String nome, String senha, String cargo) {
        super(nome, senha,-1);
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = "Bibliotecario";
    }
}
