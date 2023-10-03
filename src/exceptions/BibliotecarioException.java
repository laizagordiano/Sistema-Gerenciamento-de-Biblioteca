package exceptions;

import model.Bibliotecario;
import model.Emprestimo;

/** Esta classe é responsável por lidar com exceções relacionadas ao objeto Bibliotecário.
 * Ela estende a classe Exception, proporcionando meios de capturar e tratar erros específicos.
 * A classe possui três mensagens padrão para diferentes operações (deletar, atualizar, procurar).
 */
public class BibliotecarioException extends Exception{
    private Bibliotecario bibliotecario;
    public static final String DELETAR = "Operação de exclusão não realizada";
    public static final String ATUALIZAR = "Operação de atualização não realizada";
    public static final String PROCURAR = "Operação de busca não realizada";

    public BibliotecarioException(String mensagem){
        super(mensagem);
    }

    public BibliotecarioException(String mensagem, Bibliotecario bibliotecario){
        super(mensagem);
        this.bibliotecario = bibliotecario;
    }


}

