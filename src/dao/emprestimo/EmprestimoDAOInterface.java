package dao.emprestimo;

import dao.CRUD;
import model.Emprestimo;
import model.Leitor;
import model.Livro;

import java.util.List;

public interface EmprestimoDAOInterface extends CRUD<Emprestimo, Exception> {
    public Emprestimo findEmprestimo(Leitor leitor, Livro livro);
    public List<Emprestimo> findByLeitor(Leitor leitor);
}
