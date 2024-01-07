package tests;

import dao.DAO;
import model.Bibliotecario;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Essa classe é responsavél por realizar os testes das operações do DAO da classe BibliotecarioDAO.
 * @author Laiza Araujo Gordiano Oliveira
 * @see dao.DAO
 * @see model.Bibliotecario
 * @see org.junit.jupiter.api.AfterEach
 * @see org.junit.jupiter.api.BeforeEach
 * @see org.junit.jupiter.api.Test
 */
public class BibliotecarioDAOTest {
    Bibliotecario jonas;
    Bibliotecario bianca;
    Bibliotecario saulo;
    Bibliotecario bruno;

    /**
     * Esse método é utilizado para configurar o ambiente de teste antes da execução de cada teste.
     *
     * @throws Exception
     */
    @BeforeEach
    void setUp() throws Exception {
        jonas = DAO.getBibliotecarioDAO().create(new Bibliotecario("Jonas", "0880", "Bibliotecario"));
        bianca = DAO.getBibliotecarioDAO().create(new Bibliotecario("Bianca", "0080", "Bibliotecario"));
        saulo = DAO.getBibliotecarioDAO().create(new Bibliotecario("Saulo", "0895", "Bibliotecario"));
        bruno = DAO.getBibliotecarioDAO().create(new Bibliotecario("Bruno", "0870", "Bibliotecario"));
    }

    /**
     * Esse método é utilizado para limpar o ambiente de teste após a execução de cada teste.
     * Ele deleta todos os registros de bibliotecarios criados durante os testes.
     * @throws Exception
     */
    @AfterEach
    void tearDown() throws Exception {
        DAO.getBibliotecarioDAO().deleteMany();
    }

    /**
     * Este método testa a criação de um novo registro de Bibliotecário,
     * ele cria um novo bibliotecário e armazena o resultado da criação.
     * Obtém o bibliotecário esperado com base no ID.
     * Verifica se o ID do bibliotecário criado é o esperado e se o bibliotecário esperado não é nulo.
     * @throws Exception
     */
    @Test
    void create() throws Exception {
        Bibliotecario atual = DAO.getBibliotecarioDAO().create(new Bibliotecario("Neto", "0094", "Administrador"));
        Bibliotecario esperado = DAO.getBibliotecarioDAO().findById(4);

        assertEquals(esperado.getNumeroID(), atual.getNumeroID(), "Esse teste deveria passar!");
        assertNotNull(esperado, "Esse teste deveria passar!");

    }

    /**
     * Este método verifica se a operação de deleção específica funciona corretamente.
     * Remove o bibliotecário utilizando o método delete() do DAO e, em seguida, verifica se o
     * número total de bibliotecários foi reduzido adequadamente.
     *
     * @throws Exception
     */

    @Test
    void delete() throws Exception {
        DAO.getBibliotecarioDAO().delete(bianca);
        int atual = DAO.getBibliotecarioDAO().findMany().size();
        assertEquals(3, atual, "Esse teste deveria passar!");
    }

    /**
     * Este método verifica se a operação de deletar todos os registros funciona corretamente.
     * Utiliza o método deleteMany() do DAO para remover todos os bibliotecários e, em seguida, verifica se o
     * número total de bibliotecários é zero após deletados.
     *
     * @throws Exception
     */
    @Test
    void deleteMany() throws Exception {
        DAO.getBibliotecarioDAO().deleteMany();
        int atual = DAO.getBibliotecarioDAO().findMany().size();
        assertEquals(0, atual, "Esse teste deveria passar!");

    }

    /**
     * Este método verifica se a operação de atualização específica funciona corretamente.
     * Ele Modifica as informações do Bibliotecário, como nome, senha e cargo, utilizando os métodos set.
     * Utiliza o método update() do DAO para aplicar as mudanças e, em seguida, verifica se o
     * número de ID do bibliotecário atualizado é o mesmo que o esperado.
     *
     * @throws Exception
     */
    @Test
    void update() throws Exception {
        jonas.setNome("Jonas");
        jonas.setSenha("0880");
        jonas.setCargo("Bibliotecario");
        Bibliotecario atual = DAO.getBibliotecarioDAO().update(jonas);
        assertEquals(jonas.getNumeroID(), atual.getNumeroID(), "Esse teste deveria passar!");
    }

    /**
     * Este método verifica se a operação de busca de todos os registros funciona corretamente.
     * Utiliza o método findMany() do DAO para obter a lista de bibliotecárioss e, em seguida, verifica se o
     * número total de bibliotecários é o mesmo que o esperado.
     *
     * @throws Exception
     */
    @Test
    void findMany() throws Exception {
        int atual = DAO.getBibliotecarioDAO().findMany().size();
        assertEquals(4, atual, "Esse teste deveria passar!");
    }

    /**
     * Este método verifica se a operação de busca por ID funciona corretamente.
     * Utiliza o método findById() do DAO para obter o bibliotecário com ID 1 e, em seguida, verifica se o
     * número de ID do bibliotecário retornado é o mesmo que o esperado.
     *
     * @throws Exception
     */
    @Test
    void findByID() throws Exception {
        Bibliotecario atual = DAO.getBibliotecarioDAO().findById(1);
        assertEquals(bianca.getNumeroID(), atual.getNumeroID(), "Esse teste deveria passar!");
    }

}
