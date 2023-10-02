package tests;

import dao.DAO;
import exceptions.EmprestimoException;
import exceptions.LeitorException;
import exceptions.LivroException;
import exceptions.ReservaException;
import model.Emprestimo;
import model.Leitor;
import model.Livro;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EmprestimoDAOTest {

    Emprestimo emprestimo1;
    Emprestimo emprestimo2;
    Emprestimo emprestimo3;
    Leitor antonio;
    Leitor lucas;
    Leitor bia;
    Livro livro1;
    Livro livro2;
    ArrayList<Emprestimo> lista;

    @BeforeEach
    void setUp() throws Exception {
        bia = DAO.getLeitorDAO().create(new Leitor("Bia","Rua A,12 - Feira VI","75988622311","452361"));
        antonio = DAO.getLeitorDAO().create(new Leitor("Antonio","Rua Primavera,16 - Bairro Muchila","71998301542","4569871"));
        lucas = DAO.getLeitorDAO().create(new Leitor("Lucas","Rua D,20 - Feira VI","75982830091","239020"));
        livro1 = DAO.getLivroDAO().create(new Livro("O cortiço","Aluísio","Leia","AAR20",2000,"Romance","L302"));
        livro2 = DAO.getLivroDAO().create(new Livro("O beijo","Joaozinho","SumadeLetras","BJR20",2000,"Romance","L0301"));
        emprestimo1 = DAO.getEmprestimoDAO().create(new Emprestimo(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),bia, livro1));
        emprestimo2 = DAO.getEmprestimoDAO().create(new Emprestimo(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),lucas,livro2));
        emprestimo3 = DAO.getEmprestimoDAO().create(new Emprestimo(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),antonio, livro1));
        livro2.setId(12);
    }

    @AfterEach
    void tearDown() throws Exception {
        DAO.getLeitorDAO().deleteMany();
        DAO.getLivroDAO().deleteMany();
        DAO.getEmprestimoDAO().deleteMany();

    }

    @Test
   void create() throws Exception {
       Emprestimo expectedEmprestimo = DAO.getEmprestimoDAO().create(new Emprestimo("19/09/2023",antonio, livro2));
        Emprestimo actualEmprestimo = DAO.getEmprestimoDAO().findById(3);
        assertEquals(expectedEmprestimo,actualEmprestimo,"Esse teste deveria passar!");

    }

    @Test
    void deletarObj() throws Exception {
        DAO.getEmprestimoDAO().delete(emprestimo1);
        int atual = DAO.getEmprestimoDAO().findMany().size();
        assertEquals(2,atual,"Esse teste deveria passar!");
    }

    @Test
    void deletarTudo() throws Exception{
        DAO.getEmprestimoDAO().deleteMany();
        int atual = DAO.getEmprestimoDAO().findMany().size();
        assertEquals(0,atual,"Esse teste deveria passar!");

    }

    @Test
    void update() throws Exception {
        emprestimo1.setDataEmprestimo("20/09/2023");
        emprestimo1.setLeitor(bia);
        Emprestimo atual = DAO.getEmprestimoDAO().update(emprestimo1);
        assertEquals(emprestimo1,atual,"Esse teste deveria passar!");
    }

    @Test
    void findMany() throws Exception {
        int atual = DAO.getEmprestimoDAO().findMany().size();
        assertEquals(3,atual,"Esse teste deveria passar!");
    }

    @Test
    void findById() throws Exception{
        Emprestimo atual = DAO.getEmprestimoDAO().findById(0);
        assertEquals(emprestimo1,atual,"Esse teste deveria passar!");
    }

    @Test
    void atrasados() throws EmprestimoException {
        emprestimo1.setDataDevolucao(LocalDate.now().minusDays(1));
        int atrasou = DAO.getEmprestimoDAO().numLivrosAtrasados();
        int esperado = 1;
        assertEquals(esperado,atrasou,"Esse teste deveria passar!");

    }

    @Test
    void livrosEmprestados() throws EmprestimoException {
        int emprestados = DAO.getEmprestimoDAO().numLivrosEmprestados();
        int esperado = 3;
        assertEquals(esperado,emprestados,"Esse teste deveria passar! ");

    }

    @Test
    void findActiveUser() throws Exception {
      List<Emprestimo> esperado = DAO.getEmprestimoDAO().findActiveUser(bia);
      assertEquals(esperado.get(0),emprestimo1);
      emprestimo1.setSituacao(false);
      DAO.getEmprestimoDAO().update(emprestimo1);
      assertTrue(DAO.getEmprestimoDAO().findActiveUser(bia).isEmpty());
    }
    @Test
    void findEmprestimoAtivo() throws EmprestimoException {
        List<Emprestimo> esperado = DAO.getEmprestimoDAO().findEmprestimoAtivo();
        assertEquals(esperado.get(0),emprestimo1);
        assertEquals(esperado.get(1), emprestimo2);
        assertEquals(esperado.get(2),emprestimo3);
    }
    @Test
    void failFindById() throws Exception {
        emprestimo1.setSituacao(false);
        emprestimo2.setSituacao(false);
        emprestimo3.setSituacao(false);
        DAO.getEmprestimoDAO().update(emprestimo1);
        DAO.getEmprestimoDAO().update(emprestimo2);
        DAO.getEmprestimoDAO().update(emprestimo3);

        try{
            DAO.getEmprestimoDAO().findEmprestimoAtivo();
            fail("Uma exceção deveria ser lançada");
        } catch (EmprestimoException e) {
            assertEquals(EmprestimoException.ATIVOS,e.getMessage());
        }
    }
    @Test
    void validaFinalDaMulta() throws LeitorException {
        emprestimo1.calcularMulta(LocalDate.now().plusDays(8));
        bia = DAO.getLeitorDAO().findById(0);
        assertEquals(2,bia.getMulta());
        assertFalse(DAO.getEmprestimoDAO().validaFimDaMulta(bia,LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
        assertTrue(DAO.getEmprestimoDAO().validaFimDaMulta(bia,LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
        assertTrue(DAO.getEmprestimoDAO().validaFimDaMulta(lucas,LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
        assertNull(lucas.getFimDaMulta());
    }

    @Test
    void vericaAtrasoUsuario() throws Exception {
        assertFalse(DAO.getEmprestimoDAO().verificaAtrasoDoLeitor(bia));
        emprestimo1.setDataDevolucao(LocalDate.now().minusDays(1));
        DAO.getEmprestimoDAO().update(emprestimo1);
        assertTrue(DAO.getEmprestimoDAO().verificaAtrasoDoLeitor(bia));
    }
    @Test
    void numLivrosEmprestados() throws EmprestimoException {
        assertEquals(3,DAO.getEmprestimoDAO().numLivrosEmprestados());
    }
    @Test
    void numLivrosAtrasados() throws EmprestimoException {
        emprestimo1.setDataDevolucao(LocalDate.now().minusDays(1));
        assertEquals(1,DAO.getEmprestimoDAO().numLivrosAtrasados());
    }

    @Test
    void livroMaisPopular() throws LivroException,LeitorException, ReservaException, EmprestimoException {
        ArrayList<Livro> livrosMaisPopulares = DAO.getEmprestimoDAO().livrosMaisPolulares();
        assertEquals(livrosMaisPopulares.size(),1);
        livro2.setDisponilidadeEmprestimo(true);
        DAO.getLivroDAO().update(livro2);
        DAO.getEmprestimoDAO().create(new Emprestimo(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),bia,livro2));
        ArrayList<Livro> livrosMaisPopulares2 = DAO.getEmprestimoDAO().livrosMaisPolulares();
        assertEquals(livrosMaisPopulares2.size(),2);

    }

}
