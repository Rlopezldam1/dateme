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
import javafx.stage.Window;

import java.io.IOException;

public class SignUpController {

    private final String MAINPAGE = "mainpage/mainpage.fxml";

    private final String LOGIN = "login/login.fxml";

    @FXML
    private Button botonCrear;

    @FXML
    private Label mensajeError;
    @FXML
    private Button botonCuentaExistente;

    @FXML
    private PasswordField campoContraseña;

    @FXML
    private TextField campoIdUsuario;

    @FXML
    private PasswordField campoRepetirContraseña;

    @FXML
    void accionCrear(ActionEvent event) {
        if (!validarCampos()) {
            mensajeError.setVisible(true);
            campoIdUsuario.setText("");
            campoContraseña.setText("");
            campoRepetirContraseña.setText("");
        }
        else {
            //TODO crear usuario, introducirlo en la BBDD
            cambiarEscena(event, MAINPAGE, 900, 600);
        }
    }

    /*
    * Cambia a la pantalla de LogIn si el usuario ya tiene cuenta
    */
    @FXML
    void cambiarLogIn(ActionEvent event) {
        cambiarEscena(event, LOGIN, 480, 630);
    }

    /*
    * Valida que los campos no esten vacios, que no
    * contengan espacios y que la contraseña sea la
    * misma en los dos campos.
    */
    public boolean validarCampos() {
        //TODO validar que el idUsuario no esta en la BBDD
        String idUsuario = campoIdUsuario.getText();
        String contraseña = campoContraseña.getText();
        String contraseñaRepetida = campoRepetirContraseña.getText();
        if (idUsuario.equals("") || contraseña.equals("") || contraseñaRepetida.equals("")) {
            return false;
        }
        else if (idUsuario.contains(" ") || contraseña.contains(" ") || contraseñaRepetida.contains(" ")) {
            return false;
        }
        else if (!contraseña.equals(contraseñaRepetida)) {
            return false;
        }
        return true;
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
