package com.example.dateme;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.Double.MAX_VALUE;

public class chatsController implements Initializable {

    @FXML
    private Button ajustes;

    @FXML
    private HBox chat1;

    @FXML
    private Button chats;

    @FXML
    private VBox vboxChats;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String consultaResultados = SQLiteConnection.ejecutarConsulta("SELECT m.perfil_id, u.foto FROM mensajes m JOIN usuarios u ON m.perfil_id = u.id_usuario where m.user_id = '" + DateMeApp.nombreUsuarioIniciado + "'");
        consultaResultados += SQLiteConnection.ejecutarConsulta("SELECT m.user_id, u.foto FROM mensajes m JOIN usuarios u ON m.user_id = u.id_usuario where m.perfil_id = '" + DateMeApp.nombreUsuarioIniciado + "'");
        String[] lineas = consultaResultados.split("\n");

        // Recorrer las líneas y separar los campos
        for (String linea : lineas) {
            String[] campos = linea.split(";");
        // Obtener los valores de los campos
            if (campos.length == 2) {
                String nombre = campos[0];
                String foto = campos[1];
                HBox hbox = new HBox();
                hbox.setPadding(new Insets(15,0,0,0));
                ImageView iv = new ImageView();
                iv.setFitWidth(60);
                iv.setFitHeight(60);
                String ruta = "fotosperfil/" + foto;
                URL urlFoto = getClass().getResource(ruta);
                Image img = null;
                try {
                    img = new Image(urlFoto.openStream());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                iv.setImage(img);
                HBox.setMargin(iv, new Insets(5,0,0,5));
                Label label = new Label();
                HBox.setHgrow(label, Priority.ALWAYS);
                HBox.setMargin(label, new Insets(17,0,0,5));
                label.setText(nombre);
                label.setTextFill(Paint.valueOf("black"));
                label.setFont(Font.font(20));
                label.setAlignment(Pos.CENTER_LEFT);
                Image finalImg = img;
                label.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        try {
                            DateMeApp.cambiarChat(label.getText(), finalImg);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
                hbox.getChildren().add(iv);
                hbox.getChildren().add(label);
                vboxChats.getChildren().add(hbox);
            }


        }
    }
}
