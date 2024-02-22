package com.example.sistemagerenciamentodebiblioteca.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.example.sistemagerenciamentodebiblioteca.HelloApplication;
import com.example.sistemagerenciamentodebiblioteca.armazenamento.OpenScreen;
import com.example.sistemagerenciamentodebiblioteca.dao.DAO;
import com.example.sistemagerenciamentodebiblioteca.exceptions.LivroException;
import com.example.sistemagerenciamentodebiblioteca.model.Livro;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class TelaGerenciarLivros {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuItem btnAdicionar;

    @FXML
    private Button btnBack;

    @FXML
    private MenuButton btnControle;

    @FXML
    private MenuItem btnEditar;

    @FXML
    private MenuItem btnRemover;

    @FXML
    private ListView<Livro> idListView;

    @FXML
    void btnAdicionarAction(ActionEvent event) {
        abrirProximaTelaPeloMenu("telaCadastroLivro.fxml");

    }

    @FXML
    void btnBackAction(ActionEvent event) {
        OpenScreen.OpenPage(event,"telaAdministrador.fxml");
    }

    @FXML
    void btnControleAction(ActionEvent event) {

    }

    @FXML
    void btnEditarAction(ActionEvent event) {
        abrirProximaTelaPeloMenu("telaAtualizar.fxml");

    }

    @FXML
    void btnRemoverAction(ActionEvent event) {
        abrirProximaTelaPeloMenu("telaRemoverLivro.fxml");

    }
    @FXML
    private void abrirProximaTelaPeloMenu(String nome) {
        try {
            // Carregar o arquivo FXML da próxima tela
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(nome));
            Parent root = loader.load();

            // Criar uma nova cena com a próxima tela
            Scene scene = new Scene(root);

            // Obter o palco (stage) do MenuItem usando o método getScene().getWindow()
            Stage stage = (Stage) btnControle.getScene().getWindow();

            // Definir a nova cena no palco (stage)
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void exibeLivros() {
        ArrayList<Livro> resultados = DAO.getLivroDAO().findMany();
        if (resultados != null) {
            if (!resultados.isEmpty()) {
                idListView.setItems(FXCollections.observableArrayList(resultados).sorted());
            } else {
                idListView.getItems().clear();

            }
        } else {
            idListView.getItems().clear();

        }
    }
    @FXML
    void initialize() {
        assert btnAdicionar != null : "fx:id=\"btnAdicionar\" was not injected: check your FXML file 'telaGereciarLivros.fxml'.";
        assert btnBack != null : "fx:id=\"btnBack\" was not injected: check your FXML file 'telaGereciarLivros.fxml'.";
        assert btnControle != null : "fx:id=\"btnControle\" was not injected: check your FXML file 'telaGereciarLivros.fxml'.";
        assert btnEditar != null : "fx:id=\"btnEditar\" was not injected: check your FXML file 'telaGereciarLivros.fxml'.";
        assert btnRemover != null : "fx:id=\"btnRemover\" was not injected: check your FXML file 'telaGereciarLivros.fxml'.";
        assert idListView != null : "fx:id=\"idListView\" was not injected: check your FXML file 'telaGereciarLivros.fxml'.";

        exibeLivros();

    }

}
