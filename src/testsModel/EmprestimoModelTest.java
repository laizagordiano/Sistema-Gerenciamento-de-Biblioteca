package testsModel;

import dao.DAO;
import exceptions.EmprestimoException;
import exceptions.LeitorException;
import exceptions.LivroException;
import model.Emprestimo;
import model.Leitor;
import model.Livro;
import model.Reserva;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EmprestimoModelTest {
    Leitor bia;
    Livro livro1;
    Livro livro2;
    Livro livro3;
    Livro livro4;
    Emprestimo emprestimo1;

    @BeforeEach
    void setUp() throws Exception {
        bia = DAO.getLeitorDAO().create(new Leitor("Bia", "Rua A,12 - Feira VI", "75988622311", "452361"));
        livro1 = DAO.getLivroDAO().create(new Livro("O cortiço", "Aluísio", "Leia", "AAR20", 2000, "Romance", "L302"));
        livro2 = DAO.getLivroDAO().create(new Livro("Capitães da Areia","Jorge Amado","Gringa","1101",1999,"Ação","023"));
        livro3 = DAO.getLivroDAO().create(new Livro("O Pequeno Principe"," Antoine de Saint-Exupéry","Companhia das Letras","1107",1899,"Romance","156"));
        livro4 = DAO.getLivroDAO().create(new Livro("A pequena Sereia"," Antoine de Saint-Exupéry","Companhia das Letras","1100",2000,"Contos","156"));
        emprestimo1 = DAO.getEmprestimoDAO().create(new Emprestimo(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), bia, livro1));
    }
    @AfterEach
        void tearDown(){
        DAO.getLeitorDAO().deleteMany();
        DAO.getLivroDAO().deleteMany();
        DAO.getEmprestimoDAO().deleteMany();

    }
    @Test
    void calcularMulta() throws LeitorException {
        emprestimo1.calcularMulta(LocalDate.now().plusDays(8));
        bia = DAO.getLeitorDAO().findById(0);
        assertEquals(2,bia.getMulta());
    }
    @Test
    void finalizarEmprestimo() throws LivroException, LeitorException {
        emprestimo1.finalizarEmprestimo(LocalDate.now().plusDays(1));
        assertFalse(emprestimo1.isSituacao());
    }
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
    @Test
    void renovaremprestimo() throws Exception {
        emprestimo1 = DAO.getEmprestimoDAO().create(new Emprestimo(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), bia, livro1));
        emprestimo1.renovarEmprestimo(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        assertEquals(emprestimo1.getRenovou(),1);
    }

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
    @Test
    void failDisponibilidadeLivro() throws Exception {
        livro1.setDisponilidadeEmprestimo(false);
        DAO.getLivroDAO().update(livro1);
        try{
            DAO.getEmprestimoDAO().create(new Emprestimo(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),bia,livro1));
            fail("Uma exceção deveria ser lançada");
        }catch (LivroException e){
            assertEquals(LivroException.INDISPONIVEL, e.getMessage());
        }

    }

    }




