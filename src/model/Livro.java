package model;

import java.util.Objects;

public class Livro {
    private String titulo;
    private String autor;
    private String editora;
    private String isbn;
    private Integer anoPublicacao;
    private boolean disponilidadeEmprestimo;
    private boolean reserva;
    private String categoria;
    private int id;

    public Livro(String titulo, String autor, String editora, String isbn, Integer anoPublicacao, String categoria) {
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.isbn = isbn;
        this.anoPublicacao = anoPublicacao;
        this.disponilidadeEmprestimo = true;
        this.reserva = false;
        this.categoria = categoria;
    }
    public int hashCode() {
        return Objects.hash(getId());
    }
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(Integer anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public boolean getDisponilidadeEmprestimo() {
        return disponilidadeEmprestimo;
    }

    public void setDisponilidadeEmprestimo(boolean disponilidadeEmprestimo) {
        this.disponilidadeEmprestimo = disponilidadeEmprestimo;
    }

    public boolean getReserva() {
        return reserva;
    }

    public void setReserva(boolean reserva) {
        this.reserva = reserva;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", editora='" + editora + '\'' +
                ", isbn=" + isbn +
                ", anoPublicacao=" + anoPublicacao +
                ", disponilidadeEmprestimo=" + disponilidadeEmprestimo +
                ", reserva=" + reserva +
                ", categoria='" + categoria + '\'' +
                '}';
    }


}
