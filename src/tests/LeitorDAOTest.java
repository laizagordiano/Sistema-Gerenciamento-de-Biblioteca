package tests;

import dao.DAO;
import exceptions.LeitorException;
import model.Leitor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class LeitorDAOTest {
    Leitor joao;
    Leitor pablo;
    Leitor cris;
    Leitor naoExiste;

    @BeforeEach
    void setUp(){
        joao = DAO.getLeitorDAO().create(new Leitor("João","Conceição","11 22222","0232"));
        pablo = DAO.getLeitorDAO().create(new Leitor("Pablo","Feira VI","22 22222","1214563"));
        cris = DAO.getLeitorDAO().create(new Leitor("Cris","Papagaio","33 33333","143225"));
        naoExiste = new Leitor("notExisted","notEndereço","notTel","03698");
    }

    @AfterEach
    void tearDown(){
        DAO.getLeitorDAO().deleteMany();
    }

    @Test
    void create() throws Exception {
        Leitor atual = DAO.getLeitorDAO().create(new Leitor("Alice", "Senador, 15", "75988562315","441"));
        Leitor esperado = DAO.getLeitorDAO().findById(atual.getNumeroID());

        assertEquals(atual, esperado,"Esse teste deve passar");
    }
    @Test
    void failDelete() {
        try {
            DAO.getLeitorDAO().delete(naoExiste);
            fail("Uma exceção deveria ser levantada");
        } catch (LeitorException e) {
            assertEquals(LeitorException.DELETAR, e.getMessage());
        }
    }
    @Test
    void deleteMany(){
        DAO.getLeitorDAO().deleteMany();
        assertEquals(DAO.getLeitorDAO().findMany().size(),0);
    }
    @Test
    void failUpdate(){
        try{
            DAO.getLeitorDAO().update(naoExiste);
            fail("Uma exceção deveria ser levantada");
        } catch (LeitorException e) {
            assertEquals(LeitorException.ATUALIZAR,e.getMessage());
        }
    }
    @Test
    void update() throws LeitorException {
        joao.setNome("Jhon");
        joao.setEndereco("Rio Branco");
        Leitor atualizado = DAO.getLeitorDAO().update(joao);
        assertEquals(atualizado, joao);
    }

    @Test
    void findMAny(){
        ArrayList<Leitor> todosOsLeitores= DAO.getLeitorDAO().findMany();
        assertEquals(todosOsLeitores.size(),3);
    }
    @Test
    void failFindById(){
        try{
            DAO.getLeitorDAO().findById(10);
            fail("Uma exceção deveria ser lançada");
        } catch (LeitorException e) {
            assertEquals(LeitorException.PROCURAR,e.getMessage());
        }
    }

    @Test
    void findById() throws LeitorException {
        Leitor econtrado = DAO.getLeitorDAO().findById(2);
        assertEquals(econtrado, cris);
    }

}
