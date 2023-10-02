package dao.livro;

import exceptions.LivroException;
import model.Livro;

import java.util.ArrayList;
import java.util.Objects;

public class LivroDAO implements LivroDAOInterface {
    private ArrayList<Livro> listLivros;
    private int proximoID;

    public LivroDAO() {
        this.listLivros = new ArrayList<>();
        this.proximoID = 0;
    }

    private int getProximoID() {
        return this.proximoID++;
    }

    @Override
    public Livro create(Livro obj) {
        obj.setId(this.getProximoID());
        this.listLivros.add(obj);
        return obj;
    }

    @Override
    public void delete(Livro obj) throws LivroException {
        boolean deletou = this.listLivros.remove(obj);
        if(!deletou){
            throw new LivroException(LivroException.DELETAR);
        }
    }


    @Override
    public void deleteMany() {
        this.listLivros = new ArrayList<>();
        this.proximoID = 0;
    }

    @Override
    public Livro update(Livro obj) throws LivroException {
        int index = this.listLivros.indexOf(obj);
        if (index == -1){
            throw new LivroException(LivroException.ATUALIZAR);
        }
        this.listLivros.set(index, obj);
        return obj;
    }

    @Override
    public ArrayList<Livro> findMany() {
        return this.listLivros;
    }


    @Override
    public Livro findById(int id) throws LivroException {
        for (Livro livros : listLivros) {
            if (Objects.equals(livros.getId(), id)) {
                return livros;
            }
        }
        throw new LivroException(LivroException.PROCURAR);

    }
    @Override
    public ArrayList<Livro> findISBN(String isbn) throws LivroException {
        ArrayList<Livro> listISBN = new ArrayList<>();
        for (Livro livros : this.listLivros) {
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
        ArrayList<Livro> listAutor = new ArrayList<>();
        for (Livro livros : this.listLivros) {
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
        ArrayList<Livro> listCategoria = new ArrayList<>();
        for (Livro livros : this.listLivros) {
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
        ArrayList<Livro> listTitulos = new ArrayList<>();
        for (Livro livros : this.listLivros) {
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

