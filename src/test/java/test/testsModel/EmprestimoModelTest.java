package test.testsModel;

import com.example.sistemagerenciamentodebiblioteca.dao.DAO;
import com.example.sistemagerenciamentodebiblioteca.exceptions.EmprestimoException;
import com.example.sistemagerenciamentodebiblioteca.exceptions.LeitorException;
import com.example.sistemagerenciamentodebiblioteca.exceptions.LivroException;
import com.example.sistemagerenciamentodebiblioteca.model.Emprestimo;
import com.example.sistemagerenciamentodebiblioteca.model.Leitor;
import com.example.sistemagerenciamentodebiblioteca.model.Livro;
import com.example.sistemagerenciamentodebiblioteca.model.Reserva;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Essa classe é responsavél por realizar os testes das operações do DAO da classe EmprestimoModel.
 * @author Laiza Araujo Gordiano Oliveira
 * @see DAO
 * @see EmprestimoException
 * @see LeitorException
 * @see LivroException
 * @see DateTimeFormatter
 * @see Emprestimo
 * @see Leitor
 * @see Livro
 * @see LocalDate
 * @see org.junit.jupiter.api.AfterEach
 * @see org.junit.jupiter.api.BeforeEach
 * @see org.junit.jupiter.api.Test
 */
public class EmprestimoModelTest {
    Leitor bia;
    Livro livro1;
    Livro livro2;
    Livro livro3;
    Livro livro4;
    Emprestimo emprestimo1;
    /**
     * Esse método é utilizado para configurar o ambiente de teste antes da execução de cada teste.
     * @throws Exception
     */
    @BeforeEach
    void setUp() throws Exception {
        bia = DAO.getLeitorDAO().create(new Leitor("Bia", "Rua A,12 - Feira VI", "75988622311", "452361"));
        livro1 = DAO.getLivroDAO().create(new Livro("O cortiço", "Aluísio", "Leia", "AAR20", 2000, "Romance", "L302"));
        livro2 = DAO.getLivroDAO().create(new Livro("Capitães da Areia","Jorge Amado","Gringa","1101",1999,"Ação","023"));
        livro3 = DAO.getLivroDAO().create(new Livro("O Pequeno Principe"," Antoine de Saint-Exupéry","Companhia das Letras","1107",1899,"Romance","156"));
        livro4 = DAO.getLivroDAO().create(new Livro("A pequena Sereia"," Antoine de Saint-Exupéry","Companhia das Letras","1100",2000,"Contos","156"));
        emprestimo1 = DAO.getEmprestimoDAO().create(new Emprestimo(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), bia, livro1));
    }
    /**
     *  Esse método é utilizado para limpar o ambiente de teste após a execução de cada teste.
     *  Ele deleta todos os registros de empréstimos criados durante os testes.
     * @throws Exception
     */
    @AfterEach
        void tearDown(){
        DAO.getLeitorDAO().deleteMany();
        DAO.getLivroDAO().deleteMany();
        DAO.getEmprestimoDAO().deleteMany();

    }

    /**
     * Esse teste verifica se o método calcularMulta funciona corretamente.
     * @throws LeitorException
     */
    @Test
    void calcularMulta() throws LeitorException {
        emprestimo1.calcularMulta(LocalDate.now().plusDays(8));
        bia = DAO.getLeitorDAO().findById(0);
        assertEquals(2,bia.getMulta());
    }

    /**
     * Esse teste verifica se a operação finalizar emprestimo funciona corretamente.
     * @throws LivroException
     * @throws LeitorException
     */
    @Test
    void finalizarEmprestimo() throws LivroException, LeitorException {
        emprestimo1.finalizarEmprestimo(LocalDate.now().plusDays(1));
        assertFalse(emprestimo1.isSituacao());
    }

    /**
     * Esse teste verifica se a exceção é lançada quando o limite de renovação é atingido.
     * @throws Exception
     */
    @Test
    void failLimiteRenovacao() throws Exception {
        emprestimo1.renovarEmprestimo(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        try {
            emprestimo1.renovarEmprestimo(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            fail("Uma exceção deveria ser lançada");
        }
        catch (EmprestimoException e){
            assertEquals(EmprestimoException.LIMITE, e.getMessage());
        }
    }

    /**
     * Esse teste verifica se a exceção é lançada quando a situação do leitor é false.
     * @throws Exception
     */
    @Test
    void failSituaofalse() throws Exception {
        bia.setStatus(false);
        DAO.getLeitorDAO().update(bia);
        try {
            emprestimo1.renovarEmprestimo(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            fail("Uma exceção deveria ser lançada");
        }
        catch (EmprestimoException e){
            assertEquals(EmprestimoException.BLOQUEADO, e.getMessage());
        }

    }

    /**
     * Esse teste verifica se a exceção é lançada quando existem reservas e o leitor não é o primeiro da lista.
     * @throws Exception
     */
    @Test
    void failHaReservas() throws Exception{
        Leitor leitor1 = DAO.getLeitorDAO().create(new Leitor("João", "Conceição", "11 22222", "0232"));
        Leitor leitor2 = DAO.getLeitorDAO().create(new Leitor("Pablo", "Feira VI", "22 22222", "1214563"));
        Reserva reserva1 = DAO.getReservaDAO().create(new Reserva(leitor1,livro1));
        Reserva reserva2 = DAO.getReservaDAO().create(new Reserva(leitor2,livro1));
        try {
            emprestimo1.renovarEmprestimo(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            fail("Uma exceção deveria ser lançada");
        } catch (EmprestimoException e) {
            assertEquals(EmprestimoException.HARESERVA, e.getMessage());
        }

    }

    /**
     * Esse teste verifica se a exceção é lançada quando o leitor ainda está multado.
     * @throws Exception
     */
    @Test
    void failValidaFimDaMulta() throws Exception {
        emprestimo1.calcularMulta(LocalDate.now().plusDays(8));
        bia = DAO.getLeitorDAO().findById(0);
        boolean multado = DAO.getEmprestimoDAO().validaFimDaMulta(bia,LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        try {
            emprestimo1.renovarEmprestimo(LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            fail("Uma exceção deveria ser lançada");
        }
        catch (EmprestimoException e){
            assertEquals(EmprestimoException.MULTADO, e.getMessage());
        }

    }

    /**
     * Esse teste verifica se a operação de renovar emprestimo funciona corretamente.
     * @throws Exception
     */
    @Test
    void renovaremprestimo() throws Exception {
        emprestimo1 = DAO.getEmprestimoDAO().create(new Emprestimo(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), bia, livro1));
        emprestimo1.renovarEmprestimo(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        assertEquals(emprestimo1.getRenovou(),1);
    }

    /**
     * Esse teste verifica se a exceção é lançada quando o leitor está multado e não pode fazer o emprestimo.
     * @throws Exception
     */
    @Test
    void failEmprestimoConstrutorLeitorMultado() throws Exception {
        emprestimo1.calcularMulta(LocalDate.now().plusDays(8));
        bia.setFimDaMulta(LocalDate.now().plusDays(3));
        DAO.getLeitorDAO().update(bia);
        try{
            new Emprestimo(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),bia,livro1);
            fail("Uma exceção deveria ser lançada");
        } catch (LeitorException e) {
            assertEquals(LeitorException.MULTADO,e.getMessage());
        }
    }

    /**
     * Esse teste verifica se a exceção é lançada quando o leitor atinge o limite de livros a serem emprestados.
     * @throws Exception
     */
    @Test
    void failEmprestimoConstrutorLimiteDeLivros() throws Exception {
        DAO.getEmprestimoDAO().create(new Emprestimo(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),bia,livro1));
        DAO.getEmprestimoDAO().create(new Emprestimo(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),bia,livro2));
        DAO.getEmprestimoDAO().create(new Emprestimo(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),bia,livro3));
            try{
            DAO.getEmprestimoDAO().create(new Emprestimo(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),bia,livro4));
            fail("Uma exceção deveria ser lançada");
        } catch (LeitorException e){
            assertEquals(LeitorException.LIMITE,e.getMessage());
        }
    }

    /**
     * Esse teste verifica se a excção é lançada quando o livro não está disponivel para emprestimo.
     * @throws Exception
     */
    @Test
    void failDisponibilidadeLivro() throws Exception {
        livro1.setDisponibilidadeEmprestimo(false);
        DAO.getLivroDAO().update(livro1);
        try{
            DAO.getEmprestimoDAO().create(new Emprestimo(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),bia,livro1));
            fail("Uma exceção deveria ser lançada");
        }catch (LivroException e){
            assertEquals(LivroException.INDISPONIVEL, e.getMessage());
        }

    }

    }




