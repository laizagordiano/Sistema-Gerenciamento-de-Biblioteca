package com.example.sistemagerenciamentodebiblioteca.controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.example.sistemagerenciamentodebiblioteca.armazenamento.LoginOn;
import com.example.sistemagerenciamentodebiblioteca.armazenamento.OpenScreen;
import com.example.sistemagerenciamentodebiblioteca.dao.DAO;
import com.example.sistemagerenciamentodebiblioteca.exceptions.LivroException;
import com.example.sistemagerenciamentodebiblioteca.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class TelaReserva {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnReservar;

    @FXML
    private TextField textIdLivro;

    @FXML
    void btnBack(ActionEvent event) {
        OpenScreen.OpenPage(event, "telaLeitor.fxml");
    }

    @FXML
    void btnReservarAction(ActionEvent event) throws LivroException {
        String idLivro = textIdLivro.getText();
        Usuario login = LoginOn.getUserInSession();
        Leitor leitor = (Leitor) login;

        verificaCampo(idLivro);
        verificaNumeroId(idLivro);
        Livro livro = buscarLivro(Integer.parseInt(idLivro));



        verificaAtrasoDeLivro(leitor);
        livroDisponivel(livro);



        Reserva reserva= DAO.getReservaDAO().create(new Reserva(leitor,livro));

        if (reserva != null){
            informationAlert("Reserva de livro com sucesso.","O livro foi reservado com sucesso, com o id: " + reserva.getId());
        } else {
            informationAlert("Error", "Ocorreu um erro ao reservar o livro.");
        }


    }

    private void livroDisponivel(Livro livro) throws LivroException {
        if (DAO.getLivroDAO().findById(livro.getId()).isStatus()){
            informationAlert("Error", "Livro disponível para empréstimos");
            throw new IllegalArgumentException();
        }
    }

    private void verificaAtrasoDeLivro(Leitor leitor){
        LocalDate dataHoje = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = dataHoje.format(formatter);
        Boolean atrasado = DAO.getEmprestimoDAO().verificaAtrasoDoLeitor(leitor,dataFormatada);

        if (atrasado){
            informationAlert("Error", "O leitor esta com livro atrasado para devolução");
            throw  new IllegalArgumentException();
        }
    }

    @FXML
    void textIdLivroAction(ActionEvent event) {

    }



    private Livro buscarLivro(Integer id) throws LivroException {
        Livro livro;
        try{
            livro = DAO.getLivroDAO().findById(id);
        } catch (LivroException e) {
            informationAlert("ERROR", "Livro não encontrado!");
            throw e;
        }
        return livro;
    }

    private void informationAlert(String title,String texto){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(texto);
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
        assert btnBack != null : "fx:id=\"btnBack\" was not injected: check your FXML file 'telaReserva.fxml'.";
        assert btnReservar != null : "fx:id=\"btnReservar\" was not injected: check your FXML file 'telaReserva.fxml'.";
        assert textIdLivro != null : "fx:id=\"textIdLivro\" was not injected: check your FXML file 'telaReserva.fxml'.";

    }

}
