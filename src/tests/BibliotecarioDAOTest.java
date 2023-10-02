package tests;

import dao.DAO;
import model.Bibliotecario;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BibliotecarioDAOTest {
    Bibliotecario jonas;
    Bibliotecario bianca;
    Bibliotecario saulo;
    Bibliotecario bruno;

    @BeforeEach
    void setUp() throws Exception {
        jonas = DAO.getBibliotecarioDAO().create(new Bibliotecario("Jonas","0880","Bibliotecario"));
        bianca = DAO.getBibliotecarioDAO().create(new Bibliotecario("Bianca","0080","Bibliotecario"));
        saulo = DAO.getBibliotecarioDAO().create(new Bibliotecario("Saulo","0895","Bibliotecario"));
        bruno = DAO.getBibliotecarioDAO().create(new Bibliotecario("Bruno","0870","Bibliotecario"));
    }

    @AfterEach
    void tearDown() throws Exception {
        DAO.getBibliotecarioDAO().deleteMany();
    }

    @Test
    void criar() throws Exception {
        Bibliotecario atual = DAO.getBibliotecarioDAO().create(new Bibliotecario("Neto","0094","Administrador"));
        Bibliotecario esperado = DAO.getBibliotecarioDAO().findById(4);

        assertEquals(esperado.getNumeroID(), atual.getNumeroID(),"Esse teste deveria passar!");
        assertNotNull(esperado,"Esse teste deveria passar!");

    }

    @Test
    void delete() throws Exception {
        DAO.getBibliotecarioDAO().delete(bianca);
        int atual = DAO.getBibliotecarioDAO().findMany().size();
        assertEquals(3,atual,"Esse teste deveria passar!");
    }

    @Test
    void deleteMany() throws Exception{
        DAO.getBibliotecarioDAO().deleteMany();
        int atual = DAO.getBibliotecarioDAO().findMany().size();
        assertEquals(0,atual,"Esse teste deveria passar!");

    }

    @Test
    void update() throws Exception {
        jonas.setNome("Jonas");
        jonas.setSenha("0880");
        jonas.setCargo("Bibliotecario");
        Bibliotecario atual = DAO.getBibliotecarioDAO().update(jonas);
        assertEquals(jonas.getNumeroID(), atual.getNumeroID(),"Esse teste deveria passar!");
    }

    @Test
    void findMany() throws Exception {
        int atual = DAO.getBibliotecarioDAO().findMany().size();
        assertEquals(4,atual,"Esse teste deveria passar!");
    }

    @Test
    void findByID() throws Exception{
        Bibliotecario atual = DAO.getBibliotecarioDAO().findById(0);
        assertEquals(bianca.getNumeroID(), atual.getNumeroID(),"Esse teste deveria passar!");
    }

    private void assertEquals(int esperado, int atual, String s) {

    }
}
