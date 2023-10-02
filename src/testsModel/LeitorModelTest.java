package testsModel;

import dao.DAO;
import exceptions.LeitorException;
import model.Leitor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class LeitorModelTest {
    Leitor joao;
    @BeforeEach
    void setUp(){
        joao = DAO.getLeitorDAO().create(new Leitor("João","Conceição","11 22222","0232"));

    }

    @AfterEach
    void tearDown(){
        DAO.getLeitorDAO().deleteMany();
    }

    @Test
    void bloquearLeitor() throws LeitorException {
        Leitor bloqueado = joao.bloquearLeitor(joao);
        assertFalse(bloqueado.isStatus());
    }

    void desbloquearLeitor() throws LeitorException {
        Leitor desbloqueado = joao.desbloquearLeitor(joao);
        assertTrue(desbloqueado.isStatus());
    }
}