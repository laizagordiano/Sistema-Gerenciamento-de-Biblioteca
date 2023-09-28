package dao.livro;

import model.Administrador;
import model.Livro;

import java.util.ArrayList;

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
        this.listLivros.add(obj);
        return obj;
    }

    @Override
    public void delete(Livro obj) throws Exception {
        this.listLivros.remove(obj);
    }


    @Override
    public void deleteMany() {
        this.listLivros = new ArrayList<>();
        this.proximoID = 0;
    }

    @Override
    public Livro update(Livro obj) throws Exception {
        int index = this.listLivros.indexOf(obj);
        this.listLivros.set(index, obj);
        return obj;
    }

    @Override
    public ArrayList<Livro> findMany() {
        return this.listLivros;
    }


    @Override
    public Livro findById(int id) throws Exception {
        for (Livro livros : this.listLivros) {
            if (livros.getId() == id) {
                return livros;
            }
        }
        return null;
    }
}
