package com.example.dateme;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;

public class AjustesController {

    @FXML
    private Button ajustes;

    @FXML
    private Button botonCrear;

    @FXML
    private Button botonCrear1;

    @FXML
    private Button botonCrear11;

    @FXML
    private Button botonCrear12;

    @FXML
    private Button chats;

    @FXML
    private Button inicio;

    @FXML
    private Button matches;

    @FXML
    void accionCrear(ActionEvent event) {

    }

    @FXML
    void ajustesClick(ActionEvent event) {
        DateMeApp.cambiarPestana("settings/settings.fxml","Ajustes",900,600);
    }

    @FXML
    void chatsClick(ActionEvent event) {
        DateMeApp.cambiarPestana("chats/chats.fxml","Chats",900,600);
    }

    @FXML
    void inicioClick(ActionEvent event) {
        DateMeApp.cambiarPestana("mainpage/mainpage.fxml","DateMe",900,600);
    }

    @FXML
    void matchesClick(ActionEvent event) {
        DateMeApp.cambiarPestana("match/match.fxml","Matches",900,600);
    }

    @FXML
    void click_log_out(ActionEvent event) {
        DateMeApp.cambiarPestana("login/login.fxml","DateMe",480,630);
    }
}
