package model;

public class Bibliotecario extends Usuario{
    private String cargo;
    public Bibliotecario(String nome, String senha, String cargo) {
        super(nome, senha);
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
