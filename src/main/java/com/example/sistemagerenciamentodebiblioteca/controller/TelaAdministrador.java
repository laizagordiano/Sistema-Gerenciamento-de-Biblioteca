package com.example.sistemagerenciamentodebiblioteca.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.sistemagerenciamentodebiblioteca.armazenamento.OpenScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class TelaAdministrador {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button bntBloquearUsuario;

    @FXML
    private Button bntRegistrarUsuario;

    @FXML
    private Button bntSolicitarRelatorio;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnGerenciarLivros;

    @FXML
    private Button registraFuncionario;

    @FXML
    void bntBloquearUsuarioAction(ActionEvent event) {
        OpenScreen.OpenPage(event,"telaBloquearLeitor.fxml");
    }

    @FXML
    void bntRegistrarUsuarioAction(ActionEvent event) {
        OpenScreen.OpenPage(event,"telaCadastroLeitor.fxml");
    }

    @FXML
    void bntSolicitarRelatorioAction(ActionEvent event) {
        OpenScreen.OpenPage(event,"telaRelatorio.fxml");
    }

    @FXML
    void btnBackAction(ActionEvent event) {
        OpenScreen.OpenPage(event,"login.fxml");
    }

    @FXML
    void btnGerenciarLivrosAction(ActionEvent event) {
        OpenScreen.OpenPage(event,"telaGereciarLivros.fxml");

    }


    @FXML
    void registraFuncionarioAction(ActionEvent event) {
        OpenScreen.OpenPage(event,"telaCadastroFuncionario.fxml");
    }

    @FXML
    void initialize() {
        assert bntBloquearUsuario != null : "fx:id=\"bntGerenciaUsuario\" was not injected: check your FXML file 'telaAdministrador.fxml'.";
        assert bntRegistrarUsuario != null : "fx:id=\"bntRegistrarUsuario\" was not injected: check your FXML file 'telaAdministrador.fxml'.";
        assert bntSolicitarRelatorio != null : "fx:id=\"bntSolicitarRelatorio\" was not injected: check your FXML file 'telaAdministrador.fxml'.";
        assert btnBack != null : "fx:id=\"btnBack\" was not injected: check your FXML file 'telaAdministrador.fxml'.";
        assert btnGerenciarLivros != null : "fx:id=\"btnGerenciarLivros\" was not injected: check your FXML file 'telaAdministrador.fxml'.";
        assert registraFuncionario != null : "fx:id=\"registraFuncionario\" was not injected: check your FXML file 'telaAdministrador.fxml'.";

    }

}
