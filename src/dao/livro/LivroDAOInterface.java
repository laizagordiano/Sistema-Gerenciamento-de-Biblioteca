package dao.livro;

import dao.CRUD;
import model.Livro;

public interface LivroDAOInterface extends CRUD<Livro, Exception> {
    public Livro findISBN(String isbn) throws Exception;
    public Livro findAutor(String autor) throws Exception;
    public Livro findCategoria(String categoria) throws Exception;
    public Livro findTitulo(String titulo) throws Exception;

}
