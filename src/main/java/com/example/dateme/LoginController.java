package com.example.dateme;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
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
        System.out.println(event.toString());
        if (GestorUsuarios.existeUsuario(campoIdUsuario.getText()) != null && validarCredenciales()) {
            GestorUsuarios.inicializarUsuarioActual(campoIdUsuario.getText());
            cambiarEscena(event, MAINPAGE, 900, 600);
            DateMeApp.nombreUsuarioIniciado = campoIdUsuario.getText();
            String rutaFoto = SQLiteConnection.ejecutarConsulta("SELECT foto FROM usuarios where id_usuario = '" + campoIdUsuario.getText() + "'");
            String rutaFotoFinal = rutaFoto.substring(0, rutaFoto.length() - 1);
            String ruta = "fotosperfil/" + rutaFotoFinal;
            URL urlFoto = getClass().getResource(ruta);
            Image img = null;
            try {
                img = new Image(urlFoto.openStream());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            DateMeApp.fotoUsuario = img;
        } else {
            campoIdUsuario.setText("");
            campoContraseña.setText("");
            mensajeError.setVisible(true);
        }
    }

    public boolean validarCredenciales() {
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

    @FXML
    void teclaPulsada(KeyEvent event) {
        KeyCode caracter = event.getCode();
        if (caracter == KeyCode.ENTER){
            actionIngresar(new ActionEvent(botonIngresar, null));
        }
    }

}
