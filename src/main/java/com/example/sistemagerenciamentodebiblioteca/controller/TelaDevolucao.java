package com.example.sistemagerenciamentodebiblioteca.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import com.example.sistemagerenciamentodebiblioteca.armazenamento.OpenScreen;
import com.example.sistemagerenciamentodebiblioteca.dao.DAO;
import com.example.sistemagerenciamentodebiblioteca.exceptions.EmprestimoException;
import com.example.sistemagerenciamentodebiblioteca.exceptions.LeitorException;
import com.example.sistemagerenciamentodebiblioteca.exceptions.LivroException;
import com.example.sistemagerenciamentodebiblioteca.model.Emprestimo;
import com.example.sistemagerenciamentodebiblioteca.model.Leitor;
import com.example.sistemagerenciamentodebiblioteca.model.Livro;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class TelaDevolucao {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnDevolver;

    @FXML
    private TextField textDevolverLivro;

    @FXML
    void btnBack(ActionEvent event) {
        OpenScreen.OpenPage(event,"telaBibliotecario.fxml");
    }

    @FXML
    void btnDevolverAction(ActionEvent event) throws LivroException, EmprestimoException, LeitorException {
        String idLivro = textDevolverLivro.getText();

        verificaCampo(idLivro);
        verificaNumeroId(idLivro);

        Livro livro = buscarLivro(Integer.parseInt(idLivro));
        Emprestimo emprestimo = encontraEmprestimo(livro);

        devolverLivro(emprestimo);

        informationAlert("Sucesso","A devolução do livro " + livro.getTitulo() + " foi realizada com sucesso!");

    }

    @FXML
    void textDevolverLivroAction(ActionEvent event) {

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
    private Emprestimo encontraEmprestimo(Livro livro) throws EmprestimoException {
        Emprestimo emprestimo;
        try {
            emprestimo = DAO.getEmprestimoDAO().encontraPorIdDoLivro(livro.getId());
        } catch (EmprestimoException e) {
            informationAlert("Error","Não existe empréstimo ativo para esse livro.");
            throw e;
        }
        return emprestimo;
    }
    private void devolverLivro(Emprestimo emprestimo) throws LivroException, LeitorException {
        LocalDate dataHoje = LocalDate.now();
        try {
           emprestimo.finalizarEmprestimo(dataHoje);
        } catch (LivroException | LeitorException e) {
            informationAlert("Error",e);
            throw e;
        }
    }

    private void informationAlert(String title,Exception e){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(e.getMessage());
        alert.showAndWait();
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
        assert btnBack != null : "fx:id=\"btnBack\" was not injected: check your FXML file 'telaDevolucao.fxml'.";
        assert btnDevolver != null : "fx:id=\"btnDevolver\" was not injected: check your FXML file 'telaDevolucao.fxml'.";
        assert textDevolverLivro != null : "fx:id=\"textDevolverLivro\" was not injected: check your FXML file 'telaDevolucao.fxml'.";

    }

}
