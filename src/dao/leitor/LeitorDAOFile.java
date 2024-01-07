package dao.leitor;

import armazenamento.FileMethods;
import exceptions.LeitorException;
import exceptions.LivroException;
import model.Leitor;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

/**
 * A classe implementa a interface LeitorDAOInterface e fornece métodos
 * para realizar operações relacionadas a leitores, como criação, exclusão, atualização
 * e consulta.
 * @author Laiza Araujo Gordiano Oliveira
 */
public class LeitorDAOFile implements LeitorDAOInterface {
    File arquivo;
    private static final String NAMEFILE= "leitores";
    public LeitorDAOFile(){
        arquivo = FileMethods.createFile(NAMEFILE);
    }

    public void delete(Leitor obj) throws LeitorException {
        ArrayList<Leitor> listLeitor = findMany();
        boolean deletou = listLeitor.remove(obj);
        if (!deletou){
            throw new LeitorException(LeitorException.DELETAR,obj);
        }
        FileMethods.sobreescreverArquivo(arquivo, listLeitor);
    }

    @Override
    public void deleteMany() {
        FileMethods.apagarConteudoArquivo(arquivo);
    }

    @Override
    public Leitor update(Leitor obj) throws LeitorException {
        ArrayList<Leitor> listLeitor = findMany();
        int index = listLeitor.indexOf(obj);
        if (index == -1){
            throw new LeitorException(LeitorException.ATUALIZAR,obj);
        }
        listLeitor.set(index, obj);
        FileMethods.sobreescreverArquivo(arquivo,listLeitor);
        return obj;
    }
    @Override
    public ArrayList<Leitor> findMany() {
        return  FileMethods.consultarArquivoList(arquivo);
    }

    @Override
    public Leitor findById(int id) throws LeitorException {
        ArrayList<Leitor> listLeitor = findMany();
        for (Leitor leitor: listLeitor){
            if (Objects.equals(leitor.getNumeroID(), id)){
                return leitor;
            }
        }
        throw new LeitorException(LeitorException.PROCURAR);
    }

    @Override
    public Leitor create(Leitor obj) {
        ArrayList<Leitor> leitor = findMany() ;
        obj.setNumeroID(this.getProximoID(leitor));
        leitor.add(obj);
        FileMethods.sobreescreverArquivo(arquivo, leitor);
        return obj;
    }
    private int getProximoID(ArrayList<Leitor> leitor){
        int cont =-1;
        for (Leitor l : leitor){
            cont++;
        }
        return (cont+1);
    }
}
