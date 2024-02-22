package com.example.sistemagerenciamentodebiblioteca.controller;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import com.example.sistemagerenciamentodebiblioteca.armazenamento.OpenScreen;
import com.example.sistemagerenciamentodebiblioteca.dao.DAO;
import com.example.sistemagerenciamentodebiblioteca.exceptions.LivroException;
import com.example.sistemagerenciamentodebiblioteca.model.Livro;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

public class TelaAtualizar {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnAtualizar;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnBusca;

    @FXML
    private RadioMenuItem idAnoPublicacao;

    @FXML
    private RadioMenuItem idAutor;

    @FXML
    private RadioMenuItem idCategoria;

    @FXML
    private RadioMenuItem idEditora;

    @FXML
    private RadioMenuItem idIsbn;

    @FXML
    private RadioMenuItem idLocalizacao;

    @FXML
    private RadioMenuItem idTitulo;

    @FXML
    private ListView<Livro> listView;

    @FXML
    private MenuButton menuBtn;

    @FXML
    private TextField textId;

    @FXML
    private TextField textInformacao;

    @FXML
    private HBox hBox;

    @FXML
    void btnAtualizarAction(ActionEvent event) throws LivroException {
        String idLivro = textId.getText();
        String novaInfo = textInformacao.getText();
        verificaCampo(idLivro);
        verificaNumeroId(idLivro);
        verificaCampo(novaInfo);

        Livro livro = buscarLivro(Integer.parseInt(idLivro));
        String infoSeleciona = edicaoSelecionada();
        verificaAtualizacao(infoSeleciona,novaInfo);

        atualizarInfos(livro,infoSeleciona,novaInfo);
        listView.setItems(FXCollections.observableArrayList(livro).sorted());



    }


    private void atualizarInfos(Livro livro, String tipoEdicao, String novaInfo) throws LivroException {
        switch (tipoEdicao){
            case "Título" ->{
                livro.setTitulo(novaInfo);
                DAO.getLivroDAO().update(livro);
            }
            case "Autor" ->{
                livro.setAutor(novaInfo);
                DAO.getLivroDAO().update(livro);
            }
            case "Ano de Publicação" ->{
                livro.setAnoPublicacao(Integer.parseInt(novaInfo));
                DAO.getLivroDAO().update(livro);
            }
            case "ISBN" ->{
                livro.setISBN(novaInfo);
                DAO.getLivroDAO().update(livro);
            }
            case "Categoria" ->{
                livro.setCategoria(novaInfo);
                DAO.getLivroDAO().update(livro);
            }
            case "Editora" ->{
                livro.setEditora(novaInfo);
                DAO.getLivroDAO().update(livro);
            }
            case "Localização" ->{
                livro.setLocalizacao(novaInfo);
                DAO.getLivroDAO().update(livro);
            }
        }
    }

    @FXML
    void btnBackAction(ActionEvent event) {
        OpenScreen.OpenPage(event,"telaGereciarLivros.fxml");
    }

    @FXML
    void btnBuscaAction(ActionEvent event) throws LivroException {
        String idLivro = textId.getText();
        verificaCampo(idLivro);
        verificaNumeroId(idLivro);
        Livro livro = buscarLivro(Integer.parseInt(idLivro));

        listView.setItems(FXCollections.observableArrayList(livro).sorted());
    }

    @FXML
    void idAnoPublicacaoAction(ActionEvent event) {
       desselecionarOutrosRadios(idAnoPublicacao);
    }

    @FXML
    void idAutorAction(ActionEvent event) {
        desselecionarOutrosRadios(idAutor);
    }

    @FXML
    void idCategoriaAction(ActionEvent event) {
        desselecionarOutrosRadios(idCategoria);
    }

    @FXML
    void idEditoraAction(ActionEvent event) {
        desselecionarOutrosRadios(idEditora);
    }

    @FXML
    void idIsbnAction(ActionEvent event) {
        desselecionarOutrosRadios(idIsbn);
    }

    @FXML
    void idLocalizacaoAction(ActionEvent event) {
        desselecionarOutrosRadios(idLocalizacao);
    }

    @FXML
    void idTituloAction(ActionEvent event) {
        desselecionarOutrosRadios(idTitulo);
    }

    @FXML
    void menuBtnAction(ActionEvent event) {

    }

    @FXML
    void textIdAction(ActionEvent event) {

    }

    @FXML
    void textInformacaoAction(ActionEvent event) {

    }
    private void verificaAtualizacao(String tipoEdicao, String novaInfo){
        if(Objects.equals(tipoEdicao,"Ano de Publicação")){
            verificaNumeroId(novaInfo);
        }
    }
    private void desselecionarOutrosRadios(RadioMenuItem selecionado) {
        for (MenuItem item: menuBtn.getItems()) {
            if (item instanceof RadioMenuItem && item != selecionado) {
                ((RadioMenuItem) item).setSelected(false);
            }
        }
    }
    private String edicaoSelecionada() {
        for (MenuItem item : menuBtn.getItems()) {
            if (item instanceof RadioMenuItem radioMenuItem) {
                if (radioMenuItem.isSelected()) {
                    return radioMenuItem.getText(); // Retorna o texto do RadioMenuItem selecionado
                }
            }
        }
        return null; // Retorna null se nenhum RadioMenuItem estiver selecionado
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
            informationAlert("Error","Digite apenas números");
            throw e;
        }
    }


    @FXML
    void initialize() {
        assert btnAtualizar != null : "fx:id=\"btnAtualizar\" was not injected: check your FXML file 'telaAtualizar.fxml'.";
        assert btnBack != null : "fx:id=\"btnBack\" was not injected: check your FXML file 'telaAtualizar.fxml'.";
        assert btnBusca != null : "fx:id=\"btnBusca\" was not injected: check your FXML file 'telaAtualizar.fxml'.";
        assert idAnoPublicacao != null : "fx:id=\"idAnoPublicacao\" was not injected: check your FXML file 'telaAtualizar.fxml'.";
        assert idAutor != null : "fx:id=\"idAutor\" was not injected: check your FXML file 'telaAtualizar.fxml'.";
        assert idCategoria != null : "fx:id=\"idCategoria\" was not injected: check your FXML file 'telaAtualizar.fxml'.";
        assert idEditora != null : "fx:id=\"idEditora\" was not injected: check your FXML file 'telaAtualizar.fxml'.";
        assert idIsbn != null : "fx:id=\"idIsbn\" was not injected: check your FXML file 'telaAtualizar.fxml'.";
        assert idLocalizacao != null : "fx:id=\"idLocalizacao\" was not injected: check your FXML file 'telaAtualizar.fxml'.";
        assert idTitulo != null : "fx:id=\"idTitulo\" was not injected: check your FXML file 'telaAtualizar.fxml'.";
        assert listView != null : "fx:id=\"listView\" was not injected: check your FXML file 'telaAtualizar.fxml'.";
        assert menuBtn != null : "fx:id=\"menuBtn\" was not injected: check your FXML file 'telaAtualizar.fxml'.";
        assert textId != null : "fx:id=\"textId\" was not injected: check your FXML file 'telaAtualizar.fxml'.";
        assert textInformacao != null : "fx:id=\"textInformacao\" was not injected: check your FXML file 'telaAtualizar.fxml'.";

        // Cria um novo ToggleGroup
        ToggleGroup toggleGroup = new ToggleGroup();

        // Adiciona os RadioMenuItems ao ToggleGroup
        idTitulo.setToggleGroup(toggleGroup);
        idAutor.setToggleGroup(toggleGroup);
        idEditora.setToggleGroup(toggleGroup);
        idIsbn.setToggleGroup(toggleGroup);
        idAnoPublicacao.setToggleGroup(toggleGroup);
        idLocalizacao.setToggleGroup(toggleGroup);
        idCategoria.setToggleGroup(toggleGroup);

        // Adiciona um listener de propriedade ao selectedToggleProperty do ToggleGroup
        toggleGroup.selectedToggleProperty().addListener((observable, oldToggle, newToggle) -> {
            if (newToggle != null) {
                RadioMenuItem selectedRadioMenuItem = (RadioMenuItem) newToggle;
                menuBtn.setText(selectedRadioMenuItem.getText()); // Define o texto do MenuButton
            }
        });
    }

}
