package model;

import java.util.Objects;

/**
 * Essa classe é responsável pelas informações na criação de um livro.
 * @author Laiza Araujo Gordiano Oliveira
 * @see java.util.Objects
 */
public class Livro {
    private String titulo;
    private String autor;
    private String editora;
    private String ISBN;
    private Integer anoPublicacao;
    private boolean disponilidadeEmprestimo;
    private boolean reserva;
    private String categoria;
    private int id;
    private String localizacao;

    public Livro(String titulo, String autor, String editora, String ISBN, Integer anoPublicacao, String categoria, String localizacao) {
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.ISBN = ISBN;
        this.anoPublicacao = anoPublicacao;
        this.disponilidadeEmprestimo = true;
        this.reserva = false;
        this.categoria = categoria;
        this.localizacao = localizacao;
    }
    public int hashCode() {
        return Objects.hash(id);
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

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
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
                ", ISBN='" + ISBN + '\'' +
                ", anoPublicacao=" + anoPublicacao +
                ", disponilidadeEmprestimo=" + disponilidadeEmprestimo +
                ", reserva=" + reserva +
                ", categoria='" + categoria + '\'' +
                ", id=" + id +
                ", localizacao='" + localizacao + '\'' +
                '}';
    }
}

