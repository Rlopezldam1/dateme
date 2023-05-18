package com.example.dateme;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class perfilUsuarioController {

    @FXML
    private Button ajustes;

    @FXML
    private Label campoApellidos;

    @FXML
    private Label campoDescripcion;

    @FXML
    private Label campoEdad;

    @FXML
    private Label campoId;

    @FXML
    private ImageView campoImagen;

    @FXML
    private Label campoLocalidad;

    @FXML
    private Label campoNombre;

    @FXML
    private Button chats;

    @FXML
    private Button inicio;

    @FXML
    private Button matches;

    @FXML
    void ajustesClick(MouseEvent event) {
        DateMeApp.cambiarPestana("ajustes/ajustes.fxml","Ajustes");
    }

    @FXML
    void chatsClick(MouseEvent event) {
        DateMeApp.cambiarPestana("chats/chats.fxml","Chats");
    }

    @FXML
    void inicioClick(MouseEvent event) {
        DateMeApp.cambiarPestana("mainpage/mainpage.fxml","Inicio");
    }

    @FXML
    void matchesClick(MouseEvent event) {
        DateMeApp.cambiarPestana("matches/matches.fxml","Matches");
    }

}
