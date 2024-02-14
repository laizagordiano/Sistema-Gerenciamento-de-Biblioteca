package com.example.sistemagerenciamentodebiblioteca.dao;


import com.example.sistemagerenciamentodebiblioteca.dao.administrador.AdministradorDAOFile;
import com.example.sistemagerenciamentodebiblioteca.dao.bibliotecario.BibliotecarioDAOFile;
import com.example.sistemagerenciamentodebiblioteca.dao.emprestimo.EmprestimoDAOFile;
import com.example.sistemagerenciamentodebiblioteca.dao.leitor.LeitorDAOFile;
import com.example.sistemagerenciamentodebiblioteca.dao.livro.LivroDAOFile;
import com.example.sistemagerenciamentodebiblioteca.dao.reserva.ReservaDAOFile;

/**
 * Classe responsável por implementar o Padrão Singleton tem como definição garantir que uma classe,
 * tenha apenas uma instância de si mesma e que forneça um ponto global de acesso a ela;
 *
 * @author Laiza Araujo Gordiano Oliveira
 */
public class DAO {
    private static AdministradorDAOFile administradorDAOinstancia;
    private static BibliotecarioDAOFile bibliotecarioDAOinstancia;
    private static EmprestimoDAOFile emprestimoDAOinstancia;
    private static LivroDAOFile livroDAOinstancia;
    private static LeitorDAOFile leitorDAOinstancia;
    private static ReservaDAOFile reservaDAOinstancia;

    public static AdministradorDAOFile getAdministradorDAO() {
        if(administradorDAOinstancia == null){
            administradorDAOinstancia = new AdministradorDAOFile();
        }
        return administradorDAOinstancia;
    }

    public static BibliotecarioDAOFile getBibliotecarioDAO() {
        if(bibliotecarioDAOinstancia == null){
            bibliotecarioDAOinstancia = new BibliotecarioDAOFile();
        }
        return bibliotecarioDAOinstancia;
    }

    public static EmprestimoDAOFile getEmprestimoDAO() {
        if(emprestimoDAOinstancia == null){
            emprestimoDAOinstancia = new EmprestimoDAOFile();
        }
        return emprestimoDAOinstancia;
    }

    public static LivroDAOFile getLivroDAO() {
        if(livroDAOinstancia == null){
            livroDAOinstancia = new LivroDAOFile();

        }
        return livroDAOinstancia;
    }

    public static LeitorDAOFile getLeitorDAO() {
        if (leitorDAOinstancia == null){
            leitorDAOinstancia = new LeitorDAOFile();
        }
        return leitorDAOinstancia;
    }

    public static ReservaDAOFile getReservaDAO() {
        if (reservaDAOinstancia == null){
            reservaDAOinstancia = new ReservaDAOFile();
        }
        return reservaDAOinstancia;
    }
}