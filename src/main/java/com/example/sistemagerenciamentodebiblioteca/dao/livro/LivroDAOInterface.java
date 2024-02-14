package com.example.sistemagerenciamentodebiblioteca.dao.livro;

import com.example.sistemagerenciamentodebiblioteca.dao.CRUD;
import com.example.sistemagerenciamentodebiblioteca.exceptions.LivroException;
import com.example.sistemagerenciamentodebiblioteca.model.Livro;

import java.util.ArrayList;
/** Essa interface contém métodos que correspondem as informações do livro, como:
 * Encontrar por ISBN;
 * Encontrar por autor;
 * Encontar por Categoria;
 * Encontrar por Título.
 * @author Laiza Araujo Gordiano Oliveira
 * @see CRUD
 * @see Livro
 */
public interface LivroDAOInterface extends CRUD<Livro, Exception> {
    /**
     * Esse método tem como objetivo encontrar e retornar uma lista de livros que têm um número ISBN específico.
     * @param isbn
     * @return
     * @throws Exception
     */
    public ArrayList<Livro> findISBN(String isbn) throws LivroException;

    /**
     * Esse método tem como objetivo encontrar e retornar uma lista de livros que têm um autor específico.
     * @param autor
     * @return
     * @throws Exception
     */
    public ArrayList<Livro> findAutor(String autor) throws LivroException;

    /**
     * Esse método tem como objetivo encontrar e retornar uma lista de livros que têm uma categoria específica.
     * @param categoria
     * @return
     * @throws Exception
     */
    public ArrayList<Livro> findCategoria(String categoria) throws LivroException;

    /**
     * Esse método tem como objetivo encontrar e retornar uma lista de livros que têm um título específico
     * @param titulo
     * @return
     * @throws Exception
     */
    public ArrayList<Livro> findTitulo(String titulo) throws LivroException;

}
