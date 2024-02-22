package com.example.sistemagerenciamentodebiblioteca.armazenamento;

import com.example.sistemagerenciamentodebiblioteca.model.Usuario;

public abstract class LoginOn {

    private static Usuario session;

    public static Usuario getUserInSession(){
        return session;
    }
    public static void loginUser(Usuario user){
        session = user;
    }

    public static void logoutUser(){
        session = null;
    }

}

