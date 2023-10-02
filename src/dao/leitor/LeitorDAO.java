package dao.leitor;

import exceptions.LeitorException;
import model.Leitor;

import java.util.ArrayList;
import java.util.Objects;

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
        obj.setNumeroID(this.getProximoID());
        this.listLeitor.add(obj);
        return obj;
    }

    @Override
    public void delete(Leitor obj) throws LeitorException {
        boolean deletou = this.listLeitor.remove(obj);
        if (!deletou){
            throw new LeitorException(LeitorException.DELETAR);
        }
    }

    @Override
    public void deleteMany() {
        this.listLeitor = new ArrayList<>();
        this.proximoID = 0;

    }

    @Override
    public Leitor update(Leitor obj) throws LeitorException {
        int index = this.listLeitor.indexOf(obj);
        if (index == -1){
            throw new LeitorException(LeitorException.ATUALIZAR);
        }
        this.listLeitor.set(index, obj);
        return obj;
    }

    @Override
    public ArrayList<Leitor> findMany() {
        return this.listLeitor;
    }

    @Override
    public Leitor findById(int id) throws LeitorException {
        for (Leitor leitor : listLeitor) {
            if (Objects.equals(leitor.getNumeroID(), id)) {
                return leitor;
            }
        }
        throw new LeitorException(LeitorException.PROCURAR);
    }

}

