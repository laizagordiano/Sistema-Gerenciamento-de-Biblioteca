package model;

import dao.DAO;
import exceptions.EmprestimoException;
import exceptions.LeitorException;
import exceptions.LivroException;
import exceptions.ReservaException;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import static dao.DAO.getReservaDAO;

public class Emprestimo {
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private Leitor leitor;
    private Livro livro;
    private boolean situacao; // true = ainda com emprestimo; false = emprestimo finalizado
    private int id;
    private int renovou;

    public Emprestimo(String dataDoEmprestimo, Leitor leitor, Livro livro) throws EmprestimoException, LivroException, LeitorException, ReservaException {
        if (leitor.getMulta() == 0){
            this.leitor = leitor;
        }
        else{
            throw new LeitorException(LeitorException.MULTADO);
        }
        if (livro.getDisponilidadeEmprestimo()){
            this.livro = livro;
        }
        else{
            throw new LivroException(LivroException.INDISPONIVEL);
        }
        if (DAO.getEmprestimoDAO().findActiveUser(leitor).size() > 3){
            throw new LeitorException(LeitorException.LIMITE);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.dataEmprestimo = LocalDate.parse(dataDoEmprestimo, formatter);
        this.dataDevolucao = dataEmprestimo.plus(Period.ofDays(7));
        this.situacao = true;
        this.renovou = 0;
        DAO.getLivroDAO().update(livro);
    }
    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(String dataEmprestimo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.dataEmprestimo = LocalDate.parse(dataEmprestimo, formatter);
    }



    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Leitor getLeitor() {
        return leitor;
    }

    public void setLeitor(Leitor leitor) {
        this.leitor = leitor;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public boolean isSituacao() {
        return situacao;
    }

    public void setSituacao(boolean situacao) {
        this.situacao = situacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRenovou() {
        return renovou;
    }

    public void setRenovou(int renovou) {
        this.renovou = renovou;
    }

    public void calcularMulta(LocalDate dataAtual) throws LeitorException {
        int data = (int) ChronoUnit.DAYS.between(dataDevolucao, dataAtual); // subtrai a diferença entre as datas
        if (data <= 0){
            leitor.setMulta(leitor.getMulta());
            DAO.getLeitorDAO().update(leitor);
        }
        else {
            leitor.setMulta(leitor.getMulta() + (data * 2));
            leitor.setFimDaMulta(LocalDate.now().plusDays(leitor.getMulta()));
            DAO.getLeitorDAO().update(leitor);
        }
    }
    public void finalizarEmprestimo(LocalDate dataAtual) throws LivroException, LeitorException {
        this.situacao = false;
        calcularMulta(dataAtual);
        this.livro.setDisponilidadeEmprestimo(true);
        DAO.getLivroDAO().update(livro);

    }


    public void renovarEmprestimo( String datAtual) throws Exception {
        if (this.renovou >= 1){
            throw new EmprestimoException(EmprestimoException.LIMITE);
        }
        if (!getLeitor().isStatus()){
            throw new EmprestimoException(EmprestimoException.BLOQUEADO);
        }
        if (getReservaDAO().haReservas() && (!getReservaDAO().primeiroLeitor(leitor))){
            throw new EmprestimoException(EmprestimoException.HARESERVA);
        }
        if (!DAO.getEmprestimoDAO().validaFimDaMulta(leitor, datAtual)){
            throw new EmprestimoException(EmprestimoException.MULTADO);
        }
        else {
            Emprestimo emprestimo = DAO.getEmprestimoDAO().findById(id);
            LocalDate dataDeDevolucao = emprestimo.getDataDevolucao();
            emprestimo.setDataDevolucao(dataDeDevolucao.plusDays(7));
            DAO.getEmprestimoDAO().update(emprestimo);
            this.renovou ++;

        }

    }

    @Override
    public String toString() {
        return "Emprestimo{" +
                "dataEmprestimo=" + dataEmprestimo +
                ", dataDevolucao=" + dataDevolucao +
                ", leitor=" + leitor +
                ", livro=" + livro +
                ", situacao=" + situacao +
                ", id=" + id +
                '}';
    }
}
