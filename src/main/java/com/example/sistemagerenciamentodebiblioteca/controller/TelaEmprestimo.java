package com.example.sistemagerenciamentodebiblioteca.controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import com.example.sistemagerenciamentodebiblioteca.armazenamento.OpenScreen;
import com.example.sistemagerenciamentodebiblioteca.dao.DAO;
import com.example.sistemagerenciamentodebiblioteca.exceptions.EmprestimoException;
import com.example.sistemagerenciamentodebiblioteca.exceptions.LeitorException;
import com.example.sistemagerenciamentodebiblioteca.exceptions.LivroException;
import com.example.sistemagerenciamentodebiblioteca.exceptions.ReservaException;
import com.example.sistemagerenciamentodebiblioteca.model.Emprestimo;
import com.example.sistemagerenciamentodebiblioteca.model.Leitor;
import com.example.sistemagerenciamentodebiblioteca.model.Livro;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class TelaEmprestimo {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnConfirmar;

    @FXML
    private TextField textIdLivro;

    @FXML
    private TextField textIdUsuario;

    @FXML
    void btnBackAction(ActionEvent event) {
        OpenScreen.OpenPage(event,"telaBibliotecario.fxml");
    }

    @FXML
    void btnConfirmarAction(ActionEvent event) throws LivroException, LeitorException, EmprestimoException, ReservaException {
        String idLivro = textIdLivro.getText();
        String idUsuario = textIdUsuario.getText();
        verificaCampo(idLivro, idUsuario);
        verificaNumeroId(idLivro, idUsuario);

        Livro livro = buscarLivro(Integer.parseInt(idLivro));
        Leitor leitor= buscarLeitor(Integer.parseInt(idUsuario));
        emprestar(leitor, livro);
        textIdLivro.clear();
        textIdUsuario.clear();
    }

    private void emprestar(Leitor leitor, Livro livro) throws LivroException, EmprestimoException, LeitorException, ReservaException {
        LocalDate dataHoje = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = dataHoje.format(formatter);

        try {
            DAO.getEmprestimoDAO().create(new Emprestimo(dataFormatada, leitor, livro));
        } catch (LivroException | EmprestimoException | LeitorException | ReservaException e) {
            informationAlert("Error",e);
            throw e;
        }
        informationAlert("Sucesso.","O empréstimo do Livro " +livro.getTitulo() + "foi realizado com sucesso!" );
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

    @FXML
    void textIdLivroAction(ActionEvent event) {

    }

    @FXML
    void textIdUsuarioAction(ActionEvent event) {

    }
    private void informationAlert(String title,String texto){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(texto);
        alert.showAndWait();
    }
    private void informationAlert(String title,Exception e){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(e.getMessage());
        alert.showAndWait();
    }
    private void verificaCampo(String idLivro, String idUsuario){
        if(idLivro.isEmpty() || idUsuario.isEmpty()){
            informationAlert("Error","Por favor, preencha todos os campos.");
            throw new IllegalArgumentException();
        }
    }
    private void verificaNumeroId(String idLivro, String idUsuario){
        try{
            int idLivroInteger = Integer.parseInt(idLivro);
            int idUsuarioInteger = Integer.parseInt(idUsuario);
        } catch (NumberFormatException e){
            informationAlert("Error","Digite apenas Id numérico");
            throw e;
        }
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

    @FXML
    void initialize() {
        assert btnBack != null : "fx:id=\"btnBack\" was not injected: check your FXML file 'telaEmprestimo.fxml'.";
        assert btnConfirmar != null : "fx:id=\"btnConfirmar\" was not injected: check your FXML file 'telaEmprestimo.fxml'.";
        assert textIdLivro != null : "fx:id=\"textIdLivro\" was not injected: check your FXML file 'telaEmprestimo.fxml'.";
        assert textIdUsuario != null : "fx:id=\"textIdUsuario\" was not injected: check your FXML file 'telaEmprestimo.fxml'.";

    }

}
