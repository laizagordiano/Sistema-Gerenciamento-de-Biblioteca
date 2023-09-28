package model;
public class Leitor extends Usuario {
    private int multa;
    private String endereco;
    private String telefone;
    private boolean status;
    public Leitor(String nome, String endereco, String telefone, String senha) {
        super(nome, senha);
        this.endereco = endereco;
        this.telefone = telefone;
        this.status = true;
    }

    public int getMulta() {
        return multa;
    }

    public void setMulta(int multa) {
        this.multa = multa;
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

    public void bloquearLeitor(){
        this.setStatus(false);
    }
    public void desbloquearLeitor(){
        this.setStatus(true);
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
