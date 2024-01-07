package dao.emprestimo;

import armazenamento.FileMethods;
import exceptions.EmprestimoException;
import model.Emprestimo;
import model.Leitor;
import model.Livro;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A classe implementa a interface EmprestimoDAOInterface e fornece métodos
 * para realizar operações relacionadas a empréstimos, como criação, exclusão, atualização
 * e consulta.
 * @author Laiza Araujo Gordiano Oliveira
 */

public class EmprestimoDAOFile implements EmprestimoDAOInterface{
    File arquivo;
    private static final String NAMEFILE= "emprestimos";

    public EmprestimoDAOFile(){
        arquivo = FileMethods.createFile(NAMEFILE);
    }
    @Override
    public Emprestimo create(Emprestimo obj) {
        ArrayList<Emprestimo> emprestimos = findMany() ;
        obj.setId(this.getProximoID(emprestimos));
        emprestimos.add(obj);
        FileMethods.sobreescreverArquivo(arquivo, emprestimos);
        return obj;
    }

    @Override
    public void delete(Emprestimo obj) throws EmprestimoException {
        ArrayList<Emprestimo> listEmprestimo = findMany();
        boolean deletou = listEmprestimo.remove(obj);
        if (!deletou){
            throw new EmprestimoException(EmprestimoException.DELETAR,obj);
        }
        FileMethods.sobreescreverArquivo(arquivo, listEmprestimo);
    }

    @Override
    public void deleteMany() {
        FileMethods.apagarConteudoArquivo(arquivo);
    }

    @Override
    public Emprestimo update(Emprestimo obj) throws EmprestimoException {
        ArrayList<Emprestimo> listEmprestimos = findMany();
        int index = listEmprestimos.indexOf(obj);
        if (index == -1){
            throw new EmprestimoException(EmprestimoException.ATUALIZAR,obj);
        }
        listEmprestimos.set(index, obj);
        FileMethods.sobreescreverArquivo(arquivo,listEmprestimos);
        return obj;
    }

    @Override
    public ArrayList<Emprestimo> findMany() {
        return  FileMethods.consultarArquivoList(arquivo);
    }

    @Override
    public Emprestimo findById(int id) throws Exception {
        ArrayList<Emprestimo> listEmprestimo = findMany();
        for (Emprestimo emprestimo: listEmprestimo){
            if (Objects.equals(emprestimo.getId(), id)){
                return emprestimo;
            }
        }
        throw new EmprestimoException(EmprestimoException.PROCURAR);
    }

    @Override
    public Emprestimo findEmprestimo(Leitor leitor, Livro livro) {
        ArrayList<Emprestimo> listEmprestimos = findMany();
        for (Emprestimo emprestimo : listEmprestimos) {
            if (emprestimo.getLeitor().equals(leitor) && emprestimo.getLivro().equals(livro)) {
                return emprestimo;
            }
        }
        return null;
    }

    @Override
    public List<Emprestimo> findByLeitor(Leitor leitor) {
        ArrayList<Emprestimo> listEmprestimos = findMany();
        List<Emprestimo> listEmprestimoLeitor = new ArrayList<>();
        for (Emprestimo emprestimo : listEmprestimos) {
            if (emprestimo.getLeitor().equals(leitor)) {
                listEmprestimoLeitor.add(emprestimo);
            }
        }
        return listEmprestimoLeitor;
    }

    @Override
    public List<Emprestimo> findActiveUser(Leitor leitor) throws EmprestimoException {
        ArrayList<Emprestimo> listEmprestimos = findMany();
        List<Emprestimo> emprestimoAtivoDoLeitor = new ArrayList<>();
        for (Emprestimo emprestimo : listEmprestimos) {
            if (emprestimo.getLeitor().getNumeroID() == leitor.getNumeroID() && emprestimo.isSituacao()) {
                emprestimoAtivoDoLeitor.add(emprestimo);
            }
        }
        return emprestimoAtivoDoLeitor;
    }

    @Override
    public List<Emprestimo> findEmprestimoAtivo() throws EmprestimoException {
        ArrayList<Emprestimo> listEmprestimos = findMany();
        List<Emprestimo> emprestimoAtivo = new ArrayList<>();
        for (Emprestimo emprestimo : listEmprestimos) {
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
    public ArrayList<Livro> livrosMaisPolulares() {
        ArrayList<Emprestimo> listEmprestimos = findMany();
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
        ArrayList<Emprestimo> listEmprestimos = findMany();
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
    public Boolean verificaAtrasoDoLeitor(Leitor leitor, String dataHoje) {
        ArrayList<Emprestimo> listEmprestimos = findMany();
        DateTimeFormatter dataFormatada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate newDate = LocalDate.parse(dataHoje, dataFormatada);
        for (Emprestimo emprestimo : listEmprestimos) {
            if (emprestimo.getLeitor().getNumeroID() == leitor.getNumeroID()
                    && newDate.isAfter(emprestimo.getDataDevolucao())
                    && emprestimo.isSituacao()) {
                return true;
            }
        }
        return false; // indica que o leitor não tem atrasos
    }

    private int getProximoID(ArrayList<Emprestimo> emprestimos){
        int cont = -1;
        for (Emprestimo l : emprestimos){
            cont++;
        }
        return (cont+1);
    }
}
