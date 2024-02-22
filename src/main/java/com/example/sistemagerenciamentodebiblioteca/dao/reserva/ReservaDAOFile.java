package com.example.sistemagerenciamentodebiblioteca.dao.reserva;

import com.example.sistemagerenciamentodebiblioteca.armazenamento.FileMethods;
import com.example.sistemagerenciamentodebiblioteca.exceptions.EmprestimoException;
import com.example.sistemagerenciamentodebiblioteca.exceptions.LivroException;
import com.example.sistemagerenciamentodebiblioteca.exceptions.ReservaException;
import com.example.sistemagerenciamentodebiblioteca.model.Emprestimo;
import com.example.sistemagerenciamentodebiblioteca.model.Leitor;
import com.example.sistemagerenciamentodebiblioteca.model.Livro;
import com.example.sistemagerenciamentodebiblioteca.model.Reserva;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A classe implementa a interface ReservaDAOInterface e fornece métodos
 * para realizar operações relacionadas a reservas, como criação, exclusão, atualização
 * e consulta.
 * @author Laiza Araujo Gordiano Oliveira
 */
public class ReservaDAOFile implements ReservaDAOInterface{
    File arquivo;
    private static final String NAMEFILE= "reservas";

    public ReservaDAOFile(){
        arquivo = FileMethods.createFile(NAMEFILE);
    }


    private int getProximoID(ArrayList<Reserva> reservas){
        int cont = -1;
        for (Reserva l : reservas){
            cont++;
        }
        return (cont+1);
    }
    @Override
    public Reserva create(Reserva obj) {
        ArrayList<Reserva> reservas = findMany() ;
        obj.setId(this.getProximoID(reservas));
        reservas.add(obj);
        FileMethods.sobreescreverArquivo(arquivo, reservas);
        return obj;

    }

    @Override
    public void delete(Reserva obj) throws ReservaException {
        ArrayList<Reserva> listReservas = findMany();
        boolean deletou = listReservas.remove(obj);
        if (!deletou){
            throw new ReservaException(ReservaException.DELETAR,obj);
        }
        FileMethods.sobreescreverArquivo(arquivo, listReservas);

    }

    @Override
    public void deleteMany() {
        FileMethods.apagarConteudoArquivo(arquivo);

    }

    @Override
    public Reserva update(Reserva obj) throws ReservaException {
        ArrayList<Reserva> listReservas = findMany();
        int index = listReservas.indexOf(obj);
        if (index == -1){
            throw new ReservaException(ReservaException.ATUALIZAR,obj);
        }
        listReservas.set(index, obj);
        FileMethods.sobreescreverArquivo(arquivo,listReservas);
        return obj;
    }

    @Override
    public ArrayList<Reserva> findMany() {
        return  FileMethods.consultarArquivoList(arquivo);
    }

    @Override
    public Reserva findById(int id) throws ReservaException {
        ArrayList<Reserva> listReservas = findMany();
        for (Reserva reserva: listReservas){
            if (Objects.equals(reserva.getId(), id)){
                return reserva;
            }
        }
        throw new ReservaException(ReservaException.PROCURAR);
    }

    @Override
    public Reserva findReservas(Leitor leitor, Livro livro) throws ReservaException {
        ArrayList<Reserva> listReservas = findMany();
        for (Reserva reserva : listReservas) {
            if (reserva.getLeitor().equals(leitor) && reserva.getLivro().equals(livro)) {
                return reserva;
            }
        }
        throw new ReservaException(ReservaException.PROCURAR);
    }

    @Override
    public Reserva findLeitorNaReserva(Leitor leitor) throws ReservaException{
        ArrayList<Reserva> listReservas = findMany();
        for (Reserva reserva : listReservas) {
            if (reserva.getLeitor().equals(leitor)) {
                return reserva;
            }
        }
        return null;
    }

    @Override
    public boolean primeiroLeitor(Leitor leitor) throws ReservaException {
        ArrayList<Reserva> listReservas = findMany();
        Reserva primeiraReserva = findLeitorNaReserva(leitor);
        return primeiraReserva != null && listReservas.indexOf(primeiraReserva) == 0;
    }

    @Override
    public boolean haReservas() {
        ArrayList<Reserva> listReservas = findMany();
        return !listReservas.isEmpty();
    }

    @Override
    public int numReservados() {
        ArrayList<Reserva> listReservas = findMany();
        int cont = 0;
        List<String> isbnContabilizados = new ArrayList<>();
        for (Reserva reserva : listReservas) {
            if (!isbnContabilizados.contains(reserva.getLivro().getISBN())) {
                isbnContabilizados.add(reserva.getLivro().getISBN());
                cont++;
            }
        }
        return cont;
    }
}
