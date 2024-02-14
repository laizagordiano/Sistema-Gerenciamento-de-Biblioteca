package com.example.sistemagerenciamentodebiblioteca.exceptions;


import com.example.sistemagerenciamentodebiblioteca.model.Leitor;
/**
 * Esta classe é responsável por lidar com exceções relacionadas ao objeto Leitor.
 * Ela estende a classe Exception, proporcionando meios de capturar e tratar erros específicos.
 * A classe possui mensagens padrão para diversas operações (deletar, atualizar, procurar, etc.).
 */

public class LeitorException extends Exception{
    private Leitor leitor;
    public static final String DELETAR = "Operação de exclusão não realizada";
    public static final String ATUALIZAR = "Operação de atualização não realizada";
    public static final String PROCURAR = "Operação de busca não realizada";
    public static final String MULTADO = "O usuário não está apto para o empréstimo";
    public static final  String LIMITE = "Usuário atingiu o limite de empréstimo";
    public LeitorException(String mensagem){
        super(mensagem);
    }

    public LeitorException(String mensagem, Leitor leitor){
        super(mensagem);
        this.leitor = leitor;
    }


}

