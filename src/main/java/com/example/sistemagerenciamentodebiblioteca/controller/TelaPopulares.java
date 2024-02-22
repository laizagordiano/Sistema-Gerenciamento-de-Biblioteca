package com.example.sistemagerenciamentodebiblioteca.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.example.sistemagerenciamentodebiblioteca.armazenamento.OpenScreen;
import com.example.sistemagerenciamentodebiblioteca.dao.DAO;
import com.example.sistemagerenciamentodebiblioteca.model.Livro;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class TelaPopulares {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnBack;

    @FXML
    private ListView<Livro> listViewLivros;

    @FXML
    private ListView<Integer> listViewNumero;

    @FXML
    void btnBackAction(ActionEvent event) {
        OpenScreen.OpenPage(event,"telaRelatorio.fxml");
    }

    @FXML
    void listViewLivrosAction(ActionEvent event) {

    }

    @FXML
    void listViewNumero(ActionEvent event) {

    }

    private void mostraView() {
        try {
            ArrayList<Livro> listPopular = DAO.getEmprestimoDAO().livrosMaisPolulares();
            Integer numLivros= DAO.getEmprestimoDAO().livrosMaisPolulares().size();
            if (listPopular != null ) {
                // Verifica se a lista de resultados não está vazia
                if (!listPopular.isEmpty()) {
                    // Importa a classe javafx.collections.FXCollections para usar o método observableArrayList
                    for (Livro livro : listPopular) {
                        listViewLivros.setItems(FXCollections.observableArrayList(listPopular).sorted());
                    }
                } else {
                    // Se não houver resultados, limpa a lista
                    listViewLivros.getItems().clear();
                }
            } else {
                // Se a lista de resultados for nula, limpa a lista
                listViewLivros.getItems().clear();
            }
        } catch (Exception e) {
            // Trata adequadamente as exceções, se necessário
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        assert btnBack != null : "fx:id=\"btnBack\" was not injected: check your FXML file 'telaPopulares.fxml'.";
        assert listViewLivros != null : "fx:id=\"listViewLivros\" was not injected: check your FXML file 'telaPopulares.fxml'.";
        assert listViewNumero != null : "fx:id=\"listViewNumero\" was not injected: check your FXML file 'telaPopulares.fxml'.";

        ArrayList<Livro> listPopular = DAO.getEmprestimoDAO().livrosMaisPolulares();
        Integer numLivros= DAO.getEmprestimoDAO().livrosMaisPolulares().size();
        listViewLivros.setItems(FXCollections.observableArrayList(listPopular).sorted());
        listViewNumero.setItems(FXCollections.observableArrayList(numLivros).sorted());
    }

}
