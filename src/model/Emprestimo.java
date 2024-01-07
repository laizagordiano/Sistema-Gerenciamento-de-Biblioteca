package model;

import dao.DAO;
import exceptions.EmprestimoException;
import exceptions.LeitorException;
import exceptions.LivroException;
import exceptions.ReservaException;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import static dao.DAO.getReservaDAO;

/**
 * Esta classe é responsável por registrar um empréstimo contendo as informações:
 * Data de emprestimo
 * Data de devolução
 * Leitor
 * Livro
 * Id
 * @author Laiza Araujo Gordiano Oliveira
 * @see exceptions.EmprestimoException
 * @see exceptions.LeitorException
 * @see exceptions.LivroException
 * @see exceptions.ReservaException
 * @see java.time.LocalDate
 * @see java.time.Period
 * @see java.time.format.DateTimeFormatter
 * @see java.time.temporal.ChronoUnit
 * @see java.util.Objects
 */
public class Emprestimo implements Serializable {
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
        if (livro.getDisponibilidadeEmprestimo()){
            this.livro = livro;
        }
        else{
            throw new LivroException(LivroException.INDISPONIVEL);
        }
        if (DAO.getEmprestimoDAO().findActiveUser(leitor).size() > 3){
            throw new LeitorException(LeitorException.LIMITE);
        }
        if(DAO.getEmprestimoDAO().verificaAtrasoDoLeitor(leitor, dataDoEmprestimo)){
            throw new EmprestimoException(EmprestimoException.LEITOR_COM_ATRASO);
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.dataEmprestimo = LocalDate.parse(dataDoEmprestimo, formatter);
        this.dataDevolucao = dataEmprestimo.plus(Period.ofDays(7));
        this.situacao = true;
        this.renovou = 0;
        this.id = -1;
        livro.setDisponibilidadeEmprestimo(true);
        DAO.getLivroDAO().update(livro);
        //DAO.getLeitorDAO().update(leitor);
    }
    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Emprestimo emprestimo = (Emprestimo) o;
        return id == emprestimo.id;
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

    /**
     * Esse método é responsavél por calcular a multa do usuario dobrando
     * o valor de dias de atraso após a data de devolução.
     * @param dataAtual
     * @throws LeitorException
     */
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

    /**
     * Esse método é responsável por finalizar o emprestimo do usuário,
     * fazendo um set na situação para indicar que o emprestimo foi finalizado,
     * e devolvendo o livro do emprestimo (alterando sua disponibilidade para true).
     * @param dataAtual
     * @throws LivroException
     * @throws LeitorException
     */
    public void finalizarEmprestimo(LocalDate dataAtual) throws LivroException, LeitorException {
        this.situacao = false;
        calcularMulta(dataAtual);
        this.livro.setDisponibilidadeEmprestimo(true);
        DAO.getLivroDAO().update(livro);

    }

    /**
     * Esse método é responsável pela renovação do emprestimo,
     * atendendo aos requisitos necessarios para fazer a renovação, como:
     * Ainda não ter renovado;
     * Não estar bloqueado;
     * Que não tenha reservas para o livro, mas se existir, o leitor precisa ser o primeiro na lista de reservas;
     * Não estar multado.
     * @param datAtual
     * @throws Exception
     */
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
