package com.example.sistemagerenciamentodebiblioteca.armazenamento;


import com.example.sistemagerenciamentodebiblioteca.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public abstract class OpenScreen {
    public static void OpenPage(ActionEvent event, String nomeProximaTela) {
        try {
            Stage currentScreen = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentScreen.close();
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(nomeProximaTela));
            Parent root = loader.load();
            Stage mainPage = new Stage();
            Scene scene = new Scene(root);
            mainPage.setResizable(false);
            mainPage.setScene(scene);
            mainPage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }
