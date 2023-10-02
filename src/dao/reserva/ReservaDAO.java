package dao.reserva;

import exceptions.ReservaException;
import model.Leitor;
import model.Livro;
import model.Reserva;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ReservaDAO implements ReservaDAOInterface {
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
        obj.setId(this.getProximoID());
        this.listReserva.add(obj);
        return obj;
    }

    @Override
    public void delete(Reserva obj) throws ReservaException {
        boolean deletou = this.listReserva.remove(obj);
        if (!deletou) {
            throw new ReservaException(ReservaException.DELETAR);
        }
    }

    @Override
    public void deleteMany() {
        this.listReserva = new ArrayList<>();
        this.proximoID = 0;

    }

    @Override
    public Reserva update(Reserva obj) throws ReservaException {
        int index = this.listReserva.indexOf(obj);
        if (index == -1) {
            throw new ReservaException(ReservaException.ATUALIZAR);
        }
        this.listReserva.set(index, obj);
        return obj;
    }

    @Override
    public ArrayList<Reserva> findMany() {
        return this.listReserva;
    }

    @Override
    public Reserva findById(int id) throws ReservaException {
        for (Reserva reserva : listReserva) {
            if (Objects.equals(reserva.getId(), id)) {
                return reserva;
            }
        }
        throw new ReservaException(ReservaException.PROCURAR);
    }

    @Override
    public Reserva findReservas(Leitor leitor, Livro livro) {
        for (Reserva reserva : this.listReserva) {
            if (reserva.getLeitor().equals(leitor) && reserva.getLivro().equals(livro)) {
                return reserva;
            }
        }
        return null;
    }

    @Override
    public Reserva findLeitorNaReserva(Leitor leitor) {
        for (Reserva reserva : this.listReserva) {
            if (reserva.getLeitor().equals(leitor)) {
                return reserva;
            }
        }
        return null;
    }

    @Override
    public boolean primeiroLeitor(Leitor leitor) {
        Reserva primeiraReserva = findLeitorNaReserva(leitor);
        return primeiraReserva != null && listReserva.indexOf(primeiraReserva) == 0;
    }

    @Override
    public int numReservados() {
        int cont = 0;
        List<String> isbnContabilizados = new ArrayList<>();
        for (Reserva reserva : listReserva) {
            if (!isbnContabilizados.contains(reserva.getLivro().getISBN())) {
                isbnContabilizados.add(reserva.getLivro().getISBN());
                cont++;
            }
        }
        return cont;
    }



    @Override
    public boolean haReservas() {
        return !listReserva.isEmpty();
    }

}
