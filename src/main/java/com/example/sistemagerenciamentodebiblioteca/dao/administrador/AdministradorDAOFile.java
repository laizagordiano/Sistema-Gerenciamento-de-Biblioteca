package com.example.sistemagerenciamentodebiblioteca.dao.administrador;

import com.example.sistemagerenciamentodebiblioteca.armazenamento.FileMethods;
import com.example.sistemagerenciamentodebiblioteca.exceptions.AdministradorException;
import com.example.sistemagerenciamentodebiblioteca.exceptions.LivroException;
import com.example.sistemagerenciamentodebiblioteca.model.Administrador;
import com.example.sistemagerenciamentodebiblioteca.model.Livro;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

/**
 * A classe implementa a interface AdministradorDAOInterface e fornece métodos
 * para realizar operações relacionadas a administradores, como criação, exclusão, atualização
 * e consulta.
 * @author Laiza Araujo Gordiano Oliveira
 */
public class AdministradorDAOFile implements AdministradorDAOInterface{
    File arquivo;
    private static final String NAMEFILE= "administradores";
    public AdministradorDAOFile(){
        arquivo = FileMethods.createFile(NAMEFILE);
    }

    public void delete(Administrador obj) throws AdministradorException {
        ArrayList<Administrador> listAdministrador = findMany();
        boolean deletou = listAdministrador.remove(obj);
        if (!deletou){
            throw new AdministradorException(AdministradorException.DELETAR,obj);
        }
        FileMethods.sobreescreverArquivo(arquivo, listAdministrador);
    }

    @Override
    public void deleteMany() {
        FileMethods.apagarConteudoArquivo(arquivo);
    }

    @Override
    public Administrador update(Administrador obj) throws Exception {
        ArrayList<Administrador> listAdministrador = findMany();
        int index = listAdministrador.indexOf(obj);
        if (index == -1){
            throw new AdministradorException(AdministradorException.ATUALIZAR,obj);
        }
        listAdministrador.set(index, obj);
        FileMethods.sobreescreverArquivo(arquivo,listAdministrador);
        return obj;
    }
    @Override
    public ArrayList<Administrador> findMany() {
        return  FileMethods.consultarArquivoList(arquivo);
    }

    @Override
    public Administrador findById(int id) throws Exception {
        ArrayList<Administrador> listAdministrador = findMany();
        for (Administrador administrador: listAdministrador){
            if (Objects.equals(administrador.getNumeroID(), id)){
                return administrador;
            }
        }
        throw new AdministradorException(AdministradorException.PROCURAR);
    }


    @Override
    public Administrador create(Administrador obj) {
        ArrayList<Administrador> administrador = findMany() ;
        obj.setNumeroID(this.getProximoID(administrador));
        administrador.add(obj);
        FileMethods.sobreescreverArquivo(arquivo, administrador);
        return obj;
    }
    private int getProximoID(ArrayList<Administrador> administrador){
        int cont =-1;
        for (Administrador l : administrador){
            cont++;
        }
        return (cont+1);
    }
}

