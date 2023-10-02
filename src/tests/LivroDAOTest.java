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

public class LivroDAOTest{

    Livro livro1;
    Livro livro2;
    Livro livro3;
    Livro naoCriado;

    @BeforeEach
    void setUp(){
        livro1 = DAO.getLivroDAO().create(new Livro("A rua","José","Brasileira","2577",1989,"Romance","001"));
        livro2 = DAO.getLivroDAO().create(new Livro("Capitães da Areia","Jorge Amado","Gringa","1101",1999,"Ação","023"));
        livro3 = DAO.getLivroDAO().create(new Livro("O Pequeno Principe"," Antoine de Saint-Exupéry","Companhia das Letras","1107",1899,"Romance","156"));
        naoCriado = new Livro("naoCriado","not","not","111",111,"not","455");
    }
    @AfterEach
    void tearDown(){
        DAO.getLivroDAO().deleteMany();
    }

    @Test
    void create() throws LivroException {
        Livro atual = DAO.getLivroDAO().create(new Livro("Esperado", "Rafael", "BST livros","441",1899,"Tiro","223"));
        Livro esperado = DAO.getLivroDAO().findById(atual.getId());

        assertEquals(atual, esperado,"Esse teste deve passar");
    }


    @Test
    void failfindISBN() throws LivroException {
        try{
            DAO.getLivroDAO().findISBN("00");
            fail("Uma exceção deveria ser levantada");
        } catch(Exception e){
            assertEquals(LivroException.SEM_ISBN, e.getMessage());
        }
    }

    @Test
    void findISBN() throws LivroException {
        Livro isbnTeste = DAO.getLivroDAO().create(new Livro("A rua","José","Brasileira","2577",1989,"Romance","023"));
        ArrayList<Livro> lista= DAO.getLivroDAO().findISBN("2577");
        assertEquals(lista.size(),2,"Essa mensagem deve aparecer");
        assertEquals(lista.get(0), livro1,"Essa mensagem deve aparecer");
        assertEquals(lista.get(1),isbnTeste,"Essa mensagem deve aparecer");
    }

    @Test
    void failFindTitulo(){
        try{
            DAO.getLivroDAO().findTitulo("The Devers");
            fail("Uma exceção deveria ser levantada");
        } catch (LivroException e) {
            assertEquals(LivroException.SEM_EXEMPLAR, e.getMessage());
        }
    }

    @Test
    void findTitulo() throws LivroException {
        Livro tituloTeste = DAO.getLivroDAO().create(new Livro("A rua","José","Brasileira","2577",1989,"Romance","655"));
        ArrayList<Livro> lista= DAO.getLivroDAO().findTitulo("A rua");
        assertEquals(lista.size(),2,"Essa mensagem deve aparecer");
        assertEquals(lista.get(0), livro1,"Essa mensagem deve aparecer");
        assertEquals(lista.get(1),tituloTeste,"Essa mensagem deve aparecer");
    }

    @Test
    void failFindCategoria(){
        try{
            DAO.getLivroDAO().findCategoria("Inexistente");
            fail("Uma exceção deveria ser levantada");
        } catch (LivroException e) {
            assertEquals(LivroException.SEM_CATEGORIA, e.getMessage());
        }
    }

    @Test
    void findCategoria() throws LivroException {
        ArrayList<Livro> lista= DAO.getLivroDAO().findCategoria("Romance");
        assertEquals(lista.size(),2,"Essa mensagem deve aparecer");
        assertEquals(lista.get(0), livro1,"Essa mensagem deve aparecer");
    }

    @Test
    void failFindAutor(){
        try{
            DAO.getLivroDAO().findAutor("Inexistente");
            fail("Uma exceção deveria ser levantada");
        } catch (LivroException e) {
            assertEquals(LivroException.SEM_AUTOR, e.getMessage());
        }
    }
    @Test
    void findAutor() throws LivroException {
        ArrayList<Livro> lista= DAO.getLivroDAO().findAutor("José");
        assertEquals(lista.size(),1,"Essa mensagem deve aparecer");
        assertEquals(lista.get(0), livro1,"Essa mensagem deve aparecer");
    }

    @Test
    void failDelete(){
        try{
            DAO.getLivroDAO().delete(naoCriado);
            fail("Uma exceção deveria ser levantada");
        } catch (LivroException e) {
            assertEquals(LivroException.DELETAR,e.getMessage());
        }
    }

    @Test
    void delete() throws LivroException {
        DAO.getLivroDAO().delete(livro1);
        assertEquals(DAO.getLivroDAO().findMany().size(),2);
    }

    @Test
    void deleteMany(){
        DAO.getLivroDAO().deleteMany();
        assertEquals(DAO.getLivroDAO().findMany().size(),0);
    }

    @Test
    void failUpdate(){
        try{
            DAO.getLivroDAO().update(naoCriado);
            fail("Uma exceção deveria ser levantada");
        } catch (LivroException e) {
            assertEquals(LivroException.ATUALIZAR,e.getMessage());
        }
    }
    @Test
    void update() throws LivroException {
        livro3.setAutor("Novo Autor");
        livro3.setISBN("1999");
        Livro atualizado = DAO.getLivroDAO().update(livro3);
        assertEquals(atualizado, livro3);
    }

    @Test
    void findMAny(){
        ArrayList<Livro> todosOsLivros= DAO.getLivroDAO().findMany();
        assertEquals(todosOsLivros.size(),3);
    }

    @Test
    void failFindById(){
        try{
            DAO.getLivroDAO().findById(10);
            fail("Uma exceção deveria ser lançada");
        } catch (LivroException e) {
            assertEquals(LivroException.PROCURAR,e.getMessage());
        }
    }

    @Test
    void findById() throws LivroException {
        Livro econtrado= DAO.getLivroDAO().findById(2);
        assertEquals(econtrado, livro3);
    }
}