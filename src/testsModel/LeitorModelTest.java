package testsModel;

import dao.DAO;
import exceptions.LeitorException;
import model.Leitor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Essa classe é responsavél por realizar os testes das operações do DAO da classe LeitorModel.
 * @author Laiza Araujo Gordiano Oliveira
 * @see dao.DAO
 * @see exceptions.LeitorException
 * @see model.Leitor
 * @see org.junit.jupiter.api.AfterEach
 * @see org.junit.jupiter.api.BeforeEach
 * @see org.junit.jupiter.api.Test
 */
public class LeitorModelTest {
    Leitor joao;
    /**
     * Esse método é utilizado para configurar o ambiente de teste antes da execução de cada teste.
     * @throws Exception
     */
    @BeforeEach
    void setUp(){
        joao = DAO.getLeitorDAO().create(new Leitor("João","Conceição","11 22222","0232"));

    }
    /**
     *  Esse método é utilizado para limpar o ambiente de teste após a execução de cada teste.
     *  Ele deleta todos os registros de leitor criados durante os testes.
     * @throws Exception
     */
    @AfterEach
    void tearDown(){
        DAO.getLeitorDAO().deleteMany();
    }

    /**
     * Esse teste é responsável por verificar se a operação de bloquear leitor está funcionando.
     * @throws LeitorException
     */
    @Test
    void bloquearLeitor() throws LeitorException {
        Leitor bloqueado = joao.bloquearLeitor(joao);
        assertFalse(bloqueado.isStatus());
    }

    /**
     * Esse teste é responsável por verificar se a operação de desbloquear leitor está funcionando.
     * @throws LeitorException
     */
    void desbloquearLeitor() throws LeitorException {
        Leitor desbloqueado = joao.desbloquearLeitor(joao);
        assertTrue(desbloqueado.isStatus());
    }
}