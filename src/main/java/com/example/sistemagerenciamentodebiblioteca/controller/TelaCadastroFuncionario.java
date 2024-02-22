package com.example.sistemagerenciamentodebiblioteca.controller;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import com.example.sistemagerenciamentodebiblioteca.armazenamento.OpenScreen;
import com.example.sistemagerenciamentodebiblioteca.dao.DAO;
import com.example.sistemagerenciamentodebiblioteca.model.Administrador;
import com.example.sistemagerenciamentodebiblioteca.model.Bibliotecario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class TelaCadastroFuncionario {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private RadioButton btnAdministrador;

    @FXML
    private Button btnBack;

    @FXML
    private RadioButton btnBibliotecario;

    @FXML
    private Button btnCadastrar;

    @FXML
    private HBox hBoxbtn;

    @FXML
    private TextField textConfirmarSenha;

    @FXML
    private TextField textNome;

    @FXML
    private TextField textSenha;

    @FXML
    void btnAdministradorAction(ActionEvent event) {
        desselecionarOutrosRadios(btnAdministrador);
    }

    @FXML
    void btnBackAction(ActionEvent event) {
        OpenScreen.OpenPage(event,"telaAdministrador.fxml");
    }

    @FXML
    void btnBibliotecarioAction(ActionEvent event) {
        desselecionarOutrosRadios(btnBibliotecario);
    }

    @FXML
    void btnCadastrarAction(ActionEvent event) {
        String nome = textNome.getText();
        String senha = textSenha.getText();
        String confirmarSenha = textConfirmarSenha.getText();

        String cargo = cargoSelecionado();
        verificaCampo(nome,senha,confirmarSenha,cargo);
        verificaSenha(senha, confirmarSenha);
        cadastro(nome, senha, cargo);

        informationAlert("Sucesso","Funcionário cadastrado com sucesso.");
        textNome.clear();
        textSenha.clear();
        textConfirmarSenha.clear();
        btnAdministrador.setSelected(false);
        btnBibliotecario.setSelected(false);

    }

    @FXML
    void textConfirmarSenhaAction(ActionEvent event) {

    }

    @FXML
    void textNomeAction(ActionEvent event) {

    }

    @FXML
    void textSenhaAction(ActionEvent event) {

    }
    private void desselecionarOutrosRadios(RadioButton selecionado) {
        for (javafx.scene.Node node : hBoxbtn.getChildren()) {
            if (node instanceof RadioButton && node != selecionado) {
                ((RadioButton) node).setSelected(false);
            }
        }
    }
    private String cargoSelecionado() {
        for (javafx.scene.Node node : hBoxbtn.getChildren()) {
            if (node instanceof RadioButton radioButton) {
                if (radioButton.isSelected()) {
                    return radioButton.getText(); // Retorna o texto do RadioButton selecionado
                }
            }
        }
        return null; // Retorna null se nenhum RadioButton estiver selecionado
    }

    private void verificaCampo(String nome, String senha, String confirmaSenha, String cargo) {
        if (nome.isEmpty() || senha.isEmpty() || confirmaSenha.isEmpty() || cargo == null) {
            informationAlert("Error", "Por favor, preencha todos os campos.");
            throw new IllegalArgumentException();
        }
    }

    private void informationAlert(String title,String texto){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(texto);
        alert.showAndWait();
    }

    private void verificaSenha(String senha, String confirmaSenha){
        if(!Objects.equals(senha, confirmaSenha)){
            informationAlert("Error","As senhas devem ser iguais.");
            throw new IllegalArgumentException();
        }
    }

    private void cadastro(String nome, String senha, String cargo){
        if(Objects.equals(cargo, "Bibliotecário")){
            DAO.getBibliotecarioDAO().create(new Bibliotecario(nome,senha,cargo));
        }else {
            DAO.getAdministradorDAO().create(new Administrador(nome,senha,cargo));
        }
    }
    @FXML
    void initialize() {
        assert btnAdministrador != null : "fx:id=\"btnAdministrador\" was not injected: check your FXML file 'telaCadastroFuncionario.fxml'.";
        assert btnBack != null : "fx:id=\"btnBack\" was not injected: check your FXML file 'telaCadastroFuncionario.fxml'.";
        assert btnBibliotecario != null : "fx:id=\"btnBibliotecario\" was not injected: check your FXML file 'telaCadastroFuncionario.fxml'.";
        assert btnCadastrar != null : "fx:id=\"btnCadastrar\" was not injected: check your FXML file 'telaCadastroFuncionario.fxml'.";
        assert hBoxbtn != null : "fx:id=\"hBoxbtn\" was not injected: check your FXML file 'telaCadastroFuncionario.fxml'.";
        assert textConfirmarSenha != null : "fx:id=\"textConfirmarSenha\" was not injected: check your FXML file 'telaCadastroFuncionario.fxml'.";
        assert textNome != null : "fx:id=\"textNome\" was not injected: check your FXML file 'telaCadastroFuncionario.fxml'.";
        assert textSenha != null : "fx:id=\"textSenha\" was not injected: check your FXML file 'telaCadastroFuncionario.fxml'.";

    }

}
