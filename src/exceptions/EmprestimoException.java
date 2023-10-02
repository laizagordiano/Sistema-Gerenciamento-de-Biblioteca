package exceptions;

import model.Emprestimo;
import model.Livro;

public class EmprestimoException extends Exception{
    private Emprestimo emprestimo;
    public static final String EMPRESTAR = "Operação de emprestimo não realizada";
    public static final String DELETAR = "Operação de exclusão não realizada";
    public static final String ATUALIZAR = "Operação de atualização não realizada";
    public static final String PROCURAR = "Operação de busca não realizada";
    public static final String EMPRESTIMO_ATIVO = "Erro ao encontrar empréstimo ativo do usuário";
    public static final String RENOVACAO = "Renovação não pode ser realizada";
    public static final String BLOQUEADO = "Renovação não pode ser realizada, pois o usuário está bloquado";
    public static final String LIMITE = "Renovação não pode ser realizada, pois o limite de renovações foi atingido";
    public static final String HARESERVA = "Renovação não pode ser realizada, pois ja existe usuário na lista de reservas ";
    public static final String MULTADO = "Renovação não pode ser realizada, pois o usuário possui multa";
    public static final String ATIVOS = "Sem empréstimos ativos";
    public static final String SEM_ATRASADOS = "Não existe livros atrasados";

    public EmprestimoException(String mensagem){
        super(mensagem);
    }

    public EmprestimoException(String mensagem, Emprestimo emprestimo){
        super(mensagem);
        this.emprestimo = emprestimo;
    }


}
