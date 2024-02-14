package com.example.sistemagerenciamentodebiblioteca.dao.emprestimo;

import com.example.sistemagerenciamentodebiblioteca.dao.CRUD;
import com.example.sistemagerenciamentodebiblioteca.exceptions.EmprestimoException;
import com.example.sistemagerenciamentodebiblioteca.model.Emprestimo;
import com.example.sistemagerenciamentodebiblioteca.model.Leitor;
import com.example.sistemagerenciamentodebiblioteca.model.Livro;

import java.util.ArrayList;
import java.util.List;

/** Essa interface contém métodos que correspodem as informações do empréstimo, como:
 * Procurar por empréstimo;
 * Procurar por leitor;
 * Procura os empréstimos ativos do usuário;
 * Procurar se o empréstimo está ativo;
 * Procurar os livros mais populares;
 * Validar se a multa acabou;
 * Relatar o número de livros emprestados;
 * Relatar o número de livros atrasados.
 * @author Laiza Araujo Gordiano Oliveira
 * @see dao.CRUD
 * @see EmprestimoException
 * @see Emprestimo
 * @see Leitor
 * @see Livro
 * @see ArrayList
 * @see List
 */

public interface EmprestimoDAOInterface extends CRUD<Emprestimo, Exception> {
    /**
     * Esse método busca por um objeto Emprestimo associado a um leitor e a um livro específicos dentro
     * de uma lista de empréstimos.
     * @param leitor
     * @param livro
     * @return
     */
    public Emprestimo findEmprestimo(Leitor leitor, Livro livro);

    /**
     * Esse método busca por um objeto Emprestimo associado a um leitor e a um livro específicos dentro de uma lista
     * de empréstimos.
     * @param leitor
     * @return
     */
    public List<Emprestimo> findByLeitor(Leitor leitor);

    /**
     * Esse metódo tem como objetivo encontrar e retornar todos os empréstimos associados a um leitor específico
     * dentro de uma lista de empréstimos.
     * @param leitor
     * @return
     * @throws EmprestimoException
     */
    public List<Emprestimo> findActiveUser(Leitor leitor) throws EmprestimoException;

    /**
     * Esse método tem como objetivo encontrar e retornar todos os empréstimos ativos presentes em uma lista
     * de empréstimos.
     * @return
     * @throws EmprestimoException
     */
    public List<Emprestimo> findEmprestimoAtivo() throws EmprestimoException;

    /**
     *Esse método tem como objetivo encontrar e retornar uma lista de livros que são considerados os mais populares
     *  com base na quantidade de empréstimos registrados para cada livro em uma lista de empréstimos.
     * @return
     */
    ArrayList<Livro> livrosMaisPolulares();

    /**
     * Esse método tem como objetivo validar se a data atual (dataAtual) é posterior à data de término da multa
     * associada a um leitor específico.
     * @param leitor
     * @param dataAtual
     * @return
     */
    public boolean validaFimDaMulta(Leitor leitor, String dataAtual);

    /**
     * Esse método retorna o número de livros que estão atualmente emprestados com base nos empréstimos ativos.
     * @return
     * @throws EmprestimoException
     */
    public int numLivrosEmprestados() throws EmprestimoException;

    /**
     * Esse método retorna o número de livros atualmente emprestados que estão
     * em atraso, ou seja, aqueles cuja data de devolução já passou.
     * @return
     * @throws EmprestimoException
     */
    public int numLivrosAtrasados() throws EmprestimoException;

}
