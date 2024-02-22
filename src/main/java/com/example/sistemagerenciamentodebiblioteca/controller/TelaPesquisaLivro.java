package com.example.sistemagerenciamentodebiblioteca.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.example.sistemagerenciamentodebiblioteca.armazenamento.LoginOn;
import com.example.sistemagerenciamentodebiblioteca.armazenamento.OpenScreen;
import com.example.sistemagerenciamentodebiblioteca.dao.DAO;
import com.example.sistemagerenciamentodebiblioteca.exceptions.LivroException;
import com.example.sistemagerenciamentodebiblioteca.model.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class TelaPesquisaLivro {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backBtn;

    @FXML
    private RadioMenuItem btnAutor;

    @FXML
    private RadioMenuItem btnCategoria;

    @FXML
    private RadioMenuItem btnIsbn;

    @FXML
    private RadioMenuItem btnTitulo;

    @FXML
    private ListView<Livro> listViewLivros;

    @FXML
    private Button okBtn;

    @FXML
    private MenuButton pesquisaBtn;

    @FXML
    private TextField textPesquisa;

    @FXML
    void btnAutorAction(ActionEvent event) {
        descelecionarOutrosRadios(btnAutor);
    }

    @FXML
    void btnBackAction(ActionEvent event) {
        Usuario usuario= LoginOn.getUserInSession();
        if (usuario instanceof Leitor) {
            OpenScreen.OpenPage(event, "telaLeitor.fxml");
        }else if (usuario instanceof Administrador){
            OpenScreen.OpenPage(event,"telaAdministrador.fxml");
        } else if (usuario instanceof Bibliotecario){
            OpenScreen.OpenPage(event,"telaBibliotecario.fxml");
        }
    }

    @FXML
    void btnCategoriaAction(ActionEvent event) {
        descelecionarOutrosRadios(btnCategoria);
    }

    @FXML
    void btnIsbnAction(ActionEvent event) {
        descelecionarOutrosRadios(btnIsbn);
    }

    @FXML
    void btnOkAction(ActionEvent event) {
    String pesquisa = pesquisaSelecionada();
    if (pesquisa == null){
        informationAlert("Error","Selecione o campo de pesquisa.");
        throw new IllegalArgumentException();
    }
    mostraView(pesquisa,textPesquisa.getText());
    }

    @FXML
    void btnPesquisaAction(ActionEvent event) {

    }

    @FXML
    void btnTituloAction(ActionEvent event) {
        descelecionarOutrosRadios(btnTitulo);
    }

    @FXML
    void textPesquisaAction(ActionEvent event) {

    }
    private String pesquisaSelecionada() {
        for (MenuItem item : pesquisaBtn.getItems()) {
            if (item instanceof RadioMenuItem radioMenuItem) {
                if (radioMenuItem.isSelected()) {
                    return radioMenuItem.getText();
                }
            }
        }
        return null;
    }
    private void descelecionarOutrosRadios(RadioMenuItem selecionado) {
        for (MenuItem item : pesquisaBtn.getItems()) {
            if (item instanceof RadioMenuItem && item != selecionado) {
                ((RadioMenuItem) item).setSelected(false);
            }
        }
    }
    private void informationAlert(String title, String texto) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(texto);
        alert.showAndWait();
    }
    private void mostraView(String tipoDePesquisa, String termoDePesquisa) {
        try {
            ArrayList<Livro> resultados = retornaPesquisa(tipoDePesquisa, termoDePesquisa);
            if (resultados != null) {
                if (!resultados.isEmpty()) {
                    listViewLivros.setItems(FXCollections.observableArrayList(resultados).sorted());
                } else {
                    listViewLivros.getItems().clear();
                    informationAlert("ERROR", "Nenhum resultado encontrado.");
                }
            } else {
                listViewLivros.getItems().clear();
                informationAlert("ERROR", "Nenhum resultado encontrado.");
            }
        } catch (LivroException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<Livro> retornaPesquisa(String selecaoDaPesquisa, String pesquisa) throws LivroException {
        try {
            switch (selecaoDaPesquisa) {
                case "Título" -> {
                    return DAO.getLivroDAO().findTitulo(pesquisa);
                }
                case "Autor" -> {
                    return DAO.getLivroDAO().findAutor(pesquisa);
                }
                case "ISBN" -> {
                    return DAO.getLivroDAO().findISBN(pesquisa);
                }
                case "Categoria" -> {
                    return DAO.getLivroDAO().findCategoria(pesquisa);
                }
                default -> {
                    return null;
                }
            }
        } catch (Exception e) {
            informationAlert("ERROR", "Busca não encontrada!");
            throw e;
        }
    }

    @FXML
    void initialize() {
        assert backBtn != null : "fx:id=\"backBtn\" was not injected: check your FXML file 'telaPesquisaLivro.fxml'.";
        assert btnAutor != null : "fx:id=\"btnAutor\" was not injected: check your FXML file 'telaPesquisaLivro.fxml'.";
        assert btnCategoria != null : "fx:id=\"btnCategoria\" was not injected: check your FXML file 'telaPesquisaLivro.fxml'.";
        assert btnIsbn != null : "fx:id=\"btnIsbn\" was not injected: check your FXML file 'telaPesquisaLivro.fxml'.";
        assert btnTitulo != null : "fx:id=\"btnTitulo\" was not injected: check your FXML file 'telaPesquisaLivro.fxml'.";
        assert listViewLivros != null : "fx:id=\"listViewLivros\" was not injected: check your FXML file 'telaPesquisaLivro.fxml'.";
        assert okBtn != null : "fx:id=\"okBtn\" was not injected: check your FXML file 'telaPesquisaLivro.fxml'.";
        assert pesquisaBtn != null : "fx:id=\"pesquisaBtn\" was not injected: check your FXML file 'telaPesquisaLivro.fxml'.";
        assert textPesquisa != null : "fx:id=\"textPesquisa\" was not injected: check your FXML file 'telaPesquisaLivro.fxml'.";
        // Cria um novo ToggleGroup
        ToggleGroup toggleGroup = new ToggleGroup();

        // Adiciona os RadioMenuItems ao ToggleGroup
        btnAutor.setToggleGroup(toggleGroup);
        btnCategoria.setToggleGroup(toggleGroup);
        btnIsbn.setToggleGroup(toggleGroup);
        btnTitulo.setToggleGroup(toggleGroup);

        // Adiciona um listener de propriedade ao selectedToggleProperty do ToggleGroup
        toggleGroup.selectedToggleProperty().addListener((observable, oldToggle, newToggle) -> {
            if (newToggle != null) {
                RadioMenuItem selectedRadioMenuItem = (RadioMenuItem) newToggle;
                pesquisaBtn.setText(selectedRadioMenuItem.getText());
            }
        });

    }

}
