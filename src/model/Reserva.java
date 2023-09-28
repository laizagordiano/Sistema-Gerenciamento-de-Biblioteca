package model;

public class Reserva {
    private Leitor leitor;
    private Livro livro;

    public Reserva( Leitor leitor, Livro livro ){
        this.setLeitor(leitor);
        this.setLivro(livro);
        livro.setReserva(true);
    }

    public Leitor getLeitor() {
        return leitor;
    }

    public void setLeitor(Leitor leitor) {
        this.leitor = leitor;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "leitor=" + leitor +
                ", livro=" + livro +
                '}';
    }
}