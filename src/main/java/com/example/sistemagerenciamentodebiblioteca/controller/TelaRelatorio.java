package com.example.sistemagerenciamentodebiblioteca.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.sistemagerenciamentodebiblioteca.armazenamento.OpenScreen;
import com.example.sistemagerenciamentodebiblioteca.dao.DAO;
import com.example.sistemagerenciamentodebiblioteca.exceptions.EmprestimoException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

public class TelaRelatorio {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnBack;

    @FXML
    private Button idHistoricoEmprestimo;

    @FXML
    private Button idLivrosMaisPopulares;

    @FXML
    private ListView<?> idQtdAtrasados;

    @FXML
    private ListView<?> idQtdEmprestados;

    @FXML
    private ListView<?> idQtdReservados;

    @FXML
    private Text textQtdAtrasados;

    @FXML
    private Text textQtdEmprestimo;

    @FXML
    private Text textQtdReservados;

    @FXML
    private Text textRelatorio;

    @FXML
    void btnBackAction(ActionEvent event) {
        OpenScreen.OpenPage(event, "telaAdministrador.fxml");

    }

    @FXML
    void idHistoricoEmprestimoAction(ActionEvent event) {
        OpenScreen.OpenPage(event,"telaHistorico.fxml");
    }

    @FXML
    void idLivrosMaisPopularesAction(ActionEvent event) {
        OpenScreen.OpenPage(event,"telaPopulares.fxml");
    }

    @FXML
    void initialize() throws EmprestimoException {
        assert btnBack != null : "fx:id=\"btnBack\" was not injected: check your FXML file 'telaRelatorio.fxml'.";
        assert idHistoricoEmprestimo != null : "fx:id=\"idHistoricoEmprestimo\" was not injected: check your FXML file 'telaRelatorio.fxml'.";
        assert idLivrosMaisPopulares != null : "fx:id=\"idLivrosMaisPopulares\" was not injected: check your FXML file 'telaRelatorio.fxml'.";
        assert idQtdAtrasados != null : "fx:id=\"idQtdAtrasados\" was not injected: check your FXML file 'telaRelatorio.fxml'.";
        assert idQtdEmprestados != null : "fx:id=\"idQtdEmprestados\" was not injected: check your FXML file 'telaRelatorio.fxml'.";
        assert idQtdReservados != null : "fx:id=\"idQtdReservados\" was not injected: check your FXML file 'telaRelatorio.fxml'.";
        assert textQtdAtrasados != null : "fx:id=\"textQtdAtrasados\" was not injected: check your FXML file 'telaRelatorio.fxml'.";
        assert textQtdEmprestimo != null : "fx:id=\"textQtdEmprestimo\" was not injected: check your FXML file 'telaRelatorio.fxml'.";
        assert textQtdReservados != null : "fx:id=\"textQtdReservados\" was not injected: check your FXML file 'telaRelatorio.fxml'.";
        assert textRelatorio != null : "fx:id=\"textRelatorio\" was not injected: check your FXML file 'telaRelatorio.fxml'.";


        mostraView((ListView<Integer>) idQtdAtrasados, DAO.getEmprestimoDAO().numLivrosAtrasados());
        mostraView((ListView<Integer>) idQtdEmprestados, DAO.getEmprestimoDAO().numLivrosEmprestados());
        mostraView((ListView<Integer>) idQtdReservados, DAO.getReservaDAO().numReservados());

    }

    private void mostraView(ListView<Integer> lugarParaExibir, Integer exibicao){
        lugarParaExibir.setItems(FXCollections.observableArrayList(exibicao).sorted());
    }

}
