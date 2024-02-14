package com.example.sistemagerenciamentodebiblioteca.dao;

import java.util.ArrayList;

public interface CRUD<T, E extends Exception> {

    /**
     * Cria novo objeto
     */
    public T create(T obj);

    /**
     * Deleta um objeto
     */
    public void delete(T obj) throws E;

    /**
     * Detela todos os dados
     */
    public void deleteMany();

    /**
     * Atualiza um objeto
     */
    public T update(T obj) throws E;

    /**
     * Ler toda a lista de dados
     */
    public ArrayList<T> findMany();

    /**
     * Encontra um objeto específico pelo id
     */
    public T findById(int id) throws E;

}
