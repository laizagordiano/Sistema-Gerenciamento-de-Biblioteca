package dao.reserva;

import dao.CRUD;
import model.Leitor;
import model.Livro;
import model.Reserva;

public interface ReservaDAOInterface extends CRUD<Reserva, Exception> {
    public Reserva findReservas(Leitor leitor, Livro livro);
}
