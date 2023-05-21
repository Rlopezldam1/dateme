package com.example.dateme;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class chatsController {

    @FXML
    private Button ajustes;

    @FXML
    private HBox chat1;

    @FXML
    private Button chats;

    @FXML
    private Button inicio;

    @FXML
    private Button matches;

    @FXML
    void ajustesClick(MouseEvent event) {
        DateMeApp.cambiarPestana("settings/settings.fxml","Ajustes",900,600);
    }

    @FXML
    void chatsClick(MouseEvent event) {
        DateMeApp.cambiarPestana("chats/chats.fxml","Chats",900,600);
    }

    @FXML
    void inicioClick(MouseEvent event) {
        DateMeApp.cambiarPestana("mainpage/mainpage.fxml","DateMe",900,600);
    }

    @FXML
    void matchesClick(MouseEvent event) {
        DateMeApp.cambiarPestana("match/match.fxml","Matches",900,600);
    }

}
