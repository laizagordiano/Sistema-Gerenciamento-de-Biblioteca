package model;

import java.time.LocalDate;
import java.util.Objects;

public class Emprestimo {
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private Leitor leitor;
    private Livro livro;
    private boolean situacao; // true = situação em aberto; false = emprestimo finalizado
    private int id;

    public Emprestimo(Leitor leitor, Livro livro) {
        LocalDate dataDeEmprestimo = LocalDate.now();
        LocalDate dataDeDevolucao = dataDeEmprestimo.plusDays(7);
        livro.setDisponilidadeEmprestimo(false);
        this.livro = livro;
        this.leitor = leitor;
        this.situacao = true;
        this.dataEmprestimo = dataDeEmprestimo;
        this.dataDevolucao = dataDeDevolucao;

    }
    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
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

    public void set(Leitor leitor) {
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