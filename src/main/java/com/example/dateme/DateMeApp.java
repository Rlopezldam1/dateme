package com.example.dateme;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class DateMeApp extends Application {
    public static Stage dateMe;
    @Override
    public void start(Stage stage) throws IOException {
        dateMe = stage;
        URL url = getClass().getResource("imgs/cupido.png");
        Image icon = new Image(url.openStream());
        stage.getIcons().add(icon);
        FXMLLoader loginloader = new FXMLLoader(this.getClass().getResource("login/login.fxml"));
        FXMLLoader signuploader = new FXMLLoader(this.getClass().getResource("signup/signup.fxml"));
        FXMLLoader homeloader = new FXMLLoader(this.getClass().getResource("home/home.fxml"));
        FXMLLoader mensajesloader = new FXMLLoader(this.getClass().getResource("mensajes/mensajes.fxml"));
        FXMLLoader historialloader = new FXMLLoader(this.getClass().getResource("historial/historial.fxml"));
        FXMLLoader mainloader = new FXMLLoader(this.getClass().getResource("mainpage/mainpage.fxml"));
        Scene login = new Scene(loginloader.load(), 480, 630);
        Scene signup = new Scene(signuploader.load(), 480, 630);
        Scene home = new Scene(homeloader.load(), 900, 600);
        Scene mensajes = new Scene(mensajesloader.load(), 600, 400);
        Scene historial = new Scene(historialloader.load(), 600, 400);
        Scene main = new Scene(mainloader.load(), 900, 600);
        dateMe.setTitle("DateMe");
        dateMe.setScene(login);
        dateMe.setResizable(false);
        dateMe.show();
    }

    public static void cambiarPestana(String ruta, String nombreVentana) {
        try {
            FXMLLoader pestañaLoader = new FXMLLoader(DateMeApp.class.getResource(ruta));
            Scene nuevaEscena = new Scene(pestañaLoader.load(), 900, 600);
            dateMe.setScene(nuevaEscena);
            dateMe.setTitle(nombreVentana);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch();
    }
}