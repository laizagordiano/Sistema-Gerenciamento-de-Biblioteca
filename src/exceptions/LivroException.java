package exceptions;

import model.Livro;

public class LivroException extends Exception{
    private Livro livro;
    public static final String DELETAR = "Operação de exclusão não realizada";
    public static final String ATUALIZAR = "Operação de atualização não realizada";
    public static final String PROCURAR = "Operação de busca não realizada";
    public static final String SEM_ISBN = "ISBN não encontrado";
    public static final String SEM_AUTOR = "Autor não encontrado";
    public static final String SEM_EXEMPLAR = "Sem exemplares desse livro";
    public static final String SEM_CATEGORIA = "Categoria não encontrada";
    public static final String INDISPONIVEL = "Livro não disponível";
    public LivroException(String mensagem){
        super(mensagem);
    }

    public LivroException(String mensagem, Livro livro){
        super(mensagem);
        this.livro = livro;
    }


}

