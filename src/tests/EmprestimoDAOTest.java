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
/**
 * Essa classe é responsavél por realizar os testes das operações do DAO da classe EmprestimoDAO.
 * @author Laiza Araujo Gordiano Oliveira
 * @see dao.DAO
 * @see exceptions.EmprestimoException
 * @see exceptions.LeitorException
 * @see exceptions.LivroException
 * @see exceptions.ReservaException
 * @see model.Emprestimo
 * @see model.Leitor
 * @see model.Livro
 * @see java.time.LocalDate
 * @see java.time.format.DateTimeFormatter
 * @see java.util.ArrayList
 * @see java.util.List
 * @see org.junit.jupiter.api.AfterEach
 * @see org.junit.jupiter.api.BeforeEach
 * @see org.junit.jupiter.api.Test
 */
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

    /**
     * Esse método é utilizado para configurar o ambiente de teste antes da execução de cada teste.
     * @throws Exception
     */
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
    }
    /**
     *  Esse método é utilizado para limpar o ambiente de teste após a execução de cada teste.
     *  Ele deleta todos os registros de empréstimos criados durante os testes.
     * @throws Exception
     */
    @AfterEach
    void tearDown() throws Exception {
        DAO.getLeitorDAO().deleteMany();
        DAO.getLivroDAO().deleteMany();
        DAO.getEmprestimoDAO().deleteMany();

    }


    /**
     * Este método testa a criação de um novo registro de Empréstimo,
     * ele cria um novo empréstimo e armazena o resultado da criação.
     * Obtém o empréstimo esperado com base no ID.
     * Verifica se o empréstimo criado é o esperado e se o empréstimo esperado não é nulo.
     * @throws Exception
     */
    @Test
   void create() throws Exception {
       Emprestimo expectedEmprestimo = DAO.getEmprestimoDAO().create(new Emprestimo("19/09/2023",antonio, livro2));
       Emprestimo actualEmprestimo = DAO.getEmprestimoDAO().findById(3);
       assertEquals(expectedEmprestimo,actualEmprestimo,"Esse teste deveria passar!");

    }

    /**
     * Este teste verifica se a operação de deleção específica funciona corretamente.
     * Remove o empréstimo utilizando o método delete() do DAO e, em seguida, verifica se o
     * número total de empréstimos foi reduzido adequadamente.
     * @throws Exception
     */
    @Test
    void delete() throws Exception {
        DAO.getEmprestimoDAO().delete(emprestimo1);
        int atual = DAO.getEmprestimoDAO().findMany().size();
        assertEquals(2,atual,"Esse teste deveria passar!");
    }

    /**
     * Este teste verifica se a operação de deletar todos os registros funciona corretamente.
     * Utiliza o método deleteMany() do DAO para remover todos os empréstimos e, em seguida, verifica se o
     * número total de empréstimos é zero após deletados.
     * @throws Exception
     */
    @Test
    void deleteMany() throws Exception{
        DAO.getEmprestimoDAO().deleteMany();
        int atual = DAO.getEmprestimoDAO().findMany().size();
        assertEquals(0,atual,"Esse teste deveria passar!");

    }
    /**
     * Este teste verifica se a operação de atualização específica funciona corretamente.
     * Ele Modifica as informações do empréstimo, como data, leitor e livro, utilizando os métodos set.
     * Utiliza o método update() do DAO para aplicar as mudanças e, em seguida, verifica se o
     * Empréstimo atualizado é o mesmo que o esperado.
     * @throws Exception
     */

    @Test
    void update() throws Exception {
        emprestimo1.setDataEmprestimo("20/09/2023");
        emprestimo1.setLeitor(bia);
        Emprestimo atual = DAO.getEmprestimoDAO().update(emprestimo1);
        assertEquals(emprestimo1,atual,"Esse teste deveria passar!");
    }

    /**
     * Este teste verifica se a operação de busca de todos os registros funciona corretamente.
     * Utiliza o método findMany() do DAO para obter a lista de empréstimos e, em seguida, verifica se o
     * número total de empréstimos é o mesmo que o esperado.
     * @throws Exception
     */
    @Test
    void findMany() throws Exception {
        int atual = DAO.getEmprestimoDAO().findMany().size();
        assertEquals(3,atual,"Esse teste deveria passar!");
    }

    /**
     * Este teste verifica se a operação de busca por ID funciona corretamente.
     * Utiliza o método findById() do DAO para obter o Empréstimo com ID 0 e, em seguida, verifica se o
     * Empréstimo retornado é o mesmo que o esperado.
     * @throws Exception
     */
    @Test
    void findById() throws Exception{
        Emprestimo atual = DAO.getEmprestimoDAO().findById(0);
        assertEquals(emprestimo1,atual,"Esse teste deveria passar!");
    }

    /**
     * Este teste verifica se a operação de contagem de livros emprestados funciona corretamente.
     * Utiliza o método numLivrosEmprestados() do DAO para obter o número de livros atualmente emprestados e,
     * em seguida, verifica se o número retornado é o mesmo que o esperado.
     * @throws EmprestimoException
     */
    @Test
    void livrosEmprestados() throws EmprestimoException {
        int emprestados = DAO.getEmprestimoDAO().numLivrosEmprestados();
        int esperado = 3;
        assertEquals(esperado,emprestados,"Esse teste deveria passar! ");

    }

    /**
     * Este teste verifica se a operação de busca de empréstimos ativos de um usuário funciona corretamente.
     * Utiliza o método findActiveUser() do DAO para obter a lista de empréstimos ativos associados ao usuário
     * e, em seguida, verifica se o primeiro empréstimo retornado é o mesmo que o esperado.
     * Modifica a situação do Empréstimo para inativo (false) utilizando o método setSituacao() e,
     * em seguida, atualiza com o método update().
     * Depois verifica se a lista de empréstimos ativos associados ao usuário está vazia.
     * @throws Exception
     */
    @Test
    void findActiveUser() throws Exception {
      List<Emprestimo> esperado = DAO.getEmprestimoDAO().findActiveUser(bia);
      assertEquals(esperado.get(0),emprestimo1);
      emprestimo1.setSituacao(false);
      DAO.getEmprestimoDAO().update(emprestimo1);
      assertTrue(DAO.getEmprestimoDAO().findActiveUser(bia).isEmpty());
    }

    /**
     * Este teste verifica se a operação de busca de empréstimos ativos funciona corretamente.
     * Utiliza o método findEmprestimoAtivo() do DAO para obter a lista de todos os empréstimos ativos e,
     * em seguida, verifica se cada empréstimo retornado é o mesmo que o esperado
     * @throws EmprestimoException
     */
    @Test
    void findEmprestimoAtivo() throws EmprestimoException {
        List<Emprestimo> esperado = DAO.getEmprestimoDAO().findEmprestimoAtivo();
        assertEquals(esperado.get(0),emprestimo1);
        assertEquals(esperado.get(1), emprestimo2);
        assertEquals(esperado.get(2),emprestimo3);
    }

    /**
     * Este teste verifica se a operação de busca de empréstimos ativos lança a exceção correta quando todos os empréstimos estão inativos.
     * É modificada a situação de todos os Empréstimos para inativo (false) utilizando o método setSituacao(), para forçar a exceção
     * Em seguida, tenta executar o método findEmprestimoAtivo() e espera que uma exceção do tipo EmprestimoException
     * seja lançada, com a mensagem correspondente a "Sem empréstimos ativos".
     * @throws Exception
     */
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

    /**
     * Este teste verifica se a operação de validação do fim da multa funciona corretamente.
     * Calcula a multa associada ao Empréstimo 'emprestimo1' utilizando o método calcularMulta(),
     * e verifica se o valor da multa no Leitor é o esperado.
     * @throws LeitorException
     */
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

    /**
     * Esse teste verifica se há atraso na devolução do usuário,
     * ele utiliza o LocalDate.now().minusDays() para "forçar" um atraso na data de entrega do usuário.
     * Em seguida ele chama o método verificaAtrasoDoLeitor() para verificar se o leitor está com empréstimo atrasado.
     * @throws Exception
     */
    @Test
    void vericaAtrasoLeitor() throws Exception {
        assertFalse(DAO.getEmprestimoDAO().verificaAtrasoDoLeitor(bia,LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
        emprestimo1.setDataDevolucao(LocalDate.now().minusDays(1));
        DAO.getEmprestimoDAO().update(emprestimo1);
        assertTrue(DAO.getEmprestimoDAO().verificaAtrasoDoLeitor(bia,LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
    }

    /**
     * Esse teste verifica se o método opera corretamente, pegando o número de livros emprestados.
     * @throws EmprestimoException
     */
    @Test
    void numLivrosEmprestados() throws EmprestimoException {
        assertEquals(3,DAO.getEmprestimoDAO().numLivrosEmprestados());
        /**
         * Esse teste verifica se o método opera corretamente, retornando o número de livros atrasados.
         */
    }
    @Test
    void numLivrosAtrasados() throws EmprestimoException {
        emprestimo1.setDataDevolucao(LocalDate.now().minusDays(1));
        DAO.getEmprestimoDAO().update(emprestimo1);
        assertEquals(1,DAO.getEmprestimoDAO().numLivrosAtrasados());
    }

    /**
     * Esse teste verifica se o método consegue retornar a lista com os livros mais emprestados.
     * @throws LivroException
     * @throws LeitorException
     * @throws ReservaException
     * @throws EmprestimoException
     */

    @Test
    void livroMaisPopular() throws LivroException,LeitorException, ReservaException, EmprestimoException {
        ArrayList<Livro> livrosMaisPopulares = DAO.getEmprestimoDAO().livrosMaisPolulares();
        assertEquals(livrosMaisPopulares.size(),1);
        livro2.setDisponibilidadeEmprestimo(true);
        DAO.getLivroDAO().update(livro2);
        DAO.getEmprestimoDAO().create(new Emprestimo(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),bia,livro2));
        ArrayList<Livro> livrosMaisPopulares2 = DAO.getEmprestimoDAO().livrosMaisPolulares();
        assertEquals(livrosMaisPopulares2.size(),2);

    }

}
