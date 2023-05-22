package com.example.dateme;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class HomeController {

    @FXML
    void clickCrear(MouseEvent event) {
        DateMeApp.cambiarPestana("signup/signup.fxml","DateMe",480,630);
    }

    @FXML
    void clickIniciar(MouseEvent event) {
        DateMeApp.cambiarPestana("login/login.fxml","DateMe",480,630);
    }

}
