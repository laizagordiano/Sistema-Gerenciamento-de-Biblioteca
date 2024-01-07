package dao.bibliotecario;

import exceptions.BibliotecarioException;
import model.Administrador;
import model.Bibliotecario;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A classe BibliotecarioDAO implementa a interface BibliotecarioDAOInterface e fornece métodos
 * para realizar operações relacionadas a bibliotecários, como criação, exclusão, atualização
 * e consulta.
 * @author Laiza Araujo Gordiano Oliveira
 */
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
        obj.setNumeroID(this.getProximoID());
        this.listBibliotecarios.add(obj);
        return obj;
    }

    @Override
    public void delete(Bibliotecario obj) throws BibliotecarioException {
        boolean deletou = this.listBibliotecarios.remove(obj);
        if (!deletou){
            throw new BibliotecarioException(BibliotecarioException.DELETAR);
        }
    }

    @Override
    public void deleteMany() {
        this.listBibliotecarios = new ArrayList<>();
        this.proximoID = 0;
    }

    @Override
    public Bibliotecario update(Bibliotecario obj) throws BibliotecarioException {
        int index = this.listBibliotecarios.indexOf(obj);
        if (index == -1){
            throw new BibliotecarioException(BibliotecarioException.ATUALIZAR);
        }
        this.listBibliotecarios.set(index, obj);
        return obj;
    }

    @Override
    public ArrayList<Bibliotecario> findMany() {
        return this.listBibliotecarios;
    }

    @Override
    public Bibliotecario findById(int id) throws BibliotecarioException {
        for (Bibliotecario bibliotecario : listBibliotecarios) {
            if (Objects.equals(bibliotecario.getNumeroID(),id)) {
                return bibliotecario;
            }
        }
        throw new BibliotecarioException(BibliotecarioException.PROCURAR);
    }
}