package exceptions;

import model.Administrador;
/** Esta classe é responsável pelas exceções relacionadas ao Administrador
 **/
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

