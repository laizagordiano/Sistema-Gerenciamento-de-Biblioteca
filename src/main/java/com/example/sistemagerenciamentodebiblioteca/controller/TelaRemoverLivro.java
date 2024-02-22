package com.example.sistemagerenciamentodebiblioteca.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.sistemagerenciamentodebiblioteca.armazenamento.OpenScreen;
import com.example.sistemagerenciamentodebiblioteca.dao.DAO;
import com.example.sistemagerenciamentodebiblioteca.exceptions.LivroException;
import com.example.sistemagerenciamentodebiblioteca.model.Livro;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class TelaRemoverLivro {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnRemover;

    @FXML
    private TextField textIdLivro;

    @FXML
    void btnBackAction(ActionEvent event) {
        OpenScreen.OpenPage(event,"telaGereciarLivros.fxml");
    }

    @FXML
    void btnRemoverAction(ActionEvent event) throws LivroException {
        String idLivro = textIdLivro.getText();

        verificaCampo(idLivro);
        verificaNumeroId(idLivro);
        Livro livro = buscarLivro(Integer.valueOf(idLivro));

        removerLivro(livro);
        informationAlert("Sucesso","O livro " + livro.getTitulo() + " foi removido com sucesso.");



    }

    @FXML
    void textIdLivroAction(ActionEvent event) {

    }

    private void removerLivro(Livro livro) throws LivroException {
        try{
            DAO.getLivroDAO().delete(livro);
        } catch (LivroException e) {
            informationAlert("Error", "Livro não encontrado.");
            throw e;
        }
    }
    private Livro buscarLivro(Integer id) throws LivroException {
        Livro livro;
        try{
            livro = DAO.getLivroDAO().findById(id);
        } catch (LivroException e) {
            informationAlert("ERROR", "Livro não encontrado!");
            throw e;
        }
        return livro;
    }
    private void informationAlert(String title,String e){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(e);
        alert.showAndWait();
    }
    private void verificaCampo(String id){
        if(id.isEmpty()){
            informationAlert("Error","Por favor, preencha todos os campos.");
            throw new IllegalArgumentException();
        }
    }
    private void verificaNumeroId(String id){
        try{
            int idInteger = Integer.parseInt(id);
        } catch (NumberFormatException e){
            informationAlert("Error","Digite apenas Id numérico");
            throw e;
        }
    }


    @FXML
    void initialize() {
        assert btnBack != null : "fx:id=\"btnBack\" was not injected: check your FXML file 'telaRemoverLivro.fxml'.";
        assert btnRemover != null : "fx:id=\"btnRemover\" was not injected: check your FXML file 'telaRemoverLivro.fxml'.";
        assert textIdLivro != null : "fx:id=\"textIdLivro\" was not injected: check your FXML file 'telaRemoverLivro.fxml'.";

    }

}
