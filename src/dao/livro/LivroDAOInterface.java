package dao.livro;

import dao.CRUD;
import model.Livro;

import java.util.ArrayList;

public interface LivroDAOInterface extends CRUD<Livro, Exception> {
    public ArrayList<Livro> findISBN(String isbn) throws Exception;
    public ArrayList<Livro> findAutor(String autor) throws Exception;
    public ArrayList<Livro> findCategoria(String categoria) throws Exception;
    public ArrayList<Livro> findTitulo(String titulo) throws Exception;

}
