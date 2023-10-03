package tests;

import dao.DAO;
import exceptions.LivroException;
import model.Livro;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;
/**
 * Essa classe é responsavél por realizar os testes das operações do DAO da classe LeitorDAO.
 * @author Laiza Araujo Gordiano Oliveira
 * @see dao.DAO
 * @see exceptions.LeitorException
 * @see model.Livro
 * @see java.util.ArrayList
 * @see org.junit.jupiter.api.AfterEach
 * @see org.junit.jupiter.api.BeforeEach
 * @see org.junit.jupiter.api.Test
 */

public class LivroDAOTest{

    Livro livro1;
    Livro livro2;
    Livro livro3;
    Livro naoCriado;
    /**
     * Esse método é utilizado para configurar o ambiente de teste antes da execução de cada teste.
     * @throws Exception
     */
    @BeforeEach
    void setUp(){
        livro1 = DAO.getLivroDAO().create(new Livro("A rua","José","Brasileira","2577",1989,"Romance","001"));
        livro2 = DAO.getLivroDAO().create(new Livro("Capitães da Areia","Jorge Amado","Gringa","1101",1999,"Ação","023"));
        livro3 = DAO.getLivroDAO().create(new Livro("O Pequeno Principe"," Antoine de Saint-Exupéry","Companhia das Letras","1107",1899,"Romance","156"));
        naoCriado = new Livro("naoCriado","not","not","111",111,"not","455");
    }
    /**
     * Esse método é utilizado para limpar o ambiente de teste após a execução de cada teste.
     * Ele deleta todos os registros de livros criados durante os testes.
     * @throws Exception
     */
    @AfterEach
    void tearDown(){
        DAO.getLivroDAO().deleteMany();
    }
    /**
     * Este método testa a criação de um novo registro de Livro,
     * ele cria um novo livro e armazena o resultado da criação.
     * Obtém o livro esperado com base no ID.
     * Verifica se o livro criado é o esperado.
     * @throws Exception
     */

    @Test
    void create() throws LivroException {
        Livro atual = DAO.getLivroDAO().create(new Livro("Esperado", "Rafael", "BST livros","441",1899,"Tiro","223"));
        Livro esperado = DAO.getLivroDAO().findById(atual.getId());

        assertEquals(atual, esperado,"Esse teste deve passar");
    }

    /**
     * Esse teste verifica se a exceção está passando corretamente.
     * @throws LivroException
     */
    @Test
    void failfindISBN() throws LivroException {
        try{
            DAO.getLivroDAO().findISBN("00");
            fail("Uma exceção deveria ser levantada");
        } catch(Exception e){
            assertEquals(LivroException.SEM_ISBN, e.getMessage());
        }
    }

    /**
     * Esse teste verifica se a operação de encontrar o livro por ISBN funciona corretamente.
     * @throws LivroException
     */
    @Test
    void findISBN() throws LivroException {
        Livro isbnTeste = DAO.getLivroDAO().create(new Livro("A rua","José","Brasileira","2577",1989,"Romance","023"));
        ArrayList<Livro> lista= DAO.getLivroDAO().findISBN("2577");
        assertEquals(lista.size(),2,"Essa mensagem deve aparecer");
        assertEquals(lista.get(0), livro1,"Essa mensagem deve aparecer");
        assertEquals(lista.get(1),isbnTeste,"Essa mensagem deve aparecer");
    }

    /**
     * Esse teste verifica se a excessão é passada quando não é encontrado o livro prlo titulo.
     */
    @Test
    void failFindTitulo(){
        try{
            DAO.getLivroDAO().findTitulo("The Devers");
            fail("Uma exceção deveria ser levantada");
        } catch (LivroException e) {
            assertEquals(LivroException.SEM_EXEMPLAR, e.getMessage());
        }
    }

    /**
     * Esse teste verifica se a operação de encontrar o livro pelo titulo funciona corretamente.
     * @throws LivroException
     */
    @Test
    void findTitulo() throws LivroException {
        Livro tituloTeste = DAO.getLivroDAO().create(new Livro("A rua","José","Brasileira","2577",1989,"Romance","655"));
        ArrayList<Livro> lista= DAO.getLivroDAO().findTitulo("A rua");
        assertEquals(lista.size(),2,"Essa mensagem deve aparecer");
        assertEquals(lista.get(0), livro1,"Essa mensagem deve aparecer");
        assertEquals(lista.get(1),tituloTeste,"Essa mensagem deve aparecer");
    }

    /**
     * Esse teste verifica se a exceção é passada corretamente após não ser encontrada a categoria.
     */
    @Test
    void failFindCategoria(){
        try{
            DAO.getLivroDAO().findCategoria("Inexistente");
            fail("Uma exceção deveria ser levantada");
        } catch (LivroException e) {
            assertEquals(LivroException.SEM_CATEGORIA, e.getMessage());
        }
    }

    /**
     * Esse teste verifica se a operação de encontrar por categoria funciona corretamente.
     * @throws LivroException
     */
    @Test
    void findCategoria() throws LivroException {
        ArrayList<Livro> lista= DAO.getLivroDAO().findCategoria("Romance");
        assertEquals(lista.size(),2,"Essa mensagem deve aparecer");
        assertEquals(lista.get(0), livro1,"Essa mensagem deve aparecer");
    }

    /**
     * Esse teste verifica se é passada a exceção quando não é encontrado o autor.
     */
    @Test
    void failFindAutor(){
        try{
            DAO.getLivroDAO().findAutor("Inexistente");
            fail("Uma exceção deveria ser levantada");
        } catch (LivroException e) {
            assertEquals(LivroException.SEM_AUTOR, e.getMessage());
        }
    }

    /**
     * Esse teste verifica se a operação de encontrar por autor funciona corretamente.
     * @throws LivroException
     */
    @Test
    void findAutor() throws LivroException {
        ArrayList<Livro> lista= DAO.getLivroDAO().findAutor("José");
        assertEquals(lista.size(),1,"Essa mensagem deve aparecer");
        assertEquals(lista.get(0), livro1,"Essa mensagem deve aparecer");
    }

    /**
     * Esse teste verifica se a exceção é passada corretamente após não conseguir deletar um livro.
     */
    @Test
    void failDelete(){
        try{
            DAO.getLivroDAO().delete(naoCriado);
            fail("Uma exceção deveria ser levantada");
        } catch (LivroException e) {
            assertEquals(LivroException.DELETAR,e.getMessage());
        }
    }

    /**
     * Esse teste verifica se o método consegue deletar o livro
     * @throws LivroException
     */

    @Test
    void delete() throws LivroException {
        DAO.getLivroDAO().delete(livro1);
        assertEquals(DAO.getLivroDAO().findMany().size(),2);
    }

    /**
     * Esse teste verifica se a operação deleta todos os livros.
     */
    @Test
    void deleteMany(){
        DAO.getLivroDAO().deleteMany();
        assertEquals(DAO.getLivroDAO().findMany().size(),0);
    }

    /**
     * Esse teste verifica se a exceção é passada corretamente, após não coonseguir atualizar um livro.
     */
    @Test
    void failUpdate(){
        try{
            DAO.getLivroDAO().update(naoCriado);
            fail("Uma exceção deveria ser levantada");
        } catch (LivroException e) {
            assertEquals(LivroException.ATUALIZAR,e.getMessage());
        }
    }

    /**
     * Esse teste verifica se a operação de atualizar o livro funciona corretamente.
     */

    @Test
    void update() throws LivroException {
        livro3.setAutor("Novo Autor");
        livro3.setISBN("1999");
        Livro atualizado = DAO.getLivroDAO().update(livro3);
        assertEquals(atualizado, livro3);
    }

    /**
     * Esse teste verifica se o método consegue encontar todods os livros.
     */
    @Test
    void findMAny(){
        ArrayList<Livro> todosOsLivros= DAO.getLivroDAO().findMany();
        assertEquals(todosOsLivros.size(),3);
    }

    /**
     * Esse teste verifica se a exceção é lançada corretamente quando da erro na procura por ID.
     */
    @Test
    void failFindById(){
        try{
            DAO.getLivroDAO().findById(10);
            fail("Uma exceção deveria ser lançada");
        } catch (LivroException e) {
            assertEquals(LivroException.PROCURAR,e.getMessage());
        }
    }

    /**
     * Este teste verifica se a operação de busca de um Livro pelo ID funciona corretamente.
     * @throws LivroException
     */
    @Test
    void findById() throws LivroException {
        Livro econtrado= DAO.getLivroDAO().findById(2);
        assertEquals(econtrado, livro3);
    }
}