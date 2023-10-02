package model;

import dao.DAO;
import exceptions.LeitorException;

import java.time.LocalDate;

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

    public Leitor bloquearLeitor(Leitor leitor) throws LeitorException {
        leitor.setStatus(false);
        DAO.getLeitorDAO().update(leitor);
        return leitor;
    }
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
