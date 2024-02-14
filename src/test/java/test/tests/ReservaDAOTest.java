package test.tests;

import com.example.sistemagerenciamentodebiblioteca.dao.DAO;
import com.example.sistemagerenciamentodebiblioteca.exceptions.LeitorException;
import com.example.sistemagerenciamentodebiblioteca.exceptions.LivroException;
import com.example.sistemagerenciamentodebiblioteca.exceptions.ReservaException;
import com.example.sistemagerenciamentodebiblioteca.model.Leitor;
import com.example.sistemagerenciamentodebiblioteca.model.Livro;
import com.example.sistemagerenciamentodebiblioteca.model.Reserva;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Essa classe é responsavél por realizar os testes das operações do DAO da classe ReservaDAO.
 * @author Laiza Araujo Gordiano Oliveira
 * @see com.example.sistemagerenciamentodebiblioteca.dao.DAO
 * @see com.example.sistemagerenciamentodebiblioteca.exceptions.LeitorException
 * @see com.example.sistemagerenciamentodebiblioteca.exceptions.LivroException
 * @see com.example.sistemagerenciamentodebiblioteca.exceptions.ReservaException
 * @see com.example.sistemagerenciamentodebiblioteca.model.Reserva
 * @see com.example.sistemagerenciamentodebiblioteca.model.Leitor
 * @see com.example.sistemagerenciamentodebiblioteca.model.Livro
 * @see ArrayList
 * @see org.junit.jupiter.api.AfterEach
 * @see org.junit.jupiter.api.BeforeEach
 * @see org.junit.jupiter.api.Test
 */
public class ReservaDAOTest {
    private Leitor leitor1;
    private Leitor leitor2;
    private Livro livro1;
    private Livro livro2;
    private Reserva reserva1;
    private Reserva reserva2;
    private Reserva semReserva;
    /**
     * Esse método é utilizado para configurar o ambiente de teste antes da execução de cada teste.
     * @throws Exception
     */

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
    /**
     * Esse método é utilizado para limpar o ambiente de teste após a execução de cada teste.
     * Ele deleta todos os registros de reservas criadas durante os testes.
     * @throws Exception
     */
    @AfterEach
    void tearDown(){
        DAO.getLeitorDAO().deleteMany();
        DAO.getLivroDAO().deleteMany();
        DAO.getReservaDAO().deleteMany();
    }
    /**
     * Este método testa a criação de um novo registro de Reserva,
     * ele cria uma nova reserva e armazena o resultado da criação.
     * Obtém a reserva esperada com base no ID.
     * Verifica se a resrerva criada é o esperado.
     * @throws Exception
     */

    @Test
    void create() throws ReservaException,LivroException, LeitorException {
        Reserva atual = DAO.getReservaDAO().create(new Reserva(leitor2,livro1));
        Reserva esperado = DAO.getReservaDAO().findReservas(leitor2,livro1);

        assertEquals(esperado,atual,"Esse teste deveria passar!");
        assertNotNull(esperado,"Esse teste deveria passar!");
    }
    /**
     * Esse teste verifica se a exceção é passada corretamente após não conseguir deletar uma reserva.
     */
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
    /**
     * Esse teste verifica se o método consegue deletar a reserva.
     * @throws LivroException
     */
    @Test
    void delete() throws ReservaException {
        DAO.getReservaDAO().delete(reserva1);
        assertEquals(DAO.getReservaDAO().findMany().size(),1);
    }
    /**
     * Esse teste verifica se a operação deleta todas as reservas.
     */
    @Test
    void deleteMany(){
        DAO.getReservaDAO().deleteMany();
        assertEquals(DAO.getReservaDAO().findMany().size(),0);
    }
    /**
     * Esse teste verifica se a exceção é passada corretamente, após não coonseguir atualizar uma reserva.
     */
    @Test
    void failUpdate(){
        try{
            DAO.getReservaDAO().update(semReserva);
            fail("Uma exceção deveria ser levantada");
        } catch (ReservaException e) {
            assertEquals(LivroException.ATUALIZAR,e.getMessage());
        }
    }
    /**
     * Esse teste verifica se a operação de atualizar a reserva funciona corretamente.
     */
    @Test
    void update() throws  ReservaException {
        reserva1.setLeitor(leitor1);
        reserva1.setLivro(livro2);
        Reserva atualizado = DAO.getReservaDAO().update(reserva1);
        assertEquals(atualizado, reserva1);
    }
    /**
     * Esse teste verifica se o método consegue encontar todods as reservas.
     */
    @Test
    void findMAny(){
        ArrayList<Reserva> todasAsReservas= DAO.getReservaDAO().findMany();
        assertEquals(todasAsReservas.size(),2);
    }
    /**
     * Esse teste verifica se a exceção é lançada corretamente quando da erro na procura por ID.
     */
    @Test
    void failFindById(){
        try{
            DAO.getReservaDAO().findById(2);
            fail("Uma exceção deveria ser lançada");
        } catch (ReservaException e) {
            assertEquals(ReservaException.PROCURAR,e.getMessage());
        }
    }
    /**
     * Este teste verifica se a operação de busca de uma reserva pelo ID funciona corretamente.
     * @throws LivroException
     */
    @Test
    void findById() throws ReservaException {
        Reserva econtrada = DAO.getReservaDAO().findById(1);
        assertEquals(econtrada, reserva2);
    }

    /**
     * Esse teste verifica se é encontrado reservas.
     */
    @Test
    void findReservas() throws ReservaException{
        assertEquals(reserva1,DAO.getReservaDAO().findReservas(leitor1,livro1));
    }

    /**
     * Esse teste verifica se a operação de encontrar o leitor na reserva está correta.
     */
    @Test
    void findLeitornaReserva() throws ReservaException{
        assertEquals(reserva1,DAO.getReservaDAO().findLeitorNaReserva(leitor1));
    }

    /**
     * Esse teste verifica se a operação de encontrar o leitor sendo o primeiro da lista de reservas funciona.
     */
    @Test
    void primeiroLeitor() throws ReservaException{
        assertTrue(DAO.getReservaDAO().primeiroLeitor(leitor1));
        assertFalse(DAO.getReservaDAO().primeiroLeitor(leitor2));
    }

    /**
     * Esse teste verifica se a operação de encontrar o número de reservados funciona.
     */
    @Test
    void numReservados(){
        assertEquals(2,DAO.getReservaDAO().numReservados());
    }

    /**
     * Esse teste verifica se a operação de encontrar se existem reservas funciona.
     */
    @Test
    void haReservas() {
        assertTrue(DAO.getReservaDAO().haReservas());
    }

}




