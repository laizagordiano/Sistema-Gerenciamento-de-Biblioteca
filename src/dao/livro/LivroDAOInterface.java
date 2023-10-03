package dao.livro;

import dao.CRUD;
import model.Livro;

import java.util.ArrayList;
/** Essa interface contém métodos que correspondem as informações do livro, como:
 * Encontrar por ISBN;
 * Encontrar por autor;
 * Encontar por Categoria;
 * Encontrar por Título.
 * @author Laiza Araujo Gordiano Oliveira
 * @see dao.CRUD
 * @see model.Livro
 */
public interface LivroDAOInterface extends CRUD<Livro, Exception> {
    /**
     * Esse método tem como objetivo encontrar e retornar uma lista de livros que têm um número ISBN específico.
     * @param isbn
     * @return
     * @throws Exception
     */
    public ArrayList<Livro> findISBN(String isbn) throws Exception;

    /**
     * Esse método tem como objetivo encontrar e retornar uma lista de livros que têm um autor específico.
     * @param autor
     * @return
     * @throws Exception
     */
    public ArrayList<Livro> findAutor(String autor) throws Exception;

    /**
     * Esse método tem como objetivo encontrar e retornar uma lista de livros que têm uma categoria específica.
     * @param categoria
     * @return
     * @throws Exception
     */
    public ArrayList<Livro> findCategoria(String categoria) throws Exception;

    /**
     * Esse método tem como objetivo encontrar e retornar uma lista de livros que têm um título específico
     * @param titulo
     * @return
     * @throws Exception
     */
    public ArrayList<Livro> findTitulo(String titulo) throws Exception;

}
