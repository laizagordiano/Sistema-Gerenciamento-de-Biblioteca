package com.example.sistemagerenciamentodebiblioteca.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.sistemagerenciamentodebiblioteca.armazenamento.LoginOn;
import com.example.sistemagerenciamentodebiblioteca.armazenamento.OpenScreen;
import com.example.sistemagerenciamentodebiblioteca.dao.DAO;
import com.example.sistemagerenciamentodebiblioteca.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class TelaCadastroLivro {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnCadastro;

    @FXML
    private TextField caixaTextAnoPublicacao;

    @FXML
    private TextField caixaTextAutor;

    @FXML
    private TextField caixaTextCategoria;

    @FXML
    private TextField caixaTextEditora;

    @FXML
    private TextField caixaTextIsbn;

    @FXML
    private TextField caixaTextLocalizacao;

    @FXML
    private TextField caixaTextTitulo;

    @FXML
    private Pane idPane;

    @FXML
    private Text textAnoPublicacao;

    @FXML
    private Text textAutor;

    @FXML
    private Text textCadastroLivro;

    @FXML
    private Text textCategoria;

    @FXML
    private Text textEditora;

    @FXML
    private Text textIsbn;

    @FXML
    private Text textLocalizacao;

    @FXML
    private Text textTitulo;

    @FXML
    void bntCadastroAction(ActionEvent event) {
        String isbn = caixaTextIsbn.getText();
        String titulo = caixaTextTitulo.getText();
        String autor = caixaTextAutor.getText();
        String categoria = caixaTextCategoria.getText();
        String localizacao = caixaTextLocalizacao.getText();
        String editora = caixaTextEditora.getText();
        String anoPublicacao = caixaTextAnoPublicacao.getText();

        verificaCampo(isbn,titulo,autor,anoPublicacao,editora,categoria,localizacao);
        verificaInteiro(anoPublicacao);

        Livro livroRegistrado = DAO.getLivroDAO().create(new
                Livro(titulo,autor,editora,isbn,Integer.parseInt(anoPublicacao),categoria,localizacao));

        if (livroRegistrado != null){
            informationAlert("Registro de livro com sucesso.","O livro foi registrado com sucesso, com o id: " + livroRegistrado.getId());
        } else {
            informationAlert("Error", "Ocorreu um erro ao registrar o livro.");
        }
        caixaTextEditora.clear(); caixaTextLocalizacao.clear(); caixaTextAnoPublicacao.clear();
        caixaTextAutor.clear(); caixaTextIsbn.clear(); caixaTextTitulo.clear(); caixaTextCategoria.clear();

    }

    private void verificaInteiro(String anoPublicacao){
        try{
            int anoInteger = Integer.parseInt(anoPublicacao);
        } catch (NumberFormatException e){
            informationAlert("Error","Digite apenas números no ano de publicação");
            throw e;
        }
    }


    @FXML
    void btnBackAction(ActionEvent event) {
        Usuario usuario= LoginOn.getUserInSession();
        if (usuario instanceof Administrador){
            OpenScreen.OpenPage(event,"telaGereciarLivros.fxml");
        } else if (usuario instanceof Bibliotecario){
            OpenScreen.OpenPage(event,"telaBibliotecario.fxml");
        }
    }

    @FXML
    void caixaTextAnoPublicacaoAction(ActionEvent event) {

    }

    @FXML
    void caixaTextAutorAction(ActionEvent event) {

    }

    @FXML
    void caixaTextCategoriaAction(ActionEvent event) {

    }

    @FXML
    void caixaTextEditoraAction(ActionEvent event) {

    }

    @FXML
    void caixaTextIsbnAction(ActionEvent event) {

    }

    @FXML
    void caixaTextLocalizacaoAction(ActionEvent event) {

    }

    @FXML
    void caixaTextTituloAction(ActionEvent event) {

    }
    private void informationAlert(String title, String texto) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(texto);
        alert.showAndWait();
    }
    private void verificaCampo(String isbn, String titulo, String autor, String anoPublicacao,
                               String editora, String categoria, String localizacao){
        if (isbn.isEmpty() || titulo.isEmpty() || autor.isEmpty()
                || anoPublicacao.isEmpty() || editora.isEmpty() || categoria.isEmpty() || localizacao.isEmpty()){
            informationAlert("Error", "Todos os campos precisam ser preenchidos");
            throw new IllegalArgumentException();
        }

    }

    @FXML
    void initialize() {
        assert btnBack != null : "fx:id=\"btnBack\" was not injected: check your FXML file 'telaCadastroLivro.fxml'.";
        assert btnCadastro != null : "fx:id=\"btnCadastro\" was not injected: check your FXML file 'telaCadastroLivro.fxml'.";
        assert caixaTextAnoPublicacao != null : "fx:id=\"caixaTextAnoPublicacao\" was not injected: check your FXML file 'telaCadastroLivro.fxml'.";
        assert caixaTextAutor != null : "fx:id=\"caixaTextAutor\" was not injected: check your FXML file 'telaCadastroLivro.fxml'.";
        assert caixaTextCategoria != null : "fx:id=\"caixaTextCategoria\" was not injected: check your FXML file 'telaCadastroLivro.fxml'.";
        assert caixaTextEditora != null : "fx:id=\"caixaTextEditora\" was not injected: check your FXML file 'telaCadastroLivro.fxml'.";
        assert caixaTextIsbn != null : "fx:id=\"caixaTextIsbn\" was not injected: check your FXML file 'telaCadastroLivro.fxml'.";
        assert caixaTextLocalizacao != null : "fx:id=\"caixaTextLocalizacao\" was not injected: check your FXML file 'telaCadastroLivro.fxml'.";
        assert caixaTextTitulo != null : "fx:id=\"caixaTextTitulo\" was not injected: check your FXML file 'telaCadastroLivro.fxml'.";
        assert idPane != null : "fx:id=\"idPane\" was not injected: check your FXML file 'telaCadastroLivro.fxml'.";
        assert textAnoPublicacao != null : "fx:id=\"textAnoPublicacao\" was not injected: check your FXML file 'telaCadastroLivro.fxml'.";
        assert textAutor != null : "fx:id=\"textAutor\" was not injected: check your FXML file 'telaCadastroLivro.fxml'.";
        assert textCadastroLivro != null : "fx:id=\"textCadastroLivro\" was not injected: check your FXML file 'telaCadastroLivro.fxml'.";
        assert textCategoria != null : "fx:id=\"textCategoria\" was not injected: check your FXML file 'telaCadastroLivro.fxml'.";
        assert textEditora != null : "fx:id=\"textEditora\" was not injected: check your FXML file 'telaCadastroLivro.fxml'.";
        assert textIsbn != null : "fx:id=\"textIsbn\" was not injected: check your FXML file 'telaCadastroLivro.fxml'.";
        assert textLocalizacao != null : "fx:id=\"textLocalizacao\" was not injected: check your FXML file 'telaCadastroLivro.fxml'.";
        assert textTitulo != null : "fx:id=\"textTitulo\" was not injected: check your FXML file 'telaCadastroLivro.fxml'.";

    }

}
