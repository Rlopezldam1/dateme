package com.example.dateme;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class LoginController {

    private final String MAINPAGE = "mainpage/mainpage.fxml";

    private final String SIGNUP = "signup/signup.fxml";

    @FXML
    private Button botonCambiarSignup;

    @FXML
    private Button botonIngresar;

    @FXML
    private PasswordField campoContraseña;

    @FXML
    private TextField campoIdUsuario;

    @FXML
    private Label mensajeError;

    @FXML
    void cambiarSignup(ActionEvent event) {
        cambiarEscena(event, SIGNUP, 480, 630);
    }

    @FXML
    void actionIngresar(ActionEvent event) {
        if (GestorUsuarios.existeUsuario(campoIdUsuario.getText()) != null && validarCredenciales()) {
            cambiarEscena(event, MAINPAGE, 900, 600);
        } else {
            campoIdUsuario.setText("");
            campoContraseña.setText("");
            mensajeError.setVisible(true);
        }
    }

    public boolean validarCredenciales() {
        //TODO validar credenciales de usuario con la BBDD
        Usuario usuario = GestorUsuarios.existeUsuario(campoIdUsuario.getText());
        if (usuario.getIdUsuario().equals(campoIdUsuario.getText()) && usuario.getContraseñaUsuario().equals(campoContraseña.getText())) {
            return true;
        }
        return false;
    }

    public void cambiarEscena(ActionEvent event, String fxml, int width, int height) {
        Button botonEvento = (Button) event.getSource();
        Stage stage = (Stage) botonEvento.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(fxml));
        try {
            Scene escena = new Scene(loader.load(), width, height);
            stage.setScene(escena);
        } catch(IOException exception) {
            exception.printStackTrace();
        }
    }
}
