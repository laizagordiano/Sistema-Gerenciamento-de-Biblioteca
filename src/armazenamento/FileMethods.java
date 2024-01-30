package armazenamento;

import java.io.*;
import java.util.ArrayList;
/**
 * A classe FileMethods fornece métodos para trabalhar com arquivos e operações de arquivos.
 * Inclui métodos para criar, ler e escrever objetos ArrayList em arquivos.
 * @author Laiza Araujo Gordiano Oliveira
 */
public class FileMethods {
    /**
     * Diretório base para operações de arquivo.
     */
    public static final File DIRECTORY = new File("cache");

    /**
     * Extensão padrão para arquivos criados.
     */
    public static final String EXTENSAO = ".mh";

    /**
     * Cria um novo arquivo no diretório base.
     *
     * @param nomeArquivo Nome do arquivo a ser criado.
     * @return O arquivo recém-criado ou null em caso de erro.
     */
    public static File createFile(String nomeArquivo) {
        if (!DIRECTORY.exists())
            DIRECTORY.mkdir();
        File arquivo = new File(DIRECTORY.getName() + "/" + nomeArquivo + EXTENSAO);
        if (!arquivo.exists()){
            try {
                arquivo.createNewFile();
            } catch (IOException e) {
                return null;
            }
        }
        return arquivo;
    }

    /**
     * Lê o conteúdo de um arquivo e retorna uma lista de objetos serializáveis.
     *
     * @param file Arquivo a ser lido.
     * @param <T> Tipo dos objetos armazenados na lista.
     * @return Uma lista de objetos desserializados do arquivo, ou uma lista vazia em caso de erro.
     */

    public static <T extends Serializable> ArrayList<T> consultarArquivoList(File file) {
        ArrayList<T> list;

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            list = (ArrayList<T>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            list = new ArrayList<>();
        }

        return list;
    }

    /**
     * Sobrescreve o conteúdo de um arquivo com uma lista de objetos serializáveis.
     *
     * @param file Arquivo a ser sobrescrito.
     * @param list Lista de objetos serializáveis a ser escrita.
     * @param <T> Tipo dos objetos armazenados na lista.
     * @return true se a operação for bem-sucedida, false em caso de falha.
     */

    public static <T extends Serializable> boolean sobreescreverArquivo(File file, ArrayList<T> list){
        if (file == null || list == null) {
            return false;
        }
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(list);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Limpa o conteúdo de um arquivo sem removê-lo.
     *
     * @param file Arquivo a ter o conteúdo apagado.
     */
    public static void apagarConteudoArquivo(File file) {
        try {
            new FileOutputStream(file).close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
