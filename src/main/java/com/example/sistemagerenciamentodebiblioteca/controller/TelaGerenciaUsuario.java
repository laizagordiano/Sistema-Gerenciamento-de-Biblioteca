package com.example.sistemagerenciamentodebiblioteca.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class TelaGerenciaUsuario {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnApagarUsuario;

    @FXML
    private Button btnAtualizaInfo;

    @FXML
    private Button btnBloquearUsuario;

    @FXML
    private Pane idPane;

    @FXML
    private Text textGerenciaUsusarios;

    @FXML
    void btnApagarUsuarioAction(ActionEvent event) {

    }

    @FXML
    void btnAtualizaInfoAction(ActionEvent event) {

    }

    @FXML
    void btnBloquearUsuarioActin(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert btnApagarUsuario != null : "fx:id=\"btnApagarUsuario\" was not injected: check your FXML file 'telaGerenciaUsuarios.fxml'.";
        assert btnAtualizaInfo != null : "fx:id=\"btnAtualizaInfo\" was not injected: check your FXML file 'telaGerenciaUsuarios.fxml'.";
        assert btnBloquearUsuario != null : "fx:id=\"btnBloquearUsuario\" was not injected: check your FXML file 'telaGerenciaUsuarios.fxml'.";
        assert idPane != null : "fx:id=\"idPane\" was not injected: check your FXML file 'telaGerenciaUsuarios.fxml'.";
        assert textGerenciaUsusarios != null : "fx:id=\"textGerenciaUsusarios\" was not injected: check your FXML file 'telaGerenciaUsuarios.fxml'.";

    }

}
