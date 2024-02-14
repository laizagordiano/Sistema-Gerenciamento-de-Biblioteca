package com.example.sistemagerenciamentodebiblioteca.dao.reserva;

import com.example.sistemagerenciamentodebiblioteca.dao.CRUD;
import com.example.sistemagerenciamentodebiblioteca.exceptions.ReservaException;
import com.example.sistemagerenciamentodebiblioteca.model.Leitor;
import com.example.sistemagerenciamentodebiblioteca.model.Livro;
import com.example.sistemagerenciamentodebiblioteca.model.Reserva;

/**
 * Essa interface contém métodos que correspondem as informações da reserva, como:
 * Procurar por reservas específicas;
 * Procurar por leitor na reserva;
 * Verifica se o leitor é o primeiro na lista de reservas;
 * Verifica se há reservas;
 * Retorna o número de livros renovados.
 * @author Laiza Araujo Gordiano Oliveira
 * @see Leitor
 * @see Livro,
 * @see Reserva
 */
public interface ReservaDAOInterface extends CRUD<Reserva, Exception> {
    /**
     * Esse método procura e retorna uma reserva específica associada a um leitor e a um livro dentro
     * de uma lista de reservas. Se encontrar, retorna a reserva; caso contrário, retorna null
     * @param leitor
     * @param livro
     * @return
     */
    public Reserva findReservas(Leitor leitor, Livro livro) throws ReservaException;

    /**
     * Esse método procura e retorna uma reserva específica associada a um leitor dentro de uma lista de reservas.
     * Se encontrar, retorna a reserva; caso contrário, retorna null
     * @param leitor
     * @return
     */
    public Reserva findLeitorNaReserva(Leitor leitor) throws ReservaException;

    /**
     * Esse método verifica se o leitor fornecido é o primeiro na lista de reservas associadas a ele.
     *  Se for o caso, retorna true; caso contrário, retorna false.
     * @param leitor
     * @return
     */
    public boolean primeiroLeitor(Leitor leitor) throws ReservaException;

    /**
     * Esse método verifica se existem reservas na lista.
     * @return
     */
    public boolean haReservas();

    /**
     * Esse método conta o número de livros distintos reservados.
     * @return
     */
    public int numReservados();
}
