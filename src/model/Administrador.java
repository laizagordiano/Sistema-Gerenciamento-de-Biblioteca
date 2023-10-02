package model;

public class Administrador extends Usuario{
    private String cargo;
    public Administrador(String nome, String senha, String cargo){
        super(nome, senha,-1);

    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = "Administrador";
    }


    }
