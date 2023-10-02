package dao.emprestimo;

import exceptions.EmprestimoException;
import model.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class EmprestimoDAO implements EmprestimoDAOInterface {
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
        obj.setId(this.getProximoID());
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

    @Override
    public Emprestimo findEmprestimo(Leitor leitor, Livro livro) {
        for (Emprestimo emprestimo : this.listEmprestimos) {
            if (emprestimo.getLeitor().equals(leitor) && emprestimo.getLivro().equals(livro)) {
                return emprestimo;
            }
        }
        return null;
    }

    @Override
    public List<Emprestimo> findByLeitor(Leitor leitor) {
        List<Emprestimo> listEmprestimoLeitor = new ArrayList<>();
        for (Emprestimo emprestimo : this.listEmprestimos) {
            if (emprestimo.getLeitor().equals(leitor)) {
                listEmprestimoLeitor.add(emprestimo);
            }
        }
        return listEmprestimoLeitor;
    }

    @Override
    public List<Emprestimo> findActiveUser(Leitor leitor) throws EmprestimoException {
        List<Emprestimo> emprestimoAtivoDoLeitor = new ArrayList<>();
        for (Emprestimo emprestimo : this.listEmprestimos) {
            if (emprestimo.getLeitor() == leitor && emprestimo.isSituacao()) {
                emprestimoAtivoDoLeitor.add(emprestimo);
            }
        }
        return emprestimoAtivoDoLeitor;
    }

    @Override
    public List<Emprestimo> findEmprestimoAtivo() throws EmprestimoException {
        List<Emprestimo> emprestimoAtivo = new ArrayList<>();
        for (Emprestimo emprestimo : this.listEmprestimos) {
            if (emprestimo.isSituacao()) {
                emprestimoAtivo.add(emprestimo);
            }
        }
        if (emprestimoAtivo.isEmpty()) {
            throw new EmprestimoException(EmprestimoException.ATIVOS);
        }
        return emprestimoAtivo;

    }
    @Override
    public ArrayList<Livro> livrosMaisPolulares(){
        ArrayList<Livro> livrosMaisPopulares = new ArrayList<>();
        int maiorQuantidade = 0;
        for (Emprestimo emprestimos : listEmprestimos) {
            int quantidade = contarElemento(listEmprestimos, emprestimos.getLivro());
            if (quantidade > maiorQuantidade) {
                maiorQuantidade = quantidade;
                livrosMaisPopulares.clear();
                livrosMaisPopulares.add(emprestimos.getLivro());
            } else if (quantidade == maiorQuantidade && !livrosMaisPopulares.contains(emprestimos.getLivro())) {
                livrosMaisPopulares.add(emprestimos.getLivro());
            }
        }
        return livrosMaisPopulares;
    }
    private static int contarElemento(ArrayList<Emprestimo> list, Livro livro) {
        int contagem = 0;
        for (Emprestimo emprestimos : list) {
            if (emprestimos.getLivro().equals(livro)) {
                contagem++;
            }
        }
        return contagem;
    }

    @Override
    public boolean validaFimDaMulta(Leitor leitor, String dataAtual) {
        DateTimeFormatter dataFormatada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate newDate = LocalDate.parse(dataAtual, dataFormatada);
        if (leitor.getFimDaMulta() == null) {
            return true;
        } else return newDate.isAfter(leitor.getFimDaMulta());
    }

    @Override
    public int numLivrosEmprestados() throws EmprestimoException {
        return findEmprestimoAtivo().size();
    }

    @Override
    public int numLivrosAtrasados() throws EmprestimoException {
        int numDeAtraso = 0;
        LocalDate dataAtual = LocalDate.now();
        for (Emprestimo emprestimo : listEmprestimos) {
            if (emprestimo.isSituacao() && dataAtual.isAfter(emprestimo.getDataDevolucao())) {
                numDeAtraso++;
            }
        }
        if (numDeAtraso == 0) {
            throw new EmprestimoException(EmprestimoException.SEM_ATRASADOS);
        }
        return numDeAtraso;
    }

    public Boolean verificaAtrasoDoLeitor(Leitor leitor) {
        LocalDate dataHoje = LocalDate.now();
        for (Emprestimo emprestimo : listEmprestimos) {
            if (emprestimo.getLeitor() == leitor
                    && dataHoje.isAfter(emprestimo.getDataDevolucao())
                    && emprestimo.isSituacao()) {
                return true;
            }
        }
        return false; // indica que o leitor não tem atrasos
    }


}
