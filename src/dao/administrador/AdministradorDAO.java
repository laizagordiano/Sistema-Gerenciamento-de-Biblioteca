package dao.administrador;

import exceptions.AdministradorException;
import model.Administrador;

import java.util.ArrayList;
import java.util.Objects;

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
    public void delete(Administrador obj) throws AdministradorException {
        boolean deletou = this.listAdministradores.remove(obj);
        if (!deletou){
            throw new AdministradorException(AdministradorException.DELETAR);
        }
    }



    @Override
    public void deleteMany() {
        this.listAdministradores = new ArrayList<>();
        this.proximoID = 0;
    }

    @Override
    public Administrador update(Administrador obj) throws AdministradorException {
        int index = this.listAdministradores.indexOf(obj);
        if (index == -1){
            throw new AdministradorException(AdministradorException.ATUALIZAR);
        }
        this.listAdministradores.set(index, obj);
        return obj;
    }

    @Override
    public ArrayList<Administrador> findMany() {
        return this.listAdministradores;
    }


    @Override
    public Administrador findById(int id) throws AdministradorException {
        for (Administrador administrador : listAdministradores) {
            if (Objects.equals(administrador.getNumeroID(), id)) {
                return administrador;
            }
        }
        throw new AdministradorException(AdministradorException.PROCURAR);
    }
}
