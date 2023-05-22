package com.example.dateme;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class DateMeApp extends Application {
    public static Image fotoUsuario;
    public static String nombreUsuarioIniciado;
    public static Stage dateMe;
    public void start(Stage stage) throws IOException {
        SQLiteConnection.main();
        LogGenerator.main();
        dateMe = stage;
        URL url = getClass().getResource("imgs/cupido.png");
        Image icon = new Image(url.openStream());
        stage.getIcons().add(icon);
        FXMLLoader loginloader = new FXMLLoader(this.getClass().getResource("login/login.fxml"));
        FXMLLoader signuploader = new FXMLLoader(this.getClass().getResource("signup/signup.fxml"));
        FXMLLoader homeloader = new FXMLLoader(this.getClass().getResource("home/home.fxml"));
        FXMLLoader mensajesloader = new FXMLLoader(this.getClass().getResource("mensajes/mensajes.fxml"));
        FXMLLoader matchloader = new FXMLLoader(this.getClass().getResource("match/match.fxml"));
        FXMLLoader mainloader = new FXMLLoader(this.getClass().getResource("mainpage/mainpage.fxml"));
        FXMLLoader perfilUsuarioloader = new FXMLLoader(this.getClass().getResource("perfilUsuario/perfilUsuario.fxml"));
        Scene login = new Scene(loginloader.load(), 480, 630);
        Scene signup = new Scene(signuploader.load(), 480, 630);
        Scene home = new Scene(homeloader.load(), 900, 600);
        Scene mensajes = new Scene(mensajesloader.load(), 900, 600);
        Scene match = new Scene(matchloader.load(), 900, 600);
        Scene perfilUsuario = new Scene(perfilUsuarioloader.load(), 900, 600);
        dateMe.setTitle("DateMe");
        dateMe.setScene(home);
        dateMe.setResizable(false);
        dateMe.show();
        SQLiteConnection.main();
    }

    public static void cambiarPestana(String ruta, String nombreVentana, int width, int height) {
        try {
            FXMLLoader pestañaLoader = new FXMLLoader(DateMeApp.class.getResource(ruta));
            Scene nuevaEscena = new Scene(pestañaLoader.load(), width, height);
            dateMe.setScene(nuevaEscena);
            dateMe.setTitle(nombreVentana);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void cambiarADatos(String nombreUsuario, Image foto) throws IOException {
        cambiarPestana("perfilUsuario/perfilUsuario.fxml", "Perfil",900,600);
        perfilUsuarioController.cargarDatosUsuario(nombreUsuario,foto);
    }
    public static void cambiarChat(String nombreUsuario, Image foto) throws IOException {
        cambiarPestana("mensajes/mensajes.fxml", "Mensajes",900,600);
        MensajesController.cambiarChat(nombreUsuario, foto);
    }


    public static void main(String[] args) {
        launch();
    }
}