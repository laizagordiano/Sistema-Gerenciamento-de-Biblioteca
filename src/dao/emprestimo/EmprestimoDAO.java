package dao.emprestimo;

import model.Bibliotecario;
import model.Emprestimo;

import java.util.ArrayList;

public class EmprestimoDAO implements EmprestimoDAOInterface{
    private ArrayList<Emprestimo> listEmprestimos;
    private int proximoID;
    public EmprestimoDAO() {
        this.listEmprestimos = new ArrayList<>();
        this.proximoID = 0;
    }
    private int getProximoID() {
        return this.proximoID++;
    }
    @Override
    public Emprestimo create(Emprestimo obj) {
        this.listEmprestimos.add(obj);
        return obj;
    }

    @Override
    public void delete(Emprestimo obj) throws Exception {
        this.listEmprestimos.remove(obj);
    }

    @Override
    public void deleteMany() {
        this.listEmprestimos = new ArrayList<>();
        this.proximoID = 0;

    }

    @Override
    public Emprestimo update(Emprestimo obj) throws Exception {
        int index = this.listEmprestimos.indexOf(obj);
        this.listEmprestimos.set(index, obj);
        return obj;
    }

    @Override
    public ArrayList<Emprestimo> findMany() {
        return this.listEmprestimos;
    }

    @Override
    public Emprestimo findById(int id) throws Exception {
        for (Emprestimo emprestimo : this.listEmprestimos) {
            if (emprestimo.getId() == id) {
                return emprestimo;
            }
        }
        return null;
    }
}
