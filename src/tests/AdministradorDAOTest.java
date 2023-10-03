package tests;

import dao.DAO;
import model.Administrador;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Essa classe é responsavél por realizar os testes das operações do DAO da classe AdministradorDAO.
 * @author Laiza Araujo Gordiano Oliveira
 * @see dao.DAO
 * @see model.Administrador
 * @see org.junit.jupiter.api.AfterEach
 * @see org.junit.jupiter.api.BeforeEach
 * @see org.junit.jupiter.api.Test
 */

public class AdministradorDAOTest {

        Administrador jose;
        Administrador bruna;
        Administrador sofia;
        Administrador isa;

    /**
     * Esse método é utilizado para configurar o ambiente de teste antes da execução de cada teste.
     * @throws Exception
     */
    @BeforeEach
        void setUp() throws Exception {
            jose = DAO.getAdministradorDAO().create(new Administrador("José","0880","Administrador"));
            bruna = DAO.getAdministradorDAO().create(new Administrador("Bruna","0080","Administrador"));
            sofia = DAO.getAdministradorDAO().create(new Administrador("Sofia","0895","Administrador"));
            isa = DAO.getAdministradorDAO().create(new Administrador("Isa","0870","Administrador"));
        }

    /**
     * Esse método é utilizado para limpar o ambiente de teste após a execução de cada teste.
     * Ele deleta todos os registros de administradores criados durante os testes.
     * @throws Exception
     */
        @AfterEach
        void tearDown() throws Exception {
            DAO.getAdministradorDAO().deleteMany();
        }

    /**
     * Este método testa a criação de um novo registro de Administrador,
     * ele cria um novo administrador e armazena o resultado da criação.
     * Obtém o administrador esperado com base no ID.
     * Verifica se o ID do administrador criado é o esperado e se o administrador esperado não é nulo.
     * @throws Exception
     */
        @Test
        void create() throws Exception {
            Administrador atual = DAO.getAdministradorDAO().create(new Administrador("Neto","0094","Administrador"));
            Administrador esperado = DAO.getAdministradorDAO().findById(4);

            assertEquals(esperado.getNumeroID(), atual.getNumeroID(),"Esse teste deveria passar!");
            assertNotNull(esperado,"Esse teste deveria passar!");

        }

    /**
     * Este método verifica se a operação de deleção específica funciona corretamente.
     * Remove o administrador utilizando o método delete() do DAO e, em seguida, verifica se o
     * número total de administradores foi reduzido adequadamente.
     * @throws Exception
     */
        @Test
        void delete() throws Exception {
            DAO.getAdministradorDAO().delete(bruna);
            int atual = DAO.getAdministradorDAO().findMany().size();
            assertEquals(3,atual,"Esse teste deveria passar!");
        }

    /**
     * Este método verifica se a operação de deletar todos os registros funciona corretamente.
     * Utiliza o método deleteMany() do DAO para remover todos os administradores e, em seguida, verifica se o
     * número total de administradores é zero após deletados.
     * @throws Exception
     */
        @Test
        void deleteMany() throws Exception{
            DAO.getAdministradorDAO().deleteMany();
            int atual = DAO.getAdministradorDAO().findMany().size();
            assertEquals(0,atual,"Esse teste deveria passar!");

        }

    /**
     * Este método verifica se a operação de atualização específica funciona corretamente.
     * Ele Modifica as informações do Administrador, como nome, senha e cargo, utilizando os métodos set.
     * Utiliza o método update() do DAO para aplicar as mudanças e, em seguida, verifica se o
     * número de ID do administrador atualizado é o mesmo que o esperado.
     * @throws Exception
     */
        @Test
        void update() throws Exception {
            jose.setNome("Jose");
            jose.setSenha("0880");
            jose.setCargo("Administrador");
            Administrador atual = DAO.getAdministradorDAO().update(jose);
            assertEquals(jose.getNumeroID(), atual.getNumeroID(),"Esse teste deveria passar!");
        }

    /**
     * Este método verifica se a operação de busca de todos os registros funciona corretamente.
     * Utiliza o método findMany() do DAO para obter a lista de administradores e, em seguida, verifica se o
     * número total de administradores é o mesmo que o esperado.
     * @throws Exception
     */
        @Test
        void findMany() throws Exception {
            int atual = DAO.getAdministradorDAO().findMany().size();
            assertEquals(4,atual,"Esse teste deveria passar!");
        }

    /**
     * Este método verifica se a operação de busca por ID funciona corretamente.
     * Utiliza o método findById() do DAO para obter o Administrador com ID 1 e, em seguida, verifica se o
     * número de ID do administrador retornado é o mesmo que o esperado.
     * @throws Exception
     */
        @Test
        void findById() throws Exception{
            Administrador atual = DAO.getAdministradorDAO().findById(1);
            assertEquals(bruna.getNumeroID(), atual.getNumeroID(),"Esse teste deveria passar!");
        }

}

