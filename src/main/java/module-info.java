module com.example.sistemagerenciamentodebiblioteca {
    requires javafx.controls;
    requires javafx.fxml;
            
        requires org.controlsfx.controls;
                            
    opens com.example.sistemagerenciamentodebiblioteca to javafx.fxml;
    exports com.example.sistemagerenciamentodebiblioteca;
    exports com.example.sistemagerenciamentodebiblioteca.controller;
    opens com.example.sistemagerenciamentodebiblioteca.controller to javafx.fxml;
}