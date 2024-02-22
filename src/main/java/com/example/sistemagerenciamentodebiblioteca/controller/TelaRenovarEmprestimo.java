package com.example.sistemagerenciamentodebiblioteca.controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.example.sistemagerenciamentodebiblioteca.armazenamento.LoginOn;
import com.example.sistemagerenciamentodebiblioteca.armazenamento.OpenScreen;
import com.example.sistemagerenciamentodebiblioteca.dao.DAO;
import com.example.sistemagerenciamentodebiblioteca.exceptions.EmprestimoException;
import com.example.sistemagerenciamentodebiblioteca.exceptions.LivroException;
import com.example.sistemagerenciamentodebiblioteca.model.Emprestimo;
import com.example.sistemagerenciamentodebiblioteca.model.Leitor;
import com.example.sistemagerenciamentodebiblioteca.model.Livro;
import com.example.sistemagerenciamentodebiblioteca.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class TelaRenovarEmprestimo {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnRenovar;

    @FXML
    private TextField textIdLivro;

    @FXML
    void btnBack(ActionEvent event) {
        OpenScreen.OpenPage(event,"telaLeitor.fxml");
    }

    @FXML
    void btnRenovarAction(ActionEvent event) throws Exception {
        String idLivro = textIdLivro.getText();
        Usuario login = LoginOn.getUserInSession();
        Leitor leitor = (Leitor) login;

        verificaCampo(idLivro);
        verificaNumeroId(idLivro);

        Livro livro = buscarLivro(Integer.parseInt(idLivro));
        List<Emprestimo> listDeEmprestimo =buscarListEmprestimo(leitor);

        Emprestimo emprestimoEcontrado= encontraEmprestimo(listDeEmprestimo,livro);

        renovar(emprestimoEcontrado);

    }

    private void renovar(Emprestimo emprestimo) throws Exception {
        LocalDate dataHoje = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = dataHoje.format(formatter);
        try {
            emprestimo.renovarEmprestimo(dataFormatada);
        } catch (EmprestimoException e) {
            informationAlert("Error", e);
        }
    }

    private Emprestimo encontraEmprestimo(List<Emprestimo> emprestimo, Livro livro){

        for(Emprestimo emp: emprestimo){
            if (emp.getLivro().getId()== livro.getId()){
                return emp;
            }
        }
        informationAlert("Error","Empréstimo não encontrado!");
        throw new IllegalArgumentException();
    }
    private List<Emprestimo> buscarListEmprestimo ( Leitor leitor) throws EmprestimoException {
        List<Emprestimo> emprestimo = null;
        try{
            emprestimo = DAO.getEmprestimoDAO().findActiveUser(leitor);

        }catch (Exception e){
            informationAlert("Error","Empréstimo não encontrado!");
            throw e;
        }
        return emprestimo;
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
    void textIdLivroAction(ActionEvent event) {

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
        assert btnBack != null : "fx:id=\"btnBack\" was not injected: check your FXML file 'telaRenovarEmprestimo.fxml'.";
        assert btnRenovar != null : "fx:id=\"btnRenovar\" was not injected: check your FXML file 'telaRenovarEmprestimo.fxml'.";
        assert textIdLivro != null : "fx:id=\"textIdLivro\" was not injected: check your FXML file 'telaRenovarEmprestimo.fxml'.";

    }

}
