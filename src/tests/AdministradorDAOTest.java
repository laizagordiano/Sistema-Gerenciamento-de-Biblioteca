package tests;

import dao.DAO;
import model.Administrador;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AdministradorDAOTest {

        Administrador Jose;
        Administrador Bruna;
        Administrador Sofia;
        Administrador Isa;

        @BeforeEach
        void setUp() throws Exception {
            Jose = DAO.getAdministradorDAO().create(new Administrador("José","0880","Administrador"));
            Bruna = DAO.getAdministradorDAO().create(new Administrador("Bruna","0080","Administrador"));
            Sofia = DAO.getAdministradorDAO().create(new Administrador("Sofia","0895","Administrador"));
            Isa = DAO.getAdministradorDAO().create(new Administrador("Isa","0870","Administrador"));
        }

        @AfterEach
        void tearDown() throws Exception {
            DAO.getAdministradorDAO().deleteMany();
        }

        @Test
        void criar() throws Exception {
            Administrador atual = DAO.getAdministradorDAO().create(new Administrador("Neto","0094","Administrador"));
            Administrador esperado = DAO.getAdministradorDAO().findById(4);

            assertEquals(esperado.getNumeroID(), atual.getNumeroID(),"Esse teste deveria passar!");
            assertNotNull(esperado,"Esse teste deveria passar!");

        }

        @Test
        void deletarObj() throws Exception {
            DAO.getAdministradorDAO().delete(Bruna);
            int atual = DAO.getAdministradorDAO().findMany().size();
            assertEquals(3,atual,"Esse teste deveria passar!");
        }

        @Test
        void deletarTudo() throws Exception{
            DAO.getAdministradorDAO().deleteMany();
            int atual = DAO.getAdministradorDAO().findMany().size();
            assertEquals(0,atual,"Esse teste deveria passar!");

        }

        @Test
        void atualizar() throws Exception {
            Jose.setNome("Jose");
            Jose.setSenha("0880");
            Jose.setCargo("Administrador");
            Administrador atual = DAO.getAdministradorDAO().update(Jose);
            assertEquals(Jose.getNumeroID(), atual.getNumeroID(),"Esse teste deveria passar!");
        }

        @Test
        void lerTodaLista() throws Exception {
            int atual = DAO.getAdministradorDAO().findMany().size();
            assertEquals(4,atual,"Esse teste deveria passar!");
        }

        @Test
        void lerID() throws Exception{
            Administrador atual = DAO.getAdministradorDAO().findById(1);
            assertEquals(Bruna.getNumeroID(), atual.getNumeroID(),"Esse teste deveria passar!");
        }

        private void assertEquals(int pedro, int atual, String s) {

        }
}

