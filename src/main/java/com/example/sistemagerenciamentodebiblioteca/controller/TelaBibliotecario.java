package com.example.sistemagerenciamentodebiblioteca.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.sistemagerenciamentodebiblioteca.armazenamento.OpenScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class TelaBibliotecario {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnLogOut;

    @FXML
    private Button btnPesquisarLivros;

    @FXML
    private Button btnRegistrarDevolucao;

    @FXML
    private Button btnRegistrarEmprestimo;

    @FXML
    private Button btnRegistrarNovoLivro;

    @FXML
    void btnLogOutAction(ActionEvent event) {
        OpenScreen.OpenPage(event,"login.fxml");
    }

    @FXML
    void btnPesquisarLivrosAction(ActionEvent event) {
        OpenScreen.OpenPage(event,"telaPesquisaLivro.fxml");
    }

    @FXML
    void btnRegistrarDevolucaoAction(ActionEvent event) {
        OpenScreen.OpenPage(event,"telaDevolucao.fxml");
    }

    @FXML
    void btnRegistrarEmprestimoAction(ActionEvent event) {
        OpenScreen.OpenPage(event,"telaEmprestimo.fxml");
    }

    @FXML
    void btnRegistrarNovoLivroAction(ActionEvent event) {
        OpenScreen.OpenPage(event,"telaCadastroLivro.fxml");
    }

    @FXML
    void initialize() {
        assert btnLogOut != null : "fx:id=\"btnLogOut\" was not injected: check your FXML file 'telaBibliotecario.fxml'.";
        assert btnPesquisarLivros != null : "fx:id=\"btnPesquisarLivros\" was not injected: check your FXML file 'telaBibliotecario.fxml'.";
        assert btnRegistrarDevolucao != null : "fx:id=\"btnRegistrarDevolucao\" was not injected: check your FXML file 'telaBibliotecario.fxml'.";
        assert btnRegistrarEmprestimo != null : "fx:id=\"btnRegistrarEmprestimo\" was not injected: check your FXML file 'telaBibliotecario.fxml'.";
        assert btnRegistrarNovoLivro != null : "fx:id=\"btnRegistrarNovoLivro\" was not injected: check your FXML file 'telaBibliotecario.fxml'.";

    }

}
