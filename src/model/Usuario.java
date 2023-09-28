package model;

import java.util.Objects;

public class Usuario {
    private String nome;
    private String endereco;
    private String telefone;
    private int numeroID;
    private String senha;
    private String cargo;
    private boolean status;

    public Usuario(String nome, String endereco, String telefone, String senha) {
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.status = true;
    }
    public Usuario(String nome, String senha, String cargo){
        this.nome = nome;
        this.senha = senha;
        this.cargo = cargo;

    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumeroID());
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getNumeroID() {
        return numeroID;
    }

    public void setNumeroID(int numeroID) {
        this.numeroID = numeroID;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", telefone='" + telefone + '\'' +
                ", numeroID=" + numeroID +
                ", senha='" + senha + '\'' +
                ", cargo='" + cargo + '\'' +
                ", status=" + status +
                '}';
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
