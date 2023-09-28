package dao.reserva;

import model.Emprestimo;
import model.Leitor;
import model.Livro;
import model.Reserva;

import java.util.ArrayList;

public class ReservaDAO implements ReservaDAOInterface{
    private ArrayList<Reserva> listReserva;
    private int proximoID;
    public ReservaDAO() {
        this.listReserva = new ArrayList<>();
        this.proximoID = 0;
    }
    private int getProximoID() {
        return this.proximoID++;
    }
    @Override
    public Reserva create(Reserva obj) {
        this.listReserva.add(obj);
        return obj;
    }

    @Override
    public void delete(Reserva obj) throws Exception {
        this.listReserva.remove(obj);
    }

    @Override
    public void deleteMany() {
        this.listReserva = new ArrayList<>();
        this.proximoID = 0;

    }

    @Override
    public Reserva update(Reserva obj) throws Exception {
        int index = this.listReserva.indexOf(obj);
        this.listReserva.set(index, obj);
        return obj;
    }

    @Override
    public ArrayList<Reserva> findMany() {
        return this.listReserva;
    }

    @Override
    public Reserva findById(int id) throws Exception {
        for (Reserva reserva : this.listReserva) {
            if (reserva.getId() == id) {
                return reserva;
            }
        }
        return null;
    }

    @Override
    public Reserva findReservas(Leitor leitor, Livro livro) {
        for (Reserva reserva: this.listReserva){
            if(reserva.getLeitor().equals(leitor) && reserva.getLivro().equals(livro) ) {
                return reserva;
            }
        }
        return null;
    }
}
