package dao.bibliotecario;

import model.Administrador;
import model.Bibliotecario;

import java.util.ArrayList;

public class BibliotecarioDAO implements BibliotecarioDAOInterface{
    private ArrayList<Bibliotecario> listBibliotecarios;
    private int proximoID;
    public BibliotecarioDAO() {
        this.listBibliotecarios = new ArrayList<>();
        this.proximoID = 0;
    }
    private int getProximoID() {
        return this.proximoID++;
    }
    @Override
    public Bibliotecario create(Bibliotecario obj) {
        this.listBibliotecarios.add(obj);
        return obj;
    }

    @Override
    public void delete(Bibliotecario obj) throws Exception {
        this.listBibliotecarios.remove(obj);
    }

    @Override
    public void deleteMany() {
        this.listBibliotecarios = new ArrayList<>();
        this.proximoID = 0;
    }

    @Override
    public Bibliotecario update(Bibliotecario obj) throws Exception {
        int index = this.listBibliotecarios.indexOf(obj);
        this.listBibliotecarios.set(index, obj);
        return obj;
    }

    @Override
    public ArrayList<Bibliotecario> findMany() {
        return this.listBibliotecarios;
    }

    @Override
    public Bibliotecario findById(int id) throws Exception {
        for (Bibliotecario bibliotecario : this.listBibliotecarios) {
            if (bibliotecario.getNumeroID() == id) {
                return bibliotecario;
            }
        }
        return null;
    }
}