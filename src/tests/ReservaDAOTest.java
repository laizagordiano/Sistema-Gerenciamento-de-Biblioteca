package tests;

import dao.DAO;
import exceptions.LeitorException;
import exceptions.LivroException;
import exceptions.ReservaException;
import model.Leitor;
import model.Livro;
import model.Reserva;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ReservaDAOTest {
    private Leitor leitor1;
    private Leitor leitor2;
    private Livro livro1;
    private Livro livro2;
    private Reserva reserva1;
    private Reserva reserva2;
    private Reserva semReserva;

    @BeforeEach
    void setUp(){
        leitor1 = DAO.getLeitorDAO().create(new Leitor("João","Conceição","11 22222","0232"));
        leitor2 = DAO.getLeitorDAO().create(new Leitor("Pablo","Feira VI","22 22222","1214563"));
        livro1 = DAO.getLivroDAO().create(new Livro("A rua","José","Brasileira","2577",1989,"Romance","001"));
        livro2 = DAO.getLivroDAO().create(new Livro("Capitães da Areia","Jorge Amado","Gringa","1101",1999,"Ação","023"));
        reserva1 = DAO.getReservaDAO().create(new Reserva(leitor1,livro1));
        reserva2 = DAO.getReservaDAO().create(new Reserva(leitor2,livro2));
        semReserva = new Reserva(leitor2, livro2);
        semReserva.setId(15);
    }
    @AfterEach
    void tearDown(){
        DAO.getLeitorDAO().deleteMany();
        DAO.getLivroDAO().deleteMany();
        DAO.getReservaDAO().deleteMany();
    }

    @Test
    void create() throws ReservaException,LivroException, LeitorException {
        Reserva atual = DAO.getReservaDAO().create(new Reserva(leitor2,livro1));
        Reserva esperado = DAO.getReservaDAO().findReservas(leitor2,livro1);

        assertEquals(esperado,atual,"Esse teste deveria passar!");
        assertNotNull(esperado,"Esse teste deveria passar!");
    }
    @Test
    void failDelete(){
        try{
            DAO.getReservaDAO().delete(semReserva);
            fail("Uma exceção deveria ser levantada");
        }
        catch (ReservaException e) {
            assertEquals(ReservaException.DELETAR,e.getMessage());
        }
    }
    @Test
    void delete() throws ReservaException {
        DAO.getReservaDAO().delete(reserva1);
        assertEquals(DAO.getReservaDAO().findMany().size(),1);
    }

    @Test
    void deleteMany(){
        DAO.getReservaDAO().deleteMany();
        assertEquals(DAO.getReservaDAO().findMany().size(),0);
    }
    @Test
    void failUpdate(){
        try{
            DAO.getReservaDAO().update(semReserva);
            fail("Uma exceção deveria ser levantada");
        } catch (ReservaException e) {
            assertEquals(LivroException.ATUALIZAR,e.getMessage());
        }
    }
    @Test
    void update() throws  ReservaException {
        reserva1.setLeitor(leitor1);
        reserva1.setLivro(livro2);
        Reserva atualizado = DAO.getReservaDAO().update(reserva1);
        assertEquals(atualizado, reserva1);
    }
    @Test
    void findMAny(){
        ArrayList<Reserva> todasAsReservas= DAO.getReservaDAO().findMany();
        assertEquals(todasAsReservas.size(),2);
    }
    @Test
    void failFindById(){
        try{
            DAO.getReservaDAO().findById(2);
            fail("Uma exceção deveria ser lançada");
        } catch (ReservaException e) {
            assertEquals(ReservaException.PROCURAR,e.getMessage());
        }
    }

    @Test
    void findById() throws ReservaException {
        Reserva econtrada = DAO.getReservaDAO().findById(1);
        assertEquals(econtrada, reserva2);
    }
    @Test
    void findReservas(){
        assertEquals(reserva1,DAO.getReservaDAO().findReservas(leitor1,livro1));
    }

    @Test
    void findLeitornaReserva(){
        assertEquals(reserva1,DAO.getReservaDAO().findLeitorNaReserva(leitor1));
    }
    @Test
    void primeiroLeitor(){
        assertTrue(DAO.getReservaDAO().primeiroLeitor(leitor1));
        assertFalse(DAO.getReservaDAO().primeiroLeitor(leitor2));
    }
    @Test
    void numReservados(){
        assertEquals(2,DAO.getReservaDAO().numReservados());
    }
    @Test
    void haReservas(){
        assertTrue(DAO.getReservaDAO().haReservas());
    }

}




