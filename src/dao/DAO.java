package dao;


import dao.administrador.AdministradorDAO;
import dao.bibliotecario.BibliotecarioDAO;
import dao.emprestimo.EmprestimoDAO;
import dao.leitor.LeitorDAO;
import dao.livro.LivroDAO;
import dao.reserva.ReservaDAO;
/**
 * Classe responsável por implementar o Padrão Singleton tem como definição garantir que uma classe,
 * tenha apenas uma instância de si mesma e que forneça um ponto global de acesso a ela;
 *
 * @author Laiza Araujo Gordiano Oliveira
 */
public class DAO {
    private static AdministradorDAO administradorDAOinstancia;
    private static BibliotecarioDAO bibliotecarioDAOinstancia;
    private static EmprestimoDAO emprestimoDAOinstancia;
    private static LivroDAO livroDAOinstancia;
    private static LeitorDAO leitorDAOinstancia;
    private static ReservaDAO reservaDAOinstancia;

    public static AdministradorDAO getAdministradorDAO() {
        if(administradorDAOinstancia == null){
            administradorDAOinstancia = new AdministradorDAO();
        }
        return administradorDAOinstancia;
    }

    public static BibliotecarioDAO getBibliotecarioDAO() {
        if(bibliotecarioDAOinstancia == null){
            bibliotecarioDAOinstancia = new BibliotecarioDAO();
        }
        return bibliotecarioDAOinstancia;
    }

    public static EmprestimoDAO getEmprestimoDAO() {
        if(emprestimoDAOinstancia == null){
            emprestimoDAOinstancia = new EmprestimoDAO();
        }
        return emprestimoDAOinstancia;
    }

    public static LivroDAO getLivroDAO() {
        if(livroDAOinstancia == null){
            livroDAOinstancia = new LivroDAO();

        }
        return livroDAOinstancia;
    }

    public static LeitorDAO getLeitorDAO() {
        if (leitorDAOinstancia == null){
            leitorDAOinstancia = new LeitorDAO();
        }
        return leitorDAOinstancia;
    }

    public static ReservaDAO getReservaDAO() {
        if (reservaDAOinstancia == null){
            reservaDAOinstancia = new ReservaDAO();
        }
        return reservaDAOinstancia;
    }
}