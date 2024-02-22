package com.example.sistemagerenciamentodebiblioteca.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.sistemagerenciamentodebiblioteca.armazenamento.OpenScreen;
import com.example.sistemagerenciamentodebiblioteca.dao.DAO;
import com.example.sistemagerenciamentodebiblioteca.exceptions.LeitorException;
import com.example.sistemagerenciamentodebiblioteca.model.Leitor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class TelaBloquearLeitor {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnBloquear;

    @FXML
    private Button btnDesbloquear;

    @FXML
    private TextField textIdLeitor;

    @FXML
    void btnBackAction(ActionEvent event) {
        OpenScreen.OpenPage(event, "telaAdministrador.fxml");
    }

    @FXML
    void btnBloquearAction(ActionEvent event) throws LeitorException {
        String idLeitor = textIdLeitor.getText();
        verificaCampo(idLeitor);
        verificaNumeroId(idLeitor);

        Leitor leitor = buscarLeitor(Integer.parseInt(idLeitor));
        leitor.bloquearLeitor(leitor);

        informationAlert("Bloqueado","O leitor " +leitor.getNome()+ " foi bloqueado.");
        textIdLeitor.clear();
    }

    @FXML
    void btnDesbloquearAction(ActionEvent event) throws LeitorException {
        String idLeitor = textIdLeitor.getText();
        verificaCampo(idLeitor);
        verificaNumeroId(idLeitor);

        Leitor leitor = buscarLeitor(Integer.parseInt(idLeitor));
        leitor.desbloquearLeitor(leitor);

        informationAlert("Bloqueado","O leitor " +leitor.getNome()+ " foi desbloqueado.");
        textIdLeitor.clear();

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
    private Leitor buscarLeitor(Integer idLeitor) throws LeitorException {
        Leitor leitor;
        try {
            leitor = DAO.getLeitorDAO().findById(idLeitor);
        }catch (Exception e){
            informationAlert("Error", "Leitor não encontrado.");
            throw e;
        } return leitor;
    }

    @FXML
    void initialize() {
        assert btnBack != null : "fx:id=\"btnBack\" was not injected: check your FXML file 'telaBloquearLeitor.fxml'.";
        assert btnBloquear != null : "fx:id=\"btnBloquear\" was not injected: check your FXML file 'telaBloquearLeitor.fxml'.";
        assert btnDesbloquear != null : "fx:id=\"btnDesbloquear\" was not injected: check your FXML file 'telaBloquearLeitor.fxml'.";
        assert textIdLeitor != null : "fx:id=\"textIdLeitor\" was not injected: check your FXML file 'telaBloquearLeitor.fxml'.";

    }

}
