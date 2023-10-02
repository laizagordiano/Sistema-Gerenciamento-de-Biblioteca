package dao.emprestimo;

import dao.CRUD;
import exceptions.EmprestimoException;
import model.Emprestimo;
import model.Leitor;
import model.Livro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface EmprestimoDAOInterface extends CRUD<Emprestimo, Exception> {
    public Emprestimo findEmprestimo(Leitor leitor, Livro livro);
    public List<Emprestimo> findByLeitor(Leitor leitor);
    public List<Emprestimo> findActiveUser(Leitor leitor) throws EmprestimoException;
    public List<Emprestimo> findEmprestimoAtivo() throws EmprestimoException;

    ArrayList<Livro> livrosMaisPolulares();


    public boolean validaFimDaMulta(Leitor leitor, String dataAtual);

    public int numLivrosEmprestados() throws EmprestimoException;
    public int numLivrosAtrasados() throws EmprestimoException;

}
