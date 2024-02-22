package com.example.sistemagerenciamentodebiblioteca.controller;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import com.example.sistemagerenciamentodebiblioteca.armazenamento.OpenScreen;
import com.example.sistemagerenciamentodebiblioteca.dao.DAO;
import com.example.sistemagerenciamentodebiblioteca.model.Leitor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class TelaCadastroLeitor {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnCadastro;

    @FXML
    private Text idCadastroUsuario;

    @FXML
    private Pane idPane;

    @FXML
    private TextField idTextConfirmarSenha;

    @FXML
    private TextField idTextEndereco;

    @FXML
    private TextField idTextNome;

    @FXML
    private TextField idTextSenha;

    @FXML
    private TextField idTextTelefone;

    @FXML
    private Text textConfirmarSenha;

    @FXML
    private Text textEndereco;

    @FXML
    private Text textNomeusuario;

    @FXML
    private Text textSenha;

    @FXML
    private Text textTelefone;

    @FXML
    void btnBackAction(ActionEvent event) {
        OpenScreen.OpenPage(event,"telaAdministrador.fxml");
    }

    @FXML
    void btnCadastroAction(ActionEvent event) {
        String nome = idTextNome.getText();
        String endereco = idTextEndereco.getText();
        String senha = idTextSenha.getText();
        String confirmaSenha = idTextConfirmarSenha.getText();
        String telefone = idTextTelefone.getText();

        verificaCampo(nome,endereco,senha,confirmaSenha,telefone);
        verificaSenha(senha, confirmaSenha);

        Leitor leitor = DAO.getLeitorDAO().create(new Leitor(nome,endereco,telefone,senha));

        if (leitor != null){
            informationAlert("Sucesso","Usuário cadastrado com sucesso, com o id: "+ leitor.getNumeroID() );
        } else{
            informationAlert("Error","Erro ao cadastrar usuário");
        }

    }

    @FXML
    void idTextConfirmarSenhaAction(ActionEvent event) {

    }

    @FXML
    void idTextEnderecoAction(ActionEvent event) {

    }

    @FXML
    void idTextNomeAction(ActionEvent event) {

    }

    @FXML
    void idTextSenhaAction(ActionEvent event) {

    }

    @FXML
    void idTextTelefoneAction(ActionEvent event) {

    }
    private void verificaCampo(String nome, String endereco, String senha, String confirmaSenha, String telefone){
        if(nome.isEmpty() || endereco.isEmpty() || senha.isEmpty() || confirmaSenha.isEmpty() || telefone.isEmpty()){
            informationAlert("Error","Por favor, preencha todos os campos.");
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

    @FXML
    void initialize() {
        assert btnBack != null : "fx:id=\"btnBack\" was not injected: check your FXML file 'telaCadastroLeitor.fxml'.";
        assert btnCadastro != null : "fx:id=\"btnCadastro\" was not injected: check your FXML file 'telaCadastroLeitor.fxml'.";
        assert idCadastroUsuario != null : "fx:id=\"idCadastroUsuario\" was not injected: check your FXML file 'telaCadastroLeitor.fxml'.";
        assert idPane != null : "fx:id=\"idPane\" was not injected: check your FXML file 'telaCadastroLeitor.fxml'.";
        assert idTextConfirmarSenha != null : "fx:id=\"idTextConfirmarSenha\" was not injected: check your FXML file 'telaCadastroLeitor.fxml'.";
        assert idTextEndereco != null : "fx:id=\"idTextEndereco\" was not injected: check your FXML file 'telaCadastroLeitor.fxml'.";
        assert idTextNome != null : "fx:id=\"idTextNome\" was not injected: check your FXML file 'telaCadastroLeitor.fxml'.";
        assert idTextSenha != null : "fx:id=\"idTextSenha\" was not injected: check your FXML file 'telaCadastroLeitor.fxml'.";
        assert idTextTelefone != null : "fx:id=\"idTextTelefone\" was not injected: check your FXML file 'telaCadastroLeitor.fxml'.";
        assert textConfirmarSenha != null : "fx:id=\"textConfirmarSenha\" was not injected: check your FXML file 'telaCadastroLeitor.fxml'.";
        assert textEndereco != null : "fx:id=\"textEndereco\" was not injected: check your FXML file 'telaCadastroLeitor.fxml'.";
        assert textNomeusuario != null : "fx:id=\"textNomeusuario\" was not injected: check your FXML file 'telaCadastroLeitor.fxml'.";
        assert textSenha != null : "fx:id=\"textSenha\" was not injected: check your FXML file 'telaCadastroLeitor.fxml'.";
        assert textTelefone != null : "fx:id=\"textTelefone\" was not injected: check your FXML file 'telaCadastroLeitor.fxml'.";

    }

}
