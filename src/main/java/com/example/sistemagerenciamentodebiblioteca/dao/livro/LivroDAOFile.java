package com.example.sistemagerenciamentodebiblioteca.dao.livro;

import com.example.sistemagerenciamentodebiblioteca.armazenamento.FileMethods;
import com.example.sistemagerenciamentodebiblioteca.exceptions.LivroException;
import com.example.sistemagerenciamentodebiblioteca.model.Livro;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

/**
 * A classe implementa a interface LivroDAOInterface e fornece métodos
 * para realizar operações relacionadas a livros, como criação, exclusão, atualização
 * e consulta.
 * @author Laiza Araujo Gordiano Oliveira
 */
public class LivroDAOFile implements LivroDAOInterface {
    File arquivo;
    private static final String NAMEFILE= "livros";

    public LivroDAOFile(){
        arquivo = FileMethods.createFile(NAMEFILE);
    }


    private int getProximoID(ArrayList<Livro> livros){
        int cont = -1;
        for (Livro l : livros){
            cont++;
        }
        return (cont+1);
    }


    @Override
    public Livro create(Livro obj) {
        ArrayList<Livro> livros = findMany() ;
        obj.setId(this.getProximoID(livros));
        livros.add(obj);
        FileMethods.sobreescreverArquivo(arquivo, livros);
        return obj;
    }

    @Override
    public void delete(Livro obj) throws LivroException {
        ArrayList<Livro> listLivros = findMany();
        boolean deletou = listLivros.remove(obj);
        if (!deletou){
            throw new LivroException(LivroException.DELETAR,obj);
        }
        FileMethods.sobreescreverArquivo(arquivo, listLivros);
    }

    @Override
    public void deleteMany() {
        FileMethods.apagarConteudoArquivo(arquivo);

    }

    @Override
    public Livro update(Livro obj) throws LivroException {
        ArrayList<Livro> listLivros = findMany();
        int index = listLivros.indexOf(obj);
        if (index == -1){
            throw new LivroException(LivroException.ATUALIZAR,obj);
        }
        listLivros.set(index, obj);
        FileMethods.sobreescreverArquivo(arquivo,listLivros);
        return obj;
    }

    @Override
    public ArrayList<Livro> findMany() {
        return  FileMethods.consultarArquivoList(arquivo);
    }

    @Override
    public Livro findById(int id) throws LivroException {
        ArrayList<Livro> listLivros = findMany();
        for (Livro livros : listLivros) {
            if (Objects.equals(livros.getId(), id)) {
                return livros;
            }
        }
        throw new LivroException(LivroException.PROCURAR);
    }

    @Override
    public ArrayList<Livro> findISBN(String isbn) throws LivroException {
        ArrayList<Livro> listLivros = findMany();
        ArrayList<Livro> listISBN = new ArrayList<>();
        for (Livro livros : listLivros) {
            if (livros.getISBN().equals(isbn)) {
                listISBN.add(livros);
            }
        }
        if (listISBN.isEmpty()) {
            throw new LivroException(LivroException.SEM_ISBN);
        }
        return listISBN;
    }

    @Override
    public ArrayList<Livro> findAutor(String autor) throws LivroException {
        ArrayList<Livro> listLivros = findMany();
        ArrayList<Livro> listAutor = new ArrayList<>();
        for (Livro livros : listLivros) {
            if (livros.getAutor().equals(autor)) {
                listAutor.add(livros);
            }
        }
        if (listAutor.isEmpty()) {
            throw new LivroException(LivroException.SEM_AUTOR);
        }
        return listAutor;
    }

    @Override
    public ArrayList<Livro> findCategoria(String categoria) throws LivroException {
        ArrayList<Livro> listLivros = findMany();
        ArrayList<Livro> listCategoria = new ArrayList<>();
        for (Livro livros : listLivros) {
            if (livros.getCategoria().equals(categoria)) {
                listCategoria.add(livros);
            }
        }
        if (listCategoria.isEmpty()) {
            throw new LivroException(LivroException.SEM_CATEGORIA);
        }
        return listCategoria;
    }

    @Override
    public ArrayList<Livro> findTitulo(String titulo) throws LivroException {
        ArrayList<Livro> listLivros = findMany();
        ArrayList<Livro> listTitulos = new ArrayList<>();
        for (Livro livros : listLivros) {
            if (livros.getTitulo().equals(titulo)) {
                listTitulos.add(livros);
            }
        }
        if (listTitulos.isEmpty()) {
            throw new LivroException(LivroException.SEM_EXEMPLAR);
        }
        return listTitulos;
    }
}
