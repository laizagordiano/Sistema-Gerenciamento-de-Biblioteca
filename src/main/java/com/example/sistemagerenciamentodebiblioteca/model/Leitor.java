package com.example.sistemagerenciamentodebiblioteca.model;

import com.example.sistemagerenciamentodebiblioteca.dao.DAO;
import com.example.sistemagerenciamentodebiblioteca.exceptions.LeitorException;

import java.time.LocalDate;

/**
 * Essa classe é responsável por criar o Leitor contendo algumas informações, como:
 * Nome;
 * Endereço;
 * Telefone;
 * Senha.
 * @author Laiza Araujo Gordiano Oliveira
 * @see com.example.sistemagerenciamentodebiblioteca.dao.DAO;
 * @see com.example.sistemagerenciamentodebiblioteca.exceptions.LeitorException
 * @see LocalDate
 */
public class Leitor extends Usuario {
    private int multa;
    private String endereco;
    private String telefone;
    private boolean status; // false = bloqueado, true = desbloqueado
    private LocalDate fimDaMulta;
    public Leitor(String nome, String endereco, String telefone, String senha) {
        super(nome, senha, -1);
        this.endereco = endereco;
        this.telefone = telefone;
        this.status = true;
        this.fimDaMulta = null;
    }

    public int getMulta() {
        return multa;
    }

    public void setMulta(int multa) {
        this.multa = multa;
    }

    public LocalDate getFimDaMulta() {
        return fimDaMulta;
    }

    public void setFimDaMulta(LocalDate fimDaMulta) {
        this.fimDaMulta = fimDaMulta;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * Esse método é responsável por bloquear o leitor, alterando o seu status para false.
     * @param leitor
     * @return
     * @throws LeitorException
     */
    public Leitor bloquearLeitor(Leitor leitor) throws LeitorException {
        leitor.setStatus(false);
        DAO.getLeitorDAO().update(leitor);
        return leitor;
    }

    /**
     * Esse método é responsável por desbloquear o leitor, alterando o seu status para true.
     * @param leitor
     * @return
     * @throws LeitorException
     */
    public Leitor desbloquearLeitor(Leitor leitor) throws LeitorException {
        leitor.setStatus(true);
        DAO.getLeitorDAO().update(leitor);
        return leitor;
    }


    @Override
    public String toString() {
        return "Leitor{" +
                "nome=" + super.getNome() +
                ", multa=" + multa +
                ", endereco='" + endereco + '\'' +
                ", telefone='" + telefone + '\'' +
                ", status=" + status +
                '}';
    }
}
