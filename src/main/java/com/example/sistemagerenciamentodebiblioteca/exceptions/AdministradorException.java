package com.example.sistemagerenciamentodebiblioteca.exceptions;

import com.example.sistemagerenciamentodebiblioteca.model.Administrador;
/** Esta classe é responsável por lidar com exceções relacionadas ao objeto Administrador.
 * Ela estende a classe Exception, proporcionando meios de capturar e tratar erros específicos.
 * A classe possui três mensagens padrão para diferentes operações (deletar, atualizar, procurar).
 */

public class AdministradorException extends Exception{
    private Administrador administrador;
    public static final String DELETAR = "Operação de exclusão não realizada";
    public static final String ATUALIZAR = "Operação de atualização não realizada";
    public static final String PROCURAR = "Operação de busca não realizada";

    public AdministradorException(String mensagem){
        super(mensagem);
    }

    public AdministradorException(String mensagem, Administrador administrador){
        super(mensagem);
        this.administrador = administrador;
    }


}

