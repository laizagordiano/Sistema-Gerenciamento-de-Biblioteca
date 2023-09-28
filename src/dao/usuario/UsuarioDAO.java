package dao.usuario;

import model.Leitor;
import model.Usuario;

import java.util.ArrayList;

public class UsuarioDAO implements UsuarioDAOInterface{
    private ArrayList<Usuario> listUsuarios;
    private int proximoID;
    public UsuarioDAO() {
        this.listUsuarios = new ArrayList<>();
        this.proximoID = 0;
    }
    private int getProximoID() {
        return this.proximoID++;
    }
    @Override
    public Usuario create(Usuario obj) {
        this.listUsuarios.add(obj);
        return obj;
    }

    @Override
    public void delete(Usuario obj) throws Exception {
        this.listUsuarios.remove(obj);
    }

    @Override
    public void deleteMany() {
        this.listUsuarios = new ArrayList<>();
        this.proximoID = 0;

    }

    @Override
    public Usuario update(Usuario obj) throws Exception {
        int index = this.listUsuarios.indexOf(obj);
        this.listUsuarios.set(index, obj);
        return obj;
    }

    @Override
    public ArrayList<Usuario> findMany() {
        return this.listUsuarios;
    }

    @Override
    public Usuario findById(int id) throws Exception {
        for (Usuario usuario : this.listUsuarios) {
            if (usuario.getNumeroID() == id) {
                return usuario;
            }
        }
        return null;
    }
}
