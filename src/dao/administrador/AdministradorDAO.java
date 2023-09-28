package dao.administrador;

import model.Administrador;

import java.util.ArrayList;

public class AdministradorDAO implements AdministradorDAOInterface{
    private ArrayList<Administrador> listAdministradores;
    private int proximoID;

    public AdministradorDAO() {
        this.listAdministradores = new ArrayList<>();
        this.proximoID = 0;
    }
    private int getProximoID() {
        return this.proximoID++;
    }
    @Override
    public Administrador create(Administrador obj) {
        obj.setNumeroID(this.getProximoID());
        this.listAdministradores.add(obj);
        return obj;
    }

    @Override
    public void delete(Administrador obj) throws Exception {
        this.listAdministradores.remove(obj);
    }



    @Override
    public void deleteMany() {
        this.listAdministradores = new ArrayList<>();
        this.proximoID = 0;
    }

    @Override
    public Administrador update(Administrador obj) throws Exception {
        int index = this.listAdministradores.indexOf(obj);
        this.listAdministradores.set(index, obj);
        return obj;
    }

    @Override
    public ArrayList<Administrador> findMany() {
        return this.listAdministradores;
    }


    @Override
    public Administrador findById(int id) throws Exception {
        for (Administrador administrador : this.listAdministradores) {
            if (administrador.getNumeroID() == id) {
                return administrador;
            }
        }
        return null;
    }
}
