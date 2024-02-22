package com.example.sistemagerenciamentodebiblioteca.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.sistemagerenciamentodebiblioteca.armazenamento.OpenScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class TelaLeitor {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnPesquisa;

    @FXML
    private Button btnRenovarEmprestimo;

    @FXML
    private Button btnReserva;

    @FXML
    private Button btnSair;

    @FXML
    private Pane idPane;

    @FXML
    private Text texLeitor;

    @FXML
    void btnPesquisaAction(ActionEvent event) {
        OpenScreen.OpenPage(event,"telaPesquisaLivro.fxml");
    }

    @FXML
    void btnRenovarEmprestimoAction(ActionEvent event) {
        OpenScreen.OpenPage(event,"telaRenovarEmprestimo.fxml");
    }

    @FXML
    void btnReservaAction(ActionEvent event) {
        OpenScreen.OpenPage(event,"telaReserva.fxml");
    }

    @FXML
    void btnSairAction(ActionEvent event) {
        OpenScreen.OpenPage(event, "login.fxml");
    }

    @FXML
    void initialize() {
        assert btnPesquisa != null : "fx:id=\"btnPesquisa\" was not injected: check your FXML file 'telaLeitor.fxml'.";
        assert btnRenovarEmprestimo != null : "fx:id=\"btnRenovarEmprestimo\" was not injected: check your FXML file 'telaLeitor.fxml'.";
        assert btnReserva != null : "fx:id=\"btnReserva\" was not injected: check your FXML file 'telaLeitor.fxml'.";
        assert btnSair != null : "fx:id=\"btnSair\" was not injected: check your FXML file 'telaLeitor.fxml'.";
        assert idPane != null : "fx:id=\"idPane\" was not injected: check your FXML file 'telaLeitor.fxml'.";
        assert texLeitor != null : "fx:id=\"texLeitor\" was not injected: check your FXML file 'telaLeitor.fxml'.";

    }

}
