package exceptions;

import model.Reserva;

public class ReservaException extends Exception {
    private Reserva reserva;

    public static final String DELETAR = "Operação de exclusão não realizada";
    public static final String ATUALIZAR = "Operação de atualização não realizada";
    public static final String PROCURAR = "Operação de busca não realizada";

    public ReservaException(String mensagem){
        super(mensagem);
    }

    public ReservaException(String mensagem, Reserva reserva){
        super(mensagem);
        this.reserva = reserva;
    }


}

