package test.tests;

import com.example.sistemagerenciamentodebiblioteca.dao.DAO;
import com.example.sistemagerenciamentodebiblioteca.exceptions.LeitorException;
import com.example.sistemagerenciamentodebiblioteca.model.Leitor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Essa classe é responsavél por realizar os testes das operações do DAO da classe LeitorDAO.
 * @author Laiza Araujo Gordiano Oliveira
 * @see com.example.sistemagerenciamentodebiblioteca.dao.DAO
 * @see com.example.sistemagerenciamentodebiblioteca.exceptions.LeitorException
 * @see com.example.sistemagerenciamentodebiblioteca.model.Leitor
 * @see ArrayList
 * @see org.junit.jupiter.api.AfterEach
 * @see org.junit.jupiter.api.BeforeEach
 * @see org.junit.jupiter.api.Test
 */
public class LeitorDAOTest {
    Leitor joao;
    Leitor pablo;
    Leitor cris;
    Leitor naoExiste;
    /**
     * Esse método é utilizado para configurar o ambiente de teste antes da execução de cada teste.
     * @throws Exception
     */
    @BeforeEach
    void setUp(){
        joao = DAO.getLeitorDAO().create(new Leitor("João","Conceição","11 22222","0232"));
        pablo = DAO.getLeitorDAO().create(new Leitor("Pablo","Feira VI","22 22222","1214563"));
        cris = DAO.getLeitorDAO().create(new Leitor("Cris","Papagaio","33 33333","143225"));
        naoExiste = new Leitor("notExisted","notEndereço","notTel","03698");
    }
    /**
     * Esse método é utilizado para limpar o ambiente de teste após a execução de cada teste.
     * Ele deleta todos os registros de leitores criados durante os testes.
     * @throws Exception
     */
    @AfterEach
    void tearDown(){
        DAO.getLeitorDAO().deleteMany();
    }

    /**
     * Este método testa a criação de um novo registro de Leitor,
     * ele cria um novo leitor e armazena o resultado da criação.
     * Obtém o leitor esperado com base no ID.
     * Verifica se o leitor criado é o esperado.
     * @throws Exception
     */
    @Test
    void create() throws Exception {
        Leitor atual = DAO.getLeitorDAO().create(new Leitor("Alice", "Senador, 15", "75988562315","441"));
        Leitor esperado = DAO.getLeitorDAO().findById(atual.getNumeroID());

        assertEquals(atual, esperado,"Esse teste deve passar");
    }

    /**
     * Este teste verifica se a operação de exclusão de um Leitor que não existe elança a exceção correta.
     * Tenta excluir o Leitor utilizando o método delete() do DAO e espera que uma exceção do tipo LeitorException
     * seja lançada, com a mensagem correspondente a "Operação de exclusão não realizada".
     */
    @Test
    void failDelete() {
        try {
            DAO.getLeitorDAO().delete(naoExiste);
            fail("Uma exceção deveria ser levantada");
        } catch (LeitorException e) {
            assertEquals(LeitorException.DELETAR, e.getMessage());
        }
    }

    /**
     * Este teste verifica se a operação de exclusão de todos os Leitores funciona corretamente.
     * Utiliza o método deleteMany() do DAO para excluir todos os Leitores e, em seguida, verifica se a quantidade
     * de Leitores é zero utilizando o método findMany().
     */
    @Test
    void deleteMany(){
        DAO.getLeitorDAO().deleteMany();
        assertEquals(DAO.getLeitorDAO().findMany().size(),0);
    }

    /**
     * Este teste verifica se a operação de atualização de um Leitor que não existe e lança a exceção correta.
     * Tenta atualizar o Leitor 'naoExiste' utilizando o método update() do DAO
     * e espera que uma exceção do tipo LeitorException seja lançada, com a mensagem correspondente
     * a "Operação de atualização não realizada".
     */
    @Test
    void failUpdate(){
        try{
            DAO.getLeitorDAO().update(naoExiste);
            fail("Uma exceção deveria ser levantada");
        } catch (LeitorException e) {
            assertEquals(LeitorException.ATUALIZAR,e.getMessage());
        }
    }

    /**
     * Esse teste verifica se a operação de atualizar o leitor funcions corretamente.
     * @throws LeitorException
     */
    @Test
    void update() throws LeitorException {
        joao.setNome("Jhon");
        joao.setEndereco("Rio Branco");
        Leitor atualizado = DAO.getLeitorDAO().update(joao);
        assertEquals(atualizado, joao);
    }

    /**
     * Este teste verifica se a operação de busca de todos os Leitores no banco de dados funciona corretamente.
     * Utiliza o método findMany() do DAO para obter a lista de todos os Leitores e, em seguida, verifica se a
     * quantidade de Leitores na lista é a esperada.
     */
    @Test
    void findMAny(){
        ArrayList<Leitor> todosOsLeitores= DAO.getLeitorDAO().findMany();
        assertEquals(todosOsLeitores.size(),3);
    }

    /**
     * Este teste verifica se a operação de busca de um Leitor pelo ID, quando o ID não existe, lança a exceção
     * correta. Tenta buscar o Leitor com o ID utilizando o método findById() do DAO
     * e espera que uma exceção do tipo LeitorException seja lançada,
     * com a mensagem correspondente a "Operação de busca não realizada".
     */
    @Test
    void failFindById(){
        try{
            DAO.getLeitorDAO().findById(10);
            fail("Uma exceção deveria ser lançada");
        } catch (LeitorException e) {
            assertEquals(LeitorException.PROCURAR,e.getMessage());
        }
    }

    /**
     * Este método verifica se a operação de busca de um Leitor pelo ID funciona corretamente.
     * @throws LeitorException
     */
    @Test
    void findById() throws LeitorException {
        Leitor econtrado = DAO.getLeitorDAO().findById(2);
        assertEquals(econtrado, cris);
    }

}
