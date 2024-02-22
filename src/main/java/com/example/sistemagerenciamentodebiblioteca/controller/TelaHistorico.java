package com.example.sistemagerenciamentodebiblioteca.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.example.sistemagerenciamentodebiblioteca.armazenamento.OpenScreen;
import com.example.sistemagerenciamentodebiblioteca.dao.DAO;
import com.example.sistemagerenciamentodebiblioteca.exceptions.LeitorException;
import com.example.sistemagerenciamentodebiblioteca.model.Emprestimo;
import com.example.sistemagerenciamentodebiblioteca.model.Leitor;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class TelaHistorico {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnBusca;

    @FXML
    private ListView<Emprestimo> listView;

    @FXML
    private TextField textId;

    @FXML
    void btnBackAction(ActionEvent event) {
        OpenScreen.OpenPage(event,"telaRelatorio.fxml");
    }

    @FXML
    void btnBuscaAction(ActionEvent event) throws LeitorException {
        String idLeitor = textId.getText();
        verificaCampo(idLeitor);
        verificaNumeroId(idLeitor);

        Leitor leitor = buscarLeitor(Integer.parseInt(idLeitor));
        mostraView(leitor);


    }

    @FXML
    void textIdAction(ActionEvent event) {

    }

    private void mostraView(Leitor leitor) {
        try {
            ArrayList<Emprestimo> listEmprestimo = DAO.getEmprestimoDAO().findEmprestimosUser(leitor);
            if (listEmprestimo != null) {
                // Verifica se a lista de resultados não está vazia
                if (!listEmprestimo.isEmpty()) {
                    // Importe a classe javafx.collections.FXCollections para usar o método observableArrayList
                    for (Emprestimo emprestimos : listEmprestimo) {
                        listView.setItems(FXCollections.observableArrayList(listEmprestimo).sorted());
                    }
                } else {
                    // Se não houver resultados, limpa a lista
                    listView.getItems().clear();
                    informationAlert("ERROR", "Nenhum resultado encontrado.");
                    throw new IllegalArgumentException();
                }
            } else {
                // Se a lista de resultados for nula, limpa a lista
                listView.getItems().clear();
                informationAlert("ERROR", "Nenhum resultado encontrado.");
                throw new IllegalArgumentException();
            }
        } catch (Exception e) {
            // Trata adequadamente as exceções, se necessário
            e.printStackTrace();
        }
    }

    private Leitor buscarLeitor(Integer id) throws LeitorException {
        Leitor leitor;
        try{
            leitor = DAO.getLeitorDAO().findById(id);
        } catch (LeitorException e) {
            informationAlert("Error", "Leitor não encontrado!");
            throw e;
        }
        return leitor;
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
        assert btnBack != null : "fx:id=\"btnBack\" was not injected: check your FXML file 'telaHistorico.fxml'.";
        assert btnBusca != null : "fx:id=\"btnBusca\" was not injected: check your FXML file 'telaHistorico.fxml'.";
        assert listView != null : "fx:id=\"listView\" was not injected: check your FXML file 'telaHistorico.fxml'.";
        assert textId != null : "fx:id=\"textId\" was not injected: check your FXML file 'telaHistorico.fxml'.";

    }

}
