package com.example.sistemagerenciamentodebiblioteca.dao.bibliotecario;

import com.example.sistemagerenciamentodebiblioteca.armazenamento.FileMethods;
import com.example.sistemagerenciamentodebiblioteca.exceptions.BibliotecarioException;
import com.example.sistemagerenciamentodebiblioteca.model.Bibliotecario;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

/**
 * A classe implementa a interface BibliotecarioDAOInterface e fornece métodos
 * para realizar operações relacionadas a bibliotecários, como criação, exclusão, atualização
 * e consulta.
 * @author Laiza Araujo Gordiano Oliveira
 */
public class BibliotecarioDAOFile implements BibliotecarioDAOInterface {
    File arquivo;
    private static final String NAMEFILE= "bibliotecarios";
    public BibliotecarioDAOFile(){
        arquivo = FileMethods.createFile(NAMEFILE);
    }

    public void delete(Bibliotecario obj) throws BibliotecarioException {
        ArrayList<Bibliotecario> listBibliotecario = findMany();
        boolean deletou = listBibliotecario.remove(obj);
        if (!deletou){
            throw new BibliotecarioException(BibliotecarioException.DELETAR,obj);
        }
        FileMethods.sobreescreverArquivo(arquivo, listBibliotecario);
    }

    @Override
    public void deleteMany() {
        FileMethods.apagarConteudoArquivo(arquivo);
    }

    @Override
    public Bibliotecario update(Bibliotecario obj) throws Exception {
        ArrayList<Bibliotecario> listBibliotecario = findMany();
        int index = listBibliotecario.indexOf(obj);
        if (index == -1){
            throw new BibliotecarioException(BibliotecarioException.ATUALIZAR,obj);
        }
        listBibliotecario.set(index, obj);
        FileMethods.sobreescreverArquivo(arquivo,listBibliotecario);
        return obj;
    }
    @Override
    public ArrayList<Bibliotecario> findMany() {
        return  FileMethods.consultarArquivoList(arquivo);
    }

    @Override
    public Bibliotecario findById(int id) throws Exception {
        ArrayList<Bibliotecario> listBibliotecario = findMany();
        for (Bibliotecario bibliotecario: listBibliotecario){
            if (Objects.equals(bibliotecario.getNumeroID(), id)){
                return bibliotecario;
            }
        }
        throw new BibliotecarioException(BibliotecarioException.PROCURAR);
    }


    @Override
    public Bibliotecario create(Bibliotecario obj) {
        ArrayList<Bibliotecario> bibliotecario = findMany() ;
        obj.setNumeroID(this.getProximoID(bibliotecario));
        bibliotecario.add(obj);
        FileMethods.sobreescreverArquivo(arquivo, bibliotecario);
        return obj;
    }
    private int getProximoID(ArrayList<Bibliotecario> bibliotecario){
        int cont =-1;
        for (Bibliotecario l : bibliotecario){
            cont++;
        }
        return (cont+1);
    }
}
