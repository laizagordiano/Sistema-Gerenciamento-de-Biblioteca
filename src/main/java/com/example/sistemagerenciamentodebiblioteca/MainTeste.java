package com.example.sistemagerenciamentodebiblioteca;

import com.example.sistemagerenciamentodebiblioteca.dao.DAO;
import com.example.sistemagerenciamentodebiblioteca.model.Administrador;
import com.example.sistemagerenciamentodebiblioteca.model.Bibliotecario;
import com.example.sistemagerenciamentodebiblioteca.model.Leitor;

public class MainTeste {

    /**
     * Usuarios pré estabelecidos com o id:0 e senha:1234 para testagem do programa.
     * @param args
     */
    public static void main(String[] args) {
        DAO.getLeitorDAO().create(new Leitor("Laiza", "Rua do J","7599999999","1234"));
        DAO.getBibliotecarioDAO().create(new Bibliotecario("Jose","1234","Bibliotecário"));
        DAO.getAdministradorDAO().create(new Administrador("Carlos","1234","Administrador"));
    }
}
