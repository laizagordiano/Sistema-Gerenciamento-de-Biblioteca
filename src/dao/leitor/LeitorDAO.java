package dao.leitor;

import model.Leitor;

import java.util.ArrayList;

public class LeitorDAO implements LeitorDAOInterface{
    private ArrayList<Leitor> listLeitor;
    private int proximoID;
    public LeitorDAO() {
        this.listLeitor = new ArrayList<>();
        this.proximoID = 0;
    }
    private int getProximoID() {
        return this.proximoID++;
    }
    @Override
    public Leitor create(Leitor obj) {
        this.listLeitor.add(obj);
        return obj;
    }

    @Override
    public void delete(Leitor obj) throws Exception {
        this.listLeitor.remove(obj);
    }

    @Override
    public void deleteMany() {
        this.listLeitor = new ArrayList<>();
        this.proximoID = 0;

    }

    @Override
    public Leitor update(Leitor obj) throws Exception {
        int index = this.listLeitor.indexOf(obj);
        this.listLeitor.set(index, obj);
        return obj;
    }

    @Override
    public ArrayList<Leitor> findMany() {
        return this.listLeitor;
    }

    @Override
    public Leitor findById(int id) throws Exception {
        for (Leitor leitor : this.listLeitor) {
            if (leitor.getNumeroID() == id) {
                return leitor;
            }
        }
        return null;
    }
}

