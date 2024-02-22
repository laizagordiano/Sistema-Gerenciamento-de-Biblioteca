package com.example.sistemagerenciamentodebiblioteca.controller;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import com.example.sistemagerenciamentodebiblioteca.armazenamento.LoginOn;
import com.example.sistemagerenciamentodebiblioteca.armazenamento.OpenScreen;
import com.example.sistemagerenciamentodebiblioteca.dao.DAO;
import com.example.sistemagerenciamentodebiblioteca.exceptions.LeitorException;
import com.example.sistemagerenciamentodebiblioteca.model.Administrador;
import com.example.sistemagerenciamentodebiblioteca.model.Bibliotecario;
import com.example.sistemagerenciamentodebiblioteca.model.Leitor;
import com.example.sistemagerenciamentodebiblioteca.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class LoginController{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private RadioButton administradorBtn;

    @FXML
    private RadioButton bibliotecarioBtn;

    @FXML
    private Button botaoLogin;

    @FXML
    private RadioButton leitorBtn;

    @FXML
    private TextField nomeUsuario;

    @FXML
    private PasswordField senhaUsuario;

    @FXML
    private VBox vBoxbtn;
    private ToggleGroup toggleGroup;
    @FXML
    void btnAdministradorAction(ActionEvent event) {
        desselecionarOutrosRadios(administradorBtn);
    }

    @FXML
    void btnBibliotecarioAction(ActionEvent event) {
        desselecionarOutrosRadios(bibliotecarioBtn);

    }

    @FXML
    void btnLeitorAction(ActionEvent event) {
        desselecionarOutrosRadios(leitorBtn);
    }

    @FXML
    void botaoLoginAction(ActionEvent event) throws Exception {
        String idUsuario = nomeUsuario.getText();
        String senha = senhaUsuario.getText();
        String cargo = cargoSelecionado();
        verificaBloqueio(idUsuario, Objects.requireNonNull(cargo));

        verificaCampo(idUsuario, senha, cargo);
        verificaNumeroId(idUsuario);

        Usuario usuario =buscaUsuario(Integer.parseInt(idUsuario), cargo);
        login(usuario,senha);
        LoginOn.loginUser(usuario);


        assert cargo != null;
        String proxTela = proxPagina(cargo);
        OpenScreen.OpenPage(event, proxTela);

    }

    private void login(Usuario usuario, String senha){
        if (usuario instanceof Leitor){
            if (!usuario.getSenha().equals(senha)){
                informationAlert("Error","Senha incorreta.");
                throw new IllegalArgumentException();
            }
        } else if (usuario instanceof Administrador){
            if (!usuario.getSenha().equals(senha)){
                informationAlert("Error","Senha incorreta.");
                throw new IllegalArgumentException();
            }
        }else if (usuario instanceof  Bibliotecario){
            if (!usuario.getSenha().equals(senha)){
                informationAlert("Error","Senha incorreta.");
                throw new IllegalArgumentException();
            }
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

    private Usuario buscaUsuario(Integer idUsuario, String cargo) throws Exception {
        try{
            switch (cargo){
                case "Leitor" -> {
                    return DAO.getLeitorDAO().findById(idUsuario);
                }
                case "Bibliotecário" -> {
                    return DAO.getBibliotecarioDAO().findById(idUsuario);
                }
                case "Administrador" ->{
                    return DAO.getAdministradorDAO().findById(idUsuario);
                }
                default -> {
                    return null;
                }
            }
        } catch (Exception e){
            informationAlert("Error","Id não encontrado.");
            throw e;
        }
    }

    private void verificaCampo(String nome, String senha, String cargo){
        if(nome.isEmpty() || senha.isEmpty() || cargo== null){
            informationAlert("Error","Por favor, preencha todos os campos.");
            throw new IllegalArgumentException();
        }
    }

    private String proxPagina(String cargo){
        switch (cargo){
            case "Bibliotecário" ->{
                return "telaBibliotecario.fxml";
            }case "Leitor" -> {
                return "telaLeitor.fxml";
            }case "Administrador" -> {
                return "telaAdministrador.fxml";
            }default -> {
                return null;
            }
        }
    }
    private void desselecionarOutrosRadios(RadioButton selecionado) {
        for (javafx.scene.Node node : vBoxbtn.getChildren()) {
            if (node instanceof RadioButton && node != selecionado) {
                ((RadioButton) node).setSelected(false);
            }
        }
    }
    private String cargoSelecionado() {
        for (javafx.scene.Node node : vBoxbtn.getChildren()) {
            if (node instanceof RadioButton radioButton) {
                if (radioButton.isSelected()) {
                    return radioButton.getText(); // Retorna o texto do RadioButton selecionado
                }
            }
        }
        return null; // Retorna null se nenhum RadioButton estiver selecionado
    }


    @FXML
    void nomeUsuarioAction(ActionEvent event) {

    }

    @FXML
    void senhaUsuarioAction(ActionEvent event) {

    }
    private void informationAlert(String title,String texto){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(texto);
        alert.showAndWait();
    }

    private void verificaBloqueio(String id, String cargo) throws LeitorException {
        if (cargo.equals("Leitor")){
            Leitor leitor = DAO.getLeitorDAO().findById(Integer.parseInt(id));
            if (!leitor.isStatus()){
                informationAlert("Error","Usuário bloqueado.");
                throw new IllegalArgumentException();
            }
        }
    }

    @FXML
    void initialize() {
        assert administradorBtn != null : "fx:id=\"administradorBnt\" was not injected: check your FXML file 'login.fxml'.";
        assert bibliotecarioBtn != null : "fx:id=\"bibliotecarioBnt\" was not injected: check your FXML file 'login.fxml'.";
        assert botaoLogin != null : "fx:id=\"botaoLogin\" was not injected: check your FXML file 'login.fxml'.";
        assert leitorBtn != null : "fx:id=\"leitorBnt\" was not injected: check your FXML file 'login.fxml'.";
        assert nomeUsuario != null : "fx:id=\"nomeUsuario\" was not injected: check your FXML file 'login.fxml'.";
        assert senhaUsuario != null : "fx:id=\"senhaUsuario\" was not injected: check your FXML file 'login.fxml'.";

    }

}
